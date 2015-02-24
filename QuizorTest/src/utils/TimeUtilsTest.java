package utils;
import android.test.AndroidTestCase;
import junit.framework.TestCase;


public class TimeUtilsTest extends AndroidTestCase
{
	public void testConvertToUnixTime()
	{
		long actual = TimeUtils.convertToUnixTime(28, 1, 2016, 23, 28, 33);
		long expected = 1454023713000l;
		
		assertEquals(expected, actual);
	}
	
	public void testConvertToReadableDate()
	{
		long unixTime = 1454023713000l;
		String str = TimeUtils.convertToReadableDate(unixTime);
		
		assertEquals("28/1/2016  23:28", str);
	}
	
	public void testConvertToReadableDuration()
	{
		int duration = 103*60;
		String actual = TimeUtils.convertToReadableDuration(duration);
		String expected = "1 hour, 43 minutes";
		
		assertEquals(expected, actual);
	}
}
