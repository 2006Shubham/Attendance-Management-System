package com.shubhamdeshmukh.newattendancemanagement.flow4fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shubhamdeshmukh.newattendancemanagement.R;
import com.shubhamdeshmukh.newattendancemanagement.adaptermodelclass.Subject;
import com.shubhamdeshmukh.newattendancemanagement.flow3activity.YourLoadActivity;
import com.shubhamdeshmukh.newattendancemanagement.recycleradapters.SubjectsAdapter;

import java.util.ArrayList;

//
//public class YourLoadFragment extends Fragment {
//
//
//    CardView subject;
//
//    private RecyclerView recyclerView;
//    private View rootView;
//    private SubjectsAdapter adapter;
//    public ArrayList<Subject> subjectArrayList;
//
//    Intent intent;
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_your_load, container, false);
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
////        subject = view.findViewById(R.id.subject1);
//        intent = new Intent(getActivity(), YourLoadActivity.class);
//
//
//        recyclerView = view.findViewById(R.id.recyclerViewSubjects);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        subjectArrayList = new ArrayList<>();
//        subjectArrayList.add(new Subject("Cloud Computing", "6S406"));
//        subjectArrayList.add(new Subject("Computer Network", "6S405"));
//        subjectArrayList.add(new Subject("Advance Microprocessor", "6P401"));
//        subjectArrayList.add(new Subject("Seminar", "6P402"));
//        subjectArrayList.add(new Subject("Mobile Commputing", "6P403"));
//
//
//        adapter = new SubjectsAdapter(getContext(), subjectArrayList);
//        recyclerView.setAdapter(adapter);
//
//
//
//
////        subject.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                startActivity(intent);
////            }
////        });
//
//    }
//}



public class YourLoadFragment extends Fragment {

    private RecyclerView recyclerView;
    private SubjectsAdapter adapter;
    private ArrayList<Subject> subjectArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_your_load, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recyclerViewSubjects);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize data list
        subjectArrayList = new ArrayList<>();
        subjectArrayList.add(new Subject("Cloud Computing", "6S406"));
        subjectArrayList.add(new Subject("Computer Network", "6S405"));
        subjectArrayList.add(new Subject("Advance Microprocessor", "6P401"));
        subjectArrayList.add(new Subject("Seminar", "6P402"));
        subjectArrayList.add(new Subject("Mobile Computing", "6P403"));

        // Create and set adapter with the showPopup flag set to true
        adapter = new SubjectsAdapter(getContext(), subjectArrayList, true);  // Pass true to show popup
        recyclerView.setAdapter(adapter);
    }
}
