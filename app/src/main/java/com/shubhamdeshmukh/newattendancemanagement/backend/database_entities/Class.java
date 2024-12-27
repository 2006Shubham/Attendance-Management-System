package com.shubhamdeshmukh.newattendancemanagement.backend.database_entities;

import android.util.Log;

//import com.shubhamdeshmukh.newattendancemanagement.frontend.MainActivity;

import java.util.ArrayList;
import java.util.Calendar;

// Defines a Class Entity - like 'AIML 3rd Year', 'CO 2nd Year', etc.
public class Class {
    private String name;    // Ex. "AIML 3rd Year"
    private ArrayList<Attendance> attendanceList;

    public ArrayList<Batch> batchList;

    public Class() {

        this.attendanceList = new ArrayList<>();
        this.batchList = new ArrayList<>();
    }

    public Class(String name)
    {
        this.name = name;
        this.attendanceList = new ArrayList<>();
        this.batchList = new ArrayList<>();
    }

    public void addAttendance(Attendance attendance) {
        this.attendanceList.add(attendance);
    }

    public void addBatch(Batch batch) {
        this.batchList.add(batch);
    }


    public String getName() {
        if (!attendanceList.isEmpty())
        {
//            Log.d(MainActivity.TAG, "getName: SEE AL Date: " + attendanceList.get(0).getDate().getTime());
//            Log.d(MainActivity.TAG, "getName: SEE SYS Date: " + Calendar.getInstance().getTime());
        }
        return name;
    }

    public ArrayList<Attendance> getAttendanceList() {
        return attendanceList;
    }

    public Attendance getAttendance(Calendar calendarDate)
    {
        for (Attendance attendance:
             this.attendanceList) {
//            if (attendance.getDate().compareTo(date) == 0)
            Calendar cal = Calendar.getInstance();
            cal.setTime(attendance.getDate());
            if (isSameCalendarDate(cal, calendarDate))
            {
//                Log.d(MainActivity.TAG, "getAttendance: Same Date");
                return attendance;
            }
            else {
//                Log.d(MainActivity.TAG, "getAttendance: Different Date " + attendance.getDate() + " | " + calendarDate);
            }
        }
        return null;
    }

    private boolean isSameCalendarDate(Calendar c1, Calendar c2)
    {
        if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR))
        {
            if (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH))
            {
                return c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH);
            }
        }
        return false;
    }

    public ArrayList<Batch> getBatchList() {
        return batchList;
    }

    public void setBatchList(ArrayList<Batch> batchList) {
        this.batchList = batchList;
    }

    public void setName(String name) {
        this.name = name;
    }
}
