package net.sarigul.usermanager;

import net.sarigul.usermanager.config.ConfigurationException;
import net.sarigul.usermanager.core.ApplicationException;
import net.sarigul.usermanager.core.IndexViolationException;
import net.sarigul.usermanager.core.ValidationException;
import net.sarigul.usermanager.entity.User;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class CreateUserTest extends UserTest {
	
	@BeforeClass
	public static void init() throws ConfigurationException {
		UserTest.init();
	}
	
	@Test
	public void invalidFirstName() throws ApplicationException {
		try {
			userService().create(null, validLastName(), validPhoneNumberStr());
			Assert.fail();
		} catch (ValidationException e) {
			try {
				userService().create(tooShortFirstName(), validLastName(), validPhoneNumberStr());
				Assert.fail();
			} catch(ValidationException ex) {
				try {
					userService().create(tooLongFirstName(), validLastName(), validPhoneNumberStr());
					Assert.fail();
				} catch(ValidationException exx) {
					// OK
				}
			}
		}
	}
	
	
	@Test
	public void invalidLastName() throws ApplicationException {
		String validFirstName = validFirstName(), validPhoneNumber = validPhoneNumberStr();
		
		try {
			userService().create(validFirstName, null, validPhoneNumber);
			Assert.fail();
		} catch (ValidationException e) {
			try {
				userService().create(validFirstName, tooShortLastName(), validPhoneNumber);
				Assert.fail();
			} catch(ValidationException ex) {
				try {
					userService().create(validFirstName, tooLongLastName(), validPhoneNumber);
					Assert.fail();
				} catch(ValidationException exx) {
					// OK
				}
			}
		}
	}

	@Test
	public void invalidPhoneNumber() throws ApplicationException {
		String validFirstName = validFirstName(), validLastName = validLastName();
		
		try {
			userService().create(validFirstName, validLastName, null);
			Assert.fail();
		} catch (ValidationException e) {
			try {
				userService().create(validFirstName, validLastName, tooShortPhoneNumber());
				Assert.fail();
			} catch(ValidationException ex) {
				try {
					userService().create(validFirstName, validLastName, tooLongPhoneNumber());
					Assert.fail();
				} catch(ValidationException exx) {
					try {
						userService().create(validFirstName, validLastName, phoneNumberWithLetters());
						Assert.fail();
					} catch(ValidationException exxx) {
						// OK
					}
				}
			}
		}
	}
	
	public void validUser() throws Exception {
		User user = null;
		try {
			user = userService().create(validFirstName(), validLastName(), validPhoneNumberStr());
			if(user == null) {
				Assert.fail();
			}
		} catch(Exception ex) {
			Assert.fail();
		} finally {
			if(user != null) {
				userService().delete(user.getId().toString());
			}
		}
	}
	
	@Test
	public void violateIndex() throws Exception {
		User first = userService().create(validFirstName(), validLastName(), validPhoneNumberStr());
		try {
			userService().create(first.getFirstName(), first.getLastName(), first.getPhoneNumber().toString());
			Assert.fail();
		} catch(IndexViolationException e) {
			// OK
		} finally {
			userService().delete(first.getId().toString());
		}
	}
}
