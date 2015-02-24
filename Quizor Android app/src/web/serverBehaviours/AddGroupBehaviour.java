package web.serverBehaviours;

import org.json.JSONException;
import org.json.JSONObject;

import core.ProfessorInfo;
import dbc.DBC;
import utils.HTTPUtils;
import web.serverRequests.AddGroupRequest;
import web.serverRequests.LoginRequest;
import web.serverRequests.ServerRequest;
import web.serverResponse.AddGroupResponse;
import web.serverResponse.LoginResponse;
import web.serverResponse.ServerResponse;
import android.webkit.WebBackForwardList;

// TODO: Auto-generated Javadoc
/**
 * The Class AddGroupBehaviour.
 */
public class AddGroupBehaviour extends ServerBehaviour
{

	/* (non-Javadoc)
	 * @see web.serverBehaviours.ServerBehaviour#handleRequest(web.serverRequests.ServerRequest)
	 */
	@Override
	public ServerResponse handleRequest(ServerRequest request)
	{
		String responseString = getResponseString((AddGroupRequest) request);
		return decodeResponseString(responseString);
	}

	/**
	 * parse error = none, group_id = groupId.
	 *
	 * @param responseStr the response str
	 * @return the adds the group response
	 */
	public AddGroupResponse decodeResponseString(String responseStr)
	{
		//DBC.require(responseStr != null);

		// check it was successful
		AddGroupResponse response = new AddGroupResponse();
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
				response.setGroupId(json.getString("group_id"));
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

	/**
	 * Gets the response string.
	 *
	 * @param request an add group request
	 * make a request with keys group[name] and group[description]
	 * @return the response string
	 */
	public String getResponseString(AddGroupRequest request)
	{
		//DBC.require(request.getClass() == AddGroupRequest.class);

		
		// make params
		String[] keys = new String[]{"group[name]", "group[description]"};
		String[] values = new String[]{request.getGroupName(), request.getGroupDescription()};
		
		// get the response
		HTTPUtils utils = HTTPUtils.getInstance();
		String responseString = utils.postRequest(HTTPUtils.baseURL + "groups.json", keys, values);
		
		//DBC.ensure(responseString != null);
		return responseString;
	}

}
