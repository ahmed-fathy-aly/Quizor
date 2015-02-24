package ui;

import ui.activities.LoginRegisterActivity;
import ui.activities.ProfessorViewGroups;
import utils.ServerUtils;
import web.serverResponse.ServerResponse;
import android.support.v4.view.ViewPager;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;

import com.robotium.solo.Solo;
import com.team_egor.quizor.R;

public class LoginRegisterProfessorActivity extends
		ActivityInstrumentationTestCase2<LoginRegisterActivity>
{

	/* fields */
	Solo solo;
	LoginRegisterActivity activity;
	ViewPager viewPager;

	public LoginRegisterProfessorActivity()
	{
		super("", LoginRegisterActivity.class);
	}

	@Override
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
		viewPager = (ViewPager) activity.findViewById(R.id.pager);

	}


	
	public void test() throws Throwable
	{

		// fill registration
		runTestOnUiThread(new Runnable()
		{

			@Override
			public void run()
			{
				// reference the edit texts and buttons
				EditText userNameEditText = (EditText) activity
						.findViewById(R.id.editTextUserNameRegister);
				EditText passwordEditText = (EditText) activity
						.findViewById(R.id.editTextPasswordRegister);
				EditText passwordConfirmationEditText = (EditText) activity
						.findViewById(R.id.editTextPasswordConfirmation);
				EditText fullNameEditText = (EditText) activity.findViewById(R.id.editTextFullName);
				EditText mailEditText = (EditText) activity.findViewById(R.id.editTextMail);
				Button registerButton = (Button) activity.findViewById(R.id.buttonRegister);

				viewPager.setCurrentItem(2);
				userNameEditText.setText("ahmed fathy");
				passwordEditText.setText("abc123");
				passwordConfirmationEditText.setText("abc123");
				fullNameEditText.setText("Ahmed fathy aly");
				mailEditText.setText("ahmed@eng.asu.edu.eg");
				registerButton.performClick();
			}
		});

		// register
		activity.controller.getCurrentTask().get();
		assertTrue(solo.waitForText(ServerResponse.STATUS_SUCCESSFUL));

		// fill login
		runTestOnUiThread(new Runnable()
		{

			@Override
			public void run()
			{
				Button loginButton = (Button) activity.findViewById(R.id.buttonLogIn);
				EditText userNameEditText = (EditText) activity.findViewById(R.id.editTextUserName);
				EditText passwordEditText = (EditText) activity.findViewById(R.id.editTextPassword);

				viewPager.setCurrentItem(1);
				userNameEditText.setText("ahmed fathy");
				passwordEditText.setText("abc123");
				loginButton.performClick();
			}
		});

		// login
		activity.controller.getCurrentTask().get();
		assertTrue(solo.waitForText(ServerResponse.STATUS_SUCCESSFUL));
		 this.sendKeys(KeyEvent.KEYCODE_BACK);
	}

}
