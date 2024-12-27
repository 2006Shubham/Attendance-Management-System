package com.shubhamdeshmukh.newattendancemanagement.backend.other_entities;

// User Account representing either Teacher or a Student (Class representative) Entity
public class Account {
    private String name;
    private String userID;

    private String type;
    private MetaInfo metaInfo;

    public Account () {}

    public Account (String type) { this.type = type; }

    public Account(String name, String userID, String type) {
        this.name = name;
        this.userID = userID;
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public String getUserID() {
        return userID;
    }

    public String getType() { return type; }

    public MetaInfo getMetaInfo() {
        return metaInfo;
    }
}
