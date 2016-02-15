package tabsAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.gwalior.promote.Category;
import com.gwalior.promote.FeatureFragment;
import com.gwalior.promote.LogoutFragment;
import com.gwalior.promote.NearByFragment;
import com.gwalior.promote.Register;

public class ServicesAdapter extends FragmentPagerAdapter {

	public ServicesAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public Fragment getItem(int index) {
		switch (index) {
		case 0:
			
			return new Category();
			
		case 1:
			
			return new FeatureFragment();
			
		case 2:
			
			return new NearByFragment();
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
