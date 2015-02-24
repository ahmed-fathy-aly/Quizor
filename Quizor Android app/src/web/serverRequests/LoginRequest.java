package web.serverRequests;

// TODO: Auto-generated Javadoc
/**
 * The parameters sent to the server to login.
 */
public class LoginRequest extends ServerRequest
{
	/* fields */
	/** The user name. */
	String userName;
	
	/** The password. */
	String password;
	
	/** The remember me. */
	String rememberMe;
	
	String gmcId;

	/* Constructors */
	/**
	 * Instantiates a new login request.
	 *
	 * @param userName the user name
	 * @param password the password
	 * @param rememberMe the remember me
	 */
	public LoginRequest(String userName, String password, String rememberMe)
	{
		this.userName = userName;
		this.password = password;
		this.rememberMe = rememberMe;
		this.gmcId = "";
	}

	/**
	 * Instantiates a new login request.
	 */
	public LoginRequest()
	{
		this.userName = "";
		this.password = "";
		this.rememberMe = "";
		this.gmcId = "";
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
	 * Gets the remember me.
	 *
	 * @return the remember me
	 */
	public String getRememberMe()
	{
		return rememberMe;
	}

	/**
	 * Sets the remember me.
	 *
	 * @param rememberMe the new remember me
	 */
	public void setRememberMe(String rememberMe)
	{
		this.rememberMe = rememberMe;
	}

	public String getGCMId()
	{
		return gmcId;
	}
	

	public void setGCmId(String deviceId)
	{
		this.gmcId = deviceId;
	}

	
}
