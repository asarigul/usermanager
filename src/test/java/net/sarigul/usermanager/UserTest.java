package net.sarigul.usermanager;

import net.sarigul.usermanager.config.ConfigurationException;
import net.sarigul.usermanager.controller.Protocol;
import net.sarigul.usermanager.core.Application;
import net.sarigul.usermanager.core.HostUnknownException;
import net.sarigul.usermanager.core.MongoNetworkException;
import net.sarigul.usermanager.service.UserService;
import net.sarigul.usermanager.util.Toolbox;

public class UserTest {
	private static UserService manager;
	
	private static final int ID_LENGTH = 24;

	protected static void init() throws ConfigurationException {
		Application.initConfiguration(null);
	}

	protected static String tooShortFirstName() {
		return Toolbox.randomString(Protocol.FIRSTNAME_MIN_LENGTH - 1);
	}
	
	protected static String tooLongFirstName() {
		return Toolbox.randomString(Protocol.FIRSTNAME_MAX_LENGTH + 1);
	}
	
	protected static String tooShortLastName() {
		return Toolbox.randomString(Protocol.LASTNAME_MIN_LENGTH - 1);
	}
	
	protected static String tooLongLastName() {
		return Toolbox.randomString(Protocol.LASTNAME_MAX_LENGTH + 1);
	}
	
	protected static String tooShortPhoneNumber() {
		return Toolbox.randomDigits(Protocol.PHONE_NUMBER_LENGTH - 1);
	}
	
	protected static String tooLongPhoneNumber() {
		return Toolbox.randomDigits(Protocol.PHONE_NUMBER_LENGTH + 1);
	}
	
	protected static String phoneNumberWithLetters() {
		return Toolbox.randomString(Protocol.PHONE_NUMBER_LENGTH);
	}
	
	protected static String validId() {
		return Toolbox.randomHexString(ID_LENGTH);
	}
	
	public static String tooShortId() {
		return Toolbox.randomHexString(0, ID_LENGTH - 1);
	}
	
	public static String tooLongId() {
		return Toolbox.randomHexString(ID_LENGTH + 1);
	}
	
	public static String idWithNonHexLetters() {
		return Toolbox.randomHexString(ID_LENGTH - 1) + "X";
	}
	
	protected static String validFirstName() {
		return Toolbox.randomString(Protocol.FIRSTNAME_MIN_LENGTH, Protocol.FIRSTNAME_MAX_LENGTH);
	}
	
	protected static String validLastName() {
		return Toolbox.randomString(Protocol.LASTNAME_MIN_LENGTH, Protocol.LASTNAME_MAX_LENGTH);
	}

	protected static String validPhoneNumberStr() {
		return Toolbox.randomDigits(Protocol.PHONE_NUMBER_LENGTH);
	}
	
	protected static Long validPhoneNumber() {
		return Long.parseLong(validPhoneNumberStr());
	}
	
	protected static UserService userService() throws ConfigurationException, HostUnknownException, MongoNetworkException {
		synchronized (UserTest.class) {
			if(manager == null) {
				manager = new UserService();
			}
		}
		return manager;
	}
}
