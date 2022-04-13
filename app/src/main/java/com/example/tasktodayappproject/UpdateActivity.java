package com.example.tasktodayappproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import Classes.LocalDatabase;

public class UpdateActivity extends AppCompatActivity {
    EditText title_input,description_input,dateTime_input;
    Button updateBtn,deleteBtn;
    String id,title,description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title_input=findViewById(R.id.editTextTitle2);
        description_input=findViewById(R.id.editTextDescription2);
        dateTime_input=findViewById(R.id.editTextDate2);
        updateBtn=findViewById(R.id.updateBtn);
        deleteBtn=findViewById(R.id.deleteBtn);
        getAndSetTheData();
        configureBackButton();
        configureMenuButton();


        LocalDatabase myDb=new LocalDatabase((UpdateActivity.this));
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title=title_input.getText().toString().trim();
                description=description_input.getText().toString().trim();

                myDb.updateData(id,title,description);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }




   private void getAndSetTheData() {
        if(getIntent().hasExtra("id")&& getIntent().hasExtra("title")&&getIntent().hasExtra("description")){
           //luam datele din intentie
            id=getIntent().getStringExtra("id");
            title=getIntent().getStringExtra("title");
            description=getIntent().getStringExtra("description");
        //setam datele din intentie
            title_input.setText(title);
            description_input.setText(description);
           // dateTime_input.setText(title);
        }
        else{
            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
        }
    }

    private void configureMenuButton(){
        ImageButton menuButton=(ImageButton) findViewById(R.id.MenuIcon);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UpdateActivity.this, MenuActivity.class));
                UpdateActivity.this.finish();
            }
        });
    }
    private void configureBackButton(){
        ImageButton backButton=(ImageButton) findViewById(R.id.BackIcon);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UpdateActivity.this,PersonalTasksActivity.class));
                UpdateActivity.this.finish();
            }
        });
    }

    private void confirmDialog(){

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Delete "+title+"?");
        builder.setMessage("Are you sure you want to delete" +title+"?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                LocalDatabase myDb=new LocalDatabase(UpdateActivity.this);
                myDb.deleteOneRow(id);
                UpdateActivity.this.finish();
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