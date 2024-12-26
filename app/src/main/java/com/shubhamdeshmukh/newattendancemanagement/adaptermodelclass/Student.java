package com.shubhamdeshmukh.newattendancemanagement.adaptermodelclass;

public class Student {
    private String name;
    private String id;
    private boolean isChecked;

    public Student(String name, String id, boolean isChecked) {
        this.name = name;
        this.id = id;
        this.isChecked = isChecked;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}

