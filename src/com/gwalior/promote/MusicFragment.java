package com.gwalior.promote;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;

import com.gwalior.connectivity.Music;

@SuppressLint("ValidFragment")
public class MusicFragment extends Fragment {
	private static Music mp;
	
	public void setMusicPlayer(Music mediaPlayer) {
		mp=mediaPlayer;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.music_player, container, false);
		ImageButton mpPlay = (ImageButton)rootView.findViewById(R.id.mp_play);
		ImageButton mpStop = (ImageButton)rootView.findViewById(R.id.mp_stop);
		SeekBar progress = (SeekBar) rootView.findViewById(R.id.seekBar1);
		
		mp.setUrl(getActivity().getIntent().getExtras().getString("musicUrl"));
		mp.setProgress(progress);
		mp.setContext(getActivity());
		progress.setEnabled(false);
		
		mpPlay.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				mp.startMusic();
			}
		});
		mpStop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				mp.stopMusic();
			}
		});
		return rootView;

	}
}
