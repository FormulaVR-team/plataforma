package com.fvr.logon.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fvr._comun.Subrutinas;
import com.fvr.logon.forms.Logon_AF;

public class Index_A extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String resultado = "OK";
        Logon_AF pantalla = (Logon_AF)form;

        /////////////////////////
        // Sincronizar con su sesión en servidor:
        /////////////////////////
        sincroSesion( request, form );  // Arrastre del usuario y sincronización de estados.
        // La pantalla debe poder ser "autónoma" para que permita seguir funcionando aunque haya caducado su sesión en el servidor.
        // Actúa como repositorio de estados para poder regenerar la sesión si ha caducado.

        ///////////////////
        // Arrastre de roleKey (funciona por presencia / ausencia ):
        String roleKey = pantalla.getContextVars().getRole_id();
        if ( roleKey == null || roleKey.trim().length() < 1 ) {
        	roleKey = (String) request.getSession().getAttribute( "roleKey" );
        }
        if ( roleKey != null && roleKey.trim().length() > 0 ) {
        	request.getSession().setAttribute( "roleKey", roleKey );
        } else {
        	request.getSession().removeAttribute( "roleKey" );
        }
        ///////////////////

        return mapping.findForward(resultado);
    }

    private void sincroSesion( HttpServletRequest request, ActionForm  actioForm ) {
        ///////////////////////////////////////////
        Logon_AF pantalla = (Logon_AF)actioForm;
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
        new Subrutinas().sincroSesion_COMUN( request, actioForm );
        ///////////////////////////////////////////
    }

}
