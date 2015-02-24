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
 * The Class StudentsListAdapter.
 */
public class StudentsListAdapter extends BaseAdapter
{

	/* fields */
	/** The listener. */
	private StudentInfoGetter listener;
	
	/** The context. */
	private Context context;

	/**
	 * Instantiates a new students list adapter.
	 *
	 * @param context the context
	 * @param listener the listener
	 */
	public StudentsListAdapter(Context context, StudentInfoGetter listener)
	{
		this.listener = listener;
		this.context = context;
	}

	/**
	 * The Interface StudentInfoGetter.
	 */
	public interface StudentInfoGetter
	{

		/**
		 * Gets the user name.
		 *
		 * @param i the i
		 * @return the user name
		 */
		String getUserName(int i);

		/**
		 * Gets the mail.
		 *
		 * @param i the i
		 * @return the mail
		 */
		String getMail(int i);

		/**
		 * Gets the department.
		 *
		 * @param i the i
		 * @return the department
		 */
		String getDepartment(int i);

		/**
		 * Gets the gradution year.
		 *
		 * @param i the i
		 * @return the gradution year
		 */
		String getGradutionYear(int i);

		/**
		 * Gets the section.
		 *
		 * @param i the i
		 * @return the section
		 */
		String getSection(int i);

		/**
		 * Gets the students count.
		 *
		 * @return the students count
		 */
		int getStudentsCount();

	}

	/**
	 * The Class ViewHolder.
	 */
	public class ViewHolder
	{
		
		/** The user name. */
		TextView userName;
		
		/** The mail. */
		TextView mail;
		
		/** The graduation year. */
		TextView graduationYear;
		
		/** The department. */
		TextView department;
		
		/** The section. */
		TextView section;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount()
	{
		return listener.getStudentsCount() + 1;
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
			convertView = inflater.inflate(com.team_egor.quizor.R.layout.students_list_item, null);

			// handle view holder
			viewHolder = new ViewHolder();
			convertView.setTag(viewHolder);

			// reference views
			viewHolder.userName = (TextView) convertView.findViewById(R.id.textViewStudentUserName);
			viewHolder.mail = (TextView) convertView.findViewById(R.id.textViewStudentMail);
			viewHolder.graduationYear = (TextView) convertView
					.findViewById(R.id.textViewStudentGraduationYear);
			viewHolder.department = (TextView) convertView
					.findViewById(R.id.textViewStudentDepartment);
			viewHolder.section = (TextView) convertView.findViewById(R.id.textViewStudentSection);

		} else
			viewHolder = (ViewHolder) convertView.getTag();

		// skip the first row
		if (position == 0)
		{
			// fill views
			viewHolder.userName.setText("UserName");
			viewHolder.mail.setText("Mail");
			viewHolder.department.setText("Department");
			viewHolder.graduationYear.setText("Graduation year");
			viewHolder.section.setText("Section");
		} else
		{
			// get data
			String userName = listener.getUserName(position - 1);
			String mail = listener.getMail(position - 1);
			String department = listener.getDepartment(position - 1);
			String graduationYear = listener.getGradutionYear(position - 1);
			String section = listener.getSection(position - 1);

			// fill views
			viewHolder.userName.setText(userName);
			viewHolder.mail.setText(mail);
			viewHolder.department.setText(department);
			viewHolder.graduationYear.setText(graduationYear);
			viewHolder.section.setText(section);
		}
		return convertView;
	}

}
