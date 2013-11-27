package net.sarigul.usermanager.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationLoader {
	private final Properties p = new Properties();
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public ConfigurationLoader(String fileName) throws IOException {
		if(fileName == null) {
			throw new NullPointerException();
		}
		
		InputStream in = null;
		try {
			in = getClass().getClassLoader().getResourceAsStream(fileName);
			if(in == null) {
				throw new IOException();
			}
			p.load(in);
		} finally {
			if(in != null) {
				in.close();
			}
		}
	}
	
	public Configuration get() throws ConfigurationException {
		String mongodbHost = getProperty(Configuration.MONGODB_HOST_KEY);
		if(mongodbHost == null) {
			configurationError(Configuration.MONGODB_HOST_KEY, mongodbHost);
		}
		logger.debug("mongodb host set: {}", mongodbHost);
		
		Integer mongodbPort = getMongodbPort(getProperty(Configuration.MONGODB_PORT_KEY));
		
		String mongodbUsername = getProperty(Configuration.MONGODB_USERNAME_KEY),
				mongodbPassword = getProperty(Configuration.MONGODB_PASSWORD_KEY);
		
		checkMongodbCredentials(mongodbUsername, mongodbPassword);
		
		if(mongodbUsername == null) {
			logger.info("will connect to mongodb server without authentication");
		} else {
			logger.info("will connect to mongodb server with authentication");
		}
		
		String captchaCharacters = getCaptchaCharacters(getProperty(Configuration.CAPTCHA_CHARACTERS_KEY));
		int captchaLength = getCaptchaLength(getProperty(Configuration.CAPTCHA_LENGTH_KEY));
		boolean caseSensitive = getCaseSensitivity(getProperty(Configuration.CAPTCHA_CASE_SENSITIVE_KEY));
		
		return new Configuration()
			.setMongodbHost(mongodbHost)
			.setMongodbPort(mongodbPort)
			.setMongodbUsername(mongodbUsername)
			.setMongodbPassword(mongodbPassword)
			.setCaptchaLength(captchaLength)
			.setCaptchaCharacters(captchaCharacters)
			.setCaseSensitive(caseSensitive);
	}
	
	private String getProperty(String key) {
		String val = p.getProperty(key);
		if(val == null || val.trim().length() == 0) {
			return null;
		}
		return val.trim();
	}
	
	private Integer getMongodbPort(String propertyValue) throws ConfigurationException {
		Integer mongodbPort = null;
		if(propertyValue != null && propertyValue.length() > 0) {
			propertyValue = propertyValue.trim();
			if(! propertyValue.matches("[\\d]+$")) {
				configurationError(Configuration.MONGODB_PORT_KEY, propertyValue);
			}
			mongodbPort = Integer.parseInt(propertyValue);
			logger.info("mongodb port set: {}", mongodbPort);
		} else {
			logger.info("mongodb port setting not set");
		}
		return mongodbPort;
	}
	
	private void checkMongodbCredentials(String mongodbUsername, String mongodbPassword) throws ConfigurationException {
		if(mongodbUsername != null && mongodbPassword == null) {
			configurationError("mongodb password is not set while username is set. " +  
					"(both should be unset to disable authentication check)");
		} else if(mongodbPassword != null && mongodbUsername == null) {
			configurationError("mongodb username is not set while password is set. " +  
					"(both should be unset to disable authentication check)");
		}
	}
	
	private String getCaptchaCharacters(String propertyValue) {
		String captchaCharacters = Configuration.DEFAULT_CAPTCHA_CHARACTERS;
		if(propertyValue == null) {
			logger.info("no captcha characters setting found. using default: {}", captchaCharacters);
		} else {
			if(propertyValue.matches("[\\w\\d]+")) {
				captchaCharacters = propertyValue;
				logger.info("captcha characters set: {}", captchaCharacters);
			} else {
				logger.warn("invalid captcha characters: {}. using default: {}", propertyValue, captchaCharacters);
			}
		}
		return captchaCharacters;
	}
	
	private int getCaptchaLength(String propertyValue) {
		int captchaLenght = Configuration.DEFAULT_CAPTCHA_LENGTH;
		if(propertyValue == null) {
			logger.info("no captcha length setting found. using default: {}", captchaLenght);
		} else {
			try {
				captchaLenght = Integer.parseInt(propertyValue);
				logger.info("captcha characters length set: {}", captchaLenght);
			} catch(NumberFormatException e) {
				logger.warn("invalid captcha length setting: {}. using default: {}", 
								propertyValue, captchaLenght);
			}
		}
		return captchaLenght;
	}
	
	private boolean getCaseSensitivity(String propertyValue) {
		boolean sensitive = Configuration.DEFAULT_CASE_SENSITIVE;
		
		if(propertyValue == null) {
			logger.info("captcha case sensitivity setting not found. using default: {}", 
							sensitive);
		} else {
			if("true".equals(propertyValue)) {
				sensitive = true;
				logger.info("captcha case sensitivity set: {}", sensitive);
			} else if("false".equals(propertyValue)) {
				sensitive = false;
				logger.info("captcha case sensitivity set: {}", sensitive);
			} else {
				logger.warn("invalid captcha case sensitivity setting: {}. using default: {}", 
								propertyValue, sensitive);
			}
		}
		return sensitive;
	}
	
	private void configurationError(String message) throws ConfigurationException {
		ConfigurationException ex = new ConfigurationException(message);
		logger.error(ex.getMessage());
		throw ex;
	}
	
	private void configurationError(String key, String value) throws ConfigurationException {
		ConfigurationException ex = new ConfigurationException(key, value);
		logger.error(ex.getMessage());
		throw ex;
	}
}
