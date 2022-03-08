package com.example.tasktodayappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MenuActivity extends AppCompatActivity {
    ImageButton backImageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_hub);
        confirgureBackButton();
        backImageButton=(ImageButton) findViewById(R.id.MenuIcon);
        backImageButton.setVisibility(View.GONE);
    }
    private void confirgureBackButton(){
        ImageButton backImageButton=(ImageButton) findViewById(R.id.BackIcon);

        backImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,UserHub.class));
            }
        });
    }
}