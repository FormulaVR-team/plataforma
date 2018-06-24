package com.fvr._comun.mail;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.fvr.FuentesDeDatos.BDConexion;
import com.fvr._comun.StExcepcion;
import com.fvr._comun.Subrutinas;
import com.fvr._comun._K;
import com.fvr.es_eventSusbscriptions.bean.EsBean;
import com.fvr.ev_events.bean.EvBean;
import com.fvr.rs_reservations.bean.RsBean;
import com.fvr.us_users.bean.UsBean;

import net.sf.json.JSONObject;

public class SendMail implements Serializable {

	private static final long serialVersionUID = 7773950270400468338L;
	private final static String preHTML = "X=X=";						// Secuencia de caracteres prefijo del patrón.
	private final static String posHTML = "=X=X";						// Secuencia de caracteres sufijo del patrón.

	////////////////////////////////////////////
	public class UtilDataMail {
		public String smtp_HOST_NAME; 
		public String smtp_AUTH_USER; 
		public String smtp_AUTH_PWD;
		public String smtp_MAIL_PORT; 
		public String from;
		public String to;
		public String subject;
		public String text_html;

		public UtilDataMail fromJSON( String jsonText ) {
			try { 
				if ( jsonText == null ) { return null; }
				JSONObject json = JSONObject.fromObject( jsonText ); 
				if ( json == null ) { return null; }
				try { this.smtp_HOST_NAME = json.getString("smtp_HOST_NAME"); } catch(Exception e) {;}
				try { this.smtp_AUTH_USER = json.getString("smtp_AUTH_USER"); } catch(Exception e) {;}
				try { this.smtp_AUTH_PWD = json.getString("smtp_AUTH_PWD"); } catch(Exception e) {;}
				try { this.smtp_MAIL_PORT = json.getString("smtp_MAIL_PORT"); } catch(Exception e) {;} 
				try { this.from = json.getString("from"); } catch(Exception e) {;}
				try { this.to = json.getString("to"); } catch(Exception e) {;}
				try { this.subject = json.getString("subject"); } catch(Exception e) {;}
				try { this.text_html = json.getString("text_html"); } catch(Exception e) {;}
			} catch(Exception e) {
				e.printStackTrace();
			}
			return this;
		}
		
		public JSONObject toJSON(
			String smtp_HOST_NAME, 
			String smtp_AUTH_USER, 
			String smtp_AUTH_PWD, 
			String smtp_MAIL_PORT, 
			String from, 
			String to, 
			String subject, 
			String text_html, 
			String replyTo_o_null
			) {
			JSONObject resultado = new JSONObject();
			resultado.put("smtp_HOST_NAME", smtp_HOST_NAME);
			resultado.put("smtp_AUTH_USER", smtp_AUTH_USER); 
			resultado.put("smtp_AUTH_PWD", smtp_AUTH_PWD);
			resultado.put("smtp_MAIL_PORT", smtp_MAIL_PORT);
			resultado.put("from", from);
			resultado.put("to", to);
			resultado.put("subject", subject);
			resultado.put("text_html", text_html);
			return resultado;
		}
	}
	public static void sendMail( 
		final String SMTP_HOST_NAME, 
		final String SMTP_AUTH_USER, 
		final String SMTP_AUTH_PWD, 
		final String SMTP_MAIL_PORT,
		final String from,
		final String to,
		String subject,
		final String text_html,
		final String replyTo_o_null
		) throws MessagingException {

		// Ejemplo: "smtp.mail.yahoo.com", "emilio_estecha@yahoo.es", "secretita", "465"
		
		subject = validarSubject(subject); // quitar acentos en el Subject
		System.out.println("Enviando Mail a '" + to + "' '" + subject + "'...");
		String autorParaLog = (from.trim().length()>50)?from.substring(0,50):from;
		BDConexion dataBase = null;
		try {
			dataBase = new BDConexion();
		} catch (StExcepcion e) {
			System.err.println( e.getCause() + "\n" +	e.getMessage() );
		}

		Properties props = new Properties();
		props.setProperty("mail.smtps.host" , SMTP_HOST_NAME);
		props.setProperty("mail.smtps.user" , SMTP_AUTH_USER);
		props.setProperty("mail.smtps.password", SMTP_AUTH_PWD);
		//	To use TLS
		//	props.setProperty("mail.smtps.auth", "true"); 
		props.setProperty("mail.smtps.starttls.enable", "false");	// No TLS
		//To use SSL
		props.setProperty("mail.smtps.socketFactory.port", SMTP_MAIL_PORT);
		props.setProperty("mail.smtps.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtps.auth", "true");
		props.setProperty("mail.smtps.port", SMTP_MAIL_PORT);	 
		// Para que funcione el SMTP server montado por Pedro:
		props.setProperty("mail.smtps.ssl.trust",SMTP_HOST_NAME);	 
		props.setProperty("mail.debug", "false");
				
/////////////////////////////////////////////////////////////////////////////////////////////
//	Me cascaba en Glassfish 4.0 en internet.
//	Hubo que corregir 'getDefaultInstance' por 'getInstance':
//	http://stackoverflow.com/questions/11566772/java-mail-api-exception-thrown-saying-java-lang-securityexception-access-to-d
//				Session session = Session.getDefaultInstance(
//							props,
//								new Authenticator() {
//										protected PasswordAuthentication	getPasswordAuthentication() {
//										return new PasswordAuthentication(
//												SMTP_AUTH_USER, SMTP_AUTH_PWD);
//														}
//								}
//						);				
		Session session = Session.getInstance(
			props,
			new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(
						SMTP_AUTH_USER, SMTP_AUTH_PWD);
				}
			}
		);				
/////////////////////////////////////////////////////////////////////////////////////////////
				
		// Versión con Auth y sin SSL:
		// Transport transport = session.getTransport("smtp");	// Sin SSL
		Transport transport = session.getTransport("smtps");	// Con SSL
		transport.connect();
		Message message = new MimeMessage(session);
		// ¿múltiples destinatarios?
		String[] destinatarios = to.split(",");
		if ( destinatarios == null || destinatarios.length == 1) {
			message.setRecipient(Message.RecipientType.TO, new InternetAddress( to ));
		} else {
			for( String destinatario : destinatarios) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress( destinatario ));
			}
		}
		try {
			String trozos[] = from.split("::");
			// 0:Texto de remitente, 1:eMail 
			if ( trozos != null && trozos.length == 2 ) {
				message.setFrom(new InternetAddress( trozos[1], trozos[0] ));
			} else {
				message.setFrom(new InternetAddress( from, from ));
			}
		} catch (UnsupportedEncodingException e1) { e1.printStackTrace(); }

		// 2015-07-02
		if ( replyTo_o_null != null && replyTo_o_null.trim().length() > 0 ) {
			message.setReplyTo( new javax.mail.Address[] { new InternetAddress( replyTo_o_null ) } );
			// 2015-07-15
// Ver: http://stackoverflow.com/questions/1782659/how-to-set-the-return-path-to-an-email-address-other-than-sender-address-using-j
//					MAL MAL MAL la mete pero la vuelve a meter el RELY server => message.setHeader("Return-Path", replyTo_o_null);
//					props.put("mail.smtp.from", replyTo_o_null);	
		}

		message.setSubject( subject );

		// "multipart". Se ganan puntos de "reputación" en el mail si se incluye además del HTML otra part TEXT:
		// Crear un envoltorio "multipart"
		Multipart multipart = new MimeMultipart();
		// Crear un "part"
		BodyPart messageBodyPart = new MimeBodyPart();
		// La primera part será el mensaje principal
		messageBodyPart.setContent( text_html, "text/html" );
		multipart.addBodyPart(messageBodyPart);
		// Crear otro "part"
		BodyPart messageBodyPart_TEXT = new MimeBodyPart();
		messageBodyPart_TEXT.setContent(" ", "text/plain" );
		multipart.addBodyPart(messageBodyPart_TEXT);

		///////////////////////////////////
//		// Opcionalmente se puede attachar un fichero al mensaje.
//		if ( this.lstFileNames2Attach_o_null != null && this.lstFileNames2Attach_o_null.length > 0 ) {
//			
//			// Las siguientes parts serán los attachment
//			for ( String fileName : this.lstFileNames2Attach_o_null ) {
//				if ( fileName != null && fileName.trim().length() > 0 ) {
//					messageBodyPart = new MimeBodyPart();
//					DataSource source = new FileDataSource( fileName );
//					messageBodyPart.setDataHandler(new DataHandler(source));
//					messageBodyPart.setFileName( subject + ".pdf" );
//					multipart.addBodyPart(messageBodyPart);
//					System.out.print(" attach:'" + fileName + "' ");
//				}
//			}
//
//			// Meter las parts en el mensaje
//			message.setContent(multipart);
//
//		} else {
////			message.setText( text );
//				message.setContent( text_html, "text/html" );
				message.setContent(multipart);
//		}
//		///////////////////////////////////

		message.saveChanges();	// don't forget this
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();

		System.out.print("enviado OK.");
		Subrutinas.addLog(dataBase, autorParaLog, "Mail enviado OK de '" + from + "' a '" + to + "'", SendMail.class.getSimpleName() + ".sendMail()", text_html);
	}
	public static String validarSubject(String subject) {
		String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
		String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
		String output = subject;
		for (int i=0; i<original.length(); i++) {
			output = output.replace(original.charAt(i), ascii.charAt(i));
		}
		return output;
}
	////////////////////////////////////////////

	public static String send_registroNuevoUsuario( BDConexion dataBase, final String url_base, final String user_id, final List<String> errores, boolean isEnviar ) {

		// - - - - - - - - - - 
		///////////////////////
		String idioma_ISO = "es";
		String eMailDestino = user_id;
		String domain = Subrutinas.getDBValueFromKey(dataBase, _K.PA_KEY_DOMAIN);
		String molde_pa = _K.PA_KEY_MAIL_NEW_USER_REGISTRATION;
		String docHtml = Subrutinas.getDBValueFromKey(dataBase, molde_pa);
		if ( docHtml == null ) { errores.add("No hallado en PA el molde del mail o está vacío " + molde_pa); return docHtml; }
		String smtp_mail_from = Subrutinas.getDBValueFromKey(dataBase, _K.PA_KEY_SMTP_MAIL_REGISTER_FROM) + "@" + domain;
		String smtp_mail_subject = Subrutinas.getDBValueFromKey(dataBase, _K.PA_KEY_SMTP_MAIL_REGISTER_SUBJECT);

		// Variables de macrosustitución en este molde de mail:
		String callBack;
		String urlImagenes;
		String textoBoton = "Nuevo usuario";
		///////////////////////
		// - - - - - - - - - - 
		

		///////////////////////
		// Conseguir una clave única para el callback:
		String token_id = Subrutinas.getHashFromRandomCode();
		while ( ! "".equalsIgnoreCase( Subrutinas.getTkFromId(dataBase, token_id).getTk_sincro() ) ) {
			// Si tiene "Sincro" es que ya existía...
			token_id = Subrutinas.getHashFromRandomCode();
		}
		com.fvr.tk_tokens.db.TkAccesoBaseDatos dao_tk = new com.fvr.tk_tokens.db.TkAccesoBaseDatos();
		com.fvr.tk_tokens.bean.TkBean reg_tk = new com.fvr.tk_tokens.bean.TkBean();
		reg_tk.setTk_author( smtp_mail_from );
		reg_tk.setTk_token_id( token_id );
		///////////////////////


		///////////////////////
		// Macrosustitución de valores:
		urlImagenes	= url_base + "/emails/"+idioma_ISO+"/img/";
		callBack	= url_base 					
					+ "/ConfirmServlet?key=" 
					+ token_id 
					+ "&lng="+idioma_ISO
					;	// Llamada de continuación en el servidor:

		docHtml = Subrutinas.macroSustituye( docHtml, preHTML + "LOGO_IMG_SRC" + posHTML, urlImagenes + "logo-FVR-20170603.png");
		docHtml = Subrutinas.macroSustituye( docHtml, preHTML + "TEXTO_BOTON"  + posHTML, textoBoton);
		docHtml = Subrutinas.macroSustituye( docHtml, preHTML + "CALLBACK" + posHTML, callBack);
		///////////////////////


		if ( !isEnviar ) {
			return docHtml;
		}


		/////////////////////////////////////
		// EMISIÓN DEL CORREO:
		/////////////////////////////////////
		try {
			String replyTo_o_null = null;
			String smtp_host_name = Subrutinas.getDBValueFromKey(dataBase, _K.PA_KEY_SMTP_HOST_NAME); 
			String smtp_auth_user = Subrutinas.getDBValueFromKey(dataBase, _K.PA_KEY_SMTP_AUTH_USER);
			String smtp_auth_pwd  = Subrutinas.getDBValueFromKey(dataBase, _K.PA_KEY_SMTP_AUTH_PWD);
			String smtp_mail_port = Subrutinas.getDBValueFromKey(dataBase, _K.PA_KEY_SMTP_MAIL_PORT);
			if (	   
					   smtp_host_name != null && smtp_host_name.trim().length() > 0
					&& smtp_auth_user != null && smtp_auth_user.trim().length() > 0
					&& smtp_auth_pwd  != null && smtp_auth_pwd.trim().length() > 0
					&& smtp_mail_port != null && smtp_mail_port.trim().length() > 0
					&& smtp_mail_from != null && smtp_mail_from.trim().length() > 0
					&& smtp_mail_subject != null && smtp_mail_subject.trim().length() > 0
					) {
				
				sendMail(
						smtp_host_name, 
						smtp_auth_user, 
						smtp_auth_pwd, 
						smtp_mail_port,
						smtp_mail_from,
						eMailDestino,
						smtp_mail_subject,
						docHtml,
						replyTo_o_null
						);

				try {
					JSONObject json = new JSONObject();
					json.put("acc", molde_pa);
					json.put("eMailDestino", eMailDestino);
					json.put("docHtml", docHtml);
					
					reg_tk.setTk_json( json.toString() );
					dao_tk.tk_crtObj(dataBase, reg_tk);
				} catch (StExcepcion e) {
					errores.add("ERROR " + e.getMessage());
				}

			}
		} catch (MessagingException e) {
			errores.add("ERROR " + e.getMessage());
		}
		/////////////////////////////////////
		
		return docHtml;

	}

	public static String send_CambiarPassword( BDConexion dataBase, final String url_base, final String user_id, final List<String> errores, boolean isEnviar ) {

		// - - - - - - - - - - 
		///////////////////////
		String idioma_ISO = "es";
		String eMailDestino = user_id;
		String domain = Subrutinas.getDBValueFromKey(dataBase, _K.PA_KEY_DOMAIN);
		String molde_pa = _K.PA_KEY_MAIL_CHANGE_PASSWORD;
		String docHtml = Subrutinas.getDBValueFromKey(dataBase, molde_pa);
		if ( docHtml == null || docHtml.trim().length() < 1 ) { errores.add("No hallado en PA el molde del mail o está vacío " + molde_pa); return docHtml; }
		String smtp_mail_from = Subrutinas.getDBValueFromKey(dataBase, _K.PA_KEY_SMTP_MAIL_CHANGE_PASSWORD_FROM) + "@" + domain;
		String smtp_mail_subject = Subrutinas.getDBValueFromKey(dataBase, _K.PA_KEY_SMTP_MAIL_CHANGE_PASSWORD_SUBJECT);

		// Variables de macrosustitución en este molde de mail:
		String callBack;
		String urlImagenes;
		String textoBoton = "Cambiar mi contraseña";
		///////////////////////
		// - - - - - - - - - - 
		

		///////////////////////
		// Conseguir una clave única para el callback:
		String token_id = Subrutinas.getHashFromRandomCode();
		while ( ! "".equalsIgnoreCase( Subrutinas.getTkFromId(dataBase, token_id).getTk_sincro() ) ) {
			// Si tiene "Sincro" es que ya existía...
			token_id = Subrutinas.getHashFromRandomCode();
		}
		com.fvr.tk_tokens.db.TkAccesoBaseDatos dao_tk = new com.fvr.tk_tokens.db.TkAccesoBaseDatos();
		com.fvr.tk_tokens.bean.TkBean reg_tk = new com.fvr.tk_tokens.bean.TkBean();
		reg_tk.setTk_token_id( token_id );
		reg_tk.setTk_author( user_id );
		///////////////////////


		///////////////////////
		// Macrosustitución de valores:
		urlImagenes	= url_base + "/emails/"+idioma_ISO+"/img/";
		callBack	= url_base 					
					+ "/ConfirmServlet?key=" 
					+ token_id 
					+ "&lng="+idioma_ISO
					;	// Llamada de continuación en el servidor:

		docHtml = Subrutinas.macroSustituye( docHtml, preHTML + "LOGO_IMG_SRC" + posHTML, urlImagenes + "logo-FVR-20170603.png");
		docHtml = Subrutinas.macroSustituye( docHtml, preHTML + "TEXTO_BOTON"  + posHTML, textoBoton);
		docHtml = Subrutinas.macroSustituye( docHtml, preHTML + "CALLBACK" + posHTML, callBack);
		///////////////////////


		if ( !isEnviar ) {
			return docHtml;
		}


		/////////////////////////////////////
		// EMISIÓN DEL CORREO:
		/////////////////////////////////////
		try {
			String replyTo_o_null = null;
			String smtp_host_name = Subrutinas.getDBValueFromKey(dataBase, _K.PA_KEY_SMTP_HOST_NAME); 
			String smtp_auth_user = Subrutinas.getDBValueFromKey(dataBase, _K.PA_KEY_SMTP_AUTH_USER);
			String smtp_auth_pwd  = Subrutinas.getDBValueFromKey(dataBase, _K.PA_KEY_SMTP_AUTH_PWD);
			String smtp_mail_port = Subrutinas.getDBValueFromKey(dataBase, _K.PA_KEY_SMTP_MAIL_PORT);
			if (	   
					   smtp_host_name != null && smtp_host_name.trim().length() > 0
					&& smtp_auth_user != null && smtp_auth_user.trim().length() > 0
					&& smtp_auth_pwd  != null && smtp_auth_pwd.trim().length() > 0
					&& smtp_mail_port != null && smtp_mail_port.trim().length() > 0
					&& smtp_mail_from != null && smtp_mail_from.trim().length() > 0
					&& smtp_mail_subject != null && smtp_mail_subject.trim().length() > 0
					) {
				
				sendMail(
						smtp_host_name, 
						smtp_auth_user, 
						smtp_auth_pwd, 
						smtp_mail_port,
						smtp_mail_from,
						eMailDestino,
						smtp_mail_subject,
						docHtml,
						replyTo_o_null
						);

				try {
					JSONObject json = new JSONObject();
					json.put("acc", molde_pa);
					json.put("eMailDestino", eMailDestino);
					json.put("docHtml", docHtml);
					
					reg_tk.setTk_json( json.toString() );
					dao_tk.tk_crtObj(dataBase, reg_tk);
				} catch (StExcepcion e) {
					errores.add("ERROR " + e.getMessage());
				}

			}
		} catch (MessagingException e) {
			errores.add("ERROR " + e.getMessage());
		}
		/////////////////////////////////////
		
		return docHtml;

	}

	public static String send_comprobanteReserva( BDConexion dataBase, final String url_base, final String user_id, final String reservation_id, final List<String> errores, boolean isEnviar  ) {

		RsBean reg_rs = Subrutinas.getRsFromId(dataBase, reservation_id);
		// if ( reg_rs == null || reg_rs.getRs_sincro() == null || reg_rs.getRs_sincro().trim().length() == 0 || !reg_rs.getRs_pay_status().contains(_K.OK) ) {
		if ( reg_rs == null || reg_rs.getRs_sincro() == null || reg_rs.getRs_sincro().trim().length() == 0 ) {
			errores.add( "Clave de reserva " + reservation_id + " no hallada." );
			return null;
		}

		// - - - - - - - - - - 
		///////////////////////
		String idioma_ISO = "es";
		String eMailDestino = user_id;
		String domain = Subrutinas.getDBValueFromKey(dataBase, _K.PA_KEY_DOMAIN);
		String molde_pa = _K.PA_KEY_MAIL_RESERVE_CONFIRM;
		String docHtml = Subrutinas.getDBValueFromKey(dataBase, molde_pa);
		if ( docHtml == null ) { errores.add("No hallado en PA el molde del mail o está vacío " + molde_pa); return docHtml; }
		String smtp_mail_from = Subrutinas.getDBValueFromKey(dataBase, _K.PA_KEY_SMTP_MAIL_RESERVE_CONFIRM_FROM) + "@" + domain;
		String smtp_mail_subject = Subrutinas.getDBValueFromKey(dataBase, _K.PA_KEY_SMTP_MAIL_RESERVE_CONFIRM_SUBJECT) 
				+ " " + reg_rs.getRs_reservation_id()
				+ " " + reg_rs.getRs_LO_name() 
				+ " " + reg_rs.getRs_start_date() 
				+ " " + Subrutinas.getStartTime( reg_rs.getRs_start_time() )
				;

		// Variables de macrosustitución en este molde de mail:
		String callBack;
		String urlImagenes;
		String textoBoton = reservation_id;
		///////////////////////
		// - - - - - - - - - - 
		

		///////////////////////
		// Conseguir una clave única para el callback:
		String token_id = Subrutinas.getHashFromRandomCode();
		while ( ! "".equalsIgnoreCase( Subrutinas.getTkFromId(dataBase, token_id).getTk_sincro() ) ) {
			// Si tiene "Sincro" es que ya existía...
			token_id = Subrutinas.getHashFromRandomCode();
		}
		com.fvr.tk_tokens.db.TkAccesoBaseDatos dao_tk = new com.fvr.tk_tokens.db.TkAccesoBaseDatos();
		com.fvr.tk_tokens.bean.TkBean reg_tk = new com.fvr.tk_tokens.bean.TkBean();
		reg_tk.setTk_token_id( token_id );
		reg_tk.setTk_author( user_id );
		///////////////////////


		///////////////////////
		// Macrosustitución de valores:
		urlImagenes	= url_base + "/emails/"+idioma_ISO+"/img/";
		callBack	= url_base 					
					+ "/ConfirmServlet?key=" 
					+ token_id 
					+ "&lng="+idioma_ISO
					;	// Llamada de continuación en el servidor:

		docHtml = Subrutinas.macroSustituye( docHtml, preHTML + "LOGO_IMG_SRC" + posHTML, urlImagenes + "logo-FVR-20170603.png");
		docHtml = Subrutinas.macroSustituye( docHtml, preHTML + "TEXTO_BOTON"  + posHTML, textoBoton);
		docHtml = Subrutinas.macroSustituye( docHtml, preHTML + "CALLBACK" + posHTML, callBack);
		///////////////////////


		if ( !isEnviar ) {
			return docHtml;
		}


		/////////////////////////////////////
		// EMISIÓN DEL CORREO:
		/////////////////////////////////////
		try {
			String replyTo_o_null = null;
			String smtp_host_name = Subrutinas.getDBValueFromKey(dataBase, _K.PA_KEY_SMTP_HOST_NAME); 
			String smtp_auth_user = Subrutinas.getDBValueFromKey(dataBase, _K.PA_KEY_SMTP_AUTH_USER);
			String smtp_auth_pwd  = Subrutinas.getDBValueFromKey(dataBase, _K.PA_KEY_SMTP_AUTH_PWD);
			String smtp_mail_port = Subrutinas.getDBValueFromKey(dataBase, _K.PA_KEY_SMTP_MAIL_PORT);
			if (	   
					   smtp_host_name != null && smtp_host_name.trim().length() > 0
					&& smtp_auth_user != null && smtp_auth_user.trim().length() > 0
					&& smtp_auth_pwd  != null && smtp_auth_pwd.trim().length() > 0
					&& smtp_mail_port != null && smtp_mail_port.trim().length() > 0
					&& smtp_mail_from != null && smtp_mail_from.trim().length() > 0
					&& smtp_mail_subject != null && smtp_mail_subject.trim().length() > 0
					) {
				
				sendMail(
						smtp_host_name, 
						smtp_auth_user, 
						smtp_auth_pwd, 
						smtp_mail_port,
						smtp_mail_from,
						eMailDestino,
						smtp_mail_subject,
						docHtml,
						replyTo_o_null
						);

				try {
					JSONObject json = new JSONObject();
					json.put("acc", molde_pa);
					json.put("eMailDestino", eMailDestino);
					json.put("docHtml", docHtml);
					
					reg_tk.setTk_json( json.toString() );
					dao_tk.tk_crtObj(dataBase, reg_tk);
				} catch (StExcepcion e) {
					errores.add("ERROR " + e.getMessage());
				}
				
				////////////////////
				// 2018-06-20 EED: Pedro quiere que además se envíe un correo a reservas@formulavr.net
				try {
					String nick = Subrutinas.getUsFromId(dataBase, user_id).us_nick; nick = (nick==null)?"":nick;
					String datos = smtp_mail_subject + "    " + eMailDestino;
					datos += (nick.trim().length()>0)?"     " + nick:"";
					sendMail(
							smtp_host_name, 
							smtp_auth_user, 
							smtp_auth_pwd, 
							smtp_mail_port,
							smtp_mail_from,
							"reservas@formulavr.net",	// eMailDestino,
							smtp_mail_subject,
							datos,						// docHtml,
							null						// replyTo_o_null
							);
				} catch (MessagingException e) {
					;
				}
				////////////////////

			}
		} catch (MessagingException e) {
			errores.add("ERROR " + e.getMessage());
		}
		/////////////////////////////////////
		
		return docHtml;

	}

	public static String send_OleOleEntrada_20180131( BDConexion dataBase, final String url_base, final EsBean reg_es, final List<String> errores, boolean isEnviar  ) {

		String event_id = reg_es.getEs_event_id();
		String user_id = reg_es.getEs_inscription_user_id();
		String reservation_id = reg_es.getEs_tpv_order();
		
		UsBean reg_us = Subrutinas.getUsFromId(dataBase, user_id);
		EvBean reg_ev = Subrutinas.getEvFromId(dataBase, event_id);

		// - - - - - - - - - - 
		///////////////////////
		String idioma_ISO = "es";
		String eMailDestino = user_id;
		String domain = Subrutinas.getDBValueFromKey(dataBase, _K.PA_KEY_DOMAIN);
		String molde_pa = _K.PA_KEY_MAIL_OLEOLE_CONFIRM;
		String docHtml = Subrutinas.getDBValueFromKey(dataBase, molde_pa);
		if ( docHtml == null ) { errores.add("No hallado en PA el molde del mail o está vacío " + molde_pa); return docHtml; }
		String smtp_mail_from = Subrutinas.getDBValueFromKey(dataBase, _K.PA_KEY_SMTP_MAIL_OLEOLE_CONFIRM_FROM) + "@" + domain;
		String smtp_mail_subject = Subrutinas.getDBValueFromKey(dataBase, _K.PA_KEY_SMTP_MAIL_OLEOLE_CONFIRM_SUBJECT) 
				+ " " + reservation_id
				;

		// Variables de macrosustitución en este molde de mail:
//		String callBack;
		String urlImagenes;
		String textoBoton1 = reg_ev.getEv_name();
		String textoBoton2 = reg_us.getUs_first_name() + " " + reg_us.getUs_last_name();
		String textoBoton3 = reservation_id;
		///////////////////////
		// - - - - - - - - - - 
		

//		///////////////////////
//		// Conseguir una clave única para el callback:
//		String token_id = Subrutinas.getHashFromRandomCode();
//		while ( ! "".equalsIgnoreCase( Subrutinas.getTkFromId(dataBase, token_id).getTk_sincro() ) ) {
//			// Si tiene "Sincro" es que ya existía...
//			token_id = Subrutinas.getHashFromRandomCode();
//		}
//		com.fvr.tk_tokens.db.TkAccesoBaseDatos dao_tk = new com.fvr.tk_tokens.db.TkAccesoBaseDatos();
//		com.fvr.tk_tokens.bean.TkBean reg_tk = new com.fvr.tk_tokens.bean.TkBean();
//		reg_tk.setTk_token_id( token_id );
//		reg_tk.setTk_author( user_id );
//		///////////////////////


		///////////////////////
		// Macrosustitución de valores:
		urlImagenes	= url_base + "/emails/"+idioma_ISO+"/img/";
//		callBack	= url_base 					
//					+ "/ConfirmServlet?key=" 
//					+ token_id 
//					+ "&lng="+idioma_ISO
//					;	// Llamada de continuación en el servidor:

		docHtml = Subrutinas.macroSustituye( docHtml, preHTML + "IMG_SRC" + posHTML, urlImagenes + "OleOle20180131.png");
		docHtml = Subrutinas.macroSustituye( docHtml, preHTML + "TEXTO_BOTON1"  + posHTML, textoBoton1);
		docHtml = Subrutinas.macroSustituye( docHtml, preHTML + "TEXTO_BOTON2"  + posHTML, textoBoton2);
		docHtml = Subrutinas.macroSustituye( docHtml, preHTML + "TEXTO_BOTON3"  + posHTML, textoBoton3);
//		docHtml = Subrutinas.macroSustituye( docHtml, preHTML + "CALLBACK" + posHTML, callBack);
		///////////////////////


		if ( !isEnviar ) {
			return docHtml;
		}


		/////////////////////////////////////
		// EMISIÓN DEL CORREO:
		/////////////////////////////////////
		try {
			String replyTo_o_null = null;
			String smtp_host_name = Subrutinas.getDBValueFromKey(dataBase, _K.PA_KEY_SMTP_HOST_NAME); 
			String smtp_auth_user = Subrutinas.getDBValueFromKey(dataBase, _K.PA_KEY_SMTP_AUTH_USER);
			String smtp_auth_pwd  = Subrutinas.getDBValueFromKey(dataBase, _K.PA_KEY_SMTP_AUTH_PWD);
			String smtp_mail_port = Subrutinas.getDBValueFromKey(dataBase, _K.PA_KEY_SMTP_MAIL_PORT);
			if (	   
					   smtp_host_name != null && smtp_host_name.trim().length() > 0
					&& smtp_auth_user != null && smtp_auth_user.trim().length() > 0
					&& smtp_auth_pwd  != null && smtp_auth_pwd.trim().length() > 0
					&& smtp_mail_port != null && smtp_mail_port.trim().length() > 0
					&& smtp_mail_from != null && smtp_mail_from.trim().length() > 0
					&& smtp_mail_subject != null && smtp_mail_subject.trim().length() > 0
					) {
				
				sendMail(
						smtp_host_name, 
						smtp_auth_user, 
						smtp_auth_pwd, 
						smtp_mail_port,
						smtp_mail_from,
						eMailDestino,
						smtp_mail_subject,
						docHtml,
						replyTo_o_null
						);

//				try {
//					JSONObject json = new JSONObject();
//					json.put("acc", molde_pa);
//					json.put("eMailDestino", eMailDestino);
//					json.put("docHtml", docHtml);
//					
//					reg_tk.setTk_json( json.toString() );
//					dao_tk.tk_crtObj(dataBase, reg_tk);
//				} catch (StExcepcion e) {
//					errores.add("ERROR " + e.getMessage());
//				}

			}
		} catch (MessagingException e) {
			errores.add("ERROR " + e.getMessage());
		}
		/////////////////////////////////////
		
		return docHtml;

	}

}
