package ui.adapters;

import ui.views.ToggleableRadioButton;

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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import core.MCQChoice;

// TODO: Auto-generated Javadoc
/**
 * The Class MCQChoicesAnswerAdapter.
 */
public class MCQChoicesAnswerAdapter extends BaseAdapter
{

	/**
	 * The Interface ChoiceAnswerer.
	 */
	public interface ChoiceAnswerer
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
		public void setCorrectChoice(int questionIdx,  int position, boolean isChecked);


		/**
		 * Checks if is choice checked.
		 *
		 * @param questionIdx the question idx
		 * @param i the i
		 * @return true, if is choice checked
		 */
		public boolean isChoiceChecked(int questionIdx,int i);


		/**
		 * Gets the choice body.
		 *
		 * @param questionIdx the question idx
		 * @param position the position
		 * @return the choice body
		 */
		public String getChoiceBody(int questionIdx, int position);
	}

	/**
	 * The Class ViewHolder.
	 */
	public static class ViewHolder
	{
		
		/** The text view body. */
		TextView textViewBody;
		
		/** The radio button is checked. */
		RadioButton  radioButtonIsChecked;
		
		/** The on checked changed listener. */
		OnCheckedChangeListener onCheckedChangedListener;
		
		/** The on text clicked listener. */
		OnClickListener onTextClickedListener;
	}

	/* fields */
	/** The context. */
	Context context;
	
	/** The listener. */
	ChoiceAnswerer listener;
	
	/** The questionidx. */
	private int questionidx;

	/**
	 * Instantiates a new MCQ choices answer adapter.
	 *
	 * @param context the context
	 * @param listener the listener
	 * @param questionIdx the question idx
	 */
	public MCQChoicesAnswerAdapter(Context context, ChoiceAnswerer listener, int questionIdx)
	{
		super();
		this.context = context;
		this.listener = listener;
		this.questionidx = questionIdx;

	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount()
	{
		return listener.getChoicesCount(questionidx);
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
			convertView = inflater.inflate(com.team_egor.quizor.R.layout.mcq_answer_choice_item,
					null);

			// create views
			viewHolder = new ViewHolder();
			convertView.setTag(viewHolder);
			viewHolder.radioButtonIsChecked = (RadioButton) convertView
					.findViewById(R.id.radioButtonAnswer);
			viewHolder.textViewBody = (TextView) convertView.findViewById(R.id.textViewChoiceBody);

			// make listeners
			viewHolder.onCheckedChangedListener = new OnCheckedChangeListener()
			{

				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
				{
					// change this one
					listener.setCorrectChoice(questionidx, position, isChecked);

					// clear previous
					boolean uiChanged = false;
					if (isChecked)
						for (int i = 0; i < listener.getChoicesCount(questionidx); i++)
							if (i != position)
								{
									boolean isOtherChecked = listener.isChoiceChecked(questionidx, i);
									if (isOtherChecked)
									{
										listener.setCorrectChoice(questionidx ,i, false);
										uiChanged = true;
									}
								}
					if (uiChanged)
						notifyDataSetChanged();
				}
			};
			viewHolder.onTextClickedListener = new OnClickListener()
			{
				
				@Override
				public void onClick(View v)
				{
					viewHolder.radioButtonIsChecked.toggle();
				}
			};
		} else
			viewHolder = (ViewHolder) convertView.getTag();

		// set choice body
		String body = listener.getChoiceBody(questionidx ,position);
		viewHolder.textViewBody.setOnClickListener(null);
		viewHolder.textViewBody.setText(body);
		viewHolder.textViewBody.setOnClickListener(viewHolder.onTextClickedListener);
		
		// set radio button
		boolean isChecked = listener.isChoiceChecked(questionidx, position);
		viewHolder.radioButtonIsChecked.setOnCheckedChangeListener(null);
		viewHolder.radioButtonIsChecked.setChecked(isChecked);
		viewHolder.radioButtonIsChecked.setOnCheckedChangeListener(viewHolder.onCheckedChangedListener);
		
		return convertView;
	}

}
