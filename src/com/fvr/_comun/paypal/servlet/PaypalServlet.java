package com.fvr._comun.paypal.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fvr.FuentesDeDatos.BDConexion;
import com.fvr._comun.Subrutinas;
import com.fvr._comun.paypal.Paypal_API;
import com.fvr.us_users.bean.UsBean;

/**
 * Servlet implementation class PaypalServlet
 */
public class PaypalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PaypalServlet() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Determinar el tipo de llamada recibido:
		//	1 . setExpressCheckout callback ACK=Success
		//	2 . setExpressCheckout callback ACK=Failure
		//	3 . IPN endpoint para "recurring payments" (Instant Paypal notification)
		
		// user-agent=PayPal IPN ( https://www.paypal.com/ipn )
		
		boolean isMsgIPN = false;
		String user_agent = request.getHeader("user-agent");
		if (  user_agent != null && user_agent.contains("PayPal IPN") ) {
			isMsgIPN = true;
		};

//		Map<String,String> headers = new HashMap<String, String>();
//		String payload = Subrutinas.getRequestPayload_txt(request);
//		if ( payload != null ) {
//			if ( payload.toLowerCase().contains("&ipn_track_id=") ) {
//				isMsgIPN = true;
//				
//				for (Enumeration<String> e = request.getHeaderNames(); e.hasMoreElements();) {
//				    String name = (String) e.nextElement();
//				    String value = request.getHeader( name );
//				    headers.put( name, value );
//				 }
//				
//			}
//		}

		if ( isMsgIPN ) {
			Paypal_API.onCallBack_IPN(request, response);
		} else {
			
	        ArrayList<String> errores = new ArrayList<String>();

			Paypal_API.onCallBack_setExpressCheckout(request, response, errores);
			try {
			        if ( ! errores.isEmpty() ) {
			        	for( String item : errores ) {
			        		System.err.println( item );
			        	}
			        }
					String link = Subrutinas.get_urlBase(request) + "/";
					
					BDConexion dataBase = new Subrutinas().getBDConexion(request);
					String user_id = request.getParameter("USR");
					if ( user_id != null && user_id.trim().length() > 0 ) {
						UsBean reg_us = Subrutinas.getUsFromId(dataBase, user_id);
				       	request.getSession(true).setAttribute( "logon_USR", reg_us.getUs_user_id() );
				        request.getSession(true).setAttribute( "logon_HSH", reg_us.getUs_hash_code() );
						request.getSession(true).setAttribute( "roleKey", reg_us.getUs_role_id() );
						link +=  "Index_A.do#/RsDSPFIL/";
					}

					response.sendRedirect( link );	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//					request.getRequestDispatcher( "/CpDSPFIL_A.do" ).forward(request, response);
		
				} catch (IOException e) {
					e.printStackTrace();
				}	
		}
		
//		String ack = request.getParameter("ACK");
//		String token = request.getParameter("token");
//		String payerId = request.getParameter("PayerID");
//		String kDocument = request.getParameter("KDOC");
//		
//		
//		response.getWriter().append(" ACK: ").append(ack);
//		response.getWriter().append(" TOKEN: ").append(token);
//		response.getWriter().append(" PAYERID: ").append(payerId);
//		response.getWriter().append(" KDOC: ").append(kDocument);
//		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
