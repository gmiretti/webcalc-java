
package ar.com.ascentio.calcservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.ascentio.calculator.Calculator;

@Path("/eval")
public class Eval {
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public EvalResponse eval(EvalRequest request) {
    	
    	boolean error = false;
    	Double result = 0.0;
    	try {
    		result = Calculator.eval(request.expression);
    		
    	} catch (Exception e) {
    		error = true;
    	}
    	
    	EvalResponse evalRes = new EvalResponse(request.expression, result, error);
    	return evalRes;
    }
}
