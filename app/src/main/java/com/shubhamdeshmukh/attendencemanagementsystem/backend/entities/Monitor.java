package com.shubhamdeshmukh.attendencemanagementsystem.backend.entities;

public class Monitor extends Account {

    private String className;

    public Monitor() { super("Monitor"); }

    public Monitor(String name, String userId) {
        super(name, userId, "Monitor");
    }

    public Monitor(String name, String userId, String className) { super(name, userId, "Monitor"); this.className = className; }

    public String getClassName() {
        return className;
    }

    public String printInfo() { return "Name: " + getName() + " Class: " + className; }
}
