package com.shubhamdeshmukh.attendencemanagementsystem.frontend.teacher;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shubhamdeshmukh.attendencemanagementsystem.R;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.Subject;

import java.util.ArrayList;


public class SubjectRecyclerAdapter extends RecyclerView.Adapter<SubjectRecyclerAdapter.ViewHolder> {

    Context context;
    ArrayList<Subject> subjects_array_list;

    public SubjectRecyclerAdapter(Context context,ArrayList<Subject> subjects_array_list){
        this.subjects_array_list = subjects_array_list;
        this.context = context;
    }
    @NonNull
    @Override
    public SubjectRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v =  LayoutInflater.from(context).inflate(R.layout.layout_subject_portal_recycler,parent,false);
       ViewHolder viewHolder  = new ViewHolder(v);
       return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull SubjectRecyclerAdapter.ViewHolder holder, int position) {

        holder.subname.setText(subjects_array_list.get(position).getName());
        holder.coursecode.setText(subjects_array_list.get(position).getCode());

    }

    @Override
    public int getItemCount() {
        return subjects_array_list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView subname,coursecode;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            subname = itemView.findViewById(R.id.sub_name);
            coursecode = itemView.findViewById(R.id.course_code);

        }

    }
}
