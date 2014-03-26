
package ar.com.ascentio.calcservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/session/")
public class SessionHandler {
    
    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Session storeSession(Session session) {
    	
    	//String status = "OK";
    	
    	try {
    		// TODO: This could be a singleton
    		SessionRepository repo = new SessionRepository();
    		repo.storeSession(session);
    		
    	} catch (Exception e) {
    		//status = "ERROR";
    	}
    	
    	return session;
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Session getSession(@PathParam("id") String sessionId) {
    	
    	//String status = "OK";
    	Session response = null;
    	
    	try {
    		// TODO: This could be a singleton
    		SessionRepository repo = new SessionRepository();
    	
    		response = repo.getSession(sessionId);
    		
    	} catch (Exception e) {
    		//status = "ERROR";
    	}
    	
    	return response;
    }
}
