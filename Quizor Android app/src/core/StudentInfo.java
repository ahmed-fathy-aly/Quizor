package core;

import org.json.JSONException;
import org.json.JSONObject;

import dbc.DBC;

// TODO: Auto-generated Javadoc
/**
 * The Class StudentInfo.
 */
public class StudentInfo
{
	/* fields */
	/** The user name. */
	String userName;
	
	/** The password. */
	String password;
	
	/** The email. */
	String email;
	
	/** The full name. */
	String fullName;
	
	/** The department. */
	String department;
	
	/** The graduation year. */
	String graduationYear;
	
	/** The section. */
	String section;
	
	/** The web id. */
	String webId;

	/* constructors */
	/**
	 * Instantiates a new student info.
	 *
	 * @param userName the user name
	 * @param password the password
	 * @param email the email
	 * @param fullName the full name
	 * @param department the department
	 * @param graduationYear the graduation year
	 * @param section the section
	 * @param webId the web id
	 */
	public StudentInfo(String userName, String password, String email, String fullName,
			String department, String graduationYear, String section, String webId)
	{
		super();
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.fullName = fullName;
		this.department = department;
		this.graduationYear = graduationYear;
		this.section = section;
		this.webId = webId;
	}

	/**
	 * Instantiates a new student info.
	 */
	public StudentInfo()
	{
		super();
		this.userName = "";
		this.password = "";
		this.email = "";
		this.fullName = "";
		this.department = "";
		this.graduationYear = "";
		this.section = "";
		this.webId = "";
	}

	/* setters and getters */
	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName()
	{
		return userName;
	}

	/**
	 * Sets the user name.
	 *
	 * @param userName the new user name
	 */
	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * Gets the full name.
	 *
	 * @return the full name
	 */
	public String getFullName()
	{
		return fullName;
	}

	/**
	 * Sets the full name.
	 *
	 * @param fullName the new full name
	 */
	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}

	/**
	 * Gets the department.
	 *
	 * @return the department
	 */
	public String getDepartment()
	{
		return department;
	}

	/**
	 * Sets the department.
	 *
	 * @param department the new department
	 */
	public void setDepartment(String department)
	{
		this.department = department;
	}

	/**
	 * Gets the graduation year.
	 *
	 * @return the graduation year
	 */
	public String getGraduationYear()
	{
		return graduationYear;
	}

	/**
	 * Sets the graduation year.
	 *
	 * @param graduationYear the new graduation year
	 */
	public void setGraduationYear(String graduationYear)
	{
		this.graduationYear = graduationYear;
	}

	/**
	 * Gets the section.
	 *
	 * @return the section
	 */
	public String getSection()
	{
		return section;
	}

	/**
	 * Sets the section.
	 *
	 * @param section the new section
	 */
	public void setSection(String section)
	{
		this.section = section;
	}

	/**
	 * Gets the web id.
	 *
	 * @return the web id
	 */
	public String getWebId()
	{
		return webId;
	}

	/**
	 * Sets the web id.
	 *
	 * @param webId the new web id
	 */
	public void setWebId(String webId)
	{
		this.webId = webId;
	}

	/* methods */
	/**
	 * ensures a non null student info object is returned.
	 *
	 * @param json            requires fields name, email, username, department_name,
	 *            year_number, section_number
	 * @return the student info
	 */
	public static StudentInfo parseFromJSON(JSONObject json)
	{
		//DBC.require(json.has("name"));
		//DBC.require(json.has("email"));
		//DBC.require(json.has("username"));
		//DBC.require(json.has("department_name"));
		//DBC.require(json.has("year_number"));
		//DBC.require(json.has("section_number"));

		StudentInfo studentInfo = new StudentInfo();
		try
		{
			studentInfo.setFullName(json.getString("name"));
			studentInfo.setEmail(json.getString("email"));
			studentInfo.setUserName(json.getString("username"));
			studentInfo.setDepartment(json.getString("department_name"));
			studentInfo.setGraduationYear(json.getString("year_number"));
			studentInfo.setSection(json.getString("section_number"));

		} catch (JSONException e)
		{
			studentInfo = null;
		}

		//DBC.ensure(studentInfo != null);
		return studentInfo;
	}

}
