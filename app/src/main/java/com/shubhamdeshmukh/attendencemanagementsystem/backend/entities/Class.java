package com.shubhamdeshmukh.attendencemanagementsystem.backend.entities;

import java.util.ArrayList;

// Defines a Class Entity - like 'AIML 3rd Year', 'CO 2nd Year', etc.
public class Class {
    private String name;    // Ex. "AIML 3rd Year"
    private ArrayList<Attendance> attendanceList;

    public Class() {}

    public Class(String name)
    {
        this.name = name;
        this.attendanceList = new ArrayList<>();
    }

    public void addAttendance(Attendance attendance) {
        this.attendanceList.add(attendance);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Attendance> getAttendanceList() {
        return attendanceList;
    }
}
