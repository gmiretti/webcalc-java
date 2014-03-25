package ar.com.ascentio.calcclient;

public class EvalResponse {
	public String statement;
	public String status;
	public Double result;
	
	public EvalResponse(String statement, Double result, String status) {
		this.statement = statement;
		this. result = result;
		this.status = status;
	}
}
