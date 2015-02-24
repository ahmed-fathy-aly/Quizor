package controller;

import web.gcm.GCMUtils;
import web.serverBehaviours.LoginBehavior;
import web.serverBehaviours.RegisterProfessorBehavior;
import web.serverBehaviours.RegisterStudentBehavior;
import web.serverBehaviours.ServerBehaviour;
import web.serverRequests.LoginRequest;
import web.serverRequests.RegisterProfessorRequest;
import web.serverRequests.RegisterStudentRequest;
import web.serverRequests.ServerRequest;
import web.serverResponse.ServerResponse;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import controller.AsyncTaskController.PostExecutioner;

// TODO: Auto-generated Javadoc
/**
 * The Class UserController.
 */
public class UserController
{
	/* fields */
	/** The context. */
	private Context context;

	/** The current task. */
	private AsyncTask<ServerRequest, Object, ServerResponse> currentTask;

	/* Constructors */
	/**
	 * Instantiates a new user controller.
	 *
	 * @param context
	 *            the context
	 */
	public UserController(Context context)
	{
		this.context = context;
	}

	/* setters and getters */
	/**
	 * Gets the context.
	 *
	 * @return the context
	 */
	public Context getContext()
	{
		return context;
	}

	/**
	 * Sets the context.
	 *
	 * @param context
	 *            the new context
	 */
	public void setContext(Context context)
	{
		this.context = context;
	}

	/**
	 * Gets the current task.
	 *
	 * @return the current task
	 */
	public AsyncTask<ServerRequest, Object, ServerResponse> getCurrentTask()
	{
		return currentTask;
	}

	/* methods */

	/**
	 * asks the server to register a new user.
	 *
	 * @param userName
	 *            the user name
	 * @param password
	 *            the password
	 * @param passwordConfirmation
	 *            the password confirmation
	 * @param mail
	 *            the mail
	 * @param name
	 *            the name
	 * @param postExecutioner
	 *            the post executioner
	 */
	public void registerProfessor(String userName, String password, String passwordConfirmation,
			String mail, String name, PostExecutioner postExecutioner)
	{
		// sets the request and behaviour
		RegisterProfessorRequest request = new RegisterProfessorRequest(userName, password,
				passwordConfirmation, name, mail);
		ServerBehaviour behaviour = new RegisterProfessorBehavior();

		// make the task
		AsyncTask<ServerRequest, Object, ServerResponse> asyncTask = AsyncTaskController
				.getAsyncTask(request, behaviour, postExecutioner, context);
		currentTask = asyncTask;
		asyncTask.execute(request);
	}

	/**
	 * asks the server to login.
	 *
	 * @param userName
	 *            the user name
	 * @param password
	 *            the password
	 * @param rememberMe
	 *            the remember me
	 * @param postExecutioner
	 *            the post executioner
	 */
	public void login(Activity activity, String userName, String password, boolean rememberMe,
			PostExecutioner postExecutioner)
	{
		// get the gcm registration id
		GCMUtils utils = new GCMUtils();
		String gcmId = utils.getRegistrationId(activity);
		Log.e("Game", "registration id:" + gcmId);

		// set the request and the behavior
		LoginRequest request = new LoginRequest(userName, password, rememberMe ? "1" : "0");
		request.setGCmId(gcmId);
		LoginBehavior behavior = new LoginBehavior();

		// make the task
		AsyncTask<ServerRequest, Object, ServerResponse> asyncTask = AsyncTaskController
				.getAsyncTask(request, behavior, postExecutioner, context);
		currentTask = asyncTask;
		asyncTask.execute(request);
	}

	/**
	 * asks the server to register a new user.
	 *
	 * @param userName
	 *            the user name
	 * @param password
	 *            the password
	 * @param passwordConfirmation
	 *            the password confirmation
	 * @param mail
	 *            the mail
	 * @param fullName
	 *            the full name
	 * @param department
	 *            the department
	 * @param graduationYear
	 *            the graduation year
	 * @param section
	 *            the section
	 * @param postExecutioner
	 *            the post executioner
	 */
	public void registerStudent(String userName, String password, String passwordConfirmation,
			String mail, String fullName, String department, String graduationYear, String section,
			PostExecutioner postExecutioner)
	{
		// make the request and behavior
		RegisterStudentRequest request = new RegisterStudentRequest(userName, password,
				passwordConfirmation, mail, fullName, department, graduationYear, section);
		RegisterStudentBehavior register = new RegisterStudentBehavior();

		// get task
		AsyncTask<ServerRequest, Object, ServerResponse> task = AsyncTaskController.getAsyncTask(
				request, register, postExecutioner, context);
		currentTask = task;

		// execute
		task.execute(request);
	}

}
