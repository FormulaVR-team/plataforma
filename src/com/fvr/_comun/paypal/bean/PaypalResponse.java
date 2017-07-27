package com.fvr._comun.paypal.bean;

import java.util.ArrayList;
import java.util.List;

public class PaypalResponse {
	private String token;
	private String correlationID;
	private String acknowledge;
	private String timeStamp;
	private String version;
	private String build;
	
	// 2016-11-03. Ampliado para Recurring Payments:
	private String profileId;
	private String profileStatus;
	
	
	private List<PaypalFailure> errors = new ArrayList<PaypalFailure>();

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getCorrelationID() {
		return correlationID;
	}
	public void setCorrelationID(String correlationID) {
		this.correlationID = correlationID;
	}
	public String getAcknowledge() {
		return acknowledge;
	}
	public void setAcknowledge(String acknowledge) {
		this.acknowledge = acknowledge;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getBuild() {
		return build;
	}
	public void setBuild(String build) {
		this.build = build;
	}
	public List<PaypalFailure> getErrors() {
		return errors;
	}
	public void setErrors(List<PaypalFailure> errors) {
		this.errors = errors;
	}
	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	public String getProfileStatus() {
		return profileStatus;
	}
	public void setProfileStatus(String profileStatus) {
		this.profileStatus = profileStatus;
	}


}
