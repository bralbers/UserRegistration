package com.brian.albers.userregistrationsystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Implements the ReadCommand interface. Class will create a FileReadCommand
 * object with a String that holds the file to read user information from. There
 * is a method which will take a supplied username from the user and return any
 * stored information in a supplied file path.
 * 
 * @see ReadCommand
 * @author Brian.Albers
 * @version 1.0
 */
public class FileReadCommand implements ReadCommand {
	private final static String LOG_CONFIG_FILE_PATH = "./log4j.properties";
	private static final String FRC_LOG_NAME = "fileReadCommandLog";
	private static Logger fileReadCommandLog = Logger.getLogger(FRC_LOG_NAME);
	
	static {
		PropertyConfigurator.configure(LOG_CONFIG_FILE_PATH);
	}
	/**
	 * String that will hold a file name provided by client
	 */
	private String fileToReadFrom;

	/**
	 * Constructor that will create the FileReadCommand object using a supplied file
	 * path.
	 * 
	 * @param fileToReadFrom
	 *            String that will hold a name of a file to read user information
	 *            from.
	 */
	public FileReadCommand(String fileToReadFrom) {
		fileReadCommandLog.trace(FRC_LOG_NAME+": Constructor making object");
		this.fileToReadFrom = fileToReadFrom;
	}

	/**
	 * This method takes a username as a String supplied by the client to check the
	 * file supplied by the client for any relevant information of a user i.e.
	 * username, password, role. If the username is found then the information is
	 * used to return an User object containing the information.
	 * 
	 * @return Found user information in a User object. If no information found
	 *         returns an User with default information.
	 * @see User
	 */
	@Override
	public User readUser(String username) {
		fileReadCommandLog.trace(FRC_LOG_NAME+": readUser is attempting to find user form a file");
		username = username.toLowerCase();
		String userInformation = null;
		User user = new User();
		try (BufferedReader in = new BufferedReader(new FileReader(new File(fileToReadFrom)))) {
			String line;
			while ((line = in.readLine()) != null) {
				if (line.contains(username)) {
					userInformation = line;
					break;
				}
			}

			if (userInformation != null) {
				String delims = ",";
				String[] parts = userInformation.split(delims);

				user.setUsername(parts[0]);
				user.setPassword(parts[1]);
				user.setRole(parts[2]);
			}
		} catch (FileNotFoundException e) {
			fileReadCommandLog.error(FRC_LOG_NAME+": file to read from was not found.");
			e.printStackTrace();
		} catch (IOException ex) {
			fileReadCommandLog.error(FRC_LOG_NAME+": error in the input steam of readUser.");
			ex.printStackTrace();
		}
		return user;
	}
}