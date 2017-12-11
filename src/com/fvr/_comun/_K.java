package com.fvr._comun;

import java.io.File;

/**
 * @author Emilio Estecha 2013
 *
 */
public class _K {
	
	public static final String unidadIntercambio = "c:";
	
	public static final String caminoInterfaz    = unidadIntercambio +File.separator+"datos"+File.separator+"SisExt"+File.separator;
	public static final String caminoSalida      = caminoInterfaz + "llamadas" + File.separator;
	public static final String caminoEntrada     = caminoInterfaz + "retornos" + File.separator;
	public static final String caminoExecExterno = caminoInterfaz + "exec"     + File.separator;
	
	public static final String ejecutableExterno = "SE.bat";
	
	public static final String extFicParm = ".txt";
	
	public static final String sepFld = "\t";
	public static final String sepReg = "\r\n";
	public static final String sepReg_0x0D = "\r";
	public static final String sepReg_0x0A = "\n";

	public static final String AVATAR_IMG_UNKNOWN = "./resBS/img/unknown_120.png";
	public static final String AVATAR_FLAG_UNKNOWN = "./resBS/img/unknown_flag_32.png";

	public static final long   TIME_SLICE = 10L;		// Menor tiempo vendible. Granularidad de la tabla "timeTabkeReference"
	public static final String SI = "S";
	public static final String NO = "N";
	public static final String OK = "OK";
	public static final String SYS = "SYS";
	public static final String CUENTA_DE_SISTEMA = "SYS";
	public static final String FECHA_ISO_MIN = "1950-01-01";
	public static final String FECHA_ISO_MAX = "2099-12-31";
	public static final String GENDER_MALE = "M";
	public static final String GENDER_FEMALE = "F";
	
	public static final String ROL_ADMIN = "A";
	public static final String ROL_FRANQUICIA = "F";
	public static final String ROL_USER = "U";
	public static final String PWD_EN_BLANCO = "d41d8cd98f00b204e9800998ecf8427e";
	
	public static final String CHART_min_startdate_REF = "0900";	// Mínimo por defecto para escala de gráfico horario.
	public static final String CHART_max_startdate_REF = "2200";	// Máximo por defecto para escala de gráfico horario.
	public static final String PA_CHART_min_startdate = "chart_min_startdate";	// Mínimo para escala de gráfico horario.
	public static final String PA_CHART_max_startdate = "chart_max_startdate";	// Máximo para escala de gráfico horario.

	public static final String PA_KEY_VERSION = "VERSION";											// Fecha juliana: 17148
	
	public static final String PA_KEY_RS_LAST_KEY = "RS_LAST_KEY";									// Siguiente clave de reserva
	public static final String PA_KEY_RS_MIN_FEC = "RS_MIN_FEC";									// Fecha ISO: 2017-09-01

	public static final String PA_KEY_WEEKLY_CALENDAR= "WEEKLY_CALENDAR";	// [DLMXJVS]. "0..3456" => "Abierto todos menos lunes y martes" (Un punto significa cerrado ese día)

	public static final String PA_KEY_URL_WEB_PAGE = "URL_WEB_PAGE";								// Url de la página web: https:www.formulavr.net
	
	public static final String PA_KEY_COCKPIT_CONFIG_MASTER_FILE_NAME = "COCKPIT_CONFIG_MASTER_FILE_NAME";	// Fichero para inyectar en el cockpit cuando se ejecuta una reserva.
	public static final String PA_KEY_COCKPIT_CONFIG_MASTER_FILE_CONTENT = "COCKPIT_CONFIG_MASTER_FILE_CONTENT";	// Fichero para inyectar en el cockpit cuando se ejecuta una reserva.

	// SMTP:
	public static final String PA_KEY_DOMAIN = "DOMAIN";											// no-reply@formulavr.net
	public static final String PA_KEY_SMTP_HOST_NAME = "SMTP_HOST_NAME";							// smtp.formulavr.net
	public static final String PA_KEY_SMTP_AUTH_USER = "SMTP_AUTH_USER";							// info@formulavr.net
	public static final String PA_KEY_SMTP_AUTH_PWD = "SMTP_AUTH_PWD";								// info.formulavr.net
	public static final String PA_KEY_SMTP_MAIL_PORT = "SMTP_MAIL_PORT";							// 465

	////////////////////
	// PAYPAL MERCHANT "Formula VR":
	public static final String PA_KEY_paypal_USR = "Paypal_MERCHANT_FVR_USR";
	public static final String PA_KEY_paypal_PWD = "Paypal_MERCHANT_FVR_PWD";
	public static final String PA_KEY_paypal_SIGNATURE = "Paypal_MERCHANT_FVR_SIGNATURE";
	//Paypal
	public static final String PA_KEY_PAYPAL_MERCHANT_URL_ACCESS = "Paypal_MERCHANT_URL_ACCESS";	// TEST: https://www.sandbox.paypal.com/es/webapps/mpp/merchant
	public static final String PA_KEY_PAYPAL_MERCHANT_URL_REG = "Paypal_MERCHANT_URL_REG";	// TEST: https://www.sandbox.paypal.com/es/webapps/mpp/merchant
	/**URL para llegar al endpoint de Paypal API
	 * <ul>
	 * <li>Desarrollo/Test = https://api-3t.sandbox.paypal.com/nvp</li>
	 * <li>Produccion = https://api-3t.paypal.com/nvp</li>
	 * </ul>
	 */
	public static final String PA_KEY_PAYPAL_CHECKOUT_EXPRESS_API_URL = "Paypal_API_Checkout_Express_URL";
	/**
	 * Version de la API de Paypal que se utiliza, ahora la ultima version es '204'
	 * 
	 * Se puede comprobar accediendo a: https://www.paypalobjects.com/wsdl/PayPalSvc.wsdl ,
	 * comprobando el valor ns:version="xxxx"
	 */
	public static final String PA_KEY_PAYPAL_CHECKOUT_EXPRESS_API_VERSION = "Paypal_API_Checkout_Express_Version";
	/**
	 * URL a la que redireccionar al usuario hacia Paypal
	 * <ul>
	 * <li>Desarrollo/Test = https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token=</li>
	 * <li>Prod<uccion = https://www.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token=</li>
	 * </ul>
	 */
	public static final String PA_KEY_PAYPAL_CHECKOUT_EXPRESS_REDIRECT_URL = "Paypal_Redirect_Checkout_Express_URL";
	/**
	 * URL del endpoint en Billin que recepcionara el callback de return y el de cancel
	 * 
	 * {dominio}/Billin/Paypal
	 * 
	 * Por ejemplo: para desarrollo local se puede utilizar NGROK para "publicar" tu localhost y poder
	 * recibir el callback de Paypal.
	 */
	public static final String PA_KEY_PAYPAL_CHECKOUT_EXPRESS_CALLBACK_URL = "Paypal_Callback_Checkout_Express_URL";
	public static final String PA_KEY_PAYPAL_IPN_URL = "Paypal_IPN_URL";

	////////////////////
	// Valores de "rs_pay_status"
	public static final String PAY_STS_PAYPAL_PreConfirmado = "--";	
	public static final String PAY_STS_CASH_PreConfirmado = "CASH_PDT";	

	public static final String PAY_STS_CASH_Completado = "CASH_OK";
	public static final String PAY_STS_GRATIS_Completado = "GRATIS_OK";
	public static final String PAY_STS_TPV_Completado = "TPV_OK";
	public static final String PAY_STS_PAYPAL_Completado = "PAYPAL_OK";

	public static final String PAY_STS_TPV_Fallido = "TPV_KO";
	public static final String PAY_STS_PAYPAL_Fallido = "PAYPAL_KO";

	// Eventos de pago:
	public static final String EV_PAYPAL_PAGO_OK = "Pago con Paypal OK";
	public static final String EV_TPV_PAGO_OK = "Pago con TPV OK";
	public static final String EV_TPV_PAGO_EVENTO_OK = "Pago EVENTO TPV OK";

	////////////////////
	// TPV LaCaixa
	public static final String TPV_LaCaixa_Ds_Merchant_MerchantCode = "TPV_LaCaixa_Ds_Merchant_MerchantCode";
	public static final String TPV_LaCaixa_Ds_Merchant_Terminal = "TPV_LaCaixa_Ds_Merchant_Terminal";
	public static final String TPV_LaCaixa_Ds_Merchant_Currency = "TPV_LaCaixa_Ds_Merchant_Currency";
	public static final String TPV_LaCaixa_claveCifrado = "TPV_LaCaixa_claveCifrado";
	public static final String TPV_LaCaixa_tipoCifrado = "TPV_LaCaixa_tipoCifrado";
	public static final String TPV_LaCaixa_version = "TPV_LaCaixa_version";
	public static final String TPV_LaCaixa_URL = "TPV_LaCaixa_URL";
	////////////////////

	// USER DEFAULT VALUES: (RESERVA EXPRESS)
	public static final String USER_DEFAULT_author = _K.CUENTA_DE_SISTEMA;
	public static final String USER_DEFAULT_birth_day = _K.FECHA_ISO_MIN;
	public static final String USER_DEFAULT_first_name = "pending...";
	public static final String USER_DEFAULT_last_name = "pending...";
	public static final String USER_DEFAULT_gender = _K.GENDER_MALE;
	public static final String USER_DEFAULT_nick = "";
	public static final String USER_DEFAULT_avatar = "";

	////////////////////////
	// URL del módulo de gamificación:
	public static final String PA_MG_URL = "MG_URL";

	////////////////////////
	// MOLDES DE CORREOS SALIENTE:
	public static final String PA_KEY_MAIL_NEW_USER_REGISTRATION = "MAIL_NEW_USER_REGISTRATION";			// También es la ACCIÓN en json de TK.
	public static final String PA_KEY_SMTP_MAIL_REGISTER_FROM = "SMTP_MAIL_REGISTER_FROM";						// no-reply@formulavr.net
	public static final String PA_KEY_SMTP_MAIL_REGISTER_SUBJECT = "SMTP_MAIL_REGISTER_SUBJECT";				// FORMULA-VR: Registro nuevo usuario

	public static final String PA_KEY_MAIL_CHANGE_PASSWORD = "MAIL_CHANGE_PASSWORD";						// También es la ACCIÓN en json de TK.
	public static final String PA_KEY_SMTP_MAIL_CHANGE_PASSWORD_FROM = "SMTP_MAIL_CHANGE_PASSWORD_FROM";		// no-reply@formulavr.net
	public static final String PA_KEY_SMTP_MAIL_CHANGE_PASSWORD_SUBJECT = "SMTP_MAIL_CHANGE_PASSWORD_SUBJECT";	// FORMULA-VR: Contraseña usuario

	public static final String PA_KEY_MAIL_RESERVE_CONFIRM = "MAIL_RESERVE_CONFIRM";						// También es la ACCIÓN en json de TK.
	public static final String PA_KEY_SMTP_MAIL_RESERVE_CONFIRM_FROM = "SMTP_MAIL_RESERVE_CONFIRM_FROM";		// no-reply@formulavr.net
	public static final String PA_KEY_SMTP_MAIL_RESERVE_CONFIRM_SUBJECT = "SMTP_MAIL_RESERVE_CONFIRM_SUBJECT";	// FORMULA-VR: Confirmar reserva
	////////////////////////

	////////////////////////
	// CLAVES TK ESPECIALES:
	public static final String TK_ACC_USERCHANGED = "USERCHANGED";						// Es la ACCIÓN en json de TK.
	////////////////////////

}
