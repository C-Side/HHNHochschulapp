package de.moelschl.hhnhochschulapp.controller.Forum;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import de.moelschl.hhnhochschulapp.R;
import de.moelschl.hhnhochschulapp.controller.OnWindowTitleSet;
import de.moelschl.hhnhochschulapp.io.DatabaseHelper;
import de.moelschl.hhnhochschulapp.model.ThreadListItem;
import de.moelschl.hhnhochschulapp.tools.ThreadAdapter;

/**
 * Created by Hasbert on 24.06.2016.
 */
public class FoThreadFragment extends ListFragment implements View.OnClickListener{
    private ThreadAdapter threadAdapter;
    private DatabaseHelper dbHelper;
    private String navigationKey;

    private OnWindowTitleSet titleSetter;
    private OnThreadManage listener;
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
        View rootView = inflater.inflate(R.layout.fo_threads_fragment, container, false);

        Button newQuestion = (Button) rootView.findViewById(R.id.new_question_button);
        newQuestion.setOnClickListener(this);

        this.context = getContext();
        this.dbHelper = new DatabaseHelper(context);
        this.threadAdapter = new ThreadAdapter(getActivity(), dbHelper.getThreadList(navigationKey));
        titleSetter.setWindowTitle(navigationKey.substring(0, 1).toUpperCase() +
                navigationKey.substring(1));
        setListAdapter(threadAdapter);

        return rootView;
    }

    /**
     *
     * @param key
     */

    public void setNavigationKey(String key){
        this.navigationKey = key;
    }


    /**
     * Interface to communicate with the activity
     *
     */

    public interface OnThreadManage {
        void onThreadClicked(int postition, String question, String questionHeader);
        void onNewQuestionClick();
    }


    /**
     *
     * @param v
     */

    @Override
    public void onClick(View v) {
        listener.onNewQuestionClick();
    }


    /**
     * calles the inner class method onThemeClicked and gives the activity the full control
     *
     * @param l {@link android.widget.ListView}
     * @param v {@link android.view.View}
     * @param position the position in the list
     * @param id serializable number
     */

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ThreadListItem threadListItem = (ThreadListItem) l.getAdapter().getItem(position);
        int key = threadListItem.getId();
        String question = threadListItem.getQuestionText();
        String questionHeader = threadListItem.getQuestionHeader();
        listener.onThreadClicked(key, question, questionHeader);
    }



    /**
     * checks that the activity has implemaentated OnThreadManage
     *
     * @param context {@link android.content.Context}
     */

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
        try {
            listener = (OnThreadManage) context;
            titleSetter = (OnWindowTitleSet) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnThreadManage or OnWindowTitleSet");
        }
    }


    /**
     * sets variable to default
     */

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
        titleSetter = null;
    }
}
