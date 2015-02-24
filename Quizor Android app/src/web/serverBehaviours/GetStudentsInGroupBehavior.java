package web.serverBehaviours;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import utils.HTTPUtils;
import web.serverRequests.GetStudentsInGroupRequest;
import web.serverRequests.LoginRequest;
import web.serverRequests.ServerRequest;
import web.serverResponse.GetStudentsInGroupResponse;
import web.serverResponse.LoginResponse;
import web.serverResponse.RegisterProfessorResponse;
import web.serverResponse.ServerResponse;
import core.ProfessorInfo;
import core.StudentInfo;
import dbc.DBC;

// TODO: Auto-generated Javadoc
/**
 * The Class GetStudentsInGroupBehavior.
 */
public class GetStudentsInGroupBehavior extends ServerBehaviour
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
	 * @param serverRequest            required to be a Login request
	 * @return ensures a non-null string is returned
	 */
	public String getResponseString(ServerRequest serverRequest)
	{
		//DBC.require(serverRequest.getClass() == GetStudentsInGroupRequest.class);

		// make keys and values
		GetStudentsInGroupRequest request = (GetStudentsInGroupRequest) serverRequest;
		String[] keys = new String[]
		{};
		String[] values = new String[]
		{};

		// get the response
		HTTPUtils utils = HTTPUtils.getInstance();
		String url = HTTPUtils.baseURL + "groups/" + request.getGroupId() + "/students.json";
		String responseString = utils.getRequest(url, keys, values);

		//DBC.ensure(responseString != null);
		return responseString;
	}

	/**
	 * requires responseStr to not be null ensures a GetStudentsInGroups
	 * response with status set.
	 *
	 * @param responseStr the response str
	 * @return the gets the students in group response
	 */
	public GetStudentsInGroupResponse decodeResponseString(String responseStr)
	{
		//DBC.require(responseStr != null);

		// check it was successful
		GetStudentsInGroupResponse response = new GetStudentsInGroupResponse();
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

				// get student info
				JSONArray arr = json.getJSONArray("students");
				for (int i = 0; i < arr.length(); i++)
				{
					JSONObject jsonObject = arr.getJSONObject(i);
					response.getStudentInfos().add(StudentInfo.parseFromJSON(jsonObject));
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
