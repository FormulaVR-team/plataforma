package com.fvr._servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fvr.FuentesDeDatos.BDConexion;
import com.fvr._comun.StExcepcion;
import com.fvr._comun.Subrutinas;
import com.fvr._comun._K;
import com.fvr.tk_tokens.bean.TkBean;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class ConfirmServlet
 */
public class ConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 8969285001963807881L;
	public static String m_IP = "127.0.0.1";
       
//	/////////////////////////////////
    public ConfirmServlet() {
        super();
		try {
	        byte[] ip = InetAddress.getLocalHost().getAddress();
			m_IP = 	(ip[0]&0xFF) + "." +
					(ip[1]&0xFF) + "." +
					(ip[2]&0xFF) + "." +
					(ip[3]&0xFF);
			
			m_IP = Subrutinas.getComputername();

//			// Mac Addres: (Puede no ser �nica. Hay tantas como adaptadores de datos...
//			Enumeration<NetworkInterface> it;
//			try {
//				it = NetworkInterface.getNetworkInterfaces();
//				while ( it.hasMoreElements() ) {
//				    byte[] macAddress = it.nextElement().getHardwareAddress();
//				}			
//			} catch (SocketException e) {
//				e.printStackTrace();
//			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

    	///////////////////////////
    	// CORS:
//        String ipAddress = request.getHeader("x-forwarded-for");
//        if (ipAddress == null) { ipAddress = request.getRemoteAddr(); }  
    	String clientOrigin = request.getHeader("origin");
    	// ATENCI�N puede contener la cadena "null"
    	if ( clientOrigin != null && clientOrigin.trim().length() > 0 ) {
//    		System.out.println("*** Rec.llamada CORS clientOrigin: " + clientOrigin);
        	List<String> incomingURLs = Arrays.asList(
            		Subrutinas.get_CORS_incomingURLs().trim().split(",")
            		);

            response.setHeader("Cache-control", "no-cache, no-store");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "-1");	// Manda m�s "Access-Control-Max-Age"

            int myIndex = incomingURLs.indexOf(clientOrigin);
            if ( myIndex == -1 ) {
            	 myIndex = incomingURLs.indexOf("*");
            }
            if ( myIndex != -1 ) {
            	response.setHeader("Access-Control-Allow-Origin", clientOrigin); 	// * es todo, tb se pueden especificar dominios
                response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, DELETE, HEAD, OPTIONS"); 	// metodos permitidos
                response.setHeader("Access-Control-Allow-Headers", "Content-Type");

                response.setHeader("Access-Control-Expose-Headers", "Location, Content-Disposition");
                response.setHeader("Access-Control-Max-Age", "86400");				// 24h
            } else {
				// LOG FUNCIONAL DEL SISTEMA:
            	Subrutinas.addLog(new Subrutinas().getBDConexion(request), "SYS", clientOrigin + " : CORS ERROR.", this.getClass().getSimpleName(), this.getClass().getSimpleName());
        		System.err.println("Error CORS para: " + clientOrigin);
        		return;
            }
    	}
    	///////////////////////////
    	// Los parámetros de entrada: (Puede llamarse por POST o por GET)

    	JSONObject payload = Subrutinas.getRequestPayload_json(request, true);
    	payload = payload==null?new JSONObject():payload;

    	///////////////////////////
    	// Recepción de parámetros
    	String token_id = null; 
    	
    	// Modo PAYLOAD:
    	try { token_id = payload.getString("key"); } catch (Exception e) { ; }

    	// Modo URL:
    	token_id = token_id==null?request.getParameter("key"):token_id;   // Clave ACCIÓN: LOGON, UPLOAD, DOWNLOAD.

    	///////////////////////////
    	// Recuperar datos de continuación del token_id:
    	String acc = null;
		JSONObject json = null;

    	if ( token_id != null ) {
			try { json = Subrutinas.getJsonFromToken(new Subrutinas().getBDConexion(request), token_id); } catch (Exception e1) {;}
			if ( json != null ) { try { acc = json.getString("acc"); } catch (Exception e) {;} }
    	} else {
    		responder(request, response, false, "No hallado valor para token_id: " + token_id);
    		return;
    	}

    	///////////////////////////
    	// El distribuidor de procesos. cada uno con sus datos específicos:
		if ( _K.PA_KEY_MAIL_NEW_USER_REGISTRATION.equalsIgnoreCase( acc )) {
			cmd_mailNewUserRegistration_received( request,  response, token_id, json );
		} else 
		if ( _K.PA_KEY_MAIL_CHANGE_PASSWORD.equalsIgnoreCase( acc )) {
			cmd_mailChangePassword_received( request,  response, token_id, json );
		} else 
		if ( _K.PA_KEY_MAIL_RESERVE_CONFIRM.equalsIgnoreCase( acc )) {
			cmd_mailReserveView_received( request,  response, token_id, json );
		} else 
		if ( _K.TK_ACC_USERCHANGED.equalsIgnoreCase( acc )) {
			cmd_userChanged( request,  response, token_id, json );
		} else 
		{
    		responder(request, response, false, "Acción no reconocida en el sistema.");
    		return;
		}

    	///////////////////////////
    }
	//	/////////////////////////////////
	private void responder( HttpServletRequest request, HttpServletResponse response, boolean rc, String texto ) throws IOException {

//      response.setContentType("text/html;charset=UTF-8");
//		response.setContentType("application/octet-stream");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		JSONObject jsonObject = new JSONObject();

		jsonObject.put( "server",m_IP );
		jsonObject.put( "class",this.getClass().getSimpleName() );
		jsonObject.put( "rc",rc?"OK":"KO" );
		jsonObject.put( "text",texto );

		out.print( jsonObject.toString() );

		out.close();
    }
	//	/////////////////////////////////

	private void cmd_mailNewUserRegistration_received(HttpServletRequest request, HttpServletResponse response, String token_id, JSONObject json) throws IOException {

		String user_id = null;

		try { user_id = json.getString("eMailDestino"); } catch (Exception e) {;}

		if ( user_id != null && user_id.trim().length() > 1 ) {
			BDConexion dataBase = new Subrutinas().getBDConexion(request);
			try {
				String link = Subrutinas.get_urlBase(request);
				
				com.fvr.us_users.bean.UsBean reg_us = Subrutinas.getUsFromId(dataBase, user_id);
				if ( reg_us == null || "".equalsIgnoreCase( reg_us.getUs_sincro() ) ) {
					// Pantalla de registro de nuevo usuario:
					request.setAttribute("token_id", token_id);
					request.setAttribute("user_id", user_id);

					// Para seleccionar los includes según su rol:
					request.getSession(true).setAttribute( "roleKey", _K.ROL_USER );
					link += "/Index_A.do#/rsUsADDRCD/" + token_id + "/" + user_id;

					// link = http://13458618.ngrok.io:80/FormulaVR/Index_A.do#/rsUsADDRCD/385C3935A7A3A9D72847A122BDD330EC2B399D14/eestecha@gmail.com
				} else {
					// Ya existe!! (Ir a la entrada principal)
					link += "";
				}

				response.sendRedirect( link );	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

			} catch (IOException e) {
				Subrutinas.addLog( dataBase,  _K.SYS, "cmd_mailNewUserRegistration_received()", e.getMessage(), this.getClass().getSimpleName() );
				e.printStackTrace();
			}	
		}
	}

	private void cmd_mailChangePassword_received(HttpServletRequest request, HttpServletResponse response, String token_id, JSONObject json) throws IOException {

		String user_id = null;

		try { user_id = json.getString("eMailDestino"); } catch (Exception e) {;}

		if ( user_id != null && user_id.trim().length() > 1 ) {
			BDConexion dataBase = new Subrutinas().getBDConexion(request);
			try {
				String link = Subrutinas.get_urlBase(request);
				
				com.fvr.us_users.bean.UsBean reg_us = Subrutinas.getUsFromId(dataBase, user_id);
				if ( reg_us == null || "".equalsIgnoreCase( reg_us.getUs_sincro() ) ) {
					// No existe!! (Ir a la entrada principal)
					link += "";
				} else {

					///////////////////
					// Reentrada en el sistema:
					if ( user_id != null && user_id.trim().length() > 0 ) {
				       	request.getSession(true).setAttribute( "logon_USR", reg_us.getUs_user_id() );
				        request.getSession(true).setAttribute( "logon_HSH", reg_us.getUs_hash_code() );
						link += "/Index_A.do#/rsUsCHGPWD/" + token_id + "/" + user_id;
					}
					///////////////////

					// link = http://13458618.ngrok.io:80/FormulaVR/Index_A.do#/rsUsADDRCD/385C3935A7A3A9D72847A122BDD330EC2B399D14/eestecha@gmail.com
				}

				response.sendRedirect( link );	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

			} catch (IOException e) {
				Subrutinas.addLog( dataBase,  _K.SYS, "cmd_mailChangePassword_received()", e.getMessage(), this.getClass().getSimpleName() );
				e.printStackTrace();
			}	
		}
	}

	private void cmd_mailReserveView_received(HttpServletRequest request, HttpServletResponse response, String token_id, JSONObject json) {

		String user_id = null;

		try { user_id = json.getString("eMailDestino"); } catch (Exception e) {;}

		if ( user_id != null && user_id.trim().length() > 1 ) {
			BDConexion dataBase = new Subrutinas().getBDConexion(request);
			try {
				String link = Subrutinas.get_urlBase(request) + "/";

				com.fvr.us_users.bean.UsBean reg_us = Subrutinas.getUsFromId(dataBase, user_id);
				if ( reg_us == null || "".equalsIgnoreCase( reg_us.getUs_sincro() ) ) {
					// No existe!! (Ir a la entrada principal)
					link += "";
				} else {
					///////////////////
					// Reentrada en el sistema:
					if ( user_id != null && user_id.trim().length() > 0 ) {
				       	request.getSession(true).setAttribute( "logon_USR", reg_us.getUs_user_id() );
				        request.getSession(true).setAttribute( "logon_HSH", reg_us.getUs_hash_code() );
						link +=  "Index_A.do#/RsDSPFIL/";
					}
					///////////////////

				}

				response.sendRedirect( link );	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//				request.getRequestDispatcher( "/CpDSPFIL_A.do" ).forward(request, response);

			} catch (IOException e) {
				Subrutinas.addLog( dataBase,  _K.SYS, "cmd_mailReserveView_received()", e.getMessage(), this.getClass().getSimpleName() );
				e.printStackTrace();
			}	
		}
	}

	private void cmd_userChanged(HttpServletRequest request, HttpServletResponse response, String token_id, JSONObject json) {

		String user_id = null;

		try { user_id = json.getString("chosenUser"); } catch (Exception e) {;}

		if ( user_id != null && user_id.trim().length() > 1 ) {
			BDConexion dataBase = new Subrutinas().getBDConexion(request);
			
			// ELIMINAR EL TOKEN PARA NO PODER VOLVER A USRLO !!!
			if ( token_id != null && token_id.trim().length() > 1 ) {
				TkBean key_tk = new TkBean();
				key_tk.setTk_token_id(token_id);
				try { new com.fvr.tk_tokens.db.TkAccesoBaseDatos().tk_dltObj(dataBase, key_tk); } catch (StExcepcion e) {;}
			}
			
			try {
				String link = Subrutinas.get_urlBase(request) + "/";

				com.fvr.us_users.bean.UsBean reg_us = Subrutinas.getUsFromId(dataBase, user_id);
				if ( reg_us == null || "".equalsIgnoreCase( reg_us.getUs_sincro() ) ) {
					// No existe!! (Ir a la entrada principal)
					link += "";
				} else {
					///////////////////
					// Reentrada en el sistema:
					if ( user_id != null && user_id.trim().length() > 0 ) {
				       	request.getSession(true).setAttribute( "logon_USR", reg_us.getUs_user_id() );
				        request.getSession(true).setAttribute( "logon_HSH", reg_us.getUs_hash_code() );
						link +=  "Index_A.do#/RsDSPFIL/";
					}
					///////////////////

				}

				response.sendRedirect( link );	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//				request.getRequestDispatcher( "/CpDSPFIL_A.do" ).forward(request, response);

			} catch (IOException e) {
				Subrutinas.addLog( dataBase,  _K.SYS, "cmd_userChanged()", e.getMessage(), this.getClass().getSimpleName() );
				e.printStackTrace();
			}	
		}
	}

	//	/////////////////////////////////
}
