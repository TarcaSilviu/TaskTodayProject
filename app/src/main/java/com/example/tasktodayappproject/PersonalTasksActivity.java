package com.example.tasktodayappproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.app.Activity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Classes.LocalDatabase;
import Classes.Task;

public class PersonalTasksActivity extends AppCompatActivity {
    private ArrayList<Task> tasksList;
    private ArrayList<String> task_id,task_title,task_description;
    private RecyclerView recyclerViewTasks;
    private LocalDatabase myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        recyclerViewTasks=findViewById(R.id.recyclerViewTasks);
        /*tasksList=new ArrayList<>();
        tasksList.add((new Task("man")));
        tasksList.add((new Task("man")));
        tasksList.add((new Task("man")));
        tasksList.add((new Task("man")));
        setAdapter();
*/
        myDb=new LocalDatabase(PersonalTasksActivity.this);
        task_id=new ArrayList<>();
        task_title=new ArrayList<>();
        task_description=new ArrayList<>();

        storeData();
        setAdapter();

        configureFloatAddButton();

        ImageButton backButton=(ImageButton) findViewById(R.id.BackIcon);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PersonalTasksActivity.this,MenuActivity.class));
                PersonalTasksActivity.this.finish();
            }
        });

        configureDeleteButton();

        ImageButton menuButton=(ImageButton) findViewById(R.id.MenuIcon);
        menuButton.setVisibility(View.GONE);

    }

    void storeData(){
        Cursor cursor=myDb.readAllData();
        if(cursor.getColumnCount()==0){
            Toast.makeText(this,"No data.",Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor.moveToNext()){
                task_id.add(cursor.getString(0));
                task_title.add(cursor.getString(1));
                task_description.add(cursor.getString(2));
            }
        }
    }



    //buttons configurations
    private void configureMenuButton(){
        ImageButton menuButton=(ImageButton) findViewById(R.id.MenuIcon);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PersonalTasksActivity.this,MenuActivity.class));

            }
        });
    }


    private void setAdapter(){
        PersonalRecyclerAdapter adapter=new PersonalRecyclerAdapter(PersonalTasksActivity.this,this,task_id,task_title,task_description);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager((getApplicationContext()));
        recyclerViewTasks.setLayoutManager((layoutManager));
        recyclerViewTasks.setItemAnimator((new DefaultItemAnimator()));
        recyclerViewTasks.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    private void configureFloatAddButton(){
        FloatingActionButton addButton=(FloatingActionButton) findViewById(R.id.addPersonalTask);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(PersonalTasksActivity.this,AddPersonalTaskActivity.class);
                startActivity(intent);
            }
        });
    }

    private void configureDeleteButton(){

        ImageButton deleteImageIcon=(ImageButton) findViewById(R.id.deleteIcon);
        deleteImageIcon.setVisibility(View.VISIBLE);
        deleteImageIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(this,"Delete",Toast.LENGTH_SHORT).show();
                confirmDialog();
            }
        });
    }
    private void confirmDialog(){

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Delete all?");
        builder.setMessage("Are you sure you want to delete all data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                LocalDatabase myDB=new LocalDatabase(PersonalTasksActivity.this);
                myDB.deleteAllData();
                PersonalTasksActivity.this.recreate();
                Intent intent=new Intent(PersonalTasksActivity.this,PersonalTasksActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }
}