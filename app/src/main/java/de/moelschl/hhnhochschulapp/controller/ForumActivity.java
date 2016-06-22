package de.moelschl.hhnhochschulapp.controller;

/**
 * Created by Hasbert on 21.06.2016.
 */
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;


import de.moelschl.hhnhochschulapp.R;

public class ForumActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_forum);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_forum);
        setSupportActionBar(toolbar);

        Button newThemeButton = (Button) findViewById(R.id.newItem);
        newThemeButton.setOnClickListener(this);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.forum_toolbar_fragment, new ToolbarFragment());
        ft.add(R.id.forum_list_fragment, new ForumFragment());
        ft.commit();

        setTitle("Forum");
    }

    @Override
    public void onClick(View v) {
        System.out.println("penis");
    }
}


