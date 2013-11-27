package net.sarigul.usermanager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sarigul.usermanager.core.Application;
import net.sarigul.usermanager.core.ApplicationException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController extends UserController {

	@Override
	@RequestMapping("/")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
		logger.debug("handling main page request");
				
		return new ModelAndView("main")
			// protocol
			.addObject("capcthaLength", Application.configuration().getCaptchaLength())
			.addObject("PHONE_NUMBER_LENGTH", PHONE_NUMBER_LENGTH)
			
			.addObject("REGEX_FIRSTNAME", REGEX_FIRSTNAME)
			.addObject("REGEX_LASTNAME", REGEX_LASTNAME)
			.addObject("REGEX_PHONE_NUMBER", REGEX_PHONE_NUMBER)
			
			.addObject("FIRSTNAME_VALIDATION_ERROR", FIRSTNAME_VALIDATION_ERROR)
			.addObject("LASTNAME_VALIDATION_ERROR", LASTNAME_VALIDATION_ERROR)
			.addObject("PHONENUMBER_VALIDATION_ERROR", PHONENUMBER_VALIDATION_ERROR)
			
			.addObject("REQUEST_ID_KEY", REQUEST_ID_KEY)
			.addObject("REQUEST_FIRSTNAME_KEY", REQUEST_FIRSTNAME_KEY)
			.addObject("REQUEST_LASTNAME_KEY", REQUEST_LASTNAME_KEY)
			.addObject("REQUEST_PHONENUMBER_KEY", REQUEST_PHONENUMBER_KEY)
			.addObject("REQUEST_CAPTCHA_KEY", REQUEST_CAPTHCA_KEY)
			
			.addObject("RESPONSE_RESULT_KEY", RESPONSE_RESULT_KEY)
			.addObject("RESPONSE_INFO_KEY", RESPONSE_INFO_KEY)
			
			// data
			.addObject("userCount", userService().count())
			.addObject("users", userService().allUsers());
	}
}
