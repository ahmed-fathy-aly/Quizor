package web.serverBehaviours;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import utils.HTTPUtils;
import web.serverRequests.AnswerQuestionsRequest;
import web.serverRequests.LoginRequest;
import web.serverRequests.ServerRequest;
import web.serverResponse.AnswerQuestionsResponse;
import web.serverResponse.LoginResponse;
import web.serverResponse.RegisterProfessorResponse;
import web.serverResponse.ServerResponse;
import core.MCQ;
import core.MCQChoice;
import core.ProfessorInfo;
import core.StudentInfo;
import dbc.DBC;

// TODO: Auto-generated Javadoc
/**
 * The Class AnswerQuestionsBehavior.
 */
public class AnswerQuestionsBehavior extends ServerBehaviour
{

	/* (non-Javadoc)
	 * @see web.serverBehaviours.ServerBehaviour#handleRequest(web.serverRequests.ServerRequest)
	 */
	@Override
	public ServerResponse handleRequest(ServerRequest request)
	{
		String responseString = getResponseString(request);
		return decodeResponseString(responseString);
	}

	/**
	 * Gets the response string.
	 *
	 * @param serverRequest            required to be a Answer Questions request
	 * @return ensures a non-null string is returned
	 */
	public String getResponseString(ServerRequest serverRequest)
	{
		//DBC.require(serverRequest.getClass() == AnswerQuestionsRequest.class);

		// make keys and values
		AnswerQuestionsRequest request = (AnswerQuestionsRequest) serverRequest;
		String[] keys = new String[]
		{ "solutions" };
		String[] values = new String[]
		{ makeJSONArray(request.getQuestions()).toString() };
		// get the response
		HTTPUtils utils = HTTPUtils.getInstance();
		String url = HTTPUtils.baseURL + "/quizzes/" + request.getQuizId() + "/solutions.json";
		Log.e("Game", "url:" +url);
		Log.e("Game", "arr" + makeJSONArray(request.getQuestions()).toString());
		
		String responseString = utils.postRequest(url, keys, values);

		//DBC.ensure(responseString != null);
		return responseString;
	}

	/**
	 * convert the answered questions to a json array.
	 *
	 * @param questions the questions
	 * @return a JSON Array
	 */
	public JSONArray makeJSONArray(ArrayList<MCQ> questions)
	{
		JSONArray jsonArr = new JSONArray();

		try
		{
			// put each questions
			for (int i = 0; i < questions.size(); i++)
			{
				// question id
				JSONObject jsonObj = new JSONObject();
				MCQ mcq = questions.get(i);
				jsonObj.put("q_id", mcq.getWebId());

				// the selected choices
				jsonObj.put("answer", "");
				for (MCQChoice choice : mcq.getChoices())
					if (choice.isChecked())
						jsonObj.put("answer", choice.getBody());

				jsonArr.put(jsonObj);
			}

		} catch (JSONException e)
		{
			jsonArr = null;
		}

		//DBC.ensure(jsonArr != null);
		return jsonArr;

	}

	/**
	 * requires responseStr to not be null ensures a AnswerQuestions response
	 * with status set.
	 *
	 * @param responseStr the response str
	 * @return the answer questions response
	 */
	public AnswerQuestionsResponse decodeResponseString(String responseStr)
	{
		//DBC.require(responseStr != null);

		// check it was successful
		AnswerQuestionsResponse response = new AnswerQuestionsResponse();
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
			{
				response.setStatus(ServerResponse.STATUS_SUCCESSFUL);
				response.setMessage(json.getString("message"));
			} else
				response.setStatus(error);
		} catch (JSONException e)
		{
			response.setStatus(ServerResponse.STATUS_FAILED);
		}

		//DBC.ensure(response != null);
		//DBC.ensure(response.getStatus() != null);
		return response;

	}

}
