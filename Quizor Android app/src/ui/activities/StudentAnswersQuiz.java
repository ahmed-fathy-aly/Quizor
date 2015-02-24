package ui.activities;

import ui.adapters.QuestionsAnswerPagerAdapter;
import ui.adapters.QuestionsAnswerPagerAdapter.QuestionsAdapterListener;
import ui.transitions.ZoomOutPageTransformer;
import web.serverResponse.AnswerQuestionsResponse;
import web.serverResponse.ServerResponse;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.team_egor.quizor.R;

import controller.AsyncTaskController.PostExecutioner;
import controller.CountDownable;
import controller.QuizController;
import controller.Refreshable;

// TODO: Auto-generated Javadoc
/**
 * The Class StudentAnswersQuiz.
 */
public class StudentAnswersQuiz extends FragmentActivity implements QuestionsAdapterListener,
		Refreshable, CountDownable
{

	/* tags */
	/** The Constant TAG_QUIZ_ID. */
	public static final String TAG_QUIZ_ID = "tagQuizId";
	
	/** The Constant TAG_QUIZ_NAME. */
	public static final String TAG_QUIZ_NAME = "tagQuizName";

	/* fields */
	/** The controller. */
	QuizController controller;
	
	/** The quiz id. */
	String quizId;
	
	/** The quiz name. */
	private String quizName;
	
	/** The adapter. */
	private QuestionsAnswerPagerAdapter adapter;
	
	/** The view pager. */
	private ViewPager viewPager;
	
	/** The count down timer. */
	CountDownTimer countDownTimer;
	
	/** The count down text view. */
	TextView countDownTextView;

	/* (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// initialize
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_answer_questions);
		this.controller = new QuizController(this);
		countDownTextView = (TextView) findViewById(R.id.textViewCountDown);

		// get tags
		this.quizId = getIntent().getStringExtra(TAG_QUIZ_ID);
		this.quizName = getIntent().getStringExtra(TAG_QUIZ_NAME);
		setTitle(quizName);

		// handle adapter
		adapter = new QuestionsAnswerPagerAdapter(getSupportFragmentManager(), this);
		viewPager = (ViewPager) findViewById(R.id.viewPagerQuestions);
		viewPager.setAdapter(adapter);
		viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
		viewPager.setOnPageChangeListener(new OnPageChangeListener()
		{

			@Override
			public void onPageSelected(int arg0)
			{
				onPageChanged();
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2)
			{
				onPageChanged();

			}

			@Override
			public void onPageScrollStateChanged(int arg0)
			{
				onPageChanged();

			}
		});
		onPageChanged();

		// set buttons listeners
		ImageButton next = (ImageButton) findViewById(R.id.imageButtonnextQuestion);
		next.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				onNextQuestionClicked();
			}
		});
		ImageButton previous = (ImageButton) findViewById(R.id.imageButtonPreviousQuestion);
		previous.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				onPreviousQuestionClicked();
			}
		});
		Button submitAnswer = (Button) findViewById(R.id.buttonSubmitAnswers);
		submitAnswer.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				onSubmitButtonClicked();
			}
		});
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#onStart()
	 */
	@Override
	protected void onStart()
	{
		super.onStart();

		// download the quiz
		controller.downloadQuestions(this, quizId, this);
		;
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_answer_questions, menu);
		return true;
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		return super.onOptionsItemSelected(item);
	}

	/* questions stuff */
	/* (non-Javadoc)
	 * @see ui.adapters.QuestionsAnswerPagerAdapter.QuestionsAdapterListener#getController()
	 */
	@Override
	public QuizController getController()
	{
		return controller;
	}

	/* (non-Javadoc)
	 * @see controller.Refreshable#refreshUI()
	 */
	@Override
	public void refreshUI()
	{
		adapter.notifyDataSetChanged();
	}

	/* (non-Javadoc)
	 * @see controller.CountDownable#startCountDown(long)
	 */
	@Override
	public void startCountDown(long duration)
	{
		countDownTimer = new CountDownTimer(duration * 1000, 1000)
		{

			@Override
			public void onTick(long millisUntilFinished)
			{
				int seconds = (int) (millisUntilFinished / 1000);
				int minutes = seconds / 60;
				seconds -= minutes * 60;
				countDownTextView.setText(minutes + ":" + seconds);
			}

			@Override
			public void onFinish()
			{
				// show time is expired
				countDownTextView.setText("Time Expired");
				Toast.makeText(StudentAnswersQuiz.this,
						"Time expired, Any submission after 2 minutes will be dismissed",
						Toast.LENGTH_SHORT);
				submitAnswers();
			}
		};
		countDownTimer.start();
	}

	/**
	 * ask the server to upload the answers.
	 */
	protected void submitAnswers()
	{
		// make post executioner
		PostExecutioner postExecutioner = new PostExecutioner()
		{

			@Override
			public void onPostExecution(ServerResponse serverResponse)
			{
				// make a toast
				AnswerQuestionsResponse response = (AnswerQuestionsResponse) serverResponse;
				Toast.makeText(StudentAnswersQuiz.this, response.getStatus(), Toast.LENGTH_SHORT)
						.show();
				;

				// check response
				if (response.getStatus().equals(ServerResponse.STATUS_SUCCESSFUL))
				{
					Toast.makeText(StudentAnswersQuiz.this, response.getMessage(),
							Toast.LENGTH_LONG).show();
					finish();
				}
			}
		};

		// ask controller to submit
		controller.submitAnswers(this, quizId, postExecutioner);
	}

	/**
	 * ask to submit the answers.
	 */
	protected void onSubmitButtonClicked()
	{
		// make a dialog
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

		// set dialog message
		alertDialogBuilder.setTitle("Submit Answers?");
		alertDialogBuilder.setMessage("You can only submit once").setCancelable(true)
				.setPositiveButton("Yes", new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int id)
					{
						submitAnswers();
					}
				}).setNegativeButton("Cancel", new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int id)
					{
						// close the dialog
						dialog.cancel();
					}
				});

		// create and show the dialog
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}

	/* navigation stuff */
	/**
	 * On previous question clicked.
	 */
	protected void onPreviousQuestionClicked()
	{
		// invalid
		if (viewPager.getCurrentItem() <= 0)
			return;

		// set pager to prev element
		viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true);
	}

	/**
	 * On next question clicked.
	 */
	protected void onNextQuestionClicked()
	{
		// invalid
		if (viewPager.getCurrentItem() + 1 >= controller.getQuestionsCount())
			return;

		// set pager to prev element
		viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
	}

	/**
	 * On page changed.
	 */
	protected void onPageChanged()
	{
		// change text
		TextView questionIdxText = (TextView) findViewById(R.id.textViewQuestionIndex);
		String str;
		if (viewPager.getChildCount() > 0)
			str = (viewPager.getCurrentItem() + 1) + "/" + controller.getQuestionsCount();
		else
			str = "-/0";
		questionIdxText.setText(str);
	}

}
