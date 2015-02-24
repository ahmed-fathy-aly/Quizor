package ui.adapters;

import java.util.List;

import org.json.JSONArray;

import com.team_egor.quizor.R;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import core.MCQChoice;

// TODO: Auto-generated Javadoc
/**
 * The Class MCQChoicesEditAdapter.
 */
public class MCQChoicesEditAdapter extends BaseAdapter
{

	/**
	 * The Interface ChoiceEditor.
	 */
	public interface ChoiceEditor
	{
		
		/**
		 * Gets the choices count.
		 *
		 * @param questionIdx the question idx
		 * @return the choices count
		 */
		public int getChoicesCount(int questionIdx);

		/**
		 * Sets the correct choice.
		 *
		 * @param questionIdx the question idx
		 * @param position the position
		 * @param isChecked the is checked
		 */
		public void setCorrectChoice(int questionIdx, int position, boolean isChecked);

		/**
		 * Checks if is choice checked.
		 *
		 * @param questionIdx the question idx
		 * @param i the i
		 * @return true, if is choice checked
		 */
		public boolean isChoiceChecked(int questionIdx, int i);

		/**
		 * Sets the choice body.
		 *
		 * @param questionIdx the question idx
		 * @param position the position
		 * @param string the string
		 */
		public void setChoiceBody(int questionIdx, int position, String string);

		/**
		 * Gets the choice body.
		 *
		 * @param questionIdx the question idx
		 * @param position the position
		 * @return the choice body
		 */
		public String getChoiceBody(int questionIdx, int position);

		/**
		 * Delete choice.
		 *
		 * @param questionIdx the question idx
		 * @param pos the pos
		 */
		public void deleteChoice(int questionIdx, int pos);

	}

	/**
	 * The Class ViewHolder.
	 */
	public static class ViewHolder
	{
		
		/** The check box. */
		CheckBox checkBox;
		
		/** The edit text. */
		EditText editText;
		
		/** The image button. */
		ImageButton imageButton;
		
		/** The text watcher. */
		TextWatcher textWatcher;
		
		/** The on check change listener. */
		OnCheckedChangeListener onCheckChangeListener;
		
		/** The on delete click listener. */
		OnClickListener onDeleteClickListener;
	}

	/* fields */
	/** The context. */
	Context context;
	
	/** The listener. */
	ChoiceEditor listener;
	
	/** The question idx. */
	int questionIdx;

	/**
	 * Instantiates a new MCQ choices edit adapter.
	 *
	 * @param context the context
	 * @param listener the listener
	 * @param questionIdx the question idx
	 */
	public MCQChoicesEditAdapter(Context context, ChoiceEditor listener, int questionIdx)
	{
		super();
		this.context = context;
		this.listener = listener;
		this.questionIdx = questionIdx;

	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount()
	{
		return listener.getChoicesCount(questionIdx);
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
	public View getView(final int position, View convertView, ViewGroup parent)
	{
		// check previous view
		final ViewHolder viewHolder;
		if (convertView == null)
		{
			// inflate
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater
					.inflate(com.team_egor.quizor.R.layout.mcq_edit_choice_item, null);

			// create views
			viewHolder = new ViewHolder();
			convertView.setTag(viewHolder);
			viewHolder.checkBox = (CheckBox) convertView
					.findViewById(com.team_egor.quizor.R.id.checkBoxIsChoiceCorrect);
			viewHolder.editText = (EditText) convertView
					.findViewById(com.team_egor.quizor.R.id.editTextChoice);
			viewHolder.imageButton = (ImageButton) convertView
					.findViewById(R.id.imageButtonDeleteChoice);
			// create listeners
			viewHolder.textWatcher = new TextWatcher()
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
					int pos = (int) viewHolder.editText.getTag();
					listener.setChoiceBody(questionIdx, pos, s.toString());
				}
			};
			viewHolder.onCheckChangeListener = new OnCheckedChangeListener()
			{

				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
				{
					int pos = (int) buttonView.getTag();
					listener.setCorrectChoice(questionIdx, pos, isChecked);
				}
			};
			viewHolder.onDeleteClickListener = new OnClickListener()
			{

				@Override
				public void onClick(View v)
				{
					int pos = (int) v.getTag();
					listener.deleteChoice(questionIdx, pos);
					notifyDataSetChanged();
				}
			};

		} else
			viewHolder = (ViewHolder) convertView.getTag();

		// set check box
		viewHolder.checkBox.setTag(position);
		viewHolder.checkBox.setOnCheckedChangeListener(null);
		viewHolder.checkBox.setChecked(listener.isChoiceChecked(questionIdx, position));
		viewHolder.checkBox.setOnCheckedChangeListener(viewHolder.onCheckChangeListener);

		// set edit text
		viewHolder.editText.setTag(position);
		viewHolder.editText.removeTextChangedListener(viewHolder.textWatcher);
		viewHolder.editText.setText(listener.getChoiceBody(questionIdx, position));
		viewHolder.editText.addTextChangedListener(viewHolder.textWatcher);

		// set image button
		viewHolder.imageButton.setTag(position);
		viewHolder.imageButton.setOnClickListener(viewHolder.onDeleteClickListener);
		return convertView;
	}

}
