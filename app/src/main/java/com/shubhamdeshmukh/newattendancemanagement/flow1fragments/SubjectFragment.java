package com.shubhamdeshmukh.newattendancemanagement.flow1fragments;

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
import com.shubhamdeshmukh.newattendancemanagement.adaptermodelclass.Subject;
import com.shubhamdeshmukh.newattendancemanagement.flow1activity.SubjectInfoActivity;
import com.shubhamdeshmukh.newattendancemanagement.recycleradapters.SubjectsAdapter;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;


public class SubjectFragment extends Fragment {

    FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private View rootView;
    private SubjectsAdapter adapter;
    public ArrayList<Subject>subjectArrayList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        if (rootView != null) {
//            // If rootView already has a parent, detach it
//            ViewGroup parent = (ViewGroup) rootView.getParent();
//            if (parent != null) {
//                parent.removeView(rootView);
//            }
//        } else {
//            // Inflate the layout if rootView is null
//        }
        rootView = inflater.inflate(R.layout.fragment_subject, container, false);
        return rootView;

    }



    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Find the FloatingActionButton by its ID
        FloatingActionButton fab = view.findViewById(R.id.fab);
        Intent intent = new Intent(getContext(), SubjectInfoActivity.class);

        // Set a click listener on the FAB
        fab.setOnClickListener(v -> {

            startActivity(intent);

        });


        recyclerView = view.findViewById(R.id.recyclerViewSubjects);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        subjectArrayList = new ArrayList<>();
        subjectArrayList.add(new Subject("Cloud Computing", "6S406"));
        subjectArrayList.add(new Subject("Computer Network", "6S405"));
        subjectArrayList.add(new Subject("Advance Microprocessor", "6P401"));
        subjectArrayList.add(new Subject("Seminar", "6P402"));
        subjectArrayList.add(new Subject("Mobile Commputing", "6P403"));


        adapter = new SubjectsAdapter(getContext(), subjectArrayList, false);
        recyclerView.setAdapter(adapter);


    }

//    public void onDestroyView() {
//        super.onDestroyView();
//        // Clean up the rootView reference if needed
//        rootView = null;
//    }




}