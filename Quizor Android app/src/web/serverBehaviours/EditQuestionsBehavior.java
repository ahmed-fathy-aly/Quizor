package web.serverBehaviours;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import core.MCQ;
import dbc.DBC;
import utils.HTTPUtils;
import web.serverRequests.EditQuestionsRequest;
import web.serverRequests.RegisterStudentRequest;
import web.serverRequests.ServerRequest;
import web.serverResponse.EditQuestionsResponse;
import web.serverResponse.RegisterStudentResponse;
import web.serverResponse.ServerResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class EditQuestionsBehavior.
 */
public class EditQuestionsBehavior extends ServerBehaviour
{
	
	/* (non-Javadoc)
	 * @see web.serverBehaviours.ServerBehaviour#handleRequest(web.serverRequests.ServerRequest)
	 */
	@Override
	public ServerResponse handleRequest(ServerRequest httpRequest)
	{
		String responseStr = getResponseString(httpRequest);
		return decodeResponseString(responseStr);

	}

	/**
	 * ensures a non null string is returned.
	 *
	 * @param httpRequest            required to be a editquestions request
	 * @return the response string
	 */
	public String getResponseString(ServerRequest httpRequest)
	{
		//DBC.require(httpRequest.getClass() == EditQuestionsRequest.class);

		// make the questions array
		EditQuestionsRequest request = (EditQuestionsRequest) httpRequest;
		JSONArray arr = new JSONArray();
		for (MCQ question : request.getQuestions())
			arr.put(question.getJSON());

		// make keys and values
		String keys[] = new String[]
		{ "questions" };
		String[] values = new String[]
		{ arr.toString() };

		// get the response
		HTTPUtils utils = HTTPUtils.getInstance();
		String url = HTTPUtils.baseURL + "/quizzes/" + request.getQuizId() + "/questions.json";
		String responseString = utils.postRequest(url, keys, values);

		//DBC.ensure(responseString != null);
		return responseString;
	}

	/**
	 * ensures a response with non null status is returned.
	 *
	 * @param responseStr            the string returned from the server
	 * @return a EditQuestions response with the status set
	 */
	public EditQuestionsResponse decodeResponseString(String responseStr)
	{
		// check it was succesful
		EditQuestionsResponse response = new EditQuestionsResponse();
		if (responseStr == null)
		{
			response.setStatus(ServerResponse.STATUS_FAILED);
			return response;
		}

		// parse the json
		try
		{
			JSONObject json = new JSONObject(responseStr);
			String error = json.getString("errors");
			if (error.equals("none"))
				response.setStatus(ServerResponse.STATUS_SUCCESSFUL);
			else
				response.setStatus(error);
		} catch (JSONException e)
		{
			response.setStatus(ServerResponse.STATUS_FAILED);
		}

		//DBC.ensure(response.getStatus() != null);
		return response;
	}

}
