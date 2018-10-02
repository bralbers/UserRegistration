package com.brian.albers.userregistrationsystem;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.brian.albers.userregistrationsystem.exceptions.EmptyStringException;
import com.brian.albers.userregistrationsystem.exceptions.NullException;

/**
 * Represents a controller that will register new users. A new user will consist
 * of a: username, password, and role.
 * 
 * @author Brian.Albers
 * @version 1.0
 */
public class RegistrationController {

	private final String EMPTY_STRING_USERNAME = "Username parameter is empty";
	private final String EMPTY_STRING_PASSWORD = "Username parameter is empty";
	private final String EMPTY_STRING_ROLE = "Username parameter is empty";

	private final String NULL_USER_FACTORY = "Reference to UserFactory was null.";
	private final String NULL_WRITE_COMMAND = "Reference to Write Command was null.";
	private final String NULL_READ_COMMAND = "Reference to Read Command was null.";

	private final String NULL_USERNAME = "Username parameter is null";
	private final String NULL_PASSWORD = "Password parameter is null";
	private final String NULL_ROLE = "Role parameter is null";

	private final static String RC_LOG_NAME = "registrationControllerLog";
	private final static String LOG_CONFIG_FILE_PATH = "./log4j.properties";

	private static Logger registrationControllerLog = Logger.getLogger(RC_LOG_NAME);

	static {
		PropertyConfigurator.configure(LOG_CONFIG_FILE_PATH);
	}
	/**
	 * UserFactory object
	 * 
	 * @see UserFactory
	 */
	private UserFactory uf;
	/**
	 * WriteCommand object
	 * 
	 * @see WriteCommand
	 */
	private WriteCommand wc;
	/**
	 * ReadCommand object
	 * 
	 * @see ReadCommand
	 */
	private ReadCommand rdc;

	/**
	 * This is the constructor for the RegistrationController object. It sets the
	 * UserFactory, WriteCommand, and ReadCommand objects to the object parameters
	 * supplied by the client
	 * 
	 * @param uf
	 *            Reference to an UserFactory object. Must not be null
	 * @param wc
	 *            Reference to a WriteCommand object. Must not be null
	 * @param rdc
	 *            Reference to a ReadCommand object. Must not be null
	 * @throws NullException
	 *             Custom Exception called it client passes null as a parameter
	 * 
	 * @see UserFactory
	 * @see WriteCommand
	 * @see ReadCommand
	 * @see NullException
	 */
	public RegistrationController(UserFactory uf, WriteCommand wc, ReadCommand rdc) throws NullException {
		registrationControllerLog.trace(RC_LOG_NAME + ": Constructor making object");
		if (uf != null && wc != null && rdc != null) {
			this.uf = uf;
			this.wc = wc;
			this.rdc = rdc;
		} else if (uf != null && wc != null && rdc == null) {
			registrationControllerLog.error(RC_LOG_NAME + ": " + NULL_READ_COMMAND);
			throw new NullException(NULL_READ_COMMAND);
		} else if (uf != null && wc == null && rdc != null) {
			registrationControllerLog.error(RC_LOG_NAME + ": " + NULL_WRITE_COMMAND);
			throw new NullException(NULL_WRITE_COMMAND);
		} else if (uf == null && wc != null && rdc != null) {
			registrationControllerLog.error(RC_LOG_NAME + ": " + NULL_USER_FACTORY);
			throw new NullException(NULL_USER_FACTORY);
		}
	}

	/**
	 * This method will take parameters supplied by the client and create a new User
	 * object via the UserFactory class
	 * 
	 * @see User
	 * @see UserFactory
	 * 
	 * @param username
	 *            String containing a username supplied by a client. Must not be
	 *            null.
	 * @param password
	 *            String containing a password supplied by a client. Must not be
	 *            null.
	 * @param role
	 *            String containing a role supplied by a client. Must not be null.
	 * @throws NullException
	 *             Exception called when client passes null as a parameter
	 * @throws EmptyStringException
	 *             Exception called when client passes an empty String
	 * @see NullException
	 * @see EmptyStringException
	 */
	public void registerNewUser(String username, String password, String role)
			throws NullException, EmptyStringException {
		registrationControllerLog.trace(RC_LOG_NAME + ": registerNewUser is making a user");
		if (username != null) {
			username = username.toLowerCase();
		}

		if (username == "") {
			registrationControllerLog.error(RC_LOG_NAME + ": " + EMPTY_STRING_USERNAME);
			throw new EmptyStringException(EMPTY_STRING_USERNAME);
		} else if (password == "") {
			registrationControllerLog.error(RC_LOG_NAME + ": " + EMPTY_STRING_PASSWORD);
			throw new EmptyStringException(EMPTY_STRING_PASSWORD);
		} else if (role == "") {
			registrationControllerLog.error(RC_LOG_NAME + ": " + EMPTY_STRING_ROLE);
			throw new EmptyStringException(EMPTY_STRING_ROLE);
		}

		if (username != null && password != null && role != null) {
			User user = uf.createUser(username, password, role);
			wc.writeUser(user);
			rdc.readUser(username);
		} else if (username != null && password != null && role == null) {
			registrationControllerLog.error(RC_LOG_NAME + ": " + NULL_ROLE);
			throw new NullException(NULL_ROLE);
		} else if (username != null && password == null && role != null) {
			registrationControllerLog.error(RC_LOG_NAME + ": " + NULL_PASSWORD);
			throw new NullException(NULL_PASSWORD);
		} else if (username == null && password != null && role != null) {
			registrationControllerLog.error(RC_LOG_NAME + ": " + NULL_USERNAME);
			throw new NullException(NULL_USERNAME);
		}
	}
}