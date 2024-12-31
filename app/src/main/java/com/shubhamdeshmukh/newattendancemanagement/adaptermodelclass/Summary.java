package com.shubhamdeshmukh.newattendancemanagement.adaptermodelclass;

import android.accessibilityservice.GestureDescription;

public class Summary extends Subject{



    public  String category;

    public String date,time ;


    public Summary(String courseName, String courseCode, String category, String date,String time) {
        super(courseName, courseCode);

        this.category = category;
        this.date = date;
        this.time = time;



    }



    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
