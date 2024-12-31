package com.shubhamdeshmukh.newattendancemanagement.adaptermodelclass;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class Student {
    private String name;
    private String rollNumber;
    private boolean isChecked;




    public Student(String name, String rollNumber, boolean isChecked) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.isChecked = isChecked;
    }




    public String getName() {
        return name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }


}
