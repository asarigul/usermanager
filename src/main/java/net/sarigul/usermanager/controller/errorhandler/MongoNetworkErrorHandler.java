package net.sarigul.usermanager.controller.errorhandler;

import net.sarigul.usermanager.controller.Controller;
import net.sarigul.usermanager.core.MongoNetworkException;

public class MongoNetworkErrorHandler extends ErrorHandler<MongoNetworkException> {
	protected static final String INFO_MESSAGE = NetworkErrorHandler.INFO_MESSAGE;

	protected MongoNetworkErrorHandler(Controller controller, MongoNetworkException cause) {
		super(controller, cause, INFO_MESSAGE);
	}
}
