package core;

import org.json.JSONObject;

import dbc.DBC;

// TODO: Auto-generated Javadoc
/**
 * The Class GroupInfo.
 */
public class GroupInfo
{

	/* fields */
	/** The group name. */
	String groupName;
	
	/** The description. */
	String description;
	
	/** The professor info. */
	ProfessorInfo professorInfo;
	
	/** The web id. */
	String webId;

	/**
	 * Constructor.
	 */
	public GroupInfo()
	{
	}

	/**
	 * Instantiates a new group info.
	 *
	 * @param groupName the group name
	 * @param description the description
	 * @param professorInfo the professor info
	 * @param webId the web id
	 */
	public GroupInfo(String groupName, String description, ProfessorInfo professorInfo, String webId)
	{
		this.groupName = groupName;
		this.description = description;
		this.professorInfo = professorInfo;
		this.webId = webId;
	}

	/* getters and setters */

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
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * Gets the professor info.
	 *
	 * @return the professor info
	 */
	public ProfessorInfo getProfessorInfo()
	{
		return professorInfo;
	}

	/**
	 * Sets the professor info.
	 *
	 * @param professorInfo the new professor info
	 */
	public void setProfessorInfo(ProfessorInfo professorInfo)
	{
		this.professorInfo = professorInfo;
	}

	/**
	 * Gets the web id.
	 *
	 * @return the web id
	 */
	public String getWebId()
	{
		return webId;
	}

	/**
	 * Sets the web id.
	 *
	 * @param webId the new web id
	 */
	public void setWebId(String webId)
	{
		this.webId = webId;
	}

	/* methods */

	/**
	 * Parses the from json.
	 *
	 * @param json            requires to have fileds name, id, description, professor_info
	 * @return null if parsing failed
	 */
	public static GroupInfo parseFromJson(JSONObject json)
	{
		//DBC.require(json.has("name"));
		//DBC.require(json.has("id"));
		//DBC.require(json.has("description"));
		//DBC.require(json.has("professor_info"));
		
		GroupInfo group = new GroupInfo();
		try
		{
			group.setWebId(json.getString("id"));
			group.setGroupName(json.getString("name"));
			group.setDescription(json.getString("description"));
			group.setProfessorInfo(ProfessorInfo
					.praseFromJson(json.getJSONObject("professor_info")));
		} catch (Exception e)
		{
			group = null;
		}
		
		//DBC.ensure(group != null);
		return group;
	}

}
