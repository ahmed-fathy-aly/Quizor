package web.serverResponse;

import java.util.ArrayList;

import core.QuizInfo;

// TODO: Auto-generated Javadoc
/**
 * The Class ViewQuizzesResponse.
 */
public class ViewQuizzesResponse extends ServerResponse
{

	/* fields */
	/** The quiz infos. */
	ArrayList<QuizInfo> quizInfos;

	/* Constructors */
	/**
	 * Instantiates a new view quizzes response.
	 *
	 * @param quizzes the quizzes
	 */
	public ViewQuizzesResponse(ArrayList<QuizInfo> quizzes)
	{
		this.quizInfos = quizzes;
	}

	/**
	 * Instantiates a new view quizzes response.
	 */
	public ViewQuizzesResponse()
	{
		quizInfos = new ArrayList<>();
	}

	/* Setters and getters */
	/**
	 * Gets the quiz infos.
	 *
	 * @return the quiz infos
	 */
	public ArrayList<QuizInfo> getQuizInfos()
	{
		return quizInfos;
	}

	/**
	 * Sets the quiz infos.
	 *
	 * @param quizzes the new quiz infos
	 */
	public void setQuizInfos(ArrayList<QuizInfo> quizzes)
	{
		this.quizInfos = quizzes;
	}
	
	
	
}
