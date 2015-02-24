package core;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import junit.framework.TestCase;

public class MCQTest extends TestCase
{
	public void testGetJSON()
	{

		try
		{
			MCQ mcq = new MCQ();
			mcq.setBody("q1");
			mcq.getChoices().add(new MCQChoice("choice1", true));
			mcq.getChoices().add(new MCQChoice("choice2", false));
			JSONObject json = mcq.getJSON();

			assertEquals("q1", json.getString("body"));
			JSONArray arr = json.getJSONArray("answers_attributes");
			for (int i = 0; i < 2; i++)
			{
				JSONObject choice = arr.getJSONObject(i);
				assertEquals("choice" + (i + 1), choice.get("name"));
				assertEquals(i == 0, choice.getBoolean("correct"));
			}

		} catch (JSONException e)
		{
			fail();
		}
	}

	public void testParseFromJSON()
	{
		try
		{
			String jsonStr = "{\"id\":13,\"body\":\"your name?\",\"choices\":[\"0\",\"1\"]}";
			MCQ mcq = MCQ.parseFromJSON(new JSONObject(jsonStr));
			
			assertEquals("13", mcq.getWebId());
			assertEquals("your name?", mcq.getBody());
			for (int i = 0; i < 2; i++) 
				assertEquals("" + i, mcq.getChoices().get(i).getBody());
				
		} catch (JSONException e)
		{
			fail();
			e.printStackTrace();
		}
	}
}
