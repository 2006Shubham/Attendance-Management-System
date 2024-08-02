package com.shubhamdeshmukh.attendencemanagementsystem.backend;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;
import com.shubhamdeshmukh.attendencemanagementsystem.MainActivity;
import com.shubhamdeshmukh.attendencemanagementsystem.R;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.Class;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FirebaseDBConnection {

    private FirebaseDatabase database;
    public FirebaseDBConnection(String dbAccessLink)
    {
        database = FirebaseDatabase.getInstance(dbAccessLink);
    }
    public void trialCode()
    {
        Class _class = new Class();
        _class.name = "AIML 3rd Year";
        _class.attendance = new ArrayList<>();
        Map<Student, Boolean> a1 = new HashMap<>();
        a1.put(new Student("224001", "Tushar Ambhore"), false);
        a1.put(new Student("224002", "Yash Bhavsar"), true);

        _class.attendance.add(a1);

        database.getReference("Teacher").setValue(_class);
    }
}
