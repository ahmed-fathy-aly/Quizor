package ui;

import ui.activities.ProfessorAddGroup;
import ui.activities.ProfessorViewGroups;
import ui.activities.ProfessorViewQuizzes;
import utils.ServerUtils;
import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;
import android.widget.Button;

import com.robotium.solo.Solo;
import com.team_egor.quizor.R;

public class ProfesorViewGroupsTest extends ActivityInstrumentationTestCase2<ProfessorViewGroups>
{
	/* fields */
	Solo solo;
	ProfessorViewGroups activity;

	protected void setUp() throws Exception
	{
		super.setUp();

		// init
		solo = new Solo(getInstrumentation());

		// clear server
		ServerUtils utils = ServerUtils.getInstance();
		utils.resetServer();

		// make referencs
		activity = getActivity();

	}

	public ProfesorViewGroupsTest()
	{
		super("", ProfessorViewGroups.class);
	}

	public void testAddGroup()
	{
		try
		{
			runTestOnUiThread(new Runnable()
			{

				@Override
				public void run()
				{
					Button b = (Button) activity.findViewById(R.id.buttonAddNewGroup);
					b.performClick();
				}
			});
		} catch (Throwable e)
		{
			fail();
			e.printStackTrace();
		}
		assertTrue (solo.waitForActivity(ProfessorAddGroup.class));
		 this.sendKeys(KeyEvent.KEYCODE_BACK);
		 this.sendKeys(KeyEvent.KEYCODE_BACK);

	}

	public void testClickGroup() throws Throwable
	{
		try
		{
			activity.controller.getCurrentTast().get();
			runTestOnUiThread(new Runnable()
			{

				@Override
				public void run()
				{
					activity.listView.performItemClick(activity.listView, 1, activity.listView
							.getItemIdAtPosition(1));
				}
			});

			assertTrue (solo.waitForActivity(ProfessorViewQuizzes.class));
			 this.sendKeys(KeyEvent.KEYCODE_BACK);
			 this.sendKeys(KeyEvent.KEYCODE_BACK);

		} catch (Exception e)
		{

			fail();
			e.printStackTrace();
		}
	}

}
