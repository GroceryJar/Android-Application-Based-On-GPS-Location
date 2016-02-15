package tabsAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.gwalior.connectivity.Music;
import com.gwalior.promote.MusicFragment;
import com.gwalior.promote.ProductDetailFragment;
import com.gwalior.promote.VideoFragment;

public class ProductAdapter extends FragmentPagerAdapter {
	private Music mediaPlayer;
	
	public ProductAdapter(FragmentManager supportFragmentManager) {
		super(supportFragmentManager);
	}

	public void setMediaPlayer(Music mediaPlayer)
	{
		this.mediaPlayer = mediaPlayer;
	}
	
	@Override
	public Fragment getItem(int index) {
		switch (index) {
		case 0:
			ProductDetailFragment pdf = new ProductDetailFragment();
			return pdf;
			
		case 1:
			VideoFragment vf = new VideoFragment();
			return vf;
			
		case 2:
			MusicFragment mf = new MusicFragment();
			mf.setMusicPlayer(mediaPlayer);
			return mf;
			//return new DashboardActivity();
		
		}

		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}

}
