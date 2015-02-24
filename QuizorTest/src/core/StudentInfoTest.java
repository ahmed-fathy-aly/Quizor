package core;

import org.json.JSONException;
import org.json.JSONObject;

import junit.framework.TestCase;

public class StudentInfoTest extends TestCase
{

	public void testParseFromJSON()
	{
		try
		{
			String jsonString = "{\"name\":\"eg\",\"email\":\"egor@eng.asu.edu.eg\",\"username\":\"egor\",\"department_name\":\"CSE\",\"year_number\":2017,\"section_number\":2}";
			JSONObject json = new JSONObject(jsonString);
			StudentInfo studentInfo = StudentInfo.parseFromJSON(json);
			
			assertEquals("eg", studentInfo.getFullName());
			assertEquals("egor@eng.asu.edu.eg", studentInfo.getEmail());
			assertEquals("egor", studentInfo.getUserName());
			assertEquals("CSE", studentInfo.getDepartment());
			assertEquals("2017", studentInfo.getGraduationYear());
			assertEquals("2", studentInfo.getSection());
		} catch (JSONException e)
		{
			e.printStackTrace();
			fail();
		}
	}


}
