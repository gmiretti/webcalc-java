package ar.com.ascentio.calcclient;

//import org.apache.log4j.Logger;

import ar.com.ascentio.httpclient.HTTPClient;
import ar.com.ascentio.httpclient.HTTPClientFactory;
import ar.com.ascentio.httpclient.HTTPResponse;

import com.google.gson.Gson;

public class CalcService {
	
	//static Logger logger = Logger.getLogger(CalcService.class);
	
	//private CPConfig cpConfig;
	private String baseUrl = null;
	private HTTPClientFactory factory = null;
	
	CalcService(String baseUrl, HTTPClientFactory factory) {
		this.baseUrl = baseUrl; 
		this.factory = factory;
	}

	public EvalResponse postEval (EvalRequest evalRequest) {
		
		EvalResponse evalResponse = null;
		
		try {
			String url = baseUrl + "/eval";
			HTTPClient httpClient = factory.getClient(url);
		
			httpClient.setHeader("Content-Type", "application/json");
			httpClient.setHeader("Accept", "application/json");
			
			Gson gson = new Gson();
			String body = gson.toJson(evalRequest);
			
			HTTPResponse response = httpClient.post(body);
			int responseCode = response.code;
			String responseData = response.data;
			//logger.info("HTTP Response code: " + responseCode);
			//logger.info("HTTP response data: " + responseData);
			
			if (responseCode >= 200 && responseCode <= 299) {
				Gson gsonResp = new Gson();
				evalResponse = gsonResp.fromJson(responseData, EvalResponse.class);	
			}

		} catch(Exception e) {
			//loger.error(e);
		}
		
		return evalResponse;
	}
	
	public Session storeSession(Session session) {
		
		Session responseSession = null;
		
		try {
			String url = baseUrl + "/session";
			HTTPClient httpClient = factory.getClient(url);
		
			httpClient.setHeader("Content-Type", "application/json");
			httpClient.setHeader("Accept", "application/json");
			
			Gson gson = new Gson();
			String body = gson.toJson(session);
			
			HTTPResponse response = httpClient.post(body);
			int responseCode = response.code;
			String responseData = response.data;
			//logger.info("HTTP Response code: " + responseCode);
			//logger.info("HTTP response data: " + responseData);
			
			if (responseCode >= 200 && responseCode <= 299) {
				Gson gsonResp = new Gson();
				responseSession = gsonResp.fromJson(responseData, Session.class);	
			}

		} catch(Exception e) {
			//logger.error(e);
		}
		
		return responseSession;
	}
	
	public Session retrieveSession(String sessionId) {
		
		Session responseSession = null;
		
		try {
			String url = baseUrl + "/session/" + sessionId;
			HTTPClient httpClient = factory.getClient(url);
		
			httpClient.setHeader("Content-Type", "application/json");
			httpClient.setHeader("Accept", "application/json");
			
			HTTPResponse response = httpClient.get();
			int responseCode = response.code;
			String responseData = response.data;
			//logger.info("HTTP Response code: " + responseCode);
			//logger.info("HTTP response data: " + responseData);
			
			if (responseCode >= 200 && responseCode <= 299) {
				Gson gsonResp = new Gson();
				responseSession = gsonResp.fromJson(responseData, Session.class);	
			}

		} catch(Exception e) {
			//logger.error(e);
		}
		
		return responseSession;
	}
}