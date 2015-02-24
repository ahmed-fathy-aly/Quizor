package web;

import android.test.AndroidTestCase;
import android.util.Log;
import utils.ServerUtils;
import web.serverBehaviours.AddStudentsToGroupBehavior;
import web.serverBehaviours.RegisterProfessorBehavior;
import web.serverBehaviours.RegisterStudentBehavior;
import web.serverRequests.AddStudentsToGroupRequest;
import web.serverRequests.RegisterProfessorRequest;
import web.serverRequests.RegisterStudentRequest;
import web.serverResponse.AddStudentsToGroupResponse;
import web.serverResponse.RegisterStudentResponse;
import web.serverResponse.ServerResponse;
import junit.framework.TestCase;

public class AddStudentsToGroupBehaviorTest extends AndroidTestCase
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
		 AddStudentsToGroupRequest request = new AddStudentsToGroupRequest(ServerUtils.getInstance().getDebugGroupId(), "qwerty", "", "", "", "", "1");
		 AddStudentsToGroupBehavior addStudents = new AddStudentsToGroupBehavior();
		 AddStudentsToGroupResponse response = (AddStudentsToGroupResponse) addStudents.handleRequest(request);
		 
		 assertEquals(ServerResponse.STATUS_SUCCESSFUL, response.getStatus());
		 assertNotNull(response.getMessage());
	}
	
	 public void testGetResponseString()
	 {
		 AddStudentsToGroupRequest request = new AddStudentsToGroupRequest(ServerUtils.getInstance().getDebugGroupId(), "qwerty", "", "", "", "", "1");
		 AddStudentsToGroupBehavior addStudents = new AddStudentsToGroupBehavior();
		 String responseString = addStudents.getResponseString(request);
		 
		 assertNotNull(responseString);
	 }

	public void testDecodeResponseSuccesful()
	{
		AddStudentsToGroupBehavior addStudents = new AddStudentsToGroupBehavior();
		String responseStr = "{ \"errors\":\"none\", \"message\":\"2 students added\"}";
		AddStudentsToGroupResponse response = addStudents.decodeResponseString(responseStr);

		assertEquals(ServerResponse.STATUS_SUCCESSFUL, response.getStatus());
		assertEquals("2 students added", response.getMessage());
	}

	public void testDecodeResponseError()
	{
		AddStudentsToGroupBehavior addStudents = new AddStudentsToGroupBehavior();
		String responseStr = "{ \"errors\":\"noob minion\"}";
		AddStudentsToGroupResponse response = addStudents.decodeResponseString(responseStr);

		assertEquals("noob minion", response.getStatus());
	}

}
