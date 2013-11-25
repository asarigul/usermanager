package net.sarigul.usermanager;

import net.sarigul.usermanager.entity.User;
import net.sarigul.usermanager.util.Toolbox;

import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class DeleteUserTest extends AbstractTest {
	@BeforeClass
	public static void init() {
		AbstractTest.init();
	}
	
	@Test
	public void createUser() throws Exception {
		User user = new User().setFirstName(Toolbox.randomString(5)).
				setLastName(Toolbox.randomString(5)).setPhoneNumber(Toolbox.randomLong(10));
		Object id = null; 
		try {
			id = create(user);
			user.setId(new ObjectId(id.toString()));
		} finally {
			if(! delete(user)) {
				Assert.fail();
			}
		}
	}
}
