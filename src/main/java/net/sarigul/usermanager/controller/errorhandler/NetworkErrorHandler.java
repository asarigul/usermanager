package net.sarigul.usermanager.controller.errorhandler;

import net.sarigul.usermanager.controller.Controller;
import net.sarigul.usermanager.core.HostUnknownException;

public class NetworkErrorHandler extends ErrorHandler<HostUnknownException> {
	protected static final String INFO_MESSAGE = "A Network problem prevents application from connecting to database. Try again later.";
	
	protected NetworkErrorHandler(Controller controller, HostUnknownException cause) {
		super(controller, cause, INFO_MESSAGE);
	}
}
