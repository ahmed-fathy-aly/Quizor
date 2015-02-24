package web;

import core.ProfessorInfo;
import android.test.AndroidTestCase;
import android.util.Log;
import utils.HTTPUtils;
import utils.ServerUtils;
import web.serverBehaviours.AddQuizBehavior;
import web.serverBehaviours.LoginBehavior;
import web.serverRequests.AddQuizRequest;
import web.serverResponse.AddQuizResponse;
import web.serverResponse.LoginResponse;
import web.serverResponse.ServerResponse;

public class AddQuizBehaviorTest extends AndroidTestCase
{
	private ServerUtils utils;

	protected void setUp() throws Exception
	{
		// reset the server
		utils = ServerUtils.getInstance();
		utils.resetServer();
		super.setUp();
	}

	public void testHandleRequest()
	{
		AddQuizRequest request = new AddQuizRequest(utils.getDebugGroupId(), "Quiz 2", 1000, 1454023713000l);
		AddQuizBehavior addQuiz = new AddQuizBehavior();
		AddQuizResponse response = (AddQuizResponse) addQuiz.handleRequest(request);
		
		assertEquals(ServerResponse.STATUS_SUCCESSFUL, response.getStatus());
	}

	public void testGetResponseString()
	{
		AddQuizRequest request = new AddQuizRequest("27", "Quiz 2", 1000, 1454023713000l);
		AddQuizBehavior addQuiz = new AddQuizBehavior();
		String responseStr = addQuiz.getResponseString(request);

		assertNotNull(responseStr);
	}

	public void testDecodeResponseStringSuccessful()
	{
		String responseStr = "{ \"errors\":\"none\", \"quiz_id\":\"13\"}";
		AddQuizBehavior addQuiz = new AddQuizBehavior();
		AddQuizResponse response = addQuiz.decodeResponseString(responseStr);

		assertEquals(ServerResponse.STATUS_SUCCESSFUL, response.getStatus());
		assertEquals("13", response.getQuizId());
	}

	public void testDecodeResponseStringError()
	{
		String responseStr = "{ \"errors\":\"web minion is a noob\"}";
		AddQuizBehavior addQuiz = new AddQuizBehavior();
		AddQuizResponse response = addQuiz.decodeResponseString(responseStr);

		assertEquals("web minion is a noob", response.getStatus());
	}

}
