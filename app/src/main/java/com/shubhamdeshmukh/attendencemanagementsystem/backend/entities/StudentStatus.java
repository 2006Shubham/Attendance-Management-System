package com.shubhamdeshmukh.attendencemanagementsystem.backend.entities;

public class StudentStatus {
    public Student student;
    public boolean status;

    public StudentStatus(String ID, String name, boolean status)
    {
        this.student = new Student(ID, name);
        this.status = status;
    }
}
