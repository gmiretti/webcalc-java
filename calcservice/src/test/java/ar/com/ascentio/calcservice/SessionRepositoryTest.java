package ar.com.ascentio.calcservice;

import org.junit.Assert;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class SessionRepositoryTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public SessionRepositoryTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( SessionRepositoryTest.class );
    }

    public void testASessionCanBeRetrieved()
    {
    	SessionRepository repo = null;
		
    	try {
			repo = new SessionRepository();
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
    	String[] statements = {"1+1", "2+2"};
    	String sessionId = "session1";
    	Session session1 = new Session(sessionId, statements);
    	repo.storeSession(session1);
    	Session session2 = repo.getSession(sessionId);
    	
    	assertEquals(session2.sessionId, sessionId);
    	Assert.assertArrayEquals(session2.statements, session1.statements);
    }
    
    public void testStoreSessionOverwritePreviousData()
    {
    	SessionRepository repo = null;
		
    	try {
			repo = new SessionRepository();
		} catch (Exception e) {
			fail();
		}
		
    	String[] statements1 = {"1+1", "2+2"};
    	String sessionId = "session1";
    	Session session11 = new Session(sessionId, statements1);
    	repo.storeSession(session11);
    	Session session12 = repo.getSession(sessionId);
    	Assert.assertArrayEquals(session12.statements, session11.statements);
    	
    	// Now we store some new statements using same sessionId
    	String[] statements2 = {"3+3", "4+4"};
    	Session session21 = new Session(sessionId, statements2);
    	repo.storeSession(session21);
    	Session session22 = repo.getSession(sessionId);
    	Assert.assertArrayEquals(session22.statements, session21.statements);
    }
    
    public void testCanStoreandRetrieveMultipleSessions()
    {
    	SessionRepository repo = null;
		
    	try {
			repo = new SessionRepository();
		} catch (Exception e) {
			fail();
		}
		
    	String[] statements1 = {"1+1", "2+2"};
    	Session session11 = new Session("session1", statements1);
    	repo.storeSession(session11);
    	
    	String[] statements2 = {"3+3", "4+4"};
    	Session session21 = new Session("session2", statements2);
    	repo.storeSession(session21);
    	
    	Session session12 = repo.getSession("session1");
    	Session session22 = repo.getSession("session2");
    	
    	Assert.assertArrayEquals(session12.statements, session11.statements);
    	Assert.assertArrayEquals(session22.statements, session21.statements);
    }
    
    
}
