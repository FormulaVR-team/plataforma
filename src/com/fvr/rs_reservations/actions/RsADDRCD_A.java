package com.fvr.rs_reservations.actions;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.fvr.FuentesDeDatos.BDConexion;
import com.fvr._comun.ConfigPantalla;
import com.fvr._comun.StExcepcion;
import com.fvr._comun.Subrutinas;
import com.fvr._comun._K;
import com.fvr._comun.TPV_LaCaixa.TPV_API;
import com.fvr._comun.TPV_LaCaixa.TPV_API.FormStruct;
import com.fvr._comun.disponiblidad.Reservas;
import com.fvr._comun.mail.SendMail;
import com.fvr._comun.paypal.Paypal_API;
import com.fvr.pm_promosManuales.bean.PmBean;
import com.fvr.rs_reservations.bean.RsBean;
import com.fvr.rs_reservations.forms.RsRCD_AF;

import net.sf.json.JSONObject;

public class RsADDRCD_A extends Action {
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        String resultado = "OK";
        
        RsRCD_AF pantalla = (RsRCD_AF)form;

        //////////////////////////////////////
        // Para permitir llamada de la versión en "Modal AngularJS" 
        boolean isVersionAngular = Subrutinas.isVersionAngular(request, form);	// (CARGA EL ACTION FORM DESDE EL PAYLOAD DE LA LLAMADA)
        //////////////////////////////////////

        String opcion = pantalla.getOpcionPantalla();
        /////////////////////////
        // Sincronizar con su sesión en servidor:
        /////////////////////////
        sincroSesion( request, form );  // Arrastre del usuario y sincronización de estados.
        // La pantalla debe poder ser "autónoma" para que permita seguir funcionando aunque haya caducado su sesión en el servidor.
        // Actúa como repositorio de estados para poder regenerar la sesión si ha caducado.

        /////////////////////////
        // Seguridad de acceso:
         String proceso = (this.getClass().toString()).substring((this.getClass().toString()).lastIndexOf(".")+1);
         String usuario = pantalla.getLogon_USR();
         String claveOp = pantalla.getLogon_HSH();
         Subrutinas subrutinas = new Subrutinas();
         if ( ! subrutinas.controlAcceso( subrutinas.getBDConexion(request), usuario, proceso, claveOp ) ) {
            resultado = "ERROR";
            ActionMessages errores = new ActionMessages();
//            errores.add("error", new ActionMessage( "errors.detail", "'" + usuario + "' no autorizado a '" + proceso + "'." ));
            errores.add("error", new ActionMessage( "errors.detail", "Se ha producido un error de seguridad." ));
            saveErrors(request,errores);

            if (isVersionAngular) { Subrutinas.returnActionVersionAngular(request, response, this, false, null); return null; } // No navega con struts

            return mapping.findForward(resultado);
        }
        /////////////////////////
        if ( opcion == null )
            resultado = this.cargarPantalla( request, pantalla );
        else {
            if ( opcion.trim().length()==0 ) {
                resultado = this.cargarPantalla( request, pantalla );
            } else if ( opcion.trim().equalsIgnoreCase("LeerReg") ) {
                resultado = this.cargarPantalla( request, pantalla );
            } else if ( opcion.trim().equalsIgnoreCase("NuevoReg_cash") ) {
                resultado = opcion_NuevoReg_cash(request,response, form);
            } else if ( opcion.trim().equalsIgnoreCase("NuevoReg_paypal") ) {
                resultado = opcion_NuevoReg_paypal(request,response, form);
            } else if ( opcion.trim().equalsIgnoreCase("NuevoReg_tpv") ) {
                resultado = opcion_NuevoReg_tpv(request,response, form);
                if ( isVersionAngular ) {
                    if ( "CERRAR".equalsIgnoreCase(resultado) ) {
                    	return null;	// No navega con struts
                    }
                }
            } else if ( opcion.trim().equalsIgnoreCase("Cerrar") ) {
                resultado = opcion_Cerrar(request,form);
            } else if (opcion.trim().equalsIgnoreCase("retornoSelect")) {
                recalcularVirtuales(request, pantalla);
            } else if (opcion.trim().equalsIgnoreCase("checkDisponibilidad")) {
            	resultado = checkDisponibilidad(request, form);
            } else {
                resultado = this.cargarPantalla( request, pantalla );
            }
        }
        if (resultado.equalsIgnoreCase("NOVALE"))
            resultado = this.cargarPantalla( request, pantalla );
        
        pantalla.setOpcionPantalla("");

        //////////////////////////////////////
        // Para permitir llamada de la versión en "Modal AngularJS" 
        if ( isVersionAngular ) {
        	if ( null != (ActionMessages) request.getAttribute( "org.apache.struts.action.ERROR" )) {
            	Subrutinas.returnActionVersionAngular(request, response, this, false, null);
        	} else {
        		
        		JSONObject json = new JSONObject();
        		if ( Subrutinas.ActionFormToJson(form, json) ) {
                	Subrutinas.returnActionVersionAngular(request, response, this, true, json.toString());
        		} else {
                	Subrutinas.returnActionVersionAngular(request, response, this, false, "Fallo en ActionFormToJson().");
        		};
        		
        	}
        	return null;	// No navega con struts
        }
        //////////////////////////////////////

        return mapping.findForward(resultado);
    }

	private String cargarPantalla(HttpServletRequest request, ActionForm  form) {
        String resultado = "OK";
        ///////////////////////////////////////////
        RsRCD_AF pantalla = (RsRCD_AF)form;
        ///////////////////////////////////////////
        resultado = CamposCalculados(request,pantalla);
        ///////////////////////////////////////////
        ConfigPantalla cfg = new ConfigPantalla();
        cfg.setNombrePantalla( (this.getClass().toString()).substring((this.getClass().toString()).lastIndexOf(".")+1) );
        cfg.setTituloPantalla("reservations: crear");
        // Hace falta para el título y paginado:
        request.getSession(true).setAttribute( "cfgPantalla", cfg );
        ///////////////////////////////////////////
        return resultado;
    }

    private void sincroSesion( HttpServletRequest request, ActionForm  form ) {
        ///////////////////////////////////////////
        RsRCD_AF pantalla = (RsRCD_AF)form;
        ///////////////////////////////////////////
        // Arrastre del usuario:
        String usr = (String)request.getSession().getAttribute("logon_USR");
        if ( usr != null && usr.trim().length() > 0 && !"null".equalsIgnoreCase(usr) ) { pantalla.setLogon_USR(usr); }
        request.getSession().setAttribute("logon_USR",pantalla.getLogon_USR());
        // Arrastre de la clave de operaciones:
        String hsh = (String)request.getSession().getAttribute("logon_HSH");
        if ( hsh != null && hsh.trim().length() > 0 && !"null".equalsIgnoreCase(hsh) ) { pantalla.setLogon_HSH(hsh); }
        request.getSession().setAttribute("logon_HSH",pantalla.getLogon_HSH());
        ///////////////////////////////////////////
        // Meter aqui el resto de datos a mantener en sesion que se hallan guardado en la pantalla, o que vengan por request.
        // (Por ejemplo, pueden existir datos "restrictores a nivel de aplicación" que han de ser permanentemente transportados entre
        //   pantalla<->sesión, pues deben aplicarse en todos y cada uno de los accesos a los datos...)
        new Subrutinas().sincroSesion_COMUN(request,form);
        ///////////////////////////////////////////
    }
    
    private String opcion_NuevoReg_paypal(HttpServletRequest request, HttpServletResponse response, ActionForm  form) {
        String resultado = "OK";
        ///////////////////////////////////////////
        RsRCD_AF pantalla = (RsRCD_AF)form;
        ///////////////////////////////////////////
        resultado = this.chkPantalla( request, pantalla );
        // Altero imagen WRK en disco:
        if (resultado.equalsIgnoreCase("OK"))
            resultado = this.crtRcd( request, pantalla );
        if (resultado.equalsIgnoreCase("OK")) {

        	if ( pantalla.getRs_amount() > 0L ) {
            	pagarReserva_paypal(request, response, form);
        	}

        }
        ///////////////////////////////////////////
        return resultado;
    }
    private String opcion_NuevoReg_tpv(HttpServletRequest request, HttpServletResponse response, ActionForm  form) {
        String resultado = "OK";
        ///////////////////////////////////////////
        RsRCD_AF pantalla = (RsRCD_AF)form;
        ///////////////////////////////////////////
        resultado = this.chkPantalla( request, pantalla );
        // Altero imagen WRK en disco:
        if (resultado.equalsIgnoreCase("OK"))
            resultado = this.crtRcd( request, pantalla );
        if (resultado.equalsIgnoreCase("OK")) {

        	if ( pantalla.getRs_amount() > 0L ) {
            	resultado = pagarReserva_tpv(request, response, form);
        	}

        }
        ///////////////////////////////////////////
        return resultado;
    }
    private String opcion_NuevoReg_cash(HttpServletRequest request, HttpServletResponse response, ActionForm  form) {
        String resultado = "OK";
        ///////////////////////////////////////////
        RsRCD_AF pantalla = (RsRCD_AF)form;
        ActionMessages errores = new ActionMessages();
    	BDConexion dataBase = new Subrutinas().getBDConexion(request);
        ///////////////////////////////////////////
        resultado = this.chkPantalla( request, pantalla );
        // Altero imagen WRK en disco:
        if (resultado.equalsIgnoreCase("OK")) {

        	pantalla.setRs_pay_status( _K.PAY_STS_CASH_PreConfirmado );

        	resultado = this.crtRcd( request, pantalla );
            if (resultado.equalsIgnoreCase("OK")) {
            	String url_base = Subrutinas.get_urlBase(request);
    			String user_id = pantalla.logon_USR;
    			String reservation_id = pantalla.getRs_reservation_id();
    			List<String> lstErrores = new ArrayList<String>();
    			boolean isEnviar = true;

    			SendMail.send_comprobanteReserva(dataBase, url_base, user_id, reservation_id, lstErrores, isEnviar);

    			if ( lstErrores.size() > 0 ) {
        			for ( String item : lstErrores ) { errores.add("error", new ActionMessage( "errors.detail", item )); }
        		}
                if ( errores.size() > 0 ) { 
                	saveErrors(request,errores);
                }
            }

        }
        ///////////////////////////////////////////
        return resultado;
    }
    
    private String opcion_Cerrar(HttpServletRequest request, ActionForm  form) {
        String resultado = "OK";
        ///////////////////////////////////////////
        resultado = "CERRAR";
        ///////////////////////////////////////////
        return resultado;
    }

    private void recalcularVirtuales(HttpServletRequest request, ActionForm form) {
        RsRCD_AF pantalla = (RsRCD_AF)form;
        ///////////////////////////////////////////
        Subrutinas subrutinas = new Subrutinas();
        ///////////////////////////////////////////
        // POR EJEMPLO: Provincia
        //pantalla.setAc_DESC( subrutinas.rtvProvincia( request, pantalla.getAc_KEY() ) );

        

        ///////////////////////////////////////////
    }
    
    private String CamposCalculados(HttpServletRequest request, ActionForm  form) {
        String resultado = "OK";
        ///////////////////////////////////////////
        RsRCD_AF pantalla = (RsRCD_AF)form;
        BDConexion dataBase = new Subrutinas().getBDConexion(request);
        ///////////////////////////////////////////
        // Inicializar campos:

        pantalla.setRs_author( pantalla.getLogon_USR() );
        pantalla.setRs_user_id( pantalla.getLogon_USR() );
//        pantalla.setRs_reservation_id( "DUMMY" );

        ///////////////////////////////////////////
        // Campos deducidos:
        ///////////////////////////////////////////

        ArrayList<String> misErrores = new ArrayList<String>();

        RsBean reg_rs = new RsBean();
        pantalla.copyTo( reg_rs );

		Reservas.determinarImporte(dataBase, reg_rs, misErrores);

		// Se pueden haber reescrito estos campos:
		pantalla.setRs_quantity( reg_rs.getRs_quantity() );
		pantalla.setRs_places( reg_rs.getRs_places() );
        pantalla.setRs_duration_minutes( reg_rs.getRs_duration_minutes() );
		pantalla.setRs_amount( reg_rs.getRs_amount() );

		pantalla.setRs_comment( reg_rs.getRs_comment() );
		pantalla.setRs_product_id( reg_rs.getRs_product_id() );
		pantalla.setRs_product_id2( reg_rs.getRs_product_id2() );
		pantalla.setRs_product_id3( reg_rs.getRs_product_id3() );

        ///////////////////////////////////////////
        return resultado;
    }
    
    private String chkPantalla(HttpServletRequest request, ActionForm  form) {
        String resultado = "OK";
        ///////////////////////////////////////////
        RsRCD_AF pantalla = (RsRCD_AF)form;
        ///////////////////////////////////////////
        CamposCalculados(request,pantalla);
        ///////////////////////////////////////////
        ActionMessages errores = new ActionMessages();
        BDConexion dataBase = new Subrutinas().getBDConexion(request);
        // Claves obligatorias: (AUTOMÁTICA del sistema)
//        if (
//		   pantalla.getRs_reservation_id() == null || pantalla.getRs_reservation_id().trim().length() < 1
//                ) {
//            resultado = "NOVALE";
//            errores.add("error", new ActionMessage( "errors.detail", "Valor para CLAVE obligatorio." ));
//        }

        ///////////////////////////////////////////
        
        if ( pantalla.getRs_location_id() == null || pantalla.getRs_location_id().trim().length() < 1 ) {
            resultado = "NOVALE";
            errores.add("error", new ActionMessage( "errors.detail", "Valor para LOCALIZACIÓN obligatorio." ));
        }
        if ( pantalla.getRs_start_date() == null || pantalla.getRs_start_date().trim().length() != 10 ) {
            resultado = "NOVALE";
            errores.add("error", new ActionMessage( "errors.detail", "Valor para FECHA obligatorio." ));
        }
        if ( pantalla.getRs_start_time() == null || pantalla.getRs_start_time().trim().length() < 1 ) {
            resultado = "NOVALE";
            errores.add("error", new ActionMessage( "errors.detail", "Valor para HORA obligatorio." ));
        }
        if ( pantalla.getRs_product_id() == null || pantalla.getRs_product_id().trim().length() < 1 ) {
            resultado = "NOVALE";
            errores.add("error", new ActionMessage( "errors.detail", "Valor para PRODUCTO obligatorio." ));
        }
        if ( pantalla.getRs_quantity() < 1 ) {
            resultado = "NOVALE";
            errores.add("error", new ActionMessage( "errors.detail", "Valor para CANTIDAD obligatorio." ));
        }
        if ( pantalla.getRs_places() < 1 ) {
            resultado = "NOVALE";
            errores.add("error", new ActionMessage( "errors.detail", "Valor para CABINAS obligatorio." ));
        }
        if ( pantalla.getRs_phone() == null || pantalla.getRs_phone().trim().length() < 1 ) {
            resultado = "NOVALE";
            errores.add("error", new ActionMessage( "errors.detail", "Valor para TELÉFONO obligatorio." ));
        }
        if ( pantalla.getRs_US_nick() == null || pantalla.getRs_US_nick().trim().length() < 1 ) {
            resultado = "NOVALE";
            errores.add("error", new ActionMessage( "errors.detail", "Valor para NICK obligatorio." ));
        }
        if ( pantalla.getRs_coupon_id() != null && pantalla.getRs_coupon_id().trim().length() > 0 ) {
			PmBean reg_pm = Subrutinas.getPmFromId(dataBase, pantalla.getRs_coupon_id());
			if ( reg_pm.getPm_sincro() == null || reg_pm.getPm_sincro().trim().length() < 1 ) {
	            resultado = "NOVALE";
	            errores.add("error", new ActionMessage( "errors.detail", "Cupón no hallado." ));
			} else {
				// ¿vigente?
				try {
					long ahora = Subrutinas.parse_long( Subrutinas.getDateAuditoria() );
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					long deadLinePromo = Subrutinas.parse_long( Subrutinas.getDateAuditoria( sdf.parse( reg_pm.getPm_deadline() ) ) );
					if ( ahora > deadLinePromo ) {
						// Eliminar esa clave!!
						pantalla.setRs_coupon_id("");
					}
				} catch (ParseException e) {;}

			}
        }
		if ( Subrutinas.isExisteNick(dataBase, pantalla.getLogon_USR(), pantalla.getRs_US_nick()) ) {
            resultado = "NOVALE";
            errores.add("error", new ActionMessage( "errors.detail", "Lo sentimos, ese NICK ya existe en el sistema. Elija otro por favor." ));
        }

        ///////////////////////////////////////////
        if ( errores.size() > 0 )
            saveErrors(request,errores);
        ///////////////////////////////////////////
        return resultado;
    }
    
    private String crtRcd(HttpServletRequest request, ActionForm  form) {
        String resultado = "OK";
        ///////////////////////////////////////////
        RsRCD_AF pantalla = (RsRCD_AF)form;
		BDConexion dataBase = new Subrutinas().getBDConexion(request);
        RsBean registro = null;
        if (pantalla != null ) {
            registro = new RsBean();
            
            pantalla.copyTo( registro );
            
            try {
            	
            	ArrayList<String> susErrores = new ArrayList<String>();
				if ( ! Reservas.crearReserva(dataBase, registro, susErrores) ) {
	                resultado = "NOVALE";
				};
				
				// Valor creado en BD. Se recupera aqui:
				pantalla.setRs_reservation_id( registro.getRs_reservation_id() );
				
				if ( ! susErrores.isEmpty() ) {
	                ActionMessages errores = new ActionMessages();
					for ( String item : susErrores ) {
		                errores.add("error", new ActionMessage( "errors.detail", item ));
	                }
	                saveErrors(request,errores);
				}
            	
//				new RsAccesoBaseDatos().rs_crtObj( new Subrutinas().getBDConexion(request), registro );

            } catch (StExcepcion ex) {
                resultado = "NOVALE";
                ActionMessages errores = new ActionMessages();
                errores.add("error", new ActionMessage( "errors.detail", ex.getMessage() ));
                saveErrors(request,errores);
            }
        }
        ///////////////////////////////////////////
        return resultado;
    }

	private String checkDisponibilidad(HttpServletRequest request, ActionForm form) {
		String resultado = "OK";
        ///////////////////////////////////////////
        RsRCD_AF pantalla = (RsRCD_AF)form;
        ActionMessages errores = new ActionMessages();
    	BDConexion dataBase = new Subrutinas().getBDConexion(request);

    	resultado = chkPantalla(request,form);
        if ( "OK".equalsIgnoreCase(resultado) ) {
			RsBean reg_rs = new RsBean();
			pantalla.copyTo(reg_rs);
			ArrayList<String> susErrores = new ArrayList<String>();
			if ( ! Subrutinas.checkDisponibilidad(dataBase, reg_rs, susErrores) ) {
				for ( String item : susErrores ) {
		            errores.add("error", new ActionMessage( "errors.detail", item ));
				}
	            errores.add("error", new ActionMessage( "errors.detail", "Lo sentimos, no tenemos disponibilidad para su elección." ));
	            errores.add("error", new ActionMessage( "errors.detail", "Por favor intente modificarla." ));
			} else {

				/////////////////////////////////////
				// Tiene un cupón o una promoción  de "gratis"?
				if ( reg_rs.getRs_amount() == 0.0 ) {
					if ( 
							   ( reg_rs.getRs_product_id2() != null && reg_rs.getRs_product_id2().trim().length() > 0 )
							|| ( reg_rs.getRs_product_id3() != null && reg_rs.getRs_product_id3().trim().length() > 0 ) 
						){
						
						///////////////////
						// Si es gratis ("amount == 0") SOLO PARA UNA PLAZA!!!
						pantalla.setRs_places( 1L );
						///////////////////

						crtRcd(request, form);
						
					}
				}
				/////////////////////////////////////

			};
        }

        ///////////////////////////////////////////
        if ( ! errores.isEmpty() ) {
        	saveErrors(request, errores);
        }
        ///////////////////////////////////////////
        return resultado;
	}

    private String pagarReserva_paypal(HttpServletRequest request, HttpServletResponse response, ActionForm form) {
        String resultado = "OK";
        ///////////////////////////////////////////
        RsRCD_AF pantalla = (RsRCD_AF)form;
        ActionMessages errores = new ActionMessages();
    	BDConexion dataBase = new Subrutinas().getBDConexion(request);

    	///////////////////////////////////////////
        if (resultado.equalsIgnoreCase("OK")) { 

        	Paypal_API paypal = new Paypal_API(dataBase, Subrutinas.get_urlBase(request) + "/Paypal");
        	
        	String nombreFiscalProveedor = "Formula VR";
			String user_id = pantalla.getRs_user_id();
			String reservation_id = pantalla.getRs_reservation_id();
			String amount = ""+pantalla.getRs_amount();
			String recurringPaymentDescription_o_null = null;
        	List<String> lstErrores = new ArrayList<String>();
        	
        	boolean isRedireccionar = false;

			String link_redireccion = paypal.onPagarPorPaypal(request, response, dataBase, lstErrores, nombreFiscalProveedor, user_id, reservation_id, amount, recurringPaymentDescription_o_null, isRedireccionar);

			if ( link_redireccion != null && link_redireccion.trim().length() > 0 ) {
				// Se devuelve el link para redireccionar desde el navegador, para evitar ERROR de CORS cuando se llama desde Angular (javascript XMLHttp...esa historia, ya sabes)
				pantalla.setRs_note( link_redireccion );
			}
			
    		if ( lstErrores.size() > 0 ) {
    			for ( String item : lstErrores ) { errores.add("error", new ActionMessage( "errors.detail", item )); }
    		}

        }
    	///////////////////////////////////////////

        if ( errores.size() > 0 ) { 
        	saveErrors(request,errores);
        }

        return resultado;
	}

    private String pagarReserva_tpv(HttpServletRequest request, HttpServletResponse response, ActionForm form) {
        String resultado = "OK";
        ///////////////////////////////////////////
        RsRCD_AF pantalla = (RsRCD_AF)form;
        ActionMessages errores = new ActionMessages();
    	BDConexion dataBase = new Subrutinas().getBDConexion(request);

    	///////////////////////////////////////////
        if (resultado.equalsIgnoreCase("OK")) { 

        	TPV_API tpv = new TPV_API(dataBase); 
        	
			List<String> lstErrores = new ArrayList<String>();

			String url_base = Subrutinas.get_urlBase(request);
			FormStruct out_formData = tpv.new FormStruct();
			String order = pantalla.getRs_reservation_id();
			double amount = pantalla.getRs_amount();

			///////////////////////
			// TOKEN. Conseguir una clave única para el callback:
			com.fvr.tk_tokens.bean.TkBean reg_tk = new com.fvr.tk_tokens.bean.TkBean();
			String token_id = Subrutinas.getHashFromRandomCode();
			while ( ! "".equalsIgnoreCase( Subrutinas.getTkFromId(dataBase, token_id).getTk_sincro() ) ) {
				// Si tiene "Sincro" es que ya existía...
				token_id = Subrutinas.getHashFromRandomCode();
			}
			///////////////////////
			
			String link_redireccion = tpv.prepareFormData( url_base, out_formData, order, amount, token_id, lstErrores );
			
			if ( link_redireccion != null ) {
				System.out.println( "TPV LINK REDIRECCION: " + link_redireccion );
				JSONObject jsonData = new JSONObject(); 
				jsonData.put("url_redirect", link_redireccion);
				jsonData.put("ds_SignatureVersion", out_formData.ds_SignatureVersion);
				jsonData.put("ds_MerchantParameters", out_formData.ds_MerchantParameters);
				jsonData.put("ds_Signature", out_formData.ds_Signature);

				///////////////////////
				// TOKEN. Conseguir una clave única para el callback:
				try {
					JSONObject json = new JSONObject();
					json.put("acc", "TPV_PAGO_LaCaixa");
					json.put("reservation_id", order);
					json.put("url_redirect", link_redireccion);
					json.put("ds_Signature", out_formData.ds_Signature);
					json.put("ds_MerchantParameters", out_formData.ds_MerchantParameters);
					reg_tk.setTk_token_id( token_id );
					reg_tk.setTk_author( pantalla.getLogon_USR() );
					reg_tk.setTk_json( json.toString() );

					new com.fvr.tk_tokens.db.TkAccesoBaseDatos().tk_crtObj(dataBase, reg_tk);
				} catch (StExcepcion e) {;}
				///////////////////////

				///////////////
				PrintWriter out = null;
				try {
					response.setContentType("application/json");
					out = response.getWriter();
					JSONObject json = new JSONObject();

					json.put( "server", Subrutinas.getComputername() );
					json.put( "class",  this.getClass().getSimpleName() );
					json.put( "rc",     "OK" );
					json.put( "text",   jsonData.toString() );

					out.print( json.toString() );
					
					resultado = "CERRAR";

				} catch (IOException e) {
					lstErrores.add(e.getMessage());
//					e.printStackTrace();
				} finally {
					if ( out != null) {
						out.close();
					}
				}
				///////////////
			}
    		if ( lstErrores.size() > 0 ) {
    			for ( String item : lstErrores ) { errores.add("error", new ActionMessage( "errors.detail", item )); }
    		}

        }
    	///////////////////////////////////////////

        if ( errores.size() > 0 ) { 
        	saveErrors(request,errores);
        }

        return resultado;
	}
    
}
