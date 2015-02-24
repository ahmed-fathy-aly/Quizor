package ui.fragments;

import ui.activities.LoginRegisterActivity;
import ui.activities.ProfessorViewGroups;
import ui.activities.StudentViewGroups;
import utils.PreferencesUtils;
import web.serverResponse.LoginResponse;
import web.serverResponse.ServerResponse;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.team_egor.quizor.R;

import controller.AsyncTaskController.PostExecutioner;
import core.ProfessorInfo;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginFragment.
 */
public class LoginFragment extends android.support.v4.app.Fragment
{
	/* fields */
	/** The root view. */
	View rootView;

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// initialize fields
		rootView = inflater.inflate(R.layout.fragment_login, container, false);

		// retrieve saved name and password
		restoreSavedUserInfo();

		// set button listeners
		Button loginButton = (Button) rootView.findViewById(R.id.buttonLogIn);
		loginButton.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				logIn();
			}
		});
		
		setRetainInstance(true);
		return rootView;
	}

	/**
	 * checks if a user name and password are stored.
	 */
	private void restoreSavedUserInfo()
	{
		// get the saved info
		String userName = PreferencesUtils.getUserName(getActivity());
		String password = PreferencesUtils.getPassword(getActivity());
		if (userName.equals(PreferencesUtils.NOT_FOUND)
				|| password.equals(PreferencesUtils.NOT_FOUND))
			return;

		// reference the views
		EditText userNameEditText = (EditText) rootView.findViewById(R.id.editTextUserName);
		EditText passwordEditText = (EditText) rootView.findViewById(R.id.editTextPassword);
		CheckBox rememberMe = (CheckBox) rootView.findViewById(R.id.checkBoxRememberMe);

		// set the values
		userNameEditText.setText(userName);
		passwordEditText.setText(password);
		rememberMe.setChecked(true);
	}

	/**
	 * Asks the server to log in.
	 */
	protected void logIn()
	{
		// reference the edit texts
		EditText userNameEditText = (EditText) rootView.findViewById(R.id.editTextUserName);
		EditText passwordEditText = (EditText) rootView.findViewById(R.id.editTextPassword);

		// get the strings
		final String userName = userNameEditText.getText().toString();
		final String password = passwordEditText.getText().toString();

		// make the post executioner
		PostExecutioner postExecutioner = new PostExecutioner()
		{
			@Override
			public void onPostExecution(ServerResponse serverResponse)
			{
				// make toast
				LoginResponse response = (LoginResponse) serverResponse;
				Toast.makeText(getActivity(), response.getStatus(), Toast.LENGTH_LONG).show();

				// handle response
				if (response.getStatus() == ServerResponse.STATUS_SUCCESSFUL)
				{
					// save the username and password
					CheckBox rememberMe = (CheckBox) getActivity().findViewById(
							R.id.checkBoxRememberMe);
					if (rememberMe.isChecked())
						PreferencesUtils.savedUserInfo(getActivity(), userName, password);
					else
						PreferencesUtils.clearUserInfo(getActivity());

					// go to next activity
					if (response.getUserType() == LoginResponse.UserType.PROFESSOR)
					{
						Intent intent = new Intent(rootView.getContext(),
								ProfessorViewGroups.class);
						startActivity(intent);
						getActivity().finish();
					} else
					{
						Intent intent = new Intent(rootView.getContext(),
								StudentViewGroups.class);
						startActivity(intent);
						getActivity().finish();
					}
				}
			}
		};

		// login
		LoginRegisterActivity parentActivity = (LoginRegisterActivity) getActivity();
		parentActivity.controller.login(getActivity(), userName, password, false, postExecutioner);

	}

}
