package com.fvr._comun.paypal.test;

import com.fvr._comun.StExcepcion;

public class PaypalTest {
	
	///////////////////////////////////
	// Para usar esto:
	//	1 . Con la key CT del VENDEDOR ("CTA1^S"): Conseguir las 3 credenciales en objeto "CxCredentials" 
	//		CxCredentials ==> paypal_USR, paypal_PWD y paypal_SIGNATURE
	//	2 . Construir el API de paypal: PaypalAPIFactory.createPaypalAPI( dataBase, credentials );
	//	3 . Llamar a los m�todos est�ticos:
	//			PaypalMethods.setExpressCheckout(), etc...
	///////////////////////////////////

	public static void main(String[] args) throws StExcepcion {

//		BDConexion dataBase = new BDConexion();;
//
//		String kDocumento = "111111^2222222";
//		APICredentials credentials = new PaypalCredentials(dataBase, "NICOLAS^S");
//
//		PaypalAPI paypal = PaypalAPIFactory.createPaypalAPI( dataBase, credentials );
////		String callbackUrl = "http://20e5a726.ngrok.io//Billin/Paypal";
//		String redirectUrl = Subrutinas.getDBValueFromKey(null, _K.CUENTA_DE_SISTEMA, _K.KEY_PAYPAL_CHECKOUT_EXPRESS_REDIRECT_URL);
//		DcBean dcKey = new DcBean();
//		dcKey.setKey(kDocumento);
//		DcBean documento = Subrutinas.getDcFromKLzKDc(null, Long.toString(dcKey.getDc_lzPK()), Long.toString(dcKey.getDc_dcPK()));
//
//		if ( documento.getDc_ImporteTotal() == 0 ) {
//			documento.setDc_ImporteTotal(15.87);
//		}
//
//		if (paypal!=null) {
////			PaypalResponse res = PaypalMethods.setExpressCheckout(paypal, Double.toString(documento.dc_ImporteTotal), callbackUrl + "?ACK=Success&KDOC=" + kDocumento, callbackUrl + "?ACK=Failure&KDOC=" + kDocumento);
////
////			Logger.newLogger("PaypalTest").a(redirectUrl).a(res.getToken()).pln();
//
//			PaypalResponse res = PaypalMethods.doExpressCheckoutPayment(paypal, "EC-2P853748RV467930E", "HASRZFAAB9YQA", Double.toString(documento.dc_ImporteTotal));
//			if (res.getAcknowledge().equals("Success")) {
//				Eventos.alPagarDcPorPaypal(null, kDocumento, res.getCorrelationID());
//			}
//		}
	}

}
