package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import android.util.Log;
import dbc.DBC;

// TODO: Auto-generated Javadoc
/**
 * The Class TimeUtils.
 */
public class TimeUtils
{

	/**
	 * Convert to unix time.
	 *
	 * @param day            required between 1 and 31
	 * @param month            required between 1 and 12
	 * @param year            required more than 0
	 * @param hour            required between 0 and 23
	 * @param minute            required between 0 and 59
	 * @param second            required between 0 and 59
	 * @return ensures larger than 0
	 */
	public static long convertToUnixTime(int day, int month, int year, int hour, int minute,
			int second)
	{
		Log.e("Game", "d" + day + "month" + month + "year" + year ); 
		//DBC.require(day >= 1 && day <= 31);
		//DBC.require(month >= 1 && month<= 12);
		//DBC.require(year >=0);
		//DBC.require(hour >=0 && hour <= 23);
		//DBC.require(minute >= 0 && minute <= 59);
		//DBC.require(second >= 0 && second <= 59);
		
		month--;
		GregorianCalendar c = new GregorianCalendar(year, month, day, hour, minute, second);
		c.setTimeZone(TimeZone.getTimeZone("GMT"));
		long result = c.getTimeInMillis();
		
		//DBC.ensure(result >= 0);
		return result;
	}

	/**
	 * Convert to readable date.
	 *
	 * @param unixTime required more than 0
	 * @return ensures a non empty string
	 */
	public static String convertToReadableDate(long unixTime)
	{
		//DBC.require(unixTime >= 0);
		
		// make the calendar
		GregorianCalendar c = new GregorianCalendar();
		c.setTimeZone(TimeZone.getTimeZone("GMT"));
		c.setTimeInMillis(unixTime);

		// make fields
		String year = "" + c.get(Calendar.YEAR);
		String month = "" + (c.get(Calendar.MONTH) + 1);
		String day = "" + c.get(Calendar.DAY_OF_MONTH);
		String hour = "" + c.get(Calendar.HOUR_OF_DAY);
		if (hour.length() < 2)
			hour = "0" + hour;
		String minute = "" + c.get(Calendar.MINUTE);
		String result = day + "/" + month + "/" + year + "  " + hour + ":" + minute;
		
		//DBC.ensure(result.length() > 0);
		return result;
	}

	/**
	 * Convert to readable duration.
	 *
	 * @param duration requires duration more than 0
	 * @return ensures a non empty string
	 */
	public static String convertToReadableDuration(int duration)
	{
		//DBC.require(duration > 0);
		
		// compute hours, minutes
		int minutes = duration / 60;
		int hours = minutes / 60;
		minutes %= 60;

		// hours string
		String answer = "";
		if (hours > 0)
			answer += hours + " hour";
		if (hours > 1)
			answer += "s";

		// minutes string
		if (minutes > 0 && answer.length() > 0)
			answer += ", ";
		if (minutes > 0)
			answer += minutes + " minute";
		if (minutes > 1)
			answer += "s";

		//DBC.ensure(answer.length() > 0);
		return answer;
	}

}
