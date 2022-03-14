package com.example.tasktodayappproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Classes.Task;

public class PersonalRecyclerAdapter extends RecyclerView.Adapter<PersonalRecyclerAdapter.MyViewHolder> {
    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView title;

        public MyViewHolder (final View view){
            super(view);
            title=view.findViewById(R.id.titleTextView);

        }
    }
    private ArrayList<Task> taskList;

    public PersonalRecyclerAdapter(ArrayList<Task> taskList){
        this.taskList=taskList;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.recylcer_personal_tasks_view,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    String title=taskList.get(position).getTitle();
    holder.title.setText(title);
    }


    @Override
    public int getItemCount() {
        return taskList.size();
    }
}
