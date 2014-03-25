package ar.com.ascentio.httpclient;

public class HTTPResponse {
	
	public int code;
	public String data;
	
	HTTPResponse(int code, String data) {
		this.code = code;
		this.data = data;
	}
}
