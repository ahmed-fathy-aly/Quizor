package web.serverRequests;

// TODO: Auto-generated Javadoc
/**
 * the parameters send to the server to register.
 */
public class RegisterProfessorRequest extends ServerRequest
{
	/* fields */
	/** The user name. */
	private String userName;
	
	/** The password. */
	private String password;
	
	/** The password confirmation. */
	private String passwordConfirmation;
	
	/** The full name. */
	private String fullName;
	
	/** The mail. */
	private String mail;
	
	/* Constructors */
	/**
	 * Instantiates a new register professor request.
	 */
	public RegisterProfessorRequest()
	{
	}
	
	/**
	 * Instantiates a new register professor request.
	 *
	 * @param userName the user name
	 * @param password the password
	 * @param passwordConfirmation the password confirmation
	 * @param fullName the full name
	 * @param mail the mail
	 */
	public RegisterProfessorRequest(String userName, String password, String passwordConfirmation,
			String fullName, String mail)
	{
		this.userName = userName;
		this.password = password;
		this.passwordConfirmation = passwordConfirmation;
		this.fullName = fullName;
		this.mail = mail;
	}

	/* getters and setters */
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
	 * Gets the mail.
	 *
	 * @return the mail
	 */
	public String getMail()
	{
		return mail;
	}

	/**
	 * Sets the mail.
	 *
	 * @param mail the new mail
	 */
	public void setMail(String mail)
	{
		this.mail = mail;
	}


	
	
	
}
