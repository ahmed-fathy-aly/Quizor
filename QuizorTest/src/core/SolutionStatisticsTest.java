package core;

import org.json.JSONException;
import org.json.JSONObject;

import junit.framework.TestCase;

public class SolutionStatisticsTest extends TestCase
{
	public void testParseFromJSON()
	{
		try
		{
			String jsonStr = "{\"total_score\":1,\"student_username\":\"egor\",\"individual_questions_scores\":{\"your age?\":1,\"your name?\":0},\"late\":5}";
			JSONObject json = new JSONObject(jsonStr);
			SolutionStatistics sol = SolutionStatistics.parseFromJSON(json);

			assertEquals("egor", sol.getUserName());
			assertEquals("1", sol.getScore());
			assertEquals("5", sol.getMinutesLate());

		} catch (JSONException e)
		{
			e.printStackTrace();
			fail();
		}
	}
}
