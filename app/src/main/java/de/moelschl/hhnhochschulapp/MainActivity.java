package de.moelschl.hhnhochschulapp;


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
import android.widget.ImageView;

import de.moelschl.hhnhochschulapp.forum.ForumFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ImageView kalenderImage;
    ImageView einstellungenImage;
    ImageView homeImage;
    ImageView nachrichtenImage;
    ImageView iliasImage;

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

        android.app.FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame, new HomeFragment()).commit();

        setAllImageListeners();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
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
        android.app.FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame, new KalenderFragment()).commit();
        DrawableCompat.setTint(einstellungenImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        DrawableCompat.setTint(kalenderImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
        DrawableCompat.setTint(homeImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        DrawableCompat.setTint(nachrichtenImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        DrawableCompat.setTint(iliasImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        setTitle("Kalender");
    }

    private void openHome(){
        android.app.FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame, new HomeFragment()).commit();
        DrawableCompat.setTint(einstellungenImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        DrawableCompat.setTint(kalenderImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        DrawableCompat.setTint(homeImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
        DrawableCompat.setTint(nachrichtenImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        DrawableCompat.setTint(iliasImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        setTitle("HHN Hochschulapp");
    }

    private void openBenachrichtigungen(){
        android.app.FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame, new BenachrichtigungenFragment()).commit();
        DrawableCompat.setTint(einstellungenImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        DrawableCompat.setTint(kalenderImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        DrawableCompat.setTint(homeImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        DrawableCompat.setTint(nachrichtenImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
        DrawableCompat.setTint(iliasImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        setTitle("Benachrichtigungen");
    }

    private void openIlias(){
        android.app.FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame, new IliasFragment()).commit();
        DrawableCompat.setTint(einstellungenImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        DrawableCompat.setTint(kalenderImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        DrawableCompat.setTint(homeImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        DrawableCompat.setTint(nachrichtenImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        DrawableCompat.setTint(iliasImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
        setTitle("Ilias");
    }

    private void openEinstellungen(){
        android.app.FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame, new EinstellungenFragment()).commit();
        DrawableCompat.setTint(einstellungenImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
        DrawableCompat.setTint(kalenderImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        DrawableCompat.setTint(homeImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        DrawableCompat.setTint(nachrichtenImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        DrawableCompat.setTint(iliasImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        setTitle("Einstellungen");
    }

    private void openLinda(){
        android.app.FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame, new LindaFragment()).commit();
        DrawableCompat.setTint(einstellungenImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        DrawableCompat.setTint(kalenderImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        DrawableCompat.setTint(homeImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        DrawableCompat.setTint(nachrichtenImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        DrawableCompat.setTint(iliasImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        setTitle("Linda");
    }

    private void openForum(){
        android.app.FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame, new ForumFragment()).commit();
        DrawableCompat.setTint(einstellungenImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        DrawableCompat.setTint(kalenderImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        DrawableCompat.setTint(homeImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        DrawableCompat.setTint(nachrichtenImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        DrawableCompat.setTint(iliasImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        setTitle("Forum");
    }

    private void openTutorial(){
        android.app.FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame, new TutorialsFragment()).commit();
        DrawableCompat.setTint(einstellungenImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        DrawableCompat.setTint(kalenderImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        DrawableCompat.setTint(homeImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        DrawableCompat.setTint(nachrichtenImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        DrawableCompat.setTint(iliasImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        setTitle("Tutorial");
    }


}
