package ar.com.ascentio.calcservice;

public class Session {
	
	public String sessionId;
	public String[] statements;
	
	public Session(String sessionId, String[] statements) {
		this.sessionId = sessionId;
		this.statements = statements;
	}
	
	public Session() {
		
	}
}
