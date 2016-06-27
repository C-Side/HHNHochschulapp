package de.moelschl.hhnhochschulapp.controller.Forum;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.moelschl.hhnhochschulapp.R;
import de.moelschl.hhnhochschulapp.io.DatabaseHelper;
import de.moelschl.hhnhochschulapp.tools.CommentAdapter;


/**
 * fragment displays a list of comments and a textbox with a question
 *
 */

public class FoCommentFragment extends ListFragment{

    private CommentAdapter commentAdapter;
    private DatabaseHelper dbHelper;
    private int navigationKey;
    private String questionText;

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
        View rootView = inflater.inflate(R.layout.fo_comment_fragment, container, false);
        TextView questiontext = (TextView) rootView.findViewById(R.id.question_text_com);
        questiontext.setText(questionText);

        this.context = getContext();
        this.dbHelper = new DatabaseHelper(context);
        this.commentAdapter = new CommentAdapter(getActivity(), dbHelper.getCommentList(navigationKey));
        setListAdapter(commentAdapter);

        return rootView;
    }


    /**
     * sets the navigation key to navigate to the right comments inside the database
     *
     * @param key
     */

    public void setNavigationKey(int key){
        this.navigationKey = key;
    }

    /**
     * sets the navigation key to navigate to the right comments inside the database
     *
     * @param question the question
     */

    public void setQuestionText(String question){
        this.questionText = question;
    }
}
