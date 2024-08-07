package com.shubhamdeshmukh.attendencemanagementsystem.frontend.teacher;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shubhamdeshmukh.attendencemanagementsystem.R;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.Class;

import java.util.ArrayList;

public class ClassSelectionRecyclerAdapter extends RecyclerView.Adapter<ClassSelectionRecyclerAdapter.ViewHolder> {

    ArrayList<Class> classArrayList;

    Context context;

    ClassSelectionRecyclerAdapter(Context context,ArrayList<Class>classArrayList){

        this.classArrayList = classArrayList;
        this.context = context;


    }

    @NonNull
    @Override
    public ClassSelectionRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(context).inflate(R.layout.layout_class_list_recycler,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);

        return  viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ClassSelectionRecyclerAdapter.ViewHolder holder, int position) {


        holder.className.setText(classArrayList.get(position).getName());

        Intent intent = new Intent(context,AttendanceViewActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        holder.classLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                context.startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return classArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView className;

        LinearLayout classLinearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            classLinearLayout = itemView.findViewById(R.id.class_card);
            className = itemView.findViewById(R.id.classname);
        }
    }
}
