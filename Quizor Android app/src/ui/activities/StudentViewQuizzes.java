package ui.activities;

import ui.adapters.QuizzesListAdapter;
import ui.adapters.QuizzesListAdapter.QuizzesListListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.team_egor.quizor.R;

import controller.GroupController;
import controller.Refreshable;

// TODO: Auto-generated Javadoc
/**
 * The Class StudentViewQuizzes.
 */
public class StudentViewQuizzes extends Activity implements Refreshable, QuizzesListListener
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
		setContentView(R.layout.activity_view_quizzes_student);

		Log.e("Game", "on create");
		// retrieve the group name and id
		groupId = getIntent().getStringExtra(TAG_GROUP_ID);
		groupName = getIntent().getStringExtra(TAG_GROUP_NAME);
		setTitle(groupName);

		// set up the list view
		controller = new GroupController(this);
		listView = (ListView) findViewById(R.id.quizzesListView);
		listAdapter = new QuizzesListAdapter(this, controller, this);
		registerForContextMenu(listView);

		// add the header
		RelativeLayout header = (RelativeLayout) getLayoutInflater().inflate(
				R.layout.quizzes_list_header, null);
		listView.addHeaderView(header);
		listView.setAdapter(listAdapter);

		// add listener
		listView.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				onQuizClicked(position);
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
		controller.refreshQuizzes(this, groupId);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_quizzes_student, menu);
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
		menu.add(0, v.getId(), 0, "Start Quiz");

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

		if (item.getTitle().equals("Start Quiz"))
		{
			onQuizClicked(position + 1);
			return true;
		}

		return super.onContextItemSelected(item);
	}

	/* quizzes list stuff */

	/**
	 * On quiz clicked.
	 *
	 * @param position the position
	 */
	protected void onQuizClicked(final int position)
	{

		if (position == 0)
			return;
		
		// make a dialog
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

		// set dialog message
		alertDialogBuilder.setMessage("Start the Quiz?").setCancelable(true).setPositiveButton(
				"Yes", new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int id)
					{
						// start answering quiz
						int newPos = position - 1;
						String quizName = controller.getQuizInfo(newPos).getQuizName();
						String quizId = controller.getQuizInfo(newPos).getWebId();
						Intent intent = new Intent(StudentViewQuizzes.this,
								StudentAnswersQuiz.class);
						intent.putExtra(StudentAnswersQuiz.TAG_QUIZ_ID, quizId);
						intent.putExtra(StudentAnswersQuiz.TAG_QUIZ_NAME, quizName);
						startActivity(intent);
					}
				}).setNegativeButton("Cancel", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int id)
			{
				// close the dialog
				dialog.cancel();
			}
		});

		// create and show the dialog
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();

	}

	/* (non-Javadoc)
	 * @see ui.adapters.QuizzesListAdapter.QuizzesListListener#onQuizSettingsClicked(int, android.view.View)
	 */
	@Override
	public void onQuizSettingsClicked(int position, View v)
	{
		openContextMenu(v);
	}

	/* quizzes controlelr stuff */

	/* (non-Javadoc)
	 * @see controller.Refreshable#refreshUI()
	 */
	@Override
	public void refreshUI()
	{
		listAdapter.notifyDataSetChanged();
	}
}
