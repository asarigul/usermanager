package net.sarigul.usermanager.util;

import java.net.UnknownHostException;
import java.util.List;

import net.sarigul.usermanager.dao.DAOManager;
import net.sarigul.usermanager.dao.UserDAO;
import net.sarigul.usermanager.entity.User;

import org.bson.types.ObjectId;

public class UserManager {
	public long count() throws UnknownHostException {
		return dao().count();
	}
	
	public Object create(User user) throws UnknownHostException {
		return dao().save(user).getId();
	}
	
	public List<User> allUsers() throws UnknownHostException {
		return dao().allUsers();
	}
	
	public User find(ObjectId id) throws UnknownHostException {
		return dao().find(id);
	}
	
	public boolean update(User user) throws UnknownHostException {
		return dao().updateUser(user);
	}
	
	public boolean delete(User user) throws UnknownHostException {
		return dao().deleteById(user.getId()).getN() == 1;
	}

	private UserDAO dao() throws UnknownHostException {
		return DAOManager.instance().getUserDAO();
	}
}
