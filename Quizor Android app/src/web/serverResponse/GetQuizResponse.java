package web.serverResponse;

import java.util.ArrayList;

import core.MCQ;

// TODO: Auto-generated Javadoc
/**
 * The Class GetQuizResponse.
 */
public class GetQuizResponse extends ServerResponse
{
	/* fields */
	/** The duration. */
	long duration;
	
	/** The questions. */
	ArrayList<MCQ> questions;
	
	/* Cosntructors */
	/**
	 * Instantiates a new gets the quiz response.
	 *
	 * @param duration the duration
	 * @param questions the questions
	 */
	public GetQuizResponse(long duration, ArrayList<MCQ> questions)
	{
		super();
		this.duration = duration;
		this.questions = questions;
	}
	
	/**
	 * Instantiates a new gets the quiz response.
	 */
	public GetQuizResponse()
	{
		super();
		this.questions = new ArrayList<>();
	}
	
	/* Setters and getters */
	/**
	 * Gets the duration.
	 *
	 * @return the duration
	 */
	public long getDuration()
	{
		return duration;
	}
	
	/**
	 * Sets the duration.
	 *
	 * @param duration the new duration
	 */
	public void setDuration(long duration)
	{
		this.duration = duration;
	}
	
	/**
	 * Gets the questions.
	 *
	 * @return the questions
	 */
	public ArrayList<MCQ> getQuestions()
	{
		return questions;
	}
	
	/**
	 * Sets the questions.
	 *
	 * @param questions the new questions
	 */
	public void setQuestions(ArrayList<MCQ> questions)
	{
		this.questions = questions;
	}
	
	
	
}
