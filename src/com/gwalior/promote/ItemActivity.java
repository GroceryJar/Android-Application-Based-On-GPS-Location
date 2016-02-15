package com.gwalior.promote;

import org.json.JSONException;
import org.json.JSONObject;
import tabsAdapter.ProductAdapter;
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
import android.view.MenuInflater;
import android.view.MenuItem;

import com.gwalior.connectivity.Music;
import com.gwalior.connectivity.UserFunctions;

@SuppressLint("NewApi")
public class ItemActivity extends FragmentActivity implements ActionBar.TabListener {

	private ViewPager viewPager;
	private ProductAdapter mAdapter;
	private ActionBar actionBar;
	public static Music mediaPlayer = new Music();
	long pid;
	
	// Tab titles
	private String[] tabs = { "Detail", "Vedio", "Audio" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item);
		
		Intent i = getIntent();
		Bundle b = i.getExtras();
		pid = Long.parseLong(b.getString("pid"));
		
		UserFunctions userFunction = new UserFunctions();
		JSONObject product = userFunction.getProduct(pid);
		
		// Initilization
		viewPager = (ViewPager) findViewById(R.id.productDetail);
		actionBar = getActionBar();
		mAdapter = new ProductAdapter(getSupportFragmentManager());
		
		try {
			i.putExtra("musicUrl", UserFunctions.homeUrl+"audio/"+product.getString("a"));
			i.putExtra("videoUrl", UserFunctions.homeUrl+"video/"+product.getString("v"));
			i.putExtra("imageUrl", UserFunctions.homeUrl+"pimage/"+product.getString("img"));
			i.putExtra("detail", product.getString("detail"));
			mAdapter.setMediaPlayer(mediaPlayer);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
				if(mediaPlayer.getMediaPlayer()!=null)
				{
					mediaPlayer.stopMusic();
				}
				if(VideoFragment.videoView!=null)
					VideoFragment.videoView.pause();
				
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}

		});
	}
	
	@Override
	public void onBackPressed() {
		if(mediaPlayer.getMediaPlayer()!=null)
		{
			mediaPlayer.stopMusic();
		}
		if(VideoFragment.videoView!=null)
			VideoFragment.videoView.pause();
		super.onBackPressed();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.logged, menu);
		return true;
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		viewPager.setCurrentItem(tab.getPosition());
		if(mediaPlayer.getMediaPlayer()!=null)
		{
			mediaPlayer.stopMusic();
		}
		if(VideoFragment.videoView!=null)
			VideoFragment.videoView.pause();
	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

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
		    login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
