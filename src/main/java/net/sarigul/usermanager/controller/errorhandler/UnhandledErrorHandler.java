package net.sarigul.usermanager.controller.errorhandler;

import net.sarigul.usermanager.controller.Controller;
import net.sarigul.usermanager.core.UnhandledException;

public class UnhandledErrorHandler extends ErrorHandler<UnhandledException> {
	protected static final String INFO_MESSAGE = InternalErrorHandler.INFO_MESSAGE;
	
	protected UnhandledErrorHandler(Controller controller, UnhandledException cause) {
		super(controller, cause, INFO_MESSAGE);
	}
}
