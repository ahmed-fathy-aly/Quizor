package ui.adapters;

import ui.fragments.MCQAnswerFragment;
import ui.fragments.MCQEditFragment;
import controller.QuizController;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

// TODO: Auto-generated Javadoc
/**
 * The Class QuestionsAnswerPagerAdapter.
 */
public class QuestionsAnswerPagerAdapter extends FragmentPagerAdapter
{
	
	/**
	 * The listener interface for receiving questionsAdapter events.
	 * The class that is interested in processing a questionsAdapter
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addQuestionsAdapterListener<code> method. When
	 * the questionsAdapter event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see QuestionsAdapterEvent
	 */
	public interface QuestionsAdapterListener
	{
		
		/**
		 * Gets the controller.
		 *
		 * @return the controller
		 */
		QuizController getController();
	}

	/* fields */
	/** The listener. */
	private QuestionsAdapterListener listener;

	/**
	 * Instantiates a new questions answer pager adapter.
	 *
	 * @param fm the fm
	 * @param listener the listener
	 */
	public QuestionsAnswerPagerAdapter(FragmentManager fm, QuestionsAdapterListener listener)
	{
		super(fm);
		this.listener = listener;
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.FragmentPagerAdapter#getItem(int)
	 */
	@Override
	public Fragment getItem(int pos)
	{
		QuizController controller = listener.getController();
		return new MCQAnswerFragment(pos, controller);
	}

	/* (non-Javadoc)
	 * @see android.support.v4.view.PagerAdapter#getCount()
	 */
	@Override
	public int getCount()
	{
		return listener.getController().getQuestionsCount();
	}

}
