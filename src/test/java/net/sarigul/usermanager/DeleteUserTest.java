package net.sarigul.usermanager;

import net.sarigul.usermanager.config.ConfigurationException;
import net.sarigul.usermanager.core.ValidationException;
import net.sarigul.usermanager.entity.User;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class DeleteUserTest extends UserTest {
	
	@BeforeClass
	public static void init() throws ConfigurationException {
		UserTest.init();
	}
	
	@Test
	public void invalidId() throws Exception {
		try {
			userService().delete(null);
			Assert.fail();
		} catch(ValidationException e) {
			try {
				userService().delete(tooShortId());
				Assert.fail();
			} catch(ValidationException ex) {
				try {
					userService().delete(tooLongId());
					Assert.fail();
				} catch(ValidationException exx) {
					try {
						userService().delete(idWithNonHexLetters());
						Assert.fail();
					} catch(ValidationException exxx) {
						// OK
					}
				}
			}
		}
	}

	
	@Test
	public void delete() throws Exception {
		User user = userService().create(validFirstName(), validLastName(), validPhoneNumberStr());
		try {
			if(userService().delete(user.getId().toString()) == null) {
				Assert.fail();
			}
		} catch(ValidationException e) {
			Assert.fail();
		} 
	}
}
