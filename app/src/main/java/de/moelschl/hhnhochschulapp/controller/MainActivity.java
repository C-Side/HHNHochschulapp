package de.moelschl.hhnhochschulapp.controller;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
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
import android.widget.ImageView;

import de.moelschl.hhnhochschulapp.controller.Forum.FoActivity;
import de.moelschl.hhnhochschulapp.R;

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

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.content_frame, new HomeFragment());
        ft.addToBackStack("tag");
        ft.commit();

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
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.content_frame, new KalenderFragment());
        ft.addToBackStack("tag");
        ft.commit();
        setAppbar();
        setTitle("Kalender");
    }

    private void openHome(){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.content_frame, new HomeFragment());
        ft.addToBackStack("tag");
        ft.commit();
        setAppbar();
        setTitle("HHN Hochschulapp");
    }

    private void openBenachrichtigungen(){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.content_frame, new BenachrichtigungenFragment());
        ft.addToBackStack("tag");
        ft.commit();
        setAppbar();
        setTitle("Benachrichtigungen");
    }

    private void openIlias(){
    //    android.app.FragmentManager fm = getFragmentManager();
    //    fm.beginTransaction().replace(R.id.content_frame, new IliasFragment()).commit();
    //    setAppbar()
    //    setTitle("Ilias");
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ilias.hs-heilbronn.de/login.php?target=&soap_pw=&ext_uid=&cookies=nocookies&client_id=hshn&lang=de"));
        startActivity(browserIntent);
    }

    private void openEinstellungen(){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.content_frame, new EinstellungenFragment());
        ft.addToBackStack("tag");
        ft.commit();
        setAppbar();
        setTitle("Einstellungen");
    }

    private void openLinda(){
    //    android.app.FragmentManager fm = getFragmentManager();
    //    fm.beginTransaction().replace(R.id.content_frame, new LindaFragment()).commit();
    //    setAppbar()
    //    setTitle("Linda");
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://linda.hs-heilbronn.de/"));
        startActivity(browserIntent);
    }

    private void openForum(){
        startActivity(new Intent(MainActivity.this, FoActivity.class));

        /**
        android.app.FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame, new FoThemeFragment()).commit();
         setAppbar()
         */
        //setTitle("Forum");
    }

    private void openTutorial(){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.content_frame, new TutorialsFragment());
        ft.addToBackStack("tag");
        ft.commit();
        setAppbar();
        setTitle("Tutorial");
    }

    private void setAppbar(){
        DrawableCompat.setTint(einstellungenImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        DrawableCompat.setTint(kalenderImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
        DrawableCompat.setTint(homeImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        DrawableCompat.setTint(nachrichtenImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
        DrawableCompat.setTint(iliasImage.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.black));
    }
}
