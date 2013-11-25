package net.sarigul.usermanager.core;

import com.mongodb.MongoException;

public class MongoNetworkException extends ApplicationException {
	private static final long serialVersionUID = -5115307302720719954L;

	public MongoNetworkException(String message, MongoException.Network cause) {
		super(message, cause);
	}
}
