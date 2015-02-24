package web;

import core.GroupInfo;
import core.QuizInfo;
import utils.HTTPUtils;
import utils.ServerUtils;
import web.serverBehaviours.ViewGroupsBehavior;
import web.serverBehaviours.ViewQuizzesBehavior;
import web.serverRequests.ViewGroupsRequest;
import web.serverRequests.ViewQuizzesRequest;
import web.serverResponse.ServerResponse;
import web.serverResponse.ViewGroupsResponse;
import web.serverResponse.ViewQuizzesResponse;
import android.test.AndroidTestCase;
import android.util.Log;
import junit.framework.TestCase;

public class ViewQuizzesBehaviorTest extends AndroidTestCase
{

	private ServerUtils utils;

	@Override
		protected void setUp() throws Exception
	{
		super.setUp();
		// reset the server
		utils = ServerUtils.getInstance();
		utils.resetServer();
	}

	 public void testHandleRequest()
	 {
	 ViewQuizzesRequest request = new ViewQuizzesRequest(utils.getDebugGroupId());
	 ViewQuizzesBehavior viewQuizzes= new ViewQuizzesBehavior();
	 ViewQuizzesResponse response = (ViewQuizzesResponse)
	 viewQuizzes.handleRequest(request);
	
	
	 assertEquals(ServerResponse.STATUS_SUCCESSFUL, response.getStatus());
	 }

	public void testGetResponseString()
	{
		ViewQuizzesRequest request = new ViewQuizzesRequest(utils.getDebugGroupId());
		ViewQuizzesBehavior viewQuizzes = new ViewQuizzesBehavior();
		String responseString = viewQuizzes.getResponseString(request);
		assertNotNull(responseString);
	}

	public void testParseResponseStringSuccessful()
	{
		String responseString = "{\"errors\":\"none\",\"quizzes\":[{\"id\":0,\"name\":\"myQ\",\"duration\":3600,\"unix_date\":1422574788000,\"professor_info\":{\"name\":\"Ahmed\",\"username\":\"alwa\",\"email\":\"alwa@eng.asu.edu.eg\"}},{\"id\":1,\"name\":\"myQ\",\"duration\":3600,\"unix_date\":1422574788000,\"professor_info\":{\"name\":\"Ahmed\",\"username\":\"alwa\",\"email\":\"alwa@eng.asu.edu.eg\"}}]}";
		ViewQuizzesBehavior viewQuizzes = new ViewQuizzesBehavior();
		ViewQuizzesResponse response = viewQuizzes.decodeResponseString(responseString);

		assertEquals(ServerResponse.STATUS_SUCCESSFUL, response.getStatus());
		assertEquals(2, response.getQuizInfos().size());
		for (int i = 0; i < 2; i++)
		{
			QuizInfo quiz = response.getQuizInfos().get(i);
			assertEquals(i + "", quiz.getWebId());
			assertEquals("myQ", quiz.getQuizName());
			assertEquals(3600l, quiz.getDurationSeconds());
			assertEquals(1422574788000l, quiz.getStartDate());
			assertEquals("Ahmed", quiz.getProfessorInfo().getFullName());
			assertEquals("alwa", quiz.getProfessorInfo().getUserName());
			assertEquals("alwa@eng.asu.edu.eg", quiz.getProfessorInfo().getMail());
		}

	}

	public void testParseResponseStringError()
	{
		String responseString = "{\"errors\":\"noob minion\"}";
		ViewQuizzesBehavior viewQuizzes = new ViewQuizzesBehavior();
		ViewQuizzesResponse response = viewQuizzes.decodeResponseString(responseString);

		assertEquals("noob minion", response.getStatus());
	}

}
