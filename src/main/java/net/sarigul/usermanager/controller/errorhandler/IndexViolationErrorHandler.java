package net.sarigul.usermanager.controller.errorhandler;

import net.sarigul.usermanager.controller.Controller;
import net.sarigul.usermanager.controller.Protocol;
import net.sarigul.usermanager.core.IndexViolationException;

public class IndexViolationErrorHandler extends ErrorHandler<IndexViolationException> {
	
	protected IndexViolationErrorHandler(Controller controller, IndexViolationException cause) {
		super(controller, cause, Protocol.DUPLICATE_USER);
	}
}
