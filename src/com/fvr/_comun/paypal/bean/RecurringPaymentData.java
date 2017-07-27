package com.fvr._comun.paypal.bean;

import com.fvr._comun.StBean;

public class RecurringPaymentData extends StBean implements java.io.Serializable{

	private static final long serialVersionUID = -6264891217212235722L;
	
	// Recurring Payments Profile Status:
	public static final String STS_ActiveProfile = "ActiveProfile";
	public static final String STS_PendingProfile = "sts_PendingProfile";
	public static final String STS_ExpiredProfile = "sts_ExpiredProfile";
	public static final String STS_SuspendedProfile = "sts_SuspendedProfile";
	public static final String STS_CancelledProfile = "sts_CancelledProfile";

	///////////////////
	// Datos devueltos por al API: "CreateRecurringPaymentsProfile"
	public String profileId;			// PROFILEID 	Clave devuelta por CreateRecurringPaymentsProfile (GUARDARLA!!)
	public String profileStatus;		// PROFILESTATUS 	Clave devuelta por CreateRecurringPaymentsProfile (GUARDARLA!!)

	///////////////////
	// DATOS A CARGAR ANTES DE LLAMAR A "CreateRecurringPaymentsProfile"
	// https://developer.paypal.com/docs/classic/api/merchant/CreateRecurringPaymentsProfile_API_Operation_NVP/
	public String payerID;				// Obtenido de: "GetExpressCheckoutDetails"
	public String profileStartDate;		// PROFILESTARTDATE	("2012-05-11T00:00:00Z") The date when billing for this profile begins. Note: The profile may take up to 24 hours for activation.
	public String desc;					// DESC
	public String billingPeriod;		// BILLINGPERIOD	Day Week SemiMonth Month Year
	public String billingFrequency;		// BILLINGFREQUENCY
	public String amt;					// AMT (Amount to pay)
	public String currencyCode;			// CURRENCYCODE
	public String countryCode;			// COUNTRYCODE
	public String payerEmail;			// Payer EMAIL

	public String maxFailedPayments;	// MAXFAILEDPAYMENTS
	public String AutoBillOutAmt; 		// AUTOBILLOUTAMT 	Agrega al siguiente pago lo pendiente de pagos anteriores.
	
	// Including an Optional TRIAL PERIOD	
	// Required fields for specifying a trial period
	public String trialBillingPeriod;		// TRIALBILLINGPERIOD
	public String trialBillingFrequency;	// TRIALBILLINGFREQUENCY
	public String trialAmt;					// TRIALAMT
	public String trialBillingCycles;		// TRIALTOTALBILLINGCYCLES
	
	// Specifying an INITIAL PAYMENT
	//	You can optionally specify an initial non-recurring payment when the recurring payments profile is created by including the following fields in the CreateRecurringPaymentsProfile request:
	//	Required fields for specifying an initial payment
	public String initAmt;					//	INITAMT
	public String failedInitAmtAction; 	//	FAILEDINITAMTACTION CancelOnFailure" "ContinueOnFailure"
	///////////////////////////////////////////
	///////////////////////////////////////////

	//	Recurring payments IPN messages and email
	//	=========================================
	//	Event IPN																					Buyer	Email
	//	=========																					=====	=====
	//	Profile successfully created																Yes		Yes
	//	Profile creation failed																		Yes		Yes
	//	Profile canceled from paypal.com interface													Yes		Yes
	//	Profile status changed using API															No		Yes
	//	Profile updated using API																	No		Yes
	//	Initial payment either succeeded or failed													Yes		Yes
	//	Payment either succeeded or failed (during either trial period or regular payment period)	Yes		Yes
	//	Outstanding payment either succeeded or failed												Yes		Yes
	//	Maximum number of failed payments reached													Yes		No

	///////////////////////////////////////////
	///////////////////////////////////////////

	public String getPayerID() {
		return payerID;
	}
	public void setPayerID(String payerID) {
		this.payerID = payerID;
	}
	public String getProfileStartDate() {
		return profileStartDate;
	}
	public void setProfileStartDate(String profileStartDate) {
		this.profileStartDate = profileStartDate;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getBillingPeriod() {
		return billingPeriod;
	}
	public void setBillingPeriod(String billingPeriod) {
		this.billingPeriod = billingPeriod;
	}
	public String getBillingFrequency() {
		return billingFrequency;
	}
	public void setBillingFrequency(String billingFrequency) {
		this.billingFrequency = billingFrequency;
	}
	public String getAmt() {
		return amt;
	}
	public void setAmt(String amt) {
		this.amt = amt;
	}
	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getMaxFailedPayments() {
		return maxFailedPayments;
	}
	public void setMaxFailedPayments(String maxFailedPayments) {
		this.maxFailedPayments = maxFailedPayments;
	}
	public String getTrialBillingPeriod() {
		return trialBillingPeriod;
	}
	public void setTrialBillingPeriod(String trialBillingPeriod) {
		this.trialBillingPeriod = trialBillingPeriod;
	}
	public String getTrialBillingFrequency() {
		return trialBillingFrequency;
	}
	public void setTrialBillingFrequency(String trialBillingFrequency) {
		this.trialBillingFrequency = trialBillingFrequency;
	}
	public String getTrialAmt() {
		return trialAmt;
	}
	public void setTrialAmt(String trialAmt) {
		this.trialAmt = trialAmt;
	}
	public String getTrialBillingCycles() {
		return trialBillingCycles;
	}
	public void setTrialBillingCycles(String trialBillingCycles) {
		this.trialBillingCycles = trialBillingCycles;
	}
	public String getInitAmt() {
		return initAmt;
	}
	public void setInitAmt(String initAmt) {
		this.initAmt = initAmt;
	}
	public String getFailedInitAmtAction() {
		return failedInitAmtAction;
	}
	public void setFailedInitAmtAction(String failedInitAmtAction) {
		this.failedInitAmtAction = failedInitAmtAction;
	}
	public String getAutoBillOutAmt() {
		return AutoBillOutAmt;
	}
	public void setAutoBillOutAmt(String autoBillOutAmt) {
		AutoBillOutAmt = autoBillOutAmt;
	}
	public String getProfileStatus() {
		return profileStatus;
	}
	public void setProfileStatus(String profileStatus) {
		this.profileStatus = profileStatus;
	}
	public String getPayerEmail() {
		return payerEmail;
	}
	public void setPayerEmail(String payerEmail) {
		this.payerEmail = payerEmail;
	}

}
