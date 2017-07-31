package com.fvr.logon.actions;

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
import com.fvr._comun.StExcepcion;
import com.fvr._comun.Subrutinas;
import com.fvr._comun._K;
import com.fvr._comun.mail.SendMail;
import com.fvr.logon.forms.Logon_AF;
import com.fvr.us_users.bean.UsBean;

public class Logon_A extends Action {
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        request.getSession().invalidate();

        Logon_AF pantalla = (Logon_AF) form;
        ActionMessages errores = new ActionMessages();
        BDConexion dataBase = new Subrutinas().getBDConexion(request);

        String resultado = "OK";
        
        // ATENCIÓN: solo se adminten caracteres en minúsculas.
        if ( pantalla.getLogon_USR() != null ) { pantalla.setLogon_USR( pantalla.getLogon_USR().toLowerCase().trim() ); }
        
        /////////////////////////////////////////////////
        // Acciones en 'opcionPantalla':
        //		'newUser'			Nuevo registro. Requiere		logon_USR
        //		'forgotPass'		Recordar password. Requiere		logon_USR
        //		'ENTER'				Login. Requiere					logon_USR, logon_PWD
        /////////////////////////////////////////////////
        if ( pantalla != null && pantalla.getOpcionPantalla() != null && pantalla.getOpcionPantalla().trim().length() > 0 ) {
////////////////////////////////
        	if ( "newUser".equals(pantalla.getOpcionPantalla() ) ) {
				if ( pantalla.getLogon_USR().trim().length() < 1 ) {
		            errores.add("error", new ActionMessage( "errors.detail", "Debes introducir un eMail para iniciar registro." ));
		            request.setAttribute("panel-pwd-ON", "panel-pwd-ON");
		            saveErrors(request,errores);
				} else {
					newUser(request, form, errores);
				}
        	} else	
////////////////////////////////
			if ( "forgotPass".equals(pantalla.getOpcionPantalla() )) {
				if ( pantalla.getLogon_USR().trim().length() < 1 ) {
			      errores.add("error", new ActionMessage( "errors.detail", "Debes introducir el eMail." ));
			      request.setAttribute("panel-pwd-ON", "panel-pwd-ON");
			      saveErrors(request,errores);
				} else {
					forgotPass(request, form, errores);
				}
			} else
////////////////////////////////
			if ( "reservaExpress".equals(pantalla.getOpcionPantalla() )) {
				// Hacer una reserva para el mail inidicado mediante un pseudoregisttro:
				if ( pantalla.getLogon_USR().trim().length() < 1 ) {
		            errores.add("error", new ActionMessage( "errors.detail", "Debes introducir un eMail para realizar una reserva." ));
		            saveErrors(request,errores);
				} else {
					// Existe ese usuario ya?
					UsBean reg_us = Subrutinas.getUsFromId(dataBase, pantalla.getLogon_USR());
					if ( reg_us != null && reg_us.getUs_sincro() != null && reg_us.getUs_sincro().trim().length() > 0) {
//			            errores.add("error", new ActionMessage( "errors.detail", "Ese eMail ya se usó en el sistema, solicita ahora una contraseña si todavía no la tienes." ));
			            request.setAttribute("panel-pwd-ON", "panel-pwd-ON");
			            request.setAttribute("noPwd-button-ON", "noPwd-button-ON");
			            saveErrors(request,errores);
					} else {
						String link_urlState = reservaExpress(request, form, errores);
						if ( link_urlState != null ) {
							link_urlState += "panel_add";
							response.sendRedirect( link_urlState );	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//							request.getRequestDispatcher( "/CpDSPFIL_A.do" ).forward(request, response);
						}
						
						if ( !errores.isEmpty() ) {
				            saveErrors(request,errores);
						}
					}
				}
			} else
////////////////////////////////
        	if ( "ENTER".equals(pantalla.getOpcionPantalla() )) {

                if (
           			 pantalla == null || 
           			 pantalla.getLogon_USR() == null || 
           			 pantalla.getLogon_USR().trim().length() < 1 || 
           			 pantalla.getLogon_PWD() == null || 
           			 pantalla.getLogon_PWD().trim().length() < 1 
           			 ) {
                       errores.add("error", new ActionMessage( "errors.detail", "Campos obligatorios." ));
                       saveErrors(request,errores);
                   } else {
                       if ( chkUsrPwd(request,form, errores) ) {
							String link_urlState = entrarAlSistema(request, form, errores);
							if ( link_urlState != null ) {
								response.sendRedirect( link_urlState );	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
							}
//	           				resultado = "ENTRAR";
                       } else {
                           errores.add("error", new ActionMessage( "errors.detail", "No se superó el chequeo de seguridad." ));
                           request.setAttribute("panel-pwd-ON", "panel-pwd-ON");
                           saveErrors(request,errores);
                       }
                   }

        	}
////////////////////////////////
        } 
        /////////////////////////////////////////////////
        // Para que permanezca en pantalla el user_id:
        request.setAttribute("logon_USR", pantalla.getLogon_USR());
        /////////////////////////////////////////////////
        return mapping.findForward(resultado);
        
    }
    
    private boolean chkUsrPwd(HttpServletRequest request, ActionForm  form, ActionMessages errores) {
        boolean resultado = false;
        /////////////////////////////////////
        Logon_AF pantalla = (Logon_AF) form;
        /////////////////////////////////////
        // ..acceso a BD para comprobar la contraseña...
        String usr = pantalla.getLogon_USR();
        String pwd = pantalla.getLogon_PWD();
        
        if ( usr == null || usr.trim().length() < 1 ) { return resultado; }
        if ( pwd == null || pwd.trim().length() < 1 ) { return resultado; }

		if ( _K.PWD_EN_BLANCO.equalsIgnoreCase( pwd ) ) {
			errores.add("error", new ActionMessage( "errors.detail", "La contraseña no puede estar en blanco." ));
			return resultado;
		}

        com.fvr.us_users.db.UsAccesoBaseDatos	dao_us = new com.fvr.us_users.db.UsAccesoBaseDatos();
        com.fvr.us_users.bean.UsBean			reg_us = new com.fvr.us_users.bean.UsBean();
        
        reg_us.setUs_user_id( usr.toLowerCase() );
        
        try {
			reg_us = dao_us.us_getRcd(new Subrutinas().getBDConexion(request), reg_us);
			if ( reg_us != null ) {
				if ( pwd.equalsIgnoreCase( reg_us.getUs_password() ) ) {
			        resultado = true;
				}
			}
		} catch (StExcepcion e) {
          errores.add("error", new ActionMessage( "errors.detail", e.getMessage() ));
		}

        return resultado;
    }

    private String entrarAlSistema(HttpServletRequest request, ActionForm form, ActionMessages errores) {

        Logon_AF pantalla = (Logon_AF) form;
        BDConexion dataBase = new Subrutinas().getBDConexion(request);

       	String claveOperaciones = Subrutinas.getHashFromRandomCode();

       	request.getSession(true).setAttribute( "logon_USR", pantalla.getLogon_USR().trim() );
        request.getSession(true).setAttribute( "logon_HSH", claveOperaciones );
		Subrutinas.setUsr_newHash(dataBase, pantalla.getLogon_USR().trim(), claveOperaciones);

		Subrutinas.addLog(dataBase, pantalla.getLogon_USR().trim(), "LOGON", pantalla.getLogon_USR().trim(), this.getClass().getSimpleName());

		UsBean reg_us = Subrutinas.getUsFromId(dataBase, pantalla.getLogon_USR().trim());
		request.getSession(true).setAttribute( "roleKey", reg_us.getUs_role_id() );

		String link = Subrutinas.get_urlBase(request) + "/Index_A.do#/RsDSPFIL/";
		
		// Determinar siguiente paso de navegación según el rol del usuario:
//		// USERs:
//		if ( _K.ROL_USER.equalsIgnoreCase( reg_us.getUs_role_id() ) ) {
//			link =    Subrutinas.get_urlBase(request) 
//					+ "/Index_A.do#/USER/" 
//					;
//		} else 
//		// ADMIN:
//		if ( _K.ROL_ADMIN.equalsIgnoreCase( reg_us.getUs_role_id() ) ) {
//			link =    Subrutinas.get_urlBase(request) 
//					+ "/Index_A.do#/ADMIN" 
//					;
//		} else 
//		// FRANQUICIA:
//		if ( _K.ROL_FRANQUICIA.equalsIgnoreCase( reg_us.getUs_role_id() ) ) {
//			link =    Subrutinas.get_urlBase(request) 
//					+ "/Index_A.do#/FRANCHAISE" 
//					;
//		} else {
//			link =    Subrutinas.get_urlBase(request) 
//					  + "/Index_A.do#/USER/" 
//					;
//      }

		return link;
    }

    private String reservaExpress(HttpServletRequest request, ActionForm form, ActionMessages errores) {

        Logon_AF pantalla = (Logon_AF) form;
        BDConexion dataBase = new Subrutinas().getBDConexion(request);
        
        String user_id = pantalla.getLogon_USR();
        
        /////////////////////////////////////////////////////////////////////
        // Improvisar el usuario:
        try {
            com.fvr.us_users.bean.UsBean reg_us = new com.fvr.us_users.bean.UsBean(); 
            // PK:
        	reg_us.setUs_user_id( user_id ); // user_id
        	// Resto:
//        	reg_us.setUs_sincro( "" ); // sincro
//        	reg_us.setUs_mark( "" ); // mark
//        	reg_us.setUs_is_deleted( "" ); // is_deleted
        	reg_us.setUs_author(  _K.USER_DEFAULT_author ); // author
//        	reg_us.setUs_user_id( user_id ); // user_id
        	reg_us.setUs_role_id( _K.ROL_USER ); // role_id
        	reg_us.setUs_hash_code( "" ); // hash_code
        	reg_us.setUs_nick( _K.USER_DEFAULT_nick ); // nick
        	reg_us.setUs_password( "" ); // password
        	reg_us.setUs_first_name( _K.USER_DEFAULT_first_name ); // first_name
        	reg_us.setUs_last_name( _K.USER_DEFAULT_last_name ); // last_name
        	reg_us.setUs_phone( "" ); // phone
        	reg_us.setUs_gender( _K.USER_DEFAULT_gender ); // gender
        	reg_us.setUs_birth_day( _K.USER_DEFAULT_birth_day ); // birth_day
        	reg_us.setUs_avatar( _K.USER_DEFAULT_avatar ); // avatar
        	reg_us.setUs_json( "" ); // json

			new com.fvr.us_users.db.UsAccesoBaseDatos().us_crtObj(dataBase, reg_us);

        } catch (StExcepcion e) {
            errores.add("error", new ActionMessage( "errors.detail", e.getMessage() ));
            saveErrors(request,errores);
            return null;
		}
        /////////////////////////////////////////////////////////////////////
        
        
        return entrarAlSistema(request, form, errores);
    }
    
    private void newUser(HttpServletRequest request, ActionForm form, ActionMessages errores) {

        Logon_AF pantalla = (Logon_AF) form;
        BDConexion dataBase = new Subrutinas().getBDConexion(request);

		UsBean reg_us = Subrutinas.getUsFromId(dataBase, pantalla.getLogon_USR().trim());

		if ( reg_us != null && reg_us.getUs_sincro() != null && reg_us.getUs_sincro().trim().length() > 0 ) {
            errores.add("error", new ActionMessage( "errors.detail", "El eMail ya existe. Introduce su contraseña si deseas entrar al sistema." ));
            request.setAttribute("panel-pwd-ON", "panel-pwd-ON");
		} else {
			List<String> lstErrores = new ArrayList<String>();
			String htmlDoc = SendMail.send_registroNuevoUsuario(dataBase, Subrutinas.get_urlBase(request), pantalla.getLogon_USR().trim(), lstErrores, true);
			if ( lstErrores.isEmpty() ) {
				Subrutinas.addLog(dataBase, _K.SYS, pantalla.getLogon_USR(), "Enviado correo para registro.", htmlDoc);
	            errores.add("error", new ActionMessage( "errors.detail", "Por favor consulta tu correo para continuar el proceso de registro." ));
			} else {
				Subrutinas.addLog(dataBase, _K.SYS, pantalla.getLogon_USR(), "ERROR en envío correo para registro.", lstErrores.get(0).toString() );
	            errores.add("error", new ActionMessage( "errors.detail", "Ha fallado la operación..." ));
			}
		}

		if ( ! errores.isEmpty() ) { saveErrors(request,errores); }
    }

    private void forgotPass(HttpServletRequest request, ActionForm form, ActionMessages errores) {
    	
        Logon_AF pantalla = (Logon_AF) form;
        BDConexion dataBase = new Subrutinas().getBDConexion(request);

        UsBean reg_us = Subrutinas.getUsFromId(dataBase, pantalla.getLogon_USR().trim());

		if ( reg_us != null && reg_us.getUs_sincro() != null && reg_us.getUs_sincro().trim().length() > 0 ) {
			List<String> lstErrores = new ArrayList<String>();
			String htmlDoc = SendMail.send_CambiarPassword(dataBase, Subrutinas.get_urlBase(request), pantalla.getLogon_USR().trim(), lstErrores, true);
			if ( lstErrores.isEmpty() ) {
				Subrutinas.addLog(dataBase, _K.SYS, pantalla.getLogon_USR(), "Enviado correo para cambio password.", htmlDoc);
	            errores.add("error", new ActionMessage( "errors.detail", "Por favor consulta tu correo para continuar el proceso de asignación de una nueva contraseña." ));
			} else {
				Subrutinas.addLog(dataBase, _K.SYS, pantalla.getLogon_USR(), "ERROR en envío correo para cambio de contraseña.", lstErrores.get(0).toString() );
	            errores.add("error", new ActionMessage( "errors.detail", "Ha fallado la operación..." ));
			}
		} else {
            errores.add("error", new ActionMessage( "errors.detail", "eMail no hallado en el sistema." ));
		}
		
		if ( ! errores.isEmpty() ) { saveErrors(request,errores); }
    }

}
