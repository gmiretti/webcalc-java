package ar.com.ascentio.httpclient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
//import org.apache.log4j.Logger;


public class HTTPConnection {
	
	//static Logger logger = Logger.getLogger(HTTPConnection.class);
	
	private HttpURLConnection httpConnection = null;
	
	public HTTPConnection(String url) {
		try {
			URL urlObj = new URL(url);
			
			this.httpConnection = (HttpURLConnection) urlObj.openConnection();
			httpConnection.setConnectTimeout(5000);
			httpConnection.setReadTimeout(5000);
			httpConnection.setDoOutput(true); 
			httpConnection.setDoInput(true);
		}
		catch(Exception e) {
			//logger.error(e);
		}
	}
	
	public void setHeader(String property, String value) {
		httpConnection.setRequestProperty(property, value);
	}
	
	public HTTPResponse get() {
		
		//logger.debug("GET " + httpConnection.getURL().toString());
		
		HTTPResponse response = new HTTPResponse(999, "Internal Error");
		
		try {
			httpConnection.setRequestMethod("GET");
			response = buildResponse();
		}  catch (Exception e) {
			//logger.error(e);
		}	
		
		return response;
	}
	
	public HTTPResponse post(String body) {
		
		//logger.debug("POST " + httpConnection.getURL().toString());
		//logger.debug("body: " + body);
		
		HTTPResponse response = new HTTPResponse(999, "Internal Error");
		
		try {
			httpConnection.setRequestMethod("POST");
			writeBody(body);
			response = buildResponse();
			
		} catch (Exception e) {
			//logger.error(e);
		}
		
		return response;
	}
	
	public HTTPResponse put(String body) {
		
		//logger.debug("PUT " + httpConnection.getURL().toString());
		//logger.debug("body: " + body);
		
		HTTPResponse response = new HTTPResponse(999, "Internal Error");
		
		try {
			httpConnection.setRequestMethod("PUT");
			writeBody(body);
			response = buildResponse();
			
		} catch (Exception e) {
			//logger.error(e);
		}
		
		return response;
	}
	
	private void writeBody(String body) {
		DataOutputStream wr;
		try {
			wr = new DataOutputStream(httpConnection.getOutputStream());
			wr.writeBytes(body);
			wr.flush();
			wr.close();
		} catch (IOException e) {
			//logger.error(e);
		}
	}
	
	private HTTPResponse buildResponse() throws IOException {
		
		String inputLine;
		StringBuffer responseData = new StringBuffer();
		BufferedReader in;
		int responseCode = httpConnection.getResponseCode();
		if (responseCode >= 200 && responseCode <= 299) {
			in = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));	
		}
		else {
			in = new BufferedReader(new InputStreamReader(httpConnection.getErrorStream()));	
		}
		
		while ((inputLine = in.readLine()) != null) {
			responseData.append(inputLine);
		}
		in.close();
		
		HTTPResponse response = new HTTPResponse(responseCode, responseData.toString());
		
		return response;
	}
}
