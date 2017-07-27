
package com.fvr.logon.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.fvr._comun.ContextVars;

public class Logon_AF extends org.apache.struts.action.ActionForm {
    public static final long serialVersionUID = 1L; // Para evitar "warning: [serial] serializable class...""
    
    public String logon_USR;
    public String logon_PWD;
    public String logon_HSH;
    public ContextVars contextVars = new ContextVars();

    public ContextVars getContextVars() {
		return contextVars;
	}

	public void setContextVars(ContextVars contextVars) {
		this.contextVars = contextVars;
	}

	public String getLogon_HSH() {
		return logon_HSH;
	}

	public void setLogon_HSH(String logon_HSH) {
		this.logon_HSH = logon_HSH;
	}

	private String opcionPantalla;
    
    public String getOpcionPantalla() {
		return opcionPantalla;
	}

	public void setOpcionPantalla(String opcionPantalla) {
		this.opcionPantalla = opcionPantalla;
	}

	public Logon_AF() {super();}
    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        return null;
    }

    public String getLogon_USR() {
        return logon_USR;
    }

    public void setLogon_USR(String logon_USR) {
        this.logon_USR = logon_USR;
    }

    public String getLogon_PWD() {
        return logon_PWD;
    }

    public void setLogon_PWD(String logon_PWD) {
        this.logon_PWD = logon_PWD;
    }
}
