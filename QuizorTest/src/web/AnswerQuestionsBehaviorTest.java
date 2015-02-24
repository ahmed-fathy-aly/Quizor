package web;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import controller.QuizController;
import core.MCQ;
import core.MCQChoice;
import utils.ServerUtils;
import web.serverBehaviours.AnswerQuestionsBehavior;
import web.serverBehaviours.LoginBehavior;
import web.serverRequests.AnswerQuestionsRequest;
import web.serverResponse.AnswerQuestionsResponse;
import web.serverResponse.LoginResponse;
import web.serverResponse.ServerResponse;
import junit.framework.TestCase;

public class AnswerQuestionsBehaviorTest extends TestCase
{
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		// reset the server
		ServerUtils utils = ServerUtils.getInstance();
		utils.resetServerStudent();
	}

	public void testHandleRequest()
	{
		AnswerQuestionsRequest request = new AnswerQuestionsRequest(new ArrayList<MCQ>(),
				ServerUtils.getInstance().getDebugQuizId());
		AnswerQuestionsBehavior behavior = new AnswerQuestionsBehavior();
		AnswerQuestionsResponse response = (AnswerQuestionsResponse) behavior
				.handleRequest(request);

		assertTrue(ServerResponse.STATUS_FAILED != response.getStatus());
	}

	public void testGetResponseString()
	{
		AnswerQuestionsRequest request = new AnswerQuestionsRequest(new ArrayList<MCQ>(),
				ServerUtils.getInstance().getDebugQuizId());
		AnswerQuestionsBehavior behavior = new AnswerQuestionsBehavior();
		String responseStr = behavior.getResponseString(request);

		assertNotNull(responseStr);
		Log.e("Game", "answer questsions response string:" + responseStr);
	}

	public void testDecodeResponseStringCorrect()
	{
		String responseStr = "{\"errors\":\"none\",\"message\":\"You scored 1 point.\"}";
		AnswerQuestionsBehavior behavior = new AnswerQuestionsBehavior();
		AnswerQuestionsResponse response = behavior.decodeResponseString(responseStr);

		assertEquals(ServerResponse.STATUS_SUCCESSFUL, response.getStatus());
		assertEquals("You scored 1 point.", response.getMessage());
	}

	public void testDecodeResponseStringError()
	{
		String responseStr = "{ \"errors\":\"web minion is a noob\"}";
		AnswerQuestionsBehavior behavior = new AnswerQuestionsBehavior();
		AnswerQuestionsResponse response = behavior.decodeResponseString(responseStr);

		assertEquals("web minion is a noob", response.getStatus());
	}

	public void testGetAnswersJSON()
	{
		try
		{
			// make some questions
			MCQ mcq = new MCQ();
			mcq.setWebId("13");
			mcq.addChoice(new MCQChoice("a", false));
			mcq.addChoice(new MCQChoice("b", false));
			ArrayList<MCQ> questions = new ArrayList<>();
			questions.add(mcq);

			// mark a choice
			questions.get(0).getChoices().get(1).setChecked(true);

			// get the json
			JSONArray jsonArr = new AnswerQuestionsBehavior().makeJSONArray(questions);
			JSONObject jsonObject;
			jsonObject = jsonArr.getJSONObject(0);

			assertEquals("13", jsonObject.get("q_id"));
			assertEquals("b", jsonObject.get("answer"));

		} catch (JSONException e)
		{
			e.printStackTrace();
			fail();
		}

	}

}
