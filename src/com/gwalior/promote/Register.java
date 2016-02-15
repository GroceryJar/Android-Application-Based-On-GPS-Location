package com.gwalior.promote;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gwalior.connectivity.DatabaseHandler;
import com.gwalior.connectivity.UserFunctions;

public class Register extends Fragment {

	Button btnRegister;
	EditText inputEmail;
	EditText inputPassword;
	EditText inputFirstName;
	EditText inputLastName;
	EditText inputMobile;
	TextView registerErrorMsg;

	// JSON Response node names
	private static String KEY_SUCCESS = "success";
	private static String KEY_ERROR = "error";
	private static String KEY_ERROR_MSG = "error_msg";
	private static String KEY_UID = "uid";
	private static String KEY_FNAME = "fname";
	private static String KEY_LNAME = "lname";
	private static String KEY_EMAIL = "email";
	private static String KEY_MOBILE = "mobile";
	private static String KEY_DATE = "date";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.register, container, false);
		inputFirstName = (EditText) rootView.findViewById(R.id.reg_fname);
		inputLastName = (EditText) rootView.findViewById(R.id.reg_lname);
		inputEmail = (EditText) rootView.findViewById(R.id.reg_email);
		inputPassword = (EditText) rootView.findViewById(R.id.reg_password);
		inputMobile = (EditText) rootView.findViewById(R.id.reg_mob);
		btnRegister = (Button) rootView.findViewById(R.id.btnRegister);
		registerErrorMsg = (TextView) rootView.findViewById(R.id.reg_error);

		// Register Button Click event
		btnRegister.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String fname = inputFirstName.getText().toString();
				String lname = inputLastName.getText().toString();
				String email = inputEmail.getText().toString();
				String password = inputPassword.getText().toString();
				String mobile = inputMobile.getText().toString();
				UserFunctions userFunction = new UserFunctions();
				JSONObject json = userFunction.registerUser(fname, lname,
						email, password, mobile);

				// check for login response
				try {
					if(json != null)
					if (json.getString(KEY_SUCCESS) != null) {
						registerErrorMsg.setText("");
						String res = json.getString(KEY_SUCCESS);
						if (Integer.parseInt(res) == 1) {
							// user successfully registred
							// Store user details in SQLite Database
							DatabaseHandler db = new DatabaseHandler(
									getActivity());
							JSONObject json_user = json.getJSONObject("user");

							String uid = json_user.getString(KEY_UID);
							// Clear all previous data in database
							userFunction.logoutUser(getActivity());
							db.addUser(json_user.getString(KEY_FNAME),
									json_user.getString(KEY_LNAME),
									json_user.getString(KEY_EMAIL),
									uid,
									json_user.getString(KEY_DATE),
									json_user.getString(KEY_MOBILE));
							// Launch Dashboard Screen
//							Intent i;
//							i = new Intent(getActivity(), MainActivity.class);
//							startActivity(i);

							// Close Registration Screen
							Toast.makeText(getActivity(), "Success",
									Toast.LENGTH_SHORT).show();
						} else {
							// Error in registration
							registerErrorMsg
									.setText("Error occured in registration");
							Toast.makeText(getActivity(), "Fail",
									Toast.LENGTH_SHORT).show();
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});

		// Link to Login Screen

		return rootView;
	}

}
