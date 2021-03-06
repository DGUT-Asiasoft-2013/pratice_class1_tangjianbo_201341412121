package com.example.helloworld.api;


import java.net.CookieManager;
import java.net.CookiePolicy;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class Server {
	
	public static final String serverAddress = "http://172.27.0.45:8080/membercenter/"; 
	static OkHttpClient client;
	static{
		CookieManager cookieManager = new CookieManager();
		cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
		
		client = new OkHttpClient.Builder()
				.cookieJar(new JavaNetCookieJar(cookieManager))
				.build();
	}
	
	public static OkHttpClient getSharedClient(){
		return client;}

	public static Request.Builder requestBuilderWithApi(String api){
		return new Request.Builder()
				.url(serverAddress+"api/"+api);
	}
}
