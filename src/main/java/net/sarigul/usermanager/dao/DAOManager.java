package net.sarigul.usermanager.dao;

import java.net.UnknownHostException;

import net.sarigul.usermanager.config.Configuration;
import net.sarigul.usermanager.config.ConfigurationException;
import net.sarigul.usermanager.core.Application;
import net.sarigul.usermanager.core.HostUnknownException;
import net.sarigul.usermanager.core.IndexViolationException;
import net.sarigul.usermanager.core.MongoNetworkException;
import net.sarigul.usermanager.entity.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.morphia.Morphia;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

// singleton
public final class DAOManager {
	private final Logger logger;
	private final Morphia morphia;
	private final MongoClient mongoClient;
	
	private static DAOManager instance;
	
	private DAOManager()  {
		logger = LoggerFactory.getLogger(getClass());
		morphia = new Morphia().map(User.class);
		try {
			mongoClient = getMongoClient();
		} catch (UnknownHostException e) {
			throw new HostUnknownException("cannot resolve host", e);
		}
	}
	
	public synchronized static DAOManager instance() {
		if(instance == null) {
			instance = new DAOManager();
		}
		return instance;
	}
	
	private MongoClient getMongoClient() throws UnknownHostException {
		logger.info("creating the client");
		
		String host = Application.configuration().getMongodbHost();
		Integer port = Application.configuration().getMongodbPort();
		
		if(port == null) {
			return new MongoClient(host);
		} 
		return new MongoClient(host, port);
	}
	
	// TODO a thread always gets the same dao 
	public UserDAO getUserDAO() {
		logger.debug("creating a dao object");
		
		
		if(Application.configuration().isDatabaseAuthenticationEnabled()) {
			String username = Application.configuration().getMongodbUsername();
			String password = Application.configuration().getMongodbPassword();
			
			DB db = mongoClient.getDB(Configuration.DB_NAME);
			if(! db.authenticate(username, password.toCharArray())) {
				throw new ConfigurationException("database authentication failed. check credentials in usermanager.properties");
			}
		}
		
		UserDAO dao = new UserDAO(mongoClient, morphia, Configuration.DB_NAME);
		
		try {
			dao.ensureIndexes();
		} catch(MongoException.Network e) {
			throw new MongoNetworkException("Cannot connect to database", e);
		} catch(MongoException.DuplicateKey e) {
			throw new IndexViolationException("Another user with the same data exists", e);
		}
		
		return dao;
	}
}
