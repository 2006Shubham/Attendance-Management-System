package com.shubhamdeshmukh.newattendancemanagement.adaptermodelclass;

public class Subject {

    private String courseName;
    private String courseCode;

    public Subject(String courseName, String courseCode) {
        this.courseName = courseName;
        this.courseCode = courseCode;
    }


    public String getCourseName() {
        return courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }



}
