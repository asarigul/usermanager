package net.sarigul.usermanager.util;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletResponse;

public class IOUtil {
	private static final Charset UTF8 = Charset.forName("UTF-8");
	
	public static void jsonResponse(HttpServletResponse response, JSONObject json) throws IOException {
		if(response == null) {
			throw new NullPointerException();
		}
		
		write(addJSONHeaders(response).getOutputStream(), json.toJSON().getBytes(UTF8));
	}
	
	private static final HttpServletResponse addJSONHeaders(HttpServletResponse response) {
		// provided response not null
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("application/json");
		
		return response;
	}
	
	public static void write(OutputStream stream, byte[] data) throws IOException {
		if(stream == null || data == null) {
			throw new NullPointerException();
		}
		
		 try {
        	stream.write(data);
        	stream.flush();
        } finally {
        	stream.close();
        }
	}
	
}
