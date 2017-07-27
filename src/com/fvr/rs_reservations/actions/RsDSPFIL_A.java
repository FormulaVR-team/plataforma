﻿package com.fvr.rs_reservations.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.fvr.FuentesDeDatos.BDConexion;
import com.fvr._comun.ConfigPantalla;
import com.fvr._comun.StExcepcion;
import com.fvr._comun.Subrutinas;
import com.fvr.rs_reservations.bean.RsBean;
import com.fvr.rs_reservations.bean.RsBeanFiltro;
import com.fvr.rs_reservations.db.RsAccesoBaseDatos;
import com.fvr.rs_reservations.forms.RsRCD_AF;
import com.fvr.us_users.bean.UsBean;

import net.sf.json.JSONObject;

public class RsDSPFIL_A extends org.apache.struts.action.Action {
    
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
        RsRCD_AF pantalla = (RsRCD_AF)form;
        ///////////////////////////////////////////
        aplicarParametrosDeEntrada( request, form );
        inicializarPantalla( request, pantalla );
        ///////////////////////////////////////////
        RsAccesoBaseDatos db = new RsAccesoBaseDatos();
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
            pantalla.setGrid( db.rs_getSeq(
                    new Subrutinas().getBDConexion(request)
                    , cfg
                    , pantalla.getRs_filtro()
                    , false	// isSoloPagadas
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
    
    private String opcion_AvPg( HttpServletRequest request, ActionForm  form ) {
        String resultado = "OK";
        ///////////////////////////////////////////
        RsRCD_AF pantalla = (RsRCD_AF)form;
        ///////////////////////////////////////////
        pantalla.setFilaInicioGrid( pantalla.getFilaInicioGrid() + pantalla.getFilasGrid() );
        resultado = this.cargarPantalla( request, pantalla );
        ///////////////////////////////////////////
        return resultado;
    }
    
    private String opcion_RtPg( HttpServletRequest request, ActionForm  form ) {
        String resultado = "OK";
        ///////////////////////////////////////////
        RsRCD_AF pantalla = (RsRCD_AF)form;
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
        RsRCD_AF pantalla = (RsRCD_AF)form;
        /////////////////////////////////////////////////
        resultado = this.cargarPantalla( request, form );
        if ( ! resultado.equalsIgnoreCase("OK") )
            return resultado;
        /////////////////////////////////////////////////
        if ( pantalla.getGrid() != null  ) {
            pantalla.setClavesMarcadas( null );
            pantalla.setClavesMarcadas( new String[pantalla.getGrid().length] );
            for (int i=0;i<pantalla.getGrid().length; i++) {
                RsBean reg = (RsBean) pantalla.getGrid()[i];
                // Guardar la CLAVE ÚNICA DEL SUBARCHIVO !!!
                pantalla.getClavesMarcadas()[i] = 
		reg.getRs_reservation_id();
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
        RsRCD_AF pantalla = (RsRCD_AF)form;
        String opcion = pantalla.getOpcionPantalla();
        opcion = opcion.trim().substring(4);
        ///////////////////////////////////////////
        // Rescato la clave concatenada:
        RsBean key = new RsBean();
        String k;
	k = opcion; key.setRs_reservation_id( k );
        ///////////////////////////////////
        request.setAttribute("key_Rs",key);
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
        RsRCD_AF pantalla = (RsRCD_AF)form;
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
        RsRCD_AF pantalla = (RsRCD_AF)form;
        ActionMessages errores = new ActionMessages();
        ///////////////////////////////////////////
        // Aborta si no hay filas marcadas:
        int nfilas = (pantalla.getClavesMarcadas()!=null)?pantalla.getClavesMarcadas().length:0;
        if (nfilas<1) return cargarPantalla(request,form);
        ///////////////////////////////////////////
        RsAccesoBaseDatos db = new RsAccesoBaseDatos();
        String opcion = null;
        for (int j=0;j<nfilas;j++) {
            opcion = pantalla.getClavesMarcadas()[j];
            //////////////////////////////
            // Rescato la clave concatenada:
            RsBean key = new RsBean();
            String k;
	k = opcion; key.setRs_reservation_id( k );
            ///////////////////////////////////
            try {
                db.rs_dltObj( new Subrutinas().getBDConexion(request), key );
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
        RsRCD_AF pantalla = (RsRCD_AF)form;
        ActionMessages errores = new ActionMessages();
        String accion = pantalla.getOpcionPantalla();
        ///////////////////////////////////////////
        // Aborta si no hay filas marcadas:
        int nfilas = (pantalla.getClavesMarcadas()!=null)?pantalla.getClavesMarcadas().length:0;
        if (nfilas<1) return cargarPantalla(request,form);
        ///////////////////////////////////////////
        // Actualiza la extensión (no el original de Distrialia)
        com.fvr.FuentesDeDatos.BDConexion database = new Subrutinas().getBDConexion(request);
        RsAccesoBaseDatos db = new RsAccesoBaseDatos();
        RsBean reg = null;
        String opcion = null;
        for (int j=0;j<nfilas;j++) {
            opcion = pantalla.getClavesMarcadas()[j];
            //////////////////////////////
            // Rescato la clave concatenada:
            RsBean key = new RsBean();
            String k;
	k = opcion; key.setRs_reservation_id( k );
            ///////////////////////////////////
            try {
                reg = db.rs_getRcd( database, key );
                if ( reg != null) {
                    ///////////////////////////////////
                    // Aplico el nuevo valor al registro:
		if ( accion.trim().equalsIgnoreCase("colectivo_sincro") ) { reg.setRs_sincro( pantalla.getRs_sincro() ); }
		if ( accion.trim().equalsIgnoreCase("colectivo_mark") ) { reg.setRs_mark( pantalla.getRs_mark() ); }
		if ( accion.trim().equalsIgnoreCase("colectivo_is_deleted") ) { reg.setRs_is_deleted( pantalla.getRs_is_deleted() ); }
		if ( accion.trim().equalsIgnoreCase("colectivo_author") ) { reg.setRs_author( pantalla.getRs_author() ); }
		if ( accion.trim().equalsIgnoreCase("colectivo_location_id") ) { reg.setRs_location_id( pantalla.getRs_location_id() ); }
		if ( accion.trim().equalsIgnoreCase("colectivo_user_id") ) { reg.setRs_user_id( pantalla.getRs_user_id() ); }
		if ( accion.trim().equalsIgnoreCase("colectivo_product_id") ) { reg.setRs_product_id( pantalla.getRs_product_id() ); }
		if ( accion.trim().equalsIgnoreCase("colectivo_quantity") ) { reg.setRs_quantity( pantalla.getRs_quantity() ); }
		if ( accion.trim().equalsIgnoreCase("colectivo_product_id2") ) { reg.setRs_product_id2( pantalla.getRs_product_id2() ); }
		if ( accion.trim().equalsIgnoreCase("colectivo_product_id3") ) { reg.setRs_product_id3( pantalla.getRs_product_id3() ); }
		if ( accion.trim().equalsIgnoreCase("colectivo_amount") ) { reg.setRs_amount( pantalla.getRs_amount() ); }
		if ( accion.trim().equalsIgnoreCase("colectivo_currency") ) { reg.setRs_currency( pantalla.getRs_currency() ); }
		if ( accion.trim().equalsIgnoreCase("colectivo_phone") ) { reg.setRs_phone( pantalla.getRs_phone() ); }
		if ( accion.trim().equalsIgnoreCase("colectivo_pay_status") ) { reg.setRs_pay_status( pantalla.getRs_pay_status() ); }
		if ( accion.trim().equalsIgnoreCase("colectivo_start_date") ) { reg.setRs_start_date( pantalla.getRs_start_date() ); }
		if ( accion.trim().equalsIgnoreCase("colectivo_start_time") ) { reg.setRs_start_time( pantalla.getRs_start_time() ); }
		if ( accion.trim().equalsIgnoreCase("colectivo_duration_minutes") ) { reg.setRs_duration_minutes( pantalla.getRs_duration_minutes() ); }
		if ( accion.trim().equalsIgnoreCase("colectivo_places") ) { reg.setRs_places( pantalla.getRs_places() ); }
		if ( accion.trim().equalsIgnoreCase("colectivo_coupon_id") ) { reg.setRs_coupon_id( pantalla.getRs_coupon_id() ); }
		if ( accion.trim().equalsIgnoreCase("colectivo_executed_at") ) { reg.setRs_executed_at( pantalla.getRs_executed_at() ); }
		if ( accion.trim().equalsIgnoreCase("colectivo_note") ) { reg.setRs_note( pantalla.getRs_note() ); }
		if ( accion.trim().equalsIgnoreCase("colectivo_comment") ) { reg.setRs_comment( pantalla.getRs_comment() ); }
		if ( accion.trim().equalsIgnoreCase("colectivo_json") ) { reg.setRs_json( pantalla.getRs_json() ); }
                    ///////////////////////////////////
                    db.rs_chgObj( database, reg );
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
        RsRCD_AF pantalla = (RsRCD_AF)form;
        BDConexion dataBase = new Subrutinas().getBDConexion(request);
        //////////////////////////////////
        
        // POP SuperFiltros en SESSION:
        if ( request.getSession().getAttribute("rsFilaInicioGrid") != null && pantalla.getFilaInicioGrid() == 0 )
            pantalla.setFilaInicioGrid( ((java.lang.Integer) request.getSession().getAttribute("rsFilaInicioGrid")).intValue() );

        if ( request.getSession().getAttribute("FilasGrid") != null && pantalla.getFilasGrid() == 0 )
            pantalla.setFilasGrid( ((java.lang.Integer) request.getSession().getAttribute("FilasGrid")).intValue() );

        // POP Filtros en SESSION:
        // Quién manda más, la pantalla o el filtro?
        RsBeanFiltro filtroOld = (RsBeanFiltro) request.getSession().getAttribute("rsFiltro");
        if ( filtroOld != null ) { pantalla.setRs_filtro( (RsBeanFiltro) filtroOld.coalesce(pantalla.getRs_filtro(),filtroOld) ); }
        
        // Inicializar filtros y otros campos auxiliares:
        
        UsBean reg_us = Subrutinas.getUsFromId(dataBase, pantalla.getLogon_USR());
		pantalla.setRs_US_is_admin( reg_us.getUs_role_id() );
		pantalla.setRs_US_nick( reg_us.getUs_nick() );
		pantalla.setRs_US_avatar( reg_us.getUs_avatar() );
		pantalla.setRs_US_country_id( reg_us.getUs_country_id() );
        
        // SOLAMENTE LAS SUYAS!!!
        pantalla.getRs_filtro().setRs_user_id( pantalla.getLogon_USR() );

        // PUSH SuperFiltros en SESSION:
        request.getSession().setAttribute("rsFilaInicioGrid", new java.lang.Integer( pantalla.getFilaInicioGrid()) );
        request.getSession().setAttribute("FilasGrid", new java.lang.Integer( pantalla.getFilasGrid()) );
        
        // PUSH Filtros en SESSION:
        request.getSession().setAttribute("rsFiltro",pantalla.getRs_filtro());
        
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
        RsRCD_AF pantalla = (RsRCD_AF)form;
        //////////////////////////////////
        // Restrictores y mapeos con destino campos de pantalla:
        RsBean rcd_Rs = (RsBean) request.getAttribute("rcd_Rs");
        if ( rcd_Rs != null ) {
            // Ha sido llamado desde otro programa:
            ;
        } else {
            // Recarga de sí misma...verificar si valores legales en campos importantes u obligatorios:
            // if ( pantalla.getRs_rst0() == null || pantalla.getRs_rst0().trim().length() < 1 ) {
            //     pantalla.setRs_rst0( "ValorDeProteccion" );
            // }
            ;
        }
        ///////////////////////////////////////////
        // Tratar parámetros extra recibidos:
        
        // Lo usa 'reservaExpress' para abrir el panel ADD:
        request.getSession().removeAttribute( "rsDSPFIL_ADDRCD_modal_OPEN" );

        ///////////////////////////////////////////
    }
    
    private int persistirPosiblesCambios(HttpServletRequest request, ActionForm  form ) {
        int resultado = 0;
        RsRCD_AF pantalla = (RsRCD_AF)form;
        //////////////////////////////////
        if ( pantalla != null && pantalla.getGrid() != null ) {
            RsAccesoBaseDatos db = new RsAccesoBaseDatos();
            RsBean key = null;
            RsBean reg = null;
            // Se regraba cada fila del grid marcada en campo "chg":
            for (int i=0;i<pantalla.getGrid().length;i++) {
                key = ((RsBean)(pantalla.getGrid()[i]));
                if ( key.getChg() != null && key.getChg().trim().length() > 0 ) {
                    try {
                        // Cargo clave entidad:
                        String opcion = key.getChg().trim();
                        String k;
	k = opcion; key.setRs_reservation_id( k );
                        reg = db.rs_getRcd( new Subrutinas().getBDConexion(request), key );
                        if (reg!=null) {
                            // Campo(s) a reescribir:
                            // reg.setRs_Campo( ((RsBean)(pantalla.getGrid()[i])).getRs_Campo() );
                            // ...meter a manita cada campo...y activar este chgObj:
                            // db.rs_chgObj(new Subrutinas().getBDConexion(request),reg);
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
