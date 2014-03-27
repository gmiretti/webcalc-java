package ar.com.ascentio.calcclient;

import ar.com.ascentio.httpclient.HTTPClient;
import ar.com.ascentio.httpclient.HTTPClientFactory;
import static org.mockito.Mockito.*;

public class MockHTTPClientFactory implements HTTPClientFactory {

	private HTTPClient mockClient = null;
	
	MockHTTPClientFactory() {
		mockClient = mock(HTTPClient.class);
	}
	
	public HTTPClient getClient(String url) {
		
		return mockClient;
	}
}
