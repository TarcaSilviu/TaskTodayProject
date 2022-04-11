package com.example.tasktodayappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText title_input,description_input,dateTime_input;
    Button updateBtn;
    String id,title,description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        title_input=findViewById(R.id.editTextTitle2);
        description_input=findViewById(R.id.editTextDescription2);
        dateTime_input=findViewById(R.id.editTextDate2);
        updateBtn=findViewById(R.id.updateBtn);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        getAndSetTheData();
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

}