package core;

import org.json.JSONObject;

import junit.framework.TestCase;

public class GroupInfoTest extends TestCase
{
	public void testParseFromJson()
	{
		try
		{
			String jsonString = " {    \"id\": 10,    \"name\": \"asdf34\",    \"description\": \"asdfasdf\", \"professor_info\": {\"name\": \"Ahmed\",    \"username\": \"alwa\", \"email\": \"alwa@eng.asu.edu.eg\"} }";
			JSONObject json = new JSONObject(jsonString);
			GroupInfo group = GroupInfo.parseFromJson(json);
			
			assertEquals("10", group.getWebId());
			assertEquals("asdf34", group.getGroupName());
			assertEquals("asdfasdf", group.getDescription());
			assertEquals("Ahmed", group.getProfessorInfo().getFullName());
			assertEquals("alwa", group.getProfessorInfo().getUserName());
			assertEquals("alwa@eng.asu.edu.eg", group.getProfessorInfo().getMail());
		} catch (Exception e)
		{
			fail();
		}
		
	}
}
