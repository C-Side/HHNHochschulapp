package de.moelschl.hhnhochschulapp.forum;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.content.res.XmlResourceParser;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.xml.sax.helpers.XMLReaderFactory;

import java.util.ArrayList;

import de.moelschl.hhnhochschulapp.R;

/**
 * Created by moelscmar on 19.05.2016.
 */
public class ForumFragment extends ListFragment {

    private String [] title;
    private String [] desc;
    private int image;
    private ArrayList<ListContext> myList = new ArrayList<>();

    private String[] subtitle;
    private String[] comments;
    private ArrayList<ListContext> mySubList = new ArrayList<>();

    private CutsomAdapter cutsomAdapter;





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getDataInList();
        this.cutsomAdapter = new CutsomAdapter(getActivity(), myList);
        setListAdapter(cutsomAdapter);


        View rootView = inflater.inflate(R.layout.fragment_forum, container, false);
        return rootView;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        getDataInSubList(position);
        cutsomAdapter.loadSubCategorys(mySubList);
        //String childname = mySubList.get(position).getTitle();
        //Toast.makeText(getActivity(),childname, Toast.LENGTH_LONG).show();
    }


    private void getDataInList() {
        this.title =  getResources().getStringArray(R.array.topic);
        this.desc =  getResources().getStringArray(R.array.description);
        this.image = R.drawable.goimage;

        for (int i = 0; i < title.length; i++) {
            // Create a new object for each list item
            ListContext listData = new ListContext();
            listData.setTitle(title[i]);
            listData.setDescription(desc[i]);
            listData.setImgResId(image);

            // Add this object into the ArrayList myList
            myList.add(listData);
        }
    }

    private void getDataInSubList(int position){
        this.subtitle =  getResources().getStringArray(R.array.subtitile);
        this.comments =  getResources().getStringArray(R.array.comments);
        this.image = R.drawable.goimage;

        int posOfParent = position;

        for (int i = 0; i < subtitle.length; i++) {
            // Create a new object for each list item
            ListContext listData = new ListContext();
            listData.setTitle(subtitle[i]);
            listData.setDescription(comments[i]);
            listData.setImgResId(image);

            // Add this object into the ArrayList myList
            mySubList.add(listData);
        }
    }
}

