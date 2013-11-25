package net.sarigul.usermanager.config;

public final class Configuration {
	public static final String MONGODB_HOST_KEY = "mongodb.host",
			MONGODB_PORT_KEY = "mongodb.port",
			MONGODB_USERNAME_KEY = "mongodb.username",
			MONGODB_PASSWORD_KEY = "mongodb.password",
			CAPTCHA_CHARACTERS_KEY = "captcha.characters",
			CAPTCHA_LENGTH_KEY = "captcha.length",
			CAPTCHA_CASE_SENSITIVE_KEY = "captcha.caseSensitive",
			LOG_FOLDER = "log.folder";
			
	public static String DEFAULT_CAPTCHA_CHARACTERS = "2345679QqWwEeRrTtYyUuoPpAaSsDdFfGgHhJjKkLiZzXxCcVvbNnMm";
	public static int DEFAULT_CAPTCHA_LENGTH = 4;
	public static boolean DEFAULT_CASE_SENSITIVE = true;
	
	public static final String DB_NAME = "usermanager";
	
	// db connection
	private String mongodbHost;
	private Integer mongodbPort;
	private String mongodbUsername;
	private String mongodbPassword;
	
	// kaptcha
	private String captchaCharacters;
	private int captchaLength;
	private boolean caseSensitive;
	
	// logging
	private String logFolder;
	
	public String getMongodbHost() {
		return mongodbHost;
	}
	
	public Configuration setMongodbHost(String mongodbHost) {
		this.mongodbHost = mongodbHost;
		return this;
	}
	
	public Integer getMongodbPort() {
		return mongodbPort;
	}
	
	public Configuration setMongodbPort(Integer mongodbPort) {
		this.mongodbPort = mongodbPort;
		return this;
	}
	
	public String getMongodbUsername() {
		return mongodbUsername;
	}
	
	public Configuration setMongodbUsername(String mongodbUsername) {
		this.mongodbUsername = mongodbUsername;
		return this;
	}
	
	public String getMongodbPassword() {
		return mongodbPassword;
	}
	
	public Configuration setMongodbPassword(String mongodbPassword) {
		this.mongodbPassword = mongodbPassword;
		return this;
	}
	
	public boolean isDatabaseAuthenticationEnabled() {
		return this.mongodbUsername != null && this.mongodbPassword != null;
	}
	
	public String getCaptchaCharacters() {
		return captchaCharacters;
	}
	
	public Configuration setCaptchaCharacters(String captchaCharacters) {
		this.captchaCharacters = captchaCharacters;
		return this;
	}
	
	public int getCaptchaLength() {
		return captchaLength;
	}
	
	public Configuration setCaptchaLength(int captchaLength) {
		this.captchaLength = captchaLength;
		return this;
	}

	public boolean isCaseSensitive() {
		return caseSensitive;
	}

	public Configuration setCaseSensitive(boolean caseSensitive) {
		this.caseSensitive = caseSensitive;
		return this;
	}

	public String getLogFolder() {
		return logFolder;
	}

	public Configuration setLogFolder(String logFolder) {
		this.logFolder = logFolder;
		return this;
	}
}
