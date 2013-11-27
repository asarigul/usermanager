package net.sarigul.usermanager.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import net.sarigul.usermanager.util.IOUtil;
import net.sarigul.usermanager.util.JSONObject;

public abstract class AjaxController extends UserController {

	
	protected void jsonResponse(HttpServletResponse response, String info) {
//		if(success) {
//			logger.debug("successful json response. info: {}", info);
//		} else {
//			logger.warn("failed json response. info: {}", info);
//		}
		
		try {
			_jsonResponse(response, true, info);
		} catch (IOException e) {
			logger.error("IOException while writing JSON response", e);
		}
	}
	
	public static void _jsonResponse(HttpServletResponse response, boolean success, String info) throws IOException {
		IOUtil.jsonResponse(response, new JSONObject().add(RESPONSE_RESULT_KEY, success).add(RESPONSE_INFO_KEY, info));
	}
}
