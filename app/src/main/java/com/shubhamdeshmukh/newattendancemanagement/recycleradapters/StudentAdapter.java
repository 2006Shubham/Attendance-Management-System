package com.shubhamdeshmukh.newattendancemanagement.recycleradapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shubhamdeshmukh.newattendancemanagement.R;
import com.shubhamdeshmukh.newattendancemanagement.adaptermodelclass.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private Context context;
    private ArrayList<Student> studentList;
    private Boolean layout;

    public StudentAdapter(Context context, ArrayList<Student> studentList) {
        this.context = context;
        this.studentList = studentList;
        this.layout = true;
    }

    public StudentAdapter(Context context, ArrayList<Student> studentList,Boolean layout) {
        this.context = context;
        this.studentList = studentList;
        this.layout = layout;
    }


    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View view;
        if(layout) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_student_profile, parent, false);
        }else {
            view = LayoutInflater.from(context).inflate(R.layout.layout_student_list, parent, false);
        }
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = studentList.get(position);

        if(layout) {
            holder.name.setText(student.getName());
            holder.rollNumber.setText(student.getRollNumber());
            holder.checkBox.setChecked(student.isChecked());
            holder.checkBox.setOnClickListener(v -> student.setChecked(holder.checkBox.isChecked()));

        }

        else {
            holder.name.setText(student.getName());
            holder.rollNumber.setText(student.getRollNumber());
            holder.checkBox.setChecked(student.isChecked());
            holder.checkBox.setClickable(false);
        }
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView name, rollNumber;
        CheckBox checkBox;

        ImageView imageView;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.studentName);
            rollNumber = itemView.findViewById(R.id.studentID);
            checkBox = itemView.findViewById(R.id.checkbox);

        }
    }
}
