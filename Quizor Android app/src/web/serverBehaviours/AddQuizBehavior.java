package web.serverBehaviours;

import org.json.JSONException;
import org.json.JSONObject;

import utils.HTTPUtils;
import web.serverRequests.AddQuizRequest;
import web.serverRequests.LoginRequest;
import web.serverRequests.ServerRequest;
import web.serverResponse.AddQuizResponse;
import web.serverResponse.LoginResponse;
import web.serverResponse.RegisterProfessorResponse;
import web.serverResponse.ServerResponse;
import core.ProfessorInfo;
import dbc.DBC;

// TODO: Auto-generated Javadoc
/**
 * The Class AddQuizBehavior.
 */
public class AddQuizBehavior extends ServerBehaviour
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
	 * @param serverRequest required to be a add quiz request
	 * @return ensures a non-null string is returned
	 */
	public String getResponseString(ServerRequest serverRequest)
	{
		//DBC.require(serverRequest.getClass() == AddQuizRequest.class);
		
		// make keys and values
		AddQuizRequest request = (AddQuizRequest) serverRequest;
		String[] keys = new String[]
		{ "group_id", "quiz[name]", "quiz[duration]", "quiz[date]" };
		String[] values = new String[]
		{ request.getGroupId(), request.getQuizName(), ""+request.getQuizDuration(), "" + request.getQuizStartDate() };

		// get the response
		HTTPUtils utils = HTTPUtils.getInstance();
		String responseString = utils.postRequest(HTTPUtils.baseURL + "quizzes.json", keys, values);
		
		//DBC.ensure(responseString != null);
		return responseString;
	}

	/**
	 * requires responseStr to not be null ensures a LoginResponse with status
	 * set.
	 *
	 * @param responseStr the response str
	 * @return the adds the quiz response
	 */
	public AddQuizResponse decodeResponseString(String responseStr)
	{
		//DBC.require(responseStr != null);

		// check it was successful
		AddQuizResponse response = new AddQuizResponse();
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
				response.setQuizId(json.getString("quiz_id"));
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
