package de.moelschl.hhnhochschulapp.forum;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import de.moelschl.hhnhochschulapp.R;
import de.moelschl.hhnhochschulapp.forum.XMLStuff.XMLFactory;
import de.moelschl.hhnhochschulapp.forum.model.SubTheme;
import de.moelschl.hhnhochschulapp.forum.model.Theme;

/**
 * Created by moelscmar on 19.05.2016.
 */
public class ForumFragment extends ListFragment {

    private String [] title;
    private String [] desc;
    private int image;
    //private ArrayList<Theme> myList = new ArrayList<>();

    private String[] subtitle;
    private String[] comments;
    //private ArrayList<Theme> mySubList = new ArrayList<>();

    private CutsomAdapter cutsomAdapter;

    private Context context;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.context = getContext();
        //getDataInList();
        testXML();
        this.cutsomAdapter = new CutsomAdapter(getActivity(), Theme.getConvertedThemeList()); //!!!!
        setListAdapter(cutsomAdapter);


        View rootView = inflater.inflate(R.layout.fragment_forum, container, false);
        return rootView;
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if(clickedItem.isInstanceOf(SubTheme)){
            loadSubInfoList;
        }

        Theme pulledTheme = (Theme) l.getAdapter().getItem(position);

        cutsomAdapter.loadSubCategorys(pulledTheme.getSubThemeList());

        //customAdapter.loadSubCats(LISTCONText) => dann den geklickten

        //String childname = mySubList.get(position).getTitle();
        //Toast.makeText(getActivity(),childname, Toast.LENGTH_LONG).show();
    }



    /**
    private void getDataInList() {
        this.title =  getResources().getStringArray(R.array.topic);
        this.desc =  getResources().getStringArray(R.array.description);
        this.image = R.drawable.goimage;

        for (int i = 0; i < title.length; i++) {
            // Create a new object for each list item
            Theme listData = new Theme();
            listData.setTitle(title[i]);
            listData.setDescription(desc[i]);
            listData.setImgResId(image);

            // Add this object into the ArrayList myList
            myList.add(listData);
        }
    }

    private void getDataInSubList(int position){
        this.subtitle =  getResources().getStringArray(R.array.subtitle);
        this.comments =  getResources().getStringArray(R.array.comments);
        this.image = R.drawable.goimage;

        int posOfParent = position;

        for (int i = 0; i < subtitle.length; i++) {
            // Create a new object for each list item
            Theme listData = new Theme();
            listData.setTitle(subtitle[i]);
            listData.setDescription(comments[i]);
            listData.setImgResId(image);

            // Add this object into the ArrayList myList
            mySubList.add(listData);
        }
    }
     */

    public void testXML(){
        XMLFactory xmlFactory = new XMLFactory();
        try {
            xmlFactory.createTopic(context);
        }
        catch (Exception exeption){
            exeption.printStackTrace();
        }

    }
}


