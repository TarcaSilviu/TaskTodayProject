package com.example.tasktodayappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import Classes.CurentUser;

public class MenuActivity extends AppCompatActivity {
    ImageButton backImageButton;
    ImageButton menuImageIcon;
    ImageButton deleteImageIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        configureTasksButton();

        backImageButton=(ImageButton) findViewById(R.id.BackIcon);
        backImageButton.setVisibility(View.GONE);
        menuImageIcon=(ImageButton) findViewById(R.id.MenuIcon);
        menuImageIcon.setVisibility(View.GONE);
        configureTodayTasksButton();
        configureProfileButton();

    }
    private void configureTasksButton(){
        Button tasksBtn=(Button) findViewById(R.id.personalTasksBtn);

        tasksBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurentUser.currentActivity=2;
                startActivity(new Intent(MenuActivity.this, PersonalTasksActivity.class));
            }
        });
    }

    private void configureTodayTasksButton(){
        Button today=(Button) findViewById(R.id.todayTasksBtn);

        today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurentUser.currentActivity=3;
                startActivity(new Intent(MenuActivity.this, PersonalTasksActivity.class));
            }
        });
    }
    private void configureProfileButton(){
        Button profileBtn=(Button) findViewById(R.id.profileOptionBtn);

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurentUser.currentActivity=4;
                startActivity(new Intent(MenuActivity.this, UserProfile.class));
            }
        });
    }


}