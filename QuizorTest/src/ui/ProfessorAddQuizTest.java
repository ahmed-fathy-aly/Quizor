package ui;

import ui.activities.ProfessorAddQuiz;
import utils.ServerUtils;
import web.serverResponse.ServerResponse;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.robotium.solo.Solo;
import com.team_egor.quizor.R;

public class ProfessorAddQuizTest extends ActivityInstrumentationTestCase2<ProfessorAddQuiz>
{
	/* fields */
	Solo solo;
	ProfessorAddQuiz activity;

	protected void setUp() throws Exception
	{
		super.setUp();

		// clear server
		ServerUtils utils = ServerUtils.getInstance();
		utils.resetServer();

		// make intent
		Intent intent = new Intent();
		intent.putExtra(ProfessorAddQuiz.TAG_GROUP_ID, utils.getDebugGroupId());
		setActivityIntent(intent);

		// init
		solo = new Solo(getInstrumentation());
		activity = getActivity();

	}

	public ProfessorAddQuizTest()
	{

		super("", ProfessorAddQuiz.class);
	}

	public void testAddQuiz() throws Throwable
	{

		runTestOnUiThread(new Runnable()
		{

			@Override
			public void run()
			{
				// set fields
				EditText quizNameEditText = (EditText) activity.findViewById(R.id.editTextQuizName);
				NumberPicker durationPicker = (NumberPicker) activity
						.findViewById(R.id.numberPickerQuizDuration);
				DatePicker datePicker = (DatePicker) activity.findViewById(R.id.datePickerQuizDate);
				quizNameEditText.setText("New quiz");

				// click add quiz
				Button addQuiz = (Button) activity.findViewById(R.id.buttonAddQuiz);
				addQuiz.performClick();
			}
		});
		

		// check correct adding
		assertTrue(solo.waitForText(ServerResponse.STATUS_SUCCESSFUL));
		 this.sendKeys(KeyEvent.KEYCODE_BACK);
		 this.sendKeys(KeyEvent.KEYCODE_BACK);

	}

}
