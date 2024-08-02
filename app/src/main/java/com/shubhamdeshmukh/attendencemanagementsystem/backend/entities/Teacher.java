package com.shubhamdeshmukh.attendencemanagementsystem.backend.entities;

import android.util.Log;

import java.util.ArrayList;

public class Teacher {
    String name;
    ArrayList<Subject> subjects;

    public Teacher() {}

    public Teacher(String name)
    {
        this.name = name;
        this.subjects = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public void addSubject(String name, String code)
    {
        this.subjects.add(new Subject(name, code));
    }

    public void addSubject(Subject subject)
    {
        this.subjects.add(subject);
    }

    public String printInfo()
    {
        return "Name: " + this.name + " Subjects: " + this.subjects;
    }
}
