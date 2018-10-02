package com.brian.albers.userregistrationsystem.exceptions;

/**
 * Custom exception that is called when it has been detected that the user
 * passed an empty String
 * 
 * @author Brian.Albers
 * @version 1.0
 */
public class EmptyStringException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor takes a String and passes it to the Exception Class constructor
	 * 
	 * @param message
	 *            String parameter passed from throwing Class.
	 */
	public EmptyStringException(String message) {
		super(message);
	}
}