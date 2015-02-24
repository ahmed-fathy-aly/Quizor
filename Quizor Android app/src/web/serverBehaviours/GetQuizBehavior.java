package web.serverBehaviours;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import core.MCQ;
import core.ProfessorInfo;
import core.StudentInfo;
import dbc.DBC;
import utils.HTTPUtils;
import web.serverRequests.GetQuizRequest;
import web.serverRequests.LoginRequest;
import web.serverRequests.ServerRequest;
import web.serverResponse.GetQuizResponse;
import web.serverResponse.LoginResponse;
import web.serverResponse.ServerResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class GetQuizBehavior.
 */
public class GetQuizBehavior extends ServerBehaviour
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
	 * @param serverRequest required to be a get quiz request
	 * @return ensures a non-null string is returned
	 */
	public String getResponseString(ServerRequest serverRequest)
	{
		//DBC.require(serverRequest.getClass() == GetQuizRequest.class);
		
		// make keys and values
		GetQuizRequest request = (GetQuizRequest) serverRequest;
		String[] keys = new String[]
		{};
		String[] values = new String[]
		{ };

		// get the response
		HTTPUtils utils = HTTPUtils.getInstance();
		String url = HTTPUtils.baseURL + "/quizzes/" + request.getQuizId() + ".json";
		String responseString = utils.getRequest(url, keys, values);
		
		//DBC.ensure(responseString != null);
		Log.e("Game", "response string" + responseString);
		return responseString;
	}

	/**
	 * requires responseStr to not be null ensures a LoginResponse with status
	 * set.
	 *
	 * @param responseStr the response str
	 * @return the gets the quiz response
	 */
	public GetQuizResponse decodeResponseString(String responseStr)
	{
		//DBC.require(responseStr != null);

		// check it was successful
		GetQuizResponse response = new GetQuizResponse();
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
				// parse the questions, duration
				response.setStatus(ServerResponse.STATUS_SUCCESSFUL);
				JSONObject quizJson = json.getJSONObject("quiz");
				response.setDuration(Integer.parseInt(quizJson.getString("duration")));
				JSONArray questionsJson = quizJson.getJSONArray("questions");
				for (int i = 0; i < questionsJson.length(); i++) 
				{
					JSONObject questionJson = questionsJson.getJSONObject(i);
					response.getQuestions().add(MCQ.parseFromJSON(questionJson));
				}
					
			} else
				response.setStatus(error);
		} catch (JSONException e)
		{
			Log.e("Game", e.getMessage());
			response.setStatus(ServerResponse.STATUS_FAILED);
		}

		//DBC.ensure(response != null);
		//DBC.ensure(response.getStatus() != null);
		return response;

	}

}
