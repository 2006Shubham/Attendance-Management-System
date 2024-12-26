package com.shubhamdeshmukh.newattendancemanagement.flow4activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shubhamdeshmukh.newattendancemanagement.HomeActivity;
import com.shubhamdeshmukh.newattendancemanagement.R;
import com.shubhamdeshmukh.newattendancemanagement.adaptermodelclass.Student;
import com.shubhamdeshmukh.newattendancemanagement.recycleradapters.StudentAdapter;

import java.util.ArrayList;
import java.util.List;

public class StudentListForAttendanceActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StudentAdapter adapter;
    private ArrayList<Student> studentList;

    Button btn ;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list_for_attendance);

        recyclerView = findViewById(R.id.recyclerViewStudents);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        intent = new Intent(this, HomeActivity.class);
        btn = findViewById(R.id.btnDone);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
                finish();
            }
        });


        // Initialize the student list
        studentList = new ArrayList<>();
        studentList.add(new Student("Shubham", "226008", true));
        studentList.add(new Student("Jay", "226009", true));
        studentList.add(new Student("Suraj", "226037", false));
        studentList.add(new Student("Vishal", "226038", true));
        studentList.add(new Student("Shubham", "226039", false));

        studentList.add(new Student("Shubham", "226008", true));
        studentList.add(new Student("Jay", "226009", true));
        studentList.add(new Student("Suraj", "226037", false));
        studentList.add(new Student("Vishal", "226038", true));
        studentList.add(new Student("Shubham", "226039", false));

        studentList.add(new Student("Shubham", "226008", true));
        studentList.add(new Student("Jay", "226009", true));
        studentList.add(new Student("Suraj", "226037", false));
        studentList.add(new Student("Vishal", "226038", true));
        studentList.add(new Student("Shubham", "226039", false));

        // Set the adapter
        adapter = new StudentAdapter(this,studentList);
        recyclerView.setAdapter(adapter);
    }
}
