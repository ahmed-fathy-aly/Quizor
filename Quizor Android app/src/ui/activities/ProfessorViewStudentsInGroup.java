package ui.activities;

import ui.adapters.StudentsListAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.team_egor.quizor.R;

import controller.GroupController;
import controller.Refreshable;

// TODO: Auto-generated Javadoc
/**
 * The Class ProfessorViewStudentsInGroup.
 */
public class ProfessorViewStudentsInGroup extends Activity implements Refreshable
{
	/* constants */
	/** The Constant TAG_GROUP_NAME. */
	public static final String TAG_GROUP_NAME = "tagGroupName";
	
	/** The Constant TAG_GROUP_ID. */
	public static final String TAG_GROUP_ID = "tagGroupId";

	/* fields */
	/** The controller. */
	private GroupController controller;
	
	/** The group name. */
	private String groupName;
	
	/** The group id. */
	private String groupId;
	
	/** The list view. */
	ListView listView;
	
	/** The list adapter. */
	StudentsListAdapter listAdapter;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// init
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_students);
		controller = new GroupController(this);

		// restore from intent
		groupName = getIntent().getStringExtra(TAG_GROUP_NAME);
		groupId = getIntent().getStringExtra(TAG_GROUP_ID);

		// handle list view
		listView = (ListView) findViewById(R.id.studentsListView);
		listAdapter = new StudentsListAdapter(this, controller);
		listView.setAdapter(listAdapter);

		// download info
		controller.downloadStudentsInGroup(this, groupId);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_students, menu);
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

		return super.onOptionsItemSelected(item);
	}

	/* controller stuff */
	/* (non-Javadoc)
	 * @see controller.Refreshable#refreshUI()
	 */
	@Override
	public void refreshUI()
	{
		listAdapter.notifyDataSetChanged();
	}
}
