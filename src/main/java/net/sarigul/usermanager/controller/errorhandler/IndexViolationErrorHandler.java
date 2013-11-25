package net.sarigul.usermanager.controller.errorhandler;

import net.sarigul.usermanager.controller.Controller;
import net.sarigul.usermanager.core.IndexViolationException;

public class IndexViolationErrorHandler extends ErrorHandler<IndexViolationException> {
	protected static final String INFO_MESSAGE = "A user withe the same data already exists.";
	
	protected IndexViolationErrorHandler(Controller controller, IndexViolationException cause) {
		super(controller, cause, INFO_MESSAGE);
	}
}
