package ui.adapters;

import com.team_egor.quizor.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

// TODO: Auto-generated Javadoc
/**
 * The Class SolutionStatisticsAdapter.
 */
public class SolutionStatisticsAdapter extends BaseAdapter
{
	
	/**
	 * The Interface SolutionStatisticsGetter.
	 */
	public interface SolutionStatisticsGetter
	{

		/**
		 * Gets the solutions count.
		 *
		 * @return the solutions count
		 */
		int getSolutionsCount();

		/**
		 * Gets the user name.
		 *
		 * @param i the i
		 * @return the user name
		 */
		String getUserName(int i);

		/**
		 * Gets the score.
		 *
		 * @param i the i
		 * @return the score
		 */
		String getScore(int i);

		/**
		 * Gets the late minutes.
		 *
		 * @param i the i
		 * @return the late minutes
		 */
		String getLateMinutes(int i);
	}

	/* fields */
	/** The listener. */
	private SolutionStatisticsGetter listener;
	
	/** The context. */
	private Context context;

	/**
	 * Instantiates a new solution statistics adapter.
	 *
	 * @param context the context
	 * @param listener the listener
	 */
	public SolutionStatisticsAdapter(Context context, SolutionStatisticsGetter listener)
	{
		this.listener = listener;
		this.context = context;
	}

	/**
	 * The Class ViewHolder.
	 */
	public class ViewHolder
	{
		
		/** The user name. */
		TextView userName;
		
		/** The score. */
		TextView score;
		
		/** The late minutes. */
		TextView lateMinutes;

	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount()
	{
		return listener.getSolutionsCount() + 1;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position)
	{
		return null;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position)
	{
		return 0;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		final ViewHolder viewHolder;
		if (convertView == null)
		{
			// inflate
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(
					com.team_egor.quizor.R.layout.solution_statistics_list_item, null);

			// handle view holder
			viewHolder = new ViewHolder();
			convertView.setTag(viewHolder);

			// reference views
			viewHolder.userName = (TextView) convertView.findViewById(R.id.textViewStudentUserName);
			viewHolder.score = (TextView) convertView.findViewById(R.id.textViewStudentScore);
			viewHolder.lateMinutes = (TextView) convertView.findViewById(R.id.textViewStudentLate);

		} else
			viewHolder = (ViewHolder) convertView.getTag();

		// skip the first row
		if (position == 0)
		{
			viewHolder.userName.setText("User Name");
			viewHolder.score.setText("Score");
			viewHolder.lateMinutes.setText("Late minutes");
		} else
		{
			// get data
			String userName = listener.getUserName(position - 1);
			String score = listener.getScore(position - 1);
			String lateMinute = listener.getLateMinutes(position - 1);

			// set views
			viewHolder.userName.setText(userName);
			viewHolder.score.setText(score);
			viewHolder.lateMinutes.setText(lateMinute);
		}
		return convertView;
	}

}
