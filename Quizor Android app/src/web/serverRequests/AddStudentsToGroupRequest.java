package web.serverRequests;

// TODO: Auto-generated Javadoc
/**
 * The Class AddStudentsToGroupRequest.
 */
public class AddStudentsToGroupRequest extends ServerRequest
{
	/* fields */
	/** The group id. */
	String groupId;
	
	/** The user name. */
	String userName;
	
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

	/* Constructor */
	/**
	 * Instantiates a new adds the students to group request.
	 *
	 * @param groupId the group id
	 * @param userName the user name
	 * @param email the email
	 * @param fullName the full name
	 * @param department the department
	 * @param graduationYear the graduation year
	 * @param section the section
	 */
	public AddStudentsToGroupRequest(String groupId, String userName, String email,
			String fullName, String department, String graduationYear, String section)
	{
		super();
		this.groupId = groupId;
		this.userName = userName;
		this.email = email;
		this.fullName = fullName;
		this.department = department;
		this.graduationYear = graduationYear;
		this.section = section;
	}

	/**
	 * Instantiates a new adds the students to group request.
	 */
	public AddStudentsToGroupRequest()
	{
		super();
	}

	/* Setters and Getters */
	/**
	 * Gets the group id.
	 *
	 * @return the group id
	 */
	public String getGroupId()
	{
		return groupId;
	}

	/**
	 * Sets the group id.
	 *
	 * @param groupId the new group id
	 */
	public void setGroupId(String groupId)
	{
		this.groupId = groupId;
	}

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
