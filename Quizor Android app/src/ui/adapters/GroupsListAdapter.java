package ui.adapters;

import com.team_egor.quizor.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import core.GroupInfo;

// TODO: Auto-generated Javadoc
/**
 * The Class GroupsListAdapter.
 */
public class GroupsListAdapter extends BaseAdapter
{

	/**
	 * The activity using the adapter should implement this.
	 */
	public interface GroupInfoGetter
	{
		
		/**
		 * Gets the group info.
		 *
		 * @param idx the idx
		 * @return the group info
		 */
		GroupInfo getGroupInfo(int idx);

		/**
		 * Gets the groups count.
		 *
		 * @return the groups count
		 */
		int getGroupsCount();

		/**
		 * Gets the group name.
		 *
		 * @param position the position
		 * @return the group name
		 */
		String getGroupName(int position);

		/**
		 * Professor full name.
		 *
		 * @param position the position
		 * @return the string
		 */
		String professorFullName(int position);

		/**
		 * Gets the professor mail.
		 *
		 * @param position the position
		 * @return the professor mail
		 */
		String getProfessorMail(int position);
	}

	/**
	 * The listener interface for receiving groupList events.
	 * The class that is interested in processing a groupList
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addGroupListListener<code> method. When
	 * the groupList event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see GroupListEvent
	 */
	public interface GroupListListener
	{
		
		/**
		 * On group setting clicked.
		 *
		 * @param position the position
		 * @param v the v
		 */
		void onGroupSettingClicked(int position, View v);
	}

	/* fields */
	/** The listener. */
	private GroupInfoGetter listener;
	
	/** The ui listener. */
	private GroupListListener uiListener;
	
	/** The context. */
	private Context context;

	/* Constructor */
	/**
	 * Instantiates a new groups list adapter.
	 *
	 * @param uiListener the ui listener
	 * @param listener the listener
	 * @param context the context
	 */
	public GroupsListAdapter(GroupListListener uiListener, GroupInfoGetter listener, Context context)
	{
		this.listener = listener;
		this.uiListener = uiListener;
		this.context = context;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount()
	{
		return listener.getGroupsCount();

	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position)
	{
		return listener.getGroupInfo(position);

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
	public View getView(final int position, View convertView, ViewGroup parent)
	{
		// check convert view
		if (convertView == null)
		{
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(com.team_egor.quizor.R.layout.groups_list_item, null);
		}

		// get data
		String groupname = listener.getGroupName(position);
		String profName = listener.professorFullName(position);
		String profMail = listener.getProfessorMail(position);

		// set views
		TextView groupNameTextView = (TextView) convertView
				.findViewById(com.team_egor.quizor.R.id.textViewGroupName);
		groupNameTextView.setText(groupname);
		TextView profNameText = (TextView) convertView
				.findViewById(R.id.textViewGroupProfessorName);
		profNameText.setText(profName);
		TextView profMailText = (TextView) convertView
				.findViewById(R.id.textViewGroupProfessorMail);
		profMailText.setText(profMail);

		// action listener
		ImageButton settingsButton = (ImageButton) convertView
				.findViewById(com.team_egor.quizor.R.id.imageButtonGroupSettings);
		settingsButton.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				uiListener.onGroupSettingClicked(position, v);

			}
		});
		return convertView;
	}

}
