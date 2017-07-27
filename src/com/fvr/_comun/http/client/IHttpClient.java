package com.fvr._comun.http.client;


import org.apache.http.impl.client.CloseableHttpClient;

public interface IHttpClient {
	public CloseableHttpClient getClient();
}
