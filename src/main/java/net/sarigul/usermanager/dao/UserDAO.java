package net.sarigul.usermanager.dao;

import java.util.List;

import net.sarigul.usermanager.entity.User;

import org.bson.types.ObjectId;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;
import com.mongodb.Mongo;

public class UserDAO extends BasicDAO<User, ObjectId> {

	public UserDAO(Mongo mongo, Morphia morphia, String dbName) {
		super(mongo, morphia, dbName);
	}

	public List<User> allUsers() {
		Query<User> query = ds.createQuery(User.class).order("lastName");
		return query.asList();
	}
	
	public User find(ObjectId id) {
		return ds.get(User.class, id);
	}
	
	public boolean updateUser(User user) {
		Query<User> query = ds.createQuery(User.class).filter("_id", user.getId());
		UpdateOperations<User> ops = ds.createUpdateOperations(User.class)
			.set("firstName", user.getFirstName())
			.set("lastName", user.getLastName())
			.set("phoneNumber", user.getPhoneNumber());
		return ds.update(query, ops).getUpdatedCount() == 1;
	}
}
