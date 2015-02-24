package web;

import utils.ServerUtils;
import web.serverBehaviours.GetStudentsInGroupBehavior;
import web.serverBehaviours.LoginBehavior;
import web.serverRequests.GetStudentsInGroupRequest;
import web.serverRequests.LoginRequest;
import web.serverRequests.ServerRequest;
import web.serverResponse.GetStudentsInGroupResponse;
import web.serverResponse.LoginResponse;
import web.serverResponse.ServerResponse;
import core.ProfessorInfo;
import core.StudentInfo;
import android.test.AndroidTestCase;
import android.util.Log;
import junit.framework.TestCase;

public class GetStudentsInGroupBehaviorTest extends AndroidTestCase
{

	protected void setUp() throws Exception
	{
		// reset the server
		ServerUtils utils = ServerUtils.getInstance();
		utils.resetServer();
		super.setUp();
	}

	public void testHandleRequest()
	{
		GetStudentsInGroupRequest request = new GetStudentsInGroupRequest(ServerUtils.getInstance()
				.getDebugGroupId());
		GetStudentsInGroupBehavior behavior = new GetStudentsInGroupBehavior();
		GetStudentsInGroupResponse response = (GetStudentsInGroupResponse) behavior
				.handleRequest(request);
		
		assertEquals(ServerResponse.STATUS_SUCCESSFUL, response.getStatus());
	}

	public void testGetResponseString()
	{
		GetStudentsInGroupRequest request = new GetStudentsInGroupRequest(ServerUtils.getInstance()
				.getDebugGroupId());
		GetStudentsInGroupBehavior behavior = new GetStudentsInGroupBehavior();

		String actual = behavior.getResponseString(request);
		Log.e("Game", "get students response " + actual);
		assertNotNull(actual);
	}

	public void testDecodeResponseStringSuccessful()
	{
		String responseStr = "{\"errors\":\"none\",\"students\":[{\"name\":\"eg\",\"email\":\"egor@eng.asu.edu.eg\",\"username\":\"egor\",\"department_name\":\"CSE\",\"year_number\":2017,\"section_number\":2}]}";
		GetStudentsInGroupBehavior behavior = new GetStudentsInGroupBehavior();
		GetStudentsInGroupResponse response = behavior.decodeResponseString(responseStr);
		StudentInfo studentInfo = response.getStudentInfos().get(0);

		assertEquals(ServerResponse.STATUS_SUCCESSFUL, response.getStatus());
		assertEquals("eg", studentInfo.getFullName());
		assertEquals("egor@eng.asu.edu.eg", studentInfo.getEmail());
		assertEquals("egor", studentInfo.getUserName());
		assertEquals("CSE", studentInfo.getDepartment());
		assertEquals("2017", studentInfo.getGraduationYear());
		assertEquals("2", studentInfo.getSection());
	}

	public void testDecodeResponseStringError()
	{
		String responseStr = "{ \"errors\":\"web minion is a noob\"}";
		GetStudentsInGroupBehavior behavior = new GetStudentsInGroupBehavior();
		GetStudentsInGroupResponse response = behavior.decodeResponseString(responseStr);

		assertEquals("web minion is a noob", response.getStatus());
	}

}
