package com.brian.albers.userregistrationsystem;

/**
 * This is the interface for FileWriteCommand. Contains one method that will be
 * used in the reading of an user object.
 * 
 * @see FileWriteCommand
 * @see User
 * @author Brian.Albers
 * @version 1.0
 * 
 */
public interface ReadCommand {

	/**
	 * This method will take the String parameter passed from the client and search
	 * a file for all relevant information i.e. username, password, and role.
	 * 
	 * @param username
	 *            String that will hold a username provided by the client
	 * @return User object found based on supplied parameter. If information is not
	 *         found then a null object will be returned
	 * @see User
	 */
	User readUser(String username);
}
