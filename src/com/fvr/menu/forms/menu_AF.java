package com.fvr.menu.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class menu_AF extends org.apache.struts.action.ActionForm {
    public static final long serialVersionUID = 1L; // Para evitar "warning: [serial] serializable class...""
    
    private String opcionPantalla;
    private String opcionJSMenu;
    private String logon_USR;
    
    public menu_AF() {super();}
    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        return null;
    }
    
    public String getOpcionPantalla() {
        return opcionPantalla;
    }
    
    public void setOpcionPantalla(String opcionPantalla) {
        this.opcionPantalla = opcionPantalla;
    }
    
    public String getLogon_USR() {
        return logon_USR;
    }
    
    public void setLogon_USR(String logon_USR) {
        this.logon_USR = logon_USR;
    }

    public String getOpcionJSMenu() {
        return opcionJSMenu;
    }

    public void setOpcionJSMenu(String opcionJSMenu) {
        this.opcionJSMenu = opcionJSMenu;
    }
}
