package web.serverRequests;

import java.util.ArrayList;

import core.MCQ;

// TODO: Auto-generated Javadoc
/**
 * The Class AnswerQuestionsRequest.
 */
public class AnswerQuestionsRequest extends ServerRequest
{
	/* fields */
	/** The questions. */
	ArrayList<MCQ> questions;
	
	/** The quiz id. */
	String quizId;

	/* Constructors */

	/**
	 * Instantiates a new answer questions request.
	 */
	public AnswerQuestionsRequest()
	{
		super();
		questions = new ArrayList<>();
	}

	/**
	 * Instantiates a new answer questions request.
	 *
	 * @param questions the questions
	 * @param quizId the quiz id
	 */
	public AnswerQuestionsRequest(ArrayList<MCQ> questions, String quizId)
	{
		super();
		this.questions = questions;
		this.quizId = quizId;
	}

	/* Setters and Getters */
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

	
}
