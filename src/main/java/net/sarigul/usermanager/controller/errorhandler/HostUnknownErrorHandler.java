package net.sarigul.usermanager.controller.errorhandler;

import net.sarigul.usermanager.controller.Controller;
import net.sarigul.usermanager.core.HostUnknownException;

public class HostUnknownErrorHandler extends ErrorHandler<HostUnknownException> {
	protected static final String INFO_MESSAGE = MongoNetworkErrorHandler.INFO_MESSAGE;
	
	protected HostUnknownErrorHandler(Controller controller, HostUnknownException cause) {
		super(controller, cause, INFO_MESSAGE);
	}
}
