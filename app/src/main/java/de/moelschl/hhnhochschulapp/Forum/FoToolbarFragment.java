package de.moelschl.hhnhochschulapp.Forum;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.moelschl.hhnhochschulapp.R;

/**
 * Created by Hasbert on 22.06.2016.
 */
public class FoToolbarFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.fo_activity_toolbar, container, false);

        return view;
    }
}