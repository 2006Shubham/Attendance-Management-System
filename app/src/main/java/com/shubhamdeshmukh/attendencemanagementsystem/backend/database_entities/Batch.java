package com.shubhamdeshmukh.attendencemanagementsystem.backend.database_entities;

public class Batch {
    public String name;
    public String startId;
    public String endId;

    public Batch() {}
    public Batch(String name, String startId, String endId) {
        this.name = name;
        this.startId = startId;
        this.endId = endId;
    }

    public String getName() {
        return name;
    }

    public String getStartId() {
        return startId;
    }

    public String getEndId() {
        return endId;
    }

    @Override
    public String toString() {
        return "Batch{" +
                "name='" + name + '\'' +
                ", startId='" + startId + '\'' +
                ", endId='" + endId + '\'' +
                '}';
    }
}
