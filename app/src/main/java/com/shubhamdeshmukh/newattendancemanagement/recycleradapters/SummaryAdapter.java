package com.shubhamdeshmukh.newattendancemanagement.recycleradapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.shubhamdeshmukh.newattendancemanagement.R;
import com.shubhamdeshmukh.newattendancemanagement.adaptermodelclass.Summary;
import com.shubhamdeshmukh.newattendancemanagement.flow1activity.SummaryInfoActivity;

import java.util.ArrayList;

public class SummaryAdapter extends RecyclerView.Adapter<SummaryAdapter.ViewHolder> {

    Context context;

    ArrayList<Summary> summaryArrayList;

    Intent intent;

    public SummaryAdapter(Context context, ArrayList<Summary> summaryArrayList) {
        this.context = context;
        this.summaryArrayList = summaryArrayList;
    }

    @NonNull
    @Override
    public SummaryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_summary_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SummaryAdapter.ViewHolder holder, int position) {

        Summary summary = summaryArrayList.get(position);

        holder.courseName.setText(summary.getCourseName());
        holder.courseCode.setText(summary.getCourseCode());
        holder.date.setText(summary.getDate());
        holder.category.setText(summary.getCategory());

        holder.time.setText(summary.getTime());

        intent = new Intent(context, SummaryInfoActivity .class);

        holder.summaryCard.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {
                context.startActivity(intent);
            }
        });





    }

    @Override
    public int getItemCount() {
        return summaryArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView courseName,courseCode,date,category,time;
        CardView summaryCard ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            courseName = itemView.findViewById(R.id.courseName);
            courseCode = itemView.findViewById(R.id.courseCode);
            date = itemView.findViewById(R.id.date);
            category = itemView.findViewById(R.id.category);
            time = itemView.findViewById(R.id.time);
            summaryCard = itemView.findViewById(R.id.summaryCard);
        }
    }
}
