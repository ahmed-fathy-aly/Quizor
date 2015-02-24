package web.serverRequests;

// TODO: Auto-generated Javadoc
/**
 * The Class AddGroupRequest.
 */
public class AddGroupRequest extends ServerRequest
{
	/* fields */
	/** The group name. */
	String groupName;
	
	/** The group description. */
	String groupDescription;

	/* Constructors */
	/**
	 * Instantiates a new adds the group request.
	 *
	 * @param groupName the group name
	 * @param groupDescription the group description
	 */
	public AddGroupRequest(String groupName, String groupDescription)
	{
		this.groupName = groupName;
		this.groupDescription = groupDescription;
	}

	/**
	 * Instantiates a new adds the group request.
	 */
	public AddGroupRequest()
	{
	}

	/* setters and getters */
	/**
	 * Gets the group name.
	 *
	 * @return the group name
	 */
	public String getGroupName()
	{
		return groupName;
	}

	/**
	 * Sets the group name.
	 *
	 * @param groupName the new group name
	 */
	public void setGroupName(String groupName)
	{
		this.groupName = groupName;
	}

	/**
	 * Gets the group description.
	 *
	 * @return the group description
	 */
	public String getGroupDescription()
	{
		return groupDescription;
	}

	/**
	 * Sets the group description.
	 *
	 * @param groupDescription the new group description
	 */
	public void setGroupDescription(String groupDescription)
	{
		this.groupDescription = groupDescription;
	}

}
