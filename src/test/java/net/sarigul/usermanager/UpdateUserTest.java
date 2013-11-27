package net.sarigul.usermanager;

import net.sarigul.usermanager.config.ConfigurationException;
import net.sarigul.usermanager.core.IndexViolationException;
import net.sarigul.usermanager.core.ValidationException;
import net.sarigul.usermanager.entity.User;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class UpdateUserTest extends UserTest {
	
	@BeforeClass
	public static void init() throws ConfigurationException {
		UserTest.init();
	}
	
	@Test
	public void update() throws Exception {
		User user = userService().create(validFirstName(), validLastName(), validPhoneNumberStr());
		
		try {
			userService().update(user.getId().toString(), validFirstName(), user.getLastName(), user.getPhoneNumber().toString());
			userService().update(user.getId().toString(), user.getFirstName(), validLastName(), user.getPhoneNumber().toString());
			userService().update(user.getId().toString(), validFirstName(), user.getLastName(), validPhoneNumberStr());
		} finally {
			userService().delete(user.getId().toString());
		}
	}
	
	@Test
	public void invalidId() throws Exception {
		try {
			userService().update(null, validFirstName(), validLastName(), validPhoneNumberStr());
			Assert.fail();
		} catch(ValidationException e) {
			try {
				userService().update(tooShortId(), validFirstName(), validLastName(), validPhoneNumberStr());
				Assert.fail();
			} catch(ValidationException ex) {
				try {
					userService().update(tooLongId(), validFirstName(), validLastName(), validPhoneNumberStr());
					Assert.fail();
				} catch(ValidationException exx) {
					try {
						userService().update(idWithNonHexLetters(), validFirstName(), validLastName(), validPhoneNumberStr());
						Assert.fail();
					} catch(ValidationException exxx) {
						// OK
					}
				}
			}
		}
	}
	
	@Test
	public void invalidFirstName() throws Exception {
		User user = userService().create(validFirstName(), validLastName(), validPhoneNumberStr());
	
		try {
			userService().update(user.getId().toString(), null, user.getLastName(), user.getPhoneNumber().toString());
			Assert.fail();
		} catch(ValidationException e) { // OK
			try { 
				userService().update(user.getId().toString(), tooShortFirstName(), user.getLastName(), user.getPhoneNumber().toString());
				Assert.fail();
			} catch(ValidationException ex) { // OK
				try {
					userService().update(user.getId().toString(), tooLongFirstName(), user.getLastName(), user.getPhoneNumber().toString());
					Assert.fail();
				} catch(ValidationException exx) {
					// OK
				}
			}
		} finally {
			userService().delete(user.getId().toString());
		}
	}
	
	@Test
	public void invalidLastName() throws Exception {
		User user = userService().create(validFirstName(), validLastName(), validPhoneNumberStr());
	
		try {
			userService().update(user.getId().toString(), user.getFirstName(), null, user.getPhoneNumber().toString());
			Assert.fail();
		} catch(ValidationException e) { // OK
			try { 
				userService().update(user.getId().toString(), user.getFirstName(), tooShortLastName(), user.getPhoneNumber().toString());
				Assert.fail();
			} catch(ValidationException ex) { // OK
				try {
					userService().update(user.getId().toString(), user.getFirstName(), tooLongLastName(), user.getPhoneNumber().toString());
					Assert.fail();
				} catch(ValidationException exx) {
					// OK
				}
			}
		} finally {
			userService().delete(user.getId().toString());
		}
	}
	
	@Test
	public void invalidPhoneNumber() throws Exception {
		User user = userService().create(validFirstName(), validLastName(), validPhoneNumberStr());
	
		try {
			userService().update(user.getId().toString(), user.getFirstName(), user.getLastName(), null);
			Assert.fail();
		} catch(ValidationException e) { // OK
			try { 
				userService().update(user.getId().toString(), user.getFirstName(), user.getLastName(), tooShortPhoneNumber());
				Assert.fail();
			} catch(ValidationException ex) { // OK
				try {
					userService().update(user.getId().toString(), user.getFirstName(), user.getLastName(), tooLongPhoneNumber());
					Assert.fail();
				} catch(ValidationException exx) { // OK
					try {
						userService().update(user.getId().toString(), user.getFirstName(), user.getLastName(), phoneNumberWithLetters());
						Assert.fail();
					} catch(ValidationException exxx) {
						// OK
					}
				}
			}
		} finally {
			userService().delete(user.getId().toString());
		}
	}
	
	
	@Test
	public void violateIndex() throws Exception {
		User first = userService().create(validFirstName(), validLastName(), validPhoneNumberStr());
		// set: only firstNames are different for next test
		User second = userService().create(validFirstName(), first.getLastName(), first.getPhoneNumber().toString());			
		
		try { // identical firstNames
			userService().update(second.getId().toString(), first.getFirstName(), second.getLastName(), second.getPhoneNumber().toString());
			Assert.fail();
		} catch(IndexViolationException e) { // OK, set: only lastNames are different for next test
			second = userService().update(second.getId().toString(), first.getFirstName(), validLastName(), first.getPhoneNumber().toString());
			
			try { // identical lastNames
				userService().update(second.getId().toString(), second.getFirstName(), first.getLastName(), second.getPhoneNumber().toString());
				Assert.fail();
			} catch(IndexViolationException ex) { // OK, set: only phoneNumbers are different for next test
				second = userService().update(second.getId().toString(), first.getFirstName(), first.getLastName(), validPhoneNumberStr());
				
				try { // identical phoneNumbers
					userService().update(second.getId().toString(), second.getFirstName(), second.getLastName(), first.getPhoneNumber().toString());
					Assert.fail();
				} catch(IndexViolationException exx) { 
					// OK, done
				}
			}
		} finally {
			userService().delete(first.getId().toString());
			userService().delete(second.getId().toString());
		}
	}
}
