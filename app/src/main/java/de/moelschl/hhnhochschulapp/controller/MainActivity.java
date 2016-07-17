package de.moelschl.hhnhochschulapp.controller;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;

import de.moelschl.hhnhochschulapp.R;
import de.moelschl.hhnhochschulapp.controller.Forum.FoCommentFragment;
import de.moelschl.hhnhochschulapp.controller.Forum.FoQuestionAdder;
import de.moelschl.hhnhochschulapp.controller.Forum.FoThemeFragment;
import de.moelschl.hhnhochschulapp.controller.Forum.FoThreadFragment;
import de.moelschl.hhnhochschulapp.controller.User.BenutzerFragment;
import de.moelschl.hhnhochschulapp.io.DatabaseHelper;
import de.moelschl.hhnhochschulapp.model.User;
import de.moelschl.hhnhochschulapp.tools.CustomAutoCompleteView;
import de.moelschl.hhnhochschulapp.tools.OnWindowTitleSet;

/**
 * the activity which holds the navigation-sets like appbar and toolbar and controls the action
 * of the applied fragments. Is a controller for Forum Fragments.
 *
 */


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FoThemeFragment.OnThemeManage,
        FoThreadFragment.OnThreadManage, CustomAutoCompleteView.OnThemeChooseListener,
        FoQuestionAdder.OnStoreData, OnWindowTitleSet, BenutzerFragment.OnUserManage {

    private LinearLayout appbar;
    private FloatingActionButton fab;
    private DatabaseHelper mDBHelper;
    private ImageView kalenderImage;
    private ImageView benutzerImage;
    private ImageView homeImage;
    private ImageView nachrichtenImage;
    private ImageView iliasImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setFAB();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        this.appbar=(LinearLayout)this.findViewById(R.id.therealy_appbar);
        appbar.setVisibility(View.VISIBLE);

        initDatabase();
        setAllImageListeners();
        openHome();
    }


    /**
     * switches the Fragment
     *
     * @param newFragment the new Fragment wich should be shown
     */

    private void switchFragment(Fragment newFragment){
        fab.setVisibility(View.GONE);
        appbar.setVisibility(View.VISIBLE);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.content_frame, newFragment);
        ft.addToBackStack("tag");
        ft.commit();
    }

    /**
     * implements the on back pressed event of the device
     *
     */

    @Override
    public void onBackPressed() {
        fab.setVisibility(View.GONE);
        appbar.setVisibility(View.VISIBLE);
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

    /**
     * sets the showable menu on the left side
     *
     * @param menu
     * @return
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * reacts on the item click event of the menu
     *
     * @param item
     * @return
     */

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


    /**
     * reacts on the click of the toolbar at the bottom of the device screen
     *
     * @param item
     * @return
     */

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

    /**
     * sets the click event
     */

    private void setAllImageListeners() {
        kalenderImage = (ImageView) findViewById(R.id.Kalender);
        benutzerImage = (ImageView) findViewById(R.id.einstellungen);
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

        benutzerImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openEinstellungen();
            }
        });
    }

    /**
     * initializes the Kalender Fragment
     */

    private void openKalender(){
        KalenderFragment kalenderFragment = new KalenderFragment();
        switchFragment(kalenderFragment);
        setHintColor(kalenderImage);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_kalender);
    }

    /**
     * initializes the Home Fragment
     */

    private void openHome(){
        HomeFragment homeFragment = new HomeFragment();
        switchFragment(homeFragment);
        setHintColor(homeImage);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_home);
    }

    /**
     * initializes the Benachrichtgungs Fragment
     */

    private void openBenachrichtigungen(){
        BenachrichtigungenFragment benachrichtigungenFragment = new BenachrichtigungenFragment();
        switchFragment(benachrichtigungenFragment);
        setHintColor(nachrichtenImage);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_benachrichtigungen);
    }

    /**
     * initializes the User Fragment
     */

    private void openTutorial(){
        TutorialsFragment tutorialsFragment = new TutorialsFragment();
        switchFragment(tutorialsFragment);
    }

    /**
     * initializes the User Fragment
     */

    private void openEinstellungen(){
        BenutzerFragment benutzerFragment = new BenutzerFragment();
        switchFragment(benutzerFragment);
        setHintColor(benutzerImage);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_einstellungen);
    }

    /**
     * call the creation methode for the Ilias activity
     */

    private void openIlias(){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://ilias.hs-heilbronn.de/login.php?target=&soap_pw=&ext_uid=&cookies=nocookies&client_id=hshn&lang=de"));
        startActivity(browserIntent);

    }


    /**
     * call the creation methode for the Linda activity
     *
     */

    private void openLinda(){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://linda.hs-heilbronn.de/"));
        startActivity(browserIntent);

    }



    /**
     * sets the color of the hints
     */

    private void setHintColor(ImageView hint){
        DrawableCompat.setTint(benutzerImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        DrawableCompat.setTint(kalenderImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        DrawableCompat.setTint(homeImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        DrawableCompat.setTint(nachrichtenImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        DrawableCompat.setTint(iliasImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));

        DrawableCompat.setTint(hint.getDrawable(),
                ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
    }

    //Forum activities will be written here, because we were not able to inheirite a base Toolbar
    //in the actual version of our project. The Project is nearly finished and for such a big change
    //was no time

    /**
     * opens the Forum activity
     *
     */

    private void openForum(){
       // changeLayou();
        FoThemeFragment themeFragment =  new FoThemeFragment();
        switchFragment(themeFragment);
    }

    /**
     * set a the floating action button
     *
     */

    private void setFAB(){
        this.fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        fab.setVisibility(View.GONE);
    }

    /**
     * changes the layout of the overlaying activity
     *
     */

    @Override
    public void changeToForumLayout(){
        fab.setVisibility(View.VISIBLE);
        appbar.setVisibility(View.GONE);
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
     * sets the window title
     * @param title of the next window
     */

    @Override
    public void setWindowTitle(String title) {
        setTitle(title);
    }

    /**
     * load the next fragment of the clicked topic
     *
     * @param navigationKey the clicked topic
     */

    @Override
    public void onThemeClicked(String navigationKey) {
        FoThreadFragment threadFragment = new FoThreadFragment();
        threadFragment.setNavigationKey(navigationKey);
        threadFragment.setList(mDBHelper.getThreadListByTheme(navigationKey));
        switchFragment(threadFragment);
    }


    /**
     * loads the comments fragment of the clicked thread
     *
     * @param navigationKey the clicked thread
     * @param question the question to show in comments
     */

    @Override
    public void onThreadClicked(int navigationKey, String question, String questionHeader) {
        FoCommentFragment commentFragment = new FoCommentFragment();
        commentFragment.setNavigationKey(navigationKey);
        commentFragment.setQuestion(question, questionHeader);
        switchFragment(commentFragment);
    }


    /**
     * calls the question adder fragment
     */

    @Override
    public void onNewQuestionClick() {
        FoQuestionAdder questionFragment = new FoQuestionAdder();
        switchFragment(questionFragment);
    }

    /**
     * defines which topics will be shown after clicking the topic textbox to insert text.
     *
     * @param text the input text
     */

    @Override
    public void onDropDownItemClick(String text) {
        EditText themeDesc = (EditText) findViewById(R.id.theme_desc_text);
        String description = mDBHelper.getDescByTopic(text);
        themeDesc.setText(description);
    }

    /**
     * stores a new question in the database
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
        openForum();
    }

    /**
     * sets the list of the list for the adapter
     *
     * @param position
     */

    @Override
    public void setUserListEvent(int position) {
        if (position == 0) {
            FoThreadFragment threadFragment = new FoThreadFragment();
            threadFragment.setNavigationKey("deine Beiträge");
            threadFragment.setList(mDBHelper.getThreadListByUsername(User.getUsername()));
            switchFragment(threadFragment);
        }
        /**
        else {
            FoCommentFragment commentFragment = new FoCommentFragment();
            commentFragment.setCommentList(mDBHelper.getCommentListByUsername(User.getUsername()));

        }
         */
    }
}
