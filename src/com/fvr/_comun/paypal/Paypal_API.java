package com.fvr._comun.paypal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fvr.FuentesDeDatos.BDConexion;
import com.fvr._comun.ConfigPantalla;
import com.fvr._comun.StExcepcion;
import com.fvr._comun.Subrutinas;
import com.fvr._comun._K;
import com.fvr._comun.mail.SendMail;
import com.fvr._comun.paypal.bean.GetExpressCheckoutResponse;
import com.fvr._comun.paypal.bean.PaypalFailure;
import com.fvr._comun.paypal.bean.PaypalResponse;
import com.fvr._comun.paypal.bean.RecurringPaymentData;
import com.fvr._comun.paypal.classes.APICredentials;
import com.fvr._comun.paypal.classes.PaypalAPI;
import com.fvr._comun.paypal.classes.PaypalAPIFactory;
import com.fvr._comun.paypal.classes.PaypalCredentials;
import com.fvr._comun.paypal.classes.PaypalMethods;
import com.fvr.py_PayPalTokens.bean.PyBean;
import com.fvr.py_PayPalTokens.db.PyAccesoBaseDatos;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Paypal_API {

//	Paypal Sandbox PAGAR: 	emilio.estecha@gmail.com / Passw0rd

	private String m_callBack = "http://219582dd.ngrok.io/Billin/Paypal";

	public Paypal_API( BDConexion dataBase, String callBack_o_null ) {
		
		if ( callBack_o_null != null ) {
			this.m_callBack = callBack_o_null;
		} else {
			this.m_callBack = Subrutinas.getDBValueFromKey(dataBase, _K.CUENTA_DE_SISTEMA, _K.PA_KEY_PAYPAL_CHECKOUT_EXPRESS_CALLBACK_URL );
		}
		
	}
	///////////////////////
	public String         onPagarPorPaypal( HttpServletRequest request, HttpServletResponse response, BDConexion dataBase, List<String> errores, String nombreFiscalProveedor, String user_id, String reservation_id, String amount, String RecurringPaymentDescription_o_null, boolean isRedireccionar ) {
		String link_redireccion = null;
		////////////////
		String tokenProcesado = api_isProcesadoUsrRsva(dataBase, user_id, reservation_id);
		if ( tokenProcesado != null && tokenProcesado.trim().length() > 0 ) {
			return link_redireccion;
		} else {
			api_dltTokensPdtesUsrRsva(dataBase, user_id, reservation_id);
		}
		////////////////
		boolean isPagarSinCuentaClienteObligada = true;

		PaypalResponse respuesta = api_setExpressCheckout(dataBase, nombreFiscalProveedor, user_id, reservation_id, amount, _K.CUENTA_DE_SISTEMA, true, isPagarSinCuentaClienteObligada, RecurringPaymentDescription_o_null);

		if ( respuesta != null  ) {
			
			if ( "Success".equalsIgnoreCase( respuesta.getAcknowledge() ) ) {

				link_redireccion = Subrutinas.getDBValueFromKey( dataBase, _K.CUENTA_DE_SISTEMA, _K.PA_KEY_PAYPAL_CHECKOUT_EXPRESS_REDIRECT_URL );
				
				if ( link_redireccion != null && link_redireccion.trim().length() > 0 ) {
					
					link_redireccion += respuesta.getToken();
					
					if ( isRedireccionar ) {
						try {
							response.sendRedirect( link_redireccion );	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
						} catch (IOException e) {
							errores.add(e.getMessage());
//							e.printStackTrace();
						}	
					}

				}

			} else {
				errores.add( "Paypal " + respuesta.getAcknowledge() + ":");
				if ( null != respuesta.getErrors() ){
					for ( PaypalFailure item : respuesta.getErrors() ) {
						errores.add( item.getErrorCode() + " : " + item.getLongMessage() );
					}
				}
			}
		}
		
		return link_redireccion;

	}
	public static void    onCallBack_setExpressCheckout( HttpServletRequest request, HttpServletResponse response, ArrayList<String> errores ) {
		String ack = request.getParameter("ACK");
		String token = request.getParameter("token");
		String payerId = request.getParameter("PayerID");
		String user_id = request.getParameter("USR");
		String reservation_id = request.getParameter("RSV");

		////////////////////
		if ( token == null || token.trim().length() < 1 ) { ack = "Failure"; }
//		if ( payerId == null || payerId.trim().length() < 1 ) { ack = "Failure"; }
		if ( user_id == null || user_id.trim().length() < 1 ) { ack = "Failure"; }

		////////////////////

		if ( "Success".equalsIgnoreCase( ack ) ) {
			
			// Actualizar estado del token:
			try {
				BDConexion dataBase = new Subrutinas().getBDConexion(request);
				com.fvr.py_PayPalTokens.db.PyAccesoBaseDatos dao_py = new com.fvr.py_PayPalTokens.db.PyAccesoBaseDatos();
				com.fvr.py_PayPalTokens.bean.PyBean          reg_py = new com.fvr.py_PayPalTokens.bean.PyBean();
				// PK:
				reg_py.setPy_user_id( user_id );
				reg_py.setPy_reservation_id( reservation_id );
				reg_py.setPy_paypal_token_id( token );
				reg_py = dao_py.py_getRcd(dataBase, reg_py);
				if ( reg_py != null ) {
					reg_py.setPy_stsProceso( _K.PAY_STS_PAYPAL_PreConfirmado );

					try { dao_py.py_chgObj(dataBase, reg_py); } catch (Exception e1) {;}
					
					///////////////////////////////////////////////////////////////////////////////////////////////
					// Aqui no usamos pantalla de confirmación. 
					// AUTOCONFIRMAMOS LA OPERACIÓN (No le preguntamos al usuario si quiere seguir o no...)
					String amount = null;
					JSONObject json = null;
					try { json = JSONObject.fromObject( reg_py.getPy_json() ); } catch (Exception e) {;}
					if ( json != null ) {
						try { amount = json.getString("amount"); } catch (Exception e) {;}
					}
					
					if ( reservation_id != null && reservation_id.trim().length() > 0 ) {
						if ( amount != null && amount.trim().length() > 0 ) {
							if ( onPagoAceptadoPorCliente( request, errores, token, user_id, reservation_id, amount ) ) {
								;
							};
						}
					} else {
						ack = "Failure";
					}
					///////////////////////////////////////////////////////////////////////////////////////////////

				}
			} catch (StExcepcion e) {;}

		}

		if ( "Failure".equalsIgnoreCase( ack ) ) {
			System.err.println( ack );
		}

//		///////////////////////////////////////////
//		// Se entrega el control a la pantalla de "Confirmación" y "Thank you":
//		request.getSession().setAttribute("key_Py",reg_py);		// En session para que no lo pierda el "redirect"...
//		request.getSession().setAttribute("payerId",payerId);	// En session para que no lo pierda el "redirect"...
//		request.getSession().setAttribute("paypal_ack",ack);	// En session para que no lo pierda el "redirect"...
//		request.getSession().setAttribute("pyErrores",errores);	// En session para que no lo pierda el "redirect"...
//		try {
//
//			String link = Subrutinas.get_urlBase(request) + "/PyEDTRCD_A.do";
//			response.sendRedirect( link );	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//
//		} catch (IOException e) {
//			errores.add(e.getMessage());
////			e.printStackTrace();
//		}	
		///////////////////////////////////////////
        if ( errores.size() > 0 ) {

        }
        ///////////////////////////////////////////
		
	}
	public static void    onCallBack_IPN( HttpServletRequest request, HttpServletResponse response ) {
//		//	IPN endpoint para "recurring payments" (Instant Paypal Notification)
//		
//		BDConexion dataBase = new Subrutinas().getBDConexion(request);
//
//		// DEBE CUMPLIRSE EL PROTOCOLO:
//		//	1.1 . Recibido un mensage IPN.
//		//		1.2 . Devolver un HTTP 200 con RESPONSE vac�o.
//		//	2.1 . Enviar exactamente el mismo mensaje recibido. (Mismos campos y en su mismo orden).
//		//		2.2 . Esperar una respuesta de "VERIFIED" o "INVALID".
//
//		String payload = Subrutinas.getRequestPayload_txt(request);
//		String ipn_url = Subrutinas.getDBValueFromKey(dataBase, _K.CUENTA_DE_SISTEMA, _K.KEY_PAYPAL_IPN_URL);
//		String[] resultado = null;
//		if ( PaypalMethods.ipn_ack_paso1d2(response) ) {
//			resultado = PaypalMethods.ipn_ack_paso2d2(request, response, ipn_url, payload);
//		};
//
////		if ( resultado != null && resultado.length == 2 && "VERIFIED".equalsIgnoreCase( resultado[1] ) ) {
//			String charset = "UTF-8";
//			String ipn_track_id = "";
//			String txn_type = "";
//			String payer_id = "";
//			String recurring_payment_id = "";
//			String product_name = "";
//			String[] trozos = payload.split("&");
//			if ( trozos != null ) {
//				for( String item : trozos ) {
//					String[] nvp = item.split("=");
//					if ( nvp != null && nvp.length == 2 ) {
//						if ( item.contains("charset") ) { charset = (nvp[1]!=null && nvp[1].trim().length()>0)?nvp[1]:charset; }
//						if ( item.contains("ipn_track_id") ) { ipn_track_id = nvp[1]; }
//						if ( item.contains("txn_type") ) { txn_type = nvp[1]; }
//						if ( item.contains("payer_id") ) { payer_id = nvp[1]; }
//						if ( item.contains("recurring_payment_id") ) { recurring_payment_id = nvp[1]; }
//						if ( item.contains("product_name") ) { product_name = nvp[1]; }
//					}
//				}
//			}
//			try { product_name = URLDecoder.decode(product_name, charset); } catch (UnsupportedEncodingException e1) {;}
//			try {
//				// Apuntamos el mensage en BD:
//				com.billin.pym_PayPalMessagesIPN.db.PymAccesoBaseDatos dao_pym = new com.billin.pym_PayPalMessagesIPN.db.PymAccesoBaseDatos();
//				com.billin.pym_PayPalMessagesIPN.bean.PymBean          reg_pym = new com.billin.pym_PayPalMessagesIPN.bean.PymBean(); 
//				// PK autom�tica.
//				// Resto:
////				reg_pym.setPym_sincroiso( "" ); // sincroiso
//				reg_pym.setPym_Sincro( Subrutinas.getDateInMills() ); // Sincro
//				reg_pym.setPym_Marca( _K.MARCA_PYM_NOTIFICACION_PENDIENTE ); // Marca
//				reg_pym.setPym_Suprimido( "" ); // Suprimido
//				reg_pym.setPym_Autor( _K.CUENTA_DE_SISTEMA ); // Autor
////				reg_pym.setPym_PK( 0 ); // PK
//				reg_pym.setPym_ipn_track_id( ipn_track_id ); // ipn_track_id
//				reg_pym.setPym_txn_type( txn_type ); // txn_type
//				reg_pym.setPym_payer_id( payer_id ); // payer_id
//				reg_pym.setPym_recurring_payment_id( recurring_payment_id ); // recurring_payment_id
//				reg_pym.setPym_product_name( product_name ); // product_name
//				reg_pym.setPym_rec_IPN_message( payload ); // rec_IPN_message
//				reg_pym.setPym_rec_IPN_result(  resultado[1] ); // rec_IPN_result
//				reg_pym.setPym_ExtraJson( "" ); // ExtraJson
//				dao_pym.pym_crtObj(dataBase, reg_pym);
//			} catch (StExcepcion e) {
//				e.printStackTrace();
//			}
////		}

	}
	public static boolean onPagoAceptadoPorCliente(HttpServletRequest request, ArrayList<String> errores, String token, String user_id, String reservation_id, String importe) {
		boolean seguir = true;
		BDConexion dataBase = new Subrutinas().getBDConexion(request);

		// Ahora se debe:
		//	1.1 - Confirmar a Paypal el pago.
		//  1.2 - Si es una suscripción "PAYPAL recurring Payment", llamar a "CreateRecurringPaymentsProfile" en vez de a "doExpressCheckout"
		//  2 - Marcar como "pagada" la factura. (Si NO es una suscripción "PAYPAL recurring Payment")
		//  3 - Marcar como procesado el token.

//		String profileId = null;

		///////////
		// 2016-11-02. Respuesta especial para SUSCRIPCIONES
//		if ( _K.SC_CLAVE_DUMMY_PARA_dcPK == kDc ) {
//
//			////////////////////////
//			//  1.2 - Si es una suscripción "PAYPAL recurring Payment", llamar a "CreateRecurringPaymentsProfile" en vez de a "doExpressCheckout"
//			RecurringPaymentData recPayConfig = new RecurringPaymentData();
//			if ( seguir ) {
//				ScBean reg_sc = Subrutinas.getScFromKSc(dataBase, ""+kLz);
//				if ( reg_sc != null && reg_sc.getSc_Sincro() != 0L  ) {
//					APICredentials vendedor = new PaypalCredentials(dataBase, reg_sc.getSc_Vendedor() + "^" + _K.SI );
//					PaypalAPI paypal = PaypalAPIFactory.createPaypalAPI( dataBase, vendedor );
//					PaypalResponse respuesta = api_createRecurringPaymentsProfile(dataBase, errores, paypal, reg_sc, recPayConfig);
//					if ( respuesta == null ) { seguir = false; }
//					if ( "Failure".equalsIgnoreCase( respuesta.getAcknowledge() ) ) {
//						errores.add("Paypal ha retornado error en 'CreateRecurringPaymentsProfile()'.");
//						if ( respuesta != null  && respuesta.getErrors() != null ) {
//							for ( PaypalFailure item : respuesta.getErrors() ) {
//								errores.add( item.getErrorCode() + " : " + item.getLongMessage() );
//							}
//						}
//						seguir = false;
//					}
//					if (seguir) {
//						profileId = respuesta.getProfileId();
//					}
//				} else {
//					seguir = false;
//				}
//			}
//			////////////////////////
//
//			////////////////////////
//			reg_dc = new DcBean();
//			reg_dc_PAGO = new DcBean();
//			if ( seguir ) {
//				// Genera toda la documentación internrna: el lazo y la factura de primer pago...
//				seguir = new Freemium().suscripcion_PAYPAL_onPagoClienteAceptado(request, errores, reg_dc, reg_dc_PAGO, recPayConfig, kLz, kDc);
//			}
//			////////////////////////
//
//		} else {

			////////////////////////
			//	1.1 - Confirmar a Paypal el pago.
			if ( seguir ) {
				APICredentials vendedor = new PaypalCredentials(dataBase);
				PaypalAPI paypal = PaypalAPIFactory.createPaypalAPI( dataBase, vendedor );
				PaypalResponse respuesta = api_doExpressCheckout( dataBase, errores, paypal, user_id, reservation_id, importe );
				if ( "Failure".equalsIgnoreCase( respuesta.getAcknowledge() ) ) {
					seguir = false;
				}
			}

//		}
//		///////////

		////////////////////////
		//  2 - Marcar como "pagada" la reserva.
		if ( seguir ) {
   			try {
   				com.fvr.rs_reservations.db.RsAccesoBaseDatos dao_rs = new com.fvr.rs_reservations.db.RsAccesoBaseDatos();
   				com.fvr.rs_reservations.bean.RsBean reg_rs = new com.fvr.rs_reservations.bean.RsBean();
   				// PK:
   				reg_rs.setRs_reservation_id( reservation_id );
   				reg_rs = dao_rs.rs_getRcd(dataBase, reg_rs);
   				if ( reg_rs != null ) {
   	   				reg_rs.setRs_pay_status( _K.PAY_STS_PAYPAL_Completado );
   					dao_rs.rs_chgObj(dataBase, reg_rs);

   					SendMail.send_comprobanteReserva(dataBase, Subrutinas.get_urlBase(request), user_id, reservation_id, errores, true);

   				}
			} catch (StExcepcion e) {
				seguir = false;
				e.printStackTrace();
			}
		}
		////////////////////////
		//  3 - Marcar como procesado el token.
		if ( seguir ) {
			PyAccesoBaseDatos dao_py = new PyAccesoBaseDatos();
			PyBean reg_py = new PyBean();
			// PK:
			reg_py.setPy_user_id( user_id );
			reg_py.setPy_reservation_id( reservation_id );
			reg_py.setPy_paypal_token_id( token );
			try {
				reg_py = dao_py.py_getRcd(dataBase, reg_py);
				if ( reg_py != null ) {
					reg_py.setPy_stsProceso( _K.PAY_STS_PAYPAL_Completado );
					
//					if ( profileId != null && profileId.trim().length()>0 ) {
//						JSONObject json = null;
//						try { json = JSONObject.fromObject( reg_py.getPy_ExtraJson() ); } catch (Exception e) {;}
//						if ( json == null ) { json = new JSONObject(); }
//						json.put("profileId",profileId);
//						reg_py.setPy_ExtraJson(json.toString());
//					}
					
					dao_py.py_chgObj( dataBase, reg_py );
				}
			} catch (StExcepcion e) {
				seguir = false;
				e.printStackTrace();
			}
		}
		////////////////////////
		
		return seguir;

	}
	///////////////////////
	// Antes de redireccionar al cliente a Paypal:
	public PaypalResponse api_setExpressCheckout( BDConexion dataBase, String nombreFiscalProveedor, String user_id, String reservation_id, String amount, String autor, boolean isRegistrar, boolean isPagarSinCuentaClienteObligada, String RecurringPaymentDescription_o_null ) {

		///////////////
		// PaypalResponse:
//		private String token;
//		private String correlationID;
//		private String acknowledge;
//		private String timeStamp;
//		private String version;
//		private String build;
//		private List<PaypalFailure> errors = new ArrayList<PaypalFailure>();
		///////////////

		PaypalResponse resultado = null;
		PaypalAPI paypal = null;
		
		if ( dataBase == null ) { return resultado; }
		if ( user_id   == null ) { return resultado; }

		String callbackUrl = m_callBack;

		APICredentials vendedor = new PaypalCredentials(dataBase);

		if ( vendedor != null ) {
			
			paypal = PaypalAPIFactory.createPaypalAPI( dataBase, vendedor );
			
//			String nombreFiscalProveedor = "FormulaVR";
			resultado = PaypalMethods.setExpressCheckout(
							  paypal
							, nombreFiscalProveedor
							, reservation_id
							, amount
							, callbackUrl + "?ACK=Success&USR=" + user_id + "&RSV=" + reservation_id
							, callbackUrl + "?ACK=Failure&USR=" + user_id + "&RSV=" + reservation_id
							, isPagarSinCuentaClienteObligada
							, RecurringPaymentDescription_o_null
						);
			
			if ( resultado != null ) {

				System.out.println( resultado.getAcknowledge() );

				if ( "Success".equalsIgnoreCase( resultado.getAcknowledge() ) ) {
					/////////////
					// Persistir el token:
					if ( isRegistrar ) {

						JSONObject json = new JSONObject();
						
						json.put("correlationID", resultado.getCorrelationID() );
						json.put("acknowledge", resultado.getAcknowledge() );
						json.put("timeStamp", resultado.getTimeStamp() );
						json.put("version", resultado.getVersion() );
						json.put("build", resultado.getBuild() );

						json.put("amount", amount );
						
						JSONArray errList = new JSONArray(); 
						if ( resultado.getErrors() != null ) {
							String txt = null;
							for ( PaypalFailure item : resultado.getErrors() ) {
								txt  = item.getErrorCode();
								txt += ":" + item.getErrorCode();
								txt += ":" + item.getSeverityCode();
								txt += ":" + item.getLongMessage();
								txt += ":" + item.getShortMessage();
								errList.add( txt );
							}
						}
						json.put("errList", errList );

						//////////////////////
						com.fvr.py_PayPalTokens.db.PyAccesoBaseDatos dao_py = new com.fvr.py_PayPalTokens.db.PyAccesoBaseDatos();
						com.fvr.py_PayPalTokens.bean.PyBean          reg_py = new com.fvr.py_PayPalTokens.bean.PyBean();
						// PK: PRIMARY KEY ("lzPK", "dcPK", "Token")
						reg_py.setPy_user_id( user_id );
						reg_py.setPy_reservation_id( reservation_id );
						reg_py.setPy_paypal_token_id( resultado.getToken() );
						// Resto:
						reg_py.setPy_sincro( Subrutinas.getDateAuditoria() ); // Sincro
//						reg_py.setPy_Marca( _K.MARCA_SINCRO_PENDIENTE ); // Marca
						reg_py.setPy_is_deleted(""); // Suprimido
						reg_py.setPy_author( autor==null?_K.CUENTA_DE_SISTEMA:autor ); // Autor
						reg_py.setPy_paypal_usr( vendedor.getUser() ); // paypal_USR
						reg_py.setPy_paypal_pwd( vendedor.getPassword() ); // paypal_PWD
						reg_py.setPy_paypal_signature( vendedor.getSignature() ); // paypal_SIGNATURE
						reg_py.setPy_stsProceso(""); // stsProceso: Vacio:pdte, OK:Procesado, KO:Rechazado
						reg_py.setPy_json( json.toString() ); // json
						//////////////////////
						
						try {
							dao_py.py_crtObj(dataBase, reg_py);
						} catch (StExcepcion e) {
							e.printStackTrace();
						}
						
					}
					/////////////
				}
				else
				if ( "Failure".equalsIgnoreCase( resultado.getAcknowledge() ) ) {
					for ( PaypalFailure item : resultado.getErrors()) {
						System.out.println( item.getErrorCode() + ":" + item.getSeverityCode() + ":" + item.getLongMessage() );
					}
				} 
				else {
					System.err.println( "setExpressCheckout() Respuesta no controlada: " + resultado.getAcknowledge() );
				}

			}
			
		}

		return resultado;

	}
	// Después de que cliente retorne desde Paypal:
	public static PaypalResponse api_doExpressCheckout( BDConexion dataBase, ArrayList<String> errores, PaypalAPI paypal, String user_id, String reservation_id, String importe ) {
		////////////////////////
		// NOTA para recurring Payments:
		//		Calls DoExpressCheckoutPayment if the order includes one-time purchases as well as a recurring payment. 
		//		Otherwise, skip this step.
		////////////////////////

		PaypalResponse resultado = null;
		
		if ( dataBase == null ) { errores.add("No se pudo abrir la base de datos."); return resultado; }

		///////////////////////
		// Se recupera "un" token que esté pendiente:
		String token = api_getTokenFromUsrRsva(dataBase, user_id, reservation_id);
		if ( token == null ) { errores.add("No se ha encontrado ningún token para la reserva."); return resultado; }

		///////////////////////
		// Primero se obtiene el "payerID":

		///////////////////////
		APICredentials vendedor = new PaypalCredentials(dataBase);
		if ( vendedor != null ) {

			GetExpressCheckoutResponse detailsPayer = PaypalMethods.getExpressCheckoutDetails(paypal, token);

			if ( detailsPayer == null ) { errores.add("Paypal no ha retornado detalles del comprador en 'detailsPayer()'."); return resultado; }

			resultado = 
					PaypalMethods.doExpressCheckoutPayment(
						paypal, 
						token, 
						detailsPayer.getPayerId(), 
						importe 
					);

			if ( resultado != null && "Success".equalsIgnoreCase( resultado.getAcknowledge() ) ) {

				Subrutinas.addLog(dataBase, _K.SYS, _K.EV_PAYPAL_PAGO_OK, resultado.getCorrelationID(), "api_doExpressCheckout()" );

			} else {

				errores.add("Paypal ha retornado error en 'doExpressCheckoutPayment()'.");
				if ( resultado != null  && resultado.getErrors() != null ) {
					for ( PaypalFailure item : resultado.getErrors() ) {
						errores.add( item.getErrorCode() + " : " + item.getLongMessage() );
					}
				}

			}
		}
		///////////////////////

		return resultado;
	}
	public static PaypalResponse api_createRecurringPaymentsProfile( BDConexion dataBase, ArrayList<String> errores, PaypalAPI paypal, Object ScBean__reg_sc, RecurringPaymentData recPayData_out ) {
		////////////////////////
		// NOTA para recurring Payments:
		//		Calls DoExpressCheckoutPayment if the order includes one-time purchases as well as a recurring payment. 
		//		Otherwise, skip this step.
		////////////////////////

		PaypalResponse resultado = null;
		
//		if ( dataBase == null ) { errores.add("No se pudo abrir la base de datos."); return resultado; }
//
//		DcBean reg_dc = new DcBean();
//		reg_dc.setDc_lzPK( reg_sc.getSc_Suscripcion() );
//		reg_dc.setDc_dcPK( _K.SC_CLAVE_DUMMY_PARA_dcPK );
//		String kDcCompleta = reg_dc.getDc_lzPK() + "^" + reg_dc.getDc_dcPK();
//		String kCtCompleta = reg_sc.getSc_Vendedor() + "^" + _K.SI;
//
//		///////////////////////
//		// Se recupera "un" token que esté pendiente:
//		String token = api_getTokenFromUsrRsva(dataBase, reg_dc);
//		if ( token == null ) { errores.add("No se ha encontrado ningún token para la factura."); return resultado; }
//
//		///////////////////////
//		// Primero se obtiene el "payerID":
//
//		///////////////////////
//		APICredentials vendedor = new PaypalCredentials(dataBase, kCtCompleta);
//		if ( vendedor != null ) {
//
//			GetExpressCheckoutResponse detailsPayer = PaypalMethods.getExpressCheckoutDetails(paypal, token);
//
//			if ( detailsPayer == null ) { errores.add("Paypal no ha retornado detalles del comprador en 'detailsPayer()'."); return resultado; }
//
////			RecurringPaymentData recPayConfig = new RecurringPaymentData();
//				Date date = new Date();	// AHORA !
//				String BILLINGPERIOD = "Month";
//				if ( "A".equalsIgnoreCase( reg_sc.getSc_SCCL_Periodicidad_MBTSA() ) ) { BILLINGPERIOD = "Year"; }
//				if ( "M".equalsIgnoreCase( reg_sc.getSc_SCCL_Periodicidad_MBTSA() ) ) { BILLINGPERIOD = "Month"; }
//				if ( "D".equalsIgnoreCase( reg_sc.getSc_SCCL_Periodicidad_MBTSA() ) ) { BILLINGPERIOD = "Day"; }
//
//				recPayData_out.setPayerID( detailsPayer.getPayerId() );								// Obtenido de: "GetExpressCheckoutDetails"
//				recPayData_out.setProfileStartDate( Subrutinas.getDateISO8601_UTC(date) );					// PROFILESTARTDATE	The date when billing for this profile begins. Note: The profile may take up to 24 hours for activation.
//				recPayData_out.setDesc( reg_sc.getSc_Suscripcion() + " - " + reg_sc.getSc_SCCL_Nombre() );	// DESC
//				recPayData_out.setBillingPeriod( BILLINGPERIOD );									// BILLINGPERIOD	Day Week SemiMonth Month Year
//				recPayData_out.setBillingFrequency( "1" );											// BILLINGFREQUENCY
//				recPayData_out.setAmt( ""+reg_sc.getSc_DD_ImporteTotal()  );						// AMT (Amount to pay)
//				recPayData_out.setCurrencyCode( "EUR" );											// CURRENCYCODE
//				recPayData_out.setCountryCode( detailsPayer.getPayerCountryCode() );				// COUNTRYCODE
//				recPayData_out.setPayerEmail( detailsPayer.getPayerEmail() );						// Payer EMAIL
//
//				// Including an Optional Trial Period
//				// You can optionally include a trial period in the profile by specifying the following fields in the CreateRecurringPaymentsProfile request. 
//				recPayData_out.setTrialBillingPeriod( "Month" );									// TRIALBILLINGPERIOD	Day Week SemiMonth Month Year
//				recPayData_out.setTrialBillingFrequency( "1" );										// TRIALBILLINGFREQUENCY
//				recPayData_out.setTrialAmt( "0.00" );												// TRIALAMT
//				recPayData_out.setTrialBillingCycles( "1" );										// TRIALTOTALBILLINGCYCLES
//				
//				// Specifying an INITIAL PAYMENT
//				//	You can optionally specify an initial non-recurring payment when the recurring payments profile is created by including the following fields in the CreateRecurringPaymentsProfile request:
//				//	Required fields for specifying an initial payment
////				recPayData_out.setInitAmt( ""+reg_sc.getSc_DD_ImporteTotal() );					//	INITAMT
//
//			resultado = 
//					PaypalMethods.CreateRecurringPaymentsProfile(
//						paypal, 
//						token, 
//						detailsPayer.getPayerId(), 
//						recPayData_out						// Si va bien la llamada se completa con el PROFILEID y el PROFILESTATUS.
//					);
//
//			if ( resultado != null && "Success".equalsIgnoreCase( resultado.getAcknowledge() ) ) {
//
//				Eventos.alPagarDcPorPaypal(dataBase, kDcCompleta, resultado.getCorrelationID());
//
//			} else {
//
//				errores.add("Paypal ha retornado error en 'CreateRecurringPaymentsProfile()'.");
//				if ( resultado != null  && resultado.getErrors() != null ) {
//					for ( PaypalFailure item : resultado.getErrors() ) {
//						errores.add( item.getErrorCode() + " : " + item.getLongMessage() );
//					}
//				}
//
//			}
//		}
//		///////////////////////

		return resultado;
	}
	///////////////////////
	public static String api_getTokenFromUsrRsva( BDConexion dataBase, String user_id, String reservation_id ) {
		String resultado = null;
		
		com.fvr.py_PayPalTokens.db.PyAccesoBaseDatos dao_py = new com.fvr.py_PayPalTokens.db.PyAccesoBaseDatos();
		com.fvr.py_PayPalTokens.bean.PyBeanFiltro    flt_py = new com.fvr.py_PayPalTokens.bean.PyBeanFiltro();
		com.fvr.py_PayPalTokens.bean.PyBean[]        rgs_py = null;
		
		flt_py.setPy_user_id( user_id );
		flt_py.setPy_reservation_id( reservation_id );
		
		try {
			rgs_py = dao_py.py_getSeq(dataBase, new ConfigPantalla(), flt_py);
			if ( rgs_py != null && rgs_py.length > 0) {
				resultado = rgs_py[0].getPy_paypal_token_id();
			}
		} catch (StExcepcion e) { e.printStackTrace(); }

		return resultado;
	}
	public static String api_isProcesadoUsrRsva( BDConexion dataBase, String user_id, String reservation_id ) {
		String token = null;

		com.fvr.py_PayPalTokens.db.PyAccesoBaseDatos dao_py = new com.fvr.py_PayPalTokens.db.PyAccesoBaseDatos();
		com.fvr.py_PayPalTokens.bean.PyBeanFiltro    flt_py = new com.fvr.py_PayPalTokens.bean.PyBeanFiltro();
		com.fvr.py_PayPalTokens.bean.PyBean[]        rgs_py = null;

		flt_py.setPy_user_id( user_id );
		flt_py.setPy_reservation_id( reservation_id );
		
		try {
			rgs_py = dao_py.py_getSeq(dataBase, new ConfigPantalla(), flt_py);
			if ( rgs_py != null && rgs_py.length > 0) {
				for ( PyBean item : rgs_py ) {
					if ( item != null && "OK".equalsIgnoreCase( item.getPy_stsProceso() ) ) {
						token = item.getPy_paypal_token_id();
					}
				}
			}
		} catch (StExcepcion e) { e.printStackTrace(); }

		return token;
	}
	public static int    api_dltTokensPdtesUsrRsva( BDConexion dataBase, String user_id, String reservation_id ) {
		int tokens = 0;
		
		com.fvr.py_PayPalTokens.db.PyAccesoBaseDatos dao_py = new com.fvr.py_PayPalTokens.db.PyAccesoBaseDatos();
		com.fvr.py_PayPalTokens.bean.PyBeanFiltro    flt_py = new com.fvr.py_PayPalTokens.bean.PyBeanFiltro();
		com.fvr.py_PayPalTokens.bean.PyBean[]        rgs_py = null;
		
		flt_py.setPy_user_id( user_id );
		flt_py.setPy_reservation_id( reservation_id );
		
		try {
			rgs_py = dao_py.py_getSeq(dataBase, new ConfigPantalla(), flt_py);
			if ( rgs_py != null && rgs_py.length > 0) {
				for ( PyBean item : rgs_py ) {
					if ( item != null && item.getPy_stsProceso().trim().length() == 0 ) {
						dao_py.py_dltObj( dataBase, item );
						tokens++;
					}
				}
			}
		} catch (StExcepcion e) { e.printStackTrace(); }

		return tokens;
	}
	///////////////////////

}
