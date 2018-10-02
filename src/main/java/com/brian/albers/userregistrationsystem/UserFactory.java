package com.brian.albers.userregistrationsystem;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Represents a factory that causes a new user to be created every time it is
 * called.
 * 
 * @author Brian.Albers
 * @version 1.0
 * @see User
 */
public class UserFactory {
	private final static String LOG_CONFIG_FILE_PATH = "./log4j.properties";
	private static final String UF_LOG_NAME = "userFactoryLog";
	private static Logger userFactoryLog = Logger.getLogger(UF_LOG_NAME);

	static {
		PropertyConfigurator.configure(LOG_CONFIG_FILE_PATH);
	}
	/**
	 * 
	 * @param username
	 *            String that will contain a username from RegistrationController
	 * @param password
	 *            String that will contain a password from RegistrationController
	 * @param role
	 *            String that will contain a role from RegistrationController
	 * @return An User object containing parameters passed
	 * @see RegistrationController
	 * @see User
	 */
	public User createUser(String username, String password, String role) {
		userFactoryLog.trace(UF_LOG_NAME+": createUser is generating a new User");
		return new User(username, password, role);
	}
}