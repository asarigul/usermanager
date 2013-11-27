package net.sarigul.usermanager;

import net.sarigul.usermanager.config.ConfigurationException;
import net.sarigul.usermanager.core.ValidationException;
import net.sarigul.usermanager.entity.User;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ReadUserTest extends UserTest {
	@BeforeClass
	public static void init() throws ConfigurationException {
		UserTest.init();
	}
	
	@Test
	public void existingUser() throws Exception {
		User user = userService().create(validFirstName(), validLastName(), validPhoneNumberStr());
		
		try {
			if(userService().find(user.getId().toString()) == null) {
				Assert.fail();
			}
		} finally {
			userService().delete(user.getId().toString());
		}
	}
	
	@Test
	public void invalidId() throws Exception {
		try {
			userService().find(null);
		} catch(ValidationException e) {
			try {
				userService().find(tooShortId());
			} catch(ValidationException ex) {
				try {
					userService().find(tooLongId());
				} catch(ValidationException exx) {
					// OK
				}
			}
		}
	}
	
	
	
}
