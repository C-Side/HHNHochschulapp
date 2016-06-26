package de.moelschl.hhnhochschulapp.controller.Forum;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;
import de.moelschl.hhnhochschulapp.R;
import de.moelschl.hhnhochschulapp.model.ThemeListItem;
import de.moelschl.hhnhochschulapp.io.DatabaseHelper;
import de.moelschl.hhnhochschulapp.tools.ThemeAdapter;
import de.moelschl.hhnhochschulapp.tools.OnSwipeTouchListener;

/**
 * this class provides a List Fragment which will be placed in a activity. It gets information of
 * the database and shows the full table
 *
 */

public class FoThemeFragment extends ListFragment {

    private ThemeAdapter themeAdapter;
    private DatabaseHelper dbHelper;
    private OnThemeSelectedListener listener;
    private Context context;


    /**
     *
     * the initialization method is like a constructor. it loads the main layout and set them
     * active, then gives the information to other classes.
     *
     * @param inflater Instantiates a layout XML file into its corresponding View Objects.
     * @param container Container for View Objects.
     * @param savedInstanceState a mapping form String values to whatever you want.
     * @return the showable View.
     */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fo_theme_fragment, container, false);
        //activateBackSwipeRightLeft(rootView);

        this.context = getContext();
        this.dbHelper = new DatabaseHelper(context);
        this.themeAdapter = new ThemeAdapter(getActivity(), dbHelper.getThemeList());
        setListAdapter(themeAdapter);

        return rootView;
    }

    /**
     * calles the inner class method onThemeClicked and gives the activity the full control
     *
     * @param l {@link android.widget.ListView}
     * @param v {@link android.view.View}
     * @param position the position in the list
     * @param id serializable number
     */

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        listener.onThemeClicked(position);
    }

    /**
     * Interface to communicate with the activity
     *
     */

    public interface OnThemeSelectedListener {
        void onThemeClicked(int postition);
    }

    /**
     * checks that the activity has implemaentated OnThemeSelectedListener
     *
     * @param context {@link android.content.Context}
     */

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
        try {
            listener = (OnThemeSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }


    /**
     * sets variable to default
     */

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}



