package com.shubhamdeshmukh.attendencemanagementsystem.backend.entities;

import java.util.ArrayList;
import java.util.Date;

public class DateEntry {
    private Date date;
    private ArrayList<StudentStatus> students;

    public DateEntry() {};

    public DateEntry(Date date)
    {
        this.date = date;
        this.students = new ArrayList<>();
    }

    public void addStudentStatus(StudentStatus studentStatus)
    {
        this.students.add(studentStatus);
    }

    public Date getDate() {
        return date;
    }

    public ArrayList<StudentStatus> getStudents() {
        return students;
    }
}
