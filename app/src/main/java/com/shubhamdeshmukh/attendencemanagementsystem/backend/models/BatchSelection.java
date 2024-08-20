package com.shubhamdeshmukh.attendencemanagementsystem.backend.models;

import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.Batch;

public class BatchSelection {

    Batch batch;
    boolean isSelected;
    public int globalIndex; // Index for Fetched Data

    public BatchSelection(Batch batch, Boolean isSelected)
    {
        this.batch = batch;
        this.isSelected = isSelected;
    }

    public BatchSelection(Batch batch)
    {
        this.batch = batch;
        this.isSelected = false;
    }

    public Batch getBatch() {
        return batch;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
