package ui.activities;

import web.serverResponse.ServerResponse;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.team_egor.quizor.R;

import controller.AllGroupsController;
import controller.AsyncTaskController.PostExecutioner;
import controller.Refreshable;

// TODO: Auto-generated Javadoc
/**
 * The Class ProfessorAddGroup.
 */
public class ProfessorAddGroup extends Activity implements Refreshable
{
	/* fields */
	/** The controller. */
	public AllGroupsController controller;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		
		// initialize
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_group);
		controller = new AllGroupsController(this);

		// make listeners
		Button addGroupButton = (Button) findViewById(R.id.buttonAddGroup);
		addGroupButton.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				addGroup();
			}
		});
	}

	/**
	 * asks the server to add a new group.
	 */
	protected void addGroup()
	{
		// reference text views
		EditText editTextGroupname = (EditText) findViewById(R.id.editTextGroupName);
		EditText editTextGroupDescription = (EditText) findViewById(R.id.editTextGroupName);

		// get strings
		String groupName = editTextGroupname.getText().toString();
		String groupDescription = editTextGroupDescription.getText().toString();

		// make post executioner
		PostExecutioner postExecutioner = new PostExecutioner()
		{

			@Override
			public void onPostExecution(ServerResponse response)
			{
				// if correct response leave the activity
				Toast.makeText(ProfessorAddGroup.this, response.getStatus(), Toast.LENGTH_SHORT)
						.show();
				if (response.getStatus() == ServerResponse.STATUS_SUCCESSFUL)
					finish();
			}
		};

		// add group
		controller.addGroup(this, groupName, groupDescription, postExecutioner);

	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_group, menu);
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
	 * @see controller.Refreshable#refreshUI()
	 */
	@Override
	public void refreshUI()
	{

	}
}
