package com.shubhamdeshmukh.attendencemanagementsystem.backend.entities;

import java.util.ArrayList;
import java.util.Map;

public class Category {
   public  String name; // Lecture / Practical
    public ArrayList<Class> classList;

    public Category(String name) {
        this.name = name;
        this.classList = new ArrayList<>();
    }

    public void addClass(Class _class)
    {
        this.classList.add(_class);
    }

}
