package com.shubhamdeshmukh.attendencemanagementsystem.backend;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shubhamdeshmukh.attendencemanagementsystem.frontend.MainActivity;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.Attendance;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.Category;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.Class;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.DateEntry;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.StudentStatus;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.Subject;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.Teacher;

import java.util.Date;

public class FirebaseDBConnection {

    private FirebaseDatabase database;
    public FirebaseDBConnection(String dbAccessLink)
    {
        database = FirebaseDatabase.getInstance(dbAccessLink);
    }
    public void trialCode()
    {
        StudentStatus studentStatus = new StudentStatus("226008", "Shubham Deshmukh", true);

        DateEntry dateEntry = new DateEntry(new Date(2024, 8, 2));
        dateEntry.addStudentStatus(studentStatus);

        Attendance attendance = new Attendance();
        attendance.addDateEntry(dateEntry);

        Class _class = new Class("CO 3rd Year");
        _class.addAttendance(attendance);

        Category category = new Category("Lecture");
        category.addClass(_class);

        Subject subject = new Subject("Cloud Computing", "6S403");
        subject.addCategory(category);

        Teacher teacher = new Teacher("P V Sontakke");
        teacher.addSubject(subject);

        DatabaseReference node = database.getReference("Example");
        node.setValue(teacher);
        Log.d(MainActivity.TAG, "trialCode: Success" + teacher.printInfo());
    }
}
