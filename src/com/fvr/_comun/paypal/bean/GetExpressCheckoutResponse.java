package com.fvr._comun.paypal.bean;

public class GetExpressCheckoutResponse extends PaypalResponse {
	/*Payer Information Fields*/

	/**
	 *  Email address of buyer
	 */
	private String payerEmail;

	/**
	 *  Unique PayPal Customer Account identification number
	 */
	private String payerId;

	/**
	 * Status of buyer. It is one of the following values:
	 * <ul>
	 * <li>verified</li>
	 * <li>unverified</li>
	 * </ul>
	 */
	private String payerStatus;
	
	/**
	 * Buyer's country of residence in the form of ISO standard 3166 two-character country codes
	 */
	private String payerCountryCode;
	
	/**
	 * Buyer's business name
	 */
	private String payerBusinessName;
	
	/**
	 * Buyer's first name
	 */
	private String payerFirstName;
	
	/**
	 * Buyer's last name
	 */
	private String payerLastName;

	public String getPayerEmail() {
		return payerEmail;
	}

	public void setPayerEmail(String payerEmail) {
		this.payerEmail = payerEmail;
	}

	public String getPayerId() {
		return payerId;
	}

	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}

	public String getPayerStatus() {
		return payerStatus;
	}

	public void setPayerStatus(String payerStatus) {
		this.payerStatus = payerStatus;
	}

	public String getPayerCountryCode() {
		return payerCountryCode;
	}

	public void setPayerCountryCode(String payerCountryCode) {
		this.payerCountryCode = payerCountryCode;
	}

	public String getPayerBusinessName() {
		return payerBusinessName;
	}

	public void setPayerBusinessName(String payerBusinessName) {
		this.payerBusinessName = payerBusinessName;
	}

	public String getPayerFirstName() {
		return payerFirstName;
	}

	public void setPayerFirstName(String payerFirstName) {
		this.payerFirstName = payerFirstName;
	}

	public String getPayerLastName() {
		return payerLastName;
	}

	public void setPayerLastName(String payerLastName) {
		this.payerLastName = payerLastName;
	}

}
