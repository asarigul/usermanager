package net.sarigul.usermanager;

import java.net.UnknownHostException;

import net.sarigul.usermanager.core.Application;
import net.sarigul.usermanager.entity.User;
import net.sarigul.usermanager.util.Toolbox;
import net.sarigul.usermanager.util.UserManager;

public class AbstractTest {
	private static UserManager manager = new UserManager();

	protected static void init() {
		Application.initConfiguration(null);
	}

	protected static String randomName() {
		return Toolbox.randomString(5);
	}
	
	protected static long randomLong() {
		return Toolbox.randomLong(10);
	}
	
	protected static User find(User user) throws UnknownHostException {
		return manager.find(user.getId());
	}
	
	protected static boolean delete(User user) throws UnknownHostException {
		return manager.delete(user);
	}

	protected static Object create(User user) throws UnknownHostException {
		return manager.create(user);
	}

	protected static boolean update(User user) throws UnknownHostException {
		return manager.update(user);
	}
}
