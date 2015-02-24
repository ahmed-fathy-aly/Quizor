package utils;

import dbc.DBC;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v4.app.FragmentActivity;

// TODO: Auto-generated Javadoc
/**
 * The Class PreferencesUtils.
 */
public class PreferencesUtils
{
	/* constants */
	/** The Constant PREFERENCES_NAME. */
	public  static final String PREFERENCES_NAME = "com.team_egor.quizor";
	
	/** The Constant TAG_USERNAME. */
	public static final String TAG_USERNAME = "userName";
	
	/** The Constant NOT_FOUND. */
	public  static final String NOT_FOUND = "not found";
	
	/** The Constant TAG_PASSWORD. */
	public  static final String TAG_PASSWORD = "userPassword";

	/**
	 * Gets the user name.
	 *
	 * @param context the context
	 * @return not found if it doesn't have a saved username
	 */
	public static String getUserName(Context context)
	{
		SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
		if (pref.contains(TAG_USERNAME))
			return pref.getString(TAG_USERNAME, NOT_FOUND);
		else
			return NOT_FOUND;
	}
	
	/**
	 * Gets the password.
	 *
	 * @param context the context
	 * @return not found if it doesn't have a saved username
	 */
	public static String getPassword(Context context)
	{
		SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
		if (pref.contains(TAG_PASSWORD))
			return pref.getString(TAG_PASSWORD, NOT_FOUND);
		else
			return NOT_FOUND;
	}
	
	/**
	 * saves the user name and password
	 * ensures the username and password returned are those given.
	 *
	 * @param context the context
	 * @param userName the user name
	 * @param password the password
	 */
	public static void savedUserInfo(Context context, String userName, String password)
	{
		SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
		Editor  editor = pref.edit();
		editor.putString(TAG_USERNAME, userName);
		editor.putString(TAG_PASSWORD, password);
		editor.apply();
		
		//DBC.ensure(getUserName(context).equals(userName));
		//DBC.ensure(getPassword(context).equals(password));
		
	}

	
	/**
	 * clears user name and password if they are saved.
	 *
	 * @param context the context
	 */
	public static void clearUserInfo(Context context)
	{
		SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
		Editor  editor = pref.edit();
		if (pref.contains(TAG_USERNAME))
			editor.remove(TAG_USERNAME);
		if (pref.contains(TAG_PASSWORD))
			editor.remove(TAG_PASSWORD);
		
		editor.apply();
		
		//DBC.ensure(getUserName(context).equals(NOT_FOUND));
		//DBC.ensure(getPassword(context).equals(NOT_FOUND));		
	}
	
}
