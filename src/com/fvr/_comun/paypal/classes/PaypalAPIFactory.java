package com.fvr._comun.paypal.classes;

import com.fvr.FuentesDeDatos.BDConexion;
import com.fvr._comun.Logger;
import com.fvr._comun.Subrutinas;
import com.fvr._comun._K;

public class PaypalAPIFactory {
	/**
	 * Crea una instancia {@link PaypalAPI} usando los valores de las claves {@value _K#PA_KEY_PAYPAL_CHECKOUT_EXPRESS_API_URL} 
	 * y {@value _K#PA_KEY_PAYPAL_CHECKOUT_EXPRESS_API_VERSION} de la tabla PA.
	 * @param dataBase
	 * @param credentials
	 * @return instancia de la clase  {@link PaypalAPI}, null en el caso que no se encuentren las claves necesarias de la tabla PA.
	 */
	public static PaypalAPI createPaypalAPI(BDConexion dataBase, APICredentials credentials) {
		Logger logger = Logger.newLogger("PaypalAPIFactory|createPaypalAPI");
		String paypalAPIURL = Subrutinas.getDBValueFromKey(dataBase, _K.CUENTA_DE_SISTEMA, _K.PA_KEY_PAYPAL_CHECKOUT_EXPRESS_API_URL);
		String paypalAPIVersion = Subrutinas.getDBValueFromKey(dataBase, _K.CUENTA_DE_SISTEMA, _K.PA_KEY_PAYPAL_CHECKOUT_EXPRESS_API_VERSION);
		
		if (paypalAPIURL==null || paypalAPIURL.trim().length()<1) {
			logger.a("ERROR No se ha encontrado en BD.PA la KEY: ").a(_K.PA_KEY_PAYPAL_CHECKOUT_EXPRESS_API_URL).pln();
			return null;
		}

		if (paypalAPIVersion==null || paypalAPIVersion.trim().length()<1) {
			logger.a("ERROR No se ha encontrado en BD.PA la KEY: ").a(_K.PA_KEY_PAYPAL_CHECKOUT_EXPRESS_API_VERSION).pln();
			return null;
		}
		
		PaypalAPI paypal = new PaypalAPI(paypalAPIURL, paypalAPIVersion);
		paypal.setCredentials(credentials);
		return paypal;
	}
}
