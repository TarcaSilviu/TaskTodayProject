package com.example.tasktodayappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class AddPersonalTaskActivity extends AppCompatActivity {

    EditText title;
    EditText description;
    EditText date;
    EditText time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_personal_task);
        configureBackButton();
        configureMenuButton();
    }
    private void configureMenuButton(){
        ImageButton menuButton=(ImageButton) findViewById(R.id.MenuIcon);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddPersonalTaskActivity.this,PersonlTasksActivity.class));
                AddPersonalTaskActivity.this.finish();
            }
        });
    }
    private void configureBackButton(){
        ImageButton backButton=(ImageButton) findViewById(R.id.BackIcon);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddPersonalTaskActivity.this,MenuActivity.class));
                AddPersonalTaskActivity.this.finish();
            }
        });
    }
}