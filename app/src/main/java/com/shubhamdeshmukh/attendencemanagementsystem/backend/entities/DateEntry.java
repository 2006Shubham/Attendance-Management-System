package com.shubhamdeshmukh.attendencemanagementsystem.backend.entities;

import java.util.ArrayList;
import java.util.Date;

public class DateEntry {
    public Date date;
    public ArrayList<StudentStatus> students;

    public DateEntry(Date date)
    {
        this.date = date;
        this.students = new ArrayList<>();
    }

    public void addStudentStatus(StudentStatus studentStatus)
    {
        this.students.add(studentStatus);
    }
}
