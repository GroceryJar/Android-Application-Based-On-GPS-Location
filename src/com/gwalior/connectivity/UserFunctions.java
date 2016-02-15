package com.gwalior.connectivity;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import android.content.Context;

public class UserFunctions {
	
	private JSONParser jsonParser;
	
	public static String homeUrl = "http://192.168.43.91:1234/PromoteGwalior/";
	private String loginURL = "mobile.api";
	private String registerURL = "register.api";
	private String productURL = "product.api";
	private String productListURL = "productlist.api";
	private String specialListURL = "speciallist.api";
	private String locationURL = "location.api";
	private String nearbyURL = "nearby.api";
	
	private String login_tag = "login";
	private String register_tag = "register";
	private String product_tag = "product";
	private String product_list_tag = "productList";
	private String special_list_tag = "specialList";
	private String location_tag = "location";
	private String nearby_tag = "nearbyList";
	
	// constructor
	public UserFunctions(){
		jsonParser = new JSONParser();
	}
	
	/**
	 * function make Login Request
	 * @param email
	 * @param password
	 * */
	public JSONObject loginUser(String email, String password){
		// Building Parameters
		JSONObject json = null;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", login_tag));
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("pass", password));
		json = jsonParser.getJSONFromUrl(homeUrl+loginURL, params);
		// return json
		// Log.e("JSON", json.toString());*/
		return json;
	}
	
	/**
	 * function make Login Request
	 * @param name
	 * @param email
	 * @param password
	 * */
	public JSONObject registerUser(String fname,String lname , String email, String password , String mobile){
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", register_tag));
		params.add(new BasicNameValuePair("fname", fname));
		params.add(new BasicNameValuePair("lname", lname));
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("pass", password));
		params.add(new BasicNameValuePair("mobile", mobile));
		
		// getting JSON Object
		JSONObject json = jsonParser.getJSONFromUrl(homeUrl+registerURL, params);
		// return json
		return json;
	}
	
	/**
	 * Function get Login status
	 * */
	public boolean isUserLoggedIn(Context context){
		DatabaseHandler db = new DatabaseHandler(context);
		int count = db.getRowCount();
		if(count > 0){
			// user logged in
			return true;
		}
		return false;
	}
	
	/**
	 * Function to logout user
	 * Reset Database
	 * */
	public boolean logoutUser(Context context){
		DatabaseHandler db = new DatabaseHandler(context);
		db.resetTables();
		return true;
	}

	public JSONObject getProduct(long pid) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", product_tag));
		params.add(new BasicNameValuePair("pid", String.valueOf(pid)));
		JSONObject json = jsonParser.getJSONFromUrl(homeUrl+productURL, params);
		return json;
	}
	
	public JSONObject getProductList(String cat,int start, int end) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", product_list_tag));
		params.add(new BasicNameValuePair("cat", cat));
		params.add(new BasicNameValuePair("start", String.valueOf(start)));
		params.add(new BasicNameValuePair("end", String.valueOf(end)));
		JSONObject json = jsonParser.getJSONFromUrl(homeUrl+productListURL, params);
		return json;
	}
	
	public JSONObject getSpecialList(int type,int start, int end) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", special_list_tag));
		params.add(new BasicNameValuePair("type", String.valueOf(type)));
		params.add(new BasicNameValuePair("start", String.valueOf(start)));
		params.add(new BasicNameValuePair("end", String.valueOf(end)));
		JSONObject json = jsonParser.getJSONFromUrl(homeUrl+specialListURL, params);
		return json;
	}
	
	public void sendLocation(String uid,double latitude, double longitude) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", location_tag));
		params.add(new BasicNameValuePair("uid", uid));
		params.add(new BasicNameValuePair("lt", String.valueOf(latitude)));
		params.add(new BasicNameValuePair("lg", String.valueOf(longitude)));
		jsonParser.getJSONFromUrl(homeUrl+locationURL, params);
	}

	public JSONObject getNearByList(int start, int end, double latitude, double longitude) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", nearby_tag));
		params.add(new BasicNameValuePair("lt", String.valueOf(latitude)));
		params.add(new BasicNameValuePair("lg", String.valueOf(longitude)));
		params.add(new BasicNameValuePair("start", String.valueOf(start)));
		params.add(new BasicNameValuePair("end", String.valueOf(end)));
		JSONObject json = jsonParser.getJSONFromUrl(homeUrl+nearbyURL, params);
		return json;
	}
}
