package ar.com.ascentio.calcclient;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class AppConfig {
	
	static Logger logger = Logger.getLogger(AppConfig.class);
	
	public static final String DEFAULT_FILENAME = "cp.properties"; 
	private String fileName = DEFAULT_FILENAME;
	
	private Properties prop;
	
	public String appId = null;
	public String userId = null;
	public String deviceId = null;
	public String accessToken = null;
	public Boolean deviceProfileRegistered = null;
	public String finishOnboardingProcess = null;
	
	AppConfig() {
		prop = new Properties();
	}
	
	AppConfig(String configFileName) {
		this.fileName = configFileName;
		prop = new Properties();
		loadProperties();
		
		this.appId = prop.getProperty("appId");
		this.userId = prop.getProperty("userId");
		this.deviceId = prop.getProperty("deviceId");
		this.accessToken = prop.getProperty("accessToken");
		this.deviceProfileRegistered = Boolean.parseBoolean(prop.getProperty("deviceProfileRegistered"));
		this.finishOnboardingProcess = prop.getProperty("finishOnboardingProcess");
	}
	
	private void loadProperties() {
		
    	try {
    		// Load a properties file
			prop.load(new FileInputStream(fileName));
    	} catch (IOException e) {
    		logger.warn(e);
        }
	}
	
	public void save() {
		
		if (appId != null) {
			prop.setProperty("appId", appId);
		}
		
		if (userId != null) {
			prop.setProperty("userId", userId);
		}
		
		if (deviceId != null) {
			prop.setProperty("deviceId", deviceId);
		}
		
		if (accessToken != null) {
			prop.setProperty("accessToken", accessToken);
		}
		
		if (deviceProfileRegistered != null) {
			prop.setProperty("deviceProfileRegistered", Boolean.toString(deviceProfileRegistered));
		}

		if (finishOnboardingProcess != null) {
			prop.setProperty("finishOnboardingProcess", finishOnboardingProcess);
		}

		try {
			prop.store(new FileOutputStream(fileName), "");
		}
		catch (IOException e) {
			logger.warn(e);
		}
	}
	
	public boolean isOK() {
		return (appId != null && !appId.isEmpty() &&
				userId != null && !userId.isEmpty() &&
				deviceId != null && !deviceId.isEmpty() &&
				accessToken != null && !accessToken.isEmpty());
	}
}