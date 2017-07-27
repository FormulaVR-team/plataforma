package com.fvr._comun.paypal.classes;

public class PaypalAPI {
	private APICredentials credentials;
	private final String url;
	private final String version;
	
	public PaypalAPI(String url, String version) {
		this.url = url;
		this.version = version;
	}
	
	public void setCredentials(APICredentials newCredentials) {
		this.credentials = newCredentials;
	}
	
	public APICredentials getCredentials() {
		return this.credentials;
	}
	
	public String getUrl() {
		return this.url;
	}
	
	public String getVersion() {
		return this.version;
	}
}
