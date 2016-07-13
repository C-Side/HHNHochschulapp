package de.moelschl.hhnhochschulapp.controller;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

import de.moelschl.hhnhochschulapp.R;
import de.moelschl.hhnhochschulapp.controller.Forum.FoCommentFragment;
import de.moelschl.hhnhochschulapp.controller.Forum.FoQuestionAdder;
import de.moelschl.hhnhochschulapp.controller.Forum.FoThemeFragment;
import de.moelschl.hhnhochschulapp.controller.Forum.FoThreadFragment;
import de.moelschl.hhnhochschulapp.io.DatabaseHelper;
import de.moelschl.hhnhochschulapp.tools.CustomAutoCompleteView;

/**
 * the activity which holds the navigation-sets like appbar and toolbar and controls the action
 * of the applied fragments. Is a controller for Forum Fragments.
 *
 */


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FoThemeFragment.OnThemeSelectedListener,
        FoThreadFragment.OnThemeSelectedListener, CustomAutoCompleteView.OnThemeChooseListener,
        FoQuestionAdder.OnStoreData {

    private DatabaseHelper mDBHelper;
    private ImageView kalenderImage;
    private ImageView einstellungenImage;
    private ImageView homeImage;
    private ImageView nachrichtenImage;
    private ImageView iliasImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        openHome();
        setAllImageListeners();
        //setAppbar();
    }

    /**
     * switches the Fragment
     *
     * @param newFragment the new Fragment wich should be shown
     */

    private void switchFragment(Fragment newFragment){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.content_frame, newFragment);
        ft.addToBackStack("tag");
        ft.commit();
    }

    /**
     * switches back to the last fragment when the back button is pressed
     *
     * this is the method of the old FoActivity


    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            getFragmentManager().popBackStack();
            popTitleStack();
        } else {
            super.onBackPressed();
        }
    }
     */


    /**
     * switches back to the last fragment when the back button is pressed
     */

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else if (getFragmentManager().getBackStackEntryCount() > 1) {
            getFragmentManager().popBackStack();
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
           finish();
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        android.app.FragmentManager fm = getFragmentManager();

        if (id == R.id.nav_home) {
            openHome();
        } else if (id == R.id.nav_kalender) {
            openKalender();
        } else if (id == R.id.nav_ilias) {
            openIlias();
        } else if (id == R.id.nav_linda) {
            openLinda();
        } else if (id == R.id.nav_forum) {
            openForum();
        } else if (id == R.id.nav_benachrichtigungen) {
            openBenachrichtigungen();
        } else if (id == R.id.nav_tutorials) {
            openTutorial();
        } else if (id == R.id.nav_einstellungen) {
            openEinstellungen();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setAllImageListeners() {
        kalenderImage = (ImageView) findViewById(R.id.Kalender);
        einstellungenImage = (ImageView) findViewById(R.id.einstellungen);
        homeImage = (ImageView) findViewById(R.id.Home);
        nachrichtenImage = (ImageView) findViewById(R.id.Benachrichtigungen);
        iliasImage = (ImageView) findViewById(R.id.ilias);

        kalenderImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openKalender();
            }
        });

        homeImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openHome();
            }
        });

        nachrichtenImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openBenachrichtigungen();
            }
        });

        iliasImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openIlias();
            }
        });

        einstellungenImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openEinstellungen();
            }
        });
    }

    private void openKalender(){
        KalenderFragment kalenderFragment = new KalenderFragment();
        switchFragment(kalenderFragment);
        setTitle("Kalender");
    }

    private void openHome(){
        HomeFragment homeFragment = new HomeFragment();
        switchFragment(homeFragment);
        setTitle("HHN Hochschulapp");
    }

    private void openBenachrichtigungen(){
        BenachrichtigungenFragment benachrichtigungenFragment = new BenachrichtigungenFragment();
        switchFragment(benachrichtigungenFragment);
        setTitle("Benachrichtigungen");
    }

    private void openIlias(){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://ilias.hs-heilbronn.de/login.php?target=&soap_pw=&ext_uid=&cookies=nocookies&client_id=hshn&lang=de"));
        startActivity(browserIntent);
    }

    private void openEinstellungen(){
        EinstellungenFragment einstellungenFragment = new EinstellungenFragment();
        switchFragment(einstellungenFragment);
        setTitle("Einstellungen");
    }

    private void openLinda(){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://linda.hs-heilbronn.de/"));
        startActivity(browserIntent);
    }

    private void openTutorial(){
        TutorialsFragment tutorialsFragment = new TutorialsFragment();
        switchFragment(tutorialsFragment);
        setTitle("Tutorial");
    }

    /**
     * sets the icons of the app bar
     *
     */

    private void setAppbar(){
        DrawableCompat.setTint(einstellungenImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        DrawableCompat.setTint(kalenderImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        DrawableCompat.setTint(homeImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        DrawableCompat.setTint(nachrichtenImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        DrawableCompat.setTint(iliasImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
    }

    //Forum activities will be written here, because we were not able to inheirite a base Toolbar
    //in the actual version of our project. The Project is nearly finished and for such a big change
    //was no time

    /**
     * opens the Forum activity
     *
     */

    private void openForum(){
        //vorher --> startActivity(new Intent(MainActivity.this, FoActivity.class));
        initDatabase();

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.content_frame, new FoThemeFragment());
        ft.addToBackStack("tag");
        ft.commit();
    }

    /**
     * a helper method to replace the current Frgament with a new Fragment.
     *
     * @param newFragment the next fragment
     */

    private void switchForumFragment(Fragment newFragment){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.content_frame, newFragment);
        ft.addToBackStack("tag");
        ft.commit();
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
     *
     * @param navigationKey
     */

    @Override
    public void onThemeClicked(String navigationKey) {
        FoThreadFragment threadFragment = new FoThreadFragment();
        threadFragment.setNavigationKey(navigationKey);
        switchForumFragment(threadFragment);
        //addToTitleStack(firstToUpperCase(navigationKey));
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
        switchForumFragment(commentFragment);
        //addToTitleStack("Kommentare");
    }


    /**
     *
     */

    @Override
    public void onNewQuestionClick() {
        FoQuestionAdder questionFragment = new FoQuestionAdder();
        switchForumFragment(questionFragment);
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


}
