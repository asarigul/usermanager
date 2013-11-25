package net.sarigul.usermanager.core;

public class ValidationException extends ApplicationException {
	private static final long serialVersionUID = 5781927239908177430L;

	public ValidationException(String message) {
		super(message, null);
	}
	
	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}
}
