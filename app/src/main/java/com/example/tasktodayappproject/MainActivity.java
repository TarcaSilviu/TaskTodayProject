package com.example.tasktodayappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import Classes.CurentUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureOfflineModeButton();
    }

    private void configureOfflineModeButton(){
        Button nextButton=(Button) findViewById(R.id.offlineModeBtn);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurentUser.offlineMode=true;
                startActivity(new Intent(MainActivity.this, MenuActivity.class));
            }
        });
    }
}