package ar.com.ascentio.calculator;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class CalculatorTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public CalculatorTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( CalculatorTest.class );
    }

    public void testPrecendence1()
    {
    	
        assertEquals(10.0, Calculator.eval("1+3*3"));
    }
    
    public void testPrecendence2()
    {
    	
        assertEquals(10.0, Calculator.eval("3*3+1"));
    }
}
