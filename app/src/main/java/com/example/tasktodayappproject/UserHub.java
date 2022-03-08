package com.example.tasktodayappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class UserHub extends AppCompatActivity {

        ImageButton backImageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_hub);
        configureMenuButton();
        backImageButton=(ImageButton) findViewById(R.id.BackIcon);
        backImageButton.setVisibility(View.GONE);
    }
    private void configureMenuButton(){
        ImageButton menuButton=(ImageButton) findViewById(R.id.MenuIcon);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserHub.this,MenuActivity.class));
            }
        });
    }


}