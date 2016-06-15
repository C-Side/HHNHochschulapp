package de.moelschl.hhnhochschulapp.forum;

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
import de.moelschl.hhnhochschulapp.forum.XMLStuff.XMLFactory;
import de.moelschl.hhnhochschulapp.forum.model.ForumListItem;

/**
 * Created by moelscmar on 19.05.2016.
 */
public class ForumFragment extends ListFragment {

    private XMLFactory xmlFactory;

    private CustomAdapter customAdapter;

    private Context context;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.context = getContext();
        //getDataInList();
        xmlFactory = new XMLFactory();
        //testXML();
        this.customAdapter = new CustomAdapter(getActivity(), xmlFactory.createTopic(context)); //!!!!
        setListAdapter(customAdapter);


        View rootView = inflater.inflate(R.layout.fragment_forum, container, false);
        return rootView;
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        ForumListItem listItem = (ForumListItem) l.getAdapter().getItem(position);
        ArrayList<ForumListItem> nextList = xmlFactory.getSubListByParent(listItem.getTitle());

        customAdapter.loadSubCategorys(nextList);
    }

    public void testXML(){
        this.xmlFactory = new XMLFactory();
        try {
            xmlFactory.createTopic(context);
        }
        catch (Exception exeption){
            exeption.printStackTrace();
        }

    }
}


