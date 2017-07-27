package com.fvr.us_users.actions;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.fvr.tk_tokens.bean.TkBean;
import com.fvr.us_users.bean.UsBean;
import com.fvr.us_users.db.UsAccesoBaseDatos;
import com.fvr.us_users.forms.UsRCD_AF;

import net.sf.json.JSONObject;

public class RsUsADDRCD_A extends Action {
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        String resultado = "OK";
        
        UsRCD_AF pantalla = (UsRCD_AF)form;
		BDConexion dataBase = new Subrutinas().getBDConexion(request);

        //////////////////////////////////////
        // Para permitir llamada de la versión en "Modal AngularJS" 
        boolean isVersionAngular = Subrutinas.isVersionAngular(request, form);	// (CARGA EL ACTION FORM DESDE EL PAYLOAD DE LA LLAMADA)
        //////////////////////////////////////

        // ATENCIÓN: solo se adminten caracteres en minúsculas.
        if ( pantalla.getUs_user_id() != null ) { pantalla.setUs_user_id( pantalla.getUs_user_id().toLowerCase().trim() ); }

        String opcion = pantalla.getOpcionPantalla();
        /////////////////////////
        // Sincronizar con su sesión en servidor:
        /////////////////////////
        sincroSesion( request, form );  // Arrastre del usuario y sincronización de estados.
        // La pantalla debe poder ser "autónoma" para que permita seguir funcionando aunque haya caducado su sesión en el servidor.
        // Actúa como repositorio de estados para poder regenerar la sesión si ha caducado.

        /////////////////////////
        // Seguridad de acceso:
        if ( ! isUsrTokenOk(new Subrutinas().getBDConexion(request), pantalla.getUs_json(), pantalla.getUs_user_id()) ) {
            resultado = "ERROR";
            ActionMessages errores = new ActionMessages();
            errores.add("error", new ActionMessage( "errors.detail", "Error de seguridad en parámetros recibidos." ));
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
            } else if ( opcion.trim().equalsIgnoreCase("NuevoReg") ) {
                resultado = opcion_NuevoReg(request,form);
            
                if ( "OK".equalsIgnoreCase( resultado ) ) {
                   // Proceso después de creación de nuevo usuario:
                    pantalla.setLogon_USR( pantalla.getUs_user_id() );
                    pantalla.setLogon_HSH( Subrutinas.getHashFromRandomCode() );
                    request.getSession(true).setAttribute( "logon_USR", pantalla.getLogon_USR() );
                    request.getSession(true).setAttribute( "logon_HSH", pantalla.getLogon_HSH() );
                    Subrutinas.setUsr_newHash(dataBase, pantalla.getLogon_USR(), pantalla.getLogon_HSH());

        			Subrutinas.addLog(dataBase, pantalla.getLogon_USR().trim(), "NEW USER", pantalla.getLogon_USR().trim(), this.getClass().getSimpleName());

                }

            } else if ( opcion.trim().equalsIgnoreCase("ChgPwd") ) {
                resultado = opcion_ChgPwd(request,form);
                
                if ( "OK".equalsIgnoreCase( resultado ) ) {
                    // Proceso después de cambiar password del usuario:
                    pantalla.setLogon_USR( pantalla.getUs_user_id() );
                    pantalla.setLogon_HSH( Subrutinas.getHashFromRandomCode() );
                    request.getSession(true).setAttribute( "logon_USR", pantalla.getLogon_USR() );
                    request.getSession(true).setAttribute( "logon_HSH", pantalla.getLogon_HSH() );
                    Subrutinas.setUsr_newHash(dataBase, pantalla.getLogon_USR(), pantalla.getLogon_HSH());

					Subrutinas.addLog(dataBase, pantalla.getLogon_USR().trim(), "CHGPWD", pantalla.getLogon_USR().trim(), this.getClass().getSimpleName());

                }
                
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
        UsRCD_AF pantalla = (UsRCD_AF)form;
        ///////////////////////////////////////////
        resultado = CamposCalculados(request,pantalla);
        ///////////////////////////////////////////
        ConfigPantalla cfg = new ConfigPantalla();
        cfg.setNombrePantalla( (this.getClass().toString()).substring((this.getClass().toString()).lastIndexOf(".")+1) );
        cfg.setTituloPantalla("users: crear");
        // Hace falta para el título y paginado:
        request.getSession(true).setAttribute( "cfgPantalla", cfg );
        ///////////////////////////////////////////
        return resultado;
    }

    private void sincroSesion( HttpServletRequest request, ActionForm  form ) {
        ///////////////////////////////////////////
        UsRCD_AF pantalla = (UsRCD_AF)form;
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
    
    private String opcion_NuevoReg(HttpServletRequest request, ActionForm  form) {
        String resultado = "OK";
        ///////////////////////////////////////////
        UsRCD_AF pantalla = (UsRCD_AF)form;
        BDConexion dataBase = new Subrutinas().getBDConexion(request);
        ///////////////////////////////////////////
        resultado = this.chkPantalla( request, pantalla );
        // Altero imagen WRK en disco:
        if (resultado.equalsIgnoreCase("OK"))
            resultado = this.crtRcd( request, pantalla );
        ///////////////////////////////////////////
        return resultado;
    }

    private void recalcularVirtuales(HttpServletRequest request, ActionForm form) {
        UsRCD_AF pantalla = (UsRCD_AF)form;
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
        UsRCD_AF pantalla = (UsRCD_AF)form;
        ///////////////////////////////////////////
        // Inicializar campos:
        
        pantalla.setUs_role_id( _K.ROL_USER );
    	
        pantalla.setUs_author(pantalla.getUs_user_id());

        ///////////////////////////////////////////
        // Campos deducidos:
        ///////////////////////////////////////////

        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try { date = sdf.parse( pantalla.getUs_birth_day() ); } catch (ParseException e1) {;}
        if ( date != null ) {
        	pantalla.setUs_birth_day( sdf.format(date) );
        }

        ///////////////////////////////////////////
        return resultado;
    }
    
    private String chkPantalla(HttpServletRequest request, ActionForm  form) {
        String resultado = "OK";
        ///////////////////////////////////////////
        UsRCD_AF pantalla = (UsRCD_AF)form;
        ///////////////////////////////////////////
        CamposCalculados(request,pantalla);
        ///////////////////////////////////////////
        ActionMessages errores = new ActionMessages();
        // Claves obligatorias:
        if (
		   pantalla.getUs_user_id() == null || pantalla.getUs_user_id().trim().length() < 1
                ) {
            resultado = "NOVALE";
            errores.add("error", new ActionMessage( "errors.detail", "Valores para CLAVE obligatorios." ));
        }

        ///////////////////////////////////////////

		if ( _K.PWD_EN_BLANCO.equalsIgnoreCase( pantalla.getUs_password() ) ) {
            resultado = "NOVALE";
			errores.add("error", new ActionMessage( "errors.detail", "La contraseña no puede estar en blanco." ));
            saveErrors(request,errores);
			return resultado;
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
        UsRCD_AF pantalla = (UsRCD_AF)form;
        UsBean registro = null;
        if (pantalla != null ) {
            registro = new UsBean();
            
            pantalla.copyTo( registro );
            
            UsAccesoBaseDatos db = new UsAccesoBaseDatos();
            try {
                db.us_crtObj( new Subrutinas().getBDConexion(request), registro );
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

    private String opcion_ChgPwd(HttpServletRequest request, ActionForm form) {
        String resultado = "OK";
        ///////////////////////////////////////////
        UsRCD_AF pantalla = (UsRCD_AF)form;
        BDConexion dataBase = new Subrutinas().getBDConexion(request);
        ///////////////////////////////////////////
        resultado = this.chkPantalla( request, pantalla );
        
        if ( "OK".equalsIgnoreCase( resultado ) ) {
    		
    		if ( pantalla.getUs_password().equals( pantalla.getUs_first_name() ) ) {

    			try {
            		UsBean registro = Subrutinas.getUsFromId(dataBase, pantalla.getUs_user_id());
    				registro.setUs_password( pantalla.getUs_password() );
    				new UsAccesoBaseDatos().us_chgObj(dataBase, registro);
    			} catch (StExcepcion e) {
                    resultado = "NOVALE";
                    ActionMessages errores = new ActionMessages();
                    errores.add("error", new ActionMessage( "errors.detail", e.getMessage() ));
                    saveErrors(request,errores);
    			}

    		} else {
                resultado = "NOVALE";
                ActionMessages errores = new ActionMessages();
                errores.add("error", new ActionMessage( "errors.detail", "Las contraseñas no son iguales." ));
                saveErrors(request,errores);
    		}
        }

		///////////////////////////////////////////
        return resultado;
	}
    
    private boolean isUsrTokenOk( BDConexion dataBase, String us_json, String user_id ) {

    	// Comprobamos que el token_id es del user_id adecuado:
    	
    	boolean resultado = false;

        JSONObject json = null;
        try { json = JSONObject.fromObject( us_json ); } catch (Exception e) {;}
        if ( json == null ) { return resultado; }

        String token_id = null;
        try { token_id = json.getString("crt_token_id"); } catch (Exception e) {;}
        if ( token_id == null ) { return resultado; }

        TkBean reg_tk = Subrutinas.getTkFromId(dataBase, token_id);
        if ( reg_tk == null || reg_tk.getTk_json() == null || reg_tk.getTk_json().trim().length() < 1 ) { return resultado; }

        String eMailDestino = null;
        try { eMailDestino = JSONObject.fromObject(reg_tk.getTk_json()).getString("eMailDestino"); } catch (Exception e) {;}
        if ( eMailDestino == null) { return resultado; }

        if ( eMailDestino.equals( user_id ) ) { resultado = true; }

        return resultado;
    }
    
}
