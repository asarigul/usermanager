package net.sarigul.usermanager.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserCreationController extends AjaxController {
	
	@Override
	@RequestMapping("/create")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.debug("handling create request");
		
		if(! CaptchaController.isAnswerValid(request)) {
			throw new ValidationException("invalid captcha");
		}
		
		jsonResponse(response, true, manager.create(getRequestedUser(request)).toString());
		return null;
	}
	
}
