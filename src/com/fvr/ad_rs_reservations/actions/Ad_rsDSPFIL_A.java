package com.fvr.ad_rs_reservations.actions;


import com.fvr._comun.ConfigPantalla;
import com.fvr._comun.StExcepcion;
import com.fvr._comun.Subrutinas;
import com.fvr.ad_rs_reservations.bean.Ad_rsBean;
import com.fvr.ad_rs_reservations.bean.Ad_rsBeanFiltro;
import com.fvr.ad_rs_reservations.db.Ad_rsAccesoBaseDatos;
import com.fvr.ad_rs_reservations.forms.Ad_rsRCD_AF;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class Ad_rsDSPFIL_A extends org.apache.struts.action.Action {
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        String resultado = "OK";
        
        Ad_rsRCD_AF pantalla = (Ad_rsRCD_AF)form;

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
        // Despacho de la acción:
        /////////////////////////
        if ( opcion == null )
            resultado = this.cargarPantalla( request, pantalla );
        else {
            if ( opcion.trim().length()==0 ) {
                resultado = this.cargarPantalla( request, pantalla );
            } else {
                if ( opcion.trim().substring(0,4).equalsIgnoreCase("Edit") ) {
                    resultado = opcion_Edit(request,form);
                } else if ( opcion.trim().equalsIgnoreCase("Nuevo") ) {
                    resultado = opcion_Nuevo(request,form);
                } else if ( opcion.trim().equalsIgnoreCase("Filtrar") ) {
                    pantalla.setFilaInicioGrid(1);
                    resultado = this.cargarPantalla( request, pantalla );
                } else if ( opcion.trim().equalsIgnoreCase("AvPg") ) {
                    resultado = opcion_AvPg(request,form);
                } else if ( opcion.trim().equalsIgnoreCase("RtPg") ) {
                    resultado = opcion_RtPg(request,form);
                } else if ( opcion.trim().equalsIgnoreCase("MarcarTodo") ) {
                    resultado = opcion_Selec_Marcar(request,form);
                } else if ( opcion.trim().equalsIgnoreCase("DesMarcarTodo") ) {
                    pantalla.setClavesMarcadas( null );
                    resultado = this.cargarPantalla( request, pantalla );
                } else if ( opcion.trim().equalsIgnoreCase("Borrar") ) {
                    resultado = opcion_Selec_Borrar( request, pantalla );
                } else if ( opcion.trim().equalsIgnoreCase("Exportar") ) {
                    resultado = this.opcion_Exportar( request, pantalla );
                } else if ( opcion.trim().equalsIgnoreCase("Grabar") ) {
                    persistirPosiblesCambios(request,form);
                    resultado = this.cargarPantalla( request, pantalla );
                ///////////////////////////////////////
                } else if ( opcion.trim().length() > 10 && opcion.trim().substring(0,10).equalsIgnoreCase("colectivo_") ) {
                    resultado = opcion_Selec_ChgColectivo( request, form );
                ///////////////////////////////////////
                } else {
                    resultado = this.cargarPantalla( request, pantalla );
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
    
    private String cargarPantalla( HttpServletRequest request, ActionForm  form ) {
        String resultado = "OK";
        ///////////////////////////////////////////
        Ad_rsRCD_AF pantalla = (Ad_rsRCD_AF)form;
        ///////////////////////////////////////////
        aplicarParametrosDeEntrada( request, form );
        inicializarPantalla( request, pantalla );
        ///////////////////////////////////////////
        Ad_rsAccesoBaseDatos db = new Ad_rsAccesoBaseDatos();
        ///////////////////////////////////////////
        ConfigPantalla cfg = new ConfigPantalla();
        cfg.setNombrePantalla( (this.getClass().toString()).substring((this.getClass().toString()).lastIndexOf(".")+1) );
        cfg.setTituloPantalla("reservations");
        if ( pantalla.getFilasGrid() > 0 )      // Para primera llamada...
            cfg.setFilasGrid( pantalla.getFilasGrid() );
        if ( pantalla.getFilaInicioGrid() > 0 ) // Para primera llamada...
            cfg.setFilaInicioGrid( pantalla.getFilaInicioGrid() );
        //////////////////////////////////////
        // ¿Es petición de exportación?
        if ( pantalla.getOpcionPantalla() != null && pantalla.getOpcionPantalla().trim().equalsIgnoreCase("Exportar") ) {
            cfg.setExportar(true);
            // Determinación de nombre del archivo de exportación
            // (Debe quedar guardado en sesión, dentro del cfg destinado a pantalla)
            String nombre =
            		this.getServlet().getServletContext().getRealPath( "/" ) + 
                    "x" +
                    (this.getClass().toString()).substring((this.getClass().toString()).lastIndexOf(".")+1) +
                    "_" +
                    pantalla.getLogon_USR().trim() +
                    "_" +
                    Subrutinas.getFecha_aammdd() + 
                    Subrutinas.getHora_HHMMSS() +
                    ".xls";
            cfg.setTituloPantalla( nombre );  // Nombre del archivo de exportación sin extensión.
        }
        //////////////////////////////////////
        try {
			pantalla.setFilasGrid( cfg.getFilasGrid() );
			pantalla.setFilaInicioGrid( cfg.getFilaInicioGrid() );
            pantalla.setGrid( db.ad_rs_getSeq(
                    new Subrutinas().getBDConexion(request)
                    , cfg
                    , pantalla.getAd_rs_filtro()
                    , false
                    )
                    );
            pantalla.setFilasTotales( cfg.getFilasTotales() );
        } catch (StExcepcion ex) {
            resultado = "ERROR";
            ActionMessages errores = new ActionMessages();
            errores.add("error", new ActionMessage( "errors.detail", ex.getMessage() ));
            saveErrors(request,errores);
        }
        ///////////////////////////////////////////
        // Hace falta para el paginado:
        request.getSession(true).setAttribute( "cfgPantalla", cfg );
        ///////////////////////////////////////////
        return resultado;
    }

    private void sincroSesion( HttpServletRequest request, ActionForm  form ) {
        ///////////////////////////////////////////
        Ad_rsRCD_AF pantalla = (Ad_rsRCD_AF)form;
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
    
    private String opcion_AvPg( HttpServletRequest request, ActionForm  form ) {
        String resultado = "OK";
        ///////////////////////////////////////////
        Ad_rsRCD_AF pantalla = (Ad_rsRCD_AF)form;
        ///////////////////////////////////////////
        pantalla.setFilaInicioGrid( pantalla.getFilaInicioGrid() + pantalla.getFilasGrid() );
        resultado = this.cargarPantalla( request, pantalla );
        ///////////////////////////////////////////
        return resultado;
    }
    
    private String opcion_RtPg( HttpServletRequest request, ActionForm  form ) {
        String resultado = "OK";
        ///////////////////////////////////////////
        Ad_rsRCD_AF pantalla = (Ad_rsRCD_AF)form;
        ///////////////////////////////////////////
        int f = pantalla.getFilaInicioGrid() - pantalla.getFilasGrid();
        f = (f<1)?1:f;
        pantalla.setFilaInicioGrid( f );
        resultado = this.cargarPantalla( request, pantalla );
        ///////////////////////////////////////////
        return resultado;
    }
    
    private String opcion_Selec_Marcar( HttpServletRequest request, ActionForm  form ) {
        String resultado = "OK";
        ///////////////////////////////////////////
        Ad_rsRCD_AF pantalla = (Ad_rsRCD_AF)form;
        /////////////////////////////////////////////////
        resultado = this.cargarPantalla( request, form );
        if ( ! resultado.equalsIgnoreCase("OK") )
            return resultado;
        /////////////////////////////////////////////////
        if ( pantalla.getGrid() != null  ) {
            pantalla.setClavesMarcadas( null );
            pantalla.setClavesMarcadas( new String[pantalla.getGrid().length] );
            for (int i=0;i<pantalla.getGrid().length; i++) {
                Ad_rsBean reg = (Ad_rsBean) pantalla.getGrid()[i];
                // Guardar la CLAVE ÚNICA DEL SUBARCHIVO !!!
                pantalla.getClavesMarcadas()[i] = 
		reg.getAd_rs_reservation_id();
                // Ejemplo clave múltiple:
                // pantalla.getClavesMarcadas()[i] =
                //         reg.getTf_AFACST() + "^" +
                //         reg.getTf_AFAFCD() + "^" +
                //         reg.getTf_AFAADA() + "^" +
                //         reg.getTf_AFABDA() + "^" +
                //         reg.getTf_AFAECD() + "^" +
                //         reg.getTf_AFAICD()
                //         ;
            }
        }
        /////////////////////////////////////////////////
        return resultado;
    }
    
    private String opcion_Edit( HttpServletRequest request, ActionForm  form ) {
        String resultado = "OK";
        ///////////////////////////////////////////
        Ad_rsRCD_AF pantalla = (Ad_rsRCD_AF)form;
        String opcion = pantalla.getOpcionPantalla();
        opcion = opcion.trim().substring(4);
        ///////////////////////////////////////////
        // Rescato la clave concatenada:
        Ad_rsBean key = new Ad_rsBean();
        String k;
	k = opcion; key.setAd_rs_reservation_id( k );
        ///////////////////////////////////
        request.setAttribute("key_Ad_rs",key);
        ///////////////////////////////////
        resultado = "EDTRCD";
        ///////////////////////////////////
        return resultado;
    }
    
    private String opcion_Nuevo( HttpServletRequest request, ActionForm  form ) {
        String resultado = "OK";
        ///////////////////////////////////////////
        resultado = "ADDRCD";
        ///////////////////////////////////////////
        return resultado;
    }
    
    private String opcion_Exportar( HttpServletRequest request, ActionForm  form ) {
        String resultado = "OK";
        ///////////////////////////////////////////
        Ad_rsRCD_AF pantalla = (Ad_rsRCD_AF)form;
        ///////////////////////////////////////////
        // Ejecutar en modo "exportación":
        resultado = this.cargarPantalla( request, pantalla );
        if (resultado.equalsIgnoreCase("OK")) {
            
            ConfigPantalla cfg = (ConfigPantalla) request.getSession(true).getAttribute( "cfgPantalla" );
            String NombreArchivo = "x";
            if ( cfg != null && cfg.getTituloPantalla() != null && cfg.getTituloPantalla().trim().length() > 0 ) {
                NombreArchivo = cfg.getTituloPantalla().trim();
            	int i = NombreArchivo.lastIndexOf(java.io.File.separator);
            	if ( i > -1 ) {
            		try { 
            			NombreArchivo = NombreArchivo.substring(i+1);
    				} catch (Exception e) {;}
            	}
            }
                        
            // Recargar la pantalla:
            pantalla.setOpcionPantalla("RecargarGrid");
            resultado = this.cargarPantalla( request, pantalla );
            // Enlace al resultado:
            ActionMessages errores = new ActionMessages();
            errores.add("error", new ActionMessage( "errors.detail", "<a href='" + NombreArchivo + "' target='pepito'>Enlace a reservations...</a>" ));
            saveErrors(request,errores);

        }
        ///////////////////////////////////////////
        return resultado;
    }
    
    private String opcion_Selec_Borrar( HttpServletRequest request, ActionForm  form ) {
        String resultado = "OK";
        ///////////////////////////////////////////
        Ad_rsRCD_AF pantalla = (Ad_rsRCD_AF)form;
        ActionMessages errores = new ActionMessages();
        ///////////////////////////////////////////
        // Aborta si no hay filas marcadas:
        int nfilas = (pantalla.getClavesMarcadas()!=null)?pantalla.getClavesMarcadas().length:0;
        if (nfilas<1) return cargarPantalla(request,form);
        ///////////////////////////////////////////
        Ad_rsAccesoBaseDatos db = new Ad_rsAccesoBaseDatos();
        String opcion = null;
        for (int j=0;j<nfilas;j++) {
            opcion = pantalla.getClavesMarcadas()[j];
            //////////////////////////////
            // Rescato la clave concatenada:
            Ad_rsBean key = new Ad_rsBean();
            String k;
	k = opcion; key.setAd_rs_reservation_id( k );
            ///////////////////////////////////
            try {
                db.ad_rs_dltObj( new Subrutinas().getBDConexion(request), key );
            } catch (StExcepcion ex) {
                errores.add("error", new ActionMessage( "errors.detail", ex.getMessage() ));
            }
            //////////////////////////////
        }
//        errores.add("error", new ActionMessage( "errors.detail", nfilas + " registros procesados." ));
        if (errores.size()>0) saveErrors(request,errores);
        pantalla.setClavesMarcadas(null);
        ///////////////////////////////////////////
        resultado = cargarPantalla(request,form);
        return resultado;
    }

    private String opcion_Selec_ChgColectivo(HttpServletRequest request, ActionForm form) {
        String resultado = "OK";
        ///////////////////////////////////////////
        Ad_rsRCD_AF pantalla = (Ad_rsRCD_AF)form;
        ActionMessages errores = new ActionMessages();
        String accion = pantalla.getOpcionPantalla();
        ///////////////////////////////////////////
        // Aborta si no hay filas marcadas:
        int nfilas = (pantalla.getClavesMarcadas()!=null)?pantalla.getClavesMarcadas().length:0;
        if (nfilas<1) return cargarPantalla(request,form);
        ///////////////////////////////////////////
        // Actualiza la extensión (no el original de Distrialia)
        com.fvr.FuentesDeDatos.BDConexion database = new Subrutinas().getBDConexion(request);
        Ad_rsAccesoBaseDatos db = new Ad_rsAccesoBaseDatos();
        Ad_rsBean reg = null;
        String opcion = null;
        for (int j=0;j<nfilas;j++) {
            opcion = pantalla.getClavesMarcadas()[j];
            //////////////////////////////
            // Rescato la clave concatenada:
            Ad_rsBean key = new Ad_rsBean();
            String k;
	k = opcion; key.setAd_rs_reservation_id( k );
            ///////////////////////////////////
            try {
                reg = db.ad_rs_getRcd( database, key );
                if ( reg != null) {
                    ///////////////////////////////////
                    // Aplico el nuevo valor al registro:
		if ( accion.trim().equalsIgnoreCase("colectivo_sincro") ) { reg.setAd_rs_sincro( pantalla.getAd_rs_sincro() ); }
		if ( accion.trim().equalsIgnoreCase("colectivo_mark") ) { reg.setAd_rs_mark( pantalla.getAd_rs_mark() ); }
		if ( accion.trim().equalsIgnoreCase("colectivo_is_deleted") ) { reg.setAd_rs_is_deleted( pantalla.getAd_rs_is_deleted() ); }
		if ( accion.trim().equalsIgnoreCase("colectivo_author") ) { reg.setAd_rs_author( pantalla.getAd_rs_author() ); }
		if ( accion.trim().equalsIgnoreCase("colectivo_location_id") ) { reg.setAd_rs_location_id( pantalla.getAd_rs_location_id() ); }
		if ( accion.trim().equalsIgnoreCase("colectivo_user_id") ) { reg.setAd_rs_user_id( pantalla.getAd_rs_user_id() ); }
		if ( accion.trim().equalsIgnoreCase("colectivo_product_id") ) { reg.setAd_rs_product_id( pantalla.getAd_rs_product_id() ); }
		if ( accion.trim().equalsIgnoreCase("colectivo_quantity") ) { reg.setAd_rs_quantity( pantalla.getAd_rs_quantity() ); }
		if ( accion.trim().equalsIgnoreCase("colectivo_product_id2") ) { reg.setAd_rs_product_id2( pantalla.getAd_rs_product_id2() ); }
		if ( accion.trim().equalsIgnoreCase("colectivo_product_id3") ) { reg.setAd_rs_product_id3( pantalla.getAd_rs_product_id3() ); }
		if ( accion.trim().equalsIgnoreCase("colectivo_amount") ) { reg.setAd_rs_amount( pantalla.getAd_rs_amount() ); }
		if ( accion.trim().equalsIgnoreCase("colectivo_currency") ) { reg.setAd_rs_currency( pantalla.getAd_rs_currency() ); }
		if ( accion.trim().equalsIgnoreCase("colectivo_phone") ) { reg.setAd_rs_phone( pantalla.getAd_rs_phone() ); }
		if ( accion.trim().equalsIgnoreCase("colectivo_pay_status") ) { reg.setAd_rs_pay_status( pantalla.getAd_rs_pay_status() ); }
		if ( accion.trim().equalsIgnoreCase("colectivo_start_date") ) { reg.setAd_rs_start_date( pantalla.getAd_rs_start_date() ); }
		if ( accion.trim().equalsIgnoreCase("colectivo_start_time") ) { reg.setAd_rs_start_time( pantalla.getAd_rs_start_time() ); }
		if ( accion.trim().equalsIgnoreCase("colectivo_duration_minutes") ) { reg.setAd_rs_duration_minutes( pantalla.getAd_rs_duration_minutes() ); }
		if ( accion.trim().equalsIgnoreCase("colectivo_places") ) { reg.setAd_rs_places( pantalla.getAd_rs_places() ); }
		if ( accion.trim().equalsIgnoreCase("colectivo_coupon_id") ) { reg.setAd_rs_coupon_id( pantalla.getAd_rs_coupon_id() ); }
		if ( accion.trim().equalsIgnoreCase("colectivo_executed_at") ) { reg.setAd_rs_executed_at( pantalla.getAd_rs_executed_at() ); }
		if ( accion.trim().equalsIgnoreCase("colectivo_note") ) { reg.setAd_rs_note( pantalla.getAd_rs_note() ); }
		if ( accion.trim().equalsIgnoreCase("colectivo_comment") ) { reg.setAd_rs_comment( pantalla.getAd_rs_comment() ); }
		if ( accion.trim().equalsIgnoreCase("colectivo_json") ) { reg.setAd_rs_json( pantalla.getAd_rs_json() ); }
                    ///////////////////////////////////
                    db.ad_rs_chgObj( database, reg );
                }
            } catch (StExcepcion ex) {
                errores.add("error", new ActionMessage( "errors.detail", ex.getMessage() ));
            }
            //////////////////////////////
        }
//        errores.add("error", new ActionMessage( "errors.detail", nfilas + " registros procesados." ));
        if (errores.size()>0) saveErrors(request,errores);
        pantalla.setClavesMarcadas(null);
        ///////////////////////////////////////////
        resultado = cargarPantalla(request,form);
        return resultado;
    }
    
    private void inicializarPantalla( HttpServletRequest request, ActionForm  form ) {
        Ad_rsRCD_AF pantalla = (Ad_rsRCD_AF)form;
        //////////////////////////////////
        
        // POP SuperFiltros en SESSION:
        if ( request.getSession().getAttribute("ad_rsFilaInicioGrid") != null && pantalla.getFilaInicioGrid() == 0 )
            pantalla.setFilaInicioGrid( ((java.lang.Integer) request.getSession().getAttribute("ad_rsFilaInicioGrid")).intValue() );
        
        if ( request.getSession().getAttribute("FilasGrid") != null && pantalla.getFilasGrid() == 0 )
            pantalla.setFilasGrid( ((java.lang.Integer) request.getSession().getAttribute("FilasGrid")).intValue() );
        
        // POP Filtros en SESSION:
        // Quién manda más, la pantalla o el filtro?
        Ad_rsBeanFiltro filtroOld = (Ad_rsBeanFiltro) request.getSession().getAttribute("ad_rsFiltro");
        if ( filtroOld != null ) { pantalla.setAd_rs_filtro( (Ad_rsBeanFiltro) filtroOld.coalesce(pantalla.getAd_rs_filtro(),filtroOld) ); }
        
        // Inicializar filtros:
        
        // PUSH SuperFiltros en SESSION:
        request.getSession().setAttribute("ad_rsFilaInicioGrid", new java.lang.Integer( pantalla.getFilaInicioGrid()) );
        request.getSession().setAttribute("FilasGrid", new java.lang.Integer( pantalla.getFilasGrid()) );
        
        // PUSH Filtros en SESSION:
        request.getSession().setAttribute("ad_rsFiltro",pantalla.getAd_rs_filtro());
        
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

    private void aplicarParametrosDeEntrada(HttpServletRequest request, ActionForm form ) {
        Ad_rsRCD_AF pantalla = (Ad_rsRCD_AF)form;
        //////////////////////////////////
        // Restrictores y mapeos con destino campos de pantalla:
        Ad_rsBean rcd_Ad_rs = (Ad_rsBean) request.getAttribute("rcd_Ad_rs");
        if ( rcd_Ad_rs != null ) {
            // Ha sido llamado desde otro programa:
            ;
        } else {
            // Recarga de sí misma...verificar si valores legales en campos importantes u obligatorios:
            // if ( pantalla.getAd_rs_rst0() == null || pantalla.getAd_rs_rst0().trim().length() < 1 ) {
            //     pantalla.setAd_rs_rst0( "ValorDeProteccion" );
            // }
            ;
        }
        ///////////////////////////////////////////
        // Tratar parámetros extra recibidos:

        ///////////////////////////////////////////
    }
    
    private int persistirPosiblesCambios(HttpServletRequest request, ActionForm  form ) {
        int resultado = 0;
        Ad_rsRCD_AF pantalla = (Ad_rsRCD_AF)form;
        //////////////////////////////////
        if ( pantalla != null && pantalla.getGrid() != null ) {
            Ad_rsAccesoBaseDatos db = new Ad_rsAccesoBaseDatos();
            Ad_rsBean key = null;
            Ad_rsBean reg = null;
            // Se regraba cada fila del grid marcada en campo "chg":
            for (int i=0;i<pantalla.getGrid().length;i++) {
                key = ((Ad_rsBean)(pantalla.getGrid()[i]));
                if ( key.getChg() != null && key.getChg().trim().length() > 0 ) {
                    try {
                        // Cargo clave entidad:
                        String opcion = key.getChg().trim();
                        String k;
	k = opcion; key.setAd_rs_reservation_id( k );
                        reg = db.ad_rs_getRcd( new Subrutinas().getBDConexion(request), key );
                        if (reg!=null) {
                            // Campo(s) a reescribir:
                            // reg.setAd_rs_Campo( ((Ad_rsBean)(pantalla.getGrid()[i])).getAd_rs_Campo() );
                            // ...meter a manita cada campo...y activar este chgObj:
                            // db.ad_rs_chgObj(new Subrutinas().getBDConexion(request),reg);
                            resultado++;
                            }
                    } catch (StExcepcion ex) {;}
                }
            }
        }
        //////////////////////////////////
        return resultado;
    }
}
