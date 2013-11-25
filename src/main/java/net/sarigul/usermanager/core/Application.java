package net.sarigul.usermanager.core;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.sarigul.usermanager.config.Configuration;
import net.sarigul.usermanager.config.ConfigurationException;
import net.sarigul.usermanager.config.ConfigurationLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import com.google.code.morphia.logging.MorphiaLoggerFactory;
import com.google.code.morphia.logging.slf4j.SLF4JLogrImplFactory;

public class Application implements ServletContextListener  {
	private static Configuration configuration;
	private static State state;
	private static Logger logger;
	
	public void contextInitialized(ServletContextEvent contextEvent) {
		Exception initException = null;
		try {
			initConfiguration(contextEvent.getServletContext().getRealPath("/"));
		} catch (ConfigurationException e) {
			initException = e;
			logger.error("configuration error: {}", e.getMessage());
		} catch (Exception e) {
			initException = e; 
			logger.error("unhandled exception", e);
		} finally {
			state = new State(initException);
		}
	}
	
	public static void initConfiguration(String logRootPath) {
		if(logger == null) {
			initLogger(logRootPath);
		}
		
		if(configuration != null) {
			logger.warn("application already init. skipping init call");
		} else {
			try {
				configuration = new ConfigurationLoader("usermanager.properties").get();
			} catch (IOException ex) {
				throw new ConfigurationException("cannot load usermanager.properties");
			}
			logger.info("application initialized");
		}
	}
	
	private static void initLogger(String logRootDir) {
		// centralize logging
		if(logRootDir != null) {
			// read by logback to save log files in
			System.setProperty("APP_ROOT", logRootDir);
		}
		
		// mongo client JUL logging -> slf4j
		SLF4JBridgeHandler.removeHandlersForRootLogger(); 
		SLF4JBridgeHandler.install();

		// morphia
		MorphiaLoggerFactory.registerLogger(SLF4JLogrImplFactory.class);
	
		// spring logging done by excluding commons-logging & using jcl-over-slf4j
		
		logger = LoggerFactory.getLogger(Application.class);
	}
	
	public static Configuration configuration() {
		return configuration;
	}
	
	public static State state() {
		if(state == null) {
			throw new IllegalStateException("application not initialized");
		}
		return state;
	}
	
	public void contextDestroyed(ServletContextEvent contextEvent) {
		logger.warn("context destroyed");
	}
}
