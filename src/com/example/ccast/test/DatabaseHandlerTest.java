package com.example.ccast.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import json.JSONParser;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import user.UserFunctions;
import main.MainActivity;
import database.DatabaseHandler;
import android.test.AndroidTestCase;
import android.test.InstrumentationTestCase;

public class DatabaseHandlerTest extends AndroidTestCase {

	public void testConstructor()
	{
		DatabaseHandler dbh = new DatabaseHandler(getContext());
		assertNotNull(dbh);
	}
	
	public void testUserFunctionsConstructor()
	{
		UserFunctions uf = new UserFunctions();
		assertNotNull(uf);
	}
	
	public void testUserFunctionsIsUserLoggedIn()
	{
		List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("email", "louis"));
        params.add(new BasicNameValuePair("password", "louis"));
        JSONObject json = JSONParser.getInstance().makeHttpRequest(
        		"http://10.0.2.2/login.php", "POST", params);
        JSONObject json_user;
		try {
			json_user = json.getJSONObject("user");
	        DatabaseHandler db = new DatabaseHandler(getContext());
	        db.addUser(json_user.getString("uid"), json_user.getString("first_name"), json_user.getString("username"), json_user.getString("email"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserFunctions uf = new UserFunctions();
		assertTrue(uf.isUserLoggedIn(getContext()));
	}
	
	public void testUserDetailsNotNull()
	{
		DatabaseHandler dbh = new DatabaseHandler(getContext());
		HashMap<String, String> userDets = dbh.getUserDetails();
		assertNotNull(userDets);
	}
	
	public void testUserDetailsUID()
	{
		DatabaseHandler dbh = new DatabaseHandler(getContext());
		
		HashMap<String, String> userDets = dbh.getUserDetails();
		assertEquals("5", userDets.get("uid"));
	}
}
