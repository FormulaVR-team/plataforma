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
import com.fvr._comun.img2D.util.ImageUtils;
import com.fvr._comun.mail.SendMail;
import com.fvr.cp_cockpits.bean.CpBean;
import com.fvr.lo_location.bean.LoBean;
import com.fvr.ps_countries.bean.PsBean;
import com.fvr.pt_products.bean.PtBean;
import com.fvr.rs_reservations.bean.RsBean;
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
    private static final String rtvNickData = "rtvNickData";  // Retorna datos basicos del usuario
    // http://localhost:8080/FormulaVR/FvrServlet?ACC=rtvNickData&USR=eestecha@gmail.com&KEY=2BE9D59820EE1699D54113D60FEDDC90C67D1215&NCK=Chuki
    private static final String lo_lst = "lo_lst";  // Retorna lista de Departamentos
    // http://localhost:8080/FormulaVR/FvrServlet?ACC=lo_lst
    private static final String cp_lst = "cp_lst";  // Retorna lista de cockpits de la instalación (opcionalmente también los bloqueados)
    // http://localhost:8080/FormulaVR/FvrServlet?ACC=cp_lst&LOC=CENTRAL [&BLK=S]
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
    private static final String rsAdd = "rsAdd";  // Llamada externa para "Crear nueva Reserva"
    // http://localhost:8080/FormulaVR/FvrServlet?ACC=rsAdd&USR=eestecha@gmail.com&KEY=2BE9D59820EE1699D54113D60FEDDC90C67D1215
    private static final String usEdt = "usEdt";  // Llamada externa para "Editar mis datos de usuario"
    // http://localhost:8080/FormulaVR/FvrServlet?ACC=usEdt&USR=eestecha@gmail.com&KEY=2BE9D59820EE1699D54113D60FEDDC90C67D1215

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
    	dat = dat==null?request.getParameter("DAT"):dat;   // Date
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
    	} else if (rtvNickData.equalsIgnoreCase( acc )) {
    		cmd_rtvNickData(request, response, usr, key, nck);
    	} else if (cp_lst.equalsIgnoreCase( acc )) {
    		cmd_cp_lst(request, response, loc, blk);
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
    	} else if (rsAdd.equalsIgnoreCase( acc )) {
    		cmd_rsAdd(request, response, usr, key);
    	} else if (usEdt.equalsIgnoreCase( acc )) {
    		cmd_usEdt(request, response, usr, key);
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

		// Devuelve el número de reservas pagadas de una location para OPCIONALMENTE una fecha y una hora

		com.fvr.ts_timeSlices.db.TsAccesoBaseDatos dao_ts = new com.fvr.ts_timeSlices.db.TsAccesoBaseDatos();
        com.fvr.ts_timeSlices.bean.TsBeanFiltro flt_ts = new com.fvr.ts_timeSlices.bean.TsBeanFiltro();
        com.fvr.ts_timeSlices.bean.TsBean[] rgs_ts = null;
        
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

		} catch (StExcepcion e) {
			responder(request, response, false, e.getMessage());
			return;
		}

        String ocupacion = dao_ts.beanArray2json(rgs_ts).toString();
        String horario = dao_tt.beanArray2json(rgs_tt).toString();
        JSONObject json = new JSONObject();
        json.put("ocupacion", ocupacion);
        json.put("horario", horario);

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
				String reservation_id = null;
				try { reservation_id = json.getString("reservation_id"); } catch (Exception e) {;}
				if ( reservation_id != null && reservation_id.trim().length() > 0 ) {
					RsBean reg_rs = Subrutinas.getRsFromId(dataBase, reservation_id);
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
//						request.getRequestDispatcher( "/CpDSPFIL_A.do" ).forward(request, response);
						///////////////////
						
						
						return;
					} catch (StExcepcion e) {
						responder(request, response, false, e.getMessage());
						return;
					}
				}
			}
		}
		responder(request, response, false, "No se procesó la petición tpv_callback para " + token_id );
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

	///////////////////////////

}
