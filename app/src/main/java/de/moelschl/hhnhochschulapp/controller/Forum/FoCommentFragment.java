package de.moelschl.hhnhochschulapp.controller.Forum;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import de.moelschl.hhnhochschulapp.R;
import de.moelschl.hhnhochschulapp.io.DatabaseHelper;
import de.moelschl.hhnhochschulapp.model.CommentListItem;
import de.moelschl.hhnhochschulapp.tools.CommentAdapter;


/**
 * fragment displays a list of comments and a textbox with a question
 *
 */

public class FoCommentFragment extends ListFragment implements View.OnClickListener {

    private Context context;
    private CommentAdapter commentAdapter;
    private DatabaseHelper dbHelper;
    private int navigationKey;

    private String questionHeader;
    private String questionText;

    private EditText answerText;


    /**
     * the initialization method is like a constructor. it loads the main layout and set them
     * active, then gives the information to other classes.
     *
     * @param inflater           Instantiates a layout XML file into its corresponding View Objects.
     * @param container          Container for View Objects.
     * @param savedInstanceState a mapping form String values to whatever you want.
     * @return the showable View.
     */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fo_comment_fragment, container, false);

        TextView questionHeaderView = (TextView) rootView.findViewById(R.id.question_header_com);

        TextView questionTextView = (TextView) rootView.findViewById(R.id.question_text_com);

        questionHeaderView.setText(questionHeader);
        questionTextView.setText(questionText);

        this.answerText = (EditText) rootView.findViewById(R.id.comment_anwer_field);

        Button saveAnswerButton = (Button) rootView.findViewById(R.id.save_comment);
        saveAnswerButton.setOnClickListener(this);

        this.context = getContext();
        this.dbHelper = new DatabaseHelper(context);
        this.commentAdapter = new CommentAdapter(getActivity(), dbHelper.getCommentList(navigationKey));
        setListAdapter(commentAdapter);




        return rootView;
    }


    /**
     * sets the navigation key to navigate to the right comments inside the database
     *
     * @param key the key of the pressed item
     */

    public void setNavigationKey(int key) {
        this.navigationKey = key;
    }

    /**
     * sets the question text and header in the comments fragment
     *
     * @param question the question
     */

    public void setQuestion(String questionHeader, String question) {

        this.questionHeader = questionHeader;
        this.questionText = question;
    }


    /**
     * Uese the OnClickLIstener interface to ovverride onClick method. The method calls another
     * interface method, which sends the information to the activity
     *
     * @param v the clicked button view
     */

    @Override
    public void onClick(View v) {
        int id = navigationKey;
        String answer = answerText.getText().toString();
        dbHelper.addAnswerToComment(id, answer);
        commentAdapter.refresh(dbHelper.getCommentList(navigationKey));
    }

    /**
    private void getCommentOrDie(){
        if(dbHelper.getCommentList(navigationKey).isEmpty()){
            throw noCommentsInListE
        }

    }
     */
}
