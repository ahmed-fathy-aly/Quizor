package web.serverRequests;

// TODO: Auto-generated Javadoc
/**
 * The Class ViewQuizzesRequest.
 */
public class ViewQuizzesRequest extends ServerRequest
{
	/* fields */
	/** The group id. */
	String groupId;

	/* Constructors */
	/**
	 * Instantiates a new view quizzes request.
	 *
	 * @param groupId the group id
	 */
	public ViewQuizzesRequest(String groupId)
	{
		this.groupId = groupId;
	}

	/**
	 * Instantiates a new view quizzes request.
	 */
	public ViewQuizzesRequest()
	{
	}

	/* Getters and Setters */

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
	
	
}
