package com.example.tasktodayappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class UserHub extends AppCompatActivity {

        ImageButton backImageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_hub);
        backImageButton=(ImageButton) findViewById(R.id.BackIcon);
        backImageButton.setVisibility(View.GONE);
    }


}