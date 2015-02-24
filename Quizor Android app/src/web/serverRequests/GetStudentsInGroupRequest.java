package web.serverRequests;

// TODO: Auto-generated Javadoc
/**
 * The Class GetStudentsInGroupRequest.
 */
public class GetStudentsInGroupRequest extends ServerRequest
{
	/* feilds */
	/** The group id. */
	String groupId;

	/* constructors */
	/**
	 * Instantiates a new gets the students in group request.
	 *
	 * @param groupId the group id
	 */
	public GetStudentsInGroupRequest(String groupId)
	{
		super();
		this.groupId = groupId;
	}

	/**
	 * Instantiates a new gets the students in group request.
	 */
	public GetStudentsInGroupRequest()
	{
		super();
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
	
	
}
