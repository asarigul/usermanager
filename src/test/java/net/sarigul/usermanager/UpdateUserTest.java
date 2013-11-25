package net.sarigul.usermanager;

import net.sarigul.usermanager.entity.User;

import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mongodb.MongoException;

public class UpdateUserTest extends AbstractTest {
	
	@BeforeClass
	public static void init() {
		AbstractTest.init();
	}
	
	@Test
	public void update() throws Exception {
		User user = new User().setFirstName(randomName())
			.setLastName(randomName()).setPhoneNumber(randomLong());
		
		try {
			Object id = create(user);
			user.setId(new ObjectId(id.toString()));
			user.setFirstName(randomName());
			if(! update(user)) {
				Assert.fail();
			}
		} finally {
			delete(user);
		}
	}
	
	@Test
	public void violateIndex() throws Exception {
		User first = new User()
						.setFirstName(randomName())
						.setLastName(randomName())
						.setPhoneNumber(randomLong()),
			
			second = new User()
						.setFirstName(first.getFirstName())
						.setLastName(first.getLastName())
						.setPhoneNumber(randomLong());
				
		try {
			if(create(first) != null) {
				if(create(second) != null) {
					second.setPhoneNumber(first.getPhoneNumber());
					try {
						update(second);
						Assert.fail();
					} catch(MongoException.DuplicateKey e) {
						// OK
					}
				} else {
					Assert.fail();
				}
			} else {
				Assert.fail();
			}
		} finally {
			delete(first);
			delete(second);
		}
	}
}
