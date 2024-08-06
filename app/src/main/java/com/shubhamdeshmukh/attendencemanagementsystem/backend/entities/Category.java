package com.shubhamdeshmukh.attendencemanagementsystem.backend.entities;

import java.util.ArrayList;
import java.util.Map;

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

    public ArrayList<Class> getClassList() {
        return classList;
    }
}
