package com.shubhamdeshmukh.attendencemanagementsystem.backend.entities;

import java.util.ArrayList;

public class Attendance {
    public ArrayList<DateEntry> dateEntryList;

    public Attendance()
    {
        dateEntryList = new ArrayList<>();
    }

    public void addDateEntry(DateEntry dateEntry)
    {
        this.dateEntryList.add(dateEntry);
    }
}
