package com.shubhamdeshmukh.newattendancemanagement.flow2fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.shubhamdeshmukh.newattendancemanagement.R;
import com.shubhamdeshmukh.newattendancemanagement.adaptermodelclass.Class;
import com.shubhamdeshmukh.newattendancemanagement.flow1activity.SubjectInfoActivity;
import com.shubhamdeshmukh.newattendancemanagement.flow2activity.ClassInfoActivity;
import com.shubhamdeshmukh.newattendancemanagement.recycleradapters.ClassAdapter;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class ClassFragment extends Fragment {

    FloatingActionButton fab;
    Intent intent;
    RecyclerView recyclerView;

    ArrayList<Class>classArrayList;
    ClassAdapter classAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_class, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Find the FloatingActionButton by its ID
         fab = view.findViewById(R.id.addClassFab);
         intent = new Intent(getContext(), ClassInfoActivity.class);
         recyclerView = view.findViewById(R.id.recyclerViewClass);
         recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
         classArrayList = new ArrayList<>();

         classArrayList.add(new Class("CO","FY","IV"));
        classArrayList.add(new Class("AN","FY","IV"));
        classArrayList.add(new Class("IT","FY","IV"));
        classArrayList.add(new Class("MEC","FY","IV"));
        classArrayList.add(new Class("Auto","FY","IV"));
        classArrayList.add(new Class("E&TC","FY","IV"));
        classArrayList.add(new Class("ET","FY","IV"));
        classArrayList.add(new Class("CS","FY","IV"));
        classArrayList.add(new Class("DDGM","FY","IV"));
        classArrayList.add(new Class("CO","FY","IV"));
        classArrayList.add(new Class("CO","FY","IV"));

        classArrayList.add(new Class("CO","FY","IV"));
        classArrayList.add(new Class("CO","FY","IV"));

         classAdapter = new ClassAdapter(classArrayList,getContext());
         recyclerView.setAdapter(classAdapter);

        // Set a click listener on the FAB
        fab.setOnClickListener(v -> {

            startActivity(intent);

        });
    }
}

