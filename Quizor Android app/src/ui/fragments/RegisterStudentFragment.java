package ui.fragments;

import ui.activities.LoginRegisterActivity;
import web.serverResponse.RegisterStudentResponse;
import web.serverResponse.ServerResponse;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.team_egor.quizor.R;

import controller.AsyncTaskController.PostExecutioner;
import controller.UserController;

// TODO: Auto-generated Javadoc
/**
 * The Class RegisterStudentFragment.
 */
public class RegisterStudentFragment extends android.support.v4.app.Fragment
{
	/* fields */
	/** The root view. */
	View rootView;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater,
	 * android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{

		// init
		rootView = inflater.inflate(R.layout.fragment_register_student, container, false);

		// set buttons listeners
		Button registerButton = (Button) rootView.findViewById(R.id.buttonRegisterStudent);
		registerButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				registerUser();
			}
		});
		return rootView;
	}

	/**
	 * gets registration info and asks the server to register the user.
	 */
	protected void registerUser()
	{
		// reference the edit texts
		EditText userNameEditText = (EditText) rootView
				.findViewById(R.id.editTextUserNameRegisterStudent);
		EditText passwordEditText = (EditText) rootView
				.findViewById(R.id.editTextPasswordRegisterStudent);
		EditText passwordConfirmationEditText = (EditText) rootView
				.findViewById(R.id.editTextPasswordConfirmationStudent);
		EditText fullNameEditText = (EditText) rootView.findViewById(R.id.editTextFullNameStudent);
		EditText mailEditText = (EditText) rootView.findViewById(R.id.editTextMailStudent);
		EditText departmentEditText = (EditText) rootView
				.findViewById(R.id.editTextDepartmentStudent);
		EditText graduationYearEditText = (EditText) rootView
				.findViewById(R.id.editTextGraduationYearStudent);
		EditText sectionEditText = (EditText) rootView.findViewById(R.id.editTextSectionStudent);

		// get the strings
		final String userName = userNameEditText.getText().toString();
		final String password = passwordEditText.getText().toString();
		String passwordConfirmation = passwordConfirmationEditText.getText().toString();
		String fullName = fullNameEditText.getText().toString();
		String mail = mailEditText.getText().toString();
		String department = departmentEditText.getText().toString();
		String graduationYear = graduationYearEditText.getText().toString();
		String section = sectionEditText.getText().toString();

		// make the post executioner
		PostExecutioner postExecutioner = new PostExecutioner()
		{
			@Override
			public void onPostExecution(ServerResponse serverResponse)
			{
				// toast
				RegisterStudentResponse response = (RegisterStudentResponse) serverResponse;
				Toast.makeText(getActivity(), response.getStatus(), Toast.LENGTH_LONG).show();

				// log in
				if (response.getStatus().equals(ServerResponse.STATUS_SUCCESSFUL))
				{

					LoginRegisterActivity paernt = (LoginRegisterActivity) getActivity();
					paernt.fillLoginInfo(userName, password);
				}
			}
		};

		// register
		LoginRegisterActivity parentActivity = (LoginRegisterActivity) getActivity();
		parentActivity.controller.registerStudent(userName, password, passwordConfirmation, mail,
				fullName, department, graduationYear, section, postExecutioner);
	}

}
