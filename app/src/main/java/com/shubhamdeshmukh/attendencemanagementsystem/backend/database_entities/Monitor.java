package com.shubhamdeshmukh.attendencemanagementsystem.backend.database_entities;

import com.shubhamdeshmukh.attendencemanagementsystem.backend.other_entities.Account;

public class Monitor extends Account {

    private String id;
    private String className;

    public Monitor() { super("Monitor"); }

    public Monitor(String name, String enNo, String userId) {
        super(name, userId, "Monitor");
        this.id = enNo;
    }

    public Monitor(String name, String enNo, String userId, String className) {
        super(name, userId, "Monitor");
        this.className = className;
        this.id = enNo;
    }

    public String getClassName() {
        return className;
    }

    public String getId() { return id; }

    public String printInfo() { return "Name: " + getName() + "En.No: " + id + " Class: " + className; }
}
