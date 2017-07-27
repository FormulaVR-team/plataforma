package com.fvr._comun.paypal.bean;

public class PaypalFailure {
	private String errorCode;
	private String shortMessage;
	private String longMessage;
	private String severityCode;

	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getShortMessage() {
		return shortMessage;
	}
	public void setShortMessage(String shortMessage) {
		this.shortMessage = shortMessage;
	}
	public String getLongMessage() {
		return longMessage;
	}
	public void setLongMessage(String longMessage) {
		this.longMessage = longMessage;
	}
	public String getSeverityCode() {
		return severityCode;
	}
	public void setSeverityCode(String severityCode) {
		this.severityCode = severityCode;
	}

}
