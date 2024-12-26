package com.shubhamdeshmukh.newattendancemanagement.recycleradapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AlertDialog;
import com.shubhamdeshmukh.newattendancemanagement.R;
import com.shubhamdeshmukh.newattendancemanagement.adaptermodelclass.Subject;
import com.shubhamdeshmukh.newattendancemanagement.flow3activity.YourLoadActivity;

import java.util.ArrayList;

public class SubjectsAdapter extends RecyclerView.Adapter<SubjectsAdapter.ViewHolder> {

    private ArrayList<Subject> subjectArrayList;
    private Context context;
    private boolean showPopup;  // Flag to control whether to show popup or not

    public SubjectsAdapter(Context context, ArrayList<Subject> subjectArrayList, boolean showPopup) {
        this.subjectArrayList = subjectArrayList;
        this.context = context;
        this.showPopup = showPopup;  // Pass the flag to the constructor
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout for each RecyclerView item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_subject_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the current subject at position
        Subject subject = subjectArrayList.get(position);

        // Set course name and code in the corresponding TextViews
        holder.courseName.setText(subject.getCourseName());
        holder.courseCode.setText(subject.getCourseCode());

        // If showPopup is true, set an OnClickListener for the card to show a popup
        holder.itemView.setOnClickListener(v -> {
            if (showPopup) {
                // If showPopup is true, display the popup dialog
                showYourLoadActivity(subject);
            }
        });

        Log.d("Shubham", "onBindViewHolder: Subject " + subject.getCourseName());
    }

    @Override
    public int getItemCount() {
        return subjectArrayList.size();
    }

    // Method to show a popup dialog with subject details
    private void showYourLoadActivity(Subject subject) {
        // Create and display an AlertDialog to show the subject details
        Intent intent = new Intent(context, YourLoadActivity.class);
        context.startActivity(intent);
    }

    // ViewHolder class to bind data to the views
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView courseName, courseCode;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            courseName = itemView.findViewById(R.id.courseName);
            courseCode = itemView.findViewById(R.id.courseCode);
        }
    }
}
