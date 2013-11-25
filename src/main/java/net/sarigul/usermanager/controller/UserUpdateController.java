package net.sarigul.usermanager.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sarigul.usermanager.core.ValidationException;
import net.sarigul.usermanager.entity.User;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserUpdateController extends AjaxController {
	
	@Override
	@RequestMapping(value="/update")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User user = getRequestedUser(request);
		if (manager.update(user)) {
			jsonResponse(response, true, user.getId().toString());
		} else {
			jsonResponse(response, false, "No user updated");
		}
		
		return null;
	}

	@Override
	protected User getRequestedUser(HttpServletRequest request) {
		String id = request.getParameter(REQUEST_ID_KEY);
		if(id == null) {
			throw new ValidationException("User id not sent!");
		}
		return super.getRequestedUser(request).setId(new ObjectId(id));
	}
}
