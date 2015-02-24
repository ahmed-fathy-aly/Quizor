package core;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import dbc.DBC;

// TODO: Auto-generated Javadoc
/**
 * The Class MCQ.
 */
public class MCQ
{
	/* fields */
	/** The body. */
	String body;
	
	/** The choices. */
	ArrayList<MCQChoice> choices;
	
	/** The Web id. */
	String WebId;

	/* constructors */

	/**
	 * Instantiates a new mcq.
	 */
	public MCQ()
	{
		super();
		body = "";
		choices = new ArrayList<>();
	}

	/**
	 * Instantiates a new mcq.
	 *
	 * @param body the body
	 * @param choices the choices
	 */
	public MCQ(String body, ArrayList<MCQChoice> choices)
	{
		super();
		this.body = body;
		this.choices = choices;
	}

	/* getters and setters */

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
	 * Gets the choices.
	 *
	 * @return the choices
	 */
	public ArrayList<MCQChoice> getChoices()
	{
		return choices;
	}

	/**
	 * Sets the choices.
	 *
	 * @param choices the new choices
	 */
	public void setChoices(ArrayList<MCQChoice> choices)
	{
		this.choices = choices;
	}

	/**
	 * Gets the web id.
	 *
	 * @return the web id
	 */
	public String getWebId()
	{
		return WebId;
	}

	/**
	 * Sets the web id.
	 *
	 * @param webId the new web id
	 */
	public void setWebId(String webId)
	{
		WebId = webId;
	}

	/* methods */
	/**
	 * Adds the choice.
	 *
	 * @param choice the choice
	 */
	public void addChoice(MCQChoice choice)
	{
		choices.add(choice);
	}

	/**
	 * Removes the choice.
	 *
	 * @param idx the idx
	 */
	public void removeChoice(int idx)
	{
		choices.remove(idx);
	}

	/**
	 * Gets the json.
	 *
	 * @return ensures a json with fields body, answers_attributes
	 */
	public JSONObject getJSON()
	{
		JSONObject json;
		try
		{
			json = new JSONObject();
			json.put("body", body);
			JSONArray arr = new JSONArray();
			for (MCQChoice mcqChoice : choices)
				arr.put(mcqChoice.getJSON());
			json.put("answers_attributes", arr);
		} catch (JSONException e)
		{
			json = null;
		}

		//DBC.ensure(json.has("body") || body.length() == 0);
		//DBC.ensure(json.has("answers_attributes"));
		return json;
	}

	/**
	 * parses a json with fields id, body, choices.
	 *
	 * @param json the json
	 * @return the mcq
	 */
	public static MCQ parseFromJSON(JSONObject json)
	{
		//DBC.require(json.has("id"));
		//DBC.require(json.has("body"));
		//DBC.require(json.has("choices"));

		MCQ mcq;
		try
		{
			// fields
			mcq = new MCQ();
			mcq.setWebId(json.getString("id"));
			mcq.setBody(json.getString("body"));

			// choices
			JSONArray choicesJson = json.getJSONArray("choices");
			for (int i = 0; i < choicesJson.length(); i++)
			{
				String body = choicesJson.getString(i);
				mcq.getChoices().add(new MCQChoice(body, false));
			}

		} catch (JSONException e)
		{
			mcq = null;
		}

		//DBC.ensure(mcq != null);
		return mcq;
	}

}
