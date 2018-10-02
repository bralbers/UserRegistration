package com.brian.albers.userregistationsystem;

import com.brian.albers.userregistrationsystem.FileWriteCommand;
import com.brian.albers.userregistrationsystem.User;
import com.brian.albers.userregistrationsystem.exceptions.NullException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileWriteCommandTest {

	private final String FILE_TO_SAVE_USER_TO = "C:\\JavaEclipseWorkspace\\UserRegistrationSystem\\src\\test\\resources/user.txt";
	
	private FileWriteCommand fwc;
	private User mockUser;
	private File file;
	private final String USERNAME = "example", PASSWORD = "passwrd", ROLE = "A Test";
	
	
	private User user = new User();
	
	@Before
	public void setUp() throws Exception {
		mockUser = mock(User.class);
		file = new File(FILE_TO_SAVE_USER_TO);
		file.createNewFile();
		fwc = new FileWriteCommand(FILE_TO_SAVE_USER_TO);
		user.setUsername(USERNAME);
		user.setPassword(PASSWORD);
		user.setRole(ROLE);
	}
	
	@After
	public void tearDown() {
		file.delete();
	}

	@Test
	public void testWriteUser_WritesToFile_WhenCalled() throws NullException {
		long beforeFileSize = file.length();
		fwc.writeUser(mockUser);
		long afterFileSize = file.length();
		assertNotEquals(beforeFileSize,afterFileSize);
	}

	@Test
	public void testWriteUser_WritesCorrectInformation_ToFile_WhenCalled() throws NullException {
		fwc.writeUser(user);
		String expected = "example,passwrd,A Test";
		String actual = null;
		
		try (BufferedReader in = new BufferedReader(new FileReader(new File(FILE_TO_SAVE_USER_TO)))){
			String line;
			while ((line = in.readLine()) != null) {
				if (line.contains(USERNAME)) {
					actual = line;
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(expected,actual);
	}
	
	@Test(expected = NullException.class)
	public void testFileWriteCommand_GivenNonExistingFile() throws NullException {
		String nonexisting = null;
		fwc = new FileWriteCommand(nonexisting);
	}
	
	@Test(expected = NullException.class)
	public void testWriteUser_NullException_IsThrown_ForNullUser() throws NullException {
		User nullUser = null;
		fwc.writeUser(nullUser);
	}
}