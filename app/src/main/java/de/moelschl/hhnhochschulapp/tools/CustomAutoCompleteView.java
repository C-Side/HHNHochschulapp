package de.moelschl.hhnhochschulapp.tools;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

/**
 * Created by Hasbert on 27.06.2016.
 */
public class CustomAutoCompleteView extends AutoCompleteTextView {

    private OnThemeChooseListener listener; //is in USE!!


    public CustomAutoCompleteView(Context context) {
        super(context);
        onInitListener(context);

    }

    public CustomAutoCompleteView(Context context, AttributeSet attrs) {
        super(context, attrs);
        onInitListener(context);
    }

    public CustomAutoCompleteView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        onInitListener(context);
    }



    @Override
    protected void performFiltering(final CharSequence text, final int keyCode) {
        String filterText = "";
        super.performFiltering(filterText, keyCode);
    }



    @Override
    protected void replaceText(final CharSequence text) {
        listener.onDropDownItemClick(text.toString());
        super.replaceText(text);

    }

    public interface OnThemeChooseListener{
        void onDropDownItemClick(String text);
    }

    /**
     * checks that the activity has implementated the interface and sets the interface, so it can
     * perform
     *
     * @param context {@link android.content.Context}
     */

    public void onInitListener(Context context){
        try {
            listener = (OnThemeChooseListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }
}
