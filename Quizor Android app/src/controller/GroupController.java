package controller;

import java.util.ArrayList;

import ui.adapters.QuizzesListAdapter.QuizzInfoGetter;
import ui.adapters.StudentsListAdapter.StudentInfoGetter;
import utils.TimeUtils;
import web.serverBehaviours.AddQuizBehavior;
import web.serverBehaviours.GetStudentsInGroupBehavior;
import web.serverBehaviours.ViewGroupsBehavior;
import web.serverBehaviours.ViewQuizzesBehavior;
import web.serverRequests.AddQuizRequest;
import web.serverRequests.GetStudentsInGroupRequest;
import web.serverRequests.ServerRequest;
import web.serverRequests.ViewGroupsRequest;
import web.serverRequests.ViewQuizzesRequest;
import web.serverResponse.GetStudentsInGroupResponse;
import web.serverResponse.ServerResponse;
import web.serverResponse.ViewGroupsResponse;
import web.serverResponse.ViewQuizzesResponse;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import controller.AsyncTaskController.PostExecutioner;
import core.QuizInfo;
import core.StudentInfo;
import dbc.DBC;

// TODO: Auto-generated Javadoc
/**
 * The Class GroupController.
 */
public class GroupController implements StudentInfoGetter, QuizzInfoGetter
{
	/* fields */
	/** The quiz infos. */
	private ArrayList<QuizInfo> quizInfos;
	
	/** The student infos. */
	private ArrayList<StudentInfo> studentInfos;
	
	/** The refreshable. */
	private Refreshable refreshable;
	
	/** The current task. */
	private AsyncTask<ServerRequest, Object, ServerResponse> currentTask;

	/* Constructor */
	/**
	 * Instantiates a new group controller.
	 *
	 * @param refreshable the refreshable
	 */
	public GroupController(Refreshable refreshable)
	{
		this.refreshable = refreshable;
		quizInfos = new ArrayList<>();
		studentInfos = new ArrayList<>();
	}

	/* setters and getters */
	/**
	 * Gets the quizzesinfo.
	 *
	 * @return the quizzesinfo
	 */
	public ArrayList<QuizInfo> getQuizzesinfo()
	{
		return quizInfos;
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

	/**
	 * Gets the quiz info.
	 *
	 * @param idx            more than 0 and less than quizinfos size
	 * @return the quiz info
	 */
	public QuizInfo getQuizInfo(int idx)
	{
		// DBC.require(idx >= 0 && idx < quizInfos.size());

		return quizInfos.get(idx);
	}

	/* web methods */
	/**
	 * asks the server to add a quiz.
	 *
	 * @param context the context
	 * @param groupId            requires a non empty string
	 * @param quizName the quiz name
	 * @param quizDuration            requries a numbe rmore than zero
	 * @param day            requires a number between 1 and 31
	 * @param month            requires a number between 1 and 12
	 * @param year            requires a number more than zero
	 * @param postExecutioner the post executioner
	 */
	public void addQuiz(Context context, String groupId, String quizName, long quizDuration,
			int day, int month, int year, PostExecutioner postExecutioner)
	{
		// DBC.require(groupId.length() > 0);
		// DBC.require(quizDuration > 0);
		// DBC.require(day >= 1 && day <= 31);
		// DBC.require(month >= 1 && month <= 12);
		// DBC.require(year >= 0);

		// set the request and the behavior
		AddQuizBehavior behavior = new AddQuizBehavior();
		long startDate = TimeUtils.convertToUnixTime(day, month, year, 0, 0, 0);
		AddQuizRequest request = new AddQuizRequest(groupId, quizName, quizDuration, startDate);

		// make the task
		AsyncTask<ServerRequest, Object, ServerResponse> asyncTask = AsyncTaskController
				.getAsyncTask(request, behavior, postExecutioner, context);
		currentTask = asyncTask;
		asyncTask.execute(request);
	}

	/**
	 * asks the server to download the quizzes ensures quizzes infos is not null.
	 *
	 * @param context the context
	 * @param groupId            a non empty string
	 */
	public void refreshQuizzes(final Context context, String groupId)
	{
		// DBC.require(groupId.length() > 0);

		// make the request and behavior
		ViewQuizzesRequest request = new ViewQuizzesRequest();
		request.setGroupId(groupId);
		ViewQuizzesBehavior behavior = new ViewQuizzesBehavior();

		// make the pre executioner
		PostExecutioner postExecutioner = new PostExecutioner()
		{
			@Override
			public void onPostExecution(ServerResponse serverResponse)
			{
				ViewQuizzesResponse response = (ViewQuizzesResponse) serverResponse;
				if (response.getStatus() == ServerResponse.STATUS_SUCCESSFUL)
				{
					quizInfos = response.getQuizInfos();
					refreshable.refreshUI();
					// DBC.ensure(quizInfos != null);

				} else
					Toast.makeText(context, response.getStatus(), Toast.LENGTH_SHORT).show();

			}
		};

		// make the task
		AsyncTask<ServerRequest, Object, ServerResponse> task = AsyncTaskController.getAsyncTask(
				request, behavior, postExecutioner, context);
		currentTask = task;
		task.execute(request);

		// DBC.ensure(quizInfos != null);
	}

	/**
	 * Download students in group.
	 *
	 * @param context the context
	 * @param groupId the group id
	 */
	public void downloadStudentsInGroup(final Context context, String groupId)
	{
		// make request and behavior
		GetStudentsInGroupRequest request = new GetStudentsInGroupRequest(groupId);
		GetStudentsInGroupBehavior behavior = new GetStudentsInGroupBehavior();

		// make post executioner
		PostExecutioner postExecutioner = new PostExecutioner()
		{

			@Override
			public void onPostExecution(ServerResponse serverResponse)
			{
				// make toast
				GetStudentsInGroupResponse response = (GetStudentsInGroupResponse) serverResponse;
				Toast.makeText(context, response.getStatus(), Toast.LENGTH_SHORT).show();

				// set student info
				studentInfos = response.getStudentInfos();
				refreshable.refreshUI();
			}
		};

		// make task
		AsyncTask<ServerRequest, Object, ServerResponse> task = AsyncTaskController.getAsyncTask(
				request, behavior, postExecutioner, context);
		currentTask = task;

		// execute
		task.execute(request);
	}

	/* methods for StudentInfoGetter */
	/* (non-Javadoc)
	 * @see ui.adapters.StudentsListAdapter.StudentInfoGetter#getUserName(int)
	 */
	@Override
	public String getUserName(int i)
	{
		// DBC.require(i >= 0 && i < studentInfos.size());
		return studentInfos.get(i).getUserName();
	}

	/* (non-Javadoc)
	 * @see ui.adapters.StudentsListAdapter.StudentInfoGetter#getMail(int)
	 */
	@Override
	public String getMail(int i)
	{
		// DBC.require(i >= 0 && i < studentInfos.size());

		return studentInfos.get(i).getEmail();
	}

	/* (non-Javadoc)
	 * @see ui.adapters.StudentsListAdapter.StudentInfoGetter#getDepartment(int)
	 */
	@Override
	public String getDepartment(int i)
	{
		// DBC.require(i >= 0 && i < studentInfos.size());

		return studentInfos.get(i).getDepartment();
	}

	/* (non-Javadoc)
	 * @see ui.adapters.StudentsListAdapter.StudentInfoGetter#getGradutionYear(int)
	 */
	@Override
	public String getGradutionYear(int i)
	{
		// DBC.require(i >= 0 && i < studentInfos.size());

		return studentInfos.get(i).getGraduationYear();
	}

	/* (non-Javadoc)
	 * @see ui.adapters.StudentsListAdapter.StudentInfoGetter#getSection(int)
	 */
	@Override
	public String getSection(int i)
	{
		// DBC.require(i >= 0 && i < studentInfos.size());

		return studentInfos.get(i).getSection();
	}

	/* (non-Javadoc)
	 * @see ui.adapters.StudentsListAdapter.StudentInfoGetter#getStudentsCount()
	 */
	@Override
	public int getStudentsCount()
	{

		return studentInfos.size();
	}

	/* methods for quiz info getter */
	/* (non-Javadoc)
	 * @see ui.adapters.QuizzesListAdapter.QuizzInfoGetter#getQuizzesCount()
	 */
	@Override
	public int getQuizzesCount()
	{
		return quizInfos.size();
	}

	/* (non-Javadoc)
	 * @see ui.adapters.QuizzesListAdapter.QuizzInfoGetter#getQuizName(int)
	 */
	@Override
	public CharSequence getQuizName(int position)
	{
		return quizInfos.get(position).getQuizName();
	}

	/* (non-Javadoc)
	 * @see ui.adapters.QuizzesListAdapter.QuizzInfoGetter#getAvailabilityDate(int)
	 */
	@Override
	public long getAvailabilityDate(int position)
	{
		return quizInfos.get(position).getStartDate();
	}

	/* (non-Javadoc)
	 * @see ui.adapters.QuizzesListAdapter.QuizzInfoGetter#getQuizDuration(int)
	 */
	@Override
	public int getQuizDuration(int position)
	{
		return quizInfos.get(position).getDurationSeconds();
	}

	/**
	 * Gets the quiz id.
	 *
	 * @param position the position
	 * @return the quiz id
	 */
	public String getQuizId(int position)
	{
		return quizInfos.get(position).getWebId();

	}

	
	@Override
	public boolean isQuizTaken(int position)
	{
		return quizInfos.get(position).isTaken();

	}

}
