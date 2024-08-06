package com.shubhamdeshmukh.attendencemanagementsystem.backend.entities;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Subject {
    private String name;
    private String code;
    private ArrayList<Category> categoryList;

    public Subject() {}

    public Subject(String name, String code)
    {
        this.name = name;
        this.code = code;
        this.categoryList = new ArrayList<>();
    }

    public void addCategory(Category category)
    {
        this.categoryList.add(category);
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public ArrayList<Category> getCategoryList() { return categoryList; }

    @NonNull
    @Override
    public String toString() {
        return "Name: " + name + " Code: " + code;
    }
}
