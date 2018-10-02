package com.brian.albers.userregistationsystem;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.brian.albers.userregistrationsystem.FileReadCommand;
import com.brian.albers.userregistrationsystem.User;

public class FileReadCommandTest {
	private final String FILE_TO_READ_USER_FROM = "C:\\JavaEclipseWorkspace\\UserRegistrationSystem\\src\\test\\resources/user.txt";
	
	private FileReadCommand frc;
	private File file;
	private final String USERNAME = "example", PASSWORD = "passwrd", ROLE = "A Test";
	private final String MIXED_CASE_USERNAME = "eXaMple";
	
	private User user, baseUser;
	
	@Before
	public void setUp() throws Exception {
		file = new File(FILE_TO_READ_USER_FROM);
		file.createNewFile();
		try (FileWriter fw = new FileWriter(file)){
			fw.append(USERNAME + "," + PASSWORD + "," + ROLE);
		}catch (IOException e) {
			e.printStackTrace();
		}
		frc = new FileReadCommand(FILE_TO_READ_USER_FROM);
		
		user = frc.readUser(USERNAME);
		baseUser = new User(USERNAME,PASSWORD,ROLE);
	}
	
	@After
	public void tearDown() {
		file.delete();
	}

	@Test
	public void testReadUserCapturesUserName() {
		String expected = USERNAME;
		String actual = user.getUsername();
		assertEquals(expected,actual);
	}
	
	@Test
	public void testReadUserCapturesPassword() {
		String expected = PASSWORD;
		String actual = user.getPassword();
		assertEquals(expected,actual);
	}
	
	@Test
	public void testReadUserCapturesRole() {
		String expected = ROLE;
		String actual = user.getRole();
		assertEquals(expected,actual);
	}
	
	@Test
	public void testReadUser_CaseDoesMatter() {
		String actual = user.getUsername();
		assertFalse(MIXED_CASE_USERNAME == actual);
	}
	
	@Test
	public void testReadUser_FindsUser_NoMatterCase() {
		User user = frc.readUser(MIXED_CASE_USERNAME);
		String expected = USERNAME;
		String actual = user.getUsername();
		assertEquals(expected,actual);
	}
	
	@Test
	public void testUserObject_IsNotSameObject_ReadFromFile() {
		assertFalse(baseUser == user);
	}
	
	@Test
	public void testUserObject_ContentEquals_UserCreatedFromFile( ) {
		assertTrue(baseUser.equals(user));
	}
}