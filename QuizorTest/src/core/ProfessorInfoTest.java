package core;

import org.json.JSONException;
import org.json.JSONObject;

import junit.framework.TestCase;

public class ProfessorInfoTest extends TestCase
{
	public void testDecodeFromJson()
	{
		try
		{
			String jsonStr = "{\"name\": \"Ahmed\",  \"username\": \"alwa\",  \"email\": \"alwa@eng.asu.edu.eg\"}";
			JSONObject json = new JSONObject(jsonStr);
			ProfessorInfo professor = ProfessorInfo.praseFromJson(json);
			
			assertEquals("Ahmed", professor.getFullName());
			assertEquals("alwa", professor.getUserName());
			assertEquals("alwa@eng.asu.edu.eg", professor.getMail());
			assertEquals(ProfessorInfo.WEB_ID_NOT_SET, professor.getWebId());
		} catch (JSONException e)
		{
			fail();
		}
	}
}
