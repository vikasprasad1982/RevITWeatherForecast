/*
 * This class is responsible for reading the properties file. Initializes the properties and make them available for the use.
 */
package utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadProperties {
	
	private Properties properties;
	
	/**
	 * Load the properties file and make them available for the use.
	 * Ensure that the properties are loaded only once in the project
	 */
	public LoadProperties() {
		if(properties == null) {
			Properties prop = new Properties();
			this.properties = prop;
			ClassLoader loader= Thread.currentThread().getContextClassLoader();
			InputStream stream = loader.getResourceAsStream("weatherData.properties");
			try {
				prop.load(stream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Method to return the initialzed properties object
	 * @return Properties object
	 */
	public Properties getPropertyFileValues() {	
		return properties;
	}
	

}
