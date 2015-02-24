package web.serverResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class AddStudentsToGroupResponse.
 */
public class AddStudentsToGroupResponse extends ServerResponse
{
	/* fields */
	/** The message. */
	String message;

	/* Constructors */
	/**
	 * Instantiates a new adds the students to group response.
	 *
	 * @param message the message
	 */
	public AddStudentsToGroupResponse(String message)
	{
		super();
		this.message = message;
	}
	
	/**
	 * Instantiates a new adds the students to group response.
	 */
	public AddStudentsToGroupResponse()
	{
		super();
	}

	/* Setters and Getters */
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage()
	{
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message)
	{
		this.message = message;
	}
	
	
}
