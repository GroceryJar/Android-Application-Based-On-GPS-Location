package com.gwalior.connectivity;

import java.util.Timer;
import java.util.TimerTask;
import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.IBinder;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

@SuppressLint("ShowToast")
public class Music extends Service {

	String url;
	MediaPlayer mediaPlayer=null;
	SeekBar progress;
	Context context;
	int status;
	boolean running;

	
	public void setContext(Context context) {
		this.context = context;
	}

	public void setProgress(SeekBar progress) {
		this.progress = progress;
	}

	public void setUrl(String url) {
		this.url = url;	
	}
	
	public void stopMusic(){
		if(mediaPlayer!=null)
			mediaPlayer.release();
		mediaPlayer = null;
		running = false;
		progress.setProgress(0);
	}
	
	public MediaPlayer getMediaPlayer(){
		return mediaPlayer;
	}
	
	public void startMusic() {
		if(mediaPlayer != null)
			return;
		try {
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setDataSource(url);
			mediaPlayer.prepareAsync();
			// You can show progress dialog here untill it prepared to play
			mediaPlayer.setOnPreparedListener(new OnPreparedListener() {
				@Override
				public void onPrepared(final MediaPlayer mp) {
					progress.setMax(mp.getDuration());
					mp.start();
					running=true;
					status=1;
					new Timer().schedule(new TimerTask(){

						@Override
						public void run() {
							if(running == true)
							progress.setProgress(mp.getCurrentPosition());
							else
								this.cancel();
							
						}}, 1000,1000);
				}
			});
			mediaPlayer.setOnErrorListener(new OnErrorListener() {
				@Override
				public boolean onError(MediaPlayer mp, int what, int extra) {
					// dissmiss progress bar here. It will come here when
					// MediaPlayer
					// is not able to play file. You can show error message to
					// user
					return false;
				}
			});
			mediaPlayer.setOnBufferingUpdateListener(new OnBufferingUpdateListener() {
				
				@Override
				public void onBufferingUpdate(MediaPlayer arg0, int percent) {
					if(percent != 100)
						Toast.makeText(context, "Buffering " + percent + " %", 500).show();
					else if(status==1)
					{
						Toast.makeText(context, "Buffering " + percent + " %", 500).show();
						status = 0;
					}	
				}
			});
			mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
				
				@Override
				public void onCompletion(MediaPlayer mp) {
					mp.release();
					running = false;
					Log.i("Media Player","released");
					mediaPlayer = null;
				}
			});
			progress.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
				
				@Override
				public void onStopTrackingTouch(SeekBar arg0) {
					// TODO Auto-generated method stub
					Log.i("Promote", "1");
				}
				
				@Override
				public void onStartTrackingTouch(SeekBar arg0) {
					// TODO Auto-generated method stub
					Log.i("Promote", "2->"+arg0.getMax());
					Log.i("Promote", "2->"+arg0.getProgress());
					Log.i("Promote", "2->"+arg0.getSecondaryProgress());
					Log.i("Promote", "2->"+arg0.getSecondaryProgress());
				}
				
				@Override
				public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
					// TODO Auto-generated method stub
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
