package com.example.ccast.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import main.FriendsFragment;
import main.LoginActivity;
import json.JSONParser;
import android.test.AndroidTestCase;
import android.util.Log;

public class JSONParserTest extends AndroidTestCase {


	public void testContructor()
	{
		JSONParser jp = new JSONParser();
		assertNotNull(jp);
	}
	
	public void testJSONLoginNotNull()
	{
		JSONParser jp = new JSONParser();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("email", "louis"));
        params.add(new BasicNameValuePair("password", "louis"));

        Log.d("request!", "starting");
        // getting product details by making HTTP request
        JSONObject json = jp.makeHttpRequest(
               "http://10.0.2.2/login.php", "POST", params);
        assertNotNull(json);

	}
	
	public void testJSONLoginSucess()
	{
		JSONParser jp = new JSONParser();
		int success = 0;
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("email", "louis"));
        params.add(new BasicNameValuePair("password", "louis"));

        Log.d("request!", "starting");
        // getting product details by making HTTP request
        JSONObject json = jp.makeHttpRequest(
               "http://10.0.2.2/login.php", "POST", params);
        try{
        	success = json.getInt(LoginActivity.TAG_SUCCESS);
        } catch(JSONException e) {
        	e.printStackTrace();
        }
        assertEquals(1, success);
	}
	
	public void testJSONArray1()
	{
		String jArr = "[{\"uid\":\"4\",\"first_name\":\"bob\",\"last_name\":\"bob\",\"mood\":\"fierce\",\"picture_url\":null},{\"uid\":\"6\",\"first_name\":\"stanley\",\"last_name\":\"stanley\",\"mood\":null,\"picture_url\":null}]";
		JSONArray ja = null;
		try {
			ja = new JSONArray(jArr);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(ja);
	}
	
	public void testJSONArray2()
	{
		String jArr = "[]";
		JSONArray ja = null;
		try {
			ja = new JSONArray(jArr);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(ja);
	}
	
	public void testJSONArrayGetFriends()
	{
		JSONParser jp = new JSONParser();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("uid", "5"));
		JSONArray ja = null;
		ja = jp.makeHttpRequestForFriendsArray("http://10.0.2.2/get_friends.php", "POST", params);
		assertNotNull(ja);
	}
	
	public void testJSONArrayGetFriendsLength()
	{
		JSONParser jp = new JSONParser();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("uid", "5"));
		JSONArray ja = null;
		ja = jp.makeHttpRequestForFriendsArray("http://10.0.2.2/get_friends.php", "POST", params);
		assertTrue(ja.length() > 0);
	}
	
	public void testJSONArrayGetFriendsExactLength()
	{
		JSONParser jp = new JSONParser();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("uid", "5"));
		JSONArray ja = null;
		ja = jp.makeHttpRequestForFriendsArray("http://10.0.2.2/get_friends.php", "POST", params);
		assertEquals(3, ja.length());
	}
}
