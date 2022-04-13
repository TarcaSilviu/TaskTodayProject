package com.example.tasktodayappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.AsyncQueryHandler;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Classes.CurentUser;
import Classes.ServerHelperAsyncTask;

public class MainActivity extends AppCompatActivity {
    ServerHelperAsyncTask server;
    EditText username, password, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

                CurentUser.offlineMode=true;
                CurentUser.user_email="";
                CurentUser.user_email="";
                CurentUser.currentActivity=0;

            username = findViewById(R.id.usernameTextBox);
            password = findViewById(R.id.passwordTextBox);
            email = findViewById(R.id.editTextEmail);

            server = new ServerHelperAsyncTask(this,username.getText().toString(),email.getText().toString());

            configureOfflineModeButton();
            configureLoginButton();
            configureRegisterButton();
    }

    private void configureLoginButton() {
        Button loginButton = (Button) findViewById(R.id.loginBtn);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = "Register-" + username.getText() + "-" + password.getText() + "-" + email.getText();
                server.execute(login, "192.168.43.201", "8000");

                startActivity(new Intent(MainActivity.this, MenuActivity.class));
            }
        });
    }

    private void configureRegisterButton() {
        Button nextButton = (Button) findViewById(R.id.RegisterBtn);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });
    }
    private void configureOfflineModeButton() {
        Button nextButton = (Button) findViewById(R.id.offlineModeBtn);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurentUser.offlineMode = true;
                startActivity(new Intent(MainActivity.this, MenuActivity.class));
            }
        });
    }

}