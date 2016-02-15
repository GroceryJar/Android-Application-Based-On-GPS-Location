
package com.gwalior.promote;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gwalior.connectivity.UserFunctions;

public class LogoutFragment extends Fragment {
	UserFunctions userFunctions;
	Button btnLogout;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
    	View rootView = inflater.inflate(R.layout.logout, container, false);
    	btnLogout = (Button) rootView.findViewById(R.id.btnLogout);
    	
    	/**
         * Dashboard Screen for the application
         * */        
        // Check login status in database
        userFunctions = new UserFunctions();
        if(userFunctions.isUserLoggedIn(getActivity())){
        	
        	
        	btnLogout.setOnClickListener(new View.OnClickListener() {
    			
    			public void onClick(View arg0) {
    				// TODO Auto-generated method stub
    				userFunctions.logoutUser(getActivity().getApplicationContext());
    				Services.loc = null;
    				Intent login = new Intent(getActivity().getApplicationContext(), MainActivity.class);
    	        	login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    	        	startActivity(login);
    	        	// Closing dashboard screen
    	        	//finish();
    			}
    		});
        	
        }else{
        	// user is not logged in show login screen
        	Intent login = new Intent(getActivity().getApplicationContext(), MainActivity.class);
        	Services.loc = null;
        	login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        	startActivity(login);
        	// Closing dashboard screen
        	//finish();
        }
        
        
        return rootView;
        
    }
}