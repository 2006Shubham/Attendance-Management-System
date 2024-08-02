package com.shubhamdeshmukh.attendencemanagementsystem.backend.entities;

import java.util.ArrayList;
import java.util.Map;

// Defines a Class Entity - like 'AIML 3rd Year', 'CO 2nd Year', etc.
public class Class {
    public String name;    // Ex. "AIML 3rd Year"
    public ArrayList<Map<Student, Boolean>> attendance;
}
