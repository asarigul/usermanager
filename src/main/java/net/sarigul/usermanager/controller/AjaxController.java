package net.sarigul.usermanager.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sarigul.usermanager.entity.User;
import net.sarigul.usermanager.util.IOUtil;
import net.sarigul.usermanager.util.JSONObject;
import net.sarigul.usermanager.util.Toolbox;

public abstract class AjaxController extends AbstractController {

	protected User getRequestedUser(HttpServletRequest request) {
		String firstName = request.getParameter(REQUEST_FIRSTNAME_KEY);
		String lastName = request.getParameter(REQUEST_LASTNAME_KEY);
		String phoneNumber = request.getParameter(REQUEST_PHONENUMBER_KEY);
		
		validateFirstName(firstName);
		validateLastName(lastName);
		validatePhoneNumber(phoneNumber);
		
		return new User().setFirstName(firstName).setLastName(lastName)
				.setPhoneNumber(Long.parseLong(phoneNumber));
	}
	
	private void validateFirstName(String firstName) {
		validateRegex(firstName, REGEX_FIRSTNAME, FIRSTNAME_VALIDATION_ERROR);
	}
	
	private void validateLastName(String lastName) {
		validateRegex(lastName, REGEX_LASTNAME, LASTNAME_VALIDATION_ERROR);
	}
	
	private void validatePhoneNumber(String phoneNumber) {
		validateRegex(phoneNumber, REGEX_PHONE_NUMBER, PHONENUMBER_VALIDATION_ERROR);
	}
	
	protected void validateRegex(String value, String regex, String error) {
		if(value == null) {
			throw new IllegalArgumentException(error);
		}
		
		if(! Toolbox.checkRegex(value, regex)) {
			throw new IllegalArgumentException(error);
		}
	}
	
	protected void jsonResponse(HttpServletResponse response, boolean success, String info) {
		if(success) {
			logger.debug("successful json response. info: {}", info);
		} else {
			logger.warn("failed json response. info: {}", info);
		}
		
		try {
			_jsonResponse(response, success, info);
		} catch (IOException e) {
			logger.error("IOException while writing JSON response", e);
		}
	}
	
	public static void _jsonResponse(HttpServletResponse response, boolean success, String info) throws IOException {
		IOUtil.jsonResponse(response, new JSONObject().add(RESPONSE_RESULT_KEY, success).add(RESPONSE_INFO_KEY, info));
	}
}
