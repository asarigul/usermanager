package net.sarigul.usermanager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sarigul.usermanager.core.ApplicationException;
import net.sarigul.usermanager.core.InternalErrorException;
import net.sarigul.usermanager.core.ValidationException;
import net.sarigul.usermanager.entity.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserCreationController extends AjaxController {
	
	@Override
	@RequestMapping("/create")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ApplicationException  {
		logger.debug("handling create request");
		
		if(! CaptchaController.isAnswerValid(request)) {
			throw new ValidationException("invalid captcha");
		}

		String firstName = request.getParameter(REQUEST_FIRSTNAME_KEY);
		String lastName = request.getParameter(REQUEST_LASTNAME_KEY);
		String phoneNumber = request.getParameter(REQUEST_PHONENUMBER_KEY);
		
		User created = userService().create(firstName, lastName, phoneNumber);
		if(created == null) {
			throw new InternalErrorException("cannot create user", null);
		}
		
		jsonResponse(response, created.getId().toString());
		
		return null;
	}
	
}
