package de.moelschl.hhnhochschulapp.controller.Forum;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.Toast;
import java.io.File;
import java.util.Stack;

import de.moelschl.hhnhochschulapp.R;
import de.moelschl.hhnhochschulapp.io.DatabaseHelper;
import de.moelschl.hhnhochschulapp.tools.CustomAutoCompleteView;

/**
 * Created by Hasbert on 21.06.2016.
 */
public class FoActivity extends AppCompatActivity implements FoThemeFragment.OnThemeSelectedListener,
        FoThreadFragment.OnThemeSelectedListener, CustomAutoCompleteView.OnThemeChooseListener,
        FoQuestionAdder.OnStoreData{

    private DatabaseHelper mDBHelper;
    private Stack<String> titleStack;
    private int stackWatcher = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //loads the fragments into the activity
        setContentView(R.layout.fo_activty);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_forum);
        setSupportActionBar(toolbar);

        initDatabase();

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.forum_toolbar_fragment, new FoToolbarFragment());
        ft.add(R.id.forum_list_fragment, new FoThemeFragment());
        ft.addToBackStack("tag");
        ft.commit();

        titleStack = new Stack<>();
        setTitle("Themen");
    }


    /**
     * initialize the Database and prints out a text
     *
     */

    private void initDatabase(){
        mDBHelper = new DatabaseHelper(this);

        //Check exists database
        File database = getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if(false == database.exists()){
            mDBHelper.getReadableDatabase();
            //copy Db
            if (mDBHelper.copyDatabase(this)){
                Toast.makeText(this, "Copy database successful", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Copy error", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

    /**
     * a helper method to replace the current Frgament with a new Fragment.
     *
     * @param newFragment the next fragment
     */

    private void switchFragment(Fragment newFragment){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.forum_list_fragment, newFragment);
        ft.addToBackStack("tag");
        ft.commit();
    }


    /**
     * switches back to the last fragment when the back button is pressed
     *
     */

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            getFragmentManager().popBackStack();
            popTitleStack();
        } else {
            super.onBackPressed();
        }
    }


    /**
     *
     * @param navigationKey
     */

    @Override
    public void onThemeClicked(String navigationKey) {
        FoThreadFragment threadFragment = new FoThreadFragment();
        threadFragment.setNavigationKey(navigationKey);
        switchFragment(threadFragment);
        addToTitleStack(firstToUpperCase(navigationKey));
    }


    /**
     *
     * @param navigationKey
     * @param question
     */

    @Override
    public void onThreadClicked(int navigationKey, String question, String questionHeader) {
        FoCommentFragment commentFragment = new FoCommentFragment();
        commentFragment.setNavigationKey(navigationKey);
        commentFragment.setQuestion(question, questionHeader);
        switchFragment(commentFragment);
        addToTitleStack("Kommentare");
    }


    /**
     *
     */

    @Override
    public void onNewQuestionClick() {
        FoQuestionAdder questionFragment = new FoQuestionAdder();
        switchFragment(questionFragment);
    }


    /**
     *
     * @param text
     */

    @Override
    public void onDropDownItemClick(String text) {
        EditText themeDesc = (EditText) findViewById(R.id.theme_desc_text);
        String description = mDBHelper.getDescByTopic(text);
        themeDesc.setText(description);
    }

    /**
     *
     *
     * @param themeTopic
     * @param themeDesc
     * @param questionHeader
     * @param question
     * @param context
     */

    @Override
    public void storeNewQuestion(String themeTopic, String themeDesc, String questionHeader, String question, Context context) {
        mDBHelper.addQuestionToDatabase(themeTopic, themeDesc, questionHeader, question, context);
        onBackPressed();
    }

    /**
     * makes the first letter of a String to Upper case
     *
     */

    private String firstToUpperCase(String word) {
        String upperLetter = word.substring(0,1).toUpperCase();
        return upperLetter.concat(word.substring(1,word.length()));
    }


    /**
     * a simple stack to pop title strings from
     * ok not that simple =D
     *
     */

    private void popTitleStack() {
        if (stackWatcher == 1){
            String title = "Themen";
            titleStack.push(title);
            setTitle(title);
        }
        setTitle(titleStack.pop());
        stackWatcher --;
    }


    /**
     * a simple stack to push title strings to
     *
     */

    private void addToTitleStack(String title){
        if (stackWatcher == 1){}
        else titleStack.push(title);
        setTitle(title);
        stackWatcher++;
    }
}




