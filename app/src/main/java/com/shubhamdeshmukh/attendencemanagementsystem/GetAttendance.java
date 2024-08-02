package com.shubhamdeshmukh.attendencemanagementsystem;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GetAttendance extends AppCompatActivity {


    ArrayList<AttendanceRecyclerDataModel> attendanceRecyclerDataModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_get_attendance);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });



        RecyclerView recyclerView = findViewById(R.id.studentrecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        attendanceRecyclerDataModels.add(new AttendanceRecyclerDataModel("Shubham",false));
        attendanceRecyclerDataModels.add(new AttendanceRecyclerDataModel("Vishal",true));
        attendanceRecyclerDataModels.add(new AttendanceRecyclerDataModel("Yash",true));
        attendanceRecyclerDataModels.add(new AttendanceRecyclerDataModel("Suraj",false));
        attendanceRecyclerDataModels.add(new AttendanceRecyclerDataModel("Kiran",true));
        attendanceRecyclerDataModels.add(new AttendanceRecyclerDataModel("Shubham",false));
        attendanceRecyclerDataModels.add(new AttendanceRecyclerDataModel("Akash",true));
        attendanceRecyclerDataModels.add(new AttendanceRecyclerDataModel("Harsh",false));

        AttendanceRecyclerAdapter adapter = new AttendanceRecyclerAdapter(attendanceRecyclerDataModels,this);
        recyclerView.setAdapter(adapter);



    }
}