package com.shubhamdeshmukh.newattendancemanagement.flow4activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shubhamdeshmukh.newattendancemanagement.HomeActivity;
import com.shubhamdeshmukh.newattendancemanagement.R;
import com.shubhamdeshmukh.newattendancemanagement.adaptermodelclass.Student;
import com.shubhamdeshmukh.newattendancemanagement.flow1fragments.HomeFragment;
import com.shubhamdeshmukh.newattendancemanagement.recycleradapters.StudentAdapter;

import java.util.ArrayList;

public class StudentListForAttendanceActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StudentAdapter adapter;
    private ArrayList<Student> studentList;
    private LinearLayoutManager layoutManager;

    private int currentPosition = 0;

    Button btn;
    TextView absent, present;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list_for_attendance);

        recyclerView = findViewById(R.id.recyclerViewStudents);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        btn = findViewById(R.id.btnDone);
        btn.setOnClickListener(view -> {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        });

        // Initialize the student list
        studentList = new ArrayList<>();
        studentList.add(new Student("Shubham", "226008", false));
        studentList.add(new Student("Jay", "226009", false));
        studentList.add(new Student("Suraj", "226037", false));
        studentList.add(new Student("Vishal", "226038", false));
        studentList.add(new Student("Shubham", "226039", false));
        studentList.add(new Student("Shubham", "226008", false));
        studentList.add(new Student("Jay", "226009", false));
        studentList.add(new Student("Suraj", "226037", false));
        studentList.add(new Student("Vishal", "226038", false));
        studentList.add(new Student("Shubham", "226039", false));

        // Set the adapter
        adapter = new StudentAdapter(this, studentList);
        recyclerView.setAdapter(adapter);

        // Button actions
        absent = findViewById(R.id.txtAbsent);
        present = findViewById(R.id.txtPresent);

        setupArrowNavigation(studentList.size());
    }

    private void setupArrowNavigation(int itemCount) {
        absent.setOnClickListener(v -> {
            if (currentPosition < itemCount) {
                // Check current item's checkbox state (false for absent)
                updateCheckboxState(currentPosition, false);

                if (currentPosition < itemCount - 1) {
                    // Delay scrolling to the next item by 1 second
                    recyclerView.postDelayed(() -> {
                        currentPosition++;
                        recyclerView.smoothScrollToPosition(currentPosition);
                    }, 300); // 1000ms = 1 second
                }
            }
        });

        present.setOnClickListener(v -> {
            if (currentPosition < itemCount) {
                // Check current item's checkbox state (true for present)
                updateCheckboxState(currentPosition, true);

                if (currentPosition < itemCount - 1) {
                    // Delay scrolling to the next item by 1 second
                    recyclerView.postDelayed(() -> {
                        currentPosition++;
                        recyclerView.smoothScrollToPosition(currentPosition);
                    }, 300); // 1000ms = 1 second
                }
            }
        });
    }


    private void updateCheckboxState(int position, boolean isChecked) {
        Student student = studentList.get(position);
        student.setChecked(isChecked);
        adapter.notifyItemChanged(position);
    }
}
