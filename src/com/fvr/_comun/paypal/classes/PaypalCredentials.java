package com.fvr._comun.paypal.classes;

import com.fvr.FuentesDeDatos.BDConexion;
import com.fvr._comun.Subrutinas;
import com.fvr._comun._K;

public class PaypalCredentials implements APICredentials {

	private String paypal_USR;
	private String paypal_PWD;
	private String paypal_SIGNATURE;
	
	public PaypalCredentials(BDConexion dataBase) {
		//Recuperar registros:
		paypal_USR = Subrutinas.getDBValueFromKey(dataBase, _K.PA_KEY_paypal_USR);
		paypal_PWD = Subrutinas.getDBValueFromKey(dataBase, _K.PA_KEY_paypal_PWD);
		paypal_SIGNATURE = Subrutinas.getDBValueFromKey(dataBase, _K.PA_KEY_paypal_SIGNATURE);
	}
	
	public String getUser() {
		return this.paypal_USR;
	}

	public String getPassword() {
		return this.paypal_PWD;
	}

	public String getSignature() {
		return this.paypal_SIGNATURE;
	}

}
