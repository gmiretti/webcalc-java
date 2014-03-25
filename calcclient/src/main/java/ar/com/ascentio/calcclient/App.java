package ar.com.ascentio.calcclient;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;


//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;

public class App 
{
	//static Logger logger = Logger.getLogger(App.class);
	static Properties prop;
	
    public static void main( String[] args )
    {
    	//String currentDir = System.getProperty("user.dir");
        //System.out.println("Current dir using System:" +currentDir);
    	
        loadProperties();
        //PropertyConfigurator.configure(prop);
		
		String calcServiceBaseUrl = prop.getProperty("calcservice.baseUrl", "http://localhost:8080/calcservice");
		System.out.println("Using calservice base endpoint: " + calcServiceBaseUrl);
		
		CalcService calcService = new CalcService(calcServiceBaseUrl);
		
		InputStream is = null;

		BufferedReader br = null;
		
		try {
			is = System.in;

			br = new BufferedReader(new InputStreamReader(is));

			String statement = null;

			System.out.print("Enter statement (type quit to exit) > ");
			
			while ((statement = br.readLine()) != null) {

				if (statement.equalsIgnoreCase("quit")) {
					break;
		        }
				if (statement.equalsIgnoreCase("store")) {
					break;
		        }
				
				System.out.println("statement : " + statement);
								
				EvalRequest evalRequest = new EvalRequest(statement);
				EvalResponse evalResponse = calcService.postEval(evalRequest);
			
				if (evalResponse != null && evalResponse.status.equals("OK")) {
					System.out.println("result = " + evalResponse.result);
				}
				else {
					System.out.println("Error");
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
