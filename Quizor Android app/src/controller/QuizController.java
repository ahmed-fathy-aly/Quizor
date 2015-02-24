package controller;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ui.adapters.MCQChoicesAnswerAdapter.ChoiceAnswerer;
import ui.adapters.MCQChoicesEditAdapter.ChoiceEditor;
import ui.adapters.SolutionStatisticsAdapter.SolutionStatisticsGetter;
import web.serverBehaviours.AnswerQuestionsBehavior;
import web.serverBehaviours.EditQuestionsBehavior;
import web.serverBehaviours.GetQuizBehavior;
import web.serverBehaviours.GetQuizStatisticsBehavior;
import web.serverBehaviours.ViewGroupsBehavior;
import web.serverRequests.AnswerQuestionsRequest;
import web.serverRequests.EditQuestionsRequest;
import web.serverRequests.GetQuizRequest;
import web.serverRequests.GetQuizStatisticsRequest;
import web.serverRequests.ServerRequest;
import web.serverRequests.ViewGroupsRequest;
import web.serverResponse.AnswerQuestionsResponse;
import web.serverResponse.GetQuizResponse;
import web.serverResponse.GetQuizStatisticsResponse;
import web.serverResponse.ServerResponse;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import controller.AsyncTaskController.PostExecutioner;
import core.MCQ;
import core.MCQChoice;
import core.SolutionStatistics;
import dbc.DBC;

// TODO: Auto-generated Javadoc
/**
 * The Class QuizController.
 */
public class QuizController implements SolutionStatisticsGetter, ChoiceAnswerer, ChoiceEditor
{
	/* fields */
	/** The questions. */
	ArrayList<MCQ> questions;
	
	/** The solutions. */
	ArrayList<SolutionStatistics> solutions;
	
	/** The current task. */
	private AsyncTask<ServerRequest, Object, ServerResponse> currentTask;
	
	/** The listener. */
	Refreshable listener;

	/* constructors */
	/**
	 * Instantiates a new quiz controller.
	 *
	 * @param refreshable the refreshable
	 */
	public QuizController(Refreshable refreshable)
	{
		this.questions = new ArrayList<>();
		this.solutions = new ArrayList<>();
		this.listener = refreshable;
	}

	/* getters and setters */

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
	 * ensures that choice body is set.
	 *
	 * @param questionIdx            requires more than 0 and less than questions size
	 * @param position            more than zero and less than choices size
	 * @param body the body
	 */
	public void setChoiceBody(int questionIdx, int position, String body)
	{
		// DBC.require(questionIdx >= 0 && questionIdx < questions.size());
		// DBC.require(position >= 0 && position <
		// questions.get(questionIdx).getChoices().size());

		questions.get(questionIdx).getChoices().get(position).setBody(body);

		// DBC.ensure(getChoice(questionIdx, position).getBody().equals(body));
	}

	/**
	 * Gets the questions count.
	 *
	 * @return the questions count
	 */
	public int getQuestionsCount()
	{
		return questions.size();
	}

	/**
	 * Gets the question body.
	 *
	 * @param questionIdx            requires more than 0 and less than questions size
	 * @return the question body
	 */
	public String getQuestionBody(int questionIdx)
	{
		// DBC.require(questionIdx >= 0 && questionIdx < questions.size());

		return questions.get(questionIdx).getBody();
	}

	/**
	 * ensures the question body is et.
	 *
	 * @param questionIdx            requires more than 0 and less than questions size
	 * @param body the body
	 */
	public void setQuestionBody(int questionIdx, String body)
	{
		// DBC.require(questionIdx >= 0 && questionIdx < questions.size());

		questions.get(questionIdx).setBody(body);

		// DBC.ensure(questions.get(questionIdx).getBody().equals(body));
	}

	/* methods */
	/**
	 * Gets the solutions histogram.
	 *
	 * @return the solutions histogram
	 */
	public int[] getSolutionsHistogram()
	{
		// get scores
		ArrayList<Integer> scores = new ArrayList<>();
		for (SolutionStatistics sol : solutions)
			try
			{
				int x = Integer.parseInt(sol.getScore());
				scores.add(x);
			} catch (Exception e)
			{
			}
		if (scores.size() == 0)
			return new int[]
			{};

		// find max
		int max = 0;
		for (Integer score : scores)
			max = Math.max(max, score);

		// make array
		int result[] = new int[max + 1];
		for (int i = 0; i < scores.size(); i++)
			result[scores.get(i)]++;
		return result;
	}

	/* web methods */
	/**
	 * Download questions.
	 *
	 * @param context the context
	 * @param quizId the quiz id
	 * @param countDownable the count downable
	 */
	public void downloadQuestions(final Context context, String quizId,
			final CountDownable countDownable)
	{
		// make request and behavior
		GetQuizRequest request = new GetQuizRequest(quizId);
		GetQuizBehavior getQuiz = new GetQuizBehavior();

		// make post executioner
		PostExecutioner posteExecutioner = new PostExecutioner()
		{

			@Override
			public void onPostExecution(ServerResponse serverResponse)
			{
				// make a toast
				GetQuizResponse response = (GetQuizResponse) serverResponse;
				Toast.makeText(context, response.getStatus(), Toast.LENGTH_SHORT).show();

				// set questions
				questions = response.getQuestions();
				listener.refreshUI();

				// start count down
				if (countDownable != null)
					countDownable.startCountDown(response.getDuration());
			}
		};

		// make task
		AsyncTask<ServerRequest, Object, ServerResponse> task = AsyncTaskController.getAsyncTask(
				request, getQuiz, posteExecutioner, context);
		currentTask = task;

		// execute
		task.execute(request);

	}

	/**
	 * Submit answers.
	 *
	 * @param context the context
	 * @param quizId the quiz id
	 * @param postExecutioner the post executioner
	 */
	public void submitAnswers(Context context, String quizId, PostExecutioner postExecutioner)
	{
		// make request and behavior
		AnswerQuestionsRequest request = new AnswerQuestionsRequest(questions, quizId);
		AnswerQuestionsBehavior behavior = new AnswerQuestionsBehavior();

		// make task
		AsyncTask<ServerRequest, Object, ServerResponse> task = AsyncTaskController.getAsyncTask(
				request, behavior, postExecutioner, context);
		currentTask = task;

		// execute
		task.execute(request);
	}

	/**
	 * Download solutions statistics.
	 *
	 * @param context the context
	 * @param quizId the quiz id
	 */
	public void downloadSolutionsStatistics(final Context context, String quizId)
	{
		// make a request and a behavior
		GetQuizStatisticsRequest request = new GetQuizStatisticsRequest(quizId);
		GetQuizStatisticsBehavior behavior = new GetQuizStatisticsBehavior();

		// make post executioner
		PostExecutioner postExecutioner = new PostExecutioner()
		{

			@Override
			public void onPostExecution(ServerResponse serverResponse)
			{
				// make toast
				GetQuizStatisticsResponse response = (GetQuizStatisticsResponse) serverResponse;
				Toast.makeText(context, response.getStatus(), Toast.LENGTH_SHORT).show();

				// set solutions statistics
				solutions = response.getSolutions();
				listener.refreshUI();
			}
		};

		// make task
		AsyncTask<ServerRequest, Object, ServerResponse> task = AsyncTaskController.getAsyncTask(
				request, behavior, postExecutioner, context);
		currentTask = task;

		// execute task
		task.execute(request);
	}

	/**
	 * Edits the questions.
	 *
	 * @param context the context
	 * @param postExcecutioner the post excecutioner
	 * @param quizId the quiz id
	 */
	public void editQuestions(Context context, PostExecutioner postExcecutioner, String quizId)
	{
		// make the request, behavior
		EditQuestionsRequest request = new EditQuestionsRequest(quizId, questions);
		EditQuestionsBehavior editQuestions = new EditQuestionsBehavior();

		// get the task
		AsyncTask<ServerRequest, Object, ServerResponse> task = AsyncTaskController.getAsyncTask(
				request, editQuestions, postExcecutioner, context);
		currentTask = task;

		// execute
		task.execute(request);
	}

	/* methods for solution statisitcs getter */

	/* (non-Javadoc)
	 * @see ui.adapters.SolutionStatisticsAdapter.SolutionStatisticsGetter#getSolutionsCount()
	 */
	@Override
	public int getSolutionsCount()
	{
		return solutions.size();
	}

	/* (non-Javadoc)
	 * @see ui.adapters.SolutionStatisticsAdapter.SolutionStatisticsGetter#getUserName(int)
	 */
	@Override
	public String getUserName(int i)
	{
		// DBC.require(i >= 0 && i < solutions.size());

		return solutions.get(i).getUserName();
	}

	/* (non-Javadoc)
	 * @see ui.adapters.SolutionStatisticsAdapter.SolutionStatisticsGetter#getScore(int)
	 */
	@Override
	public String getScore(int i)
	{
		// DBC.require(i >= 0 && i < solutions.size());

		return solutions.get(i).getScore();
	}

	/* (non-Javadoc)
	 * @see ui.adapters.SolutionStatisticsAdapter.SolutionStatisticsGetter#getLateMinutes(int)
	 */
	@Override
	public String getLateMinutes(int i)
	{
		// DBC.require(i >= 0 && i < solutions.size());

		return solutions.get(i).getMinutesLate();
	}

	/* methods for choice answerer, choice editor */

	/* (non-Javadoc)
	 * @see ui.adapters.MCQChoicesAnswerAdapter.ChoiceAnswerer#getChoicesCount(int)
	 */
	@Override
	public int getChoicesCount(int questionIdx)
	{
		return questions.get(questionIdx).getChoices().size();
	}

	/**
	 * ensures that choice is marked isChecked.
	 *
	 * @param questionIdx            requires more than 0 and less than questions size
	 * @param position            more than zero and less than choices size
	 * @param isChecked the is checked
	 */
	@Override
	public void setCorrectChoice(int questionIdx, int position, boolean isChecked)
	{
		// DBC.require(questionIdx >= 0 && questionIdx < questions.size());
		// DBC.require(position >= 0 && position <
		// questions.get(questionIdx).getChoices().size());

		questions.get(questionIdx).getChoices().get(position).setChecked(isChecked);

		// DBC.ensure(getChoice(questionIdx, position).isChecked() ==
		// isChecked);
	}

	/**
	 * Checks if is choice checked.
	 *
	 * @param questionIdx            requires more than 0 and less than questions size
	 * @param i the i
	 * @return true, if is choice checked
	 */
	@Override
	public boolean isChoiceChecked(int questionIdx, int i)
	{
		// DBC.require(questionIdx >= 0 && questionIdx < questions.size());
		// DBC.require(position >= 0 && position <
		// questions.get(questionIdx).getChoices().size());

		return questions.get(questionIdx).getChoices().get(i).isChecked();
	}

	/**
	 * Gets the choice body.
	 *
	 * @param questionIdx            requires more than 0 and less than questions size
	 * @param i the i
	 * @return the choice body
	 */
	@Override
	public String getChoiceBody(int questionIdx, int i)
	{
		// DBC.require(questionIdx >= 0 && questionIdx < questions.size());
		// DBC.require(position >= 0 && position <
		// questions.get(questionIdx).getChoices().size());

		return questions.get(questionIdx).getChoices().get(i).getBody();
	}

	/**
	 * ensures choices size decreased.
	 *
	 * @param questionIdx            requires more than 0 and less than questions size
	 * @param position            more than zero and less than choices size
	 */
	@Override
	public void deleteChoice(int questionIdx, int position)
	{
		int oldSize = questions.get(questionIdx).getChoices().size();
		// DBC.require(questionIdx >= 0 && questionIdx < questions.size());
		// DBC.require(position >= 0 && position <
		// questions.get(questionIdx).getChoices().size());

		questions.get(questionIdx).removeChoice(position);

		// DBC.ensure(oldSize - 1 ==
		// questions.get(questionIdx).getChoices().size());
	}

	/* methods for the question, choices buttons (adding, deleting) */

	/**
	 * ensures choices size increases.
	 *
	 * @param questionIdx            requires more than 0 and less than questions size
	 */
	public void addChoice(int questionIdx)
	{
		int oldSize = questions.get(questionIdx).getChoices().size();
		// DBC.require(questionIdx >= 0 && questionIdx < questions.size());

		questions.get(questionIdx).getChoices().add(new MCQChoice());

		// DBC.ensure(oldSize + 1 ==
		// questions.get(questionIdx).getChoices().size());
	}

	/**
	 * ensures an empty question is added.
	 */
	public void addQuestion()
	{
		int oldSize = questions.size();

		questions.add(new MCQ());

		// DBC.ensure(oldSize + 1 == questions.size());
	}

	/**
	 * ensures the question is removed.
	 *
	 * @param questionIdx            requires more than 0 and less than questions size
	 */
	public void removeQuestion(int questionIdx)
	{
		int oldSize = questions.size();

		// DBC.require(questionIdx >= 0 && questionIdx < questions.size());

		questions.remove(questionIdx);
		// DBC.ensure(oldSize - 1 == questions.size());

	}

}
