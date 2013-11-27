package net.sarigul.usermanager.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sarigul.usermanager.core.Application;
import net.sarigul.usermanager.core.InternalErrorException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

@Controller
public class CaptchaController extends UserController {
	private static final Properties CAPTCHA_PROPERTIES = new Properties();
	
	static {
		if(Application.initException() == null) {
			CAPTCHA_PROPERTIES.put(Constants.KAPTCHA_TEXTPRODUCER_CHAR_STRING,
					Application.configuration().getCaptchaCharacters());
			CAPTCHA_PROPERTIES.put(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH,
					Application.configuration().getCaptchaLength() + "");
		}
	}
	
	@Override
	@RequestMapping("/captcha/*")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws InternalErrorException {
		logger.debug("creating captcha");
		
		DefaultKaptcha producer = producer();
		String captchaText = producer.createText();
		try {
			writeCaptcha(producer, captchaText, response.getOutputStream());
		} catch(IOException e) {
			throw new InternalErrorException("cannot write captcha", e);
		}
		
		session(request).setAttribute(Constants.KAPTCHA_SESSION_KEY, captchaText);
		
		return null;
	}
	
	static boolean isAnswerValid(HttpServletRequest request) {
		Object textObject = session(request).getAttribute(Constants.KAPTCHA_SESSION_KEY);
		
		if(textObject == null) {
			throw new NullPointerException("dead session");
		}
		
		String text = textObject.toString(), answer = request.getParameter(REQUEST_CAPTHCA_KEY);
		
		if(Application.configuration().isCaseSensitive()) {
			return text.equals(answer);
		}
		
		return text.equalsIgnoreCase(answer);
	}

	private static DefaultKaptcha producer() {
		DefaultKaptcha producer = new DefaultKaptcha();
		producer.setConfig(new Config(CAPTCHA_PROPERTIES));
		
		return producer;
	}

	private static void writeCaptcha(DefaultKaptcha producer, String text, OutputStream out) throws IOException {
		try {
			BufferedImage bufferedImage = producer.createImage(text);
			if (! ImageIO.write(bufferedImage, "jpg", out)) {
				throw new IOException("Cannot write image");
			}
			out.flush();
		} finally {
			out.close();
		}
	}
}
