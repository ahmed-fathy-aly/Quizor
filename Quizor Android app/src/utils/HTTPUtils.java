package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.CookieHandler;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import ui.activities.LoginRegisterActivity;
import web.serverBehaviours.AddGroupBehaviour;
import web.serverBehaviours.AddQuizBehavior;
import web.serverBehaviours.LoginBehavior;
import web.serverBehaviours.RegisterProfessorBehavior;
import web.serverRequests.AddGroupRequest;
import web.serverRequests.AddQuizRequest;
import web.serverRequests.LoginRequest;
import web.serverRequests.RegisterProfessorRequest;
import web.serverResponse.AddGroupResponse;
import web.serverResponse.AddQuizResponse;
import web.serverResponse.LoginResponse;
import web.serverResponse.RegisterProfessorResponse;
import web.serverResponse.ServerResponse;
import dbc.DBC;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.webkit.CookieManager;

// TODO: Auto-generated Javadoc
/**
 * The Class HTTPUtils.
 */
public class HTTPUtils
{
	/* constans */
	/** The Constant baseURL. */
	public static final String baseURL = "http://quizor-cse-3-2016.herokuapp.com/";

	/* fields */
	/** The instance. */
	static HTTPUtils instance;
	
	/** The cookie manager. */
	private java.net.CookieManager cookieManager;

	/**
	 * Instantiates a new HTTP utils.
	 */
	private HTTPUtils()
	{
		cookieManager = new java.net.CookieManager();
		CookieHandler.setDefault(cookieManager);
	}

	/**
	 * Gets the single instance of HTTPUtils.
	 *
	 * @return the single instance of the http utils
	 */
	public static HTTPUtils getInstance()
	{
		if (instance == null)
			instance = new HTTPUtils();
		return instance;
	}

	/**
	 * Posts keys and values pairs requires number of keys = number of values
	 * ensures a non null string is returned.
	 *
	 * @param urlString            base url
	 * @param keys            list of keys
	 * @param values            list of values
	 * @return response
	 */
	public String postRequest(String urlString, String[] keys, String[] values)
	{
		//DBC.require(keys.length == values.length);
		//DBC.require(urlString != null);

		String result;
		try
		{
			// make the url
			String newUrlString = urlString;
			URL url = new URL(newUrlString);

			// make the connection
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");

			// add the data
			if (keys.length > 0)
			{
				connection.setDoOutput(true);
				OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
				out.write(encodeParams(keys, values));
				out.close();
			}

			// get response stream
			StringBuilder strb = new StringBuilder();
			BufferedReader in;
			if (connection.getResponseCode() >= 200 && connection.getResponseCode() <= 299)
				in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			else
				in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));

			// extract into string
			while (true)
			{
				String str = in.readLine();
				if (str != null)
					strb.append(str);
				else
					break;
			}
			in.close();
			result = strb.toString();
		} catch (Exception e)
		{
			result = null;
		}
		
		//DBC.ensure(result != null);
		return result;

	}

	/**
	 * gets data from the server requires keys length = values length.
	 *
	 * @param urlString            base url
	 * @param keys            list keys of the params
	 * @param values            lists of values of the params
	 * @return result of the query
	 */
	public String getRequest(String urlString, String[] keys, String[] values)
	{
		//DBC.require(keys.length == values.length);
		//DBC.require(urlString != null);

		String result;
		try
		{
			// make the url
			String newUrlString = urlString;
			if (keys.length > 0)
				newUrlString += "?" + encodeParams(keys, values);
			URL url = new URL(newUrlString);

			// make the connection
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			// get response stream
			StringBuilder strb = new StringBuilder();
			BufferedReader in;

			if (connection.getResponseCode() >= 200 && connection.getResponseCode() <= 299)
				in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			else
				in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));

			// extract into string
			while (true)
			{
				String str = in.readLine();
				if (str != null)
					strb.append(str);
				else
					break;
			}
			in.close();
			result = strb.toString();
		} catch (Exception e)
		{
			result = null;
		}

		return result;
	}

	/**
	 * sends a delete request to the server requires keys length = values length.
	 *
	 * @param urlString the url string
	 * @param keys the keys
	 * @param values the values
	 * @return a non null string
	 */
	public String deleteRequest(String urlString, String[] keys, String[] values)
	{
		//DBC.require(keys.length == values.length);
		//DBC.require(urlString != null);

		String result;
		try
		{
			// make the url
			String newUrlString = urlString;
			URL url = new URL(newUrlString);

			// make the connection
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("DELETE");

			// add the data
			if (keys.length > 0)
			{
				connection.setDoOutput(true);
				OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
				out.write(encodeParams(keys, values));
				out.close();
			}

			// get response stream
			StringBuilder strb = new StringBuilder();
			BufferedReader in;

			if (connection.getResponseCode() >= 200 && connection.getResponseCode() <= 299)
				in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			else
				in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));

			// extract into string
			while (true)
			{
				String str = in.readLine();
				if (str != null)
					strb.append(str);
				else
					break;
			}
			in.close();
			result = strb.toString();
		} catch (Exception e)
		{
			result = null;
		}

		//DBC.ensure(result != null);
		return result;
	}

	/**
	 * Decodes keys and values to UTF-8 encoding requires number of keys =
	 * number of values.
	 *
	 * @param keys            list of keys
	 * @param values            lisy of values
	 * @return encoded string
	 */
	public String encodeParams(String[] keys, String[] values)
	{
		//DBC.require(keys.length == values.length);

		StringBuilder strb = new StringBuilder();

		for (int i = 0; i < keys.length; i++)
			try
			{
				String key = "" + keys[i];
				String value = "" + values[i];
				value = URLEncoder.encode(value, "UTF-8");
				if (strb.length() > 0)
					strb.append("&");
				strb.append(key + "=" + value);
			} catch (Exception e)
			{
			}
		String result = strb.toString();

		return strb.toString();
	}

	/**
	 * Checks if is connected.
	 *
	 * @param context the context
	 * @return true if there's an internet connection available
	 */
	public static boolean isConnected(Context context)
	{
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = cm.getActiveNetworkInfo();
		return networkInfo != null && networkInfo.isConnected();
	}

}
