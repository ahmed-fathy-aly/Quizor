package utils;

import java.net.CookieHandler;

import android.util.Log;
import web.serverBehaviours.AddGroupBehaviour;
import web.serverBehaviours.AddQuizBehavior;
import web.serverBehaviours.AddStudentsToGroupBehavior;
import web.serverBehaviours.LoginBehavior;
import web.serverBehaviours.RegisterProfessorBehavior;
import web.serverBehaviours.RegisterStudentBehavior;
import web.serverRequests.AddGroupRequest;
import web.serverRequests.AddQuizRequest;
import web.serverRequests.AddStudentsToGroupRequest;
import web.serverRequests.LoginRequest;
import web.serverRequests.RegisterProfessorRequest;
import web.serverRequests.RegisterStudentRequest;
import web.serverResponse.AddGroupResponse;
import web.serverResponse.AddQuizResponse;
import web.serverResponse.AddStudentsToGroupResponse;
import web.serverResponse.LoginResponse;
import web.serverResponse.RegisterProfessorResponse;
import web.serverResponse.RegisterStudentResponse;
import web.serverResponse.ServerResponse;
import dbc.DBC;

// TODO: Auto-generated Javadoc
/**
 * reets the server for debuggins.
 */
public class ServerUtils
{
	/* fields */
	/** The instance. */
	static ServerUtils instance;
	
	/** The debug group id. */
	String debugGroupId;
	
	/** The debug quiz id. */
	private String debugQuizId;

	/**
	 * Instantiates a new server utils.
	 */
	private ServerUtils()
	{
	}

	/**
	 * Gets the single instance of ServerUtils.
	 *
	 * @return single instance of ServerUtils
	 */
	public static ServerUtils getInstance()
	{
		if (instance == null)
			instance = new ServerUtils();
		return instance;
	}

	/* setters and getters */

	/**
	 * Gets the debug group id.
	 *
	 * @return the debug group id
	 */
	public String getDebugGroupId()
	{
		return this.debugGroupId;
	}

	/**
	 * Gets the debug quiz id.
	 *
	 * @return the debug quiz id
	 */
	public String getDebugQuizId()
	{
		return debugQuizId;
	}

	/* Methods */

	/**
	 * asks the server to clear all data and logs in a dummy account called
	 * debug and make a dummy group called debug and make a dummy quiz called
	 * debug.
	 *
	 * @return a server response of status successful
	 */
	public void resetServer()
	{
		clearServer();
		registerDebugProfUser();
		registerDebugStudentUser();
		loginProfessorDebug();
		makeDummyGroup();
		makeDummyQuiz();
		addDebugUsers();
	}

	/**
	 * Reset server student.
	 */
	public void resetServerStudent()
	{
		clearServer();
		registerDebugProfUser();
		registerDebugStudentUser();
		loginProfessorDebug();
		makeDummyGroup();
		makeDummyQuiz();
		addDebugUsers();
		loginStudentDebug();
	}

	/**
	 * enures server is cleared.
	 */
	private void clearServer()
	{
		// send a delete request
		HTTPUtils utils = HTTPUtils.getInstance();
		String responseString = utils.deleteRequest(HTTPUtils.baseURL + "clear_server.json",
				new String[0], new String[0]);

		//DBC.ensure(responseString != null);
	}

	/**
	 * ensures a user is registered.
	 */
	private void registerDebugProfUser()
	{
		// register a dummy user
		RegisterProfessorRequest registerRequest = new RegisterProfessorRequest("debug", "debug",
				"debug", "debug", "debug@eng.asu.edu.eg");
		RegisterProfessorBehavior register = new RegisterProfessorBehavior();
		RegisterProfessorResponse registerResponse = (RegisterProfessorResponse) register
				.handleRequest(registerRequest);

		//DBC.ensure(registerResponse.getStatus() == ServerResponse.STATUS_SUCCESSFUL);

	}

	/**
	 * ensures a user is registered.
	 */
	private void registerDebugStudentUser()
	{
		// register a dummy user
		RegisterStudentRequest registerRequest = new RegisterStudentRequest("qwerty", "qwerty",
				"qwerty", "qwertino@eng.asu.edu.eg", "qwertiy", "CSE", "2016", "1");
		RegisterStudentBehavior register = new RegisterStudentBehavior();
		RegisterStudentResponse registerResponse = (RegisterStudentResponse) register
				.handleRequest(registerRequest);

		//DBC.ensure(registerResponse.getStatus() == ServerResponse.STATUS_SUCCESSFUL);

	}

	/**
	 * enures the account is logged in.
	 */
	private void loginProfessorDebug()
	{
		// log in
		LoginRequest loginRequest = new LoginRequest("debug", "debug", "0");
		LoginBehavior login = new LoginBehavior();
		LoginResponse loginResponse = (LoginResponse) login.handleRequest(loginRequest);
		//DBC.ensure(loginResponse.getStatus() == ServerResponse.STATUS_SUCCESSFUL);

	}

	/**
	 * ensures the account is logged in.
	 */
	private void loginStudentDebug()
	{
		// log in
		LoginRequest loginRequest = new LoginRequest("qwerty", "qwerty", "0");
		LoginBehavior login = new LoginBehavior();
		LoginResponse loginResponse = (LoginResponse) login.handleRequest(loginRequest);
		
		//DBC.ensure(loginResponse.getStatus() == ServerResponse.STATUS_SUCCESSFUL);

	}

	/**
	 * ensures a group is made.
	 */
	private void makeDummyGroup()
	{
		// make a dummy group
		AddGroupRequest addGroupRequest = new AddGroupRequest("debug", "for debugging");
		AddGroupBehaviour addGroup = new AddGroupBehaviour();
		AddGroupResponse addGroupResponse = (AddGroupResponse) addGroup
				.handleRequest(addGroupRequest);
		debugGroupId = addGroupResponse.getGroupId();

		//DBC.ensure(addGroupResponse.getStatus() == ServerResponse.STATUS_SUCCESSFUL);

	}

	/**
	 * ensures the debug student is added to the debug group.
	 */
	private void addDebugUsers()
	{
		AddStudentsToGroupRequest request = new AddStudentsToGroupRequest(debugGroupId, "", "", "",
				"", "", "");
		AddStudentsToGroupBehavior addStudents = new AddStudentsToGroupBehavior();
		AddStudentsToGroupResponse response = (AddStudentsToGroupResponse) addStudents
				.handleRequest(request);

		//DBC.ensure(response.getStatus().equals(ServerResponse.STATUS_SUCCESSFUL));
	}

	/**
	 * ensures a quiz is made.
	 */
	private void makeDummyQuiz()
	{
		// make a dummy quiz
		AddQuizRequest addQuizRequest = new AddQuizRequest(debugGroupId, "debug", 3600,
				100000000000l);
		AddQuizBehavior addQuiz = new AddQuizBehavior();
		AddQuizResponse addQuizResponse = (AddQuizResponse) addQuiz.handleRequest(addQuizRequest);
		this.debugQuizId = addQuizResponse.getQuizId();

		//DBC.ensure(addQuizResponse.getStatus() == ServerResponse.STATUS_SUCCESSFUL);

	}

}
