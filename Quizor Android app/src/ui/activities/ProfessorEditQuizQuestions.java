package ui.activities;

import ui.adapters.QuestionsEditPagerAdapter;
import ui.adapters.QuestionsEditPagerAdapter.QuestionsAdapterListener;
import ui.transitions.ZoomOutPageTransformer;
import web.serverResponse.ServerResponse;
import android.os.Bundle;
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
import controller.QuizController;
import controller.Refreshable;

// TODO: Auto-generated Javadoc
/**
 * The Class ProfessorEditQuizQuestions.
 */
public class ProfessorEditQuizQuestions extends FragmentActivity implements Refreshable,
		QuestionsAdapterListener
{
	/* constants */
	/** The Constant TAG_QUIZ_ID. */
	public static final String TAG_QUIZ_ID = "tagQuizId";

	/* fields */
	/** The quiz id. */
	String quizId;
	
	/** The controller. */
	QuizController controller;
	
	/** The view pager. */
	ViewPager viewPager;
	
	/** The adapter. */
	QuestionsEditPagerAdapter adapter;

	/* (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// initialize
		controller = new QuizController(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_questions);

		// get tag
		quizId = getIntent().getStringExtra(TAG_QUIZ_ID);

		// handle adapter
		adapter = new QuestionsEditPagerAdapter(getSupportFragmentManager(), this);
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

		// set listeners
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
		ImageButton addQuestion = (ImageButton) findViewById(R.id.imageButtonAddQuestion);
		addQuestion.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				onAddQuestionsClicked();
			}
		});
		ImageButton deleteQuestions = (ImageButton) findViewById(R.id.imageButtonDeleteQuestion);
		deleteQuestions.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				onDeleteQuestionClicked();
			}
		});
		Button doneEditing = (Button) findViewById(R.id.buttonDoneEditing);
		doneEditing.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				onDoneEditingClicked();
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

		// download questions
		controller.downloadQuestions(this, quizId, null);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_questions, menu);
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

	/* buttons listeners */
	/**
	 * On done editing clicked.
	 */
	protected void onDoneEditingClicked()
	{
		// make post executioner
		PostExecutioner postExecutioner = new PostExecutioner()
		{
			@Override
			public void onPostExecution(ServerResponse response)
			{
				Toast.makeText(ProfessorEditQuizQuestions.this, response.getStatus(),
						Toast.LENGTH_SHORT).show();

			}
		};

		// ask the controller to edit questions
		controller.editQuestions(this, postExecutioner, quizId);
	}

	/**
	 * On delete question clicked.
	 */
	protected void onDeleteQuestionClicked()
	{
		// invalid
		if (controller.getQuestionsCount() == 0)
			return;

		// remove that one
		int position = viewPager.getCurrentItem();
		controller.removeQuestion(position);
		viewPager.setAdapter(null);
		viewPager.setAdapter(adapter);

		// go to the next one
		if (controller.getQuestionsCount() >= 1)
			if (position == controller.getQuestionsCount())
				viewPager.setCurrentItem(controller.getQuestionsCount() - 1, true);
			else
				viewPager.setCurrentItem(position, true);
		onPageChanged();
	}

	/**
	 * On add questions clicked.
	 */
	protected void onAddQuestionsClicked()
	{
		// add the question
		controller.addQuestion();
		adapter.notifyDataSetChanged();

		// navigate to that question
		viewPager.setCurrentItem(controller.getQuestionsCount() - 1, true);

	}

	/**
	 * On previous question clicked.
	 */
	protected void onPreviousQuestionClicked()
	{
		// invalid
		if (viewPager.getCurrentItem() <= 0)
			return;

		// set pager to that element
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

	/* (non-Javadoc)
	 * @see controller.Refreshable#refreshUI()
	 */
	@Override
	public void refreshUI()
	{
		adapter.notifyDataSetChanged();
	}

	/* adapter stuff */

	/* (non-Javadoc)
	 * @see ui.adapters.QuestionsEditPagerAdapter.QuestionsAdapterListener#getController()
	 */
	@Override
	public QuizController getController()
	{
		return controller;
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
