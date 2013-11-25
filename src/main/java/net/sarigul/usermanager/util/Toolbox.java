package net.sarigul.usermanager.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Toolbox {
	private static final String LETTERS = "ABCDEFGHIJKLMNOQPRSTUVWXYZ";
	private static final String DIGITS = "1234567890";
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy.MM.dd-");

	public static String randomString(int length) {
		return random(LETTERS, length);
	}
	
	public static Long randomLong(int length) {
		return Long.parseLong(random(DIGITS, length));
	}
	
	public static String random(String characters, int length) {
		int charactersLength = characters.length();
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < length; i++) {
			double index = Math.random() * charactersLength;
			b.append(characters.charAt((int) index));
		}
		return b.toString();
	}
	
	public static boolean checkRegex(String value, String regex) {
		if(value == null || regex == null) {
			throw new NullPointerException();
		}
		return value != null && value.matches(regex);
	}
	
	public static String newExceptionId() {
		synchronized (DATE_FORMAT) {
			return DATE_FORMAT.format(new Date()) + randomString(4);
		}
	}
}
