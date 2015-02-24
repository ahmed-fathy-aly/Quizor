package web.serverResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class AddGroupResponse.
 */
public class AddGroupResponse extends ServerResponse
{
	/* fields */
	/** The group id. */
	String groupId;

	/* constructors */
	/**
	 * Instantiates a new adds the group response.
	 *
	 * @param groupId the group id
	 */
	public AddGroupResponse(String groupId)
	{
		this.groupId = groupId;
	}

	/**
	 * Instantiates a new adds the group response.
	 */
	public AddGroupResponse()
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

}
