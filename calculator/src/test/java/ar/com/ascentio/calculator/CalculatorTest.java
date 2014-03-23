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

    public void testSum1()
    {	
        assertEquals(27.0, Calculator.eval("1+3+3+20"));
    }
    
    public void testMin1()
    {	
        assertEquals(11.0, Calculator.eval("20-5-4"));
    }
    
    public void testMin2()
    {	
        assertEquals(-7.0, Calculator.eval("20-27"));
    }
    
    public void testMult1()
    {	
        assertEquals(120.0, Calculator.eval("2*3*4*5"));
    }
    
    public void testDiv1()
    {	
        assertEquals(2.0, Calculator.eval("120/60"));
    }
    
    public void testPar1()
    {	
        assertEquals(2.0, Calculator.eval("10/(6-1)"));
    }
    
    public void testPar2()
    {	
        assertEquals(27.0, Calculator.eval("((((((((((27))))))))))"));
    }
    
    public void testPrecendence1()
    {	
        assertEquals(10.0, Calculator.eval("1+3*3"));
    }
    
    public void testPrecendence2()
    {	
        assertEquals(10.0, Calculator.eval("3*3+1"));
    }
    
    public void testUnaryMinus1()
    {	
        assertEquals(-1.0, Calculator.eval("-1"));
    }
    
    public void testUnaryMinus2()
    {	
        assertEquals(-8.0, Calculator.eval("-3*3+1"));
    }
    
    public void testUnaryMinus3()
    {	
        assertEquals(-8.0, Calculator.eval("-(3*3)+1"));
    }
    
    public void testUnaryPlus1()
    {	
        assertEquals(5.0, Calculator.eval("+5"));
    }
    
    public void testUnaryPlus2()
    {	
        assertEquals(16.0, Calculator.eval("+3*+5+1"));
    }
    
    public void testUnaryPlus3()
    {	
        assertEquals(30.0, Calculator.eval("+(5*5)+5"));
    }
    
    public void testUnaryMinus4()
    {
        assertEquals(-10.0, Calculator.eval("-((3*3)+1)"));
    }
    
    public void testLog1()
    {
        assertEquals(1.0, Calculator.eval("log 10"));
    }
    
    public void testLog2()
    {
        assertTrue(Double.isNaN(Calculator.eval("log -10")));
    }
    
    public void testLog3()
    {
        assertEquals(2.0, Calculator.eval("log ((-3*4)+12+(200-100))"));
    }
    
    public void testLog4()
    {
        assertEquals(1.5, Calculator.eval("log 1000/2"));
    }
}
