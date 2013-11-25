package net.sarigul.usermanager.config;

import net.sarigul.usermanager.core.ApplicationException;

public class ConfigurationException extends ApplicationException {
	private static final long serialVersionUID = -3999121980979660894L;
	
	public ConfigurationException(String key, String value) {
		this(value == null ? 
			"Value for " + key + " is not set." : 
			"Value for " + key + " (" + value + ") is invalid");
	}
	
	public ConfigurationException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ConfigurationException(String message) {
		super(message, null);
	}
}

