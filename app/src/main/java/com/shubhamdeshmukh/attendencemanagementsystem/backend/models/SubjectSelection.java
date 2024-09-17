package com.shubhamdeshmukh.attendencemanagementsystem.backend.models;

import com.shubhamdeshmukh.attendencemanagementsystem.backend.database_entities.Subject;

public class SubjectSelection {
    Subject subject;
    boolean isSelected;
//    public int globalIndex; // Index for Fetched Data

    public SubjectSelection(Subject subject, Boolean isSelected)
    {
        this.subject = subject;
        this.isSelected = isSelected;
    }

    public SubjectSelection(Subject subject)
    {
        this.subject = subject;
        this.isSelected = false;
    }

    public Subject getSubject() {
        return subject;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
