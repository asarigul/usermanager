package net.sarigul.usermanager.core;

import net.sarigul.usermanager.util.Toolbox;


public class ApplicationException extends RuntimeException {
	private static final long serialVersionUID = 3080797957050113711L;
	private final String eventId, message;
	
	
	public ApplicationException(String message) {
		this(message, null);
	}
	
	public ApplicationException(String message, Throwable cause) {
		super(null, cause);
		this.message = message;
		this.eventId = Toolbox.newExceptionId();
	}
	
	public String getEventId() {
		return this.eventId;
	}
	
	@Override
	public String getMessage() {
		return "Event id: [" + this.eventId + "] " +  this.message;
	}
}
