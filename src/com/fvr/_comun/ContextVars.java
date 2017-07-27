package com.fvr._comun;

import java.io.Serializable;

public class ContextVars extends StBean implements Serializable {

	private static final long serialVersionUID = -3555622942726578080L;

	public String role_id;
	public String specialMenu;
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	public String getSpecialMenu() {
		return specialMenu;
	}
	public void setSpecialMenu(String specialMenu) {
		this.specialMenu = specialMenu;
	}

}
