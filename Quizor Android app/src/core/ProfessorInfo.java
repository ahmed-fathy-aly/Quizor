package core;

import org.json.JSONObject;

import dbc.DBC;

// TODO: Auto-generated Javadoc
/**
 * models the professor user.
 *
 * @author ahmed fathy
 */
public class ProfessorInfo
{
	/* constans */
	/** The Constant WEB_ID_NOT_SET. */
	public static final String WEB_ID_NOT_SET = "web id not set";

	/* fields */
	/** The user name. */
	String userName;
	
	/** The full name. */
	String fullName;
	
	/** The mail. */
	String mail;
	
	/** The web id. */
	String webId;

	/* constructor */
	/**
	 * Instantiates a new professor info.
	 */
	public ProfessorInfo()
	{
	}

	/**
	 * Instantiates a new professor info.
	 *
	 * @param userName the user name
	 * @param fullName the full name
	 * @param mail the mail
	 * @param webId the web id
	 */
	public ProfessorInfo(String userName, String fullName, String mail, String webId)
	{
		this.userName = userName;
		this.fullName = fullName;
		this.mail = mail;
		this.webId = webId;
	}

	/* getters and setters */
	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName()
	{
		return userName;
	}

	/**
	 * Sets the user name.
	 *
	 * @param userName the new user name
	 */
	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	/**
	 * Gets the full name.
	 *
	 * @return the full name
	 */
	public String getFullName()
	{
		return fullName;
	}

	/**
	 * Sets the full name.
	 *
	 * @param fullName the new full name
	 */
	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}

	/**
	 * Gets the mail.
	 *
	 * @return the mail
	 */
	public String getMail()
	{
		return mail;
	}

	/**
	 * Sets the mail.
	 *
	 * @param mail the new mail
	 */
	public void setMail(String mail)
	{
		this.mail = mail;
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

	/* medthos */

	/**
	 * requires  string should have the feilds name, username, email.
	 *
	 * @param json the json
	 * @return null if the json is not correct
	 */
	public static ProfessorInfo praseFromJson(JSONObject json)
	{
		//DBC.require(json.has("name"));
		//DBC.require(json.has("username"));
		//DBC.require(json.has("email"));
		
		// parse fields
		ProfessorInfo professor = new ProfessorInfo();
		try
		{
			professor.setUserName(json.getString("username"));
			professor.setFullName(json.getString("name"));
			professor.setMail(json.getString("email"));
			professor.setWebId(ProfessorInfo.WEB_ID_NOT_SET);
		} catch (Exception e)
		{
			professor = null;
		}

		//DBC.ensure(professor != null);
		return professor;
	}

}
