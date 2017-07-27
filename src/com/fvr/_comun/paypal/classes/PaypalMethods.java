package com.fvr._comun.paypal.classes;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.fvr._comun.Logger;
import com.fvr._comun.StExcepcion;
import com.fvr._comun.Subrutinas;
import com.fvr._comun.http.client.IHttpClient;
import com.fvr._comun.http.client.IHttpMethods;
import com.fvr._comun.http.client.ProxyHttpMethods;
import com.fvr._comun.http.client.TLSHttpClient;
import com.fvr._comun.paypal.bean.GetExpressCheckoutResponse;
import com.fvr._comun.paypal.bean.PaypalFailure;
import com.fvr._comun.paypal.bean.PaypalResponse;
import com.fvr._comun.paypal.bean.RecurringPaymentData;

public class PaypalMethods {


	public static PaypalResponse setExpressCheckout(PaypalAPI paypal, String nombreFiscalProveedor, String numeroDeFactura, String amount, String returnUrl, String cancelUrl, boolean isPagarSinCuentaClienteObligada, String RecurringPaymentDescription_o_null) {
		
		// Ver:
		// https://developer.paypal.com/docs/classic/api/merchant/SetExpressCheckout_API_Operation_NVP/
		
		Logger logger =  Logger.newLogger("PaypalMethods|SetExpressCheckout");
		String resp = "";
		final String charset = "UTF-8";

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		Map<String,String> headers = new HashMap<String, String>();

		params.add(new BasicNameValuePair("USER", paypal.getCredentials().getUser()));
		params.add(new BasicNameValuePair("PWD", paypal.getCredentials().getPassword()));
		params.add(new BasicNameValuePair("SIGNATURE", paypal.getCredentials().getSignature()));
		params.add(new BasicNameValuePair("METHOD", "SetExpressCheckout"));
		params.add(new BasicNameValuePair("VERSION", paypal.getVersion()));

		params.add(new BasicNameValuePair("LOCALECODE", "ES"));
//		params.add(new BasicNameValuePair("LOGOIMG", "https://app.billin.net/img/logos/Billin-ico.png" ));
		params.add(new BasicNameValuePair("BRANDNAME", "Billin - Clientes y Proveedores unidos en la nube"));
		if ( isPagarSinCuentaClienteObligada ) {
			params.add(new BasicNameValuePair("SOLUTIONTYPE", "Sole"));	// 2016-07-04. Para que no necesite crear cuenta y usar solo tarjeta de cr�dito:
		}

		params.add(new BasicNameValuePair("returnUrl", returnUrl));
		params.add(new BasicNameValuePair("cancelUrl", cancelUrl));

		///////////
		// 2016-11-03. Recurring Payments
		if ( RecurringPaymentDescription_o_null != null ) {
			// Recurring Payment:
			params.add(new BasicNameValuePair("L_BILLINGTYPE0", "RecurringPayments"));
			params.add(new BasicNameValuePair("L_BILLINGAGREEMENTDESCRIPTION0", RecurringPaymentDescription_o_null));
		} else {
			// Pago simple:
			/////////
			// 2016-07-08 Importe debe estar formateado en americano con dos decimales al final:
			double imnporte = Subrutinas.parse_double(amount);
			DecimalFormat formateador = (DecimalFormat)NumberFormat.getInstance( Locale.US );
			formateador.applyPattern( "#,###,###,##0.00" );
			String amountFomatted = formateador.format( imnporte );
			/////////

			///////////
			// 2016-07-08. Pepe Arespacochaga (PayPal): Para que salga el importe se deben pasar detalles completos del pedido, ser�a usar par�metros como estos:
//			L_PAYMENTREQUEST_0_NAME0 = Producto A
//			L_PAYMENTREQUEST_0_DESC0 = Esta es la descripcion del producto A
//			L_PAYMENTREQUEST_0_AMT0 = 49.95
//			L_PAYMENTREQUEST_0_NAME1 = Producto B
//			L_PAYMENTREQUEST_0_DESC1 = Esta es la descripcion del producto B
//			L_PAYMENTREQUEST_0_AMT1 = 10.00
//			PAYMENTREQUEST_0_AMT = 59.95
//			PAYMENTREQUEST_0_CURRENCYCODE = EUR
			params.add(new BasicNameValuePair("L_PAYMENTREQUEST_0_NAME0", "Factura #"+numeroDeFactura));
			params.add(new BasicNameValuePair("L_PAYMENTREQUEST_0_DESC0", "Factura #"+numeroDeFactura));
			params.add(new BasicNameValuePair("L_PAYMENTREQUEST_0_AMT0", amountFomatted));
			// 2016-07-14. Para que no salgan detalles del importe  
			params.add(new BasicNameValuePair("NOSHIPPING", "1"));
//			params.add(new BasicNameValuePair("EMAIL", "??????????"));
			params.add(new BasicNameValuePair("BRANDNAME", nombreFiscalProveedor));
			///////////

			params.add(new BasicNameValuePair("PAYMENTREQUEST_0_PAYMENTACTION", "SALE"));
			params.add(new BasicNameValuePair("PAYMENTREQUEST_0_AMT", amountFomatted));
			params.add(new BasicNameValuePair("PAYMENTREQUEST_0_CURRENCYCODE", "EUR"));
		}
		///////////

		String payload = "";

		IHttpClient clientBuilder = new TLSHttpClient("TLSv1.2");
		IHttpMethods httpMethods = new ProxyHttpMethods(charset);
		try {
			resp = httpMethods.doPost(clientBuilder, paypal.getUrl(), params, headers, payload);
		} catch (StExcepcion e) {
			logger.a(e.getMessage()).pln();
		}
		httpMethods = null;

		logger.a(resp).pln();

		//200^TOKEN=EC%2d6H461454JC6519307&TIMESTAMP=2016%2d06%2d16T22%3a43%3a57Z&CORRELATIONID=c462c0774dac4&ACK=Success&VERSION=78&BUILD=000000
		//200^TIMESTAMP=2016%2d06%2d16T22%3a46%3a15Z&CORRELATIONID=f8aeb14bd934f&ACK=Failure&VERSION=78&BUILD=000000&L_ERRORCODE0=10002&L_SHORTMESSAGE0=Security%20error&L_LONGMESSAGE0=Security%20header%20is%20not%20valid&L_SEVERITYCODE0=Error
		String[] resHttp = resp.split("\\^");

		PaypalResponse paypalResponse = new PaypalResponse();
		PaypalFailure paypalFailure = null;

		if (resHttp!=null && resHttp.length==2) {
			String[] resMessage = resHttp[1].split("&");
			for (int i = 0; i < resMessage.length; i++) {
				String[] fieldValue = resMessage[i].split("=");
				if (fieldValue!=null && fieldValue.length==2) {
					if ("TIMESTAMP".equals(fieldValue[0])) {
						try {
							paypalResponse.setTimeStamp(java.net.URLDecoder.decode(fieldValue[1], charset));
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					if ("CORRELATIONID".equals(fieldValue[0])) {
						try {
							paypalResponse.setCorrelationID(java.net.URLDecoder.decode(fieldValue[1], charset));
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					if ("ACK".equals(fieldValue[0])) {
						try {
							paypalResponse.setAcknowledge(java.net.URLDecoder.decode(fieldValue[1], charset));
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					if ("TOKEN".equals(fieldValue[0])) {
						try {
							paypalResponse.setToken(java.net.URLDecoder.decode(fieldValue[1], charset));
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					if ("VERSION".equals(fieldValue[0])) {
						try {
							paypalResponse.setVersion(java.net.URLDecoder.decode(fieldValue[1], charset));
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					if ("BUILD".equals(fieldValue[0])) {
						try {
							paypalResponse.setBuild(java.net.URLDecoder.decode(fieldValue[1], charset));
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					//Campos de errores

					if (fieldValue[0].startsWith("L_ERRORCODE")) {
						paypalFailure = new PaypalFailure();
						try {
							paypalFailure.setErrorCode(java.net.URLDecoder.decode(fieldValue[1], charset));
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					if (fieldValue[0].startsWith("L_SHORTMESSAGE")) {
						try {
							if (paypalFailure!=null) {
								paypalFailure.setShortMessage(java.net.URLDecoder.decode(fieldValue[1], charset));
							}
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					if (fieldValue[0].startsWith("L_LONGMESSAGE")) {
						try {
							if (paypalFailure!=null) {
								paypalFailure.setLongMessage(java.net.URLDecoder.decode(fieldValue[1], charset));
							}
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					if (fieldValue[0].startsWith("L_SEVERITYCODE")) {
						try {
							if (paypalFailure!=null) {
								paypalFailure.setSeverityCode(java.net.URLDecoder.decode(fieldValue[1], charset));
								paypalResponse.getErrors().add(paypalFailure);
								paypalFailure = null;
							}
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					// 2016-11-03. Recurring Payments:
					if ("PROFILEID".equals(fieldValue[0])) {
						try {
							paypalResponse.setProfileId(java.net.URLDecoder.decode(fieldValue[1], charset));
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}
					if ("PROFILESTATUS".equals(fieldValue[0])) {
						try {
							paypalResponse.setProfileStatus(java.net.URLDecoder.decode(fieldValue[1], charset));
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

				}
			}
		}

		return paypalResponse;
	}


	public static GetExpressCheckoutResponse getExpressCheckoutDetails(PaypalAPI paypal, String token) {
		
		// Ver:
		// https://developer.paypal.com/docs/classic/api/merchant/GetExpressCheckoutDetails_API_Operation_NVP/
		
		Logger logger =  Logger.newLogger("Paypal|GetExpressCheckoutDetails");
		String resp = "";
		final String charset = "UTF-8";

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		Map<String,String> headers = new HashMap<String, String>();

		params.add(new BasicNameValuePair("USER", paypal.getCredentials().getUser()));
		params.add(new BasicNameValuePair("PWD", paypal.getCredentials().getPassword()));
		params.add(new BasicNameValuePair("SIGNATURE", paypal.getCredentials().getSignature()));

		params.add(new BasicNameValuePair("VERSION", paypal.getVersion()));

		params.add(new BasicNameValuePair("METHOD", "GetExpressCheckoutDetails"));
		params.add(new BasicNameValuePair("TOKEN", token));

		String payload = "";

		IHttpClient clientBuilder = new TLSHttpClient("TLSv1.2");
		IHttpMethods httpMethods = new ProxyHttpMethods(charset);
		try {
			resp = httpMethods.doPost(clientBuilder, paypal.getUrl(), params, headers, payload);
		} catch (StExcepcion e) {
			logger.a(e.getMessage()).pln();
		}
		httpMethods = null;

		logger.a(resp).pln();

		//200^TOKEN=EC%2d107354194G272734A&BILLINGAGREEMENTACCEPTEDSTATUS=0&CHECKOUTSTATUS=PaymentActionNotInitiated&TIMESTAMP=2016%2d06%2d19T19%3a37%3a19Z&CORRELATIONID=96f2899dc44be&ACK=Success&VERSION=78&BUILD=000000&CURRENCYCODE=EUR&AMT=10%2e00&SHIPPINGAMT=0%2e00&HANDLINGAMT=0%2e00&TAXAMT=0%2e00&INSURANCEAMT=0%2e00&SHIPDISCAMT=0%2e00&PAYMENTREQUEST_0_CURRENCYCODE=EUR&PAYMENTREQUEST_0_AMT=10%2e00&PAYMENTREQUEST_0_SHIPPINGAMT=0%2e00&PAYMENTREQUEST_0_HANDLINGAMT=0%2e00&PAYMENTREQUEST_0_TAXAMT=0%2e00&PAYMENTREQUEST_0_INSURANCEAMT=0%2e00&PAYMENTREQUEST_0_SHIPDISCAMT=0%2e00&PAYMENTREQUEST_0_INSURANCEOPTIONOFFERED=false&PAYMENTREQUESTINFO_0_ERRORCODE=0
		String[] resHttp = resp.split("\\^");

		GetExpressCheckoutResponse paypalResponse = new GetExpressCheckoutResponse();
		PaypalFailure paypalFailure = null;

		if (resHttp!=null && resHttp.length==2) {
			String[] resMessage = resHttp[1].split("&");
			for (int i = 0; i < resMessage.length; i++) {
				String[] fieldValue = resMessage[i].split("=");
				if (fieldValue!=null && fieldValue.length==2) {
					if ("TIMESTAMP".equals(fieldValue[0])) {
						try {
							paypalResponse.setTimeStamp(java.net.URLDecoder.decode(fieldValue[1], charset));
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					if ("CORRELATIONID".equals(fieldValue[0])) {
						try {
							paypalResponse.setCorrelationID(java.net.URLDecoder.decode(fieldValue[1], charset));
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					if ("ACK".equals(fieldValue[0])) {
						try {
							paypalResponse.setAcknowledge(java.net.URLDecoder.decode(fieldValue[1], charset));
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					if ("TOKEN".equals(fieldValue[0])) {
						try {
							paypalResponse.setToken(java.net.URLDecoder.decode(fieldValue[1], charset));
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					if ("VERSION".equals(fieldValue[0])) {
						try {
							paypalResponse.setVersion(java.net.URLDecoder.decode(fieldValue[1], charset));
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					if ("BUILD".equals(fieldValue[0])) {
						try {
							paypalResponse.setBuild(java.net.URLDecoder.decode(fieldValue[1], charset));
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					//Campos de errores

					if (fieldValue[0].startsWith("L_ERRORCODE")) {
						paypalFailure = new PaypalFailure();
						try {
							paypalFailure.setErrorCode(java.net.URLDecoder.decode(fieldValue[1], charset));
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					if (fieldValue[0].startsWith("L_SHORTMESSAGE")) {
						try {
							if (paypalFailure!=null) {
								paypalFailure.setShortMessage(java.net.URLDecoder.decode(fieldValue[1], charset));
							}
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					if (fieldValue[0].startsWith("L_LONGMESSAGE")) {
						try {
							if (paypalFailure!=null) {
								paypalFailure.setLongMessage(java.net.URLDecoder.decode(fieldValue[1], charset));
							}
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					if (fieldValue[0].startsWith("L_SEVERITYCODE")) {
						try {
							if (paypalFailure!=null) {
								paypalFailure.setSeverityCode(java.net.URLDecoder.decode(fieldValue[1], charset));
								paypalResponse.getErrors().add(paypalFailure);
								paypalFailure = null;
							}
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					//Campos de informacion del pagador (payer)
					if ("EMAIL".equals(fieldValue[0])) {
						try {
							paypalResponse.setPayerEmail(java.net.URLDecoder.decode(fieldValue[1], charset));
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					if ("PAYERID".equals(fieldValue[0])) {
						try {
							paypalResponse.setPayerId(java.net.URLDecoder.decode(fieldValue[1], charset));
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					if ("PAYERSTATUS".equals(fieldValue[0])) {
						try {
							paypalResponse.setPayerStatus(java.net.URLDecoder.decode(fieldValue[1], charset));
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					if ("COUNTRYCODE".equals(fieldValue[0])) {
						try {
							paypalResponse.setPayerCountryCode(java.net.URLDecoder.decode(fieldValue[1], charset));
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					if ("BUSINESS".equals(fieldValue[0])) {
						try {
							paypalResponse.setPayerBusinessName(java.net.URLDecoder.decode(fieldValue[1], charset));
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					if ("FIRSTNAME".equals(fieldValue[0])) {
						try {
							paypalResponse.setPayerFirstName(java.net.URLDecoder.decode(fieldValue[1], charset));
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					if ("LASTNAME".equals(fieldValue[0])) {
						try {
							paypalResponse.setPayerLastName(java.net.URLDecoder.decode(fieldValue[1], charset));
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}
				}
			}
		}

		return paypalResponse;
	}


	/**
	 * 

	 * @param paypal
	 * @param token The timestamped token value that was returned in the SetExpressCheckout response and passed in the GetExpressCheckoutDetails request. PayPal also appends the token as a GET parameter named token to your RETURN URL when redirecting the buyer back to your website from paypal.com
	 * @param payerID Unique PayPal buyer account identification number as returned in the GetExpressCheckoutDetails response. PayPal also appends the payer ID as a GET parameter named PayerID to your RETURN URL when redirecting the buyer back to your website from paypal.com
	 * @return
	 */
	public static PaypalResponse doExpressCheckoutPayment(PaypalAPI paypal, String token, String payerID, String amount) {
		////////////////////////
		// NOTA para recurring Payments:
		//		Calls DoExpressCheckoutPayment if the order includes one-time purchases as well as a recurring payment. 
		//		Otherwise, skip this step.
		////////////////////////
		
		// Ver:
		// https://developer.paypal.com/docs/classic/api/merchant/DoExpressCheckoutPayment_API_Operation_NVP/
				
		Logger logger =  Logger.newLogger("Paypal|DoExpressCheckoutPayment");
		String resp = "";
		final String charset = "UTF-8";
		
		/////////
		// 2016-07-08 Importe debe estar formateado en americano con dos decimales al final:
		double importe = Subrutinas.parse_double(amount);
		DecimalFormat formateador = (DecimalFormat)NumberFormat.getInstance( Locale.US );
		formateador.applyPattern( "#,###,###,##0.00" );
		String amountFomatted = formateador.format( importe );
		/////////

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		Map<String,String> headers = new HashMap<String, String>();

		params.add(new BasicNameValuePair("USER", paypal.getCredentials().getUser()));
		params.add(new BasicNameValuePair("PWD", paypal.getCredentials().getPassword()));
		params.add(new BasicNameValuePair("SIGNATURE", paypal.getCredentials().getSignature()));

		params.add(new BasicNameValuePair("VERSION", paypal.getVersion()));

		params.add(new BasicNameValuePair("METHOD", "DoExpressCheckoutPayment"));
		params.add(new BasicNameValuePair("TOKEN", token));
		params.add(new BasicNameValuePair("PAYERID", payerID));
		params.add(new BasicNameValuePair("PAYMENTREQUEST_0_AMT", amountFomatted));
		params.add(new BasicNameValuePair("PAYMENTREQUEST_0_PAYMENTACTION", "SALE"));
		params.add(new BasicNameValuePair("PAYMENTREQUEST_0_CURRENCYCODE", "EUR"));

		// 2016-07-19 Este es el identificacdor para que billin pueda cobrar su comisi�n:
		params.add(new BasicNameValuePair("BUTTONSOURCE", "BILLIN_ECM"));

		String payload = "";

		IHttpClient clientBuilder = new TLSHttpClient("TLSv1.2");
		IHttpMethods httpMethods = new ProxyHttpMethods(charset);
		try {
			resp = httpMethods.doPost(clientBuilder, paypal.getUrl(), params, headers, payload);
		} catch (StExcepcion e) {
			logger.a(e.getMessage()).pln();
		}
		httpMethods = null;

		logger.a(resp).pln();

		//200^TOKEN=EC%2d7CU30191137934701&SUCCESSPAGEREDIRECTREQUESTED=false&TIMESTAMP=2016%2d06%2d19T19%3a57%3a58Z&CORRELATIONID=b0f8e615a3dcf&ACK=Success&VERSION=78&BUILD=000000&INSURANCEOPTIONSELECTED=false&SHIPPINGOPTIONISDEFAULT=false&PAYMENTINFO_0_TRANSACTIONID=7TM44567H4974890M&PAYMENTINFO_0_TRANSACTIONTYPE=expresscheckout&PAYMENTINFO_0_PAYMENTTYPE=instant&PAYMENTINFO_0_ORDERTIME=2016%2d06%2d19T19%3a57%3a58Z&PAYMENTINFO_0_AMT=10%2e00&PAYMENTINFO_0_FEEAMT=0%2e69&PAYMENTINFO_0_TAXAMT=0%2e00&PAYMENTINFO_0_CURRENCYCODE=EUR&PAYMENTINFO_0_PAYMENTSTATUS=Completed&PAYMENTINFO_0_PENDINGREASON=None&PAYMENTINFO_0_REASONCODE=None&PAYMENTINFO_0_PROTECTIONELIGIBILITY=Eligible&PAYMENTINFO_0_PROTECTIONELIGIBILITYTYPE=ItemNotReceivedEligible%2cUnauthorizedPaymentEligible&PAYMENTINFO_0_SECUREMERCHANTACCOUNTID=TU5CNPRSUQBV4&PAYMENTINFO_0_ERRORCODE=0&PAYMENTINFO_0_ACK=Success
		String[] resHttp = resp.split("\\^");

		PaypalResponse paypalResponse = new PaypalResponse();
		PaypalFailure paypalFailure = null;

		if (resHttp!=null && resHttp.length==2) {
			String[] resMessage = resHttp[1].split("&");
			for (int i = 0; i < resMessage.length; i++) {
				String[] fieldValue = resMessage[i].split("=");
				if (fieldValue!=null && fieldValue.length==2) {
					if ("TIMESTAMP".equals(fieldValue[0])) {
						try {
							paypalResponse.setTimeStamp(java.net.URLDecoder.decode(fieldValue[1], charset));
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					if ("CORRELATIONID".equals(fieldValue[0])) {
						try {
							paypalResponse.setCorrelationID(java.net.URLDecoder.decode(fieldValue[1], charset));
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					if ("ACK".equals(fieldValue[0])) {
						try {
							paypalResponse.setAcknowledge(java.net.URLDecoder.decode(fieldValue[1], charset));
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					if ("TOKEN".equals(fieldValue[0])) {
						try {
							paypalResponse.setToken(java.net.URLDecoder.decode(fieldValue[1], charset));
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					if ("VERSION".equals(fieldValue[0])) {
						try {
							paypalResponse.setVersion(java.net.URLDecoder.decode(fieldValue[1], charset));
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					if ("BUILD".equals(fieldValue[0])) {
						try {
							paypalResponse.setBuild(java.net.URLDecoder.decode(fieldValue[1], charset));
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					//Campos de errores

					if (fieldValue[0].startsWith("L_ERRORCODE")) {
						paypalFailure = new PaypalFailure();
						try {
							paypalFailure.setErrorCode(java.net.URLDecoder.decode(fieldValue[1], charset));
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					if (fieldValue[0].startsWith("L_SHORTMESSAGE")) {
						try {
							if (paypalFailure!=null) {
								paypalFailure.setShortMessage(java.net.URLDecoder.decode(fieldValue[1], charset));
							}
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					if (fieldValue[0].startsWith("L_LONGMESSAGE")) {
						try {
							if (paypalFailure!=null) {
								paypalFailure.setLongMessage(java.net.URLDecoder.decode(fieldValue[1], charset));
							}
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					if (fieldValue[0].startsWith("L_SEVERITYCODE")) {
						try {
							if (paypalFailure!=null) {
								paypalFailure.setSeverityCode(java.net.URLDecoder.decode(fieldValue[1], charset));
								paypalResponse.getErrors().add(paypalFailure);
								paypalFailure = null;
							}
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}
				}
			}
		}

		return paypalResponse;
	}

	
	public static PaypalResponse CreateRecurringPaymentsProfile(PaypalAPI paypal, String token, String payerID, RecurringPaymentData recurrenteData_inOut) {
		
		// Ver:
		// https://developer.paypal.com/docs/classic/api/merchant/CreateRecurringPaymentsProfile_API_Operation_NVP/
		
		// IPN variables:
		// https://www.paypal.com/us/cgi-bin/webscr?cmd=p/acc/ipn-subscriptions-outside
		// https://developer.paypal.com/webapps/developer/docs/classic/ipn/integration-guide/IPNandPDTVariables/#id091EB080EYK

		Logger logger =  Logger.newLogger("PaypalMethods|CreateRecurringPaymentsProfile");
		String resp = "";
		final String charset = "UTF-8";

		/////////
		// 2016-07-08 Importe debe estar formateado en americano con dos decimales al final:
		double importe = Subrutinas.parse_double( recurrenteData_inOut.getAmt() );
		DecimalFormat formateador = (DecimalFormat)NumberFormat.getInstance( Locale.US );
		formateador.applyPattern( "#,###,###,##0.00" );
		String amountFomatted = formateador.format( importe );
		/////////

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		Map<String,String> headers = new HashMap<String, String>();

		params.add(new BasicNameValuePair("USER", paypal.getCredentials().getUser()));
		params.add(new BasicNameValuePair("PWD", paypal.getCredentials().getPassword()));
		params.add(new BasicNameValuePair("SIGNATURE", paypal.getCredentials().getSignature()));
		params.add(new BasicNameValuePair("METHOD", "CreateRecurringPaymentsProfile"));
		params.add(new BasicNameValuePair("VERSION", paypal.getVersion()));

		params.add(new BasicNameValuePair("TOKEN", token));
		params.add(new BasicNameValuePair("PAYERID", recurrenteData_inOut.getPayerID()));

		params.add(new BasicNameValuePair("PROFILESTARTDATE",recurrenteData_inOut.getProfileStartDate()));	//:20XX-03-05T03:00:00
		params.add(new BasicNameValuePair("DESC",recurrenteData_inOut.getDesc()));							//=Movie clips
		params.add(new BasicNameValuePair("BILLINGPERIOD",recurrenteData_inOut.getBillingPeriod()));		//=Month
		params.add(new BasicNameValuePair("BILLINGFREQUENCY",recurrenteData_inOut.getBillingFrequency()));	//=12
		params.add(new BasicNameValuePair("AMT",amountFomatted));											//=1.00
		params.add(new BasicNameValuePair("CURRENCYCODE",recurrenteData_inOut.getCurrencyCode()));			//=USD
		params.add(new BasicNameValuePair("EMAIL",recurrenteData_inOut.getPayerEmail()));					//=payername@bbb.net

//		params.add(new BasicNameValuePair("L_PAYMENTREQUEST_0_ITEMCATEGORY0","Digital"));					//=Digital Me lleg� a dar el error: "10004 : You are not signed up to accept payment for digitally delivered goods."
		params.add(new BasicNameValuePair("L_PAYMENTREQUEST_0_NAME0",recurrenteData_inOut.getDesc()));		//=Kitty Antics
		params.add(new BasicNameValuePair("L_PAYMENTREQUEST_0_AMT0",amountFomatted));						//=1.00
		params.add(new BasicNameValuePair("L_PAYMENTREQUEST_0_QTY0","1"));									//=1

		params.add(new BasicNameValuePair("TRIALBILLINGPERIOD",recurrenteData_inOut.getTrialBillingPeriod()));			// TRIALBILLINGPERIOD: Day Week SemiMonth Month Year
		params.add(new BasicNameValuePair("TRIALBILLINGFREQUENCY",recurrenteData_inOut.getTrialBillingFrequency()));	// TRIALBILLINGFREQUENCY
		params.add(new BasicNameValuePair("TRIALAMT",recurrenteData_inOut.getTrialAmt()));								// TRIALAMT
		params.add(new BasicNameValuePair("TRIALTOTALBILLINGCYCLES",recurrenteData_inOut.getTrialBillingCycles()));		// TRIALTOTALBILLINGCYCLES

//		params.add(new BasicNameValuePair("INITAMT",amountFomatted));										//INITIAL PAYMENT

		String payload = "";

		IHttpClient clientBuilder = new TLSHttpClient("TLSv1.2");
		IHttpMethods httpMethods = new ProxyHttpMethods(charset);
		try {
			resp = httpMethods.doPost(clientBuilder, paypal.getUrl(), params, headers, payload);
		} catch (StExcepcion e) {
			logger.a(e.getMessage()).pln();
		}
		httpMethods = null;

		logger.a(resp).pln();
		String[] resHttp = resp.split("\\^");

		PaypalResponse paypalResponse = new PaypalResponse();
		PaypalFailure paypalFailure = null;

		if (resHttp!=null && resHttp.length==2) {
			String[] resMessage = resHttp[1].split("&");
			for (int i = 0; i < resMessage.length; i++) {
				String[] fieldValue = resMessage[i].split("=");
				if (fieldValue!=null && fieldValue.length==2) {
					if ("TIMESTAMP".equals(fieldValue[0])) {
						try {
							paypalResponse.setTimeStamp(java.net.URLDecoder.decode(fieldValue[1], charset));
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					if ("CORRELATIONID".equals(fieldValue[0])) {
						try {
							paypalResponse.setCorrelationID(java.net.URLDecoder.decode(fieldValue[1], charset));
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					if ("ACK".equals(fieldValue[0])) {
						try {
							paypalResponse.setAcknowledge(java.net.URLDecoder.decode(fieldValue[1], charset));
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					if ("TOKEN".equals(fieldValue[0])) {
						try {
							paypalResponse.setToken(java.net.URLDecoder.decode(fieldValue[1], charset));
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					if ("VERSION".equals(fieldValue[0])) {
						try {
							paypalResponse.setVersion(java.net.URLDecoder.decode(fieldValue[1], charset));
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					if ("BUILD".equals(fieldValue[0])) {
						try {
							paypalResponse.setBuild(java.net.URLDecoder.decode(fieldValue[1], charset));
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					//Campos de errores

					if (fieldValue[0].startsWith("L_ERRORCODE")) {
						paypalFailure = new PaypalFailure();
						try {
							paypalFailure.setErrorCode(java.net.URLDecoder.decode(fieldValue[1], charset));
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					if (fieldValue[0].startsWith("L_SHORTMESSAGE")) {
						try {
							if (paypalFailure!=null) {
								paypalFailure.setShortMessage(java.net.URLDecoder.decode(fieldValue[1], charset));
							}
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					if (fieldValue[0].startsWith("L_LONGMESSAGE")) {
						try {
							if (paypalFailure!=null) {
								paypalFailure.setLongMessage(java.net.URLDecoder.decode(fieldValue[1], charset));
							}
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					if (fieldValue[0].startsWith("L_SEVERITYCODE")) {
						try {
							if (paypalFailure!=null) {
								paypalFailure.setSeverityCode(java.net.URLDecoder.decode(fieldValue[1], charset));
								paypalResponse.getErrors().add(paypalFailure);
								paypalFailure = null;
							}
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

					// 2016-11-03. Recurring Payments:
					if ("PROFILEID".equals(fieldValue[0])) {
						try {
							paypalResponse.setProfileId(java.net.URLDecoder.decode(fieldValue[1], charset));
							recurrenteData_inOut.setProfileId( paypalResponse.getProfileId() );
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}
					if ("PROFILESTATUS".equals(fieldValue[0])) {
						try {
							paypalResponse.setProfileStatus(java.net.URLDecoder.decode(fieldValue[1], charset));
							recurrenteData_inOut.setProfileStatus( paypalResponse.getProfileStatus() );
						} catch (UnsupportedEncodingException e) {
							logger.a(fieldValue[0]).a(": ").a(e.getMessage()).pln();
						}
					}

				}
			}
		}

		return paypalResponse;
	}

	//////////////////////////
	public static boolean ipn_ack_paso1d2(HttpServletResponse response) {
		Logger logger =  Logger.newLogger("Paypal|ipn_ack_paso1d2");
		PrintWriter out = null;
		try {
//			response.setContentType("application/json");
			out = response.getWriter();
//			out.print( "" );
			out.close();
			
			return true;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	public static String[] ipn_ack_paso2d2(HttpServletRequest request, HttpServletResponse response, String ipn_url, String mensajeOriginal) {
		Logger logger =  Logger.newLogger("Paypal|ipn_ack_paso2d2");
		String resp = "";
		final String charset = "UTF-8";
		
		String url = ipn_url + "?cmd=_notify-validate&" + mensajeOriginal;
		///////////////////////
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		Map<String,String> headers = new HashMap<String, String>();
		String payload = "";
//		String url_requester = request.getScheme()
//			      + "://"
//			      + request.getServerName()
//			      + ":"
//			      + request.getServerPort()
//			      + request.getRequestURI();
//		for (Enumeration<String> e = request.getHeaderNames(); e.hasMoreElements();) {
//		    String name = (String) e.nextElement();
//		    String value = request.getHeader( name );
//		    headers.put( name, value );
//		 }
//		for (Enumeration<String> e = request.getParameterNames(); e.hasMoreElements();) {
//		    String name = (String) e.nextElement();
//		    String[] values = request.getParameterValues( name );
//		    for ( int i=0; i < values.length; i ++ ) {
//				params.add(new BasicNameValuePair( name, values[i] ));
//		    }
//		}
		///////////////////////

		IHttpClient clientBuilder = new TLSHttpClient("TLSv1.2");
		IHttpMethods httpMethods = new ProxyHttpMethods(charset);
		try {
			resp = httpMethods.doPost(clientBuilder, url, params, headers, payload);
		} catch (StExcepcion e) {
			logger.a(e.getMessage()).pln();
		}
		httpMethods = null;

		logger.a(resp).pln();

		//200^TOKEN=EC%2d107354194G272734A&BILLINGAGREEMENTACCEPTEDSTATUS=0&CHECKOUTSTATUS=PaymentActionNotInitiated&TIMESTAMP=2016%2d06%2d19T19%3a37%3a19Z&CORRELATIONID=96f2899dc44be&ACK=Success&VERSION=78&BUILD=000000&CURRENCYCODE=EUR&AMT=10%2e00&SHIPPINGAMT=0%2e00&HANDLINGAMT=0%2e00&TAXAMT=0%2e00&INSURANCEAMT=0%2e00&SHIPDISCAMT=0%2e00&PAYMENTREQUEST_0_CURRENCYCODE=EUR&PAYMENTREQUEST_0_AMT=10%2e00&PAYMENTREQUEST_0_SHIPPINGAMT=0%2e00&PAYMENTREQUEST_0_HANDLINGAMT=0%2e00&PAYMENTREQUEST_0_TAXAMT=0%2e00&PAYMENTREQUEST_0_INSURANCEAMT=0%2e00&PAYMENTREQUEST_0_SHIPDISCAMT=0%2e00&PAYMENTREQUEST_0_INSURANCEOPTIONOFFERED=false&PAYMENTREQUESTINFO_0_ERRORCODE=0
		String[] resHttp = resp.split("\\^");
		return resHttp;

	}
	//////////////////////////

}
