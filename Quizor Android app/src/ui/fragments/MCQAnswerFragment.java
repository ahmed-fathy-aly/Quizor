package ui.fragments;

import ui.adapters.MCQChoicesAnswerAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.team_egor.quizor.R;

import controller.QuizController;

/**
 * The Class MCQAnswerFragment.
 */
public class MCQAnswerFragment extends Fragment  
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
	private MCQChoicesAnswerAdapter listAdapter;

	/* activity stuff */
	/**
	 * Instantiates a new MCQ answer fragment.
	 */
	public MCQAnswerFragment()
	{
	}

	/**
	 * Instantiates a new MCQ answer fragment.
	 *
	 * @param questionIdx the question idx
	 * @param controller the controller
	 */
	public MCQAnswerFragment(int questionIdx, QuizController controller)
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
		rootView = inflater.inflate(R.layout.fragment_mcq_answer, container, false);

		// set up the list
		choicesList = (ListView) rootView.findViewById(R.id.mcqChoicesListView);
		listAdapter = new MCQChoicesAnswerAdapter(getActivity(), controller, questionIdx);
		choicesList.setAdapter(listAdapter);

		// set question body
		TextView questionBody = (TextView) rootView.findViewById(R.id.textViewQuestionBody);
		questionBody.setText(controller.getQuestionBody(questionIdx));

		// set listeners
		
		return rootView;
	}


}
