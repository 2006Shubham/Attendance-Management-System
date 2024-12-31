package com.shubhamdeshmukh.newattendancemanagement.flow1activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shubhamdeshmukh.newattendancemanagement.R;
import com.shubhamdeshmukh.newattendancemanagement.adaptermodelclass.Student;
import com.shubhamdeshmukh.newattendancemanagement.recycleradapters.StudentAdapter;

import java.util.ArrayList;

public class SummaryInfoActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Student> studentArrayList;

    StudentAdapter studentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_summary_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerViewStudents);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        studentArrayList = new ArrayList<>();

        studentArrayList.add(new Student("Shubham", "226008", true));
        studentArrayList.add(new Student("Jay", "226009", true));
        studentArrayList.add(new Student("Suraj", "226037", false));
        studentArrayList.add(new Student("Vishal", "226038", true));
        studentArrayList.add(new Student("Shubham", "226039", true));
        studentArrayList.add(new Student("Shubham", "226008", false));
        studentArrayList.add(new Student("Jay", "226009", true));
        studentArrayList.add(new Student("Suraj", "226037", false));
        studentArrayList.add(new Student("Vishal", "226038", true));
        studentArrayList.add(new Student("Shubham", "226039", false));

        studentAdapter = new StudentAdapter(this, studentArrayList,false);
        recyclerView.setAdapter(studentAdapter);



    }
}