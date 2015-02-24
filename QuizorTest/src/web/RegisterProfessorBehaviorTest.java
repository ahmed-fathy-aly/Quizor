package web;

import utils.ServerUtils;
import web.serverBehaviours.RegisterProfessorBehavior;
import web.serverRequests.RegisterProfessorRequest;
import web.serverResponse.RegisterProfessorResponse;
import web.serverResponse.ServerResponse;
import android.test.AndroidTestCase;
import android.util.Log;
import junit.framework.TestCase;

public class RegisterProfessorBehaviorTest extends AndroidTestCase
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
		// make a request
		RegisterProfessorBehavior register = new RegisterProfessorBehavior();
		RegisterProfessorRequest request = new RegisterProfessorRequest("user1", "pass", "pass", "zlatan", "something@eng.asu.edu.eg");

		// register
		RegisterProfessorResponse response = (RegisterProfessorResponse) register.handleRequest(request);
		assertNotSame(ServerResponse.STATUS_FAILED, response.getStatus());
	}
	
	public void testRegisterIncorrectly()
	{
		// make a request
		RegisterProfessorBehavior register = new RegisterProfessorBehavior();
		RegisterProfessorRequest request = new RegisterProfessorRequest("user1", "pass", "otherPass", "zlatan", "something@eng.asu.edu.eg");

		// register
		RegisterProfessorResponse response = (RegisterProfessorResponse) register.handleRequest(request);
		assertNotSame(ServerResponse.STATUS_FAILED, response.getStatus());
	}
	
	public void testGetResponseString()
	{
		// make a request
		RegisterProfessorBehavior register = new RegisterProfessorBehavior();
		RegisterProfessorRequest request = new RegisterProfessorRequest("user1", "pass", "pass", "zlatan", "something@eng.asu.edu.eg");
		String responseString = register.getResponseString(request);
		assertNotNull(responseString);
	}
	
	public void testDecodeResponseSuccesful()
	{
		RegisterProfessorBehavior register = new RegisterProfessorBehavior();
		String responseStr = "{ \"errors\":\"none\"}";
		RegisterProfessorResponse response = register.decodeResponseString(responseStr);
		
		assertEquals(ServerResponse.STATUS_SUCCESSFUL, response.getStatus());
	}
	
	public void testDecodeResponseError()
	{
		RegisterProfessorBehavior register = new RegisterProfessorBehavior();
		String responseStr = "{ \"errors\":\"noob minion\"}";
		RegisterProfessorResponse response = register.decodeResponseString(responseStr);
		
		assertEquals("noob minion", response.getStatus());
	}
	
}
