package net.sarigul.usermanager.core;

public class InternalErrorException extends ApplicationException {
	private static final long serialVersionUID = 2184020132464813068L;

	public InternalErrorException(String message, Throwable cause) {
		super(message, cause);
	}
}
