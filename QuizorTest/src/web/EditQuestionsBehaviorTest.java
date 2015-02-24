package web;

import java.util.ArrayList;

import core.MCQ;
import core.MCQChoice;
import android.test.AndroidTestCase;
import android.util.Log;
import utils.ServerUtils;
import web.serverBehaviours.EditQuestionsBehavior;
import web.serverBehaviours.RegisterProfessorBehavior;
import web.serverBehaviours.RegisterStudentBehavior;
import web.serverRequests.EditQuestionsRequest;
import web.serverRequests.RegisterProfessorRequest;
import web.serverRequests.RegisterStudentRequest;
import web.serverResponse.EditQuestionsResponse;
import web.serverResponse.RegisterStudentResponse;
import web.serverResponse.ServerResponse;
import junit.framework.TestCase;

public class EditQuestionsBehaviorTest extends AndroidTestCase
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
		// make some questions
		MCQ mcq1 = new MCQ();
		mcq1.setBody("q1");
		mcq1.getChoices().add(new MCQChoice("choice1", true));
		mcq1.getChoices().add(new MCQChoice("choice2", false));
		
		MCQ mcq2 = new MCQ();
		mcq2.setBody("q2");
		mcq2.getChoices().add(new MCQChoice("choice1", true));
		mcq2.getChoices().add(new MCQChoice("choice2", false));
		
		ArrayList<MCQ> questions = new ArrayList<>();
		questions.add(mcq1);
		questions.add(mcq2);
		
		// make request
		EditQuestionsRequest request = new EditQuestionsRequest(ServerUtils.getInstance().getDebugQuizId(), questions);
		EditQuestionsBehavior editQuestions = new EditQuestionsBehavior();
		EditQuestionsResponse response = (EditQuestionsResponse) editQuestions.handleRequest(request);
		
		assertEquals(ServerResponse.STATUS_SUCCESSFUL, response.getStatus());
	}
	
	public void testGetResponseString()
	{
		// make some questions
		MCQ mcq1 = new MCQ();
		mcq1.setBody("q1");
		mcq1.getChoices().add(new MCQChoice("choice1", true));
		mcq1.getChoices().add(new MCQChoice("choice2", false));
		
		MCQ mcq2 = new MCQ();
		mcq2.setBody("q2");
		mcq2.getChoices().add(new MCQChoice("choice1", true));
		mcq2.getChoices().add(new MCQChoice("choice2", false));
		
		ArrayList<MCQ> questions = new ArrayList<>();
		questions.add(mcq1);
		questions.add(mcq2);
		
		// make request
		EditQuestionsRequest request = new EditQuestionsRequest(ServerUtils.getInstance().getDebugQuizId(), questions);
		EditQuestionsBehavior editQuestions = new EditQuestionsBehavior();
		String response = editQuestions.getResponseString(request);
		
		assertNotNull(response);
	}

	public void testDecodeResponseSuccesful()
	{
		EditQuestionsBehavior editQuestions = new EditQuestionsBehavior();
		String responseStr = "{ \"errors\":\"none\"}";
		EditQuestionsResponse response = editQuestions.decodeResponseString(responseStr);

		assertEquals(ServerResponse.STATUS_SUCCESSFUL, response.getStatus());
	}

	public void testDecodeResponseError()
	{
		EditQuestionsBehavior editQuestions = new EditQuestionsBehavior();
		String responseStr = "{ \"errors\":\"noob minion\"}";
		EditQuestionsResponse response = editQuestions.decodeResponseString(responseStr);

		assertEquals("noob minion", response.getStatus());
	}

}
