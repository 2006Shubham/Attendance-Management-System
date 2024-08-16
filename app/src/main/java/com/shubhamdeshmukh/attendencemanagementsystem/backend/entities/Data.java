package com.shubhamdeshmukh.attendencemanagementsystem.backend.entities;

import java.util.ArrayList;

public class Data {
    public ArrayList<Account> accounts;
    public ArrayList<Subject> subjects;
    public ArrayList<Category> categories;
    public ArrayList<Class> classes;
    public ArrayList<Batch> batches;

    public Data()
    {
        this.accounts = new ArrayList<>();
        this.subjects = new ArrayList<>();
        this.categories = new ArrayList<>();
        this.classes = new ArrayList<>();
        this.batches = new ArrayList<>();
    }

    public Data(ArrayList<Account> accounts, ArrayList<Subject> subjects, ArrayList<Category> categories, ArrayList<Class> classes, ArrayList<Batch> batches) {
        this.accounts = accounts;
        this.subjects = subjects;
        this.categories = categories;
        this.classes = classes;
        this.batches = batches;
    }

    public void addAccount(Account account)
    {
        accounts.add(account);
    }

    public void addSubject(Subject subject)
    {
        subjects.add(subject);
    }

    public void addCategory(Category category)
    {
        categories.add(category);
    }

    public void addClass(Class _class)
    {
        classes.add(_class);
    }

    public void addBatch(Batch batch)
    {
        batches.add(batch);
    }
}
