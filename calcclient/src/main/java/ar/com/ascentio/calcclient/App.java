package ar.com.ascentio.calcclient;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Properties;

import ar.com.ascentio.httpclient.HTTPClientFactory;
import ar.com.ascentio.httpclient.HttpUrlHTTPClientFactory;


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
		
		HTTPClientFactory factory = new HttpUrlHTTPClientFactory();
		CalcService calcService = new CalcService(calcServiceBaseUrl, factory);
		
		InputStream is = null;
		BufferedReader br = null;
	
		ArrayList<String> sessionData = new ArrayList<String>();
		
		try {
			is = System.in;
			br = new BufferedReader(new InputStreamReader(is));
			String line = null;

			System.out.print("Enter statement (type quit to exit) > ");
			
			while ((line = br.readLine()) != null) {
				
				if (line.equalsIgnoreCase("quit")) {
					break;
		        }
				else if (line.equalsIgnoreCase("help")) {
					cmdHelp();
		        }
				else if (line.startsWith("guardar")) {
					cmdGuardar(calcService, sessionData, line);
		        }
				else if (line.startsWith("recuperar")) {
					cmdRecuperar(calcService, line);
				}
				else {
					cmdStatement(calcService, sessionData, line);
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

	private static void cmdStatement(CalcService calcService,
			ArrayList<String> sessionData, String line) {
		
		EvalRequest evalRequest = new EvalRequest(line);
		EvalResponse evalResponse = calcService.postEval(evalRequest);

		if (evalResponse == null) {
			System.out.println("Error: Could not send request.");
		}
		else if (!evalResponse.status.equals("OK")) {
			System.out.println("Error: Error processing statement. Wrong format");
		}
		else {
			String statementWithResult = line + " = " + evalResponse.result;
			System.out.println(statementWithResult);
			sessionData.add(statementWithResult);
		}
			
	}

	private static void cmdRecuperar(CalcService calcService, String line) {
		
		String[] lineSplit;
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
		}
	}

	private static void cmdGuardar(CalcService calcService,
			ArrayList<String> sessionData, String line) {
		
		String[] lineSplit;
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

	private static void cmdHelp() {
		System.out.println("Available commands:");
		System.out.println("help: print this help");
		System.out.println("quit: exit application");
		System.out.println("guardar <sessionId>: store session with id equal to sessionId");
		System.out.println("recuperar <sessionId>: retrieve session with id equal to sessionId");
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
