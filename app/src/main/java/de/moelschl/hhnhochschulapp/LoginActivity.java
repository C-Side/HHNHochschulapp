package de.moelschl.hhnhochschulapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private EditText username;
    private EditText password;
    private boolean authentication;


    public void init(){
        loginButton = (Button) findViewById(R.id.loginButton);
        username = (EditText) findViewById(R.id.editUsername);
        password = (EditText) findViewById(R.id.editPassword);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticate();
                if (authentication){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }

            }
        });

    }

    private void authenticate(){
        authentication = username.getText().toString().equals("username") && password.getText().toString().equals("password");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();

    }



}
