package com.example.ccast.test;

import java.util.HashMap;

import user.UserFunctions;
import main.MainActivity;
import database.DatabaseHandler;
import android.test.AndroidTestCase;
import android.test.InstrumentationTestCase;

public class DatabaseHandlerTest extends InstrumentationTestCase {

	public void testConstructor()
	{
		DatabaseHandler dbh = new DatabaseHandler(getInstrumentation().getContext());
		assertNotNull(dbh);
	}
	
	public void testUserFunctionsConstructor()
	{
		UserFunctions uf = new UserFunctions();
		assertNotNull(uf);
	}
	
	public void testUserFunctionsIsUserLoggedIn()
	{
		UserFunctions uf = new UserFunctions();
		assertTrue(uf.isUserLoggedIn(getInstrumentation().getContext()));
	}
	
	public void testUserDetailsNotNull()
	{
		DatabaseHandler dbh = new DatabaseHandler(getInstrumentation().getContext());
		HashMap<String, String> userDets = dbh.getUserDetails();
		assertNotNull(userDets);
	}
	
	public void testUserDetailsUID()
	{
		DatabaseHandler dbh = new DatabaseHandler(getInstrumentation().getContext());
		
		HashMap<String, String> userDets = dbh.getUserDetails();
		assertEquals("5", userDets.get("uid"));
	}
}
