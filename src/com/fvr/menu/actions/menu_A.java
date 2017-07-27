package com.fvr.menu.actions;

import com.fvr.menu.forms.menu_AF;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

public class menu_A extends Action {
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        String resultado = "OK";
        
        menu_AF pantalla = (menu_AF)form;
        
        String opcion = pantalla.getOpcionPantalla();
        
        this.cargarPantalla( request, pantalla );
        
        // Despachar petición:
        if ( opcion != null && opcion.trim().length()>=0 ) {
            if ( opcion.trim().substring(0,4).equalsIgnoreCase("Goto") ) {
                resultado = opcion_Goto(request,form);
                prepararParametros( request, pantalla, resultado );
            }
        }
        pantalla.setOpcionPantalla("");
        
        return mapping.findForward(resultado);
    }
    
    private String opcion_Goto( HttpServletRequest request, ActionForm  form ) {
        String resultado = "OK";
        ///////////////////////////////////////////
        menu_AF pantalla = (menu_AF)form;
        ///////////////////////////////////////////
        String opcion = pantalla.getOpcionPantalla();
        resultado = opcion.trim().substring(4);
        ///////////////////////////////////////////
        return resultado;
    }
    
    private String cargarPantalla( HttpServletRequest request, ActionForm  form ) {
        String resultado = "OK";
        ///////////////////////////////////////////
        menu_AF pantalla = (menu_AF)form;
        ///////////////////////////////////////////
        if (request.getSession().getAttribute("logon_USR")!=null)
            pantalla.setLogon_USR( ((String)request.getSession().getAttribute("logon_USR")).trim());
        ///////////////////////////////////////////
        return resultado;
    }
    
    private void prepararParametros(HttpServletRequest request, ActionForm  form , String pasoSiguiente) {
//        menu_AF pantalla = (menu_AF)form;
        ///////////////////////////////////////////////
        if ( pasoSiguiente.equalsIgnoreCase("DcCa_WRK_DSPFIL_rstUsuario_A") ) {
            // Filtros inicializados:
        }
        ///////////////////////////////////////////////
    }
}
