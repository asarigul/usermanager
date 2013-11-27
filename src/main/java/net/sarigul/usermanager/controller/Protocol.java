package net.sarigul.usermanager.controller;

/* *
 * defines server - client interaction protocol by keys. browser sends parameters
 * with those names as value keys, and servlets read them with those keys. same applies 
 * for vice versa; ajax responses are JSON objects using keys defined here. 
 * 
 * besides, server side validation logic and error messages passed to the clients;
 * so client side validation and error messages are the same with the server.
 * */

public interface Protocol {
	String	REQUEST_ID_KEY = "id",
			REQUEST_FIRSTNAME_KEY = "firstName",	
			REQUEST_LASTNAME_KEY = "lastName",
			REQUEST_PHONENUMBER_KEY = "phoneNumber",
			REQUEST_CAPTHCA_KEY = "capthca";
	
	String	RESPONSE_RESULT_KEY = "success",
			RESPONSE_INFO_KEY = "info";
	
	// following are not mean to be changed //
	int 	FIRSTNAME_MIN_LENGTH = 2, 
			FIRSTNAME_MAX_LENGTH = 16,
			LASTNAME_MIN_LENGTH = FIRSTNAME_MIN_LENGTH, 
			LASTNAME_MAX_LENGTH = FIRSTNAME_MAX_LENGTH,
			PHONE_NUMBER_LENGTH = 10;

	String 	ALLOWED_LETTERS = "a-zA-ZçÇıİğĞşŞöÖüÜ";
			
	String	REGEX_OBJECT_ID = "^[0-9a-fA-F]{24}$",
			REGEX_FIRSTNAME = "^([" + ALLOWED_LETTERS + "]){" + 
				FIRSTNAME_MIN_LENGTH + "," + FIRSTNAME_MAX_LENGTH + "}$",
			REGEX_LASTNAME = "^([" + ALLOWED_LETTERS + "]){" + 
				LASTNAME_MIN_LENGTH + "," + LASTNAME_MAX_LENGTH + "}$",
			REGEX_PHONE_NUMBER = "^([0-9]){" + PHONE_NUMBER_LENGTH + "}$";
	
	String 	OBJECT_ID_VALIDATION_ERROR = "Invalid object id. Expected 24 characters Hex value.",
			NO_SUCH_USER = "No such user found",
			FIRSTNAME_VALIDATION_ERROR = "First name can only contain letters, and be " + 
				FIRSTNAME_MIN_LENGTH + " - " + FIRSTNAME_MAX_LENGTH + " in length.",
			LASTNAME_VALIDATION_ERROR = "Last name can only contain letters, and be " + 
				LASTNAME_MIN_LENGTH + " - " + LASTNAME_MAX_LENGTH + " in length.",
			PHONENUMBER_VALIDATION_ERROR = "A phone number must consist of " + PHONE_NUMBER_LENGTH + " digits.",
			NOTHING_TO_UPDATE = "There is nothing to update",
			DUPLICATE_USER = "Another user with the same data already exists";	
}
