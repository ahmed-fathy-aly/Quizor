package web.serverRequests;

// TODO: Auto-generated Javadoc
/**
 * The Class GetQuizStatisticsRequest.
 */
public class GetQuizStatisticsRequest extends ServerRequest
{
	/* fields */
	/** The quiz id. */
	String quizId;

	/* constructors */
	/**
	 * Instantiates a new gets the quiz statistics request.
	 *
	 * @param quizId the quiz id
	 */
	public GetQuizStatisticsRequest(String quizId)
	{
		super();
		this.quizId = quizId;
	}

	/**
	 * Instantiates a new gets the quiz statistics request.
	 */
	public GetQuizStatisticsRequest()
	{
		super();
	}

	/* getters and setters */
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
