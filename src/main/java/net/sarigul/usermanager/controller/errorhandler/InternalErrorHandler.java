package net.sarigul.usermanager.controller.errorhandler;

import net.sarigul.usermanager.controller.Controller;
import net.sarigul.usermanager.core.InternalErrorException;

public class InternalErrorHandler extends ErrorHandler<InternalErrorException> {
	protected static final String INFO_MESSAGE = "Sorry, we encountered a problem while processing your request. Please try again.";

	protected InternalErrorHandler(Controller controller, InternalErrorException cause) {
		super(controller, cause, INFO_MESSAGE);
	}
}
