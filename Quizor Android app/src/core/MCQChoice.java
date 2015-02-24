package core;

import org.json.JSONException;
import org.json.JSONObject;

import dbc.DBC;

// TODO: Auto-generated Javadoc
/**
 * the choice of the MCQ.
 */
public class MCQChoice
{
	/* fields */
	/** The body. */
	String body;
	
	/** The is checked. */
	boolean isChecked;

	/* constructors */

	/**
	 * Instantiates a new MCQ choice.
	 */
	public MCQChoice()
	{
		body = "";
	}

	/**
	 * Instantiates a new MCQ choice.
	 *
	 * @param body the body
	 * @param isCorrect the is correct
	 */
	public MCQChoice(String body, boolean isCorrect)
	{
		this.body = body;
		this.isChecked = isCorrect;
	}

	/* Setters and Getters */
	/**
	 * Gets the body.
	 *
	 * @return the body
	 */
	public String getBody()
	{
		return body;
	}

	/**
	 * Sets the body.
	 *
	 * @param body the new body
	 */
	public void setBody(String body)
	{
		this.body = body;
	}

	/**
	 * Checks if is checked.
	 *
	 * @return true, if is checked
	 */
	public boolean isChecked()
	{
		return isChecked;
	}

	/**
	 * Sets the checked.
	 *
	 * @param isCorrect the new checked
	 */
	public void setChecked(boolean isCorrect)
	{
		this.isChecked = isCorrect;
	}

	
	/* Methods */
	
	/**
	 * Gets the json.
	 *
	 * @return ensures a json with fields  name, correct
	 */
	public JSONObject getJSON()
	{
		JSONObject json;
		try
		{
			json = new JSONObject();
			json.put("name", body);
			json.put("correct", isChecked);

		} catch (JSONException e)
		{
			json = null;
		}
		
		//DBC.ensure(json.has("name") || body == null);
		//DBC.ensure(json.has("correct"));
		return json;
	}

}
