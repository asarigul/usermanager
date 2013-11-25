package net.sarigul.usermanager.controller.errorhandler;

import net.sarigul.usermanager.controller.Controller;
import net.sarigul.usermanager.core.ValidationException;

public class ValidationErrorHandler extends ErrorHandler<ValidationException> {

	protected ValidationErrorHandler(Controller controller, ValidationException cause) {
		super(controller, cause, cause.getMessage());
	}
}
