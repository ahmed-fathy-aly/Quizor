package utils;

import android.test.AndroidTestCase;
import junit.framework.TestCase;

public class PreferencesUtilsTest extends AndroidTestCase
{
	public void testGetUserInfo()
	{
		PreferencesUtils.savedUserInfo(getContext(), "ahmed", "123");
		assertEquals("ahmed", PreferencesUtils.getUserName(getContext()));
		assertEquals("123", PreferencesUtils.getPassword(getContext()));
	}
	
	public void testClearUserInfo()
	{
		PreferencesUtils.savedUserInfo(getContext(), "ahmed", "123");
		PreferencesUtils.clearUserInfo(getContext());
		
		assertEquals(PreferencesUtils.NOT_FOUND, PreferencesUtils.getUserName(getContext()));
		assertEquals(PreferencesUtils.NOT_FOUND, PreferencesUtils.getPassword(getContext()));
		
	}
}
