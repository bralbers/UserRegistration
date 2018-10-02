package com.brian.albers.userregistrationsystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.brian.albers.userregistrationsystem.exceptions.NullException;

/**
 * Implements the WriteCommand interface. Contains a constructor to set file
 * name provided by the client. Also contains one method. Method is used to
 * write the contents of an User object as a string to a file.
 * 
 * @see WriteCommand
 * @see User
 * @author Brian.Albers
 * @version 1.0
 */
public class FileWriteCommand implements WriteCommand {

	private final String FILE_NOT_FOUND = "File name not found";
	private final String OBJECT_NOT_FOUND = "Null Object was given as parameter";

	private final static String LOG_CONFIG_FILE_PATH = "./log4j.properties";
	private final static String FWC_LOG_FILE = "fileWriteCommandLog";
	private static Logger fileWriteCommandLog = Logger.getLogger(FWC_LOG_FILE);

	static {
		PropertyConfigurator.configure(LOG_CONFIG_FILE_PATH);
	}

	/**
	 * String to hold the file name and path provided by client
	 */
	private String fileToSaveUsersTo;

	/**
	 * Constructor for FileWriteCommand objects. Will create an object with a
	 * readUser method and one parameter.
	 * 
	 * @param fileToSaveUsersTo
	 *            String that will hold a file name and path provided by the client.
	 * @throws NullException
	 *             Custom Exception called it client passes null as a parameter
	 * @see NullException
	 */
	public FileWriteCommand(String fileToSaveUsersTo) throws NullException {
		fileWriteCommandLog.trace(FWC_LOG_FILE + ": Constructor making object");
		if (fileToSaveUsersTo != null)
			this.fileToSaveUsersTo = fileToSaveUsersTo;
		else {
			fileWriteCommandLog.error(FWC_LOG_FILE + ": " + FILE_NOT_FOUND);
			throw new NullException(FILE_NOT_FOUND);
		}
	}

	/**
	 * This method will write a User object to a file provided by the
	 * RegistrationController via information provided by the client.
	 * 
	 * @param user
	 *            User object containing a username, password, and role. Will be
	 *            written as a string to a file provided by the user.
	 * @throws NullException
	 *             Exception is called if null User object is passed as parameter
	 * @see User
	 * @see RegistrationController
	 * @see NullException
	 */
	@Override
	public void writeUser(User user) throws NullException {
		fileWriteCommandLog.trace(FWC_LOG_FILE + ": writeUser has been called");
		File file = new File(fileToSaveUsersTo);
		if (user != null) {
			try (FileWriter fw = new FileWriter(file)) {
				fw.append(user.toString());
			} catch (IOException e) {
				fileWriteCommandLog.error(FWC_LOG_FILE + ": IOException has been found in writeUser.");
				e.printStackTrace();
				e.getMessage();
			}
		} else
			throw new NullException(OBJECT_NOT_FOUND);
	}
}