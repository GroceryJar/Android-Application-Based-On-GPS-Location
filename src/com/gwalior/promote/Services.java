package com.gwalior.promote;

import java.util.HashMap;
import tabsAdapter.ServicesAdapter;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.gwalior.connectivity.AsyncLocation;
import com.gwalior.connectivity.DatabaseHandler;
import com.gwalior.connectivity.UserFunctions;

@SuppressLint("NewApi")
public class Services extends FragmentActivity implements ActionBar.TabListener {
	
	private ViewPager viewPager;
	private ServicesAdapter mAdapter;
	private ActionBar actionBar;
	public static AsyncLocation loc = null;
	
	// Tab titles
	private String[] tabs = { "Categories", "Featured" , "NearByOffers" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Initilization
		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		mAdapter = new ServicesAdapter(getSupportFragmentManager());

		viewPager.setAdapter(mAdapter);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);		
		
		// Adding Tabs
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
		}

		/**
		 * on swiping the viewpager make respective tab selected
		 * */
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
			
		});
		
		/*
		 * to get location regularly remove the comment
		 */
//		if(loc == null)
//		{
//			DatabaseHandler db = new DatabaseHandler(Services.this);
//			HashMap<String, String> user = db.getUserDetails();
//			loc = new AsyncLocation(getApplicationContext(), user.get("uid"));
//			loc.execute();
//		}
		
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// on tab selected
		// show respected fragment view
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}
	
	@Override  
	public void onBackPressed() {
	    	super.onBackPressed();   
	    // Do extra stuff here
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.logged, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	   try{
	    switch (item.getItemId()) {
	    case R.id.menuLogout:            
	    	UserFunctions userFunctions = new UserFunctions();
	    	Services.loc = null;
	    	if(userFunctions.isUserLoggedIn(getApplicationContext())){
		    	userFunctions.logoutUser(getApplicationContext());
		    }
		    Intent login = new Intent(getApplicationContext(), MainActivity.class);
		    login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		    startActivity(login);
		    finish();
	        return true;        
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	   }catch(Exception e){
	      e.printStackTrace();
	   }
	return false;
	}
}
