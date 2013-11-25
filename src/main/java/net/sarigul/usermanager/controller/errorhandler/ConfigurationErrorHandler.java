package net.sarigul.usermanager.controller.errorhandler;

import net.sarigul.usermanager.config.ConfigurationException;
import net.sarigul.usermanager.controller.Controller;

public class ConfigurationErrorHandler extends ErrorHandler<ConfigurationException> {
	protected static final String INFO_MESSAGE = "Sorry; this application has configuration errors and cannot service requests now.";

	protected ConfigurationErrorHandler(Controller controller, ConfigurationException e) {
		super(controller, e, INFO_MESSAGE);
	}
}
