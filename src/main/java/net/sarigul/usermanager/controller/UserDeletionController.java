package net.sarigul.usermanager.controller;

import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sarigul.usermanager.entity.User;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserDeletionController extends AjaxController {
	
	@Override
	@RequestMapping(value="/delete")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws UnknownHostException {
		String id = request.getParameter(REQUEST_ID_KEY); 
		logger.debug("handling delete request. id: {}", id);
		
		validate(id);
		
		if(! manager.delete(new User().setId(new ObjectId(id)))) {
			jsonResponse(response, false, "No such user found to delete");
		} else {
			jsonResponse(response, true, id);
		}
		
		return null;
	}

	private void validate(String id) {
		validateRegex(id, REGEX_OBJECT_ID, OBJECT_ID_VALIDATION_ERROR);
	}
}
