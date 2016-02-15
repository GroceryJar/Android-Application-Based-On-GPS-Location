package com.gwalior.promote;

import org.json.JSONObject;
import android.content.Intent;
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

public class Login extends Fragment {
	EditText lemail;
	EditText lpass;
	Button loginButton;
	
	TextView loginErrorMsg;
	
	// JSON Response node names
	private static String KEY_SUCCESS = "success";
	private static String KEY_ERROR = "error";
	private static String KEY_ERROR_MSG = "error_msg";
	private static String KEY_UID = "uid";
	private static String KEY_FNAME = "fname";
	private static String KEY_LNAME = "lname";
	private static String KEY_EMAIL = "email";
	private static String KEY_DATE = "date";
	private static String KEY_MOBILE = "mobile";

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.login, container, false);
		lemail = (EditText) rootView.findViewById(R.id.email);
		lpass = (EditText) rootView.findViewById(R.id.pass);
		loginButton = (Button) rootView.findViewById(R.id.btnLogin);
		loginErrorMsg = (TextView) rootView.findViewById(R.id.login_error);
		
		loginButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String email = lemail.getText().toString();
				String pass = lpass.getText().toString();
				UserFunctions userFunction = new UserFunctions();
				JSONObject json = userFunction.loginUser(email, pass);

				// check for login response
				try {
					if (json.getString(KEY_SUCCESS) != null) {
						loginErrorMsg.setText("");
						String res = json.getString(KEY_SUCCESS);
						if (Integer.parseInt(res) == 1) {
							// user successfully logged in
							// Store user details in SQLite Database
							DatabaseHandler db = new DatabaseHandler(
									getActivity());
							JSONObject json_user = json.getJSONObject("user");
							// Clear all previous data in database
							userFunction.logoutUser(getActivity());
							db.addUser(json_user.getString(KEY_FNAME),
									json_user.getString(KEY_LNAME),
									json_user.getString(KEY_EMAIL),
									json_user.getString(KEY_UID),
									json_user.getString(KEY_DATE),
									json_user.getString(KEY_MOBILE));
							
							// Launch Dashboard Screen
							Intent i;
							i = new Intent(getActivity(), Services.class);
							i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(i);
							getActivity().finish();
							// Close Login Screen
						} else {
							// Error in login
							// btnLinkToRegister.setText("Incorrect username/password");
							loginErrorMsg.setText("Incorrect username/password");
							Toast.makeText(getActivity(), "fail",Toast.LENGTH_SHORT).show();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		return rootView;
	}
}
