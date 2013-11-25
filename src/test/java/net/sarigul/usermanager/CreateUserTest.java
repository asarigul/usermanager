package net.sarigul.usermanager;

import net.sarigul.usermanager.entity.User;
import net.sarigul.usermanager.util.Toolbox;

import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mongodb.MongoException;

public class CreateUserTest extends AbstractTest {
	
	@BeforeClass
	public static void init() {
		AbstractTest.init();
	}
	
	@Test
	public void createUnsetUser() throws Exception {
		User unsetUser = new User().setFirstName(Toolbox.randomString(5));
		try {
			create(unsetUser);
			Assert.fail();
		} catch(Exception e) {
			unsetUser.setLastName(Toolbox.randomString(5));
			try {
				create(unsetUser);
				Assert.fail();
			} catch(Exception ex) {
				unsetUser.setPhoneNumber(Toolbox.randomLong(10));
				try {
					create(unsetUser);
					// OK
				} catch(Exception exx) {
					Assert.fail();
				} finally {
					delete(unsetUser);
				}
			}
		} 
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
			delete(user);
		}
	}
	
	@Test
	public void violateIndex() throws Exception {
		User existing = new User()
							.setFirstName(randomName())
							.setLastName(randomName())
							.setPhoneNumber(randomLong());
		
		try {
			create(existing);
			User same = new User()
								.setFirstName(existing.getFirstName())
								.setLastName(existing.getLastName())
								.setPhoneNumber(existing.getPhoneNumber());
			try {
				create(same);
				Assert.fail();
			} catch(MongoException.DuplicateKey ex) {
				// OK
			} 
		} finally {
			delete(existing);
		}
	}
}
