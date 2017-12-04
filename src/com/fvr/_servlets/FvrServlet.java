package com.fvr._servlets;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.fvr.FuentesDeDatos.BDConexion;
import com.fvr._comun.ConfigPantalla;
import com.fvr._comun.StExcepcion;
import com.fvr._comun.Subrutinas;
import com.fvr._comun._K;
import com.fvr._comun.TPV_LaCaixa.TPV_API;
import com.fvr._comun.TPV_LaCaixa.TPV_API.FormStruct;
import com.fvr._comun.img2D.util.ImageUtils;
import com.fvr._comun.mail.SendMail;
import com.fvr._comun.paypal.classes.APICredentials;
import com.fvr._comun.paypal.classes.PaypalCredentials;
import com.fvr.cd_LocationClosedDays.bean.CdBean;
import com.fvr.cp_cockpits.bean.CpBean;
import com.fvr.es_eventSusbscriptions.bean.EsBean;
import com.fvr.ev_events.bean.EvBean;
import com.fvr.lo_location.bean.LoBean;
import com.fvr.ps_countries.bean.PsBean;
import com.fvr.pt_products.bean.PtBean;
import com.fvr.rs_reservations.bean.RsBean;
import com.fvr.tj_tarjetasPrepago.bean.TjBean;
import com.fvr.tk_tokens.bean.TkBean;
import com.fvr.tt_timeTableReference.bean.TtBean;
import com.fvr.us_users.bean.UsBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class FvrServlet extends HttpServlet {
	private static final long serialVersionUID = -2863199730678795771L;
	public static String m_IP = "127.0.0.1";

    public FvrServlet() {
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
	//	/////////////////////////////////
    // Funciones del servlet:
    private static final String admMnu = "admMnu";  // Retorna lista de Departamentos
    // http://localhost:8080/FormulaVR/FvrServlet?ACC=admMnu&USR=eestecha@gmail.com&KEY=2BE9D59820EE1699D54113D60FEDDC90C67D1215
    private static final String rtvOcu = "rtvOcu";  // Retorna ocupación para Location y opcionalmente para Date y Time
    // http://localhost:8080/FormulaVR/FvrServlet?ACC=rtvOcu&LOC=CENTRAL
    // http://localhost:8080/FormulaVR/FvrServlet?ACC=rtvOcu&LOC=CENTRAL&DAT=2017-06-05
    // http://localhost:8080/FormulaVR/FvrServlet?ACC=rtvOcu&LOC=CENTRAL&DAT=2017-06-05&TIM=0930
    private static final String rtvUsData = "rtvUsData";  // Retorna datos basicos del usuario
    // http://localhost:8080/FormulaVR/FvrServlet?ACC=rtvUsData&USR=eestecha@gmail.com&KEY=2BE9D59820EE1699D54113D60FEDDC90C67D1215
    private static final String rtvTjData = "rtvTjData";  // Retorna datos  de una tarjeta prepago
    // http://localhost:8080/FormulaVR/FvrServlet?ACC=rtvTjData&USR=eestecha@gmail.com&KEY=2BE9D59820EE1699D54113D60FEDDC90C67D1215&DAT=FVR-000123
    private static final String rtvNickData = "rtvNickData";  // Retorna datos basicos del usuario
    // http://localhost:8080/FormulaVR/FvrServlet?ACC=rtvNickData&USR=eestecha@gmail.com&KEY=2BE9D59820EE1699D54113D60FEDDC90C67D1215&NCK=Chuki
    private static final String lo_lst = "lo_lst";  // Retorna lista de Departamentos
    // http://localhost:8080/FormulaVR/FvrServlet?ACC=lo_lst
    private static final String cd_lst = "cd_lst";  // Retorna lista de días de cierre del local
    // http://localhost:8080/FormulaVR/FvrServlet?ACC=cd_lst&LOC=CENTRAL
    private static final String cp_lst = "cp_lst";  // Retorna lista de cockpits de la instalación (opcionalmente también los bloqueados)
    // http://localhost:8080/FormulaVR/FvrServlet?ACC=cp_lst&LOC=CENTRAL [&BLK=S]
    private static final String ev_lst = "ev_lst";  // Retorna lista de eventos de la instalación
    // http://localhost:8080/FormulaVR/FvrServlet?ACC=ev_lst&LOC=CENTRAL
    private static final String tt_lst = "tt_lst";  // Retorna lista de sesiones
    // http://localhost:8080/FormulaVR/FvrServlet?ACC=tt_lst&LOC=CENTRAL&TYP=NORMAL [&BLK=S]
    private static final String pt_lst = "pt_lst";  // Retorna lista de Productos
    // http://localhost:8080/FormulaVR/FvrServlet?ACC=pt_lst&USR=eestecha@gmail.com&KEY=2BE9D59820EE1699D54113D60FEDDC90C67D1215
    private static final String ps_lst = "ps_lst";  // Retorna lista de Paises con opción de devolver también las banderas: &FLAG
    // http://localhost:8080/FormulaVR/FvrServlet?ACC=ps_lst&USR=eestecha@gmail.com&KEY=2BE9D59820EE1699D54113D60FEDDC90C67D1215&FLAG
    private static final String tpv_ok = "TPV_OK";  // Callback OK de pago con TPV virtual
    // http://localhost:8080/FormulaVR/FvrServlet?ACC=TPV_OK&KEY=2BE9D59820EE1699D54113D60FEDDC90C67D1215
    private static final String tpv_ko = "TPV_KO";  // Callback KO de pago con TPV virtual
    // http://localhost:8080/FormulaVR/FvrServlet?ACC=TPV_KO&KEY=2BE9D59820EE1699D54113D60FEDDC90C67D1215
    private static final String getUsrImg = "getUsrImg";  // retorna nombre del fichero de imagen del usuario aportado (Oocionalmente en binario)
    // http://localhost:8080/FormulaVR/FvrServlet?ACC=getUsrImg&USR=eestecha@gmail.com
    // http://localhost:8080/FormulaVR/FvrServlet?ACC=getUsrImg&USR=eestecha@gmail.com&BIN
    private static final String getFlgImg = "getFlgImg";  // retorna imagen de la bandera del pais indicado
    // http://localhost:8080/FormulaVR/FvrServlet?ACC=getFlgImg&KPS=724
    private static final String gamingModule = "gamingModule";  // retorna la llamada a realizar para ir al módulo de gamificación
    // http://localhost:8080/FormulaVR/FvrServlet?ACC=gamingModule&USR=eestecha@gmail.com&KEY=2BE9D59820EE1699D54113D60FEDDC90C67D1215
    private static final String rmtLogon = "rmtLogon";  // retorna la clave de operaciones a utilizar en sucesivas llamadas
    // http://localhost:8080/FormulaVR/FvrServlet?ACC=rmtLogon&USR=eestecha@gmail.com&PWD=d41d8cd98f00b204e9800998ecf8427e
    private static final String isUsrExists = "isUsrExists";  // retorna true o false según exista o no el usuario en la base de datos
    // http://localhost:8080/FormulaVR/FvrServlet?ACC=isUsrExists&USR=eestecha@gmail.com
    private static final String rsAdd = "rsAdd";  // Llamada externa para "Crear nueva Reserva"
    // http://localhost:8080/FormulaVR/FvrServlet?ACC=rsAdd&USR=eestecha@gmail.com&KEY=2BE9D59820EE1699D54113D60FEDDC90C67D1215
    private static final String usEdt = "usEdt";  // Llamada externa para "Editar mis datos de usuario"
    // http://localhost:8080/FormulaVR/FvrServlet?ACC=usEdt&USR=eestecha@gmail.com&KEY=2BE9D59820EE1699D54113D60FEDDC90C67D1215
    private static final String usAdd = "usAdd";  // Llamada externa para "Registrar un nuevo usuario"
    // http://localhost:8080/FormulaVR/FvrServlet?ACC=usAdd&USR=eestecha@gmail.com
    private static final String FVRMonitor = "FVRMonitor";  // Llamada externa para "Editar mis datos de usuario"
    // http://localhost:8080/FormulaVR/FvrServlet?ACC=FVRMonitor
    private static final String getRsFecMin = "getRsFecMin";  // Devuelve la fecha ISO mínima posible para reservar
    // http://localhost:8080/FormulaVR/FvrServlet?ACC=getRsFecMin&LOC=CENTRAL
    private static final String sndMail_forgotPass = "sndMail_forgotPass";  // Lanza un correo de "Olvidé la password"
    // http://localhost:8080/FormulaVR/FvrServlet?ACC=sndMail_forgotPass&USR=eestecha@gmail.com
    private static final String getPaymentMethods = "getPaymentMethods";  // Retorna los medios de pago de un location_id
    // http://localhost:8080/FormulaVR/FvrServlet?ACC=getPaymentMethods&USR=eestecha@gmail.com&KEY=2BE9D59820EE1699D54113D60FEDDC90C67D1215&DAT=CENTRAL
    private static final String esAdd = "esAdd";  // Llamada externa para "Inscribir un usuario en un evento"
    // http://localhost:8080/FormulaVR/FvrServlet?ACC=esAdd&USR=eestecha@gmail.com

//	/////////////////////////////////
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
    	JSONObject payload = null;
    	
    	///////////////////////////
        if (ServletFileUpload.isMultipartContent(request)){
        	uploadFile(request, response);
        	return;
        } else {
        	// Los parámetros de entrada: (Puede llamarse por POST o por GET)
        	payload = Subrutinas.getRequestPayload_json(request, true);
        	payload = payload==null?new JSONObject():payload;
    	}
    	///////////////////////////

    	payload = payload==null?new JSONObject():payload;

//    	///////////////////////////
//    	///////////////////////////
//    	///////////////////////////
//    	// TRAZA DE LA LLAMADA:
//		String headers;
//    	Map<String, String> map = getHeadersInfo(request);
//    	Iterator<String> it = map.cosaSet().iterator();
//		while (it.hasNext()) {
//			headers = (String) it.next();
//			if (headers != null) {
//				System.out.println( "(H)" + headers + ": " + map.get(headers) );
//			}
//		}
//		Enumeration<String> params = request.getParameterNames();
//		while (params.hasMoreElements()) {
//			headers = (String) params.nextElement();
//			if (headers != null) {
//				System.out.println( "(P)" + headers + ": " + request.getParameter(headers) );
//			}
//		}
//    	///////////////////////////
//    	///////////////////////////
//    	///////////////////////////

    	///////////////////////////
    	// Recepción de parámetros
    	String acc = null; 
    	String usr = null; String key = null; 
    	String loc = null; String typ = null; String blk = null; String dat = null; String tim = null; String kps = null; String nck = null;; String pwd = null;
    	
    	try { acc = payload.getString("ACC"); } catch (Exception e) { ; }
    	try { usr = payload.getString("USR"); } catch (Exception e) { ; }
    	try { key = payload.getString("KEY"); } catch (Exception e) { ; }
    	try { loc = payload.getString("LOC"); } catch (Exception e) { ; }
    	try { typ = payload.getString("TYP"); } catch (Exception e) { ; }
    	try { blk = payload.getString("BLK"); } catch (Exception e) { ; }
    	try { dat = payload.getString("DAT"); } catch (Exception e) { ; }
    	try { tim = payload.getString("TIM"); } catch (Exception e) { ; }
    	try { kps = payload.getString("KPS"); } catch (Exception e) { ; }
    	try { nck = payload.getString("NCK"); } catch (Exception e) { ; }
    	try { pwd = payload.getString("PWD"); } catch (Exception e) { ; }

    	acc = acc==null?request.getParameter("ACC"):acc;   // Clave ACCIÓN: LOGON, UPLOAD, DOWNLOAD.
    	usr = usr==null?request.getParameter("USR"):usr;   // Usuario
    	key = key==null?request.getParameter("KEY"):key;   // Clave de operaciones
    	loc = loc==null?request.getParameter("LOC"):loc;   // Location
    	typ = typ==null?request.getParameter("TYP"):typ;   // Day type
    	blk = blk==null?request.getParameter("BLK"):blk;   // Incluir también los que sean "isBlocked"
    	dat = dat==null?request.getParameter("DAT"):dat;   // Date, Data
    	tim = tim==null?request.getParameter("TIM"):tim;   // Time
    	kps = kps==null?request.getParameter("KPS"):kps;   // Clave de país
    	nck = nck==null?request.getParameter("NCK"):nck;   // Nick del usuario
    	pwd = pwd==null?request.getParameter("PWD"):pwd;   // Password en md5
    	///////////////////////////
    	// El distribuidor de procesos:
    	if(        lo_lst.equalsIgnoreCase( acc ) ) {
    		cmd_lo_lst(request, response);
    	} else if (admMnu.equalsIgnoreCase( acc )) {
    		cmd_admMnu(request, response, usr, key);
    	} else if (rtvOcu.equalsIgnoreCase( acc )) {
    		cmd_rtvOcu(request, response, loc, dat, tim);
    	} else if (rtvUsData.equalsIgnoreCase( acc )) {
    		cmd_rtvUsData(request, response, usr, key);
    	} else if (rtvTjData.equalsIgnoreCase( acc )) {
    		cmd_rtvTjData(request, response, usr, key, dat);
    	} else if (rtvNickData.equalsIgnoreCase( acc )) {
    		cmd_rtvNickData(request, response, usr, key, nck);
    	} else if (cd_lst.equalsIgnoreCase( acc )) {
    		cmd_cd_lst(request, response, loc);
    	} else if (cp_lst.equalsIgnoreCase( acc )) {
    		cmd_cp_lst(request, response, loc, blk);
    	} else if (ev_lst.equalsIgnoreCase( acc )) {
    		cmd_ev_lst(request, response, loc);
    	} else if (tt_lst.equalsIgnoreCase( acc )) {
    		cmd_tt_lst(request, response, loc, typ, blk);
    	} else if (pt_lst.equalsIgnoreCase( acc )) {
    		cmd_pt_lst(request, response, usr, key);
    	} else if (ps_lst.equalsIgnoreCase( acc )) {
    		cmd_ps_lst(request, response, usr, key);
    	} else if (tpv_ok.equalsIgnoreCase( acc )) {
    		cmd_tpv_callback(request, response, true, key);
    	} else if (tpv_ko.equalsIgnoreCase( acc )) {
    		cmd_tpv_callback(request, response, false, key);
    	} else if (getUsrImg.equalsIgnoreCase( acc )) {
    		cmd_getUsrImg(request, response, usr);
    	} else if (getFlgImg.equalsIgnoreCase( acc )) {
    		cmd_getFlgImg(request, response, kps);
    	} else if (gamingModule.equalsIgnoreCase( acc )) {
    		cmd_gamingModule(request, response, usr, key);
    	} else if (rmtLogon.equalsIgnoreCase( acc )) {
    		cmd_rmtLogon(request, response, usr, pwd);
    	} else if (isUsrExists.equalsIgnoreCase( acc )) {
    		cmd_isUsrExists(request, response, usr);
    	} else if (rsAdd.equalsIgnoreCase( acc )) {
    		cmd_rsAdd(request, response, usr, key);
    	} else if (usEdt.equalsIgnoreCase( acc )) {
    		cmd_usEdt(request, response, usr, key);
    	} else if (usAdd.equalsIgnoreCase( acc )) {
    		cmd_usAdd(request, response, usr);
    	} else if (FVRMonitor.equalsIgnoreCase( acc )) {
    		cmd_FVRMonitor(request, response, payload);
    	} else if (getRsFecMin.equalsIgnoreCase( acc )) {
    		cmd_getRsFecMin(request, response, loc);
    	} else if (sndMail_forgotPass.equalsIgnoreCase( acc )) {
    		cmd_sndMail_forgotPass(request, response, usr);
    	} else if (getPaymentMethods.equalsIgnoreCase( acc )) {
    		cmd_getPaymentMethods(request, response, usr, key, dat);
    	} else {
    		responder(request, response, false, "Servicio no contemplado " + acc );
    	}
    	///////////////////////////
    }
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
	private Map<String, String> getHeadersInfo(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}

		return map;
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	///////////////////////////

	private void uploadFile(HttpServletRequest request, HttpServletResponse response) {
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			 @ SuppressWarnings("unchecked")
			java.util.List < FileItem > items = upload.parseRequest(request);
	
			String user_id = null;
			Iterator<FileItem> iter = null;
	
			//////////////////////////
			// Primero busco el nombre de archivo destino:
			iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = iter.next();
				if (item.isFormField()) {
					if ("user_id".equalsIgnoreCase(item.getFieldName())) {
						user_id = item.getString();
					}
				}
			}
			//////////////////////////
	
			if ( user_id != null ) {
				//////////////////////////
				// Ahora busco el "archivo" en si mismo:
				iter = items.iterator();
				while (iter.hasNext()) {
					FileItem item = iter.next();
					if (!item.isFormField()) {
						////////////////////
						byte[] buffer = new byte[ (int) item.getSize() ];
						buffer = item.get();
						String b64 = new String( Base64.encodeBase64(buffer) );
						
						BDConexion dataBase = new Subrutinas().getBDConexion(request);
						UsBean reg_us = Subrutinas.getUsFromId(dataBase, user_id);
						if ( reg_us != null && reg_us.getUs_sincro() != null && reg_us.getUs_sincro().trim().length() > 0 ) {
							reg_us.setUs_avatar( "data:image/png;base64," + b64 );
							new com.fvr.us_users.db.UsAccesoBaseDatos().us_chgObj(dataBase, reg_us);
						}

					}
				}
			}
			//////////////////////////
	
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
			
	private void cmd_admMnu(HttpServletRequest request, HttpServletResponse response, String usr, String key) throws IOException {
		if ( usr == null || usr.trim().length() < 1 ) { responder(request, response, false, "Error en parámetros"); return; }
		if ( key == null || key.trim().length() < 1 ) { responder(request, response, false, "Error en parámetros"); return; }

		BDConexion dataBase = new Subrutinas().getBDConexion(request);
		com.fvr.us_users.bean.UsBean reg_us = new com.fvr.us_users.bean.UsBean();
		if ( ! Subrutinas.isUsrHashCorrecto(dataBase, usr, key, reg_us) ) {
			responder(request, response, false, "Error de seguridad"); return;
		};
		if ( reg_us != null ) {
			responder(request, response, true, Subrutinas.getRoleMenu( reg_us.getUs_role_id() ));
		}
		responder(request, response, false, "Error al ejecutar: cmd_admMnu()");
	}

	private void cmd_rtvOcu(HttpServletRequest request, HttpServletResponse response, String location_id, String start_date, String start_time) throws IOException {
		if ( location_id == null || location_id.trim().length() < 1 ) { responder(request, response, false, "Error en parámetros"); return; }

		start_date = "null".equalsIgnoreCase(start_date)?null:start_date;
		start_time = "null".equalsIgnoreCase(start_time)?null:start_time;
		
		BDConexion dataBase = new Subrutinas().getBDConexion(request);

        String chart_min_startdate = Subrutinas.getDBValueFromKey(dataBase, location_id, _K.PA_CHART_min_startdate);
        String chart_max_startdate = Subrutinas.getDBValueFromKey(dataBase, location_id, _K.PA_CHART_max_startdate);

        // Devuelve el número de reservas pagadas de una location para OPCIONALMENTE una fecha y una hora

		com.fvr.ts_timeSlices.db.TsAccesoBaseDatos dao_ts = new com.fvr.ts_timeSlices.db.TsAccesoBaseDatos();
        com.fvr.ts_timeSlices.bean.TsBeanFiltro flt_ts = new com.fvr.ts_timeSlices.bean.TsBeanFiltro();
        com.fvr.ts_timeSlices.bean.TsBean[] rgs_ts = null;
        
        // TIENE TRUCO!!!
        com.fvr.ac_activityCockpits.db.AcAccesoBaseDatos dao_ac = new com.fvr.ac_activityCockpits.db.AcAccesoBaseDatos();
        com.fvr.ts_timeSlices.bean.TsBeanFiltro          flt_ac = new com.fvr.ts_timeSlices.bean.TsBeanFiltro();
        com.fvr.ts_timeSlices.bean.TsBean[]              rgs_ac = null;	
        
        com.fvr.tt_timeTableReference.db.TtAccesoBaseDatos dao_tt = new com.fvr.tt_timeTableReference.db.TtAccesoBaseDatos();
		com.fvr.tt_timeTableReference.bean.TtBeanFiltro flt_tt = new com.fvr.tt_timeTableReference.bean.TtBeanFiltro();
        com.fvr.tt_timeTableReference.bean.TtBean[] rgs_tt = null;

        try {
	        flt_ts.setTs_RS_location_id(location_id);
	        flt_ts.setTs_start_date(start_date);
			flt_ts.setTs_start_time(start_time);
			rgs_ts = dao_ts.ts_getSeq_SumLocFecHor(dataBase, new ConfigPantalla(Integer.MAX_VALUE), flt_ts);

			flt_tt.setTt_location_id(location_id);
			flt_tt.setTt_day_type("NORMAL");
			rgs_tt = dao_tt.tt_getSeq(dataBase, new ConfigPantalla(Integer.MAX_VALUE), flt_tt );
			
	        flt_ac.setTs_RS_location_id(location_id);
	        flt_ac.setTs_start_date(start_date);
			flt_ac.setTs_start_time(start_time);
	        rgs_ac = dao_ac.ac_getSeq_SumLocFecHor(dataBase, new ConfigPantalla(Integer.MAX_VALUE), flt_ac, true);

		} catch (StExcepcion e) {
			responder(request, response, false, e.getMessage());
			return;
		}

        String actividad = dao_ts.beanArray2json(rgs_ac).toString();
        String ocupacion = dao_ts.beanArray2json(rgs_ts).toString();
        String horario = dao_tt.beanArray2json(rgs_tt).toString();
        JSONObject json = new JSONObject();
        json.put("actividad", actividad);
        json.put("ocupacion", ocupacion);
        json.put("horario", horario);
        json.put("chart_min_startdate", chart_min_startdate==null?_K.CHART_min_startdate_REF:chart_min_startdate);
        json.put("chart_max_startdate", chart_max_startdate==null?_K.CHART_max_startdate_REF:chart_max_startdate);

		responder(request, response, true, json.toString());
	}

	private void cmd_rtvUsData(HttpServletRequest request, HttpServletResponse response, String usr, String key) throws IOException {
		if ( usr == null || usr.trim().length() < 1 ) { responder(request, response, false, "Error en parámetros"); return; }
		if ( key == null || key.trim().length() < 1 ) { responder(request, response, false, "Error en parámetros"); return; }

		BDConexion dataBase = new Subrutinas().getBDConexion(request);
		com.fvr.us_users.bean.UsBean reg_us = new com.fvr.us_users.bean.UsBean();
		if ( ! Subrutinas.isUsrHashCorrecto(dataBase, usr, key, reg_us) ) {
			responder(request, response, false, "Error de seguridad"); return;
		};
		if ( reg_us != null && reg_us.getUs_sincro() != null && reg_us.getUs_sincro().trim().length() > 0 ) {
			UsBean respuesta = new UsBean();
			respuesta.setUs_phone(reg_us.getUs_phone());
			respuesta.setUs_nick(reg_us.getUs_nick());
			responder(request, response, true, respuesta.toString() ); return;
		}
		responder(request, response, false, "Error al ejecutar: cmd_rtvUsData()");
	}

	private void cmd_rtvTjData(HttpServletRequest request, HttpServletResponse response, String usr, String key, String card_id) throws IOException {
		if ( usr == null || usr.trim().length() < 1 ) { responder(request, response, false, "Error en parámetros"); return; }
		if ( key == null || key.trim().length() < 1 ) { responder(request, response, false, "Error en parámetros"); return; }
		if ( card_id == null || card_id.trim().length() < 1 ) { responder(request, response, false, "Error en parámetros"); return; }

		BDConexion dataBase = new Subrutinas().getBDConexion(request);
		com.fvr.us_users.bean.UsBean reg_us = new com.fvr.us_users.bean.UsBean();
		if ( ! Subrutinas.isUsrHashCorrecto(dataBase, usr, key, reg_us) ) {
			responder(request, response, false, "Error de seguridad"); return;
		};
		
		TjBean reg_tj = Subrutinas.getTjFromId(dataBase, card_id);
		
		if ( reg_tj != null && reg_tj.getTj_sincro() != null && reg_tj.getTj_sincro().trim().length() > 0 ) {
			responder(request, response, true, reg_tj.toString() );
		} else {
			responder(request, response, false, "No hallada la tarjeta: " + card_id );
		}
	}

	private void cmd_rtvNickData(HttpServletRequest request, HttpServletResponse response, String usr, String key, String nck) throws IOException {
		if ( usr == null || usr.trim().length() < 1 ) { responder(request, response, false, "Error en parámetros"); return; }
		if ( key == null || key.trim().length() < 1 ) { responder(request, response, false, "Error en parámetros"); return; }
		if ( nck == null || usr.trim().length() < 1 ) { responder(request, response, false, "Error en parámetros"); return; }

		BDConexion dataBase = new Subrutinas().getBDConexion(request);
		com.fvr.us_users.bean.UsBean reg_us = new com.fvr.us_users.bean.UsBean();
		if ( ! Subrutinas.isUsrHashCorrecto(dataBase, usr, key, reg_us) ) {
			responder(request, response, false, "Error de seguridad"); return;
		};
		
		reg_us = Subrutinas.getUsFromNick(dataBase, nck);
		
		if ( reg_us != null ) {
			
			reg_us.setUs_password("n/a");
			new Subrutinas().tratarImagenesEnRegUs(reg_us);
			
			responder(request, response, true, reg_us.toString() );
		} else {
			responder(request, response, false, null );
		}

	}

	private void cmd_tpv_callback(HttpServletRequest request, HttpServletResponse response, boolean isOk, String token_id) throws IOException {
		if ( token_id == null || token_id.trim().length() < 1 ) { responder(request, response, false, "Error en parámetros"); return; }

		BDConexion dataBase = new Subrutinas().getBDConexion(request);
		
		TkBean reg_tk = Subrutinas.getTkFromId(dataBase, token_id);
		if ( reg_tk != null && reg_tk.getTk_sincro() != null && reg_tk.getTk_sincro().trim().length() > 0 ) {
			JSONObject json = null;
			try { json = JSONObject.fromObject( reg_tk.getTk_json() ); } catch (Exception e) {;} 
			if ( json != null ) {
				
				String acc = null;
				try { acc = json.getString("acc"); } catch (Exception e) {;}
				if ( acc != null && acc.trim().equalsIgnoreCase("TPV_PAGO_EVENTO_LaCaixa") ) {
					// PAGO DE INSCRIPCIÓN A EVENTO:
					cmd_tpv_callback_Evento(dataBase, request, response, isOk, reg_tk, json);
				} else {
					// PAGO DE RESERVA:
					cmd_tpv_callback_Reserva(dataBase, request, response, isOk, reg_tk, json);
				}

			}
		}
		responder(request, response, false, "No se procesó la petición tpv_callback para " + token_id );
	}
	private void cmd_tpv_callback_Reserva(BDConexion dataBase, HttpServletRequest request, HttpServletResponse response, boolean isOk, TkBean reg_tk, JSONObject json) throws IOException {
		String reservation_id = null;
		try { reservation_id = json.getString("reservation_id"); } catch (Exception e) {;}
		if ( reservation_id != null && reservation_id.trim().length() > 0 ) {
			RsBean reg_rs = Subrutinas.getRsFromId(dataBase, reservation_id);
			if ( reg_rs != null && reg_rs.getRs_sincro() != null && reg_rs.getRs_sincro().trim().length() > 0 ) {
				if ( isOk ) {
					reg_rs.setRs_pay_status( _K.PAY_STS_TPV_Completado );
				} else {
					reg_rs.setRs_pay_status( _K.PAY_STS_TPV_Fallido );
				}
				 try {
					new com.fvr.rs_reservations.db.RsAccesoBaseDatos().rs_chgObj(dataBase, reg_rs);

					if ( _K.PAY_STS_TPV_Completado.equalsIgnoreCase( reg_rs.getRs_pay_status() ) ) {
						List<String> errores = new ArrayList<String>();
						String url_base = Subrutinas.get_urlBase(request);
						SendMail.send_comprobanteReserva(dataBase, url_base, reg_rs.getRs_user_id(), reg_rs.getRs_reservation_id(), errores, true);
						Subrutinas.addLog(dataBase, _K.SYS, _K.EV_TPV_PAGO_OK, reservation_id, "api_doExpressCheckout()" );
					}

					///////////////////
					// Reentrada en el sistema:
					String link = Subrutinas.get_urlBase(request) + "/";
					String user_id = reg_tk.getTk_author();
					if ( user_id != null && user_id.trim().length() > 0 ) {
						UsBean reg_us = Subrutinas.getUsFromId(dataBase, user_id);
				       	request.getSession(true).setAttribute( "logon_USR", reg_us.getUs_user_id() );
				        request.getSession(true).setAttribute( "logon_HSH", reg_us.getUs_hash_code() );
						request.getSession(true).setAttribute( "roleKey", reg_us.getUs_role_id() );
						link +=  "Index_A.do#/RsDSPFIL/";
					}
					response.sendRedirect( link );	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//					request.getRequestDispatcher( "/CpDSPFIL_A.do" ).forward(request, response);
					///////////////////
					
					
					return;
				} catch (StExcepcion e) {
					responder(request, response, false, e.getMessage());
					return;
				}
			}
		}
	}
	private void cmd_tpv_callback_Evento(BDConexion dataBase, HttpServletRequest request, HttpServletResponse response, boolean isOk, TkBean reg_tk, JSONObject json) throws IOException {
		String event_id = null;
		String inscription_user_id = null;
		try { event_id = json.getString("event_id"); } catch (Exception e) {;}
		try { inscription_user_id = json.getString("inscription_user_id"); } catch (Exception e) {;}
		if ( event_id != null && event_id.trim().length() > 0
			  &&
			 inscription_user_id != null && inscription_user_id.trim().length() > 0 
				) {
			EsBean reg_es = new EsBean();
			reg_es.setEs_event_id(event_id);
			reg_es.setEs_inscription_user_id(inscription_user_id);
			reg_es = Subrutinas.getEsFromId(dataBase, reg_es);
			if ( reg_es != null && reg_es.getEs_sincro() != null && reg_es.getEs_sincro().trim().length() > 0 ) {
				try {
					if ( isOk ) {
							reg_es.setEs_pay_status( _K.PAY_STS_TPV_Completado );
							// Actualizar estado del pago en su registro....
							new com.fvr.es_eventSusbscriptions.db.EsAccesoBaseDatos().es_chgObj(dataBase, reg_es);
	
							List<String> errores = new ArrayList<String>();
							String url_base = Subrutinas.get_urlBase(request);
	//						SendMail.send_comprobanteReserva(dataBase, url_base, reg_es.getEs_inscription_user_id(), reg_es.getEs_event_id(), errores, true);
							Subrutinas.addLog(dataBase, _K.SYS, _K.EV_TPV_PAGO_EVENTO_OK, reg_es.getKey(), "api_doExpressCheckout()" );
	
					} else {
							reg_es.setEs_pay_status( _K.PAY_STS_TPV_Fallido );
							// Eliminar su registro....
							new com.fvr.es_eventSusbscriptions.db.EsAccesoBaseDatos().es_dltObj(dataBase, reg_es);
					}
				} catch (StExcepcion e) {
					responder(request, response, false, e.getMessage());
					return;
				}

				///////////////////
				// Reentrada en el sistema:
				String link = Subrutinas.get_urlBase(request) + "/";
				String user_id = reg_tk.getTk_author();
				if ( user_id != null && user_id.trim().length() > 0 ) {
					UsBean reg_us = Subrutinas.getUsFromId(dataBase, user_id);
				   	request.getSession(true).setAttribute( "logon_USR", reg_us.getUs_user_id() );
					request.getSession(true).setAttribute( "logon_HSH", reg_us.getUs_hash_code() );
					request.getSession(true).setAttribute( "roleKey", reg_us.getUs_role_id() );
					link +=  "Index_A.do#/RsDSPFIL/";
				}
				response.sendRedirect( link );	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
				//					request.getRequestDispatcher( "/CpDSPFIL_A.do" ).forward(request, response);
				///////////////////

			}
		}
	}

	private void cmd_lo_lst(HttpServletRequest request, HttpServletResponse response) throws IOException {

		BDConexion dataBase = new Subrutinas().getBDConexion(request);

		JSONArray jsonArray = new JSONArray();
		jsonArray.add( JSONObject.fromObject("{value:'',displayName:'...'}") );

		try {
			com.fvr.lo_location.db.LoAccesoBaseDatos dao = new com.fvr.lo_location.db.LoAccesoBaseDatos();
			com.fvr.lo_location.bean.LoBeanFiltro    flt = new com.fvr.lo_location.bean.LoBeanFiltro();
			com.fvr.lo_location.bean.LoBean[]        rgs = null;
			
			rgs = dao.lo_getSeq(dataBase, new ConfigPantalla( Integer.MAX_VALUE ), flt);

			if ( rgs != null ) {
				JSONObject json = null;
				for ( LoBean item : rgs ) {
					json = new JSONObject();
					json.put("value", item.getLo_location_id() );
					json.put("displayName", item.getLo_name() );
					jsonArray.add( json );
				}
			}
		} catch (StExcepcion e) {
			responder(request, response, false, e.getMessage());
			return;
		}

		responder(request, response, true, jsonArray.toString() );
		return;
	}

	private void cmd_tt_lst(HttpServletRequest request, HttpServletResponse response, String location_id, String day_type, String isBlocked) throws IOException {
		if ( location_id == null || location_id.trim().length() < 1 ) { responder(request, response, false, "Error en parámetros"); return; }
		if ( day_type == null || day_type.trim().length() < 1 ) { responder(request, response, false, "Error en parámetros"); return; }
		
		if ( isBlocked == null || isBlocked.trim().length() < 1 ) { isBlocked = ""; }

		BDConexion dataBase = new Subrutinas().getBDConexion(request);

		JSONArray jsonArray = new JSONArray();

		try {
			com.fvr.tt_timeTableReference.db.TtAccesoBaseDatos dao = new com.fvr.tt_timeTableReference.db.TtAccesoBaseDatos();
			com.fvr.tt_timeTableReference.bean.TtBeanFiltro    flt = new com.fvr.tt_timeTableReference.bean.TtBeanFiltro();
			com.fvr.tt_timeTableReference.bean.TtBean[]        rgs = null;
			
			flt.setTt_location_id( location_id );
			flt.setTt_day_type( day_type );
			flt.setTt_isBlocked( isBlocked );
			
			rgs = dao.tt_getSeq(dataBase, new ConfigPantalla( Integer.MAX_VALUE ), flt);

			jsonArray.add( JSONObject.fromObject("{value:'',displayName:'...'}") );

			if ( rgs != null ) {
				JSONObject json = null;
				for ( TtBean item : rgs ) {
					json = new JSONObject();
					json.put("value", item.getTt_start_time() );
					json.put("displayName", item.getTt_start_time().substring(0,2) + ":" + item.getTt_start_time().substring(2) );
					jsonArray.add( json );
				}
			}
		} catch (StExcepcion e) {
			responder(request, response, false, e.getMessage());
			return;
		}

//		System.out.println( "cmd_de_lst() " + jsonArray.toString() );

		responder(request, response, true, jsonArray.toString() );
		return;
	}

	private void cmd_cd_lst(HttpServletRequest request, HttpServletResponse response, String location_id) throws IOException {
		if ( location_id == null || location_id.trim().length() < 1 ) { responder(request, response, false, "Error en parámetros"); return; }

		List<String> lista = new ArrayList<String>();

		com.fvr.cd_LocationClosedDays.bean.CdBean[] rgs = Subrutinas.getCdFromLo(new Subrutinas().getBDConexion(request), location_id);

		if ( rgs != null ) {
			for ( CdBean item : rgs ) {
				lista.add( item.getCd_closed_day_aaaa_mm_dd() );
			}
		}

		responder(request, response, true, lista.toString() );
		return;
	}

	private void cmd_cp_lst(HttpServletRequest request, HttpServletResponse response, String location_id, String isBlocked) throws IOException {
		if ( location_id == null || location_id.trim().length() < 1 ) { responder(request, response, false, "Error en parámetros"); return; }
		
		if ( isBlocked == null || isBlocked.trim().length() < 1 ) { isBlocked = ""; }

		BDConexion dataBase = new Subrutinas().getBDConexion(request);

		List<String> lista = new ArrayList<String>();

		try {
			com.fvr.cp_cockpits.db.CpAccesoBaseDatos dao = new com.fvr.cp_cockpits.db.CpAccesoBaseDatos();
			com.fvr.cp_cockpits.bean.CpBeanFiltro    flt = new com.fvr.cp_cockpits.bean.CpBeanFiltro();
			com.fvr.cp_cockpits.bean.CpBean[]        rgs = null;
			
			flt.setCp_location_id( location_id );
			flt.setCp_isBlocked( isBlocked );
			
			rgs = dao.cp_getSeq(dataBase, new ConfigPantalla( Integer.MAX_VALUE ), flt);

			if ( rgs != null ) {
				int contador = 0;
				for ( CpBean item : rgs ) {
					lista.add( "" + (++contador) );
				}
			}
		} catch (StExcepcion e) {
			responder(request, response, false, e.getMessage());
			return;
		}

//		System.out.println( "cmd_de_lst() " + jsonArray.toString() );

		responder(request, response, true, lista.toString() );
		return;
	}

	private void cmd_ev_lst(HttpServletRequest request, HttpServletResponse response, String location_id) throws IOException {
//		if ( location_id == null || location_id.trim().length() < 1 ) { responder(request, response, false, "Error en parámetros"); return; }

		JSONArray jsonArray = new JSONArray();
		jsonArray.add( JSONObject.fromObject("{value:'',displayName:'...'}") );
		
		if ( location_id != null && location_id.trim().length() > 0 ) {
			try {
				BDConexion dataBase = new Subrutinas().getBDConexion(request);

				com.fvr.ev_events.db.EvAccesoBaseDatos dao = new com.fvr.ev_events.db.EvAccesoBaseDatos();
				com.fvr.ev_events.bean.EvBeanFiltro    flt = new com.fvr.ev_events.bean.EvBeanFiltro();
				com.fvr.ev_events.bean.EvBean[]        rgs = null;
				
				flt.setEv_location_id( location_id );
				
				rgs = dao.ev_getSeq(dataBase, new ConfigPantalla( Integer.MAX_VALUE ), flt);

				if ( rgs != null ) {
					JSONObject json = null;
					for ( EvBean item : rgs ) {
						json = new JSONObject();
						json.put("value", item.getEv_event_id() );
						json.put("displayName", item.getEv_name() + " ( " + item.getEv_amount() + " " + item.getEv_currency() + " )" );
						jsonArray.add( json );
					}
				}
			} catch (StExcepcion e) {
				responder(request, response, false, e.getMessage());
				return;
			}
		}

		responder(request, response, true, jsonArray.toString() );
		return;
	}

	private void cmd_pt_lst(HttpServletRequest request, HttpServletResponse response, String usr, String key) throws IOException {
		if ( usr == null || usr.trim().length() < 1 ) { responder(request, response, false, "Error en parámetros"); return; }
		if ( key == null || key.trim().length() < 1 ) { responder(request, response, false, "Error en parámetros"); return; }

		BDConexion dataBase = new Subrutinas().getBDConexion(request);
		com.fvr.us_users.bean.UsBean reg_us = new com.fvr.us_users.bean.UsBean();
		if ( ! Subrutinas.isUsrHashCorrecto(dataBase, usr, key, reg_us) ) {
			responder(request, response, false, "Error de seguridad"); return;
		};

		JSONArray jsonArray = new JSONArray();
		jsonArray.add( JSONObject.fromObject("{value:'',displayName:'...'}") );

		if ( reg_us != null ) {
			com.fvr.pt_products.db.PtAccesoBaseDatos dao_pt = new com.fvr.pt_products.db.PtAccesoBaseDatos();
			com.fvr.pt_products.bean.PtBeanFiltro    flt_pt = new com.fvr.pt_products.bean.PtBeanFiltro();
			com.fvr.pt_products.bean.PtBean[]        rgs_pt = null;

			flt_pt.setPt_whoCanSelect_AFU( reg_us.getUs_role_id() );
			
			try {
				rgs_pt = dao_pt.pt_getSeq(dataBase, new ConfigPantalla(Integer.MAX_VALUE), flt_pt);
//				if ( rgs_pt == null ) { rgs_pt = new com.fvr.pt_products.bean.PtBean[0]; }
//				responder(request, response, true, dao_pt.beanArray2json( rgs_pt ).toString() );
//				return;
				if ( rgs_pt != null ) {
					JSONObject json = null;
					for ( PtBean item : rgs_pt ) {
						json = new JSONObject();
						json.put("value", item.getPt_product_id() );
						json.put("displayName", item.getPt_amount() + " € : " + item.getPt_name() );
						jsonArray.add( json );
					}
				}
			} catch (StExcepcion e) {
				responder(request, response, false, e.getMessage());
				return;
			}
		}

		responder(request, response, true, jsonArray.toString() );
		return;
	}

	private void cmd_ps_lst(HttpServletRequest request, HttpServletResponse response, String usr, String key) throws IOException {
		if ( usr == null || usr.trim().length() < 1 ) { responder(request, response, false, "Error en parámetros"); return; }
		if ( key == null || key.trim().length() < 1 ) { responder(request, response, false, "Error en parámetros"); return; }

		BDConexion dataBase = new Subrutinas().getBDConexion(request);
		com.fvr.us_users.bean.UsBean reg_us = new com.fvr.us_users.bean.UsBean();
		if ( ! Subrutinas.isUsrHashCorrecto(dataBase, usr, key, reg_us) ) {
			responder(request, response, false, "Error de seguridad"); return;
		};

		JSONArray jsonArray = new JSONArray();
		jsonArray.add( JSONObject.fromObject("{value:'',displayName:'...'}") );

		if ( reg_us != null ) {
			com.fvr.ps_countries.db.PsAccesoBaseDatos dao_ps = new com.fvr.ps_countries.db.PsAccesoBaseDatos();
			com.fvr.ps_countries.bean.PsBeanFiltro    flt_ps = new com.fvr.ps_countries.bean.PsBeanFiltro();
			com.fvr.ps_countries.bean.PsBean[]        rgs_ps = null;

			boolean isCargarBanderas = (null != request.getParameter("FLAG") || null != request.getParameter("amp;FLAG"))?true:false;

			try {
				rgs_ps = dao_ps.ps_getSeq(dataBase, new ConfigPantalla(Integer.MAX_VALUE), flt_ps);
				if ( rgs_ps != null ) {
					JSONObject json = null;
					for ( PsBean item : rgs_ps ) {
						json = new JSONObject();
						json.put("value", item.getPs_country_id() );
						json.put("displayName", item.getPs_name() );
						// Se quiere en BINARIO?
						if ( isCargarBanderas ) {
							json.put("flag", item.getPs_flag_base64() );
						}
						jsonArray.add( json );
					}
				}
			} catch (StExcepcion e) {
				responder(request, response, false, e.getMessage());
				return;
			}
		}

		responder(request, response, true, jsonArray.toString() );
		return;
	}

	private void cmd_getUsrImg(HttpServletRequest request, HttpServletResponse response, String usr) throws IOException {
		boolean resultado = true;

		if ( usr == null || usr.trim().length() < 1 ) { responder(request, response, false, "Error en parámetros"); return; }

		BDConexion dataBase = new Subrutinas().getBDConexion(request);
		com.fvr.us_users.bean.UsBean reg_us = Subrutinas.getUsFromId(dataBase, usr);

		///////////////////////////////////
		String dato = _K.AVATAR_IMG_UNKNOWN;
		if ( reg_us != null && reg_us.getUs_sincro() != null && reg_us.getUs_sincro().trim().length() > 0 ) {
			dato = reg_us.getUs_avatar();
//		} else {
//			return;
		}

		///////////////////////////////////
		// Se quiere en BINARIO?
		if ( null != request.getParameter("BIN") || null != request.getParameter("amp;BIN") ) {

			BufferedImage bi = null;
			// El getRcd() puede devolver:
			// 		CASO 1	La cadena "data:image/jpg;base64,......datos en base64......"
			//      CASO 2	La cadena _K.AVATAR_IMG_UNKNOWN ("./resBS/img/unknown_120.png") --> Nombre del fichero de 'no foto' si la había en la bd.

			int idx = dato.toLowerCase().indexOf("base64,");
			try {
				if ( idx > -1 ) {
					// CASO 1
					dato = dato.substring( idx + 7 );
					bi = com.fvr._comun.img2D.util.ImageUtils.decodeToImage( dato );
				} else {
					// CASO 2
					bi = ImageIO.read( new File( this.getServletContext().getRealPath( dato ) ) );
				}
			} catch (Exception e) {System.err.println(this.getClass().getSimpleName() + ".cmd_getUsrImg('"+dato+"') : " + e.getMessage() );}

			if ( bi != null ) {
				
				/////////////
				// Adaptar el tamaño para el peso de la transmisión:
				bi = ImageUtils.resize(bi,32,32);
				/////////////

				if ( bi != null ) {
					response.setContentType("image/png");
					ImageIO.write( bi, "png", response.getOutputStream() );
				}
				return;
			}
		}
		///////////////////////////////////

		responder( request, response, resultado, dato );		
	}

	private void cmd_getFlgImg(HttpServletRequest request, HttpServletResponse response, String kps) throws IOException {
		boolean resultado = true;

		if ( kps == null || kps.trim().length() < 1 ) { responder(request, response, false, "Error en parámetros"); return; }
		int country_id = Subrutinas.parse_integer(kps);
//		if ( country_id == 0 ) { responder(request, response, false, "Error en parámetros"); return; }
		
		
		BDConexion dataBase = new Subrutinas().getBDConexion(request);
		PsBean reg_ps = Subrutinas.getPsFromId(dataBase, country_id);

		///////////////////////////////////
		String dato = _K.AVATAR_FLAG_UNKNOWN;
		if ( reg_ps != null && reg_ps.getPs_sincro() != null && reg_ps.getPs_sincro().trim().length() > 0 ) {
			dato = reg_ps.getPs_flag_base64();
//		} else {
//			return;
		}

		///////////////////////////////////
		// En BINARIO
		BufferedImage bi = null;
		// El getRcd() puede devolver:
		// 		CASO 1	La cadena "data:image/jpg;base64,......datos en base64......"
		//      CASO 2	La cadena _K.AVATAR_IMG_UNKNOWN ("./resBS/img/unknown_120.png") --> Nombre del fichero de 'no foto' si la había en la bd.
		int idx = dato.toLowerCase().indexOf("base64,");
		try {
			if ( idx > -1 ) {
				// CASO 1
				dato = dato.substring( idx + 7 );
				bi = com.fvr._comun.img2D.util.ImageUtils.decodeToImage( dato );
			} else {
				// CASO 2
				bi = ImageIO.read( new File( this.getServletContext().getRealPath( dato ) ) );
			}
		} catch (Exception e) {System.err.println(this.getClass().getSimpleName() + ".cmd_getFlgImg('"+dato+"') : " + e.getMessage() );}

		if ( bi != null ) {
			response.setContentType("image/png");
			ImageIO.write( bi, "png", response.getOutputStream() );
			return;
		}
		///////////////////////////////////

		responder( request, response, resultado, dato );		
	}

	private void cmd_gamingModule(HttpServletRequest request, HttpServletResponse response, String usr, String key) throws IOException {
		if ( usr == null || usr.trim().length() < 1 ) { responder(request, response, false, "Error en parámetros"); return; }
		if ( key == null || key.trim().length() < 1 ) { responder(request, response, false, "Error en parámetros"); return; }

		BDConexion dataBase = new Subrutinas().getBDConexion(request);
		com.fvr.us_users.bean.UsBean reg_us = new com.fvr.us_users.bean.UsBean();
		if ( ! Subrutinas.isUsrHashCorrecto(dataBase, usr, key, reg_us) ) {
			responder(request, response, false, "Error de seguridad"); return;
		};
		if ( reg_us != null && reg_us.getUs_sincro() != null && reg_us.getUs_sincro().trim().length() > 0 ) {
			reg_us.setUs_password("n/a");
			
			String url_redirect = ""
					+ Subrutinas.getDBValueFromKey(dataBase, _K.PA_MG_URL)
//					+ "https://driver.formulavr.net/"
					;

			new Subrutinas().tratarImagenesEnRegUs(reg_us);
			
			JSONObject json = new JSONObject();
			json.put("url_redirect", url_redirect);
			json.put("user_id", reg_us.getUs_user_id());
			json.put("hash_code", reg_us.getUs_hash_code());
			json.put("data", reg_us);
			
			responder(request, response, true, json.toString() ); return;
		}
		responder(request, response, false, "Error al ejecutar: cmd_rtvUsData()");
	}

	private void cmd_rmtLogon(HttpServletRequest request, HttpServletResponse response, String usr, String pwd) throws IOException {
		if ( usr == null || usr.trim().length() < 1 ) { responder(request, response, false, "Error en parámetros"); return; }
		if ( pwd == null || pwd.trim().length() < 1 ) { responder(request, response, false, "Error en parámetros"); return; }

		BDConexion dataBase = new Subrutinas().getBDConexion(request);

		if ( ! _K.PWD_EN_BLANCO.equalsIgnoreCase( pwd ) ) {
			
			usr = URLDecoder.decode(usr, "UTF-8");
			
			UsBean reg_us = Subrutinas.getUsFromId(dataBase, usr);
			if ( reg_us != null && reg_us.getUs_sincro() != null && reg_us.getUs_sincro().trim().length() > 0 ) {
				if ( pwd.equalsIgnoreCase( reg_us.getUs_password() ) ) {
					
					reg_us.setUs_hash_code( Subrutinas.setUsr_newHash(dataBase, usr) );
					
					responder(request, response, true, reg_us.getUs_hash_code() );
					return;
				}
			}
		}

		responder(request, response, false, "KO");
		
	}

	private void cmd_isUsrExists(HttpServletRequest request, HttpServletResponse response, String usr) throws IOException {
		if ( usr == null || usr.trim().length() < 1 ) { responder(request, response, false, "Error en parámetros"); return; }

		try { usr = URLDecoder.decode(usr, "UTF-8"); } catch (Exception e) {;}

		BDConexion dataBase = new Subrutinas().getBDConexion(request);

		UsBean reg_us = Subrutinas.getUsFromId(dataBase, usr);
		if ( reg_us != null && reg_us.getUs_sincro() != null && reg_us.getUs_sincro().trim().length() > 0 ) {
			responder(request, response, true, "true" );
		} else {
			responder(request, response, true, "false" );
		}
	}

	private void cmd_rsAdd(HttpServletRequest request, HttpServletResponse response, String usr, String key) throws IOException {
		if ( usr == null || usr.trim().length() < 1 ) { responder(request, response, false, "Error en parámetros"); return; }
		if ( key == null || key.trim().length() < 1 ) { responder(request, response, false, "Error en parámetros"); return; }

		BDConexion dataBase = new Subrutinas().getBDConexion(request);
		com.fvr.us_users.bean.UsBean reg_us = new com.fvr.us_users.bean.UsBean();
		if ( ! Subrutinas.isUsrHashCorrecto(dataBase, usr, key, reg_us) ) {
			responder(request, response, false, "Error de seguridad"); return;
		};

		///////////////////
		// Reentrada en el sistema:
		String link = Subrutinas.get_urlBase(request);

       	request.getSession(true).setAttribute( "logon_USR", reg_us.getUs_user_id() );
        request.getSession(true).setAttribute( "logon_HSH", reg_us.getUs_hash_code() );
		request.getSession(true).setAttribute( "roleKey", reg_us.getUs_role_id() );

		link +=  "/Index_A.do#/RsDSPFIL/panel_add";

		response.sendRedirect( link );	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

//		request.getRequestDispatcher( "/CpDSPFIL_A.do" ).forward(request, response);
		///////////////////
	
	
	
	
	}

	private void cmd_usEdt(HttpServletRequest request, HttpServletResponse response, String usr, String key) throws IOException {
		if ( usr == null || usr.trim().length() < 1 ) { responder(request, response, false, "Error en parámetros"); return; }
		if ( key == null || key.trim().length() < 1 ) { responder(request, response, false, "Error en parámetros"); return; }

		BDConexion dataBase = new Subrutinas().getBDConexion(request);
		com.fvr.us_users.bean.UsBean reg_us = new com.fvr.us_users.bean.UsBean();
		if ( ! Subrutinas.isUsrHashCorrecto(dataBase, usr, key, reg_us) ) {
			responder(request, response, false, "Error de seguridad"); return;
		};

		///////////////////
		// Reentrada en el sistema:
		String link = Subrutinas.get_urlBase(request);

       	request.getSession(true).setAttribute( "logon_USR", reg_us.getUs_user_id() );
        request.getSession(true).setAttribute( "logon_HSH", reg_us.getUs_hash_code() );
		request.getSession(true).setAttribute( "roleKey", reg_us.getUs_role_id() );

		link +=  "/Index_A.do#/rsUsEDTRCD";

		response.sendRedirect( link );	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

//		request.getRequestDispatcher( "/CpDSPFIL_A.do" ).forward(request, response);
		///////////////////
	}

	private void cmd_usAdd(HttpServletRequest request, HttpServletResponse response, String usr) throws IOException {
		if ( usr == null || usr.trim().length() < 1 ) { responder(request, response, false, "Error en parámetros"); return; }

		BDConexion dataBase = new Subrutinas().getBDConexion(request);
		com.fvr.us_users.bean.UsBean reg_us = new com.fvr.us_users.bean.UsBean();
		
		reg_us = Subrutinas.getUsFromId(dataBase, usr);
		
		if ( reg_us != null && reg_us.getUs_sincro() != null && reg_us.getUs_sincro().trim().length() > 0 ) {
			responder(request, response, false, "User exists!!"); return;
		};

        /////////////////////////////////////////////////////////////////////
        // Improvisar el usuario:
        if ( ! Subrutinas.improvisarUsuario(dataBase, usr, null) ) {
			responder(request, response, false, "Error trying user creation"); return;
		}
        /////////////////////////////////////////////////////////////////////
        
        String laClave = Subrutinas.getHashFromRandomCode();
		Subrutinas.setUsr_newHash(dataBase, usr, laClave);


		//////////////////
		// Correo para que meta su contraseña:
		List<String> lstErrores = new ArrayList<String>();
		String htmlDoc = SendMail.send_CambiarPassword(dataBase, Subrutinas.get_urlBase(request), usr.trim(), lstErrores, true);
		if ( lstErrores.isEmpty() ) {
			Subrutinas.addLog(dataBase, _K.SYS, usr, "Enviado correo para cambio password.", htmlDoc);
//            errores.add("error", new ActionMessage( "errors.detail", "Por favor consulta tu correo para continuar el proceso de asignación de una nueva contraseña." ));
		} else {
			Subrutinas.addLog(dataBase, _K.SYS, usr, "ERROR en envío correo para cambio de contraseña.", lstErrores.get(0).toString() );
//            errores.add("error", new ActionMessage( "errors.detail", "Ha fallado la operación..." ));
		}
		//////////////////


		///////////////////
		// Reentrada en el sistema:
		String link = Subrutinas.get_urlBase(request);

       	request.getSession(true).setAttribute( "logon_USR", reg_us.getUs_user_id() );
        request.getSession(true).setAttribute( "logon_HSH", laClave );
		request.getSession(true).setAttribute( "roleKey", reg_us.getUs_role_id() );

		link +=  "/Index_A.do#/rsUsEDTRCD";
		link += "?CHGPWD_MAIL_SENDED=true";

		response.sendRedirect( link );	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

//		request.getRequestDispatcher( "/CpDSPFIL_A.do" ).forward(request, response);
		///////////////////
	}

	private void cmd_FVRMonitor(HttpServletRequest request, HttpServletResponse response, JSONObject payload) throws IOException {

		// Para arrancar el FVRMonitor, que es el cliente que llama aquÍ:
		// java -jar FVRMonitor.jar c:\datos\new2.html 5 http://localhost:8080/FormulaVR/FvrServlet?ACC=FVRMonitor
		
		// DEBE EJECUTARSE A TODA LECHE!!
		if ( payload == null || payload.toString().trim().length() < 3 ) { responder(request, response, false, "Error en parámetros"); return; }

		// Para arrancar el FVRMonitor, que es el cliente que llama aquÍ:
		// java -jar FVRMonitor.jar c:\datos\new2.html 5 http://localhost:8080/FormulaVR/FvrServlet?ACC=FVRMonitor

		try {
			BDConexion dataBase = new Subrutinas().getBDConexion(request);
			com.fvr.ac_activityCockpits.db.AcAccesoBaseDatos dao_ac = new com.fvr.ac_activityCockpits.db.AcAccesoBaseDatos();
			com.fvr.ac_activityCockpits.bean.AcBean          reg_ac = new com.fvr.ac_activityCockpits.bean.AcBean();
			
			// La pk es un serial:
			reg_ac.setAc_author(this.getClass().getSimpleName());
			try { reg_ac.setAc_computername( payload.getString("client") ); } catch (Exception e) {;}
			try { reg_ac.setAc_filename( payload.getString("filename") ); } catch (Exception e) {;}
			try { reg_ac.setAc_content( payload.getString("content") ); } catch (Exception e) {;}
			
			if ( reg_ac.getAc_computername() != null && reg_ac.getAc_computername().trim().length() > 0 ) {
				try {
					com.fvr.cp_cockpits.db.CpAccesoBaseDatos dao_cp = new com.fvr.cp_cockpits.db.CpAccesoBaseDatos();
					com.fvr.cp_cockpits.bean.CpBeanFiltro    flt_cp = new com.fvr.cp_cockpits.bean.CpBeanFiltro();
					com.fvr.cp_cockpits.bean.CpBean[]        rgs_cp = null;

					flt_cp.setCp_name( reg_ac.getAc_computername().trim() );

					rgs_cp = dao_cp.cp_getSeq(dataBase, new ConfigPantalla(1), flt_cp);

					if ( rgs_cp != null && rgs_cp.length > 0 ) {
						reg_ac.setAc_location_id( rgs_cp[0].getCp_location_id() );
					}
				} catch (Exception e) { ; }
			}

			dao_ac.ac_crtObj(dataBase, reg_ac);

		} catch (StExcepcion e) {
			responder(request, response, false, e.getMessage());
			return;
		}

		responder(request, response, true, "cmd_FVRMonitor OK");

	}

	private void cmd_getRsFecMin(HttpServletRequest request, HttpServletResponse response, String loc) throws IOException {
		if ( loc == null || loc.trim().length() < 1 ) { responder(request, response, false, "Error en parámetros"); return; }

		Date hoy = Subrutinas.cvtFec_aaaa_mm_dd__date( Subrutinas.getFecha_aaaa_mm_dd() ); 
		Date minReservar = hoy; 
		String rsFecMin = null;
		BDConexion dataBase = new Subrutinas().getBDConexion(request);

//		LoBean reg_lo = Subrutinas.getLoFromId(dataBase, loc);
//		if ( reg_lo != null && reg_lo.getLo_sincro() != null && reg_lo.getLo_sincro().trim().length() > 0 ) {
			rsFecMin = Subrutinas.getDBValueFromKey(dataBase, loc, _K.PA_KEY_RS_MIN_FEC);
//		}
		
		if ( rsFecMin != null && rsFecMin.trim().length() == 10 ) {
			minReservar = Subrutinas.cvtFec_aaaa_mm_dd__date( rsFecMin );
		}

		if ( minReservar.after( hoy ) ) {
			responder(request, response, true, Subrutinas.getFecha_aaaa_mm_dd( minReservar ) );
		} else {
			responder(request, response, true, Subrutinas.getFecha_aaaa_mm_dd( hoy ) );
		}

	}

	private void cmd_sndMail_forgotPass(HttpServletRequest request, HttpServletResponse response, String usr) throws IOException {
		if ( usr == null || usr.trim().length() < 1 ) { responder(request, response, false, "Error en parámetros"); return; }

		BDConexion dataBase = new Subrutinas().getBDConexion(request);
		com.fvr.us_users.bean.UsBean reg_us = Subrutinas.getUsFromId(dataBase, usr);
		
		if ( reg_us != null && reg_us.getUs_sincro() != null && reg_us.getUs_sincro().trim().length() > 0 ) {

			//////////////////
			List<String> lstErrores = new ArrayList<String>();
			String htmlDoc = SendMail.send_CambiarPassword(dataBase, Subrutinas.get_urlBase(request), reg_us.getUs_user_id(), lstErrores, true);
			if ( lstErrores.isEmpty() ) {
				Subrutinas.addLog(dataBase, _K.SYS, reg_us.getUs_user_id(), "Enviado correo para cambio password.", htmlDoc);
				responder(request, response, true, usr + ": Consulta tu correo para continuar el proceso.");
				return;
			} else {
				Subrutinas.addLog(dataBase, _K.SYS, reg_us.getUs_user_id(), "ERROR en envío correo para cambio de contraseña.", lstErrores.get(0).toString() );
				responder(request, response, false, usr + ": " + lstErrores.get(0).toString());
				return;
			}
			//////////////////

		}

		responder(request, response, false, "No existe: " + usr);
		
	}

	private void cmd_getPaymentMethods(HttpServletRequest request, HttpServletResponse response, String usr, String key, String location_id) throws IOException {
		if ( usr == null || usr.trim().length() < 1 ) { responder(request, response, false, "Error en parámetros"); return; }
		if ( key == null || key.trim().length() < 1 ) { responder(request, response, false, "Error en parámetros"); return; }
		if ( location_id == null || location_id.trim().length() < 1 ) { responder(request, response, false, "Error en parámetros"); return; }

		BDConexion dataBase = new Subrutinas().getBDConexion(request);
		com.fvr.us_users.bean.UsBean reg_us = new com.fvr.us_users.bean.UsBean();
		if ( ! Subrutinas.isUsrHashCorrecto(dataBase, usr, key, reg_us) ) {
			responder(request, response, false, "Error de seguridad"); return;
		};
		
		// Averiguar si tiene credenciales para los modos de pago del location_id pedido:
		String resultado = "";
		
		// Paypal:
		APICredentials vendedor = new PaypalCredentials(dataBase, location_id);
		if (   vendedor != null 
			&& vendedor.getUser() != null && vendedor.getUser().trim().length() > 0 
			&& vendedor.getPassword() != null && vendedor.getPassword().trim().length() > 0
			&& vendedor.getSignature() != null && vendedor.getSignature().trim().length() > 0 
			) { 
				resultado += " PAYPAL";
			}
		
		// TPV virtual de "La Caixa":
		TPV_API tpv = new TPV_API(dataBase); 
		FormStruct out_formData = tpv.new FormStruct();
		List<String> lstErrores = new ArrayList<String>();
		String link_redireccion = tpv.prepareFormData( Subrutinas.get_urlBase(request), out_formData, "order", 12.34, "token_id", location_id, lstErrores );
		if ( link_redireccion != null && link_redireccion.trim().length() > 0 ) {
			resultado += " TPV";
		}

		responder(request, response, true, resultado.trim() );
	}

	///////////////////////////

}
