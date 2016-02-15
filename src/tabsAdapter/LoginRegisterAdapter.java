package tabsAdapter;

import com.gwalior.promote.Login;
import com.gwalior.promote.Register;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class LoginRegisterAdapter extends FragmentPagerAdapter  {

	public LoginRegisterAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int index) {
		switch (index) {
		case 0:
			return new Login();
			
		case 1:
			return new Register();
		
		}
		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 2;
	}

}
