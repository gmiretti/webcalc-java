
package ar.com.ascentio.calcservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.ascentio.calculator.Calculator;

@Path("/eval")
public class EvalHandler {
    
    @POST
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public EvalResponse eval(EvalRequest request) {
    	
    	String status = "OK";
    	Double result = 0.0;
    	try {
    		result = Calculator.eval(request.statement);
    		
    	} catch (Exception e) {
    		status = "ERROR";
    	}
    	
    	EvalResponse evalRes = new EvalResponse(request.statement, result, status);
    	return evalRes;
    }
}
