package web.serverRequests;

// TODO: Auto-generated Javadoc
/**
 * The Class GetQuizRequest.
 */
public class GetQuizRequest extends ServerRequest
{
	/* fields */
	/** The quiz id. */
	String quizId;

	/* Constructors */
	/**
	 * Instantiates a new gets the quiz request.
	 *
	 * @param quizId the quiz id
	 */
	public GetQuizRequest(String quizId)
	{
		super();
		this.quizId = quizId;
	}

	/**
	 * Instantiates a new gets the quiz request.
	 */
	public GetQuizRequest()
	{
		super();
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
