package com.example.tasktodayappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Classes.CurentUser;
import Classes.ServerHelperAsyncTask;

public class RegisterActivity extends AppCompatActivity {

    ServerHelperAsyncTask server;
    EditText username, password, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        username = findViewById(R.id.registerUsernameTxtView);
        password = findViewById(R.id.registerPasswordlTxtView);
        email = findViewById(R.id.registerEmailTxtView);

        server = new ServerHelperAsyncTask(this,username.getText().toString(),email.getText().toString());

        configureRegisterButton();
        configureCancelButton();
    }

    private void configureRegisterButton() {
        Button loginButton = (Button) findViewById(R.id.registerBtn);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String register = "Register-" + username.getText() + "-" + password.getText() + "-" + email.getText();
                server.execute(register, "192.168.43.201", "8000");
                startActivity(new Intent(RegisterActivity.this, MenuActivity.class));
            }
        });
    }
    private void configureCancelButton() {
        Button nextButton = (Button) findViewById(R.id.cancelBtn);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        });
    }
}