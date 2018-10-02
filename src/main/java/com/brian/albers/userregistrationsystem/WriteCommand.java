package com.brian.albers.userregistrationsystem;

import com.brian.albers.userregistrationsystem.exceptions.NullException;

/**
 * This is the interface of the FileWriteCommand class. Contains one method
 * which will be used to write a user's information to a file
 * 
 * @see FileWriteCommand
 * @author Brian.Albers
 * @version 1.0
 */
public interface WriteCommand {

	/**
	 * This method writes a User object as a String into a file.
	 * 
	 * @param user
	 *            User object that contains a username, password, and role.
	 * @throws NullException
	 *             If a null user object is passed an exception is thrown
	 * @see User
	 */
	void writeUser(User user) throws NullException;
}
