package com.shubhamdeshmukh.attendencemanagementsystem.backend.entities;

import androidx.annotation.NonNull;

public class Subject {
    String name;
    String code;
    CategoryList categories;

    public Subject() {}

    public Subject(String name, String code)
    {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    @NonNull
    @Override
    public String toString() {
        return "Name: " + name + " Code: " + code;
    }
}
