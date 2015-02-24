package web.serverResponse;

import java.util.ArrayList;

import core.GroupInfo;

// TODO: Auto-generated Javadoc
/**
 * The server response for viewing groups contains a list of group info.
 */
public class ViewGroupsResponse extends ServerResponse
{
	/* fields */
	/** The group infos. */
	ArrayList<GroupInfo> groupInfos;

	/**
	 * Constructor.
	 */
	public ViewGroupsResponse()
	{
	}

	/**
	 * Gets the group infos.
	 *
	 * @return the group infos
	 */
	public ArrayList<GroupInfo> getGroupInfos()
	{
		return groupInfos;
	}

	/**
	 * Sets the group infos.
	 *
	 * @param groupInfos the new group infos
	 */
	public void setGroupInfos(ArrayList<GroupInfo> groupInfos)
	{
		this.groupInfos = groupInfos;
	}

}
