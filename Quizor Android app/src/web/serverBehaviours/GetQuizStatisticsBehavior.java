package web.serverBehaviours;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import utils.HTTPUtils;
import web.serverRequests.GetQuizStatisticsRequest;
import web.serverRequests.LoginRequest;
import web.serverRequests.ServerRequest;
import web.serverResponse.GetQuizResponse;
import web.serverResponse.GetQuizStatisticsResponse;
import web.serverResponse.LoginResponse;
import web.serverResponse.ServerResponse;
import core.ProfessorInfo;
import core.SolutionStatistics;
import core.StudentInfo;
import dbc.DBC;

// TODO: Auto-generated Javadoc
/**
 * The Class GetQuizStatisticsBehavior.
 */
public class GetQuizStatisticsBehavior extends ServerBehaviour
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
	 * @param serverRequest            required to be a GetQuizStatisticsRequest
	 * @return ensures a non-null string is returned
	 */
	public String getResponseString(ServerRequest serverRequest)
	{
		//DBC.require(serverRequest.getClass() == GetQuizStatisticsRequest.class);

		// make keys and values
		GetQuizStatisticsRequest request = (GetQuizStatisticsRequest) serverRequest;
		String[] keys = new String[]
		{};
		String[] values = new String[]
		{};

		// get the response
		HTTPUtils utils = HTTPUtils.getInstance();
		String url = HTTPUtils.baseURL + "/quizzes/" + request.getQuizId() + "/solutions.json";
		String responseString = utils.getRequest(url, keys, values);

		//DBC.ensure(responseString != null);
		return responseString;
	}

	/**
	 * requires responseStr to not be null ensures a GetQuizResponse with status
	 * set.
	 *
	 * @param responseStr the response str
	 * @return the gets the quiz statistics response
	 */
	public GetQuizStatisticsResponse decodeResponseString(String responseStr)
	{
		//DBC.require(responseStr != null);

		// check it was successful
		GetQuizStatisticsResponse response = new GetQuizStatisticsResponse();
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
				// parse status
				response.setStatus(ServerResponse.STATUS_SUCCESSFUL);

				// parse solutions
				JSONArray arr = json.getJSONArray("solutions");
				for (int i = 0; i < arr.length(); i++)
				{
					JSONObject solJSON = arr.getJSONObject(i);
					response.getSolutions().add(SolutionStatistics.parseFromJSON(solJSON));
				}
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
