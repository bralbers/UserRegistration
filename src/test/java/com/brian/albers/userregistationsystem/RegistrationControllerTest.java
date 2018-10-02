package com.brian.albers.userregistationsystem;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.brian.albers.userregistrationsystem.ReadCommand;
import com.brian.albers.userregistrationsystem.RegistrationController;
import com.brian.albers.userregistrationsystem.User;
import com.brian.albers.userregistrationsystem.UserFactory;
import com.brian.albers.userregistrationsystem.WriteCommand;
import com.brian.albers.userregistrationsystem.exceptions.*;

public class RegistrationControllerTest {

	private RegistrationController rc;
	private UserFactory uf;
	private WriteCommand wc;
	private ReadCommand rdc;

	private final String USERNAME = "example", PASSWORD = "passwrd", ROLE = "test";

	@Before
	public void setup() throws NullException {
		uf = mock(UserFactory.class);
		wc = mock(WriteCommand.class);
		rdc = mock(ReadCommand.class);
		rc = new RegistrationController(uf, wc, rdc);
	}

	@Test
	public void testRegistrationController_IsNotNull() {
		assertNotNull(rc);
	}

	@Test
	public void testRegisterNewUser_CallsCreateUser_OnUserFactory() throws NullException, EmptyStringException {
		rc.registerNewUser(USERNAME, PASSWORD, ROLE);
		verify(uf, times(1)).createUser(USERNAME, PASSWORD, ROLE);
	}

	@Test
	public void testRegisterNewUser_CallsWriteUser_OnWriteCommand_WithAnyUser()
			throws NullException, EmptyStringException {
		rc.registerNewUser(USERNAME, PASSWORD, ROLE);
		verify(wc, times(1)).writeUser(any(User.class));
	}

	@Test
	public void testRegisterNewUser_CallsWriteUser_OnWriteCommand_WithUserReturnedFromUserFactory()
			throws NullException, EmptyStringException {
		User mockUser = mock(User.class);
		when(uf.createUser(USERNAME, PASSWORD, ROLE)).thenReturn(mockUser);
		rc.registerNewUser(USERNAME, PASSWORD, ROLE);
		verify(wc, times(1)).writeUser(mockUser);
	}

	@Test
	public void testRegisterNewUser_CallsReadUser_OnReadCommand() throws NullException, EmptyStringException {
		rc.registerNewUser(USERNAME, PASSWORD, ROLE);
		verify(rdc, times(1)).readUser(USERNAME);
	}

	@Test(expected = NullException.class)
	public void testReadCommand_NullException_IsThrownForNullReadCommand() throws NullException {
		rdc = null;
		rc = new RegistrationController(uf, wc, rdc);
	}

	@Test(expected = NullException.class)
	public void testReadCommand_NullException_IsThrownForNullWriteCommand() throws NullException {
		wc = null;
		rc = new RegistrationController(uf, wc, rdc);
	}

	@Test(expected = NullException.class)
	public void testReadCommand_NullException_IsThrownForNullUserFactroy() throws NullException {
		uf = null;
		rc = new RegistrationController(uf, wc, rdc);
	}

	@Test(expected = NullException.class)
	public void testRegisterNewUser_NullException_IsThrownForNullRole() throws NullException, EmptyStringException {
		String role = null;
		rc.registerNewUser(USERNAME, PASSWORD, role);
	}

	@Test(expected = NullException.class)
	public void testRegisterNewUser_NullException_IsThrownForNullPassword() throws NullException, EmptyStringException {
		String password = null;
		rc.registerNewUser(USERNAME, password, ROLE);
	}

	@Test(expected = NullException.class)
	public void testRegisterNewUser_NullException_IsThrownForNullUsername() throws NullException, EmptyStringException {
		String username = null;
		rc.registerNewUser(username, PASSWORD, ROLE);
	}

	@Test(expected = EmptyStringException.class)
	public void testRegisterNewUser_EmptyStringException_IsThrownForEmptyStringRole()
			throws NullException, EmptyStringException {
		String role = "";
		rc.registerNewUser(USERNAME, PASSWORD, role);
	}

	@Test(expected = EmptyStringException.class)
	public void testRegisterNewUser_EmptyStringException_IsThrownForEmptyStringPassword()
			throws NullException, EmptyStringException {
		String password = "";
		rc.registerNewUser(USERNAME, password, ROLE);
	}

	@Test(expected = EmptyStringException.class)
	public void testRegisterNewUser_EmptyStringException_IsThrownForEmptyStringUsername()
			throws NullException, EmptyStringException {
		String username = "";
		rc.registerNewUser(username, PASSWORD, ROLE);
	}
}