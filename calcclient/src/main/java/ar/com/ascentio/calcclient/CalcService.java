package ar.com.ascentio.calcclient;

//import org.apache.log4j.Logger;

import ar.com.ascentio.httpclient.HTTPConnection;
import ar.com.ascentio.httpclient.HTTPResponse;

import com.google.gson.Gson;

public class CalcService {
	
	//static Logger logger = Logger.getLogger(CalcService.class);
	
	//private CPConfig cpConfig;
	private String baseUrl = null;
	
	CalcService(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public EvalResponse postEval(EvalRequest evalRequest) {
		
		EvalResponse evalResponse = null;
		
		try {
			String url = baseUrl + "/eval";
			HTTPConnection httpConnection = new HTTPConnection(url);
		
			httpConnection.setHeader("Content-Type", "application/json");
			httpConnection.setHeader("Accept", "application/json");
			
			Gson gson = new Gson();
			String body = gson.toJson(evalRequest);
			
			HTTPResponse response = httpConnection.post(body);
			int responseCode = response.code;
			String responseData = response.data;
			System.out.println(responseData);
			//logger.info("HTTP Response code: " + responseCode);
			//logger.info("HTTP response data: " + responseData);
			
			if (responseCode >= 200 && responseCode <= 299) {
				Gson gsonResp = new Gson();
				evalResponse = gsonResp.fromJson(responseData, EvalResponse.class);	
			}

		} catch(Exception e) {
			//logger.error(e);
		}
		
		return evalResponse;
	}

}