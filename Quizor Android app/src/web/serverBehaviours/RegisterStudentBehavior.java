package web.serverBehaviours;

import org.json.JSONException;
import org.json.JSONObject;

import dbc.DBC;
import utils.HTTPUtils;
import web.serverRequests.RegisterStudentRequest;
import web.serverRequests.ServerRequest;
import web.serverResponse.RegisterStudentResponse;
import web.serverResponse.ServerResponse;
import android.net.wifi.WifiConfiguration.Status;
import android.util.Log;

// TODO: Auto-generated Javadoc
/**
 * asks the server to register a new user.
 *
 * @author ahmed fathy aly
 */
public class RegisterStudentBehavior extends ServerBehaviour
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
	 * ensures a response with non null status is returned.
	 *
	 * @param responseStr            the string returned from the server
	 * @return a Register response with the status set
	 */
	public RegisterStudentResponse decodeResponseString(String responseStr)
	{
		// check it was succesful
		RegisterStudentResponse response = new RegisterStudentResponse();
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

	/**
	 * ensures a non null string is returned.
	 *
	 * @param httpRequest           required to be a register request
	 * @return the response string
	 */
	public String getResponseString(ServerRequest httpRequest)
	{
		//DBC.require(httpRequest.getClass() == RegisterStudentRequest.class);
		
		// make keys and values
		RegisterStudentRequest request = (RegisterStudentRequest) httpRequest;
		String keys[] = new String[]
		{ "student[username]", "student[password]", "student[password_confirmation]",
				"student[email]", "student[name]", "student[department_name]", "student[year_number]", "student[section_number]" };
		String[] values = new String[]
		{ request.getUserName(), request.getPassword(), request.getPasswordConfirmation(),
				request.getEmail(), request.getFullName(), request.getDepartment(), request.getGraduationYear(), request.getSection() };

		// get the response
		HTTPUtils utils = HTTPUtils.getInstance();
		String responseString = utils.postRequest(HTTPUtils.baseURL + "students.json", keys, values);
		
		//DBC.ensure(responseString != null);
		return responseString;
	}

}
