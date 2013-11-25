package net.sarigul.usermanager.core;

public class UnhandledException extends ApplicationException {
	private static final long serialVersionUID = -7693401298904917173L;

	public UnhandledException(String message, Throwable cause) {
		super(message, cause);
	}
}
