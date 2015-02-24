package web;

import android.test.AndroidTestCase;
import android.util.Log;
import utils.ServerUtils;
import web.serverBehaviours.RegisterProfessorBehavior;
import web.serverBehaviours.RegisterStudentBehavior;
import web.serverRequests.RegisterProfessorRequest;
import web.serverRequests.RegisterStudentRequest;
import web.serverResponse.RegisterStudentResponse;
import web.serverResponse.ServerResponse;
import junit.framework.TestCase;

public class RegisterStudentBehaviorTest extends AndroidTestCase
{
	protected void setUp() throws Exception
	{
		// reset the server
		ServerUtils utils = ServerUtils.getInstance();
		utils.resetServer();
		super.setUp();
	}

	public void testRegister()
	{
		RegisterStudentRequest request = new RegisterStudentRequest("student2", "12345", "12345",
				"student2@eng.asu.edu.eg", "s2", "CSE", "2016", "1");
		RegisterStudentBehavior register = new RegisterStudentBehavior();
		RegisterStudentResponse response = (RegisterStudentResponse) register.handleRequest(request);
		
		assertEquals(ServerResponse.STATUS_SUCCESSFUL, response.getStatus());
	}
	public void testGetResponseString()
	{
		// make a request
		RegisterStudentRequest request = new RegisterStudentRequest("student3", "12345", "12345",
				"student3@eng.asu.edu.eg", "st", "CSE", "2016", "1");
		RegisterStudentBehavior register = new RegisterStudentBehavior();
		String responseString = register.getResponseString(request);
		assertNotNull(responseString);
		Log.e("Game", "response string" + responseString);
	}

	public void testDecodeResponseSuccesful()
	{
		RegisterStudentBehavior register = new RegisterStudentBehavior();
		String responseStr = "{ \"errors\":\"none\"}";
		RegisterStudentResponse response = register.decodeResponseString(responseStr);

		assertEquals(ServerResponse.STATUS_SUCCESSFUL, response.getStatus());
	}

	public void testDecodeResponseError()
	{
		RegisterStudentBehavior register = new RegisterStudentBehavior();
		String responseStr = "{ \"errors\":\"noob minion\"}";
		RegisterStudentResponse response = register.decodeResponseString(responseStr);

		assertEquals("noob minion", response.getStatus());
	}

}
