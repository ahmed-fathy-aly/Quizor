package core;

import org.json.JSONException;
import org.json.JSONObject;

import dbc.DBC;

// TODO: Auto-generated Javadoc
/**
 * The Class SolutionStatistics.
 */
public class SolutionStatistics
{
	/* fields */
	/** The score. */
	String score;
	
	/** The user name. */
	String userName;
	
	/** The minutes late. */
	String minutesLate;

	/* Constructors */
	/**
	 * Instantiates a new solution statistics.
	 *
	 * @param score the score
	 * @param userName the user name
	 * @param minutesLate the minutes late
	 */
	public SolutionStatistics(String score, String userName, String minutesLate)
	{
		super();
		this.score = score;
		this.userName = userName;
		this.minutesLate = minutesLate;
	}

	/**
	 * Instantiates a new solution statistics.
	 */
	public SolutionStatistics()
	{
		super();
		score = "";
		userName = "";
		minutesLate = "";
	}

	/* setters and getters */
	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public String getScore()
	{
		return score;
	}

	/**
	 * Sets the score.
	 *
	 * @param score the new score
	 */
	public void setScore(String score)
	{
		this.score = score;
	}

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
	 * Gets the minutes late.
	 *
	 * @return the minutes late
	 */
	public String getMinutesLate()
	{
		return minutesLate;
	}

	/**
	 * Sets the minutes late.
	 *
	 * @param minutesLate the new minutes late
	 */
	public void setMinutesLate(String minutesLate)
	{
		this.minutesLate = minutesLate;
	}

	/**
	 * Parses the from json.
	 *
	 * @param json            requires fields student_username, total_score, late
	 * @return the solution statistics
	 */
	public static SolutionStatistics parseFromJSON(JSONObject json)
	{
		//DBC.require(json.has("student_username"));
		//DBC.require(json.has("total_score"));
		//DBC.require(json.has("late"));

		SolutionStatistics sol = new SolutionStatistics();

		try
		{
			sol.setUserName(json.getString("student_username"));
			sol.setScore(json.getString("total_score"));
			sol.setMinutesLate(json.getString("late"));
		} catch (JSONException e)
		{
			sol = null;
		}

		return sol;
	}

}
