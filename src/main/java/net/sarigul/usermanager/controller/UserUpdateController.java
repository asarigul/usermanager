package net.sarigul.usermanager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sarigul.usermanager.core.ApplicationException;
import net.sarigul.usermanager.core.InternalErrorException;
import net.sarigul.usermanager.entity.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserUpdateController extends AjaxController {
	
	@Override
	@RequestMapping(value="/update")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ApplicationException  {
		String id = request.getParameter(REQUEST_ID_KEY);
		String firstName = request.getParameter(REQUEST_FIRSTNAME_KEY);
		String lastName = request.getParameter(REQUEST_LASTNAME_KEY);
		String phoneNumber = request.getParameter(REQUEST_PHONENUMBER_KEY);
		
		User updated = userService().update(id, firstName, lastName, phoneNumber);
		if (updated == null) {
			throw new InternalErrorException("couldn't update user", null);
		} 
		
		jsonResponse(response, updated.getId().toString());
		return null;
	}
}
