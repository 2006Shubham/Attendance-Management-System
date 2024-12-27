//package com.shubhamdeshmukh.newattendancemanagement.flow4fragments;
//
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//
//import com.shubhamdeshmukh.newattendancemanagement.R;
//import com.shubhamdeshmukh.newattendancemanagement.adaptermodelclass.AttendanceSubject;
//import com.shubhamdeshmukh.newattendancemanagement.recycleradapters.AttendanceSubjectAdapter;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class TakeAttendanceFragment extends Fragment {
//
//    private RecyclerView recyclerView;
//    private ImageView leftArrow, rightArrow;
//    private LinearLayoutManager layoutManager;
//    private int currentPosition = 0;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_take_attendance, container, false);
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        // Initialize RecyclerView and navigation arrows
//        recyclerView = view.findViewById(R.id.recyclerView);
//        leftArrow = view.findViewById(R.id.leftArrow);
//        rightArrow = view.findViewById(R.id.rightArrow);
//
//        // Set up RecyclerView
//        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
//        recyclerView.setLayoutManager(layoutManager);
//
//        // Populate RecyclerView with data
//        List<AttendanceSubject> subjectList = new ArrayList<>();
//        subjectList.add(new AttendanceSubject("Computing", R.drawable.profile));
//        subjectList.add(new AttendanceSubject("Data Structures", R.drawable.profile));
//        subjectList.add(new AttendanceSubject("Systems", R.drawable.profile));
//        subjectList.add(new AttendanceSubject("Algorithms", R.drawable.profile));
//        subjectList.add(new AttendanceSubject("Intelligence", R.drawable.profile));
//
//        AttendanceSubjectAdapter adapter = new AttendanceSubjectAdapter(getContext(), subjectList);
//        recyclerView.setAdapter(adapter);
//
//        // Set up arrow click listeners
//        setupArrowNavigation(subjectList.size());
//    }
//
//    private void setupArrowNavigation(int itemCount) {
//        // Scroll back
//        leftArrow.setOnClickListener(v -> {
//            if (currentPosition > 0) {
//                currentPosition--;
//                recyclerView.smoothScrollToPosition(currentPosition);
//            }
//        });
//
//        // Scroll forward
//        rightArrow.setOnClickListener(v -> {
//            if (currentPosition < itemCount - 1) {
//                currentPosition++;
//                recyclerView.smoothScrollToPosition(currentPosition);
//            }
//        });
//    }
//}


package com.shubhamdeshmukh.newattendancemanagement.flow4fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.shubhamdeshmukh.newattendancemanagement.R;
import com.shubhamdeshmukh.newattendancemanagement.adaptermodelclass.AttendanceSubject;
import com.shubhamdeshmukh.newattendancemanagement.adaptermodelclass.Batch;
import com.shubhamdeshmukh.newattendancemanagement.recycleradapters.AttendanceSubjectAdapter;

import java.util.ArrayList;
import java.util.List;

public class TakeAttendanceFragment extends Fragment {

    private RecyclerView recyclerView;
    private ImageView leftArrow, rightArrow;
    private LinearLayoutManager layoutManager;
    private int currentPosition = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_take_attendance, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        leftArrow = view.findViewById(R.id.leftArrow);
        rightArrow = view.findViewById(R.id.rightArrow);

        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<Batch> batchArrayList = new ArrayList<>();

        batchArrayList.add(new Batch("A"));
        batchArrayList.add(new Batch("B"));
        batchArrayList.add(new Batch("C"));
        batchArrayList.add(new Batch("D"));
        batchArrayList.add(new Batch("E"));

        ArrayList<AttendanceSubject> subjectList = new ArrayList<>();
        subjectList.add(new AttendanceSubject("Computing", R.drawable.profile,batchArrayList));
        subjectList.add(new AttendanceSubject("Data Structures", R.drawable.profile,batchArrayList));
        subjectList.add(new AttendanceSubject("Systems", R.drawable.profile,batchArrayList));
        subjectList.add(new AttendanceSubject("Algorithms", R.drawable.profile,batchArrayList));
        subjectList.add(new AttendanceSubject("Intelligence", R.drawable.profile,batchArrayList));

        AttendanceSubjectAdapter adapter = new AttendanceSubjectAdapter(getContext(), subjectList);
        recyclerView.setAdapter(adapter);

        setupArrowNavigation(subjectList.size());
    }

    private void setupArrowNavigation(int itemCount) {
        leftArrow.setOnClickListener(v -> {
            if (currentPosition > 0) {
                currentPosition--;
                recyclerView.smoothScrollToPosition(currentPosition);
            }
        });

        rightArrow.setOnClickListener(v -> {
            if (currentPosition < itemCount - 1) {
                currentPosition++;
                recyclerView.smoothScrollToPosition(currentPosition);
            }
        });
    }
}
