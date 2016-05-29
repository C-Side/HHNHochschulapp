package de.moelschl.hhnhochschulapp;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * Created by moelscmar on 19.05.2016.
 */
public class ForumFragment extends ListFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        String [] itemContent = getResources().getStringArray(R.array.topic);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, itemContent);

        setListAdapter(adapter);

        View rootView = inflater.inflate(R.layout.fragment_forum, container, false);
        return rootView;
    }
}

