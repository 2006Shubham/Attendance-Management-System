package com.shubhamdeshmukh.newattendancemanagement.recycleradapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shubhamdeshmukh.newattendancemanagement.R;
import com.shubhamdeshmukh.newattendancemanagement.adaptermodelclass.Class;

import java.util.ArrayList;


public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ViewHolder> {

    Context context;
    ArrayList<Class>classArrayList;

    public ClassAdapter(ArrayList<Class> classArrayList, Context context){

        this.classArrayList = classArrayList;
        this.context = context;

    }

    @NonNull
    @Override
    public ClassAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_class_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassAdapter.ViewHolder holder, int position) {

        Class classModel = classArrayList.get(position);
        holder.classNname.setText(classModel.getClaasName());
        holder.classSem.setText(classModel.getSem());

    }

    @Override
    public int getItemCount() {
        return classArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        TextView classNname,classSem;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            classNname = itemView.findViewById(R.id.className);
            classSem = itemView.findViewById(R.id.classSem);
        }
    }
}
