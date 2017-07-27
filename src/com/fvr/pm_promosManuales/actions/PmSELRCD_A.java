package com.fvr.pm_promosManuales.actions;


import com.fvr._comun.ConfigPantalla;
import com.fvr._comun.StExcepcion;
import com.fvr._comun.Subrutinas;
import com.fvr.pm_promosManuales.bean.PmBeanFiltro;
import com.fvr.pm_promosManuales.db.PmAccesoBaseDatos;
import com.fvr.pm_promosManuales.forms.PmRCD_AF;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class PmSELRCD_A extends org.apache.struts.action.Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String resultado = "OK";

        PmRCD_AF pantalla = (PmRCD_AF)form;

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
        // Seguridad de acceso al programa:
        /////////////////////////
        String proceso = (this.getClass().toString()).substring((this.getClass().toString()).lastIndexOf(".") + 1);
        String usuario = pantalla.getLogon_USR();
        String claveOp = pantalla.getLogon_HSH();
        Subrutinas subrutinas = new Subrutinas();
        if ( ! subrutinas.controlAcceso( subrutinas.getBDConexion(request), usuario, proceso, claveOp ) ) {
            resultado = "ERROR";
            ActionMessages errores = new ActionMessages();
//            errores.add("error", new ActionMessage( "errors.detail", "'" + usuario + "' no autorizado a '" + proceso + "'." ));
            errores.add("error", new ActionMessage( "errors.detail", "Se ha producido un error de seguridad." ));
            saveErrors(request, errores);

            if (isVersionAngular) { Subrutinas.returnActionVersionAngular(request, response, this, false, null); return null; } // No navega con struts

            return mapping.findForward(resultado);
        }

        /////////////////////////
        // Despacho de la acción:
        /////////////////////////
        if (opcion == null) {
            resultado = this.cargarPantalla(request, pantalla);
        } else {
            if (opcion.trim().length() == 0) {
                resultado = this.cargarPantalla(request, pantalla);
            } else {
                if (opcion.trim().equalsIgnoreCase("Filtrar")) {
                    pantalla.setFilaInicioGrid(1);
                    resultado = this.cargarPantalla(request, pantalla);
                } else if (opcion.trim().equalsIgnoreCase("AvPg")) {
                    resultado = opcion_AvPg(request, form);
                } else if (opcion.trim().equalsIgnoreCase("RtPg")) {
                    resultado = opcion_RtPg(request, form);
                } else {
                    resultado = this.cargarPantalla(request, pantalla);
                }
            }
        }

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

    private String cargarPantalla(HttpServletRequest request, ActionForm form) {
        String resultado = "OK";
        ///////////////////////////////////////////
        PmRCD_AF pantalla = (PmRCD_AF) form;
        ///////////////////////////////////////////
        inicializarPantalla(request, pantalla);
        ///////////////////////////////////////////
        PmAccesoBaseDatos db = new PmAccesoBaseDatos();

        ConfigPantalla cfg = (ConfigPantalla) request.getSession().getAttribute("cfgPantalla");
        if ( cfg == null ) cfg = new ConfigPantalla();

        if ( pantalla.getFilasGrid() > 0 )      // Para primera llamada...
            cfg.setFilasGrid( pantalla.getFilasGrid() );
        if ( pantalla.getFilaInicioGrid() > 0 ) // Para primera llamada...
            cfg.setFilaInicioGrid( pantalla.getFilaInicioGrid() );

        pantalla.setFilasGrid( cfg.getFilasGrid() );
        pantalla.setFilaInicioGrid( cfg.getFilaInicioGrid() );

        try {
            pantalla.setGrid( db.pm_getSeq(
                    new Subrutinas().getBDConexion(request)
                    , cfg
                    , pantalla.getPm_filtro()
                    
                    )
                    );
        } catch (StExcepcion ex) {
            resultado = "ERROR";
            ActionMessages errores = new ActionMessages();
            errores.add("error", new ActionMessage("errors.detail", ex.getMessage()));
            saveErrors(request, errores);
        }
        ///////////////////////////////////////////
        // Hace falta para el paginado:
        request.getSession(true).setAttribute("cfgPantalla", cfg);
        ///////////////////////////////////////////
        return resultado;
    }

    private void sincroSesion( HttpServletRequest request, ActionForm  form ) {
        ///////////////////////////////////////////
        PmRCD_AF pantalla = (PmRCD_AF)form;
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

    private String opcion_AvPg(HttpServletRequest request, ActionForm form) {
        String resultado = "OK";
        ///////////////////////////////////////////
        PmRCD_AF pantalla = (PmRCD_AF) form;
        ///////////////////////////////////////////
        pantalla.setFilaInicioGrid(pantalla.getFilaInicioGrid() + pantalla.getFilasGrid());
        resultado = this.cargarPantalla(request, pantalla);
        ///////////////////////////////////////////
        return resultado;
    }

    private String opcion_RtPg(HttpServletRequest request, ActionForm form) {
        String resultado = "OK";
        ///////////////////////////////////////////
        PmRCD_AF pantalla = (PmRCD_AF) form;
        ///////////////////////////////////////////
        int f = pantalla.getFilaInicioGrid() - pantalla.getFilasGrid();
        f = (f < 1) ? 1 : f;
        pantalla.setFilaInicioGrid(f);
        resultado = this.cargarPantalla(request, pantalla);
        ///////////////////////////////////////////
        return resultado;
    }

    private void inicializarPantalla(HttpServletRequest request, ActionForm form) {
        PmRCD_AF pantalla = (PmRCD_AF) form;
        //////////////////////////////////

        // POP SuperFiltros en SESSION:
        if (request.getSession().getAttribute("arFilaInicioGrid") != null && pantalla.getFilaInicioGrid() == 0) {
            pantalla.setFilaInicioGrid(((java.lang.Integer) request.getSession().getAttribute("arFilaInicioGrid")).intValue());
        }

        if (request.getSession().getAttribute("FilasGrid") != null && pantalla.getFilasGrid() == 0) {
            pantalla.setFilasGrid(((java.lang.Integer) request.getSession().getAttribute("FilasGrid")).intValue());
        }

        // POP Filtros en SESSION:
        // Quién manda más, la pantalla o el filtro?
        PmBeanFiltro filtroOld = (PmBeanFiltro) request.getSession().getAttribute("pmFiltro");
        if ( filtroOld != null ) { pantalla.setPm_filtro( (PmBeanFiltro) filtroOld.coalesce(pantalla.getPm_filtro(),filtroOld) ); }

        // Inicializar filtros:
        
        // PUSH SuperFiltros en SESSION:
        request.getSession().setAttribute("pmFilaInicioGrid", new java.lang.Integer( pantalla.getFilaInicioGrid()) );
        request.getSession().setAttribute("FilasGrid", new java.lang.Integer( pantalla.getFilasGrid()) );
        
        // PUSH Filtros en SESSION:
        request.getSession().setAttribute("pmFiltro",pantalla.getPm_filtro());
        
        // Mover parámetros (y sus virtuales y tal) al control de la pantalla:
        
        /////////////////////
        
        /////////////////////////////////////////////////
        if (  request.getSession().getAttribute("lstLineasPantalla")==null )  {
            // Bean en contexto de session
            request.getSession().setAttribute("lstLineasPantalla", Subrutinas.cargarCombo_FilasGrid() );
        }

        
        /////////////////////////////////////////////////
        
        //////////////////////////////////
    }
}
