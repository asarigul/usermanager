package net.sarigul.usermanager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sarigul.usermanager.util.UserManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractController implements Controller, Protocol {
	protected final Logger logger;
	protected boolean returnsJSON = true;
	protected static final UserManager manager = new UserManager();
	
	protected AbstractController() {
		this.logger = LoggerFactory.getLogger(getClass());
	}

	// utils 
	protected final static HttpSession session(HttpServletRequest request) {
		return request.getSession();
	}
}
