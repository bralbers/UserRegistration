package com.brian.albers.userregistrationsystem;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Represents a user created by the client. Will be made every time called by
 * the UserFactory
 * 
 * @see UserFactory
 * @author Brian.Albers
 * @version 1.0
 */
public class User {
	private final static String LOG_CONFIG_FILE_PATH = "./log4j.properties";
	private static final String USER_LOG_NAME = "userLog";
	private static Logger userLog = Logger.getLogger(USER_LOG_NAME);
	static {
		PropertyConfigurator.configure(LOG_CONFIG_FILE_PATH);
	}
	/**
	 * Unmodifiable String that will set variables that are not passed parameters to
	 * a default statement
	 */
	private final String INFORMATION_NOT_SUPPLIED = "not available";

	/**
	 * String containing a username
	 */
	private String username;
	/**
	 * String containing a password
	 */
	private String password;
	/**
	 * String containing a role
	 */
	private String role;

	/**
	 * Default constructor that will set all object parameters to "not available".
	 */
	public User() {
		userLog.trace(USER_LOG_NAME + ": Default User Constructor has been called.");
		username = INFORMATION_NOT_SUPPLIED;
		password = INFORMATION_NOT_SUPPLIED;
		role = INFORMATION_NOT_SUPPLIED;

	}

	/**
	 * Constructor that will set all Object parameters of User to parameters
	 * supplied by the client.
	 * 
	 * @param username
	 *            String containing a username supplied by a client. Must not be
	 *            null.
	 * @param password
	 *            String containing a password supplied by a client. Must not be
	 *            null.
	 * @param role
	 *            String containing a role supplied by a client. Must not be null.
	 */
	public User(String username, String password, String role) {
		userLog.trace(USER_LOG_NAME + ": User Constructor has been called.");
		this.username = username.toLowerCase();
		this.password = password;
		this.role = role;
	}

	/**
	 * Gets the username of this user
	 * 
	 * @return This user's name
	 */
	public String getUsername() {
		userLog.trace(USER_LOG_NAME + ": username is being requested");
		return username;
	}

	/**
	 * Changes the name of this user
	 * 
	 * @param username
	 *            The username supplied by the client
	 */
	public void setUsername(String username) {
		userLog.trace(USER_LOG_NAME + ": Setting username");
		this.username = username;
	}

	/**
	 * Gets the password of this user
	 * 
	 * @return This user's password
	 */
	public String getPassword() {
		userLog.trace(USER_LOG_NAME + ": password is being requested");
		return password;
	}

	/**
	 * Changes the password of this user
	 * 
	 * @param password
	 *            The password supplied by the client
	 */
	public void setPassword(String password) {
		userLog.trace(USER_LOG_NAME + ": Setting password");
		this.password = password;
	}

	/**
	 * Gets the role of this user
	 * 
	 * @return This user's role
	 */
	public String getRole() {
		userLog.trace(USER_LOG_NAME + ": role is being requested");
		return role;
	}

	/**
	 * Changes the role of this user
	 * 
	 * @param role
	 *            The role supplied by the client
	 */
	public void setRole(String role) {
		userLog.trace(USER_LOG_NAME + ": Setting role");
		this.role = role;
	}

	/**
	 * Override of Object toString() to display a user's information in the format
	 * of: username,password,role
	 */
	@Override
	public String toString() {
		userLog.trace(USER_LOG_NAME + ": User object's toString is being called");
		return username + "," + password + "," + role;
	}

	/**
	 * Override of Object equals to compare the contents of this User object
	 * compared to another User object
	 * 
	 * @return True if the contents are the same. False if the contents are not the
	 *         same.
	 */
	@Override
	public boolean equals(Object obj) {
		userLog.trace(USER_LOG_NAME + ": User object comparison method has been called");
		if (obj == null || !(obj instanceof User)) {
			return false;
		} else {
			User userObj = (User) obj;
			if (this.username.equals(userObj.getUsername()) && this.password.equals(userObj.getPassword())
					&& this.role.equals(userObj.getRole()))
				return true;
		}
		return false;
	}
}