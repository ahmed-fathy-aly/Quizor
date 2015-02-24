package core;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.json.JSONObject;

import dbc.DBC;

// TODO: Auto-generated Javadoc
/**
 * The Class QuizInfo.
 */
public class QuizInfo
{
	/* fields */
	/** The Quiz name. */
	String QuizName;
	
	/** The start date. */
	long startDate;
	
	/** The duration seconds. */
	int durationSeconds;
	
	/** The professor info. */
	ProfessorInfo professorInfo;
	
	/** The web id. */
	String webId;
	
	boolean isTaken;

	/**
	 * Constructor.
	 */
	public QuizInfo()
	{
		isTaken = false;
	}

	/**
	 * Constructor.
	 *
	 * @param quizName the quiz name
	 * @param avilabilityDate the avilability date
	 * @param durationSeconds the duration seconds
	 * @param professorInfo the professor info
	 */
	public QuizInfo(String quizName, long avilabilityDate, int durationSeconds,
			ProfessorInfo professorInfo)
	{
		super();
		QuizName = quizName;
		this.startDate = avilabilityDate;
		this.durationSeconds = durationSeconds;
		this.professorInfo = professorInfo;
		this.isTaken = false;
	}

	/* setters and getters */
	/**
	 * Gets the quiz name.
	 *
	 * @return the quiz name
	 */
	public String getQuizName()
	{
		return QuizName;
	}

	/**
	 * Sets the quiz name.
	 *
	 * @param quizName the new quiz name
	 */
	public void setQuizName(String quizName)
	{
		QuizName = quizName;
	}

	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public long getStartDate()
	{
		return startDate;
	}

	/**
	 * Sets the avilability date.
	 *
	 * @param avilabilityDate the new avilability date
	 */
	public void setAvilabilityDate(long avilabilityDate)
	{
		this.startDate = avilabilityDate;
	}

	/**
	 * Gets the duration seconds.
	 *
	 * @return the duration seconds
	 */
	public int getDurationSeconds()
	{
		return durationSeconds;
	}

	/**
	 * Sets the duration seconds.
	 *
	 * @param durationMilliseconds the new duration seconds
	 */
	public void setDurationSeconds(int durationMilliseconds)
	{
		this.durationSeconds = durationMilliseconds;
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

	public boolean isTaken()
	{
		return isTaken;
	}

	public void setTaken(boolean isTaken)
	{
		this.isTaken = isTaken;
	}
	
	/* methods */



	/**
	 * requires fields id, name, duration, unix_date, professor_info
	 * ensures a non null quiz info is returned.
	 *
	 * @param json the json
	 * @return the quiz info
	 */
	public static QuizInfo parseFromJson(JSONObject json)
	{
		//DBC.require(json.has("id"));
		//DBC.require(json.has("name"));
		//DBC.require(json.has("duration"));
		//DBC.require(json.has("unix_date"));
		//DBC.require(json.has("professor_info"));
		
		QuizInfo quizInfo = new  QuizInfo();
		try
		{
			quizInfo.setWebId(json.getString("id"));
			quizInfo.setQuizName(json.getString("name"));
			quizInfo.setTaken(json.getBoolean("taken"));
			quizInfo.setDurationSeconds(Integer.parseInt(json.getString("duration")));
			quizInfo.setAvilabilityDate(Long.parseLong(json.getString("unix_date")));
			quizInfo.setProfessorInfo(ProfessorInfo.praseFromJson(json.getJSONObject("professor_info")));
		} catch (Exception e)
		{
			quizInfo = null;
		}
		
		//DBC.ensure(quizInfo != null);
		return quizInfo;
	}

}
