package net.sarigul.usermanager.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Toolbox {
	private static final String LETTERS = "ABCDEFGHIJKLMNOQPRSTUVWXYZ";
	private static final String HEX_DIGITS = "0123456789ABCDEF";
	private static final String DIGITS = "1234567890";
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy.MM.dd-");

	public static <T> boolean equals(T a, T b) {
		if(a == null) {
			return b == null;
		}
		return a.equals(b);
	}
	
	public static String randomString(int length) {
		return randomString(length, length);
	}
	
	public static String randomString(int min, int max) {
		return random(LETTERS, min, max);
	}
	
	public static String randomDigits(int length) {
		return randomDigits(length, length);
	}
	
	public static String randomDigits(int min, int max) {
		return random(DIGITS, min, max);
	}
	
	public static String randomHexString(int length) {
		return randomHexString(length, length);
	}
	public static String randomHexString(int min, int max) {
		return random(HEX_DIGITS, min, max);
	}
	
	public static String random(String characters, int min, int max) {
		int charactersLength = characters.length();
		StringBuilder b = new StringBuilder(""); // "" = 0 length random string
		int length = randomInt(min, max);
		for (int i = 0; i < length; i++) {
			int index = randomInt(0, charactersLength - 1);
			b.append(characters.charAt((int) index));
		}
		return b.toString();
	}
	
	public static int randomInt(int floor, int ceil) {
		if(floor > ceil) {
			throw new IllegalArgumentException();
		}
		
		double r =  floor + Math.random() * (ceil - floor);
		return (int) r;
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
