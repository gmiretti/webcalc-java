package ar.com.ascentio.httpclient;

public interface HTTPClientFactory {

	public abstract HTTPClient getClient(String url);
}
