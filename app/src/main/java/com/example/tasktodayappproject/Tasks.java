package com.example.tasktodayappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Tasks extends AppCompatActivity {

        ImageButton backImageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        configureMenuButton();
        ImageButton backButton=(ImageButton) findViewById(R.id.BackIcon);
        backButton.setVisibility(View.GONE);
    }

    private void configureMenuButton(){
        ImageButton menuButton=(ImageButton) findViewById(R.id.MenuIcon);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Tasks.this,MenuActivity.class));
            }
        });
    }


}