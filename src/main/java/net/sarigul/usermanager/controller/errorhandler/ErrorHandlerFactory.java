package net.sarigul.usermanager.controller.errorhandler;

import net.sarigul.usermanager.config.ConfigurationException;
import net.sarigul.usermanager.controller.Controller;
import net.sarigul.usermanager.core.ApplicationException;
import net.sarigul.usermanager.core.HostUnknownException;
import net.sarigul.usermanager.core.InternalErrorException;
import net.sarigul.usermanager.core.MongoNetworkException;
import net.sarigul.usermanager.core.UnhandledException;
import net.sarigul.usermanager.core.ValidationException;

public final class ErrorHandlerFactory {
	
	public static ErrorHandler<? extends ApplicationException> get(Throwable cause, Controller controller) {
		if(cause instanceof ConfigurationException) {
			return new ConfigurationErrorHandler(controller, (ConfigurationException) cause);
		} 
		
		if(cause instanceof ValidationException) {
			return new ValidationErrorHandler(controller, (ValidationException) cause);
		}
		
		if(cause instanceof InternalErrorException) {
			return new InternalErrorHandler(controller, (InternalErrorException) cause);
		}
		
		if(cause instanceof HostUnknownException) {
			return new NetworkErrorHandler(controller, (HostUnknownException) cause);
		}
		
		if(cause instanceof MongoNetworkException) {
			return new MongoNetworkErrorHandler(controller, (MongoNetworkException) cause);
		}
		
		return new UnhandledErrorHandler(controller, new UnhandledException("unhandled exception", cause));
	}
}
