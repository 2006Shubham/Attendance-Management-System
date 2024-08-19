package com.shubhamdeshmukh.attendencemanagementsystem.frontend.teacherregistration;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shubhamdeshmukh.attendencemanagementsystem.R;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.Subject;

import java.util.ArrayList;

public class SubjectRegisterRecyclerAdapter extends RecyclerView.Adapter<SubjectRegisterRecyclerAdapter.ViewHolder> {

    Context context;
    ArrayList<Subject> subjectArrayList;

    SubjectRegisterRecyclerAdapter(Context context, ArrayList<Subject>subjectArrayList){

        this.context = context;
        this.subjectArrayList = subjectArrayList;

    }

    @NonNull
    @Override
    public SubjectRegisterRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_class_and_subject_category_register_recycler,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectRegisterRecyclerAdapter.ViewHolder holder, int position) {

       holder.subname.setText(subjectArrayList.get(position).getName()+"( "+ subjectArrayList.get(position).getCode()+" )");

    }

    @Override
    public int getItemCount() {
        return subjectArrayList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        TextView subname ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           subname =  itemView.findViewById(R.id.name);

        }
    }

}
