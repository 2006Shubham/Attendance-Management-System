package com.shubhamdeshmukh.attendencemanagementsystem.frontend.teacher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shubhamdeshmukh.attendencemanagementsystem.R;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.StudentStatus;

import java.util.ArrayList;

public class AttendanceRecyclerAdapter extends RecyclerView.Adapter<AttendanceRecyclerAdapter.ViewHolder> {

    Context context;
    ArrayList<StudentStatus> studentStatusList;

    AttendanceRecyclerAdapter(ArrayList<StudentStatus> studentStatusList, Context context) {

        this.studentStatusList = studentStatusList;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.student_recycler_layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.status.setChecked(studentStatusList.get(position).isStatus());
        holder.status.setClickable(false);
        holder.name.setText(studentStatusList.get(position).getStudent().getName());

    }

    @Override
    public int getItemCount() {
        return studentStatusList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        CheckBox status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            status = itemView.findViewById(R.id.status);
        }
    }
}
