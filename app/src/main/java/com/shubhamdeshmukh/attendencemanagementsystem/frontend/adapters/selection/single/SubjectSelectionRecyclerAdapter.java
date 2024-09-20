package com.shubhamdeshmukh.attendencemanagementsystem.frontend.adapters.selection.single;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shubhamdeshmukh.attendencemanagementsystem.R;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.models.SubjectSelection;
import com.shubhamdeshmukh.attendencemanagementsystem.frontend.teacherregistration.RegisterSubjectInfoAndCategoryActivity;

import java.util.ArrayList;

public class SubjectSelectionRecyclerAdapter extends RecyclerView.Adapter<SubjectSelectionRecyclerAdapter.ViewHolder> {

    Context context;
    ArrayList<SubjectSelection> subjectSelectionArrayList;

    public SubjectSelectionRecyclerAdapter(Context context, ArrayList<SubjectSelection> subjectSelectionArrayList){

        this.context = context;
        this.subjectSelectionArrayList = subjectSelectionArrayList;

    }

    @NonNull
    @Override
    public SubjectSelectionRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_class_and_subject_category_register_recycler,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectSelectionRecyclerAdapter.ViewHolder holder, int position) {

        holder.subname.setText(subjectSelectionArrayList.get(holder.getBindingAdapterPosition()).getSubject().getName()+" ("+ subjectSelectionArrayList.get(holder.getBindingAdapterPosition()).getSubject().getCode()+")");

        holder.checkBox.setChecked(subjectSelectionArrayList.get(holder.getBindingAdapterPosition()).isSelected());

        holder.subname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RegisterSubjectInfoAndCategoryActivity.class);
                intent.putExtra("subjectFetchedDataIndex", holder.getBindingAdapterPosition());
                context.startActivity(intent);
            }
        });

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.checkBox.isChecked())
                {
                    holder.checkBox.setChecked(false);
                    Intent intent = new Intent(context, RegisterSubjectInfoAndCategoryActivity.class);
                    intent.putExtra("subjectFetchedDataIndex", holder.getBindingAdapterPosition());
                    context.startActivity(intent);
                }
            }
        });
    }

    public ArrayList<SubjectSelection> getSubjectSelectionArrayList() {
        return subjectSelectionArrayList;
    }

    @Override
    public int getItemCount() {
        return subjectSelectionArrayList.size();
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