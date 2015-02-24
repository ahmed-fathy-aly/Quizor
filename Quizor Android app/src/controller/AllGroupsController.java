package controller;

import java.util.ArrayList;

import ui.adapters.GroupsListAdapter.GroupInfoGetter;
import web.serverBehaviours.AddGroupBehaviour;
import web.serverBehaviours.AddStudentsToGroupBehavior;
import web.serverBehaviours.GetStudentsInGroupBehavior;
import web.serverBehaviours.ViewGroupsBehavior;
import web.serverRequests.AddGroupRequest;
import web.serverRequests.AddStudentsToGroupRequest;
import web.serverRequests.GetStudentsInGroupRequest;
import web.serverRequests.ServerRequest;
import web.serverRequests.ViewGroupsRequest;
import web.serverResponse.GetStudentsInGroupResponse;
import web.serverResponse.ServerResponse;
import web.serverResponse.ViewGroupsResponse;
import controller.AsyncTaskController.PostExecutioner;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;
import core.GroupInfo;
import dbc.DBC;

/**
 * The Class AllGroupsController.
 */
public class AllGroupsController implements GroupInfoGetter
{
	/* fields */
	/** The refreshable. */
	Refreshable refreshable;
	
	/** The group infos. */
	ArrayList<GroupInfo> groupInfos;
	
	/** The current task. */
	private AsyncTask<ServerRequest, Object, ServerResponse> currentTask;

	/* Constructor */
	/**
	 * Instantiates a new all groups controller.
	 *
	 * @param refreshable the refreshable
	 */
	public AllGroupsController(Refreshable refreshable)
	{
		this.refreshable = refreshable;
		this.groupInfos = new ArrayList<>();
	}

	/* setters and getters */
	/**
	 * Gets the current tast.
	 *
	 * @return the current tast
	 */
	public AsyncTask<ServerRequest, Object, ServerResponse> getCurrentTast()
	{
		return currentTask;
	}

	/**
	 * Gets the group count.
	 *
	 * @return the group count
	 */
	public int getGroupCount()
	{
		return groupInfos.size();
	}

	/* (non-Javadoc)
	 * @see ui.adapters.GroupsListAdapter.GroupInfoGetter#getGroupsCount()
	 */
	@Override
	public int getGroupsCount()
	{
		return groupInfos.size();
	}

	/* (non-Javadoc)
	 * @see ui.adapters.GroupsListAdapter.GroupInfoGetter#getGroupName(int)
	 */
	@Override
	public String getGroupName(int position)
	{
		return groupInfos.get(position).getGroupName();
	}

	/* (non-Javadoc)
	 * @see ui.adapters.GroupsListAdapter.GroupInfoGetter#professorFullName(int)
	 */
	@Override
	public String professorFullName(int position)
	{
		return groupInfos.get(position).getProfessorInfo().getFullName();
	}

	/* (non-Javadoc)
	 * @see ui.adapters.GroupsListAdapter.GroupInfoGetter#getProfessorMail(int)
	 */
	@Override
	public String getProfessorMail(int position)
	{
		return groupInfos.get(position).getProfessorInfo().getMail();
	}

	/**
	 * Gets the group info.
	 *
	 * @param idx            requires a number from 0 to size of group infos - 1
	 * @return the group info
	 */
	public GroupInfo getGroupInfo(int idx)
	{
		// DBC.require(idx >= 0 && idx < groupInfos.size());

		return groupInfos.get(idx);
	}

	/* web methods */
	/**
	 * Asks the server to download the group infos ensures groupinfos is not
	 * null.
	 *
	 * @param context the context
	 */
	public void refreshGroups(final Context context)
	{
		// make the request and behavior
		ViewGroupsRequest request = new ViewGroupsRequest();
		ViewGroupsBehavior behavior = new ViewGroupsBehavior();

		// make the pre executioner
		PostExecutioner postExecutioner = new PostExecutioner()
		{
			@Override
			public void onPostExecution(ServerResponse serverResponse)
			{
				ViewGroupsResponse response = (ViewGroupsResponse) serverResponse;
				if (response.getStatus() == ServerResponse.STATUS_SUCCESSFUL)
				{
					groupInfos = response.getGroupInfos();
					// DBC.ensure(groupInfos != null);
					refreshable.refreshUI();

				} else
					Toast.makeText(context, response.getStatus(), Toast.LENGTH_SHORT).show();
			}
		};

		// make the task
		AsyncTask<ServerRequest, Object, ServerResponse> task = AsyncTaskController.getAsyncTask(
				request, behavior, postExecutioner, context);
		task.execute(request);
		currentTask = task;
		// DBC.ensure(groupInfos != null);
	}

	/**
	 * Asks the server to add a group.
	 *
	 * @param context the context
	 * @param groupName the group name
	 * @param description the description
	 * @param postExecutioner the post executioner
	 */
	public void addGroup(Context context, String groupName, String description,
			PostExecutioner postExecutioner)
	{
		// set the request and the behavior
		AddGroupBehaviour behavior = new AddGroupBehaviour();
		AddGroupRequest request = new AddGroupRequest(groupName, description);

		// make the task
		AsyncTask<ServerRequest, Object, ServerResponse> asyncTask = AsyncTaskController
				.getAsyncTask(request, behavior, postExecutioner, context);
		asyncTask.execute(request);
	}

	/**
	 * asks server to add students to a certain group.
	 *
	 * @param context the context
	 * @param postExecutioner the post executioner
	 * @param groupId the group id
	 * @param userName the user name
	 * @param email the email
	 * @param department the department
	 * @param graduationYear the graduation year
	 * @param section the section
	 */
	public void addStudentsToGroup(Context context, PostExecutioner postExecutioner,
			String groupId, String userName, String email, String department,
			String graduationYear, String section)
	{
		// make request and behaviour
		AddStudentsToGroupRequest request = new AddStudentsToGroupRequest(groupId, userName, email,
				"", department, graduationYear, section);
		AddStudentsToGroupBehavior addStudents = new AddStudentsToGroupBehavior();

		// make async task
		AsyncTask<ServerRequest, Object, ServerResponse> task = AsyncTaskController.getAsyncTask(
				request, addStudents, postExecutioner, context);
		currentTask = task;

		// execute
		task.execute(request);
	}

}
