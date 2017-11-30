package com.fvr.es_eventSusbscriptions.actions;


import com.fvr._comun.ConfigPantalla;
import com.fvr._comun.StExcepcion;
import com.fvr._comun.Subrutinas;
import com.fvr.es_eventSusbscriptions.bean.EsBean;
import com.fvr.es_eventSusbscriptions.db.EsAccesoBaseDatos;
import com.fvr.es_eventSusbscriptions.forms.EsRCD_AF;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class EsEDTRCD_A extends Action {
    
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
            } else if ( opcion.trim().equalsIgnoreCase("CambiarReg") ) {
                resultado = opcion_CambiarReg(request,pantalla);
            } else if ( opcion.trim().equalsIgnoreCase("Suprimir") ) {
                resultado = opcion_Suprimir(request,form);
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
        EsBean key = null;
        EsBean registro = null;
        ////////////////////////////////
        // Recupera la clave del 'request' (desde los "parámetros"...) por si estuviera:
        key = (EsBean)request.getAttribute("key_Es");
        if ( key == null) key = new EsBean();
        // Si no estaba en los "parámetros" la toma desde la pantalla: (es una reejecución... filtro, o lo que sea)
        if (
		   key.getEs_event_id() == null || key.getEs_event_id().trim().length() < 1
		|| key.getEs_inscription_user_id() == null || key.getEs_inscription_user_id().trim().length() < 1
                ) {
		key.setEs_event_id( pantalla.getEs_event_id() );
		key.setEs_inscription_user_id( pantalla.getEs_inscription_user_id() );
            }
        ///////////////////////////////////////////
        ConfigPantalla cfg = new ConfigPantalla();
        cfg.setNombrePantalla( (this.getClass().toString()).substring((this.getClass().toString()).lastIndexOf(".")+1) );
        cfg.setTituloPantalla("eventSusbscriptions: modificar");
        // Hace falta para el título y paginado:
        request.getSession(true).setAttribute( "cfgPantalla", cfg );
        ///////////////////////////////////////////
        if ( pantalla.getOpcionPantalla() != null && pantalla.getOpcionPantalla().trim().equalsIgnoreCase("retornoSelect")) {
            // Refresca con los datos actuales de la pantalla...(no se debe recargar del disco ahora, se perderían las selecciones).
            return resultado;
        }
        ////////////////////////////////
        // Si la clave es válida:
        if (  !( 
		   key.getEs_event_id() == null || key.getEs_event_id().trim().length() < 1
		|| key.getEs_inscription_user_id() == null || key.getEs_inscription_user_id().trim().length() < 1
               )
           ) {
            EsAccesoBaseDatos db = new EsAccesoBaseDatos();
            try {
                registro = db.es_getRcd( new Subrutinas().getBDConexion(request), key );
            } catch (StExcepcion ex) {
                resultado = "ERROR";
                ActionMessages errores = new ActionMessages();
                errores.add("error", new ActionMessage( "errors.detail", ex.getMessage() ));
                saveErrors(request,errores);
            }
            if (registro != null ) {
                pantalla.copyFrom( registro );
            } else {
                pantalla.copyFrom( new EsBean() );
            }
        }
        ///////////////////////////////////////////
        CamposCalculados(request,pantalla);
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
    private String opcion_CambiarReg(HttpServletRequest request, ActionForm form) {
        String resultado = "OK";
        ///////////////////////////////////////////
        EsRCD_AF pantalla = (EsRCD_AF)form;
        ///////////////////////////////////////////
        resultado = this.chkPantalla( request, pantalla );
        if (resultado.equalsIgnoreCase("OK"))
            resultado = this.chgRcd( request, pantalla );
        if (resultado.equalsIgnoreCase("OK"))
            resultado = "CERRAR";
        else
            resultado = "OK";
        ///////////////////////////////////////////
        return resultado;
    }
    private String opcion_Suprimir(HttpServletRequest request, ActionForm  form) {
        String resultado = "OK";
        ///////////////////////////////////////////
        EsRCD_AF pantalla = (EsRCD_AF)form;
        ///////////////////////////////////////////
        resultado = this.dltRcd( request, pantalla );
        if (resultado.equalsIgnoreCase("OK")) {
            // Devuelvo el control a la lista:
            resultado = "CERRAR";
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
        ///////////////////////////////////////////
        // Inicializar campos:

        
        
        ///////////////////////////////////////////
        // Campos deducidos:
        ///////////////////////////////////////////

        
        ///////////////////////////////////////////
        return resultado;
    }

    private String chkPantalla(HttpServletRequest request, ActionForm  form) {
        String resultado = "OK";
        ///////////////////////////////////////////
        EsRCD_AF pantalla = (EsRCD_AF)form;
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

        
	
        ///////////////////////////////////////////
        if ( errores.size() > 0 )
            saveErrors(request,errores);
        ///////////////////////////////////////////
        return resultado;
    }
    private String dltRcd(HttpServletRequest request, ActionForm  form) {
        String resultado = "OK";
        ///////////////////////////////////////////
        EsRCD_AF pantalla = (EsRCD_AF)form;
        EsBean registro = null;
        if (pantalla != null ) {
            registro = new EsBean();
            
            pantalla.copyTo( registro );
            
            EsAccesoBaseDatos db = new EsAccesoBaseDatos();
            try {
                db.es_dltObj( new Subrutinas().getBDConexion(request), registro );
            } catch (StExcepcion ex) {
                resultado = "ERROR";
                ActionMessages errores = new ActionMessages();
                errores.add("error", new ActionMessage( "errors.detail", ex.getMessage() ));
                saveErrors(request,errores);
            }
        }
        ///////////////////////////////////////////
        return resultado;
    }
    private String chgRcd(HttpServletRequest request, ActionForm  form ) {
        String resultado = "OK";
        ///////////////////////////////////////////
        EsRCD_AF pantalla = (EsRCD_AF)form;
        EsBean registro = null;
        if (pantalla != null ) {
            registro = new EsBean();
            
            pantalla.copyTo( registro );
            
            EsAccesoBaseDatos db = new EsAccesoBaseDatos();
            try {
                db.es_chgObj( new Subrutinas().getBDConexion(request), registro );
            } catch (StExcepcion ex) {
                resultado = "ERROR";
                ActionMessages errores = new ActionMessages();
                errores.add("error", new ActionMessage( "errors.detail", ex.getMessage() ));
                saveErrors(request,errores);
            }
        }
        ///////////////////////////////////////////
        return resultado;
    }
}
