package core;

import org.json.JSONException;
import org.json.JSONObject;

import junit.framework.TestCase;

public class MCQChoiceTest extends TestCase
{
	public void testGetJSON()
	{

		try
		{
			MCQChoice choice = new MCQChoice("q1", true);
			JSONObject json = choice.getJSON();

			assertEquals("q1", json.get("name"));
			assertEquals(true, json.get("correct"));

		} catch (JSONException e)
		{
			fail();
		}

	}
}
