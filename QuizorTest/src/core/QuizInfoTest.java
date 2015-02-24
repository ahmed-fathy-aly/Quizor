package core;

import org.json.JSONObject;

import android.test.AndroidTestCase;

public class QuizInfoTest extends AndroidTestCase
{

	public void testParseFromJson()
	{
		try
		{
			String jsonStr = "{\"id\":0,\"name\":\"myQ\",\"duration\":3600,\"unix_date\":1422574788000,\"professor_info\":{\"name\":\"Ahmed\",\"username\":\"alwa\",\"email\":\"alwa@eng.asu.edu.eg\"}}";
			JSONObject json = new JSONObject(jsonStr);
			QuizInfo quiz = QuizInfo.parseFromJson(json);

			assertEquals("0", quiz.getWebId());
			assertEquals("myQ", quiz.getQuizName());
			assertEquals(3600l, quiz.getDurationSeconds());
			assertEquals(1422574788000l, quiz.getStartDate());
			assertEquals("Ahmed", quiz.getProfessorInfo().getFullName());
			assertEquals("alwa", quiz.getProfessorInfo().getUserName());
			assertEquals("alwa@eng.asu.edu.eg", quiz.getProfessorInfo().getMail());
		} catch (Exception e)
		{
			fail();
		}
	}

}
