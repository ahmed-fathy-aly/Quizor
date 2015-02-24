package web;

import utils.ServerUtils;
import web.serverBehaviours.GetQuizStatisticsBehavior;
import web.serverBehaviours.LoginBehavior;
import web.serverRequests.GetQuizStatisticsRequest;
import web.serverRequests.LoginRequest;
import web.serverRequests.ServerRequest;
import web.serverResponse.GetQuizStatisticsResponse;
import web.serverResponse.LoginResponse;
import web.serverResponse.ServerResponse;
import core.ProfessorInfo;
import core.SolutionStatistics;
import android.test.AndroidTestCase;
import android.util.Log;
import junit.framework.TestCase;

public class GetQuizStatisticsBehaviorTest extends AndroidTestCase
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
		GetQuizStatisticsRequest request = new GetQuizStatisticsRequest(ServerUtils.getInstance()
				.getDebugQuizId());
		GetQuizStatisticsBehavior behavior = new GetQuizStatisticsBehavior();
		GetQuizStatisticsResponse response = (GetQuizStatisticsResponse) behavior
				.handleRequest(request);

		assertEquals(ServerResponse.STATUS_SUCCESSFUL, response.getStatus());
	}

	public void testGetResponseString()
	{
		GetQuizStatisticsRequest request = new GetQuizStatisticsRequest(ServerUtils.getInstance()
				.getDebugQuizId());
		GetQuizStatisticsBehavior behavior = new GetQuizStatisticsBehavior();
		String actual = behavior.getResponseString(request);

		assertNotNull(actual);
	}

	public void testDecodeResponseStringSuccessful()
	{
		String responseStr = "{\"errors\":\"none\",\"solutions\":[{\"total_score\":1,\"student_username\":\"egor\",\"individual_questions_scores\":{\"your age?\":1,\"your name?\":0},\"late\":5}]}";
		GetQuizStatisticsBehavior behavior = new GetQuizStatisticsBehavior();
		GetQuizStatisticsResponse response = behavior.decodeResponseString(responseStr);
		SolutionStatistics sol = response.getSolutions().get(0);

		assertEquals(ServerResponse.STATUS_SUCCESSFUL, response.getStatus());
		assertEquals(1, response.getSolutions().size());
		assertEquals("egor", sol.getUserName());
		assertEquals("1", sol.getScore());
		assertEquals("5", sol.getMinutesLate());

	}

	public void testDecodeResponseStringError()
	{
		String responseStr = "{ \"errors\":\"web minion is a noob\"}";
		GetQuizStatisticsBehavior behavior = new GetQuizStatisticsBehavior();
		GetQuizStatisticsResponse response = behavior.decodeResponseString(responseStr);

		assertEquals("web minion is a noob", response.getStatus());
	}

}
