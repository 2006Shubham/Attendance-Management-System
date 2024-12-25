package com.shubhamdeshmukh.newattendancemanagement.flow2fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.shubhamdeshmukh.newattendancemanagement.R;
import com.shubhamdeshmukh.newattendancemanagement.flow1activity.SubjectInfoActivity;
import com.shubhamdeshmukh.newattendancemanagement.flow2activity.ClassInfoActivity;

import org.jetbrains.annotations.Nullable;

public class ClassFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_class, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Find the FloatingActionButton by its ID
        FloatingActionButton fab = view.findViewById(R.id.addClassFab);
        Intent intent = new Intent(getContext(), ClassInfoActivity.class);

        // Set a click listener on the FAB
        fab.setOnClickListener(v -> {

            startActivity(intent);

        });
    }
}

