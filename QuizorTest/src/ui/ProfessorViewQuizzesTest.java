package ui;

import ui.activities.ProfessorAddQuiz;
import ui.activities.ProfessorViewQuizzes;
import utils.ServerUtils;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;
import android.widget.Button;

import com.robotium.solo.Solo;
import com.team_egor.quizor.R;


public class ProfessorViewQuizzesTest extends ActivityInstrumentationTestCase2<ProfessorViewQuizzes>
{
	
	/* fields */
	Solo solo;
	ProfessorViewQuizzes activity;

	
protected void setUp() throws Exception
	{
		super.setUp();
		
		// clear server
		ServerUtils utils = ServerUtils.getInstance();
		utils.resetServer();
		
		// make intent
		Intent intent = new Intent();
		intent.putExtra(ProfessorViewQuizzes.TAG_GROUP_ID, utils.getDebugGroupId());
		setActivityIntent(intent);
		
		// init
		solo = new Solo(getInstrumentation());
		activity = getActivity();

	}

	public ProfessorViewQuizzesTest()
	{
		
		super("", ProfessorViewQuizzes.class);
	}


	public void testAddQuiz() throws Throwable
	{
		try
		{
			activity.controller.getCurrentTask().get();
			runTestOnUiThread(new Runnable()
			{

				@Override
				public void run()
				{
					Button b = (Button) activity.findViewById(R.id.buttonAddNewQuiz);
					b.performClick();
				}
			});
			solo.waitForActivity(ProfessorAddQuiz.class);
			 this.sendKeys(KeyEvent.KEYCODE_BACK);
			 this.sendKeys(KeyEvent.KEYCODE_BACK);

		} catch (Exception e)
		{
			fail();
			e.printStackTrace();
		}
	}

}
