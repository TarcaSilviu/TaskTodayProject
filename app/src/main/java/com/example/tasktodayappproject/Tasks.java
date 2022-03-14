package com.example.tasktodayappproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

import Classes.Task;

public class Tasks extends AppCompatActivity {
    private ArrayList<Task> tasksList;
    private RecyclerView recyclerViewTasks;
    ImageButton backImageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        recyclerViewTasks=findViewById(R.id.recyclerViewTasks);
        tasksList=new ArrayList<>();
        tasksList.add((new Task("man")));
        setAdapter();

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

    private void setAdapter(){
        PersonalRecyclerAdapter adapter=new PersonalRecyclerAdapter(tasksList);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager((getApplicationContext()));
        recyclerViewTasks.setLayoutManager((layoutManager));
        recyclerViewTasks.setItemAnimator((new DefaultItemAnimator()));
        recyclerViewTasks.setAdapter(adapter);
    }

}