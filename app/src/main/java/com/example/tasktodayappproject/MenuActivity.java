package com.example.tasktodayappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

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


    }
    private void configureTasksButton(){
        Button tasksBtn=(Button) findViewById(R.id.personalTasksBtn);

        tasksBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, PersonalTasksActivity.class));
            }
        });
    }



}