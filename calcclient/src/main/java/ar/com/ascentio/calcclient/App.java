package ar.com.ascentio.calcclient;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Properties;


//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;

public class App 
{
	//static Logger logger = Logger.getLogger(App.class);
	static Properties prop;
	
    public static void main( String[] args )
    {	
        loadProperties();
        //PropertyConfigurator.configure(prop);
		
		String calcServiceBaseUrl = prop.getProperty("calcservice.baseUrl", "http://localhost:8080/calcservice");
		System.out.println("Using calservice base endpoint: " + calcServiceBaseUrl);
		
		CalcService calcService = new CalcService(calcServiceBaseUrl);
		
		InputStream is = null;

		BufferedReader br = null;
		
		ArrayList<String> sessionData = new ArrayList<String>();
		
		try {
			is = System.in;

			br = new BufferedReader(new InputStreamReader(is));

			String line = null;
			String[] lineSplit = null;

			System.out.print("Enter statement (type quit to exit) > ");
			
			while ((line = br.readLine()) != null) {
				
				if (line.equalsIgnoreCase("quit")) {
					break;
		        }
				else if (line.equalsIgnoreCase("help")) {
					System.out.println("Available commands:");
					System.out.println("help: print this help");
					System.out.println("quit: exit application");
					System.out.println("guardar <sessionId>: store session with id equal to sessionId");
					System.out.println("recuperar <sessionId>: retrieve session with id equal to sessionId");
		        }
				else if (line.startsWith("guardar")) {
					lineSplit = line.split(" ");
					
					if (lineSplit.length == 2) {
						Session session = new Session(lineSplit[1], sessionData.toArray(new String[sessionData.size()]));
						calcService.storeSession(session);
						sessionData.clear();
					}
					else {
						System.out.println("Error: Missing session name");
					}
		        }
				else if (line.startsWith("recuperar")) {
					lineSplit = line.split(" ");
					if (lineSplit.length == 2) {
						Session session = calcService.retrieveSession(lineSplit[1]);
						if (session != null) {
							for (String st : session.statements) {
								System.out.println(st);
							}
						}
					}
					else {
						System.out.println("Error: Missing session name");
						continue;
					}
				}
				else {
					
					EvalRequest evalRequest = new EvalRequest(line);
					EvalResponse evalResponse = calcService.postEval(evalRequest);
				
					if (evalResponse != null && evalResponse.status.equals("OK")) {
						String statementWithResult = line + " = " + evalResponse.result;
						System.out.println(statementWithResult);
						sessionData.add(statementWithResult);
					}
					else {
						System.out.println("Error");
					}
				}
				
				System.out.print("Enter statement (type quit to exit) > ");
            }
        }
        catch (IOException ioe) {
        	System.out.println("Exception while reading input " + ioe);
		}
        finally {

        	// close the streams using close method
           try {
        	   if (br != null) {
        		   br.close();

		       }

           }
           catch (IOException ioe) {
        	   System.out.println("Error while closing stream: " + ioe);
           }
       }
    }
    
    private static void loadProperties() {
		prop = new Properties();
    	try {
			prop.load(new FileInputStream("settings.properties"));
    	} catch (IOException e) {
    		System.err.println("Could not find settings.properties file. Aborting");
			System.exit(1);
        }
	}
}
