package ui.adapters;

import com.google.android.gms.internal.co;
import com.team_egor.quizor.R;

import utils.TimeUtils;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import core.QuizInfo;

// TODO: Auto-generated Javadoc
/**
 * The Class QuizzesListAdapter.
 */
public class QuizzesListAdapter extends BaseAdapter
{

	/**
	 * The activity using the adapter should implement this.
	 *
	 * @see QuizzesListEvent
	 */
	public interface QuizzesListListener
	{

		/**
		 * On quiz settings clicked.
		 *
		 * @param position
		 *            the position
		 * @param v
		 *            the v
		 */
		void onQuizSettingsClicked(int position, View v);
	}

	/**
	 * The Interface QuizzInfoGetter.
	 */
	public interface QuizzInfoGetter
	{

		/**
		 * Gets the quizzes count.
		 *
		 * @return the quizzes count
		 */
		int getQuizzesCount();

		/**
		 * Gets the quiz name.
		 *
		 * @param position
		 *            the position
		 * @return the quiz name
		 */
		CharSequence getQuizName(int position);

		/**
		 * Gets the availability date.
		 *
		 * @param position
		 *            the position
		 * @return the availability date
		 */
		long getAvailabilityDate(int position);

		/**
		 * Gets the quiz duration.
		 *
		 * @param position
		 *            the position
		 * @return the quiz duration
		 */
		int getQuizDuration(int position);

		boolean isQuizTaken(int position);

	}

	/* fields */
	/** The ui listener. */
	private QuizzesListListener uiListener;

	/** The listener. */
	private QuizzInfoGetter listener;

	/** The context. */
	private Context context;

	/* Constructor */
	/**
	 * Instantiates a new quizzes list adapter.
	 *
	 * @param uiLlistener
	 *            the ui llistener
	 * @param listener
	 *            the listener
	 * @param context
	 *            the context
	 */
	public QuizzesListAdapter(QuizzesListListener uiLlistener, QuizzInfoGetter listener,
			Context context)
	{
		this.uiListener = uiLlistener;
		this.listener = listener;
		this.context = context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount()
	{
		return listener.getQuizzesCount();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position)
	{
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position)
	{
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	@Override
	public View getView(final int position, View convertView, ViewGroup parent)
	{
		// check convert view
		if (convertView == null)
		{
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(com.team_egor.quizor.R.layout.quizzes_list_item, null);
		}

		// quiz name
		TextView quizNameText = (TextView) convertView
				.findViewById(com.team_egor.quizor.R.id.textViewQuizName);
		quizNameText.setText(listener.getQuizName(position));

		// quiz description
		TextView quizAvailabilityDate = (TextView) convertView
				.findViewById(com.team_egor.quizor.R.id.textViewQuizAvailabilityDate);
		quizAvailabilityDate.setText(TimeUtils.convertToReadableDate(listener
				.getAvailabilityDate(position)));

		// quiz duration
		TextView quizDuration = (TextView) convertView
				.findViewById(com.team_egor.quizor.R.id.textViewQuizDuration);
		quizDuration.setText(TimeUtils
				.convertToReadableDuration(listener.getQuizDuration(position)));

		// settings button
		ImageButton buttonSettings = (ImageButton) convertView
				.findViewById(com.team_egor.quizor.R.id.imageButtonQuizSettings);
		buttonSettings.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				uiListener.onQuizSettingsClicked(position, v);
			}
		});

		// quiz taken image
		ImageButton quizTaken = (ImageButton) convertView.findViewById(R.id.imageButtonQuizTaken);
		if (listener.isQuizTaken(position))
			quizTaken.setVisibility(View.VISIBLE);
		else
			quizTaken.setVisibility(View.INVISIBLE);

		return convertView;
	}

}
