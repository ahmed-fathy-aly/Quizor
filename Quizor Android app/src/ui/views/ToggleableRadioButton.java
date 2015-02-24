package ui.views;

import android.content.Context;
import android.widget.RadioButton;
import android.widget.RadioGroup;

// TODO: Auto-generated Javadoc
/**
 * The Class ToggleableRadioButton.
 */
public class ToggleableRadioButton extends RadioButton {
    // Implement necessary constructors

    /**
     * Instantiates a new toggleable radio button.
     *
     * @param context the context
     */
    public ToggleableRadioButton(Context context)
	{
		super(context);
	}

	/* (non-Javadoc)
	 * @see android.widget.RadioButton#toggle()
	 */
	@Override
    public void toggle() {
        if(isChecked()) {
            if(getParent() instanceof RadioGroup) {
                ((RadioGroup)getParent()).clearCheck();
            }
        } else {
            setChecked(true);
        }
    }
}
