package web.serverRequests;

// TODO: Auto-generated Javadoc
/**
 * The Class RegisterStudentRequest.
 */
public class RegisterStudentRequest extends ServerRequest
{
	/* fields */
	/** The user name. */
	String userName;
	
	/** The password. */
	String password;
	
	/** The password confirmation. */
	String passwordConfirmation;
	
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
	
	/* constructors */
	/**
	 * Instantiates a new register student request.
	 *
	 * @param userName the user name
	 * @param password the password
	 * @param passwordConfirmation the password confirmation
	 * @param email the email
	 * @param fullName the full name
	 * @param department the department
	 * @param graduationYear the graduation year
	 * @param section the section
	 */
	public RegisterStudentRequest(String userName, String password, String passwordConfirmation,
			String email, String fullName, String department, String graduationYear, String section)
	{
		super();
		this.userName = userName;
		this.password = password;
		this.passwordConfirmation = passwordConfirmation;
		this.email = email;
		this.fullName = fullName;
		this.department = department;
		this.graduationYear = graduationYear;
		this.section = section;
	}

	/**
	 * Instantiates a new register student request.
	 */
	public RegisterStudentRequest()
	{
		super();
	}

	/* Setters and Getters */
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
	 * Gets the password confirmation.
	 *
	 * @return the password confirmation
	 */
	public String getPasswordConfirmation()
	{
		return passwordConfirmation;
	}

	/**
	 * Sets the password confirmation.
	 *
	 * @param passwordConfirmation the new password confirmation
	 */
	public void setPasswordConfirmation(String passwordConfirmation)
	{
		this.passwordConfirmation = passwordConfirmation;
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
	
	
}
