package web;

import utils.ServerUtils;
import web.serverBehaviours.GetQuizBehavior;
import web.serverBehaviours.LoginBehavior;
import web.serverRequests.GetQuizRequest;
import web.serverRequests.LoginRequest;
import web.serverRequests.ServerRequest;
import web.serverResponse.GetQuizResponse;
import web.serverResponse.LoginResponse;
import web.serverResponse.ServerResponse;
import core.MCQ;
import core.ProfessorInfo;
import android.test.AndroidTestCase;
import android.util.Log;
import junit.framework.TestCase;

public class GetQuizBehaviorTest extends TestCase
{
	protected void setUp() throws Exception
	{
		// reset the server
		ServerUtils utils = ServerUtils.getInstance();
		utils.resetServerStudent();
		super.setUp();
	}

	// public void testHandleRequest()
	// {
	// LoginRequest request = new LoginRequest("debug", "debug", "0");
	// LoginBehavior login = new LoginBehavior();
	// LoginResponse response = (LoginResponse) login.handleRequest(request);
	//
	// assertEquals(ServerResponse.STATUS_SUCCESSFUL, response.getStatus());
	// }
	//
	 public void testGetResponseString()
	 {
	 GetQuizRequest request = new GetQuizRequest(ServerUtils.getInstance().getDebugQuizId());
	GetQuizBehavior getQuiz = new GetQuizBehavior();
	String responseString = getQuiz.getResponseString(request);
	
	 Log.e("Game", "get quiz response " + responseString);
	 }
	
	public void testDecodeResponseStringSuccessful()
	{
		String responseStr = "{\"errors\":\"none\",\"quiz\":{\"id\":1,\"duration\":60,\"questions\":[{\"id\":0,\"body\":\"your name?0\",\"choices\":[\"0\",\"1\"]},{\"id\":1,\"body\":\"your name?1\",\"choices\":[\"0\",\"1\"]}]}}";
		GetQuizBehavior getQuiz = new GetQuizBehavior();
		GetQuizResponse response = getQuiz.decodeResponseString(responseStr);

		assertEquals(ServerResponse.STATUS_SUCCESSFUL, response.getStatus());
		assertEquals(60, response.getDuration());
		assertEquals(2, response.getQuestions().size());
		for (int i = 0; i < 2; i++)
		{
			MCQ mcq = response.getQuestions().get(i);
			assertEquals("" + i, mcq.getWebId());
			assertEquals("your name?" + i, mcq.getBody());
			for (int j = 0; j < 2; j++)
				assertEquals("" + j, mcq.getChoices().get(j).getBody());
		}

	}

	public void testDecodeResponseStringError()
	{
		String responseStr = "{ \"errors\":\"web minion is a noob\"}";
		GetQuizBehavior getQuiz = new GetQuizBehavior();
		GetQuizResponse response = getQuiz.decodeResponseString(responseStr);

		assertEquals("web minion is a noob", response.getStatus());
	}
}
