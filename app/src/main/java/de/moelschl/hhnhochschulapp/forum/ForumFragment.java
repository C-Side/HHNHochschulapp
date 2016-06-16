package de.moelschl.hhnhochschulapp.forum;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import de.moelschl.hhnhochschulapp.R;
import de.moelschl.hhnhochschulapp.forum.XMLStuff.XMLFactory;
import de.moelschl.hhnhochschulapp.forum.model.ForumListItem;

/**
 * Created by moelscmar on 19.05.2016.
 */
public class ForumFragment extends ListFragment implements View.OnClickListener {

    private XMLFactory xmlFactory;

    private CustomAdapter customAdapter;

    private Button backButton;

    private Context context;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.context = getContext();
        this.xmlFactory = new XMLFactory();
        View rootView = inflater.inflate(R.layout.fragment_forum, container, false);


        this.backButton = (Button) rootView.findViewById(R.id.backButton);
        backButton.setOnClickListener(this);

        this.customAdapter = new CustomAdapter(getActivity(), xmlFactory.createTopic(context));
        setListAdapter(customAdapter);


        return rootView;
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ArrayList<ForumListItem> nextList = null;
        ForumListItem listItem = (ForumListItem) l.getAdapter().getItem(position);
        if(listItem.getListHirarchie().equals("theme")){
            nextList = xmlFactory.getSubListByParent(listItem.getTitle());
        }
        else if (listItem.getListHirarchie().equals("subTheme")){
            nextList = xmlFactory.getCommentListByParent(listItem.getTitle());
        }
        else {}

        customAdapter.loadNewData(nextList);
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

    @Override
    public void onClick(View v) {
        customAdapter.setList();
        ArrayList<ForumListItem> lastList = customAdapter.getLastList();
        customAdapter.loadNewData(lastList);

       /**
        switch (v.getId()) {
            case R.id.textView_help:
                switchFragment(HelpFragment.TAG);
                break;
            case R.id.textView_settings:
                switchFragment(SettingsFragment.TAG);
                break;
        */
    }
}


