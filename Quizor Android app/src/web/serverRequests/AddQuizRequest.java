package web.serverRequests;

// TODO: Auto-generated Javadoc
/**
 * The Class AddQuizRequest.
 */
public class AddQuizRequest extends ServerRequest
{
	/* fields */
	/** The group id. */
	String groupId;
	
	/** The quiz name. */
	String quizName;
	
	/** The quiz duration. */
	long quizDuration;
	
	/** The quiz start date. */
	long quizStartDate;
	
	/* Constructors */
	/**
	 * Instantiates a new adds the quiz request.
	 *
	 * @param groupId the group id
	 * @param quizName the quiz name
	 * @param quizDuration the quiz duration
	 * @param quizStartDate the quiz start date
	 */
	public AddQuizRequest(String groupId, String quizName, long quizDuration, long quizStartDate)
	{
		this.groupId = groupId;
		this.quizName = quizName;
		this.quizDuration = quizDuration;
		this.quizStartDate = quizStartDate;
	}

	/**
	 * Instantiates a new adds the quiz request.
	 */
	public AddQuizRequest()
	{
	}

	/* setters and getters */
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
	 * Gets the quiz name.
	 *
	 * @return the quiz name
	 */
	public String getQuizName()
	{
		return quizName;
	}

	/**
	 * Sets the quiz name.
	 *
	 * @param quizName the new quiz name
	 */
	public void setQuizName(String quizName)
	{
		this.quizName = quizName;
	}

	/**
	 * Gets the quiz duration.
	 *
	 * @return the quiz duration
	 */
	public long getQuizDuration()
	{
		return quizDuration;
	}

	/**
	 * Sets the quiz duration.
	 *
	 * @param quizDuration the new quiz duration
	 */
	public void setQuizDuration(long quizDuration)
	{
		this.quizDuration = quizDuration;
	}

	/**
	 * Gets the quiz start date.
	 *
	 * @return the quiz start date
	 */
	public long getQuizStartDate()
	{
		return quizStartDate;
	}

	/**
	 * Sets the quiz start date.
	 *
	 * @param quizStartDate the new quiz start date
	 */
	public void setQuizStartDate(long quizStartDate)
	{
		this.quizStartDate = quizStartDate;
	}
	
	
}
