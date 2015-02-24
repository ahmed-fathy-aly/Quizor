package ui.fragments;

import ui.adapters.MCQChoicesEditAdapter;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.team_egor.quizor.R;

import controller.QuizController;

// TODO: Auto-generated Javadoc
/**
 * The Class MCQEditFragment.
 */
public class MCQEditFragment extends android.support.v4.app.Fragment
{

	/* fields */
	/** The root view. */
	View rootView;
	
	/** The question idx. */
	int questionIdx;
	
	/** The controller. */
	QuizController controller;
	
	/** The choices list. */
	ListView choicesList;
	
	/** The list adapter. */
	private MCQChoicesEditAdapter listAdapter;

	/* activity stuff */
	/**
	 * Instantiates a new MCQ edit fragment.
	 */
	public MCQEditFragment()
	{
	}

	/**
	 * Instantiates a new MCQ edit fragment.
	 *
	 * @param questionIdx the question idx
	 * @param controller the controller
	 */
	public MCQEditFragment(int questionIdx, QuizController controller)
	{
		this.questionIdx = questionIdx;
		this.controller = controller;
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// initialize fields
		rootView = inflater.inflate(R.layout.fragment_mcq_edit, container, false);

		// set up the list
		choicesList = (ListView) rootView.findViewById(R.id.mcqChoicesListView);
		listAdapter = new MCQChoicesEditAdapter(getActivity(), controller, questionIdx);
		choicesList.setAdapter(listAdapter);

		// set up the question body
		EditText questionBody = (EditText) rootView.findViewById(R.id.editTextQuestionBody);
		questionBody.setText(controller.getQuestionBody(questionIdx));
		questionBody.addTextChangedListener(new TextWatcher()
		{

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count)
			{
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after)
			{
			}

			@Override
			public void afterTextChanged(Editable s)
			{
				controller.setQuestionBody(questionIdx, s.toString());
			}
		});

		// set listener for adding choices
		Button buttonAddChoice = (Button) rootView.findViewById(R.id.buttonAddChoice);
		buttonAddChoice.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				addChoice();
			}
		});
		return rootView;
	}

	/* questions adapter stuff */

	/**
	 * Adds the choice.
	 */
	protected void addChoice()
	{
		controller.addChoice(questionIdx);
		listAdapter.notifyDataSetChanged();
	}

}
