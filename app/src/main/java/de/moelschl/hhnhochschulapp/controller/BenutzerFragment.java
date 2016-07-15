package de.moelschl.hhnhochschulapp.controller;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.moelschl.hhnhochschulapp.R;
import de.moelschl.hhnhochschulapp.model.User;

/**
 * generates and shows the user environment
 *
 * Created by Hasbert
 */
public class BenutzerFragment extends Fragment {

    private OnWindowTitleSet titleSetter;

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
        View rootView = inflater.inflate(R.layout.benutzer_fragment, container, false);
        titleSetter.setWindowTitle(User.getUsername());
        return rootView;
    }

    /**
     * activated if the interface OnWindowTitleSet will be used
     *
     * @param context the context of the activity
     */

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            titleSetter = (OnWindowTitleSet) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnThreadManage or OnWindowTitleSet");
        }
    }


    /**
     * sets variable to default
     */

    @Override
    public void onDetach() {
        super.onDetach();
        titleSetter = null;
    }
}
