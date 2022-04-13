package com.example.tasktodayappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import Classes.CurentUser;

public class UserProfile extends AppCompatActivity {
    TextView userNameTextView, emailTextView;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        userNameTextView = findViewById(R.id.usernameTextView);
        emailTextView = findViewById(R.id.emailTextView);
        imageView=findViewById(R.id.imageView);
        imageView.setVisibility(View.VISIBLE);

        if (CurentUser.offlineMode == true) {
            userNameTextView.setText("Offline mode!");
            emailTextView.setText("Offline mode!");
        } else {
            userNameTextView.setText(CurentUser.username);
            emailTextView.setText(CurentUser.user_email);
        }

        configureTodayTasksButton();
    }

    private void configureTodayTasksButton() {
        Button today = (Button) findViewById(R.id.todayTasksBtn);

        today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurentUser.currentActivity = 3;
                startActivity(new Intent(UserProfile.this, PersonalTasksActivity.class));
            }
        });
    }
}