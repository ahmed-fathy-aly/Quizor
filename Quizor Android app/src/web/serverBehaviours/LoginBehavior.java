package web.serverBehaviours;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import utils.HTTPUtils;
import web.serverRequests.LoginRequest;
import web.serverRequests.ServerRequest;
import web.serverResponse.LoginResponse;
import web.serverResponse.RegisterProfessorResponse;
import web.serverResponse.ServerResponse;
import core.ProfessorInfo;
import core.StudentInfo;
import dbc.DBC;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginBehavior.
 */
public class LoginBehavior extends ServerBehaviour
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * web.serverBehaviours.ServerBehaviour#handleRequest(web.serverRequests
	 * .ServerRequest)
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
	 * @param serverRequest
	 *            required to be a Login request
	 * @return ensures a non-null string is returned
	 */
	public String getResponseString(ServerRequest serverRequest)
	{
		// DBC.require(serverRequest.getClass() == LoginRequest.class);

		// make keys and values
		LoginRequest request = (LoginRequest) serverRequest;
		String[] keys = new String[]
		{ "username", "password", "remember_me", "gcm_id" };
		String[] values = new String[]
		{ request.getUserName(), request.getPassword(), request.getRememberMe(), request.getGCMId(), request.getGCMId() };

		// get the response
		HTTPUtils utils = HTTPUtils.getInstance();
		String responseString = utils.postRequest(HTTPUtils.baseURL + "login.json", keys, values);

		// DBC.ensure(responseString != null);
		return responseString;
	}

	/**
	 * requires responseStr to not be null ensures a LoginResponse with status
	 * set.
	 *
	 * @param responseStr
	 *            the response str
	 * @return the login response
	 */
	public LoginResponse decodeResponseString(String responseStr)
	{
		// DBC.require(responseStr != null);

		// check it was successful
		LoginResponse response = new LoginResponse();
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
				if (json.getString("type").equals("Professor"))
					response.setUserType(LoginResponse.UserType.PROFESSOR);
				else
					response.setUserType(LoginResponse.UserType.STUDENT);
			} else
				response.setStatus(error);
		} catch (JSONException e)
		{
			response.setStatus(ServerResponse.STATUS_FAILED);
		}

		// DBC.ensure(response != null);
		// DBC.ensure(response.getStatus() != null);
		return response;

	}

}
