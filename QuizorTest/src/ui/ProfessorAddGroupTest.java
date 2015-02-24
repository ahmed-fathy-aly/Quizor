package ui;

import ui.activities.ProfessorAddGroup;
import utils.ServerUtils;
import web.serverResponse.ServerResponse;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;

import com.robotium.solo.Solo;
import com.team_egor.quizor.R;

public class ProfessorAddGroupTest extends ActivityInstrumentationTestCase2<ProfessorAddGroup>
{
	/* fields */
	Solo solo;
	ProfessorAddGroup activity;
	
	protected void setUp() throws Exception
	{
		super.setUp();

		// clear server
		ServerUtils utils = ServerUtils.getInstance();
		utils.resetServer();

		// make intent
		Intent intent = new Intent();
		setActivityIntent(intent);

		// init
		solo = new Solo(getInstrumentation());
		activity = getActivity();

	}

	public ProfessorAddGroupTest()
	{

		super("", ProfessorAddGroup.class);
	}

	public void testAddGroup() throws Throwable
	{

		runTestOnUiThread(new Runnable()
		{

			@Override
			public void run()
			{
				// set fields
				EditText editTextGroupname = (EditText) activity.findViewById(R.id.editTextGroupName);
				EditText editTextGroupDescription = (EditText) activity.findViewById(R.id.editTextGroupName);
				editTextGroupname.setText("my group");
				editTextGroupDescription.setText("bla bla");
				
				// click add quiz
				Button addGroup= (Button) activity.findViewById(R.id.buttonAddGroup);
				addGroup.performClick();
			}
		});
		

		// check correct adding
		assertTrue(solo.waitForText(ServerResponse.STATUS_SUCCESSFUL));
		 this.sendKeys(KeyEvent.KEYCODE_BACK);
		 this.sendKeys(KeyEvent.KEYCODE_BACK);

	}

}
