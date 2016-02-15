package com.gwalior.promote;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoFragment extends Fragment  {
	static VideoView videoView=null;
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    if (videoView.isPlaying()) outState.putInt("pos", videoView.getCurrentPosition());
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View rootView = inflater.inflate(R.layout.vedio_player, container, false);
		videoView = (VideoView) rootView.findViewById(R.id.videoView1);
		videoView.setMediaController(new MediaController(videoView.getContext()));
		videoView.setVideoURI(Uri.parse(getActivity().getIntent().getExtras().getString("videoUrl")));
		int pos = 0;
	    if (savedInstanceState != null) {
	        videoView.seekTo(savedInstanceState.getInt("pos"));
	    }
		return rootView;

	}
	
	
}
