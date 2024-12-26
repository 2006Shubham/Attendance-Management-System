package com.shubhamdeshmukh.newattendancemanagement.backend.database_entities;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Category {
    private String name; // Lecture / Practical
    private ArrayList<Class> classList;

    public Category() {};

    public Category(String name) {
        this.name = name;
        this.classList = new ArrayList<>();
    }

    public void addClass(Class _class)
    {
        this.classList.add(_class);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Class> getClassList() {
        return classList;
    }

    @NonNull
    @Override
    public String toString() {
        return "Name: " + getName();
    }

    public void setClassList(ArrayList<Class> classList) {
        this.classList = classList;
    }
}
