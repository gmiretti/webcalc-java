package ar.com.ascentio.calcservice;

public class EvalResponse {
	public String expression;
	boolean error;
	public Double result;
	
	public EvalResponse(String expression, Double result, boolean error) {
		this.expression = expression;
		this. result = result;
		this.error = error;
	}
}
