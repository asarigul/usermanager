package net.sarigul.usermanager.controller.errorhandler;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import net.sarigul.usermanager.controller.AjaxController;
import net.sarigul.usermanager.controller.Controller;
import net.sarigul.usermanager.core.ApplicationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

/**
 * a handler to handle a sub type T of ApplicationException
 */
public class ErrorHandler<T extends ApplicationException> {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private final Controller source;
	private final T cause;
	private String info;
	
	protected ErrorHandler(Controller source, T cause, String info) {
		logger.warn("Handling error in {}: {}", source.getClass().getSimpleName(), info, cause);
		
		this.source = source;
		this.cause = cause;
		this.info = info + " [Error reference: " + cause.getEventId() +  "]";
	}
	
	public final ModelAndView handle(HttpServletResponse response) {
		if(source instanceof AjaxController) {
			ajaxResponse(cause, response);
			return null;
		} 
		
		return errorView(cause, info);
	}
	
	private void ajaxResponse(Throwable cause, HttpServletResponse response) {
		try {
			AjaxController._jsonResponse(response, false, info);
		} catch (IOException e) { // client not there?
			logger.warn("cannot write response", e);
		}
	}
	
	private static ModelAndView errorView(Throwable cause, String info) {
		return new ModelAndView("error").addObject("info", info);
	}
}
