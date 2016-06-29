package de.moelschl.hhnhochschulapp.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

import de.moelschl.hhnhochschulapp.R;
import de.moelschl.hhnhochschulapp.io.DatabaseHelper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button loginButton;
    private EditText username;
    private EditText password;
    private DatabaseHelper dbHelper;

    private int passwordTries = 5;


    /**
     * does what a constructor does. This method called by a new LoginActivity() statement
     *
     * @param savedInstanceState {@link android.os.Bundle}
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initDatabase();
        init();
    }


    /**
     * initializes the activity with the given layout of the declared XML
     *
     */


    public void init() {
        loginButton = (Button) findViewById(R.id.loginButton);
        username = (EditText) findViewById(R.id.editUsername);
        password = (EditText) findViewById(R.id.editPassword);

        loginButton.setOnClickListener(this);
    }

    /**
     * initialize the Database and prints out a text
     *
     */

    private void initDatabase(){
        this.dbHelper = new DatabaseHelper(this);

        //Check exists database
        File database = getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if(false == database.exists()){
            dbHelper.getReadableDatabase();
            //copy Db
            if (dbHelper.copyDatabase(this)){
                Toast.makeText(this, "Copy database successful", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Copy error", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

    /**
     * gets uesers the password from the Databse and checks if the users password is correct.
     *
     * @return
     */

    private boolean authenticate() {
        boolean authentification = false;
        String filledInUserName = username.getText().toString();
        String filledInPassword = password.getText().toString();
        final String USER_PASSWORD = dbHelper.getPasswordByUsername(filledInUserName);
        if (filledInPassword.equals(USER_PASSWORD)){
            authentification = true;
        }
        return authentification;
    }

    /**
     * performs the click of the login button. if password is correct the user will be logged in.s
     *
     * @param v
     */

    @Override
    public void onClick(View v) {
        if(passwordTries != 1) {
            if (authenticate()) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            else {
                passwordTries--;
                Toast.makeText(this, "flasches Password! Noch " + passwordTries + " Versuche!!",
                        Toast.LENGTH_SHORT).show();
            }
        }
        else Toast.makeText(this, "VERKACKT, ALTER!", Toast.LENGTH_LONG).show();
    }
}
