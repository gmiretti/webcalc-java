package ar.com.ascentio.httpclient;

public interface HTTPClient {

	public abstract void setHeader(String property, String value);

	public abstract HTTPResponse get();

	public abstract HTTPResponse post(String body);

	public abstract HTTPResponse put(String body);

}