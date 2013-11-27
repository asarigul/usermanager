package net.sarigul.usermanager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sarigul.usermanager.config.ConfigurationException;
import net.sarigul.usermanager.core.HostUnknownException;
import net.sarigul.usermanager.core.MongoNetworkException;
import net.sarigul.usermanager.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class UserController implements Controller, Protocol {
	protected final Logger logger;
	private static UserService userService;
	
	protected UserController() {
		this.logger = LoggerFactory.getLogger(getClass());
	}

	// utils 
	protected final static HttpSession session(HttpServletRequest request) {
		return request.getSession();
	}
	
	protected final static UserService userService() throws ConfigurationException, HostUnknownException, MongoNetworkException {
		synchronized (UserController.class) {
			if(userService == null) {
				userService = new UserService();
			}
		}
		return userService;
	}
}
