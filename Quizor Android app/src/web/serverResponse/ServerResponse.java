package web.serverResponse;

// TODO: Auto-generated Javadoc
/**
 * The general response of the server to the request.
 */
public abstract class ServerResponse
{

	/* constants */
	/** The Constant STATUS_SUCCESSFUL. */
	public static final String STATUS_SUCCESSFUL = "Succesful";
	
	/** The Constant STATUS_FAILED. */
	public static final String STATUS_FAILED = "An error has occured";
	
	/** The Constant STATUS_NO_CONECTION. */
	public static final String STATUS_NO_CONECTION = "No Internet Connection!";

	/* fields */
	/** The status. */
	private String status;

	/* setters and getters */
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus()
	{
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status)
	{
		this.status = status;
	}
	
	
	
}
