package com.shubhamdeshmukh.attendencemanagementsystem.backend.entities;

// Represents a Student entity - Like 'Yash Bhavsar - 224002', 'Shubham Deshmukh - 226008', etc
public class Student {
    String ID;
    String name;

    public Student(String ID, String name) {
        this.ID = ID;
        this.name = name;
    }
}
