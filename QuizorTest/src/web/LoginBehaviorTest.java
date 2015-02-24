package web;

import utils.ServerUtils;
import web.serverBehaviours.LoginBehavior;
import web.serverRequests.LoginRequest;
import web.serverRequests.ServerRequest;
import web.serverResponse.LoginResponse;
import web.serverResponse.ServerResponse;
import core.ProfessorInfo;
import android.test.AndroidTestCase;
import android.util.Log;
import junit.framework.TestCase;

public class LoginBehaviorTest extends AndroidTestCase
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
		LoginRequest request = new LoginRequest("debug", "debug", "0");
		LoginBehavior login = new LoginBehavior();
		LoginResponse response = (LoginResponse) login.handleRequest(request);

		assertEquals(ServerResponse.STATUS_SUCCESSFUL, response.getStatus());
	}

	public void testGetResponseString()
	{
		LoginRequest request = new LoginRequest("debug", "debug", "0");
		LoginBehavior login = new LoginBehavior();

		String actual = login.getResponseString(request);
		assertNotNull(actual);
	}

	public void testDecodeResponseStringSuccessful()
	{
		String responseStr = "{ \"errors\":\"none\", type:\"Professor\"}";
		LoginBehavior login = new LoginBehavior();
		LoginResponse response = login.decodeResponseString(responseStr);

		assertEquals(ServerResponse.STATUS_SUCCESSFUL, response.getStatus());
		assertEquals(LoginResponse.UserType.PROFESSOR, response.getUserType());
	}

	public void testDecodeResponseStringError()
	{
		String responseStr = "{ \"errors\":\"web minion is a noob\"}";
		LoginBehavior login = new LoginBehavior();
		LoginResponse response = login.decodeResponseString(responseStr);

		assertEquals("web minion is a noob", response.getStatus());
	}

}
