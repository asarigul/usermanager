package net.sarigul.usermanager.core;

import com.mongodb.MongoException;

public class IndexViolationException extends ApplicationException {
	private static final long serialVersionUID = -5620856548559429626L;

	public IndexViolationException(String message, MongoException.DuplicateKey m) {
		super(message, m);
	}
}
