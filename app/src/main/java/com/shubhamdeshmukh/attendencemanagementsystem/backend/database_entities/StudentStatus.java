package com.shubhamdeshmukh.attendencemanagementsystem.backend.database_entities;

public class StudentStatus {
    private Student student;
    private boolean status;

    public StudentStatus() {}

    public StudentStatus(String ID, String name, boolean status)
    {
        this.student = new Student(ID, name);
        this.status = status;
    }

    public Student getStudent() {
        return student;
    }

    public boolean isStatus() {
        return status;
    }
}
