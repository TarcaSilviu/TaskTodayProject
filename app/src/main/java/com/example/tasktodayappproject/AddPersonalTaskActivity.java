package com.example.tasktodayappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import Classes.LocalDatabase;

public class AddPersonalTaskActivity extends AppCompatActivity {

    EditText title;
    EditText description;
    EditText date;

    Button add_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_personal_task);
        configureBackButton();
        configureMenuButton();
        //configureAddButton();

        title=findViewById(R.id.editTextTitle);
        description=findViewById(R.id.editTextDescription);
        date=findViewById(R.id.editTextDate);

        add_button=findViewById(R.id.addBtn);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalDatabase myDb=new LocalDatabase(AddPersonalTaskActivity.this);
                myDb.addPersonalTask(title.getText().toString().trim(),
                        description.getText().toString().trim());
            }
        });
    }
    private void configureMenuButton(){
        ImageButton menuButton=(ImageButton) findViewById(R.id.MenuIcon);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddPersonalTaskActivity.this, PersonalTasksActivity.class));
                AddPersonalTaskActivity.this.finish();
            }
        });
    }
    private void configureBackButton(){
        ImageButton backButton=(ImageButton) findViewById(R.id.BackIcon);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddPersonalTaskActivity.this,PersonalTasksActivity.class));
                AddPersonalTaskActivity.this.finish();
            }
        });
    }
    private void configureAddButton(){
        title=findViewById(R.id.editTextTitle);
        description=findViewById(R.id.editTextDescription);
        //date=findViewById(R.id.editTextDate);
       // time=findViewById(R.id.editTextTime);
        add_button=findViewById(R.id.addBtn);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalDatabase myDb=new LocalDatabase(AddPersonalTaskActivity.this);
                myDb.addPersonalTask(title.getText().toString().trim(),
                        description.getText().toString().trim());
            }
        });
    }
}