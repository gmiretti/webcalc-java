package ar.com.ascentio.calcservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SessionRepository {
	
	Connection connection = null;
	
	SessionRepository() throws SQLException {
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
	}
	
	public void storeSession(Session session) {	
	    try
	    {
	    	connection = DriverManager.getConnection("jdbc:sqlite:C:/tools/calcsession.db");
	    	
	    	Statement statement = connection.createStatement();
	    	statement.setQueryTimeout(30);  // set timeout to 30 sec.
	    	
	    	String sql;
	    	//statement.executeUpdate("drop table if exists session");
	    	//statement.executeUpdate("create table session (sessionid string, statement string)");
	    	
	    	// Store delete previous session with same name
	    	sql = String.format("delete from session where sessionid = '%s'", session.sessionId);
	    	statement.executeUpdate(sql);
	    	for (String calcStatement : session.statements) {
	    		sql = String.format("insert into session values('%s', '%s')", session.sessionId, calcStatement);
	    		statement.executeUpdate(sql);
	    	}
	    }
	    catch(SQLException e)
	    {
	    	// if the error message is "out of memory", 
	    	// it probably means no database file is found
	    	System.err.println(e.getMessage());
	    }
	    finally
	    {
	    	try
	    	{
	    		if(connection != null)
	    			connection.close();
	    	}
	    	catch(SQLException e)
	    	{
	    		// connection close failed.
	    		System.err.println(e);
	    	}
	    }	
	}
	
	public Session getSession(String sessionId) {
		Session result = null;
		ResultSet rs = null;
		ArrayList<String> statements = new ArrayList<String>();
		String calcStatement;
		
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:C:/tools/calcsession.db");
			Statement statement = connection.createStatement();
			String sql = String.format("select statement from session where sessionid = '%s'", sessionId);
			rs = statement.executeQuery(sql);
			
			while(rs.next())
	    	{
	    		// read the result set
				calcStatement = rs.getString("statement");
	    		//System.out.println("statement = " + calcStatement);
	    		statements.add(calcStatement);
	    	}
			
			result = new Session(sessionId, statements.toArray(new String[statements.size()]));
		}
		catch(SQLException e)
	    {
	    	// if the error message is "out of memory", 
	    	// it probably means no database file is found
	    	//System.err.println(e.getMessage());
	    }
		finally
	    {
	    	try
	    	{
	    		if(connection != null)
	    			connection.close();
	    	}
	    	catch(SQLException e)
	    	{
	    		// connection close failed.
	    		//System.err.println(e);
	    	}
	    }	
		
		return result;
	}
}
