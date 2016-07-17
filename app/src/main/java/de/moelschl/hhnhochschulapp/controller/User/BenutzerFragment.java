package de.moelschl.hhnhochschulapp.controller.User;

import android.app.Fragment;
import android.content.Context;
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

import java.util.ArrayList;

import de.moelschl.hhnhochschulapp.R;
import de.moelschl.hhnhochschulapp.controller.IliasTutorial1Activity;
import de.moelschl.hhnhochschulapp.controller.IliasTutorial2Activity;
import de.moelschl.hhnhochschulapp.io.DatabaseHelper;
import de.moelschl.hhnhochschulapp.model.User;
import de.moelschl.hhnhochschulapp.tools.OnWindowTitleSet;

import static android.R.*;

/**
 * generates and shows the user environment
 *
 * Created by Hasbert
 */
public class BenutzerFragment extends Fragment{

    private OnUserManage userAction;
    private OnWindowTitleSet titleSetter;
    private DatabaseHelper dbHelper;

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
        View rootView = inflater.inflate(R.layout.benutzer_fragment, container, false);

        ListAdapter itemsAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,  getForumNavItems());

        ListView simpleTextList = (ListView) rootView.findViewById(R.id.simpleList);
        setClickEvent(simpleTextList);

        simpleTextList.setAdapter(itemsAdapter);

        titleSetter.setWindowTitle(User.getUsername());
        return rootView;
    }

    /**
     * a method which creates a arrayl list of content
     */

    private ArrayList<String> getForumNavItems(){
        ArrayList<String> list = new ArrayList<>();
        list.add("gestellte Fragen");
        list.add("mit hilfreich bewertete Kommentare");
        return list;
    }


    public interface OnUserManage{
        void setUserListEvent(int position);
    }

    /**
     * sets a click event on the list of the user fragment
     *
     */

    private void setClickEvent(ListView listView) {
        listView.setOnItemClickListener(
            new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    userAction.setUserListEvent(position);
                }
            }
        );
    }

    /**
     * activated if the interface OnWindowTitleSet will be used
     *
     * @param context the context of the activity
     */

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            titleSetter = (OnWindowTitleSet) context;
            userAction = (OnUserManage) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnWindowTitleSet or OnUserManage");
        }
    }


    /**
     * sets variable to default
     */

    @Override
    public void onDetach() {
        super.onDetach();
        titleSetter = null;
        userAction = null;
    }
}
