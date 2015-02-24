package web.serverResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class AnswerQuestionsResponse.
 */
public class AnswerQuestionsResponse extends ServerResponse
{
	/* fields */
	/** The message. */
	String message;

	/* Constructors */
	/**
	 * Instantiates a new answer questions response.
	 *
	 * @param message the message
	 */
	public AnswerQuestionsResponse(String message)
	{
		super();
		this.message = message;
	}

	/**
	 * Instantiates a new answer questions response.
	 */
	public AnswerQuestionsResponse()
	{
		super();
		this.message = "";
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
