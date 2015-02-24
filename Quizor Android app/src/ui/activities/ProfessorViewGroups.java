package ui.activities;

import ui.adapters.GroupsListAdapter;
import ui.adapters.GroupsListAdapter.GroupListListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.team_egor.quizor.R;

import controller.AllGroupsController;
import controller.Refreshable;

// TODO: Auto-generated Javadoc
/**
 * The Class ProfessorViewGroups.
 */
public class ProfessorViewGroups extends Activity implements Refreshable, GroupListListener
{
	/* constants */

	/* fields */
	/** The controller. */
	public AllGroupsController controller;
	
	/** The list adapter. */
	public GroupsListAdapter listAdapter;
	
	/** The list view. */
	public ListView listView;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_groups_professor);

		// set up the list view
		controller = new AllGroupsController(this);
		listView = (ListView) findViewById(R.id.groupsListView);
		listAdapter = new GroupsListAdapter(this, controller, this);
		registerForContextMenu(listView);

		// add the header
		RelativeLayout header = (RelativeLayout) getLayoutInflater().inflate(
				R.layout.groups_list_prof_header, null);
		listView.addHeaderView(header);
		Button addGroupButton = (Button) header.findViewById(R.id.buttonAddNewGroup);
		addGroupButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				addNewGroup();
			}
		});
		listView.setAdapter(listAdapter);

		// set the list click listener
		listView.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				onGroupClicked(position);

			}
		});

	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onStart()
	 */
	@Override
	protected void onStart()
	{
		super.onStart();
		controller.refreshGroups(this);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_groups_professor, menu);
		return true;
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.log_out)
		{
			// go to login register
			Intent intent = new Intent(this, LoginRegisterActivity.class);
			startActivity(intent);
			finish();
		}
		return super.onOptionsItemSelected(item);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateContextMenu(android.view.ContextMenu, android.view.View, android.view.ContextMenu.ContextMenuInfo)
	 */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo)
	{
		super.onCreateContextMenu(menu, v, menuInfo);
		AdapterView.AdapterContextMenuInfo acmi = (AdapterContextMenuInfo) menuInfo;
		int idx = listView.getPositionForView(v) + 1;
		menu.setHeaderTitle("Group options");
		menu.add(0, v.getId(), 0, "View Quizzes");

		menu.add(0, v.getId(), 0, "Add Students");
		menu.add(0, v.getId(), 0, "View Students");

	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onContextItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onContextItemSelected(MenuItem item)
	{
		// get position
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
				.getMenuInfo();
		int position = info.position - 1;

		if (item.getTitle().equals("View Quizzes"))
		{
			onGroupClicked(position + 1);
		} else if (item.getTitle().equals("Add Students"))
		{
			// open the add students activity
			Intent intent = new Intent(this, ProfessorAddStudentsToGroup.class);
			intent.putExtra(ProfessorViewQuizzes.TAG_GROUP_ID, controller.getGroupInfo(position)
					.getWebId());
			intent.putExtra(ProfessorViewQuizzes.TAG_GROUP_NAME, controller.getGroupInfo(position)
					.getGroupName());
			startActivity(intent);
			return true;
		} else if (item.getTitle().equals("View Students"))
		{
			// open the view students activity
			Intent intent = new Intent(this, ProfessorViewStudentsInGroup.class);
			intent.putExtra(ProfessorViewStudentsInGroup.TAG_GROUP_ID, controller.getGroupInfo(
					position).getWebId());
			intent.putExtra(ProfessorViewStudentsInGroup.TAG_GROUP_NAME, controller.getGroupInfo(
					position).getGroupName());
			startActivity(intent);
			return true;
		}
		return super.onContextItemSelected(item);
	}

	/* list stuff */
	/* (non-Javadoc)
	 * @see ui.adapters.GroupsListAdapter.GroupListListener#onGroupSettingClicked(int, android.view.View)
	 */
	@Override
	public void onGroupSettingClicked(int position, View v)
	{
		openContextMenu(v);

	}

	/**
	 * Opens the Add Group activity.
	 */
	protected void addNewGroup()
	{
		// open the add group activity
		Intent intent = new Intent(this, ProfessorAddGroup.class);
		startActivity(intent);
	}

	/* (non-Javadoc)
	 * @see controller.Refreshable#refreshUI()
	 */
	@Override
	public void refreshUI()
	{
		listAdapter.notifyDataSetChanged();
	}

	/**
	 * when a group from the list is clicked, open that group.
	 *
	 * @param position the position
	 */
	protected void onGroupClicked(int position)
	{
		if (position == 0)
			return;
		// go to the view quizzes activity
		Intent intent = new Intent(this, ProfessorViewQuizzes.class);

		// add the group and name
		position--;
		intent.putExtra(ProfessorViewQuizzes.TAG_GROUP_ID, controller.getGroupInfo(position)
				.getWebId());
		intent.putExtra(ProfessorViewQuizzes.TAG_GROUP_NAME, controller.getGroupInfo(position)
				.getGroupName());
		startActivity(intent);

	}

}
