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
public class UserDeletionController extends AjaxController {
	
	@Override
	@RequestMapping(value="/delete")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
		String id = request.getParameter(REQUEST_ID_KEY); 
		logger.debug("handling delete request. id: {}", id);
		
		User deleted = userService().delete(id);
		if(deleted == null) {
			throw new InternalErrorException("couldn't delete user", null);
		}
		
		jsonResponse(response, deleted.getId().toString());
		return null;
	}
}
