package com.shubhamdeshmukh.attendencemanagementsystem.backend.models;

import com.shubhamdeshmukh.attendencemanagementsystem.backend.database_entities.Category;

public class CategorySelection {

    Category category;
    boolean isSelected;
//    public int globalIndex; // Index for Fetched Data

    public CategorySelection(Category category, Boolean isSelected)
    {
        this.category = category;
        this.isSelected = isSelected;
    }

    public CategorySelection(Category category)
    {
        this.category = category;
        this.isSelected = false;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
