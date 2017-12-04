package com.fvr.es_eventSusbscriptions.actions;


import java.io.IOException;
import java.io.PrintWriter;
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
import com.fvr._comun.TPV_LaCaixa.TPV_API;
import com.fvr._comun.TPV_LaCaixa.TPV_API.FormStruct;
import com.fvr.es_eventSusbscriptions.bean.EsBean;
import com.fvr.es_eventSusbscriptions.bean.EsBeanFiltro;
import com.fvr.es_eventSusbscriptions.db.EsAccesoBaseDatos;
import com.fvr.es_eventSusbscriptions.forms.EsRCD_AF;
import com.fvr.ev_events.bean.EvBean;

import net.sf.json.JSONObject;

public class EsADDRCD_A extends Action {
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        String resultado = "OK";
        
        EsRCD_AF pantalla = (EsRCD_AF)form;

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
            } else if ( opcion.trim().equalsIgnoreCase("NuevoReg") ) {
                resultado = opcion_NuevoReg(request,response,form);
            } else if ( opcion.trim().equalsIgnoreCase("Cerrar") ) {
                resultado = opcion_Cerrar(request,form);
            } else if (opcion.trim().equalsIgnoreCase("retornoSelect")) {
                recalcularVirtuales(request, pantalla);
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
        EsRCD_AF pantalla = (EsRCD_AF)form;
        ///////////////////////////////////////////
        resultado = CamposCalculados(request,pantalla);
        ///////////////////////////////////////////
        ConfigPantalla cfg = new ConfigPantalla();
        cfg.setNombrePantalla( (this.getClass().toString()).substring((this.getClass().toString()).lastIndexOf(".")+1) );
        cfg.setTituloPantalla("eventSusbscriptions: crear");
        // Hace falta para el título y paginado:
        request.getSession(true).setAttribute( "cfgPantalla", cfg );
        ///////////////////////////////////////////
        return resultado;
    }

    private void sincroSesion( HttpServletRequest request, ActionForm  form ) {
        ///////////////////////////////////////////
        EsRCD_AF pantalla = (EsRCD_AF)form;
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
    
    private String opcion_NuevoReg(HttpServletRequest request, HttpServletResponse response, ActionForm  form) {
        String resultado = "OK";
        ///////////////////////////////////////////
        EsRCD_AF pantalla = (EsRCD_AF)form;
        ///////////////////////////////////////////
        resultado = this.chkPantalla( request, pantalla );
        // Altero imagen WRK en disco:
        if (resultado.equalsIgnoreCase("OK"))
            resultado = this.crtRcd( request, pantalla );
        if (resultado.equalsIgnoreCase("OK")) {
        	if ( pantalla.getEs_amount() > 0.0 ) {
            	resultado = pagarInscripcion_tpv(request, response, form);
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
        EsRCD_AF pantalla = (EsRCD_AF)form;
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
        EsRCD_AF pantalla = (EsRCD_AF)form;
    	BDConexion dataBase = new Subrutinas().getBDConexion(request);
        ///////////////////////////////////////////
        // Inicializar campos:

        pantalla.setEs_author( pantalla.getLogon_USR() );
        pantalla.setEs_inscription_user_id( pantalla.getLogon_USR() );

        ///////////////////////////////////////////
        // Campos deducidos:
        ///////////////////////////////////////////
        
        EsBean reg_es = new EsBean();
        
        pantalla.copyTo(reg_es);
        
		Subrutinas.derivarCamposRegistro(dataBase, reg_es, Subrutinas.getEvFromId(dataBase, pantalla.getEs_event_id()) );

        pantalla.copyFrom(reg_es);

//		pantalla.setEs_tpv_order( "" );
//        pantalla.setEs_amount( 0.0 );
//        if (pantalla.getEs_event_id() != null && pantalla.getEs_event_id().trim().length() > 0) {
//        	EvBean evBean = Subrutinas.getEvFromId(dataBase, pantalla.getEs_event_id());
//        	if (evBean.getEv_sincro() != null && evBean.getEv_sincro().trim().length()> 0) {
//        		pantalla.setEs_EV_location_id(evBean.getEv_location_id());
//        		pantalla.setEs_LO_name(evBean.getEv_LO_name());
//        		
//    			////////////
//    			String order_AUX =  Subrutinas.getDateAuditoria() + pantalla.getEs_event_id().trim() + pantalla.getEs_inscription_user_id().trim();
//    			java.util.zip.CRC32 order_checkSum = new java.util.zip.CRC32();
//    			order_checkSum.update( order_AUX.getBytes() );
//    			String order = Long.toHexString( order_checkSum.getValue() );
//    			
//    			order = Subrutinas.getDateAuditoria().substring(2,6) + order.toUpperCase();
//
//    			pantalla.setEs_tpv_order( order );
//    	        pantalla.setEs_amount( evBean.getEv_amount() );
//    	        pantalla.setEs_currency( evBean.getEv_currency() );
//    			////////////
//
//        	}
//        }
        
        ///////////////////////////////////////////
        return resultado;
    }
    
    private String chkPantalla(HttpServletRequest request, ActionForm  form) {
        String resultado = "OK";
        ///////////////////////////////////////////
        EsRCD_AF pantalla = (EsRCD_AF)form;
        BDConexion dataBase = new Subrutinas().getBDConexion(request);
        ///////////////////////////////////////////
        CamposCalculados(request,pantalla);
        ///////////////////////////////////////////
        ActionMessages errores = new ActionMessages();
        // Claves obligatorias:
        if (
		   pantalla.getEs_event_id() == null || pantalla.getEs_event_id().trim().length() < 1
		|| pantalla.getEs_inscription_user_id() == null || pantalla.getEs_inscription_user_id().trim().length() < 1
                ) {
            resultado = "NOVALE";
            errores.add("error", new ActionMessage( "errors.detail", "Valores para CLAVE obligatorios." ));
        }

        ///////////////////////////////////////////

        if (
		   pantalla.getEs_EV_location_id() == null || pantalla.getEs_EV_location_id().trim().length() < 1
                ) {
            resultado = "NOVALE";
            errores.add("error", new ActionMessage( "errors.detail", "Valor para LOCATION obligatorio." ));
        }
        
        if ( resultado.equalsIgnoreCase("OK") ) {
            EsBean reg_es = new EsBean();
            reg_es.setEs_event_id(pantalla.getEs_event_id());
            reg_es.setEs_inscription_user_id(pantalla.getEs_inscription_user_id());
            reg_es = Subrutinas.getEsFromId(dataBase, reg_es);
            if ( reg_es.getEs_sincro() != null && reg_es.getEs_sincro().trim().length() > 0) {
                resultado = "NOVALE";
                errores.add("error", new ActionMessage( "errors.detail", 
                		"El usuario " 
                		+ reg_es.getEs_inscription_user_id()
                		+ ", ya esta inscrito en "
                		+ reg_es.getEs_event_id()
                		));
            }
        }
        
        if ( resultado.equalsIgnoreCase("OK") ) {
            try {
                EsBeanFiltro flt_es = new EsBeanFiltro();
                flt_es.setEs_event_id(pantalla.getEs_event_id());
				EsBean[] rgs_es = new EsAccesoBaseDatos().es_getSeq(dataBase, new ConfigPantalla(Integer.MAX_VALUE), flt_es);
	        	EvBean evBean = Subrutinas.getEvFromId(dataBase, pantalla.getEs_event_id());
	        	if (evBean.getEv_sincro() != null && evBean.getEv_sincro().trim().length()> 0) {
	        		if ( (1 + rgs_es.length) > evBean.getEv_max_inscriptions() ) {
	    	            resultado = "NOVALE";
	    	            errores.add("error", new ActionMessage( "errors.detail", "No quedan plazas disponibles para el evento " ));
	        		}
	        	}
			} catch (StExcepcion e) {
	            resultado = "NOVALE";
	            errores.add("error", new ActionMessage( "errors.detail", e.getMessage() ));
			}
        	
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
        EsRCD_AF pantalla = (EsRCD_AF)form;
        EsBean registro = null;
        if (pantalla != null ) {
            registro = new EsBean();
            
            pantalla.copyTo( registro );
            
            EsAccesoBaseDatos db = new EsAccesoBaseDatos();
            try {
                db.es_crtObj( new Subrutinas().getBDConexion(request), registro );
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

    private String pagarInscripcion_tpv(HttpServletRequest request, HttpServletResponse response, ActionForm form) {
        String resultado = "OK";
        ///////////////////////////////////////////
        EsRCD_AF pantalla = (EsRCD_AF)form;
        ActionMessages errores = new ActionMessages();
    	BDConexion dataBase = new Subrutinas().getBDConexion(request);

    	///////////////////////////////////////////
        if (resultado.equalsIgnoreCase("OK")) { 

        	TPV_API tpv = new TPV_API(dataBase); 
        	
			List<String> lstErrores = new ArrayList<String>();

			String url_base = Subrutinas.get_urlBase(request);
			FormStruct out_formData = tpv.new FormStruct();
			double amount = pantalla.getEs_amount();
			String location_id = pantalla.getEs_EV_location_id();
			String author = pantalla.getLogon_USR();
// El n.º de pedido generado por su plataforma para el parámetro Ds_Merchant_Order cumpla las siguientes reglas:
//			• Tiene un mínimo de 4 dígitos y un máximo de 12.
//			• Los 4 primeros dígitos son numéricos.
//			• Sólo se utilizan dígitos alfanuméricos (A-Z, a-z, 0-9).
			String order = pantalla.getEs_tpv_order();

			///////////////////////
			// TOKEN. Conseguir una clave única para el callback:
			com.fvr.tk_tokens.bean.TkBean reg_tk = new com.fvr.tk_tokens.bean.TkBean();
			String token_id = Subrutinas.getHashFromRandomCode();
			while ( ! "".equalsIgnoreCase( Subrutinas.getTkFromId(dataBase, token_id).getTk_sincro() ) ) {
				// Si tiene "Sincro" es que ya existía...
				token_id = Subrutinas.getHashFromRandomCode();
			}
			///////////////////////

			String link_redireccion = tpv.prepareFormData( url_base, out_formData, order, amount, token_id, location_id, lstErrores );

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
					json.put("acc", "TPV_PAGO_EVENTO_LaCaixa");
					json.put("reservation_id", order);
					json.put("event_id", pantalla.getEs_event_id());
					json.put("inscription_user_id", pantalla.getEs_inscription_user_id());
					json.put("url_redirect", link_redireccion);
					json.put("ds_Signature", out_formData.ds_Signature);
					json.put("ds_MerchantParameters", out_formData.ds_MerchantParameters);
					reg_tk.setTk_token_id( token_id );
					reg_tk.setTk_author( author );
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
