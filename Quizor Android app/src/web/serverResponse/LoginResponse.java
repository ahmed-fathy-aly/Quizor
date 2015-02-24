package web.serverResponse;

// TODO: Auto-generated Javadoc
/**
 * 	the response of the server to a login request.
 */
public class LoginResponse extends ServerResponse
{
	
	/**
	 * The Enum UserType.
	 */
	public enum UserType{
		
		/** The professor. */
		PROFESSOR, 
 /** The student. */
 STUDENT
	}
	
	/* fields */
	/** The user type. */
	UserType userType;

	/* Constructors */
	/**
	 * Instantiates a new login response.
	 *
	 * @param userType the user type
	 */
	public LoginResponse(UserType userType)
	{
		this.userType = userType;
	}

	/**
	 * Instantiates a new login response.
	 */
	public LoginResponse()
	{
	}

	/* Getters and setters */
	/**
	 * Gets the user type.
	 *
	 * @return the user type
	 */
	public UserType getUserType()
	{
		return userType;
	}

	/**
	 * Sets the user type.
	 *
	 * @param userType the new user type
	 */
	public void setUserType(UserType userType)
	{
		this.userType = userType;
	}

}
