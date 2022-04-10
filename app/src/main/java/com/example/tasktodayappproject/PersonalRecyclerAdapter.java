package com.example.tasktodayappproject;

import android.content.Context;
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
        private TextView task_id_txt,task_title_txt,task_description_txt;

        public MyViewHolder (final View view){
            super(view);
            task_id_txt=view.findViewById(R.id.idTextView);
            task_title_txt=view.findViewById(R.id.titleTextView);
           // task_description_txt=view.findViewById(R.id.);
        }
    }

    private Context context;
    private ArrayList task_id,task_title,task_description;

    public PersonalRecyclerAdapter(Context context,ArrayList task_id, ArrayList task_title,ArrayList task_description){
        this.context=context;
        this.task_description=task_description;
        this.task_id=task_id;
        this.task_title=task_title;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_personal_tasks_view,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    holder.task_id_txt.setText(String.valueOf(task_id.get(position)));
    holder.task_title_txt.setText(String.valueOf(task_title.get(position)));
    }


    @Override
    public int getItemCount() {
        return task_id.size();
    }
}
