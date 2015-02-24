package ui.activities;

import ui.adapters.GroupsListAdapter;
import ui.adapters.GroupsListAdapter.GroupListListener;
import web.gcm.GCMUtils;
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
import android.widget.Toast;

import com.team_egor.quizor.R;

import controller.AllGroupsController;
import controller.Refreshable;

// TODO: Auto-generated Javadoc
/**
 * The Class StudentViewGroups.
 */
public class StudentViewGroups extends Activity implements Refreshable, GroupListListener
{
	/* constants */

	/* fields */
	/** The controller. */
	public AllGroupsController controller;

	/** The list adapter. */
	public GroupsListAdapter listAdapter;

	/** The list view. */
	public ListView listView;

	/* activity stuff */
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_groups_student);

		// set up the list view
		controller = new AllGroupsController(this);
		listView = (ListView) findViewById(R.id.groupsListView);
		listAdapter = new GroupsListAdapter(this, controller, this);
		registerForContextMenu(listView);

		// add the header
		RelativeLayout header = (RelativeLayout) getLayoutInflater().inflate(
				R.layout.groups_list_header, null);
		listView.addHeaderView(header);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onStart()
	 */
	@Override
	protected void onStart()
	{
		super.onStart();
		controller.refreshGroups(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_groups_student, menu);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreateContextMenu(android.view.ContextMenu,
	 * android.view.View, android.view.ContextMenu.ContextMenuInfo)
	 */
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo)
	{
		super.onCreateContextMenu(menu, v, menuInfo);
		AdapterView.AdapterContextMenuInfo acmi = (AdapterContextMenuInfo) menuInfo;
		int idx = listView.getPositionForView(v) + 1;
		menu.setHeaderTitle("Group Options");
		menu.add(0, v.getId(), 0, "View Quizzes");

	}

	/*
	 * (non-Javadoc)
	 * 
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
		}
		return super.onContextItemSelected(item);
	}

	/* stuff for groups controller */
	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.Refreshable#refreshUI()
	 */
	@Override
	public void refreshUI()
	{
		listAdapter.notifyDataSetChanged();
	}

	/* stuff for groups list */

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ui.adapters.GroupsListAdapter.GroupListListener#onGroupSettingClicked
	 * (int, android.view.View)
	 */
	@Override
	public void onGroupSettingClicked(int position, View v)
	{
		openContextMenu(v);
	}

	/**
	 * opens a group.
	 *
	 * @param position
	 *            the position
	 */
	protected void onGroupClicked(int position)
	{

		if (position == 0)
			return;
		// go to that group
		position--;
		Intent intent = new Intent(this, StudentViewQuizzes.class);
		String groupId = controller.getGroupInfo(position).getWebId();
		String groupName = controller.getGroupInfo(position).getGroupName();
		intent.putExtra(StudentViewQuizzes.TAG_GROUP_ID, groupId);
		intent.putExtra(StudentViewQuizzes.TAG_GROUP_NAME, groupName);
		startActivity(intent);
	}

}
