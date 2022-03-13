package com.example.tasktodayappproject;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Classes.Task;

public class PersonalRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder.MyViewHolder> {
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
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
