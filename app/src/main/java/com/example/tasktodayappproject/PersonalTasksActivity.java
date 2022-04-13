package com.example.tasktodayappproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import Classes.CurentUser;
import Classes.LocalDatabase;
import Classes.Task;

public class PersonalTasksActivity extends AppCompatActivity {
    private ArrayList<Task> tasksList;
    private ArrayList<String> task_id,task_title,task_description,task_date;
    private RecyclerView recyclerViewTasks;
    private LocalDatabase myDb;
    private ImageView emptyImageView;
    private TextView emptyTextView;
    int year,month,day;
    private Date date;
    FloatingActionButton searchButton;
    FloatingActionButton addButton;
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

*/      date=new Date();
        myDb=new LocalDatabase(PersonalTasksActivity.this);
        task_id=new ArrayList<>();
        task_title=new ArrayList<>();
        task_description=new ArrayList<>();
        task_date=new ArrayList<>();

        final Calendar c=Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        emptyImageView=findViewById(R.id.emptyImageView);
        emptyTextView=findViewById(R.id.emptyTextView);


        searchButton=(FloatingActionButton) findViewById(R.id.floatingSearchButton);
        addButton=(FloatingActionButton) findViewById(R.id.addPersonalTask);

        if(CurentUser.currentActivity==2) {
            storeData();
            searchButton.setVisibility(View.VISIBLE);
            addButton.setVisibility(View.VISIBLE);
        }
        else{
            searchData();
            searchButton.setVisibility(View.GONE);
            addButton.setVisibility(View.GONE);
        }



        setAdapter();



        ImageButton backButton=(ImageButton) findViewById(R.id.BackIcon);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PersonalTasksActivity.this,MenuActivity.class));
                PersonalTasksActivity.this.finish();
            }
        });

        configureDeleteButton();
        configureFloatAddButton();
        configureSearchFloatButton();

        ImageButton menuButton=(ImageButton) findViewById(R.id.MenuIcon);
        menuButton.setVisibility(View.GONE);

    }

    void storeData(){
        Cursor cursor=myDb.readAllData();
        SimpleDateFormat simpleDate=new SimpleDateFormat("yyyy-MM-dd");
        if(cursor.getCount()==0){

            emptyImageView.setVisibility(View.VISIBLE);
            emptyTextView.setVisibility(View.VISIBLE);

        } else{
            emptyImageView.setVisibility(View.GONE);
            emptyTextView.setVisibility(View.GONE);
            while (cursor.moveToNext()){
                task_id.add(cursor.getString(0));
                task_title.add(cursor.getString(1));
                task_description.add(cursor.getString(2));
                task_date.add(cursor.getString(3));
            }

        }
    }
    void clearData(){
        task_id.clear();
        task_title.clear();
        task_description.clear();
        task_date.clear();
    }

    void searchData(){
        Cursor cursor=myDb.searchTroughData(date);
        SimpleDateFormat simpleDate=new SimpleDateFormat("yyyy-MM-dd");

        clearData();

        if(cursor.getCount()==0){

        } else{
            while (cursor.moveToNext()){
                task_id.add(cursor.getString(0));
                task_title.add(cursor.getString(1));
                task_description.add(cursor.getString(2));
                task_date.add(cursor.getString(3));
            }

        }
        recyclerViewTasks.setAdapter(new PersonalRecyclerAdapter(PersonalTasksActivity.this,this,task_id,task_title,task_description,task_date));
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
        PersonalRecyclerAdapter adapter=new PersonalRecyclerAdapter(PersonalTasksActivity.this,this,task_id,task_title,task_description,task_date);
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


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(PersonalTasksActivity.this,AddPersonalTaskActivity.class);
                startActivity(intent);
            }
        });
    }
    private void configureSearchFloatButton(){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dp=new DatePickerDialog(PersonalTasksActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfmonth) {
                        try {

                            date = simpleDateFormat.parse(year + "-" + (month+1) + "-" + dayOfmonth);
                        } catch (Exception e) {
                            date = null;
                        }
                    }
                },year,month,day);
                dp.setCanceledOnTouchOutside(false);
                dp.setButton(DialogInterface.BUTTON_POSITIVE, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    date = simpleDateFormat.parse(dp.getDatePicker().getYear() + "-" + ((dp.getDatePicker().getMonth())+1) + "-" + dp.getDatePicker().getDayOfMonth());
                                }catch(Exception e){
                                    date=null;
                                }
                                searchData();
                                dp.dismiss();
                            }
                        });
                dp.show();


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