package com.gwalior.connectivity;

import java.util.Timer;
import java.util.TimerTask;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.gwalior.promote.Services;

public class AsyncLocation extends AsyncTask<String,Void,String> {
	private Context context;
	private String uid;
	private GPSTracker gps;
	
	public AsyncLocation(Context context, String uid)
	{
		this.uid = uid;
		this.context = context;
	}
	
	public GPSTracker getGps() {
		return gps;
	}

	@Override
	protected String doInBackground(String... arg0) {
		gps = new GPSTracker(context);
		new Timer().schedule(new TimerTask(){
			
			@Override
			public void run() {
				gps.callLooper();
				boolean location = gps.canGetLocation();
				if(location == true && Services.loc != null)
				{
					Log.i("location","from timer");
					gps.getLocation();
					UserFunctions user = new UserFunctions();
					user.sendLocation(uid, gps.getLatitude(), gps.getLongitude());
				}
				else
				{
					this.cancel();
					Services.loc = null;
					Log.i("location","disabled1");
				}
				
			}}, 5000, 5000);
		return null;
	}
 
}
