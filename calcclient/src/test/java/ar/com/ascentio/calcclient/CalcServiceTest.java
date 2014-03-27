package ar.com.ascentio.calcclient;

import com.google.gson.Gson;

import ar.com.ascentio.httpclient.HTTPClientFactory;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import static org.mockito.Mockito.*;

/**
 * Unit test for simple App.
 */
public class CalcServiceTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public CalcServiceTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( CalcServiceTest.class );
    }
    
    public void testPostEval() {
    	
    	HTTPClientFactory factory = new MockHTTPClientFactory();
    	CalcService calcService = new CalcService("", factory);
    	String statement = "1+1";
    	EvalRequest evalRequest = new EvalRequest(statement);
    	
    	calcService.postEval(evalRequest);
    	
    	Gson gson = new Gson();
		String body = gson.toJson(evalRequest);
		verify(factory.getClient("any")).setHeader("Content-Type", "application/json");
		verify(factory.getClient("any")).setHeader("Accept", "application/json");
    	verify(factory.getClient("any")).post(body);
    }
}
