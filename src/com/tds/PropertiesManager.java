package com.tds;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {
	static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(PropertiesManager.class);
	private Properties properties = new Properties();

	public PropertiesManager() {
		log.debug("begin read system.properties");
		try {
			InputStream in = this.getClass().getResourceAsStream("/system.properties");
//			InputStream in = new BufferedInputStream(new FileInputStream(""));
			properties.load(in);	
			log.debug("read system.properties finish");
		} catch (IOException e) {
			log.error("read system.properties error:");
			log.error(e.getMessage());
			log.error(e.getStackTrace().toString());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
	

}
