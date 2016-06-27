package de.moelschl.hhnhochschulapp.controller.Forum;

/**
 * Created by Hasbert on 21.06.2016.
 */
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.File;
import de.moelschl.hhnhochschulapp.R;
import de.moelschl.hhnhochschulapp.io.DatabaseHelper;


public class FoActivity extends AppCompatActivity implements View.OnClickListener,
        FoThemeFragment.OnThemeSelectedListener, FoThreadFragment.OnThemeSelectedListener{

    private DatabaseHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //loads the fragments into the activity
        setContentView(R.layout.fo_activty);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_forum);
        setSupportActionBar(toolbar);

        Button newThemeButton = (Button) findViewById(R.id.newItem);
        newThemeButton.setOnClickListener(this);

        initDatabase();

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.forum_toolbar_fragment, new FoToolbarFragment());
        ft.add(R.id.forum_list_fragment, new FoThemeFragment());
        ft.addToBackStack("tag");
        ft.commit();

        setTitle("Forum");
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

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public void onClick(View v) {
        System.out.println("penis");
    }


    @Override
    public void onThemeClicked(String navigationKey) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        FoThreadFragment threadFragment = new FoThreadFragment();
        threadFragment.setNavigationKey(navigationKey);
        ft.replace(R.id.forum_list_fragment, threadFragment);
        ft.addToBackStack("tag");
        ft.commit();

    }

    @Override
    public void onThreadClicked(int navigationKey, String question) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        FoCommentFragment commentFragment = new FoCommentFragment();
        commentFragment.setNavigationKey(navigationKey);
        commentFragment.setQuestionText(question);
        ft.replace(R.id.forum_list_fragment, commentFragment);
        ft.addToBackStack("tag");
        ft.commit();
    }
}




