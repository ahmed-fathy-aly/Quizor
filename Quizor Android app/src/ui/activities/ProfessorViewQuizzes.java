package ui.activities;

import ui.adapters.QuizzesListAdapter;
import ui.adapters.QuizzesListAdapter.QuizzesListListener;
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

import controller.GroupController;
import controller.Refreshable;

// TODO: Auto-generated Javadoc
/**
 * displays the quizzes inside the activity groupid and group name must be put
 * in the intent starting this.
 */
public class ProfessorViewQuizzes extends Activity implements QuizzesListListener, Refreshable
{
	/* constants */
	/** The Constant TAG_GROUP_ID. */
	public static final String TAG_GROUP_ID = "tagGroupID";
	
	/** The Constant TAG_GROUP_NAME. */
	public static final String TAG_GROUP_NAME = "tagGroupName";

	/* fields */
	/** The controller. */
	public GroupController controller;
	
	/** The list adapter. */
	QuizzesListAdapter listAdapter;
	
	/** The list view. */
	public ListView listView;
	
	/** The group name. */
	String groupName;
	
	/** The group id. */
	String groupId;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_quizzes_professor);

		// retrieve the group name and id
		groupId = getIntent().getStringExtra(TAG_GROUP_ID);
		groupName = getIntent().getStringExtra(TAG_GROUP_NAME);
		setTitle(groupName);

		// set up the list view
		controller = new GroupController(this);
		listView = (ListView) findViewById(R.id.quizzesListView);
		listAdapter = new QuizzesListAdapter(this, controller, this);
		registerForContextMenu(listView);
		listView.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				onQuizClicked(position);

			}
		});

		// add the header
		RelativeLayout header = (RelativeLayout) getLayoutInflater().inflate(
				R.layout.quizzes_list_header_prof, null);
		listView.addHeaderView(header);
		Button addQuizButton = (Button) header.findViewById(R.id.buttonAddNewQuiz);
		addQuizButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				addQuiz();
			}
		});
		listView.setAdapter(listAdapter);

		// refresh list
		refreshList();
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onStart()
	 */
	@Override
	protected void onStart()
	{
		super.onStart();
		controller.refreshQuizzes(this, groupId);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_quizzes, menu);
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

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateContextMenu(android.view.ContextMenu, android.view.View, android.view.ContextMenu.ContextMenuInfo)
	 */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo)
	{
		super.onCreateContextMenu(menu, v, menuInfo);
		AdapterView.AdapterContextMenuInfo acmi = (AdapterContextMenuInfo) menuInfo;
		int idx = listView.getPositionForView(v) + 1;
		menu.setHeaderTitle(controller.getQuizInfo(idx).getQuizName());
		menu.add(0, v.getId(), 0, "Edit Questions");
		menu.add(0, v.getId(), 0, "View Results");

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

		if (item.getTitle().equals("Edit Questions"))
		{
			onQuizClicked(position + 1);
			return true;
		} else if (item.getTitle().equals("View Results"))
		{
			// open the view resultsactivity
			Intent intent = new Intent(this, ProfesorViewQuizStatistics.class);
			intent.putExtra(ProfesorViewQuizStatistics.TAG_QUIZ_ID, controller
					.getQuizInfo(position).getWebId());
			startActivity(intent);
			return true;
		}

		return super.onContextItemSelected(item);
	}

	/**
	 * Downloads the list of quiz info and refresh the list.
	 */
	private void refreshList()
	{

	}

	/**
	 * open the add quiz activity.
	 */
	protected void addQuiz()
	{
		// open the add group activity
		Intent intent = new Intent(this, ProfessorAddQuiz.class);
		intent.putExtra(ProfessorAddQuiz.TAG_GROUP_ID, groupId);
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

	/* (non-Javadoc)
	 * @see ui.adapters.QuizzesListAdapter.QuizzesListListener#onQuizSettingsClicked(int, android.view.View)
	 */
	@Override
	public void onQuizSettingsClicked(int position, View v)
	{
		openContextMenu(v);
	}

	/**
	 * On quiz clicked.
	 *
	 * @param position the position
	 */
	protected void onQuizClicked(int position)
	{

		if (position == 0)
			return;
		
		// go to edit questions activity
		position--;
		Intent intent = new Intent(this, ProfessorEditQuizQuestions.class);
		intent.putExtra(ProfessorEditQuizQuestions.TAG_QUIZ_ID, controller.getQuizId(position));
		startActivity(intent);

	}
}
