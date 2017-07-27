package com.fvr._comun.TPV_LaCaixa;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.fvr.FuentesDeDatos.BDConexion;
import com.fvr._comun.Subrutinas;
import com.fvr._comun._K;

import sis.redsys.api.ApiMacSha256;

public class TPV_API implements Serializable {

	private static final long serialVersionUID = 538236562186654757L;
	
	private BDConexion m_dataBase;
	
	public class FormStruct {
		public String ds_SignatureVersion = null;
		public String ds_MerchantParameters = null;
		public String ds_Signature = null;
	}

	public TPV_API(BDConexion dataBase) { m_dataBase = dataBase; }

	public String prepareFormData(String url_base, FormStruct form, String order, double amount_dbl, String token_id, List<String> lstErrores) {
		String resultado = null;

    	String amount = ""+(long)(amount_dbl * 100.0);	// Truncar!!

		///////////////////////////
		try {
			ApiMacSha256 apiMacSha256 = new ApiMacSha256();
			String claveModuloAdmin = Subrutinas.getDBValueFromKey(m_dataBase, _K.TPV_LaCaixa_claveCifrado);

			apiMacSha256.setParameter("DS_MERCHANT_MERCHANTCODE", Subrutinas.getDBValueFromKey(m_dataBase, _K.TPV_LaCaixa_Ds_Merchant_MerchantCode) );
			apiMacSha256.setParameter("DS_MERCHANT_TERMINAL", Subrutinas.getDBValueFromKey(m_dataBase, _K.TPV_LaCaixa_Ds_Merchant_Terminal) );
			apiMacSha256.setParameter("DS_MERCHANT_ORDER", order);
			apiMacSha256.setParameter("DS_MERCHANT_AMOUNT", amount);	// dos últimos dígitos son los 2 céntimos para euro.
			apiMacSha256.setParameter("DS_MERCHANT_CURRENCY", Subrutinas.getDBValueFromKey(m_dataBase, _K.TPV_LaCaixa_Ds_Merchant_Currency) );
			apiMacSha256.setParameter("DS_MERCHANT_TRANSACTIONTYPE", "0");
			apiMacSha256.setParameter("DS_MERCHANT_MERCHANTURL", url_base + "/");
			apiMacSha256.setParameter("DS_MERCHANT_URLOK", url_base + "/FvrServlet?ACC=TPV_OK&KEY="+token_id);
			apiMacSha256.setParameter("DS_MERCHANT_URLKO", url_base + "/FvrServlet?ACC=TPV_KO&KEY="+token_id);

			form.ds_MerchantParameters = apiMacSha256.createMerchantParameters();
			form.ds_Signature = apiMacSha256.createMerchantSignature( claveModuloAdmin );
			form.ds_SignatureVersion = Subrutinas.getDBValueFromKey(m_dataBase, _K.TPV_LaCaixa_version);
			
			resultado = Subrutinas.getDBValueFromKey(m_dataBase, _K.TPV_LaCaixa_URL);
			
		} catch (UnsupportedEncodingException e) {
			lstErrores.add(e.getMessage());
		} catch (InvalidKeyException e) {
			lstErrores.add(e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			lstErrores.add(e.getMessage());
		} catch (IllegalStateException e) {
			lstErrores.add(e.getMessage());
		} catch (NoSuchPaddingException e) {
			lstErrores.add(e.getMessage());
		} catch (InvalidAlgorithmParameterException e) {
			lstErrores.add(e.getMessage());
		} catch (IllegalBlockSizeException e) {
			lstErrores.add(e.getMessage());
		} catch (BadPaddingException e) {
			lstErrores.add(e.getMessage());
		}

		///////////////////////////
		
		return resultado;
	}

}
