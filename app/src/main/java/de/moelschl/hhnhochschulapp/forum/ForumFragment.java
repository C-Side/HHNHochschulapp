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
 * this class is the base logic of the forum. The forum is is a visible list of themes, subthemes and
 * comments which cna be editted. the class provides methods for click handling and has a connection
 * to the custom Adapter who is responsible for the visualization and the XMLFactory where the data
 * to show, comes from. FomumFragment implements Listfragment to make use of a proper custom List.
 *
 *
 */
public class ForumFragment extends ListFragment implements View.OnClickListener {

    private CustomAdapter customAdapter;

    private Button backButton;

    private Context context;


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
        this.context = getContext();

        View rootView = inflater.inflate(R.layout.fragment_forum, container, false);

        this.backButton = (Button) rootView.findViewById(R.id.backButton);
        backButton.setOnClickListener(this);

        this.customAdapter = new CustomAdapter(getActivity(), XMLFactory.createTopic(context));
        setListAdapter(customAdapter);

        return rootView;
    }

    /**
     * loads new items inside the list. The listitem in each list (themeList, subthemeList, commentList)
     * are connected in a StringBuild way, so every new list is loaded by the clicked itemName of
     * the listItem.
     *
     * @param l {@link android.widget.ListView}
     * @param v {@link android.view.View}
     * @param position the position in the list
     * @param id serializable number
     */

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ArrayList<ForumListItem> nextList = null;
        ForumListItem listItem = (ForumListItem) l.getAdapter().getItem(position);

        if (listItem.getListHirarchie().equals("comment")){

        }
        else {
            if (listItem.getListHirarchie().equals("theme")) {
                nextList = XMLFactory.getSubListByParent(listItem.getTitle());
            } else if (listItem.getListHirarchie().equals("subTheme")) {
                nextList = XMLFactory.getCommentListByParent(listItem.getTitle());
            } else {
            }

            customAdapter.loadNewData(nextList);
        }

        System.out.println("Error there is no fragment");
    }

    /**
     * goes back with a buttonListener method.
     * @param v the clicked button view
     */

    @Override
    public void onClick(View v) {
        ArrayList <ForumListItem> lastList = null;

        if (customAdapter.getMyList().isEmpty()){
            lastList = XMLFactory.createTopic(context);

            System.out.println("Error: there is no item inside the subcatList so i cant load the" +
                    "previous depending on the current list");
        }
        else {
            if(customAdapter.getMyList().get(0).getListHirarchie().equals("comment")){
                lastList = XMLFactory.getSubThemeList();
            }
            else if (customAdapter.getMyList().get(0).getListHirarchie().equals("subTheme")){
                lastList = XMLFactory.getThemeList();
            }
            else {}
        }

        customAdapter.goBack(lastList);
    }
}


