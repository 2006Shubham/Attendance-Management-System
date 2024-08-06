package com.shubhamdeshmukh.attendencemanagementsystem.backend.entities;

// Represents a Student entity - Like 'Yash Bhavsar - 224002', 'Shubham Deshmukh - 226008', etc
public class Student {
    private String id;
    private String name;

    public Student() {}

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
