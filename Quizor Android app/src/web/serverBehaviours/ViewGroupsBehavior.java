package web.serverBehaviours;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import core.GroupInfo;
import core.ProfessorInfo;
import dbc.DBC;
import utils.HTTPUtils;
import web.serverRequests.LoginRequest;
import web.serverRequests.ServerRequest;
import web.serverRequests.ViewGroupsRequest;
import web.serverResponse.LoginResponse;
import web.serverResponse.ServerResponse;
import web.serverResponse.ViewGroupsResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class ViewGroupsBehavior.
 */
public class ViewGroupsBehavior extends ServerBehaviour
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
		//DBC.require(serverRequest.getClass() == ViewGroupsRequest.class);

		// make keys and values
		ViewGroupsRequest request = (ViewGroupsRequest) serverRequest;
		String[] keys = new String[]{ };
		String[] values = new String[]{};

		// get the response
		HTTPUtils utils = HTTPUtils.getInstance();
		String responseString = utils.getRequest(HTTPUtils.baseURL + "groups.json", keys, values);

		//DBC.ensure(responseString != null);
		return responseString;
	}

	/**
	 * requires responseStr to not be null ensures a LoginResponse with status
	 * set.
	 *
	 * @param responseStr the response str
	 * @return the view groups response
	 */
	public ViewGroupsResponse decodeResponseString(String responseStr)
	{
		//DBC.require(responseStr != null);

		// check it was successful
		ViewGroupsResponse response = new ViewGroupsResponse();
		if (responseStr == null)
		{
			response.setStatus(ServerResponse.STATUS_FAILED);
			return response;
		}

		// parse the json
		Log.e("Game", "responsestr" + responseStr);
		try
		{
			Log.e("Game", "1");
			JSONObject json = new JSONObject(responseStr);
			Log.e("Game", "2");

			String error = json.getString("errors");
			if (error.equals("none"))
			{
				Log.e("Game", "3");

				// parse array of group infos
				ArrayList<GroupInfo> groupInfos = new ArrayList<>();
				JSONArray jsonArr = json.getJSONArray("groups");
				Log.e("Game", "4");

				for (int i = 0; i < jsonArr.length(); i++)
					groupInfos.add(GroupInfo.parseFromJson(jsonArr.getJSONObject(i)));
				Log.e("Game", "5");
				// set response
				response.setStatus(ServerResponse.STATUS_SUCCESSFUL);
				response.setGroupInfos(groupInfos);
				Log.e("Game", "6");
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
