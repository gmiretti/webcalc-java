package ar.com.ascentio.calcservice;

public class EvalResponse {
	public String statement;
	public String status;
	public Double result;
	
	public EvalResponse(String expression, Double result, String status) {
		this.statement = expression;
		this. result = result;
		this.status = status;
	}
}
