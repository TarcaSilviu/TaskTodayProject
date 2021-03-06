package com.example.tasktodayappproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Classes.Task;

public class PersonalRecyclerAdapter extends RecyclerView.Adapter<PersonalRecyclerAdapter.MyViewHolder> {

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView task_id_txt,task_title_txt,task_description_txt,task_date_txt;
        protected ConstraintLayout personalTaskLayout;


        public MyViewHolder (final View view){
            super(view);
            task_id_txt=view.findViewById(R.id.idTextView);
            task_title_txt=view.findViewById(R.id.titleTextView);
            task_description_txt=view.findViewById(R.id.descriptionSecretView);
            task_date_txt=view.findViewById(R.id.dateTextVIew);
            personalTaskLayout=itemView.findViewById(R.id.personalTaskLayout);

        }
    }

    private Context context;
    private ArrayList task_id,task_title,task_description,task_date;
    Activity activity;
    protected int position;
    public PersonalRecyclerAdapter(Activity activity,Context context,ArrayList task_id, ArrayList task_title,ArrayList task_description,ArrayList task_date){
        this.activity=activity;
        this.context=context;
        this.task_description=task_description;
        this.task_id=task_id;
        this.task_title=task_title;
        this.task_date=task_date;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_personal_tasks_view,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        this.position=position;
        holder.task_id_txt.setText(String.valueOf(task_id.get(position)));
        holder.task_title_txt.setText(String.valueOf(task_title.get(position)));
        holder.task_description_txt.setText(String.valueOf(task_description.get(position)));
        holder.task_date_txt.setText(String.valueOf(task_date.get(position)));

        holder.personalTaskLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, UpdateActivity.class);
                intent.putExtra("id",String.valueOf(task_id.get(position)));
                intent.putExtra("title",String.valueOf(task_title.get(position)));
                intent.putExtra("description",String.valueOf(task_description.get(position)));
                intent.putExtra("description",String.valueOf(task_date.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });
    }


    @Override
    public int getItemCount() {
        return task_id.size();
    }
}
