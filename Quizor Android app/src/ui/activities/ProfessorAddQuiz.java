package ui.activities;

import web.serverResponse.ServerResponse;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.team_egor.quizor.R;

import controller.AsyncTaskController.PostExecutioner;
import controller.GroupController;
import controller.Refreshable;

// TODO: Auto-generated Javadoc
/**
 * The Class ProfessorAddQuiz.
 */
public class ProfessorAddQuiz extends Activity implements Refreshable
{
	/* constants */
	/** The Constant TAG_GROUP_ID. */
	public static final String TAG_GROUP_ID = "tagGroupId";

	/* fields */
	/** The group id. */
	private String groupId;
	
	/** The contoller. */
	public GroupController contoller;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// init
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_quiz);
		contoller = new GroupController(this);
		
		// get group id
		groupId = getIntent().getStringExtra(TAG_GROUP_ID);

		// set number picker
		NumberPicker durationPicker = (NumberPicker) findViewById(R.id.numberPickerQuizDuration);
		durationPicker.setMinValue(1);
		durationPicker.setMaxValue(60 * 24 * 7);
		durationPicker.setValue(30);

		// add on click listener
		Button addQuizButton = (Button) findViewById(R.id.buttonAddQuiz);
		addQuizButton.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				addQuiz();
			}
		});
	}

	/**
	 * asks the server to add a quiz.
	 */
	protected void addQuiz()
	{
		// refer to views
		EditText quizNameEditText = (EditText) findViewById(R.id.editTextQuizName);
		NumberPicker durationPicker = (NumberPicker) findViewById(R.id.numberPickerQuizDuration);
		DatePicker datePicker = (DatePicker) findViewById(R.id.datePickerQuizDate);

		// collect data
		String quizName = quizNameEditText.getText().toString();
		long quizDuration = durationPicker.getValue() * 60;
		int day = datePicker.getDayOfMonth();
		int month = datePicker.getMonth()+1;
		int year = datePicker.getYear();

		// make the post executioner
		PostExecutioner postExecutioner = new PostExecutioner()
		{

			@Override
			public void onPostExecution(ServerResponse response)
			{
				// if response ok then leave this activity
				Toast.makeText(ProfessorAddQuiz.this, response.getStatus(), Toast.LENGTH_SHORT)
						.show();
				if (response.getStatus().equals(ServerResponse.STATUS_SUCCESSFUL))
					finish();
			}
		};
		
		// ask the controller to add the quiz
		contoller.addQuiz(this, groupId,  quizName, quizDuration, day, month, year, postExecutioner);
		
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_quiz, menu);
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
