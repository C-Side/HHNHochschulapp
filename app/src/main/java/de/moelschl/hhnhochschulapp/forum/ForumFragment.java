package de.moelschl.hhnhochschulapp.forum;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import de.moelschl.hhnhochschulapp.R;

/**
 * Created by moelscmar on 19.05.2016.
 */
public class ForumFragment extends ListFragment {
    private String [] itemContent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        this.itemContent = getResources().getStringArray(R.array.topic);

        ListAdapter adapter = new ListAdapter(getActivity(), itemContent);

        setListAdapter(adapter);

        View rootView = inflater.inflate(R.layout.fragment_forum, container, false);
        return rootView;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        //View child = l.getChildAt(position);
        String childname = itemContent[position];
        Toast.makeText(getActivity(),childname, Toast.LENGTH_LONG).show();
    }
}

