package web.serverBehaviours;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import core.GroupInfo;
import core.ProfessorInfo;
import core.QuizInfo;
import dbc.DBC;
import utils.HTTPUtils;
import web.serverRequests.LoginRequest;
import web.serverRequests.ServerRequest;
import web.serverRequests.ViewGroupsRequest;
import web.serverRequests.ViewQuizzesRequest;
import web.serverResponse.LoginResponse;
import web.serverResponse.ServerResponse;
import web.serverResponse.ViewGroupsResponse;
import web.serverResponse.ViewQuizzesResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class ViewQuizzesBehavior.
 */
public class ViewQuizzesBehavior extends ServerBehaviour
{

	/* (non-Javadoc)
	 * @see web.serverBehaviours.ServerBehaviour#handleRequest(web.serverRequests.ServerRequest)
	 */
	public ServerResponse handleRequest(ServerRequest request)
	{
		String responseString = getResponseString(request);
		return decodeResponseString(responseString);
	}

	/**
	 * Gets the response string.
	 *
	 * @param serverRequest            required to be a Login request
	 * @return ensures a non-null string is returned
	 */
	public String getResponseString(ServerRequest serverRequest)
	{
		//DBC.require(serverRequest.getClass() == ViewQuizzesRequest.class);

		// make keys and values
		ViewQuizzesRequest request = (ViewQuizzesRequest) serverRequest;
		String[] keys = new String[]{"group_id"};;
		String[] values = new String[]	{request.getGroupId()};

		// get the response
		HTTPUtils utils = HTTPUtils.getInstance();
		String responseString = utils.getRequest(HTTPUtils.baseURL + "quizzes.json", keys, values);

		//DBC.ensure(responseString != null);
		return responseString;
	}

	/**
	 * requires responseStr to not be null ensures a ViewQuizzes with status set.
	 *
	 * @param responseStr the response str
	 * @return the view quizzes response
	 */
	public ViewQuizzesResponse decodeResponseString(String responseStr)
	{
		//DBC.require(responseStr != null);

		// check it was successful
		ViewQuizzesResponse response = new ViewQuizzesResponse();
		if (responseStr == null)
		{
			response.setStatus(ServerResponse.STATUS_FAILED);
			return response;
		}

		// parse the json
		Log.e("Game", "responsestr" + responseStr);
		try
		{
			JSONObject json = new JSONObject(responseStr);

			String error = json.getString("errors");
			if (error.equals("none"))
			{

				 // parse array of quiz infos
				 ArrayList<QuizInfo> quizInfos = new ArrayList<>();
				 JSONArray jsonArr = json.getJSONArray("quizzes");
				
				 for (int i = 0; i < jsonArr.length(); i++)
				 quizInfos.add(QuizInfo.parseFromJson(jsonArr.getJSONObject(i)));

				// set response
				response.setStatus(ServerResponse.STATUS_SUCCESSFUL);
				 response.setQuizInfos(quizInfos);
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
