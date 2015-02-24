package web.serverRequests;

import java.util.ArrayList;

import core.MCQ;

// TODO: Auto-generated Javadoc
/**
 * The Class EditQuestionsRequest.
 */
public class EditQuestionsRequest extends ServerRequest
{
	/* fields */
	/** The quiz id. */
	String quizId;
	
	/** The questions. */
	ArrayList<MCQ> questions;
	
	/* Constructors */
	/**
	 * Instantiates a new edits the questions request.
	 *
	 * @param quizId the quiz id
	 * @param questions the questions
	 */
	public EditQuestionsRequest(String quizId, ArrayList<MCQ> questions)
	{
		super();
		this.quizId = quizId;
		this.questions = questions;
	}

	/**
	 * Instantiates a new edits the questions request.
	 */
	public EditQuestionsRequest()
	{
		super();
	}

	/* Setters and getters */

	/**
	 * Gets the quiz id.
	 *
	 * @return the quiz id
	 */
	public String getQuizId()
	{
		return quizId;
	}

	/**
	 * Sets the quiz id.
	 *
	 * @param quizId the new quiz id
	 */
	public void setQuizId(String quizId)
	{
		this.quizId = quizId;
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
