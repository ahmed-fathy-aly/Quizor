package web.serverResponse;

import java.util.ArrayList;

import core.SolutionStatistics;

// TODO: Auto-generated Javadoc
/**
 * The Class GetQuizStatisticsResponse.
 */
public class GetQuizStatisticsResponse extends ServerResponse
{
	/* fields */
	/** The solutions. */
	ArrayList<SolutionStatistics> solutions;

	/* constructors */
	/**
	 * Instantiates a new gets the quiz statistics response.
	 *
	 * @param solutions the solutions
	 */
	public GetQuizStatisticsResponse(ArrayList<SolutionStatistics> solutions)
	{
		super();
		this.solutions = solutions;
	}

	/**
	 * Instantiates a new gets the quiz statistics response.
	 */
	public GetQuizStatisticsResponse()
	{
		super();
		solutions = new ArrayList<>();
	}

	/* setters and getters */
	/**
	 * Gets the solutions.
	 *
	 * @return the solutions
	 */
	public ArrayList<SolutionStatistics> getSolutions()
	{
		return solutions;
	}

	/**
	 * Sets the solutions.
	 *
	 * @param solutions the new solutions
	 */
	public void setSolutions(ArrayList<SolutionStatistics> solutions)
	{
		this.solutions = solutions;
	}

}
