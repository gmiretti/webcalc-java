package ar.com.ascentio.calcservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SessionRepository {
	
	Connection connection = null;
	
	SessionRepository() throws Exception {
		
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:calcsession.db");
		} catch (Exception e1) {
			throw e1;
		}	
	}
	
	public void storeSession(Session session) {	
	    try
	    {	
	    	Statement statement = connection.createStatement();
	    	statement.setQueryTimeout(30);  // set timeout to 30 sec.
	    	
	    	String sql;
	    	//statement.executeUpdate("drop table if exists session");
	    	statement.executeUpdate("create table if not exists session (sessionid string, statement string)");
	    	
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
	}
	
	public Session getSession(String sessionId) {
		Session result = null;
		ResultSet rs = null;
		ArrayList<String> statements = new ArrayList<String>();
		String calcStatement;
		
		try {
			
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
		
		return result;
	}
}
