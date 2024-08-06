package com.shubhamdeshmukh.attendencemanagementsystem.backend.entities;

import android.util.Log;

import java.util.ArrayList;

public class Teacher extends Account{

    private ArrayList<Subject> subjects;

    public Teacher() { super("Teacher"); }

    public Teacher(String name)
    {
        super("Teacher");
        this.setName(name);
        this.subjects = new ArrayList<>();
    }

    public Teacher(String name, String userID)
    {
        super(name, userID, "Teacher");
        this.subjects = new ArrayList<>();
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public void addSubject(Subject subject)
    {
        this.subjects.add(subject);
    }

    public String printInfo()
    {
        return "Name: " + this.getName() + " Subjects: " + this.subjects;
    }
}
