//package com.shubhamdeshmukh.newattendancemanagement.recycleradapters;
//
//import android.content.Context;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.shubhamdeshmukh.newattendancemanagement.R;
//import com.shubhamdeshmukh.newattendancemanagement.adaptermodelclass.AttendanceSubject;
//import com.shubhamdeshmukh.newattendancemanagement.flow4activity.TakeAttendanceOverviewActivity;
//
//import java.util.List;
//
//public class AttendanceSubjectAdapter extends RecyclerView.Adapter<AttendanceSubjectAdapter.AttendanceSubjectViewHolder> {
//
//    private Context context;
//    private List<AttendanceSubject> subjectList;
//
//    public AttendanceSubjectAdapter(Context context, List<AttendanceSubject> subjectList) {
//        this.context = context;
//        this.subjectList = subjectList;
//    }
//
//    @NonNull
//    @Override
//    public AttendanceSubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        // Inflate the item layout
//        View view = LayoutInflater.from(context).inflate(R.layout.layout_take_attendance_subject_list, parent, false);
//        return new AttendanceSubjectViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull AttendanceSubjectViewHolder holder, int position) {
//        AttendanceSubject subject = subjectList.get(position);
//
//        // Bind data to views
//        holder.subjectName.setText(subject.getSubjectName());
//        holder.subjectImage.setImageResource(subject.getImageResource());
//
//        // Set click listener on the button
//        holder.takeAttendanceButton.setOnClickListener(v -> {
//            Intent intent = new Intent(context, TakeAttendanceOverviewActivity.class);
//            intent.putExtra("subjectName", subject.getSubjectName()); // Pass subject info if needed
//            context.startActivity(intent);
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return subjectList.size();
//    }
//
//    public static class AttendanceSubjectViewHolder extends RecyclerView.ViewHolder {
//        TextView subjectName;
//        ImageView subjectImage;
//        Button takeAttendanceButton;
//
//        public AttendanceSubjectViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            subjectName = itemView.findViewById(R.id.subjectName);
//            subjectImage = itemView.findViewById(R.id.subjectImage);
//            takeAttendanceButton = itemView.findViewById(R.id.takeAttendance);
//        }
//    }
//}


package com.shubhamdeshmukh.newattendancemanagement.recycleradapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shubhamdeshmukh.newattendancemanagement.R;
import com.shubhamdeshmukh.newattendancemanagement.adaptermodelclass.AttendanceSubject;
import com.shubhamdeshmukh.newattendancemanagement.adaptermodelclass.Batch;
import com.shubhamdeshmukh.newattendancemanagement.flow4activity.TakeAttendanceOverviewActivity;

import java.util.ArrayList;

public class AttendanceSubjectAdapter extends RecyclerView.Adapter<AttendanceSubjectAdapter.AttendanceSubjectViewHolder> {

    private Context context;
    private ArrayList<AttendanceSubject> subjectList;

    public AttendanceSubjectAdapter(Context context, ArrayList<AttendanceSubject> subjectList) {
        this.context = context;
        this.subjectList = subjectList;
    }

    @NonNull
    @Override
    public AttendanceSubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_take_attendance_subject_list, parent, false);
        return new AttendanceSubjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttendanceSubjectViewHolder holder, int position) {
        AttendanceSubject subject = subjectList.get(position);
        holder.subjectName.setText(subject.getSubjectName());
        holder.subjectImage.setImageResource(subject.getImageResource());

        // Set up the nested RecyclerView (Batch list)
        ArrayList<Batch> batchList = subject.getBatchList(); // Assuming you have batches for each subject
        BatchAdapter batchAdapter = new BatchAdapter((ArrayList<Batch>) batchList, context, true, true);
        holder.batchRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        holder.batchRecyclerView.setAdapter(batchAdapter);

        holder.takeAttendanceButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, TakeAttendanceOverviewActivity.class);
            intent.putExtra("subjectName", subject.getSubjectName());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }

    public static class AttendanceSubjectViewHolder extends RecyclerView.ViewHolder {
        TextView subjectName;
        ImageView subjectImage;
        Button takeAttendanceButton;
        RecyclerView batchRecyclerView;

        public AttendanceSubjectViewHolder(@NonNull View itemView) {
            super(itemView);
            subjectName = itemView.findViewById(R.id.subjectName);
            subjectImage = itemView.findViewById(R.id.subjectImage);
            takeAttendanceButton = itemView.findViewById(R.id.takeAttendance);
            batchRecyclerView = itemView.findViewById(R.id.batchRecyclerView);
        }
    }
}

