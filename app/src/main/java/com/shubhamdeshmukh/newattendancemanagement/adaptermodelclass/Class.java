package com.shubhamdeshmukh.newattendancemanagement.adaptermodelclass;

public class Class {

    private String claaName;
    private String year;
    private String sem;

    public Class(String claaName, String year, String sem) {
        this.claaName = claaName;
        this.year = year;
        this.sem = sem;
    }

    public String getClaasName() {
        return claaName;
    }

    public String getYear() {
        return year;
    }

    public String getSem() {
        return sem;
    }

}
