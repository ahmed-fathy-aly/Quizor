package ui.activities;

import ui.adapters.LoginRegisterTabsAdapter;
import web.gcm.GCMUtils;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.team_egor.quizor.R;

import controller.UserController;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginRegisterActivity.
 */
@SuppressWarnings("deprecation")
public class LoginRegisterActivity extends FragmentActivity implements ActionBar.TabListener
{
	/* fields */
	/** The controller. */
	public UserController controller;

	/** The view pager. */
	ViewPager viewPager;

	/** The tabs adapter. */
	LoginRegisterTabsAdapter tabsAdapter;

	/** The action bar. */
	ActionBar actionBar;

	/** The tabs titles. */
	String[] tabsTitles = new String[]
	{ "Student Register", "Log in", "Prof. Register" };

	/** The icon ids. */
	int[] iconIds = new int[]
	{ R.drawable.ic_register, R.drawable.ic_login, R.drawable.ic_register };

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_register);

		// initialize
		controller = new UserController(this);
		viewPager = (ViewPager) findViewById(R.id.pager);
		tabsAdapter = new LoginRegisterTabsAdapter(getSupportFragmentManager());
		actionBar = getActionBar();

		// set values
		viewPager.setAdapter(tabsAdapter);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// add tabs
		for (int i = 0; i < tabsTitles.length; i++)
		{
			Tab tab = actionBar.newTab();
			tab.setText(tabsTitles[i]);
			tab.setTabListener(this);
			tab.setIcon(iconIds[i]);
			actionBar.addTab(tab);
		}
		viewPager.setCurrentItem(1);
		actionBar.setSelectedNavigationItem(1);

		// change tabs when swipe
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
		{

			@Override
			public void onPageSelected(int position)
			{
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2)
			{
			}

			@Override
			public void onPageScrollStateChanged(int arg0)
			{
			}
		});

		// register for google play messaginf
		registerForGCM();
	}

	private void registerForGCM()
	{
		try
		{
			GCMUtils utils = new GCMUtils();
			utils.registerForNotifications(this);
		} catch (Exception e)
		{
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login_register, menu);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		return super.onOptionsItemSelected(item);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.support.v4.app.FragmentActivity#onSaveInstanceState(android.os
	 * .Bundle)
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.app.ActionBar.TabListener#onTabSelected(android.app.ActionBar
	 * .Tab, android.app.FragmentTransaction)
	 */
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft)
	{
		viewPager.setCurrentItem(tab.getPosition());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.app.ActionBar.TabListener#onTabUnselected(android.app.ActionBar
	 * .Tab, android.app.FragmentTransaction)
	 */
	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft)
	{

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.app.ActionBar.TabListener#onTabReselected(android.app.ActionBar
	 * .Tab, android.app.FragmentTransaction)
	 */
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft)
	{

	}

	/**
	 * sets the fields and goes to login tab
	 */
	public void fillLoginInfo(String userName, String password)
	{
		viewPager.setCurrentItem(1);
		EditText userNameEditText = (EditText) findViewById(R.id.editTextUserName);
		EditText passwordEditText = (EditText) findViewById(R.id.editTextPassword);
		userNameEditText.setText(userName);
		passwordEditText.setText(password);

	}
}
