package com.shubhamdeshmukh.attendencemanagementsystem.backend.models;

import com.shubhamdeshmukh.attendencemanagementsystem.backend.database_entities.Batch;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.database_entities.Class;

import java.util.ArrayList;

public class ClassSelection {

    Class _class;
    boolean isSelected;
    ArrayList<BatchSelection> batchSelectionArrayList;

    public int globalIndex; // Index for Fetched Data

    public ClassSelection(Class _class, boolean isSelected)
    {
        this._class = _class;
        this.isSelected = isSelected;
    }

    public ClassSelection(Class _class)
    {
        this._class = _class;
        this.isSelected = false;
    }

    public Class getThisClass() {
        return _class;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setThisClass(Class _class) {
        this._class = _class;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
