package com.shubhamdeshmukh.attendencemanagementsystem.frontend.adapters.selection.single;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shubhamdeshmukh.attendencemanagementsystem.R;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.database_entities.Subject;
import com.shubhamdeshmukh.attendencemanagementsystem.frontend.teacherregistration.RegisterSubjectInfoAndCategoryActivity;

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


        ViewHolder viewHolder = new ViewHolder(parent);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectRegisterRecyclerAdapter.ViewHolder holder, int position) {

       holder.subname.setText(subjectArrayList.get(holder.getAdapterPosition()).getName()+" ("+ subjectArrayList.get(holder.getAdapterPosition()).getCode()+")");
       holder.subname.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(context, RegisterSubjectInfoAndCategoryActivity.class);
               intent.putExtra("subjectFetchedDataIndex", holder.getAdapterPosition());
               context.startActivity(intent);
           }
       });

    }

    @Override
    public int getItemCount() {
        return subjectArrayList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        TextView subname;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           subname =  itemView.findViewById(R.id.name);
           checkBox = itemView.findViewById(R.id.checkbox);
        }
    }

}
