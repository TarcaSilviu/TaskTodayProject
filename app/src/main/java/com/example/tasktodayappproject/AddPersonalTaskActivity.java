package com.example.tasktodayappproject;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;

import Classes.LocalDatabase;

public class AddPersonalTaskActivity extends AppCompatActivity {

    EditText title;
    EditText description;

    Button date_button;
    Button add_button;

    int year,month,day;
    private Date date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_personal_task);
        date=new Date();
        configureBackButton();
        configureMenuButton();
        configureDateButton();
        configureAddButton();
        final Calendar c=Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        title=findViewById(R.id.editTextTitle);
        description=findViewById(R.id.editTextDescription);

    }

    private void configureDateButton(){
        date_button=findViewById(R.id.dateBtn);
        date_button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                DatePickerDialog dp=new DatePickerDialog(AddPersonalTaskActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfmonth) {
                        try {
                            SimpleDateFormat simpleDateFomrat = new SimpleDateFormat("yyyy-MM-dd");
                            date = simpleDateFomrat.parse(year + "-" + (month+1) + "-" + dayOfmonth);
                        } catch (Exception e) {
                            date = null;
                        }
                    }
                },year,month,day);
                dp.show();
            }
        });
    }


    private void configureAddButton(){
        add_button=findViewById(R.id.addBtn);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalDatabase myDb=new LocalDatabase(AddPersonalTaskActivity.this);
                myDb.addPersonalTask(title.getText().toString().trim(),
                        description.getText().toString().trim(),date);
                AddPersonalTaskActivity.this.finish();
                startActivity(new Intent(AddPersonalTaskActivity.this,PersonalTasksActivity.class));
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
    /*
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
    }*/
}