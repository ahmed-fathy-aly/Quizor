package ui.adapters;

import ui.fragments.LoginFragment;
import ui.fragments.RegisterProfessorFragment;
import ui.fragments.RegisterStudentFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginRegisterTabsAdapter.
 */
public class LoginRegisterTabsAdapter extends FragmentPagerAdapter
{

	/**
	 * Instantiates a new login register tabs adapter.
	 *
	 * @param fm the fm
	 */
	public LoginRegisterTabsAdapter(FragmentManager fm)
	{
		super(fm);
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.FragmentPagerAdapter#getItem(int)
	 */
	@Override
	public Fragment getItem(int idx)
	{
		switch (idx)
		{
		case 0:
			return new RegisterStudentFragment();
		case 1:
			return new LoginFragment();
		case 2:
			return new RegisterProfessorFragment();
		default:
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see android.support.v4.view.PagerAdapter#getCount()
	 */
	@Override
	public int getCount()
	{
		return 3;
	}

}
