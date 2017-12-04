package com.fvr._comun;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.fvr.FuentesDeDatos.BDConexion;
import com.fvr._comun.disponiblidad.Reservas;
import com.fvr.cd_LocationClosedDays.bean.CdBean;
import com.fvr.es_eventSusbscriptions.bean.EsBean;
import com.fvr.ev_events.bean.EvBean;
import com.fvr.lo_location.bean.LoBean;
import com.fvr.pa_systemParameters.bean.PaBean;
import com.fvr.pm_promosManuales.bean.PmBean;
import com.fvr.ps_countries.bean.PsBean;
import com.fvr.pt_products.bean.PtBean;
import com.fvr.rs_reservations.bean.RsBean;
import com.fvr.tj_tarjetasPrepago.bean.TjBean;
import com.fvr.tk_tokens.bean.TkBean;
import com.fvr.us_users.bean.UsBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author Emilio Estecha 2014
 *
 */
public class Subrutinas {
	private final static String tag = "Subrutinas";	//this.getClass().getSimpleName();
    
	////////////////////////////////////
//	Para DESPLEGAR Billin:
	public static String m_modoEjecucion = "test";	// "test" / "real". En real lanza la "Autenticacion_FS()" 
	public static boolean m_isPool 		 = false;	// false  / true
	////////////////////////////////////

	private static final int g_filas_DSPFIL = 15;

	public static String archivo_config = "com.fvr.struts.Config";
	public static String archivo_es     = "com.fvr.struts.ApplicationResource";
	public static String archivo_en     = "com.fvr.struts.ApplicationResource";

	public Subrutinas() {
//		String msgId  = "configuracion.modo";
//		m_modoEjecucion = org.apache.struts.util.MessageResources.getMessageResources(Subrutinas.archivo_config).getMessage(msgId);
	}
	////////////////////////
	public void sincroSesion_COMUN(HttpServletRequest request, ActionForm form) {
        BDConexion dataBase = getBDConexion(request);
		String usr = Subrutinas.getValorFromVariableEnFormulario(form, "logon_USR");

		//////////////
		// BOLSA DE VARIABLES DE CONTEXTO en los action form:
        ContextVars cv = new ContextVars();
        ///
		cv.setRole_id( getUsrRole(dataBase, usr) );
		cv.setSpecialMenu( getRoleMenu( cv.getRole_id() ) );
        ///
		Subrutinas.setValorToVariableEnFormulario_obj( form, "contextVars", cv);
		//////////////

		//////////////
		// Variables de entorno en pantallas:
		request.setAttribute("miVersion", Subrutinas.getDBValueFromKey(dataBase, _K.PA_KEY_VERSION));
		//////////////

	}
	public String getUsrRole(BDConexion dataBase, String kuser ) {
		String resultado = "";
		try {
			com.fvr.us_users.db.UsAccesoBaseDatos dao_us = new com.fvr.us_users.db.UsAccesoBaseDatos();
			com.fvr.us_users.bean.UsBean          reg_us = new com.fvr.us_users.bean.UsBean();
			reg_us.setUs_user_id( kuser );
			reg_us = dao_us.us_getRcd(dataBase, reg_us);
			if ( reg_us != null ) {
				resultado = reg_us.getUs_role_id();
			}
		} catch (StExcepcion e) {;}
		return resultado;
	}
	public boolean controlAcceso(BDConexion dataBase, String usr, String pgm, String hsh) {
		boolean resultado = false;
		///////////////////////////////////////////////////
		if ( usr == null || usr.trim().length() < 1 || pgm == null || pgm.trim().length() < 1  || hsh == null || hsh.trim().length() < 1 ) return resultado;
		if ( "null".equalsIgnoreCase( usr ) ) return resultado;
		if ( "null".equalsIgnoreCase( pgm ) ) return resultado;
		if ( "null".equalsIgnoreCase( hsh ) ) return resultado;
		///////////////////////////////////////
		// ..acceso a BD para comprobar si el usuario contempla la acción...
		try {
			com.fvr.us_users.db.UsAccesoBaseDatos dao_us = new com.fvr.us_users.db.UsAccesoBaseDatos();
			com.fvr.us_users.bean.UsBean          reg_us = new com.fvr.us_users.bean.UsBean();
			reg_us.setUs_user_id( usr );

			reg_us = dao_us.us_getRcd(dataBase, reg_us);
			
			if ( reg_us != null ) {
				if ( hsh.equalsIgnoreCase( reg_us.getUs_hash_code() ) ) {
					
					if ( _K.ROL_ADMIN.equalsIgnoreCase( reg_us.getUs_role_id() ) ) {
						// Si el usuario es Administrador, se le acepta:
						resultado = true;
					} else {
						com.fvr.sg_publicProcesses.db.SgAccesoBaseDatos dao_sg = new com.fvr.sg_publicProcesses.db.SgAccesoBaseDatos();
						com.fvr.sg_publicProcesses.bean.SgBean			reg_sg = new com.fvr.sg_publicProcesses.bean.SgBean();
						// PK:
						reg_sg.setSg_role_id( reg_us.getUs_role_id() );
						reg_sg.setSg_process_id( pgm );
						reg_sg = dao_sg.sg_getRcd(dataBase, reg_sg);
						if ( reg_sg != null ) {
							// Si el proceso estaba presente en el fichero de "acceso público" se le acepta pasar:
							resultado = true;
						}
					}

				}
			}

		} catch (StExcepcion e) {
			e.printStackTrace();
		}
		///////////////////////////////////////////////////
		return resultado;
	}
	public void Autenticacion_FS(String camino_red, String usr_red, String pwd_red) {
		/////////////////////////////////////////
		// En OR: 2012/05/25 Se sospecha que esto provoca "cuelgues" e impide el resto del proceso.
		// La solución ha sido incluirlo en un servlet de "startup". 
		// Por ejemplo, así quedaría en el "web.xml" con orden de inicio '0':
		
//    <servlet>
//        <servlet-name>atmStartUp</servlet-name>
//        <servlet-class>com.tm.__main.startup.atmStartUp</servlet-class>
//        <load-on-startup>0</load-on-startup>
//    </servlet>
//    <servlet-mapping>
//        <servlet-name>atmStartUp</servlet-name>
//        <url-pattern>/atmStartUp</url-pattern>
//    </servlet-mapping>
	
	// Y en el método "init()" del sevlet referido, se incluye la llamada:
		
//	public void init() {
//		try { super.init(); } catch (ServletException ex) { ex.printStackTrace(); }
//		com.tm._comun.Subrutinas subrut = new com.tm._comun.Subrutinas();
//		subrut.Autenticacion_FS();
//    }

		System.out.println("**** Autenticacion_FS() >>>>>>");
		/////////////////////
		if ( camino_red == null || camino_red.trim().length() < 1 ) { return; }
		if ( usr_red == null || usr_red.trim().length() < 1 ) { return; }
		if ( pwd_red == null || pwd_red.trim().length() < 1 ) { return; }
		BDConexion bd = null;
		try {
			bd = new BDConexion();
			if ( bd != null ) {
//				String camino_red = getDBValueFromKey( bd, "SEG_pathDocs_NETUSE" );
//				String usr_red    = getDBValueFromKey( bd, "SEG_pathDocs_USR");
//				String pwd_red    = getDBValueFromKey( bd, "SEG_pathDocs_PWD");
				String strError   = "";
				try {
					System.out.println( "Aportando credenciales para " + camino_red );
					strError = logonRecursoRedWindows_sincro(camino_red, usr_red, pwd_red);
					if ( strError != null && strError.trim().length() > 01 ) {
						System.out.println( strError );
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		} catch (StExcepcion ex) {
			ex.printStackTrace();
		}
		/////////////////////
		System.out.println("**** Autenticacion_FS() <<<<<<");
	}
	public String logonRecursoRedWindows_sincro(String camino, String usuario, String password) throws IOException, InterruptedException {

		/////////////////////////////////////////
		// En OR: 2012/05/25 Se sospecha que esto provoca "cuelgues" e impide el resto del proceso.
		// Ver nota en 'Autenticacion_FS()'...
		/////////////////////////////////////////

		Process process = null;
		String strError = "";
		String strOutput = "";
		String comando = "cmd.exe /c NET USE " + camino + " " + password + " /USER:" + usuario;

		process = (Runtime.getRuntime()).exec( comando );

		if ( process != null ) {
			///////////////////////
			// Canales de entrada/salida del proceso:
			String line;
			java.io.BufferedReader ir = new java.io.BufferedReader(new java.io.InputStreamReader(process.getInputStream()));
			java.io.BufferedReader er = new java.io.BufferedReader(new java.io.InputStreamReader(process.getErrorStream()));

			while ((line = er.readLine()) != null) {
				System.out.println(line);
				strError += line + '\n';
			}
			while ((line = ir.readLine()) != null) {
				System.out.println(line);
				strOutput += line + '\n';
			}

			ir.close();
			er.close();
			process.waitFor();
			process.destroy();
			///////////////////////
		}
		return strError;
	}
	////////////////////////
	
	////////////////////////
	public static String run_comando_sincro( String[] params ) throws StExcepcion {
		String strError = "";
		String strOutput = "";
		Process process = null;
		try {
			process = new ProcessBuilder( params ).start();
			if ( process != null ) {
				///////////////////////
				// Canales de entrada/salida del proceso:
				String line;
				java.io.BufferedReader ir = new java.io.BufferedReader(new java.io.InputStreamReader(process.getInputStream()));
				java.io.BufferedReader er = new java.io.BufferedReader(new java.io.InputStreamReader(process.getErrorStream()));

				while ((line = er.readLine()) != null) {
					// System.err.println(line);
					strError += line + '\n';
				}
				while ((line = ir.readLine()) != null) {
					// System.out.println(line);
					strOutput += line + '\n';
				}

				ir.close();
				er.close();
				process.waitFor();
				process.destroy();
				
				if ( strError != null && strError.trim().length() > 0 ) {
					throw new StExcepcion( strError );
				}
				
				///////////////////////
			}
	    	
		} catch (IOException e) { 
			throw new StExcepcion( e.getMessage() ); 
		} catch (InterruptedException e) {
			throw new StExcepcion( e.getMessage() ); 
		}

		return strOutput;
	}
	////////////////////////
	public BDConexion getBDConexion(HttpServletRequest request) {
		/////////////////////////
		BDConexion bd = (BDConexion) request.getSession().getAttribute("BDConexion");
		if ( bd == null ) {
			try {bd = new BDConexion();} catch (StExcepcion ex) {;}
		}
		request.getSession(true).setAttribute( "BDConexion", bd );
		return bd;
		/////////////////////////
	}
	////////////////////////
	public static BeanParaTablaHash[] cargarCombo_FilasGrid(){
		BeanParaTablaHash[] lista = new BeanParaTablaHash[3];
		lista[0] = new BeanParaTablaHash("15","15 filas");
		lista[1] = new BeanParaTablaHash("30","30 filas");
		lista[2] = new BeanParaTablaHash("50","50 filas");
		return lista;
	}
	public static BeanParaTablaHash[] cargarCombo_entidad(StBean[] Regs, String fieldKeyName, String fieldValueName) {
		// Esta función genera una lista (posible para alimentar un combo) 
		// usando los campos indicados en parámetros como KEY y como VALUE.
		BeanParaTablaHash[] lista = null;
		if (Regs!=null) {
			lista = new BeanParaTablaHash[Regs.length + 1];
			lista[0] = new BeanParaTablaHash("","...");
			int i = 1;
			Class<?> clase = null;
			for( StBean reg : Regs ) {
				clase = reg.getClass();
				Object valor_K = null;
				Object valor_V = null;
				try {
					valor_K = clase.getField( fieldKeyName   ).get(reg);
					valor_V = clase.getField( fieldValueName ).get(reg);
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				if ( valor_K != null && valor_V != null ) {
					lista[i] = new BeanParaTablaHash( valor_K.toString(), valor_K.toString() + " " + valor_V.toString());
				} else {
					lista[i] = new BeanParaTablaHash("","error al genearar lista...");
				}
				i++;
			}
		}
		if (lista==null) { lista = new BeanParaTablaHash[1]; }
		if (Regs==null || Regs.length<1) { lista[0] = new BeanParaTablaHash("","Sin datos"); }
		return lista;
	}
	////////////////////////
	
	public static String setUsr_newHash(BDConexion dataBase, String user_id) {
		
		String resultado = null;
		
		if ( user_id == null || user_id.trim().length()   < 1 ) { return resultado; }
		
		///////////////////////////////
		// Dispone el modo en el que esta trabajando el usr: 
		//		"Mis Clientes" o "MisProveedores".
		com.fvr.us_users.db.UsAccesoBaseDatos dao = new com.fvr.us_users.db.UsAccesoBaseDatos();
		com.fvr.us_users.bean.UsBean          rcd = new com.fvr.us_users.bean.UsBean();
		try {
	        if ( user_id != null && user_id.trim().length() > 0 ) {
	        	rcd.setUs_user_id( user_id );
	        	rcd = dao.us_getRcd(dataBase, rcd);
	        	if ( rcd != null ) {
	        		resultado = Subrutinas.getHashFromRandomCode();
	        		rcd.setUs_hash_code( resultado );
	        		dao.us_chgObj(dataBase, rcd);
	        	}
	        }
		} catch (StExcepcion e) {;
		} finally {
			dao = null;
			rcd = null;
		}
		///////////////////////////////
		return resultado;
	}
	public static String setUsr_newHash(BDConexion dataBase, String user_id, String laClave) {
		
		String resultado = null;
		
		if ( user_id == null || user_id.trim().length()   < 1 ) { return resultado; }
		
		///////////////////////////////
		// Dispone el modo en el que esta trabajando el usr: 
		//		"Mis Clientes" o "MisProveedores".
		com.fvr.us_users.db.UsAccesoBaseDatos dao = new com.fvr.us_users.db.UsAccesoBaseDatos();
		com.fvr.us_users.bean.UsBean          rcd = new com.fvr.us_users.bean.UsBean();
		try {
	        if ( user_id != null && user_id.trim().length() > 0 ) {
	        	rcd.setUs_user_id( user_id );
	        	rcd = dao.us_getRcd(dataBase, rcd);
	        	if ( rcd != null ) {
	        		resultado = laClave; // Subrutinas.getHashFromRandomCode();
	        		rcd.setUs_hash_code( resultado );
	        		dao.us_chgObj(dataBase, rcd);
	        	}
	        }
		} catch (StExcepcion e) {;
		} finally {
			dao = null;
			rcd = null;
		}
		///////////////////////////////
		return resultado;
	}
	public static boolean isUsrHashCorrecto(BDConexion dbConn, String user_id, String testHsh, UsBean outReg_us_o_null) {
		if ( testHsh != null && testHsh.trim().length() > 0 ) {
			UsBean reg_us = Subrutinas.getUsFromId(dbConn, user_id);
			if ( reg_us.getUs_hash_code().equalsIgnoreCase(testHsh) ) {
				if ( outReg_us_o_null != null ) {
					outReg_us_o_null.copyFrom(reg_us);
				}
				reg_us = null;
				return true;
			}
		}
		return false;
	}
	public static boolean improvisarUsuario(BDConexion dataBase, String user_id, ActionMessages errores_o_null) {
		String first_name =  _K.USER_DEFAULT_first_name;
		String last_name = _K.USER_DEFAULT_last_name;
		String phone = "";
		return improvisarUsuario(dataBase, user_id, first_name, last_name, phone, errores_o_null);
	}
	public static boolean improvisarUsuario(BDConexion dataBase, String user_id, String first_name, String last_name, String phone, ActionMessages errores_o_null) {
		boolean resultado = false;
		
        try {
            com.fvr.us_users.bean.UsBean reg_us = new com.fvr.us_users.bean.UsBean(); 
            // PK:
        	reg_us.setUs_user_id( user_id ); // user_id
        	// Resto:
//        	reg_us.setUs_sincro( "" ); // sincro
//        	reg_us.setUs_mark( "" ); // mark
//        	reg_us.setUs_is_deleted( "" ); // is_deleted
        	reg_us.setUs_author(  _K.USER_DEFAULT_author ); // author
//        	reg_us.setUs_user_id( user_id ); // user_id
        	reg_us.setUs_role_id( _K.ROL_USER ); // role_id
        	reg_us.setUs_hash_code( "" ); // hash_code
        	reg_us.setUs_nick( _K.USER_DEFAULT_nick ); // nick
        	reg_us.setUs_password( "" ); // password
        	reg_us.setUs_first_name( first_name ); // first_name
        	reg_us.setUs_last_name( last_name ); // last_name
        	reg_us.setUs_phone( phone ); // phone
        	reg_us.setUs_gender( _K.USER_DEFAULT_gender ); // gender
        	reg_us.setUs_birth_day( _K.USER_DEFAULT_birth_day ); // birth_day
        	reg_us.setUs_avatar( _K.USER_DEFAULT_avatar ); // avatar
        	reg_us.setUs_json( "" ); // json

			new com.fvr.us_users.db.UsAccesoBaseDatos().us_crtObj(dataBase, reg_us);
			
			resultado = true;

        } catch (StExcepcion e) {
        	if ( errores_o_null != null ) {
                errores_o_null.add("error", new ActionMessage( "errors.detail", e.getMessage() ));
        	}
		}
		
		return resultado;
	}
	////////////////////////
	public static int getG_filas_DSPFIL() {
		return g_filas_DSPFIL;
	}
	public static String getG_DB_LIBDAT(String db) {
		String msgId = "configuracion." + db + ".LIBDAT." + m_modoEjecucion;
		String r = org.apache.struts.util.MessageResources.getMessageResources(Subrutinas.archivo_config).getMessage(msgId);
		if (r == null) {
			System.out.println("Error en archivo '" + Subrutinas.archivo_config + "' en key: '" + msgId + "'");
		}
		return r;
	}
	public static String getG_DB_DRIVER(String db) {
		String msgId = "configuracion." + db + ".DRIVER." + m_modoEjecucion;
		String r = org.apache.struts.util.MessageResources.getMessageResources(Subrutinas.archivo_config).getMessage(msgId);
		if (r == null) {
			System.out.println("Error en archivo '" + Subrutinas.archivo_config + "' en key: '" + msgId + "'");
		}
		return r;
	}
	public static String getG_DB_PRE_IP(String db) {
		String msgId = "configuracion." + db + ".PRE_IP." + m_modoEjecucion;
		String r = org.apache.struts.util.MessageResources.getMessageResources(Subrutinas.archivo_config).getMessage(msgId);
		if (r == null) {
			System.out.println("Error en archivo '" + Subrutinas.archivo_config + "' en key: '" + msgId + "'");
		}
		return r;
	}
	public static String getG_DB_IP(String db) {
		String msgId = "configuracion." + db + ".DIR_IP." + m_modoEjecucion;
		String r = org.apache.struts.util.MessageResources.getMessageResources(Subrutinas.archivo_config).getMessage(msgId);
		if (r == null) {
			System.out.println("Error en archivo '" + Subrutinas.archivo_config + "' en key: '" + msgId + "'");
		}
		return r;
	}
	public static String getG_DB_POS_IP(String db) {
		String msgId = "configuracion." + db + ".POS_IP." + m_modoEjecucion;
		String r = org.apache.struts.util.MessageResources.getMessageResources(Subrutinas.archivo_config).getMessage(msgId);
		if (r == null) {
			System.out.println("Error en archivo '" + Subrutinas.archivo_config + "' en key: '" + msgId + "'");
		}
		return r;
	}
	public static String getG_DB_USR(String db) {
		String msgId = "configuracion." + db + ".RMTUSR." + m_modoEjecucion;
		String r = org.apache.struts.util.MessageResources.getMessageResources(Subrutinas.archivo_config).getMessage(msgId);
		if (r == null) {
			System.out.println("Error en archivo '" + Subrutinas.archivo_config + "' en key: '" + msgId + "'");
		}
		return r;
	}
	public static String getG_DB_PWD(String db) {
		String msgId = "configuracion." + db + ".RMTPWD." + m_modoEjecucion;
		String r = org.apache.struts.util.MessageResources.getMessageResources(Subrutinas.archivo_config).getMessage(msgId);
		if (r == null) {
			System.out.println("Error en archivo '" + Subrutinas.archivo_config + "' en key: '" + msgId + "'");
		}
		return r;
	}
	public static String getG_DB_RWUPPERCASE(String db) {
		String msgId = "configuracion." + db + ".RW.UPPERCASE";
		String r = org.apache.struts.util.MessageResources.getMessageResources(Subrutinas.archivo_config).getMessage(msgId);
		if (r == null) {
			System.out.println("Error en archivo '" + Subrutinas.archivo_config + "' en key: '" + msgId + "'");
		}
		return r;
	}
	public static String getG_DB_RWLIKE(String db) {
		String msgId = "configuracion." + db + ".RW.LIKE";
		String r = org.apache.struts.util.MessageResources.getMessageResources(Subrutinas.archivo_config).getMessage(msgId);
		if (r == null) {
			System.out.println("Error en archivo '" + Subrutinas.archivo_config + "' en key: '" + msgId + "'");
		}
		return r;
	}
	public static String getG_DB_RWANYPATTERN(String db) {
		String msgId = "configuracion." + db + ".RW.ANYPATTERN";
		String r = org.apache.struts.util.MessageResources.getMessageResources(Subrutinas.archivo_config).getMessage(msgId);
		if (r == null) {
			System.out.println("Error en archivo '" + Subrutinas.archivo_config + "' en key: '" + msgId + "'");
		}
		return r;
	}
	public static String get_urlBase( HttpServletRequest request) {
		return  request.getScheme()
  		      + "://"
  		      + request.getServerName()
  		      + ":"
  		      + request.getServerPort()
  		      + request.getContextPath();
	}
	////////////////////////
	public static String padRight(String s, int n) {
		return String.format("%1$-" + n + "s", s);
	}
	public static String padLeft(String s, int n) {
		return String.format("%1$#" + n + "s", s);
	}
	public static String padLeftCeros(long dato, int n) {
		return String.format("%0" + n + "d", dato);
	}
	////////////////////////
	public static int    parse_integer( String s ) {
		int res = 0;
		try { res = Integer.parseInt(s); } catch (NumberFormatException ex) {;}
		return res;
	}
	public static long   parse_long( String s ) {
		long res = 0;
		try { res = Long.parseLong(s); } catch (NumberFormatException ex) {;}
		return res;
	}
	public static double parse_double( String s ) {
		double res = 0.0;
		try { res = Double.parseDouble(s); } catch (NumberFormatException ex) {;}
		return res;
	}
	public static String bytesToHex(byte[] b) {
        char hexDigit[] = {'0', '1', '2', '3', '4', '5', '6', '7',
                           '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        StringBuffer buf = new StringBuffer();
        for (int j=0; j<b.length; j++) {
           buf.append(hexDigit[(b[j] >> 4) & 0x0f]);
           buf.append(hexDigit[b[j] & 0x0f]);
        }
        return buf.toString();
     }
    public static String getRandomHashCode() {
    	String resultado = "???";
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA1");
			// byte[] bytes = digest.digest( usr.getBytes() );	// Siewmpre devuelve el mismo valor.
			Integer pito = (int) (Math.random() * 10000);		// hashCode aleatorio
			byte[] bytes = digest.digest( pito.toString().getBytes() );
			resultado = Subrutinas.bytesToHex( bytes );
		} catch (NoSuchAlgorithmException e) {;}
		
		return resultado;
    }
    public static String getComputername() {
    	String computername = "SinNombre";
    	try { computername = InetAddress.getLocalHost().getHostName();} catch (UnknownHostException e) {;}
    	return computername;
    }
	public static String get_CORS_incomingURLs() {
		String msgId = "CORS.incomingURLs";
		String r = org.apache.struts.util.MessageResources.getMessageResources(Subrutinas.archivo_config).getMessage(msgId);
		if (r == null) {
			r = "";
			System.err.println("Error al recuperar del archivo de propiedades '" + Subrutinas.archivo_es + "' en key: '" + msgId + "'");
		}
		return r;
	}
    public static String getHashFromRandomCode() {
    	String resultado = "???";
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA1");
			// byte[] bytes = digest.digest( usr.getBytes() );	// Siewmpre devuelve el mismo valor.
			Integer pito = (int) (Math.random() * 10000);		// hashCode aleatorio
			byte[] bytes = digest.digest( pito.toString().getBytes() );
			resultado = Subrutinas.bytesToHex( bytes );
		} catch (NoSuchAlgorithmException e) {;}
		
		return resultado;
    }
    public static String getHashFromString( String dato ) {
    	String resultado = "getHashFromString_error";
    	
    	// GENERA UN HASH: "SHA-1"
    	// SHA-1 produces a 160-bit (20-byte) hash value
    	// El resultado es una cadena de 40 caracteres.
    	
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA1");
			byte[] bytes = digest.digest( dato.getBytes() );	// Siewmpre devuelve el mismo valor.
			resultado = Subrutinas.bytesToHex( bytes );
		} catch (NoSuchAlgorithmException e) {;}
		
		return resultado;
    }
    public static String getHashMD5FromString( String dato ) {
    	String resultado = "getHashMD5FromString_error";
    	
    	// GENERA UN HASH: "SHA-1"
    	// SHA-1 produces a 160-bit (20-byte) hash value
    	// El resultado es una cadena de 40 caracteres.
    	
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			byte[] bytes = digest.digest( dato.getBytes() );	// Siewmpre devuelve el mismo valor.
			resultado = Subrutinas.bytesToHex( bytes );
		} catch (NoSuchAlgorithmException e) {;}
		
		return resultado;
    }
    public static boolean  NIF_isCorrecto( String NIF ) {
		if ( (new com.aeat.valida.Validador().checkNif( NIF )) > 0) { 
			return true;
		}
		return false;

    }
    //////////////////////
	// FICHEROS
	public static int ZIP_addFiles         ( final StringBuffer logVar_o_null, final String[] fileNamesList,  final String nombreZipFicheroCompleto ) {
		int resultado = 0;
		if(logVar_o_null!=null) logVar_o_null.append("\r\n" + "ZIP_addFiles( " + nombreZipFicheroCompleto + " ) >>>>>>>>>>>>>>");

		if ( fileNamesList == null || fileNamesList.length < 1 ) return resultado;

		try {
			File file = new File( nombreZipFicheroCompleto );
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			ZipOutputStream  zos = new ZipOutputStream(fos);
			try {
				for (int i = 0; i < fileNamesList.length; ++i) {
					byte[] bytes = readFileBin( logVar_o_null, fileNamesList[i] );
					try {
						if(logVar_o_null!=null) logVar_o_null.append("\r\n" + "ZIP_addFiles() ADD '" + fileNamesList[i] + "'...");
						zos.putNextEntry( new ZipEntry( fileNamesList[i] ) );
						zos.write(bytes);
						++resultado;
					} catch (IOException e) {
						if(logVar_o_null!=null) logVar_o_null.append("\r\n" + e.getMessage());
					} finally {
						zos.closeEntry();
						bytes = null;
					}
				}
			} catch (IOException e) {
				if(logVar_o_null!=null) logVar_o_null.append("\r\n" + e.getMessage());
			} finally {
				zos.close();
			}
		} catch (IOException e) {
			if(logVar_o_null!=null) logVar_o_null.append("\r\n" + e.getMessage());
		}
		if(logVar_o_null!=null) logVar_o_null.append("\r\n" + "ZIP_addFiles( " + nombreZipFicheroCompleto + " ) <<<<<<<<<<<<<<");
		return resultado;
	}
	public static int ZIP_extraerConFiltro ( final StringBuffer logVar_o_null, final String nombreZipFicheroCompleto, final String dirDestino, final String filtroDeNombres_patron) {
		int numExtraidos = 0;
		String patron = (filtroDeNombres_patron==null)?"":filtroDeNombres_patron.trim();
		FileInputStream fis = null;
		ZipInputStream zis = null;
		ZipEntry ze = null;
		File zipFile = new File( nombreZipFicheroCompleto );
		//////////////////////////////////////
		if ( zipFile.exists() && zipFile.canRead() ) {
			try {
				fis = new FileInputStream( zipFile );
				zis = new ZipInputStream( new BufferedInputStream(fis) );
				try {
					try {
						String nomFicComp = null;
						String filename  = null;
						while ((ze = zis.getNextEntry()) != null) {

							filename = ze.getName().replace('\\', '/');

							if ( !ze.isDirectory() && filename.indexOf( patron ) > -1 ) {
								ByteArrayOutputStream baos = new ByteArrayOutputStream();
								byte[] buffer = new byte[65536];
								int count;
								while ( (count = zis.read(buffer)) != -1 ) {
									baos.write(buffer, 0, count);
								}
								//////////////////////////////////////////
								nomFicComp = dirDestino + File.separator + filename;
								if ( grabFile( logVar_o_null, nomFicComp, baos.toByteArray() ) ) {
									if(logVar_o_null!=null) logVar_o_null.append("\r\n" + nomFicComp);
									numExtraidos++;
								}
								//////////////////////////////////////////
							}

						}
					} catch (IOException e) {
						if(logVar_o_null!=null) logVar_o_null.append("\r\n" + e.getMessage());
						e.printStackTrace();
					}
				} finally {
					try {
						if ( zis != null ) zis.close();
					} catch (IOException e) {
						if(logVar_o_null!=null) logVar_o_null.append("\r\n" + e.getMessage());
						e.printStackTrace();
					}
				}
			} catch (FileNotFoundException e) {
				if(logVar_o_null!=null) logVar_o_null.append("\r\n" + e.getMessage());
				e.printStackTrace();
			}
		} else {
			if(logVar_o_null!=null) logVar_o_null.append("\r\n" + "El fichero \n\r\t" + nombreZipFicheroCompleto + "\n\r no existe o no se puede leer.");
		}
		//////////////////////////////////////
		return numExtraidos;
	}
	public static boolean grabFile         ( final StringBuffer logVar_o_null, final String nombreFicheroCompleto, final byte[] contenido ) {
		boolean resultado = false;

		String nomCamino = nombreFicheroCompleto.replace('\\', '/');
		int idx = nomCamino.lastIndexOf( '/' );
		if (idx > -1 ) nomCamino = nomCamino.substring( 0, idx );

		crtDir(logVar_o_null, nomCamino);

		FileOutputStream fos = null;
		try {
			File file = new File( nombreFicheroCompleto );
			file.delete();
			file.createNewFile();
			fos = new FileOutputStream(file);
			fos.write( contenido );
			resultado = true;
		} catch (FileNotFoundException e) {
			if(logVar_o_null!=null) logVar_o_null.append("\r\n" + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			if(logVar_o_null!=null) logVar_o_null.append("\r\n" + e.getMessage());
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.flush();
					fos.close();
				} catch (IOException e) {
					if(logVar_o_null!=null) logVar_o_null.append("\r\n" + e.getMessage());
					e.printStackTrace();
				}
			}
		}
		return resultado;
	}
	public static String  readFile         ( final StringBuffer logVar_o_null, final String nombreFicheroCompleto ) {
		String contenido = "";
		try {
			byte[] buff = readFileBin( logVar_o_null, nombreFicheroCompleto );
			if ( buff != null ) {
				contenido = new String( buff , "ISO-8859-1");
			}
		} catch (UnsupportedEncodingException e) {
			if(logVar_o_null!=null) logVar_o_null.append("\r\n" + e.getMessage());
		}
		return contenido;
	}
	public static byte[]  readFileBin      ( final StringBuffer logVar_o_null, final String nombreFicheroCompleto ) {

		byte[] bytes = null;

		File fichero = new File ( nombreFicheroCompleto );
		int lenFic = (int) fichero.length();
		bytes = new byte[ lenFic ];

		FileInputStream fis = null;
		DataInputStream dis = null;
		try {
			fis = new FileInputStream(fichero);
			dis = new DataInputStream( fis );
			dis.readFully(bytes, 0, lenFic);
		} catch (IOException e) {
			if(logVar_o_null!=null) logVar_o_null.append("\r\n" + e.getMessage());
		} finally {
			try { if (fis!=null) { fis.close(); } } catch (IOException e) {;}
		}

		return bytes;
	}
	public static boolean crtDir           ( final StringBuffer logVar_o_null, final String nombreDirectorio ) {
		boolean resultado = false;
		//////////////////////////////////
		File dir = new File( nombreDirectorio );
		if ( !dir.exists() ) dir.mkdirs();
		if ( dir.exists() && dir.canWrite() ) resultado = true;
		//////////////////////////////////
		return resultado;
	}
	public static File[] getFiles_endsWith ( File dir, final String sufijo ) {
		// Por ejemplo que acaban en ".pdf"
	    return dir.listFiles(new FilenameFilter() {
	        public boolean accept(File dir, String name) {
	            return name.toLowerCase().endsWith( sufijo.toLowerCase() );
	        }
	    });
	}
	public static File[] getFiles_startsWith(File dir, final String prefijo ) {
		// Por ejemplo que empiezan por "123^321^"
	    return dir.listFiles(new FilenameFilter() {
	        public boolean accept(File dir, String name) {
	            return name.toLowerCase().startsWith( prefijo.toLowerCase() );
	        }
	    });
	}	
	public static File[] getFiles_deNombre ( File dir, final String nombre ) {
		final String nomSinExt;
    	int idx = nombre.lastIndexOf('.');
    	if ( idx > -1 ) {
    		nomSinExt = nombre.substring(0, idx);
    	} else {
    		nomSinExt = nombre;
    	}
	    return dir.listFiles(new FilenameFilter() {
	        public boolean accept(File dir, String name) {
	        	String nombreActualSinExt = name;
	        	int idx = name.lastIndexOf('.');
	        	if ( idx > -1 ) {
	        		nombreActualSinExt = name.substring(0, idx);
	        	}
	        	if ( nombreActualSinExt.equalsIgnoreCase( nomSinExt ) ) {
	        		return true;
	        	}
	            return false;
	        }
	    });
	}	
	public static void   copyFile( final String origen, final String destino) throws IOException {
        Path FROM = Paths.get(origen);
        Path TO = Paths.get(destino);
        //sobreescribir el fichero de destino, si existe, y copiar
        // los atributos, incluyendo los permisos rwx
        CopyOption[] options = new CopyOption[]{
          StandardCopyOption.REPLACE_EXISTING,
          StandardCopyOption.COPY_ATTRIBUTES
        };
        Files.copy(FROM, TO, options);
    }
	////////////////////////
	// FECHAS / HORAS
	public static Date addDays(Date date, int numDiasConSigno) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, numDiasConSigno);
		return cal.getTime();
	}
	public static Date addHours(Date date, int numHorasConSigno) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, numHorasConSigno);
		return cal.getTime();
	}
	public static Date addMinutes(Date date, int numMinutosConSigno) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, numMinutosConSigno);
		return cal.getTime();
	}
	
	public static String getFechaHumana() {
		return getFechaHumana(new Date());
	}
	public static String getFecha_aammdd() {
		return getFecha_aammdd(new Date());
	}
	public static String getFecha_aaaa_mm_dd() {
		return getFecha_aaaa_mm_dd(new Date());
	}
	public static String getHora_HHMMSS() {
		return getHora_HHMMSS(new Date());
	}
	public static String getHora_HHMMSSDDD() {
		return getHora_HHMMSSDDD(new Date());
	}
	public static String getFechaHumana(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.getTime().toString();
	}
	public static String getFecha_aammdd(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String aaaa = "" + cal.get(Calendar.YEAR);
		String mm = padLeftCeros(cal.get(Calendar.MONTH)+1,2);
		String dd = padLeftCeros(cal.get(Calendar.DAY_OF_MONTH),2);
		return aaaa.substring(2) + mm + dd;
	}
	public static String getFecha_aaaa_mm_dd(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String aaaa = "" + cal.get(Calendar.YEAR);
		String mm = padLeftCeros(cal.get(Calendar.MONTH)+1,2);
		String dd = padLeftCeros(cal.get(Calendar.DAY_OF_MONTH),2);
		return aaaa + "-" + mm + "-" + dd;
	}
	public static String getHora_HHMMSS(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String hh = padLeftCeros(cal.get(Calendar.HOUR_OF_DAY),2);
		String mm = padLeftCeros(cal.get(Calendar.MINUTE),2);
		String ss = padLeftCeros(cal.get(Calendar.SECOND),2);
		return hh + mm + ss;
	}
	public static String getHora_HHMMSSDDD(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String hh  = padLeftCeros(cal.get(Calendar.HOUR_OF_DAY),2);
		String mm  = padLeftCeros(cal.get(Calendar.MINUTE),2);
		String ss  = padLeftCeros(cal.get(Calendar.SECOND),2);
		String ddd = padLeftCeros(cal.get(Calendar.MILLISECOND),3);
		return hh + mm + ss + ddd;
	}
	public static long   getDateInMills() {
		return Calendar.getInstance().getTimeInMillis();
	}
	public static String getDateAuditoria() {
		// aammddhhMMssddd
		return getDateAuditoria(new Date());
	}
	public static String getDateAuditoria(Date date) {
		// aammddhhMMssddd
		return getFecha_aammdd(date) + getHora_HHMMSSDDD(date);
	}
	
	public static String cvtFec_dd_mm_aa__saammdd(String dd_mm_aa) {
		String res = dd_mm_aa;
		if ( res != null && res.trim().length() == 8 ) {
			res = res.trim();
			res = "1" + res.substring(6) + res.substring(3,5) + res.substring(0,2);
		}
		return res;
	}
	public static String cvtFec_dd_mm_aa__aammdd(String dd_mm_aa) {
		String res = dd_mm_aa;
		if ( res != null && res.trim().length() == 8 ) {
			res = res.trim();
			res = res.substring(6) + res.substring(3,5) + res.substring(0,2);
		}
		return res;
	}
	public static String cvtFec_saammdd__dd_mm_aa(String saammdd) {
		String res = saammdd;
		if ( res != null && res.trim().length() == 7 ) {
			res = res.trim();
			res = res.substring(5) + "/" + res.substring(3,5) + "/" + res.substring(1,3);
		}
		return res;
	}
	public static String cvtFec_dd_mm_aa__aaaa_mm_dd(String dd_mm_aa) {
		String res = dd_mm_aa;
		if ( res != null && res.trim().length() == 8 ) {
			res = res.trim();
			res = "20" + res.substring(6) + "-" + res.substring(3,5) + "-" + res.substring(0,2);
		}
		return res;
	}
	public static String cvtFec_aaaa_mm_dd__dd_mm_aa(String aaaa_mm_dd) {
		String res = aaaa_mm_dd;
		if ( res != null && res.trim().length() == 10 ) {
			res = res.trim();
			res = res.substring(8) + "/" + res.substring(5,7) + "/" + res.substring(2,4);
		}
		return res;
	}
	public static Date   cvtFec_aaaa_mm_dd__date(String aaaa_mm_dd) {
		Date parsed = null;
		 try {
			 if ( aaaa_mm_dd != null && aaaa_mm_dd.trim().length() == 10 ) {
				parsed = new SimpleDateFormat("yyyy-MM-dd").parse(aaaa_mm_dd);
			 }
		} catch (ParseException e) {System.err.println(e.getMessage());}
		return parsed;		
	}
	public static Date   cvtFec_mills__date( long dateMills ) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(dateMills);
		return cal.getTime();
	}
	////////////////////////
	public static String transformar_Lista_Vertical_a_Horizontal(BeanParaTablaHash[] lista) {
		String enumeracionHorizontal = "";
		if ( lista != null ) {
			for( int i=0; i <lista.length; i++ ) {
				enumeracionHorizontal += i>0?",":"";
				enumeracionHorizontal += lista[i].getValue();
			}
		}
		return enumeracionHorizontal;
	}
	public static void addLog( BDConexion dataBase, String usr, String accion, String datos, String className) {
		
		if ( dataBase == null ) { return; }
		
//		if ( t1 != null && t1.trim().length() > 1500) { t1 = t1.substring(0,1500); }
//		if ( t2 != null && t2.trim().length() > 1500) { t2 = t2.substring(0,1500); }
//		if ( t3 != null && t2.trim().length() > 1500) { t3 = t3.substring(0,1500); }

		addLogSynchronized(dataBase,usr,accion,datos,className,new com.fvr.lg_logs.db.LgAccesoBaseDatos());
		
	}
	private static synchronized void addLogSynchronized( BDConexion dataBase, String usr, String t1, String t2, String t3, com.fvr.lg_logs.db.LgAccesoBaseDatos dao_lg ) {
		try {
			com.fvr.lg_logs.bean.LgBean rgLG = new com.fvr.lg_logs.bean.LgBean();

				rgLG.setLg_sincro( Subrutinas.getDateAuditoria() );
				rgLG.setLg_author( usr );
				rgLG.setLg_text_1( t1 );
				rgLG.setLg_text_2( t2 );
				rgLG.setLg_text_3( t3 );

				JSONObject json = new JSONObject();
				json.put("computerName",Subrutinas.getComputername());
				rgLG.setLg_json( json.toString() );
			
				dao_lg.lg_crtObj( dataBase, rgLG );

		} catch (StExcepcion e) {
			System.err.println( e.getCause() + "\n" +  e.getMessage() );
		}
	}
	////////////////////////
	public static String getDBValueFromKey(BDConexion dataBase, String key) {
		return getDBValueFromKey(dataBase, _K.SYS, key);
	}
	public static String getDBValueFromKey(BDConexion dataBase, String group, String key) {
		String resultado = null;
		if ( key == null || key.trim().length() < 1 || group == null || group.trim().length() < 1 || dataBase == null ) { return resultado; }
		///////////////////////////////
		com.fvr.pa_systemParameters.db.PaAccesoBaseDatos dao_pa = new com.fvr.pa_systemParameters.db.PaAccesoBaseDatos();
		com.fvr.pa_systemParameters.bean.PaBean          reg_pa = new com.fvr.pa_systemParameters.bean.PaBean();
		try {
			// PK:
			reg_pa.setPa_group_id( group );
			reg_pa.setPa_key( key );
			reg_pa = dao_pa.pa_getRcd(dataBase,reg_pa);
			if ( reg_pa != null ) {
				resultado = reg_pa.getPa_value();
			}
		} catch (StExcepcion ex) {;
		} finally {
			dao_pa = null;
			reg_pa = null;
		}
		///////////////////////////////
		return resultado;
	}
	public static void   setDBValueToKey(BDConexion dataBase, String group, String key, String value) {
		if ( key == null || key.trim().length() < 1 || group == null || group.trim().length() < 1 || dataBase == null ) { return; }
		///////////////////////////////
		com.fvr.pa_systemParameters.db.PaAccesoBaseDatos dao_pa = new com.fvr.pa_systemParameters.db.PaAccesoBaseDatos();
		com.fvr.pa_systemParameters.bean.PaBean          reg_pa = new com.fvr.pa_systemParameters.bean.PaBean();
		try {
			// PK:
			reg_pa.setPa_group_id( group );
			reg_pa.setPa_key( key );
			reg_pa = dao_pa.pa_getRcd(dataBase,reg_pa);
			if ( reg_pa != null ) {
				reg_pa.setPa_value( value );
				dao_pa.pa_chgObj(dataBase, reg_pa);
			} else {
				reg_pa = new PaBean();
				reg_pa.setPa_author( _K.CUENTA_DE_SISTEMA );
				reg_pa.setPa_group_id( group );
				reg_pa.setPa_key( key );
				reg_pa.setPa_value( value );
				dao_pa.pa_crtObj(dataBase, reg_pa);
			}
		} catch (StExcepcion ex) {;
		} finally {
			dao_pa = null;
			reg_pa = null;
		}
		///////////////////////////////
		return;
	}
	public static String getValorFromVariableEnFormulario( final ActionForm form, final String nombreCampo) {
		/////////////////////////
		// Reflection: "nombreCampo" debe ser un campo PUBLIC en el ActionForm.
		try {
			// ¡¡¡ PUBLIC !!!!
			Field campo = form.getClass().getField( nombreCampo );
			if ( campo != null ) { 
				Object valor = campo.get(form);
				if ( valor != null) {
					return valor.toString(); 
				}
			}
		} catch (NoSuchFieldException e) {
//			e.printStackTrace();
		} catch (SecurityException e) {
//			e.printStackTrace();
		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
		} catch (IllegalAccessException e) {
//			e.printStackTrace();
		}
		/////////////////////////
		return null;
	}
	public static void setValorToVariableEnFormulario_Int( final ActionForm form, final String nombreCampo, final int valor) {
		/////////////////////////
		// Reflection: "nombreCampo" debe ser un campo PUBLIC en el ActionForm.
		try {
			// ¡¡¡ PUBLIC !!!!
			Field campo = form.getClass().getField( nombreCampo );
			if ( campo != null ) { 
				campo.setInt(form,valor);
			}
		} catch (NoSuchFieldException e) {
//			e.printStackTrace();
		} catch (SecurityException e) {
//			e.printStackTrace();
		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
		} catch (IllegalAccessException e) {
//			e.printStackTrace();
		}
		/////////////////////////
		return;
	}
	public static void setValorToVariableEnFormulario_obj( final ActionForm form, final String nombreCampo, final Object valor) {
		/////////////////////////
		// Reflection: "nombreCampo" debe ser un campo PUBLIC en el ActionForm.
		try {
			// ¡¡¡ PUBLIC !!!!
			Field campo = form.getClass().getField( nombreCampo );
			if ( campo != null ) { 
				campo.set(form,valor);
			}
		} catch (NoSuchFieldException e) {
//			e.printStackTrace();
		} catch (SecurityException e) {
//			e.printStackTrace();
		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
		} catch (IllegalAccessException e) {
//			e.printStackTrace();
		}
		/////////////////////////
		return;
	}

	public static boolean isEmailCreible( final String email ) {
		// Para evaluar cuentas de correo, por ejemplo:
		// 		eestecha@gmail.com => gmail.com
		
		if ( email == null || email.trim().length() < 1 ) { return false; }
		
		String trozos[] = email.split("@");
		if ( trozos == null || trozos.length != 2 ) { return false; }
		if ( trozos[0] == null || trozos[0].trim().length() < 1 ) { return false; }
		if ( trozos[1] == null || trozos[1].trim().length() < 1 ) { return false; }

		return isDominioCorrecto( trozos[1] );
	}
	public static boolean isDominioCorrecto( final String dominio ) {
		// Para evaluar cuentas de correo, por ejemplo:
		// 		eestecha@gmail.com => gmail.com
		boolean resultado = false;

		if ( dominio != null && dominio.trim().length() > 0 ) {
			try {
				InetAddress inet = InetAddress.getByName( dominio );
				if ( inet != null ) {
					resultado = true;
				}
			} catch (UnknownHostException e) {
//				e.printStackTrace();
				System.err.println("*** ERROR en check dominio(1d2) - InetAddress.getByName('" + dominio +"'): " + e.getMessage());
			}
		}
		
		if ( ! resultado ) {
			try {
				String[] listaHostsMX = lookupMailHosts(dominio);
				if ( listaHostsMX != null && listaHostsMX.length > 0 ) {
					resultado = true;
				}
			} catch (NamingException e) {
//				e.printStackTrace();
				System.err.println("*** ERROR en check dominio(2d2) - lookupMailHosts('" + dominio +"'): " + e.getMessage());
			}
		}
		
// INTERCEPTADO !!!!! BASURA !!!!!!! INCUBATE !!!!!
//		resultado = true;

		return resultado;
	}
	public static String[] lookupMailHosts( final String domainName) throws NamingException {
        // see: RFC 974 - Mail routing and the domain system
        // see: RFC 1034 - Domain names - concepts and facilities
        // see: http://java.sun.com/j2se/1.5.0/docs/guide/jndi/jndi-dns.html
        //    - DNS Service Provider for the Java Naming Directory Interface (JNDI)
		
//        // get the default initial Directory Context
//        InitialDirContext iDirC = new InitialDirContext();
//        // get the MX records from the default DNS directory service provider
//        //    NamingException thrown if no DNS record found for domainName
//        Attributes attributes = iDirC.getAttributes("dns:/" + domainName, new String[] {"MX"});

		////////////////
		// Retocado por EED porque el código anterior no funcionaba en GlassFish, solo iba en TomCat:
		Hashtable<String,String> env = new Hashtable<String,String>();
		env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
		env.put("java.naming.provider.url",    "dns://8.8.8.8");	// Google "anticensura" (Don´t be evil)
		////////////////

		DirContext ictx = new InitialDirContext(env);
		Attributes attributes = ictx.getAttributes( domainName, new String[] {"MX"});
        // attributeMX is an attribute ('list') of the Mail Exchange(MX) Resource Records(RR)
        Attribute attributeMX = attributes.get("MX");

        // if there are no MX RRs then default to domainName (see: RFC 974)
        if (attributeMX == null) {
//            return (new String[] {domainName});
            return null;
        }

        // split MX RRs into Preference Values(pvhn[0]) and Host Names(pvhn[1])
        String[][] pvhn = new String[attributeMX.size()][2];
        for (int i = 0; i < attributeMX.size(); i++) {
            pvhn[i] = ("" + attributeMX.get(i)).split("\\s+");
        }

        // sort the MX RRs by RR value (lower is preferred)
        Arrays.sort(
		        		pvhn, new Comparator<String[]>() {
			                public int compare(String[] o1, String[] o2) {
			                    return (Integer.parseInt(o1[0]) - Integer.parseInt(o2[0]));
			                }
		        		}
        			);

        // put sorted host names in an array, get rid of any trailing '.' 
        String[] sortedHostNames = new String[pvhn.length];
        for (int i = 0; i < pvhn.length; i++) {
            sortedHostNames[i] = pvhn[i][1].endsWith(".") ? 
                pvhn[i][1].substring(0, pvhn[i][1].length() - 1) : pvhn[i][1];
        }
        
        for( String item : sortedHostNames ) {
        	System.out.println( "Subrutinas.lookupMailHosts( " + domainName + " ) -> " + item );
        }
        
        return sortedHostNames;
    }	

	public static String macroSustituye( String losDatos, String patron, String valor ) {
        String pre, pos;
        for (int ix = losDatos.indexOf(patron);ix>-1;ix=losDatos.indexOf(patron)) {
            int fx = ix + patron.trim().length();      // Fin del patrón.
            pre = losDatos.substring(0, ix);
            pos = losDatos.substring(fx);
            losDatos = pre + valor + pos;
        }
        pre = null; pos = null;
        return losDatos;
	}
	public static String getStartTime(String start_time) {
		if ( start_time == null || start_time.trim().length() != 4 ) { return start_time; }
		return start_time.substring(0, 2) + ":" + start_time.substring(2);
	}
	///////////////////////
	
	public byte[] base64_decode( String texto_B64 ) {
		byte[] byteArray = null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byteArray = decoder.decodeBuffer(texto_B64);
		} catch (IOException e) { System.out.println( "base64_decode() : " + e.getMessage() ); }
		return byteArray;
	}
	public String base64_encode( byte[] datos ) {
		String resultado = null;
		if ( datos != null ) {
			resultado = new BASE64Encoder().encode(datos);
		}
        return resultado;
	}

	public BeanParaTablaHash[] cargarLista(HttpServletRequest request, ActionForm form, String idLista, String entidad, String action) {
		BeanParaTablaHash[] lista = null;
		if (lista == null) {
			lista = new BeanParaTablaHash[1];
		}
		lista[0] = new BeanParaTablaHash("", "Sin datos para el usuario");
		if(idLista!=null && idLista.trim().length()>0){
			//            if ("Activo".equalsIgnoreCase(idLista))
			//                return this.cargarLista_Activo(request, form, entidad, action);
			return lista;
		} else {
			return lista;
		}

	}

	public static String sql_OperandoLista(BeanParaTablaHash[] rstLista, String campo, String sqlWhere) {
		String resultado = sqlWhere;
		////////////////////////////////////////
		if (rstLista != null) {
			String operando = "";
			boolean primeraVez = true;
			for (int x = 0; x < rstLista.length; x++) {
				if (rstLista[x].getKey() != null && rstLista[x].getKey().trim().length() > 0) {
					if (!primeraVez) {
						operando += ",";
					}
					operando += "'" + rstLista[x].getKey().trim() + "'";
					primeraVez = false;
				}
			}
			operando = (operando.trim().length() < 1) ? "'nada.'" : operando;

			resultado += (resultado.trim().length() == 0) ? " WHERE " : " AND ";
			resultado += campo + " IN(" + operando + ")";
		}
		////////////////////////////////////////
		return resultado;
	}
	public static String sql_OperandoLista_Not(BeanParaTablaHash[] rstLista, String campo, String sqlWhere) {
		String resultado = sqlWhere;
		////////////////////////////////////////
		if (rstLista != null) {
			String operando = "";
			boolean primeraVez = true;
			for (int x = 0; x < rstLista.length; x++) {
				if (rstLista[x].getKey() != null && rstLista[x].getKey().trim().length() > 0) {
					if (!primeraVez) {
						operando += ",";
					}
					operando += "'" + rstLista[x].getKey().trim() + "'";
					primeraVez = false;
				}
			}
			operando = (operando.trim().length() < 1) ? "'nada.'" : operando;

			resultado += (resultado.trim().length() == 0) ? " WHERE " : " AND ";
			resultado += campo + " NOT IN(" + operando + ")";
		}
		////////////////////////////////////////
		return resultado;
	}

	//////////////////////
	// Ayudas para usar AngularJS
	public static boolean isVersionAngular(HttpServletRequest request,ActionForm form) {
		return Subrutinas.ActionFormfromJson(form,Subrutinas.getRequestPayload_json(request, true));
	}
	public static JSONObject getRequestPayload_json(HttpServletRequest request, boolean isOption_FormDataConvert) {
		/////////////////////////
		JSONObject json = null;
		String request_payload = getRequestPayload_txt(request);
	    if ( request_payload != null && request_payload.trim().length() > 0 ) {
	    	try { json = JSONObject.fromObject( request_payload ); } catch (Exception e) {;}
	    	
	    	////////////
	    	// Si se quiere forzar a json un payload recibido desde un formulario "que no viene en json":
	    	if ( json == null && isOption_FormDataConvert ) {
	    		String trozos[] = request_payload.split("&");
	    		if ( trozos != null && trozos.length > 0 ) {
	    			json = new JSONObject();
	    			for ( String item : trozos ) {
	    				String[] variable = item.split("=");
	    				if ( variable != null && variable.length == 2 ) {
	    					json.put(variable[0], variable[1]);
	    				}
	    			}
	    		}
	    	}
	    	////////////
	    	
	    }
	    return json;
		/////////////////////////
	}
	public static String     getRequestPayload_txt(HttpServletRequest request) {
		/////////////////////////
	    BufferedReader reader = null;
	    StringBuilder buffer = null;
		String request_payload = null;
		try {
			// payloadRequest: Read from request
		    reader = request.getReader();
		    buffer = new StringBuilder();
			while ((request_payload = reader.readLine()) != null) { buffer.append(request_payload); }
			request_payload = buffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		    buffer = null;
		}
	    return request_payload;
		/////////////////////////
	}
	public static boolean ActionFormfromJson( ActionForm form, JSONObject json ) { 
		boolean resultado = false;

		if ( form == null) return resultado;
		if ( json == null) return resultado;
		
		// Procesa SOLO los campos "public" !!!
		
		/////////
		// Para averiguar el xxBean de que se trata se usa un truco feo deduciéndolo de xxBeanFiltro (Atención a la nomenclatura...mala práctica!!)
		Object beanClassEntidad = null;
		for ( Field formFld : form.getClass().getFields() ) {
			if ( formFld.getType().getName().endsWith("BeanFiltro") ) {
				try {
					String beanClassName = formFld.getType().getName().replace("Filtro","");
					Class<?> clazz = Class.forName( beanClassName );
					Constructor<?> ctor = clazz.getConstructor();
					beanClassEntidad = ctor.newInstance();
					break;
				} 
				catch (ClassNotFoundException e) {break;} catch (NoSuchMethodException e) {break;} 
				catch (SecurityException e) {break;} catch (InstantiationException e) {break;} 
				catch (IllegalAccessException e) {break;} catch (IllegalArgumentException e) {break;} 
				catch (InvocationTargetException e) {break;}
			}
		}
		/////////

		Object nombreFormFld = null;
		for ( Field formFld : form.getClass().getFields() ) {
			try {
				nombreFormFld = formFld.getName();
				
				// Se evita porque este campo es "static". No se debe intentar porque arroja una exception.
				if ( "serialVersionUID".equalsIgnoreCase((String) nombreFormFld) ) continue;
				
				if ( nombreFormFld != null ) {
					Object jsonMember = json.get( nombreFormFld.toString() );
					if ( jsonMember != null ) {
						try {
							if ( "java.lang.String".equalsIgnoreCase( formFld.getType().getName() ) ) {
								formFld.set( form, jsonMember.toString() );
							} else 
							if ( "int".equalsIgnoreCase( formFld.getType().getName() ) ) {
								formFld.set( form, Subrutinas.parse_integer( jsonMember.toString() ) );
							} else 
							if ( "long".equalsIgnoreCase( formFld.getType().getName() ) ) {
								formFld.set( form, Subrutinas.parse_long( jsonMember.toString() ) );
							} else 
							if ( "double".equalsIgnoreCase( formFld.getType().getName() ) ) {
								formFld.set( form, Subrutinas.parse_double( jsonMember.toString() ) );
							} else
							if (jsonMember instanceof JSONObject) {
								// Por ejemplo el Filtro que es un bean que extiende de StBean, con el toString() sobreescrito para generar un json...
								if ( formFld.getType().getName().endsWith("BeanFiltro") ) {
									Class<?> clazz = Class.forName( formFld.getType().getName() );
									if ( null != clazz ) {
										Constructor<?> ctor = clazz.getConstructor();
										if ( null != ctor ) {
											Object object = ctor.newInstance();
											if ( null != object ) {
												Object cosa = JSONObject.toBean( (JSONObject)jsonMember, object.getClass() );
												formFld.set( form, cosa );
											}
										}
									}
								}
							} else 
							if (jsonMember instanceof JSONArray) {
								// Pueden ser varios: "clavesMarcadas", "grid", etc...
								if ( formFld.getName().equalsIgnoreCase("clavesMarcadas") ) {
									try { 
										// public String[] clavesMarcadas; // Para acciones colectivas de una lista....
										JSONArray jsonArray = JSONArray.fromObject(jsonMember);
										String[] clavesMarcadas = new String[jsonArray.size()];
										for ( int i=0; i < jsonArray.size(); i++ ) {
											if ( JSONNull.getInstance() != jsonArray.get(i) ) {
												clavesMarcadas[i] = (String) jsonArray.get(i);
											}
										}
										formFld.set( form, clavesMarcadas );
									} catch (Exception e) { e.printStackTrace(); }
								} else 
								if ( formFld.getName().equalsIgnoreCase("grid") ) {
									if ( beanClassEntidad != null ) {
										try { 
											// public StBean[] grid;
											JSONArray jsonArray = JSONArray.fromObject(jsonMember);
											StBean[] grid = new StBean[jsonArray.size()];
											for ( int i=0; i < jsonArray.size(); i++ ) {
												if ( JSONNull.getInstance() != jsonArray.get(i) ) {

													JSONObject un_bean = (JSONObject)jsonArray.get(i);

													grid[i] = (StBean) JSONObject.toBean( un_bean, beanClassEntidad.getClass() );
												}
											}
											formFld.set( form, grid ); 
										} catch (Exception e) { e.printStackTrace(); }
									}
								}
							}

							resultado = true;

						} catch (Exception e) {
							System.err.println(e.getMessage());
						}
					}
				}
			} catch (SecurityException e) {
//					e.printStackTrace();
			} catch (IllegalArgumentException e) {
//					e.printStackTrace();
//			} catch (IllegalAccessException e) {
//					e.printStackTrace();
			}
		}
		return resultado;
	}
	public static boolean ActionFormToJson( ActionForm form, JSONObject json ) { 
		boolean resultado = false;

		if ( form == null) return resultado;
		if ( json == null) return resultado;
		
		// Procesa SOLO los campos "public" !!!

		Object valor_N = null;
		Object valor_V = null;
    	for ( Field f : form.getClass().getFields() ) {
			try {
				valor_N = f.getName();
				valor_V = f.get( form );
			} catch (SecurityException e) {
//				e.printStackTrace();
			} catch (IllegalArgumentException e) {
//				e.printStackTrace();
			} catch (IllegalAccessException e) {
//				e.printStackTrace();
			}
			if ( valor_N != null && valor_V != null ) {
//				// Con .toString no traducía a texto el grid, sino que sacaba el puntero del objeto: [Lcom.fvr.us_Usuarios.bean.UsBean;@6c71f5
//				json.put(valor_N.toString(), valor_V.toString());
				json.put(valor_N.toString(), valor_V);
				if (!resultado) { resultado = true; }
			}
    	}

		return resultado;
	}
	public static void returnActionVersionAngular( HttpServletRequest request, HttpServletResponse response, Object laClaseInformante_o_null, boolean isOk, String texto_SiEsOK ) throws IOException {
		String textoDeSalida = "";
		///////////////
		if ( isOk ) {
			textoDeSalida = texto_SiEsOK;
		} else {
			ActionMessages errores = (ActionMessages) request.getAttribute( "org.apache.struts.action.ERROR" );
			if ( errores != null ) {
				@SuppressWarnings("unchecked")
				Iterator<ActionMessage> it = errores.get();
	    		ActionMessage item = null;
	        	while ( it.hasNext() ) {
	        		item = it.next();
	        		if ( item != null ) {
	        			for( Object frase : item.getValues() ) {
	        				textoDeSalida += frase + "\n";
	        			}
	        		}
	        	}
			}
		}
		///////////////
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();

		json.put( "server", Subrutinas.getComputername() );
		json.put( "class",  null!=laClaseInformante_o_null?laClaseInformante_o_null.getClass().getSimpleName():"" );
		json.put( "rc",     isOk?"OK":"KO" );
		json.put( "text",   textoDeSalida );

		out.print( json.toString() );

		out.close();
		///////////////
    }
	//////////////////////

	public static String getRoleMenu(String role) {
		String textoMenu_prefijo = "";
		String textoMenu = "";
		String textoMenu_sufijo = "";
		String calificador = null;
		int contador = 0;

			 if ( _K.ROL_USER.equals( role ) ) { calificador = "user"; }
		else if ( _K.ROL_FRANQUICIA.equals( role ) ) { calificador = "franquicia"; } 
		else if ( _K.ROL_ADMIN.equals( role ) ) {  calificador = "admin"; }

		if ( calificador != null ) {
			try { contador = parse_integer( org.apache.struts.util.MessageResources.getMessageResources(Subrutinas.archivo_es).getMessage("menu." + calificador + ".items") ); } catch (Exception e) {;}
			try { textoMenu_prefijo += org.apache.struts.util.MessageResources.getMessageResources(Subrutinas.archivo_es).getMessage("menu." + calificador + ".prefix"); } catch (Exception e) {;}
			try { textoMenu_sufijo += org.apache.struts.util.MessageResources.getMessageResources(Subrutinas.archivo_es).getMessage("menu." + calificador + ".sufix"); } catch (Exception e) {;}
			for ( int i = 1; i <= contador; i++) {
				textoMenu += org.apache.struts.util.MessageResources.getMessageResources(Subrutinas.archivo_es).getMessage("menu." + calificador + ".item_" + i);
			}
		}

		return textoMenu_prefijo + textoMenu + textoMenu_sufijo;
	}
	public static long getRsNextKey( BDConexion dataBase, boolean isAvanzarContador ) {
		long nextKey = -1L;

		GregorianCalendar gc = new GregorianCalendar();
		long diaJuliano = (1000*(gc.get(GregorianCalendar.YEAR)-2000)) + gc.get(GregorianCalendar.DAY_OF_YEAR);
		long contador = 1L;

		////////////
		// Inicializa con el último valor utilizado:
		String lastKey = getDBValueFromKey(dataBase, _K.PA_KEY_RS_LAST_KEY);
		if ( lastKey != null && lastKey.trim().length() >= 5 ) {
			long last_diaJuliano = parse_long( lastKey.substring(0,5) );
			long last_contador   = parse_long( lastKey.substring(5) );
			if ( diaJuliano == last_diaJuliano ) {
				contador = last_contador;
			}
		}
		////////////

		long clave_reserva = (diaJuliano * 10000) + contador;	// Hasta diezmil al día....

		try {
			com.fvr.rs_reservations.db.RsAccesoBaseDatos dao_rs = new com.fvr.rs_reservations.db.RsAccesoBaseDatos();
			com.fvr.rs_reservations.bean.RsBean          reg_rs = new com.fvr.rs_reservations.bean.RsBean();
			reg_rs.setRs_reservation_id( ""+clave_reserva );
			while ( dao_rs.rs_getRcd(dataBase, reg_rs) != null ) {
				reg_rs.setRs_reservation_id( ""+(++clave_reserva) );
			}
			nextKey = clave_reserva;
		} catch (StExcepcion e) {;}

		////////////
		if ( isAvanzarContador ) {
			setDBValueToKey(dataBase, _K.CUENTA_DE_SISTEMA, _K.PA_KEY_RS_LAST_KEY, ""+nextKey);
		}
		////////////

		return nextKey;
	}
	public static JSONObject getJsonFromToken( BDConexion dataBase, String token_id ) throws StExcepcion {
		JSONObject json = null;

		if ( token_id == null || token_id.trim().length() < 1 ) { return json; }

		com.fvr.tk_tokens.db.TkAccesoBaseDatos dao_tk = new com.fvr.tk_tokens.db.TkAccesoBaseDatos();
		com.fvr.tk_tokens.bean.TkBean          reg_tk = new com.fvr.tk_tokens.bean.TkBean();

		reg_tk.setTk_token_id( token_id );

		reg_tk = dao_tk.tk_getRcd(dataBase, reg_tk);

		if ( reg_tk != null ) {
			try { json =  JSONObject.fromObject( reg_tk.getTk_json() ); } catch (Exception e) {;}
		}

		return json;
	}
	public static boolean checkDisponibilidad( BDConexion dataBase, RsBean reg_rs, ArrayList<String> errores ) {
		return Reservas.checkDisponibilidad(dataBase, reg_rs, errores);
	}
	public static boolean isExisteNick(BDConexion dataBase, String user_id, String nick) {
		boolean resultado = true;
		
		if ( user_id == null || user_id.trim().length() < 1 ) { return resultado; }
		if ( nick == null || nick.trim().length() < 1 ) { return resultado; }

		try {
			com.fvr.us_users.db.UsAccesoBaseDatos dao_us = new com.fvr.us_users.db.UsAccesoBaseDatos();
			com.fvr.us_users.bean.UsBeanFiltro    flt_us = new com.fvr.us_users.bean.UsBeanFiltro();
			com.fvr.us_users.bean.UsBean[]        rgs_us = null;
			
			flt_us.setUs_nick( nick );
			
			rgs_us = dao_us.us_getSeq(dataBase, new ConfigPantalla(Integer.MAX_VALUE), flt_us, false);
			if ( rgs_us != null ) {
				boolean isHallado = false;
				for ( UsBean item : rgs_us ) {
					if ( !item.getUs_user_id().equalsIgnoreCase( user_id )  ) {
						isHallado = true;
					}
				}
				if ( ! isHallado ) {
					resultado = false;
				}
			}
		} catch (StExcepcion e) {;}
		
		return resultado;
	}

	public void tratarImagenesEnRegUs(UsBean reg_us) {
		if( reg_us == null ) return;
		////////////
		// Siempre enviar las imágenes en Base64:
		String realPathApp = "";
		try {
			realPathApp = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).toString();
			if ( realPathApp != null ) {
				int i = realPathApp.indexOf( "WEB-INF" );
				if ( 1 > -1 ) {
					realPathApp = realPathApp.substring(0, i);
				}
			}
		} catch (URISyntaxException e) {;}
		// Avatar:
		if ( reg_us.getUs_avatar() == null || reg_us.getUs_avatar().trim().length() < 1 ) {
			reg_us.setUs_avatar( _K.AVATAR_IMG_UNKNOWN );
		}
		if (  _K.AVATAR_IMG_UNKNOWN.equalsIgnoreCase( reg_us.getUs_avatar() ) ) {
			try {
				File file = new File( realPathApp + "/" + reg_us.getUs_avatar() );
				byte[] bin = Subrutinas.readFileBin(null, file.getAbsolutePath() );
				if ( bin != null && bin.length > 0) {
					String base64 = new String( Base64.encodeBase64(bin) );
					reg_us.setUs_avatar( "data:image/png;base64," + base64 );
				}
			} catch (Exception e) {;}
		}
		// Flag:
		if ( reg_us.getUs_PS_flag_base64() == null || reg_us.getUs_PS_flag_base64().trim().length() < 1 ) {
			reg_us.setUs_PS_flag_base64( _K.AVATAR_FLAG_UNKNOWN );
		}
		if ( _K.AVATAR_FLAG_UNKNOWN.equalsIgnoreCase( reg_us.getUs_PS_flag_base64() ) ) {
			try {
				File file = new File( realPathApp + "/" + reg_us.getUs_PS_flag_base64() );
				byte[] bin = Subrutinas.readFileBin(null, file.getAbsolutePath() );
				if ( bin != null && bin.length > 0) {
					String base64 = new String( Base64.encodeBase64(bin) );
					reg_us.setUs_PS_flag_base64( "data:image/png;base64," + base64 );
				}
			} catch (Exception e) {;}
		}
		////////////
	}

	public static EsBean derivarCamposRegistro(BDConexion dataBase, EsBean reg_es, EvBean evBean) {

        reg_es.setEs_author( reg_es.getEs_inscription_user_id() );

		reg_es.setEs_tpv_order( "" );
        reg_es.setEs_amount( 0.0 );
        reg_es.setEs_currency( "€" );

        if (reg_es.getEs_event_id() != null && reg_es.getEs_event_id().trim().length() > 0) {
        		reg_es.setEs_EV_location_id(evBean.getEv_location_id());
        		reg_es.setEs_LO_name(evBean.getEv_LO_name());
        		
    			////////////
    			String order_AUX =  Subrutinas.getDateAuditoria() + reg_es.getEs_event_id().trim() + reg_es.getEs_inscription_user_id().trim();
    			java.util.zip.CRC32 order_checkSum = new java.util.zip.CRC32();
    			order_checkSum.update( order_AUX.getBytes() );
    			String order = Long.toHexString( order_checkSum.getValue() );
    			
    			order = Subrutinas.getDateAuditoria().substring(2,6) + order.toUpperCase();

    			reg_es.setEs_tpv_order( order );
    	        reg_es.setEs_amount( evBean.getEv_amount() );
    	        reg_es.setEs_currency( evBean.getEv_currency() );
    			////////////
        }
		return reg_es;
	}
	
	public static TkBean getTkFromId(BDConexion dataBase, String token_id) {
		TkBean key = new TkBean();
		key.setTk_token_id(token_id);
		return getTkFromId(dataBase,key);
	}
	public static TkBean getTkFromId(BDConexion dataBase, TkBean key) {
		TkBean resultado = null;
		try {
			resultado = new com.fvr.tk_tokens.db.TkAccesoBaseDatos().tk_getRcd(dataBase, key);
		} catch (StExcepcion e) {;}
		finally {
			if ( resultado == null ) {
				resultado = key;
			}
		}
		return resultado;
	}
	public static UsBean getUsFromId(BDConexion dataBase, String user_id) {
		UsBean key = new UsBean();
		key.setUs_user_id(user_id);
		return getUsFromId(dataBase,key);
	}
	public static UsBean getUsFromId(BDConexion dataBase, UsBean key) {
		UsBean resultado = null;
		try {
			resultado = new com.fvr.us_users.db.UsAccesoBaseDatos().us_getRcd(dataBase, key);
		} catch (StExcepcion e) {;}
		finally {
			if ( resultado == null ) {
				resultado = key;
			}
		}
		return resultado;
	}
	public static UsBean getUsFromNick(BDConexion dataBase, String nick) {
		UsBean resultado = null;
		
		if ( nick == null || nick.trim().length() < 1 ) { return resultado; }
		
		try {
			com.fvr.us_users.db.UsAccesoBaseDatos dao_us = new com.fvr.us_users.db.UsAccesoBaseDatos();
			com.fvr.us_users.bean.UsBeanFiltro    flt_us = new com.fvr.us_users.bean.UsBeanFiltro();
			com.fvr.us_users.bean.UsBean[]        rgs_us = null;
			
			flt_us.setUs_nick( nick );
			
			rgs_us = dao_us.us_getSeq(dataBase, new ConfigPantalla(1), flt_us, true);
			if ( rgs_us != null && rgs_us.length > 0 ) {
				resultado = rgs_us[0];
			}
		} catch (StExcepcion e) {;}

	
		return resultado;
	}
	public static PsBean getPsFromId(BDConexion dataBase, long country_id) {
		PsBean key = new PsBean();
		key.setPs_country_id(country_id);
		return getPsFromId(dataBase,key);
	}
	public static PsBean getPsFromId(BDConexion dataBase, PsBean key) {
		PsBean resultado = null;
		try {
			resultado = new com.fvr.ps_countries.db.PsAccesoBaseDatos().ps_getRcd(dataBase, key);
		} catch (StExcepcion e) {;}
		finally {
			if ( resultado == null ) {
				resultado = key;
			}
		}
		return resultado;
	}
	public static PtBean getPtFromId(BDConexion dataBase, String product_id) {
		PtBean key = new PtBean();
		key.setPt_product_id(product_id);
		return getPtFromId(dataBase,key);
	}
	public static PtBean getPtFromId(BDConexion dataBase, PtBean key) {
		PtBean resultado = null;
		try {
			resultado = new com.fvr.pt_products.db.PtAccesoBaseDatos().pt_getRcd(dataBase, key);
		} catch (StExcepcion e) {;}
		finally {
			if ( resultado == null ) {
				resultado = key;
			}
		}
		return resultado;
	}
	public static LoBean getLoFromId(BDConexion dataBase, String location_id) {
		LoBean key = new LoBean();
		key.setLo_location_id( location_id );
		return getLoFromId(dataBase,key);
	}
	public static LoBean getLoFromId(BDConexion dataBase, LoBean key) {
		LoBean resultado = null;
		try {
			resultado = new com.fvr.lo_location.db.LoAccesoBaseDatos().lo_getRcd(dataBase, key);
		} catch (StExcepcion e) {;}
		finally {
			if ( resultado == null ) {
				resultado = key;
			}
		}
		return resultado;
	}
	public static RsBean getRsFromId(BDConexion dataBase, String reservation_id) {
		RsBean key = new RsBean();
		key.setRs_reservation_id( reservation_id );
		return getRsFromId(dataBase,key);
	}
	public static RsBean getRsFromId(BDConexion dataBase, RsBean key) {
		RsBean resultado = null;
		try {
			resultado = new com.fvr.rs_reservations.db.RsAccesoBaseDatos().rs_getRcd(dataBase, key);
		} catch (StExcepcion e) {;}
		finally {
			if ( resultado == null ) {
				resultado = key;
			}
		}
		return resultado;
	}
	public static RsBean[] getRsFromUs(BDConexion dataBase, String user_id, String coupon_id_o_null, boolean isSolopagadas) {
		RsBean[] resultado = null;
		if ( user_id == null || user_id.trim().length() < 1 ) { return resultado; }
		try {
			com.fvr.rs_reservations.db.RsAccesoBaseDatos	dao_rs = new com.fvr.rs_reservations.db.RsAccesoBaseDatos();
			com.fvr.rs_reservations.bean.RsBeanFiltro		flt_rs = new com.fvr.rs_reservations.bean.RsBeanFiltro();
			flt_rs.setRs_user_id( user_id );
			flt_rs.setRs_coupon_id( coupon_id_o_null );
			resultado = dao_rs.rs_getSeq(dataBase, new ConfigPantalla(Integer.MAX_VALUE), flt_rs, isSolopagadas);
		} catch (StExcepcion e) {;}
		return resultado;
	}
	public static CdBean[] getCdFromLo(BDConexion dataBase, String location_id) {
		com.fvr.cd_LocationClosedDays.bean.CdBean[] rgs = null;
		if ( location_id != null && location_id.trim().length() > 0 ) {
			try {
				com.fvr.cd_LocationClosedDays.db.CdAccesoBaseDatos dao = new com.fvr.cd_LocationClosedDays.db.CdAccesoBaseDatos();
				com.fvr.cd_LocationClosedDays.bean.CdBeanFiltro    flt = new com.fvr.cd_LocationClosedDays.bean.CdBeanFiltro();
				flt.setCd_location_id( location_id );
				rgs = dao.cd_getSeq(dataBase, new ConfigPantalla( Integer.MAX_VALUE ), flt);
			} catch (StExcepcion e) {;}
		}
		return rgs;
	}
	public static PmBean getPmFromId(BDConexion dataBase, String coupon_id) {
		PmBean key = new PmBean();
		key.setPm_coupon_id( coupon_id );
		return getPmFromId(dataBase,key);
	}
	public static PmBean getPmFromId(BDConexion dataBase, PmBean key) {
		PmBean resultado = null;
		try {
			resultado = new com.fvr.pm_promosManuales.db.PmAccesoBaseDatos().pm_getRcd(dataBase, key);
		} catch (StExcepcion e) {;}
		finally {
			if ( resultado == null ) {
				resultado = key;
			}
		}
		return resultado;
	}
	public static TjBean getTjFromId(BDConexion dataBase, String card_id) {
		TjBean key = new TjBean();
		key.setTj_card_id( card_id );
		return getTjFromId(dataBase,key);
	}
	public static TjBean getTjFromId(BDConexion dataBase, TjBean key) {
		TjBean resultado = null;
		try {
			resultado = new com.fvr.tj_tarjetasPrepago.db.TjAccesoBaseDatos().tj_getRcd(dataBase, key);
		} catch (StExcepcion e) {;}
		finally {
			if ( resultado == null ) {
				resultado = key;
			}
		}
		return resultado;
	}
	public static EvBean getEvFromId(BDConexion dataBase, String event_id) {
		EvBean key = new EvBean();
		key.setEv_event_id( event_id );
		return getEvFromId(dataBase,key);
	}
	public static EvBean getEvFromId(BDConexion dataBase, EvBean key) {
		EvBean resultado = null;
		try {
			resultado = new com.fvr.ev_events.db.EvAccesoBaseDatos().ev_getRcd(dataBase, key);
		} catch (StExcepcion e) {;}
		finally {
			if ( resultado == null ) {
				resultado = key;
			}
		}
		return resultado;
	}
	public static EsBean getEsFromId(BDConexion dataBase, EsBean key) {
		EsBean resultado = null;
		try {
			resultado = new com.fvr.es_eventSusbscriptions.db.EsAccesoBaseDatos().es_getRcd(dataBase, key);
		} catch (StExcepcion e) {;}
		finally {
			if ( resultado == null ) {
				resultado = key;
			}
		}
		return resultado;
	}
	
	//////////////////////

}
