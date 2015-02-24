package utils;

import org.json.JSONArray;
import org.json.JSONObject;

import web.serverResponse.ServerResponse;
import android.test.AndroidTestCase;
import android.util.Log;
import junit.framework.TestCase;

public class HTTPUtilsTest extends AndroidTestCase
{
	private HTTPUtils utils;

	@Override
	protected void setUp() throws Exception
	{
		utils = HTTPUtils.getInstance();
	}

	public void testEncodeParams()
	{
		String[] keys =
		{ "name", "age" };
		String[] values =
		{ "ahmed fathy", "21" };

		String actual = utils.encodeParams(keys, values);
		String expected = "name=ahmed+fathy&age=21";

		assertEquals(expected, actual);
	}

	public void testPost()
	{
		String url = "http://services.hanselandpetal.com/restful.php";
		String[] keys = new String[]
		{ "name", "age" };
		String[] values = new String[]
		{ "ahmed fathy", "21" };

		String actual = utils.postRequest(url, keys, values);
		String expected = "POST REQUESTname = ahmed fathyage = 21";
		assertEquals(expected, actual);
	}

	public void testPostJSON()
	{
		String url = "http://services.hanselandpetal.com/restful.php";
		
	}
	public void testGet()
	{
		String url = "http://services.hanselandpetal.com/restful.php";
		String[] keys = new String[]
		{ "name", "age" };
		String[] values = new String[]
		{ "ahmed fathy", "21" };

		String actual = utils.getRequest(url, keys, values);
		String expected = "GET REQUESTname = ahmed fathyage = 21";
		assertEquals(expected, actual);
	}

	public void testGetJSON()
	{
		String url = "http://services.hanselandpetal.com/feeds/flowers.json";
		HTTPUtils utils = HTTPUtils.getInstance();
		String responseStr = utils.getRequest(url, new String[]
		{}, new String[]
		{});

		try
		{
			JSONArray arr = new JSONArray(responseStr);
			for (int i = 0; i < arr.length(); i++)
			{
				JSONObject object = arr.getJSONObject(i);
				String category = object.getString("category");
				assertNotNull(category);
			}
		} catch (Exception e)
		{
			fail();
		}

	}

	public void testIsConnected()
	{
		try
		{
			utils.isConnected(getContext());
		} catch (Exception e)
		{
			fail();
		}
	}



}