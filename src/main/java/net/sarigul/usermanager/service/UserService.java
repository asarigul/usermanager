package net.sarigul.usermanager.service;

import java.util.List;

import net.sarigul.usermanager.config.ConfigurationException;
import net.sarigul.usermanager.controller.Protocol;
import net.sarigul.usermanager.core.HostUnknownException;
import net.sarigul.usermanager.core.IndexViolationException;
import net.sarigul.usermanager.core.MongoNetworkException;
import net.sarigul.usermanager.core.ValidationException;
import net.sarigul.usermanager.dao.DAOManager;
import net.sarigul.usermanager.dao.UserDAO;
import net.sarigul.usermanager.entity.User;
import net.sarigul.usermanager.util.Toolbox;

import org.bson.types.ObjectId;

import com.mongodb.MongoException;

public class UserService {
	private final UserDAO dao;
	
	public UserService() throws ConfigurationException, HostUnknownException, MongoNetworkException{
		this.dao = dao();
	}
	
	public long count()  {
		return dao.count();
	}
	
	public User create(String firstName, String lastName, String phoneNumberStr) throws ValidationException, IndexViolationException {
		validateFirstName(firstName);
		validateLastName(lastName);
		validatePhoneNumber(phoneNumberStr);
		
		User user = new User().setFirstName(firstName)
				.setLastName(lastName)
				.setPhoneNumber(Long.parseLong(phoneNumberStr));
		
		try {
			return user.setId((ObjectId) dao.save(user).getId());
		} catch(MongoException.DuplicateKey e) {
			throw new IndexViolationException(e);
		}
	}
	
	public List<User> allUsers()  {
		return dao.allUsers();
	}
	
	public User find(String id) throws ValidationException  {
		validateId(id);
		
		User user = dao.find(new ObjectId(id));
		if(user == null) {
			throw new ValidationException(Protocol.NO_SUCH_USER);
		}
		return user;
	}
	
	public User update(String idStr, String firstName, String lastName, String phoneNumberStr) 
			throws ValidationException, IndexViolationException  {
		User existing = find(idStr);
		
		validateFirstName(firstName);
		validateLastName(lastName);
		validatePhoneNumber(phoneNumberStr);
		
		User user = new User().setId(new ObjectId(idStr))
				.setFirstName(firstName)
				.setLastName(lastName)
				.setPhoneNumber(Long.parseLong(phoneNumberStr));
		
		if(existing.equals(user)) {
			throw new ValidationException(Protocol.NOTHING_TO_UPDATE);
		}
		
		try {
			if(dao.updateUser(user)) {
				return user;
			}
			return null;
		} catch(MongoException.DuplicateKey e) {
			throw new IndexViolationException(e);
		}
	}
	
	public User delete(String idStr) throws ValidationException  {
		User existing = find(idStr);
		
		if( dao.deleteById(new ObjectId(idStr)).getN() == 1) {
			return existing;
		}
		
		return null;
	}
	
	private void validateId(String id) throws ValidationException {
		validateRegex(id, Protocol.REGEX_OBJECT_ID, Protocol.OBJECT_ID_VALIDATION_ERROR);
	}

	private void validateFirstName(String firstName) throws ValidationException {
		validateRegex(firstName, Protocol.REGEX_FIRSTNAME, Protocol.FIRSTNAME_VALIDATION_ERROR);
	}
	
	private void validateLastName(String lastName) throws ValidationException {
		validateRegex(lastName, Protocol.REGEX_LASTNAME, Protocol.LASTNAME_VALIDATION_ERROR);
	}
	
	private void validatePhoneNumber(String phoneNumberStr) throws ValidationException {
		validateRegex(phoneNumberStr, Protocol.REGEX_PHONE_NUMBER, Protocol.PHONENUMBER_VALIDATION_ERROR);
	}
	
	private void validateRegex(String value, String regex, String error) throws ValidationException {
		if(value == null) {
			throw new ValidationException(error);
		}
		
		if(! Toolbox.checkRegex(value, regex)) {
			throw new ValidationException(error);
		}
	}
	
	private UserDAO dao() throws ConfigurationException, HostUnknownException, MongoNetworkException {
		return DAOManager.instance().getUserDAO();
	}
}
