package net.sarigul.usermanager;

import net.sarigul.usermanager.entity.User;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ReadUserTest extends AbstractTest {
	@BeforeClass
	public static void init() {
		AbstractTest.init();
	}
	
	@Test
	public void readUser() throws Exception {
		User user = new User().setFirstName(randomName())
			.setLastName(randomName())
			.setPhoneNumber(randomLong());
		
		try {
			create(user);
			Assert.assertEquals(find(user), user);
		} finally {
			delete(user);
		}
	}
	
}
