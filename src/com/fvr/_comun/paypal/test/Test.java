package com.fvr._comun.paypal.test;

public class Test {

//	public static void main(String[] args) {
//		
//		System.out.println(">>>>>>>>>>");
//		
//		try {
//			BDConexion dataBase = new BDConexion();
//
//			DcBean reg_dc = Subrutinas.getDcFromKLzKDc(dataBase, "1467037713906", "1467037773888");
////			
////			proceso(dataBase, factura);
//			
//			Paypal_API py = new Paypal_API( dataBase, "http://219582dd.ngrok.io/Billin/Paypal" );
//			
//			py.api_setExpressCheckout(dataBase, reg_dc, "pepito@gmail.com", true, true, null);
//			
//			
//
//		} catch (StExcepcion e) {
//			e.printStackTrace();
//		}
//		
//		System.out.println("<<<<<<<<<<");
//
//	}
//
//	private static String proceso( BDConexion dataBase, DcBean reg_dc ) {
//		
//		String kDcCompleta = reg_dc.getDc_lzPK() + "^" + reg_dc.getDc_dcPK();
//		String kCtCompleta = reg_dc.getDc_LZ_Cuenta_2() + "^" + reg_dc.getDc_LZ_ct_isPublica_2();
//		Double importe     = reg_dc.getDc_ImporteTotal();
//		
//		String callbackUrl = "http://219582dd.ngrok.io/Billin/Paypal";
//
//		APICredentials credentials = new PaypalCredentials(dataBase, kCtCompleta);
//		PaypalAPI paypal = null;
//		PaypalResponse res = null;
//		
//		if ( credentials != null ) {
//			
//			paypal = PaypalAPIFactory.createPaypalAPI( dataBase, credentials );
//			
//			res = PaypalMethods.setExpressCheckout(
//							  paypal
//							, reg_dc
//							, "NumFactura2"
//							, importe.toString()
//							, callbackUrl + "?ACK=Success&KDOC=" + kDcCompleta
//							, callbackUrl + "?ACK=Failure&KDOC=" + kDcCompleta
//							, true
//							, null
//						);
//			
//			if ( res != null ) {
////	private String token;
////	private String correlationID;
////	private String acknowledge;
////	private String timeStamp;
////	private String version;
////	private String build;
////	private List<PaypalFailure> errors = new ArrayList<PaypalFailure>();
//				
//				System.out.println( res.getAcknowledge() );
//
//				if ( "Failure".equalsIgnoreCase( res.getAcknowledge() ) ) {
//					for ( PaypalFailure item : res.getErrors()) {
//						System.out.println( res.getAcknowledge() );
//					}
//				} else
//				if ( "Success".equalsIgnoreCase( res.getAcknowledge() ) ) {
//					System.out.println( res.getToken() );
//				}
//
//			}
//			
//		}
//		
////		res = [PaypalMethods|SetExpressCheckout] 200^TOKEN=EC%2d46443959ML4749901&TIMESTAMP=2016%2d06%2d28T15%3a14%3a04Z&CORRELATIONID=f62c8100e155d&ACK=Success&VERSION=204&BUILD=000000
//
//		return res==null?null:res.getToken();
//		
//	}

}
