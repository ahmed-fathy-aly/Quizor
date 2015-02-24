package ui.activities;

import web.serverResponse.AddStudentsToGroupResponse;
import web.serverResponse.ServerResponse;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;

import com.team_egor.quizor.R;

import controller.AllGroupsController;
import controller.AsyncTaskController.PostExecutioner;
import controller.Refreshable;

// TODO: Auto-generated Javadoc
/**
 * The Class ProfessorAddStudentsToGroup.
 */
public class ProfessorAddStudentsToGroup extends Activity implements Refreshable
{
	/* constants */
	/** The Constant TAG_GROUP_ID. */
	public static final String TAG_GROUP_ID = "tagGroupID";
	
	/** The Constant TAG_GROUP_NAME. */
	public static final String TAG_GROUP_NAME = "tagGroupName";

	/* fields */
	/** The controller. */
	AllGroupsController controller;
	
	/** The group id. */
	String groupId;
	
	/** The group name. */
	String groupName;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// init
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_students);
		controller = new AllGroupsController(this);

		// restore extras
		groupName = getIntent().getStringExtra(TAG_GROUP_NAME);
		groupId = getIntent().getStringExtra(TAG_GROUP_ID);
		setTitle(groupName);

		// reference views
		CheckBox checkBoxuserName = (CheckBox) findViewById(R.id.checkBoxUserNameEnabled);
		EditText editTextUserName = (EditText) findViewById(R.id.editextStudentUserName);
		CheckBox checkBoxMail = (CheckBox) findViewById(R.id.checkBoxMailEnabled);
		EditText editTextMail = (EditText) findViewById(R.id.editextStudentMail);
		CheckBox checkBoxDepartment = (CheckBox) findViewById(R.id.checkBoxDepartmentEnabled);
		EditText editTextDepartment = (EditText) findViewById(R.id.editextStudentUserDepartment);
		CheckBox checkBoxGraduationYear = (CheckBox) findViewById(R.id.checkBoxGraduationYearEnabled);
		EditText editTextGraduationYear = (EditText) findViewById(R.id.editextStudentGraduationYear);
		CheckBox checkBoxSection = (CheckBox) findViewById(R.id.checkBoxSectionEnabled);
		EditText editTextSection = (EditText) findViewById(R.id.editextStudentSection);
		Button buttonAddStudents = (Button) findViewById(R.id.buttonAddStudent);

		// set listeners to check boxes
		setDependant(checkBoxuserName, editTextUserName);
		setDependant(checkBoxMail, editTextMail);
		setDependant(checkBoxDepartment, editTextDepartment);
		setDependant(checkBoxGraduationYear, editTextGraduationYear);
		setDependant(checkBoxSection, editTextSection);

		// set button listener
		buttonAddStudents.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				addStudents();
			}
		});
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_students, menu);
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

	/**
	 * makes the edit text enabled if the check box is checked.
	 *
	 * @param checkbox the checkbox
	 * @param editText the edit text
	 */
	private void setDependant(CheckBox checkbox, final EditText editText)
	{
		checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				editText.setEnabled(!isChecked);
			}
		});
	}

	/**
	 * ask the server to search and add students.
	 */
	protected void addStudents()
	{
		// reference views
		CheckBox checkBoxuserName = (CheckBox) findViewById(R.id.checkBoxUserNameEnabled);
		EditText editTextUserName = (EditText) findViewById(R.id.editextStudentUserName);
		CheckBox checkBoxMail = (CheckBox) findViewById(R.id.checkBoxMailEnabled);
		EditText editTextMail = (EditText) findViewById(R.id.editextStudentMail);
		CheckBox checkBoxDepartment = (CheckBox) findViewById(R.id.checkBoxDepartmentEnabled);
		EditText editTextDepartment = (EditText) findViewById(R.id.editextStudentUserDepartment);
		CheckBox checkBoxGraduationYear = (CheckBox) findViewById(R.id.checkBoxGraduationYearEnabled);
		EditText editTextGraduationYear = (EditText) findViewById(R.id.editextStudentGraduationYear);
		CheckBox checkBoxSection = (CheckBox) findViewById(R.id.checkBoxSectionEnabled);
		EditText editTextSection = (EditText) findViewById(R.id.editextStudentSection);
		CheckBox checkBoxFullName= (CheckBox) findViewById(R.id.checkBoxSectionEnabled);
		
		// get text
		String username = checkBoxuserName.isChecked() ? "" : editTextUserName.getText().toString();
		String mail = checkBoxMail.isChecked() ? "" : editTextMail.getText().toString();
		String department = checkBoxDepartment.isChecked() ? "" : editTextDepartment.getText()
				.toString();
		String graduationYear = checkBoxGraduationYear.isChecked() ? "" : editTextGraduationYear
				.getText().toString();
		String section = checkBoxSection.isChecked() ? "" : editTextSection.getText().toString();

		// make post executioner
		PostExecutioner postExecutioner = new PostExecutioner()
		{

			@Override
			public void onPostExecution(ServerResponse serverResponse)
			{
				AddStudentsToGroupResponse response = (AddStudentsToGroupResponse) serverResponse;
				if (response.getStatus() == ServerResponse.STATUS_SUCCESSFUL)
					Toast.makeText(ProfessorAddStudentsToGroup.this, response.getMessage(), Toast.LENGTH_SHORT)
						.show();
				else
					Toast.makeText(ProfessorAddStudentsToGroup.this, response.getStatus(), Toast.LENGTH_SHORT)
					.show();
			}
		};

		// ask controller
		controller.addStudentsToGroup(this, postExecutioner, groupId, username, mail, department,
				graduationYear, section);
	}

	/* (non-Javadoc)
	 * @see controller.Refreshable#refreshUI()
	 */
	@Override
	public void refreshUI()
	{
		// TODO Auto-generated method stub

	}

}
