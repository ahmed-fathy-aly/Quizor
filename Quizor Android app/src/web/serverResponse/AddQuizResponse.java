package web.serverResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class AddQuizResponse.
 */
public class AddQuizResponse extends ServerResponse
{
	/* fields */
	/** The quiz id. */
	String quizId;

	/* Constructors */
	/**
	 * Instantiates a new adds the quiz response.
	 *
	 * @param quizId the quiz id
	 */
	public AddQuizResponse(String quizId)
	{
		this.quizId = quizId;
	}

	/**
	 * Instantiates a new adds the quiz response.
	 */
	public AddQuizResponse()
	{
	}

	/* Setters and Getters */
	/**
	 * Gets the quiz id.
	 *
	 * @return the quiz id
	 */
	public String getQuizId()
	{
		return quizId;
	}

	/**
	 * Sets the quiz id.
	 *
	 * @param quizId the new quiz id
	 */
	public void setQuizId(String quizId)
	{
		this.quizId = quizId;
	}
	
	
	
	
}
