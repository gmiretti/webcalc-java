package ar.com.ascentio.calcclient;

import org.apache.log4j.Logger;

import ar.com.ascentio.httpclient.HTTPConnection;
import ar.com.ascentio.httpclient.HTTPResponse;

import com.google.gson.Gson;

public class CalcService {
	
	static Logger logger = Logger.getLogger(CalcService.class);
	
	//private CPConfig cpConfig;
	private String baseUrl = null;
	private AppConfig config = null;
	
	CalcService(String baseUrl, AppConfig config) {
		this.baseUrl = baseUrl;
		this.config = config;
	}

	public void postEval(EvalRequest evalRequest) {
		
		try {
			String url = baseUrl + "/eval";
			HTTPConnection httpConnection = new HTTPConnection(url);
		
			httpConnection.setHeader("Content-Type", "application/json");
			httpConnection.setHeader("Accept", "application/json");
			String appId = config.appId != null ? config.appId : "";
			httpConnection.setHeader("api-key", appId);
			String userId = config.userId != null ? config.userId : "";
			httpConnection.setHeader("x-userid", userId);
			
			logger.debug("apiKey: " + appId + ", userId: " + userId);
			
			Gson gson = new Gson();
			String body = gson.toJson(evalRequest);
			
			HTTPResponse response = httpConnection.put(body);
			int responseCode = response.code;
			String responseData = response.data;
			logger.info("HTTP Response code: " + responseCode);
			logger.info("HTTP response data: " + responseData);

		} catch(Exception e) {
			logger.error(e);
		}
	}
}
