package de.moelschl.hhnhochschulapp;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by moelscmar on 19.05.2016.
 */
public class TutorialsFragment extends Fragment {

    private View myFragmentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        myFragmentView = inflater.inflate(R.layout.fragment_tutorials, container, false);
        initListViewIlias();
        return myFragmentView;
    }

    private void initListViewIlias() {
        String[] iliasTutorials = {"Ilias-Kurse beitreten", "Ilias-Kurse verlassen"};
        ListAdapter messageAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, iliasTutorials);
        ListView myListView = (ListView) myFragmentView.findViewById(R.id.listViewIlias);
        myListView.setAdapter(messageAdapter);

    }
}
