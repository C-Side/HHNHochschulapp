package de.moelschl.hhnhochschulapp;

import android.app.Fragment;
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
public class BenachrichtigungenFragment extends Fragment {
    private View myFragmentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        myFragmentView = inflater.inflate(R.layout.fragment_benachrichtigungen, container, false);

        init();
        return myFragmentView;
    }

    private void init() {
        String[] messages = {"Doneit: SV2 Ergebnisse","System: Hochwasser","System: Parkplatz geschlossen"};
        ListAdapter messageAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, messages);
        ListView myListView = (ListView) myFragmentView.findViewById(R.id.listView1);
        myListView.setAdapter(messageAdapter);

        myListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        android.app.FragmentManager fm = getFragmentManager();
                        fm.beginTransaction().replace(R.id.content_frame, new NachrichtFragment()).commit();
                    }
                }
        );
    }
}
