package com.shubhamdeshmukh.newattendancemanagement.flow3fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.shubhamdeshmukh.newattendancemanagement.R;
import com.shubhamdeshmukh.newattendancemanagement.adaptermodelclass.Batch;
import com.shubhamdeshmukh.newattendancemanagement.adaptermodelclass.Category;
import com.shubhamdeshmukh.newattendancemanagement.flow3activity.SchedulePreviewActivity;
import com.shubhamdeshmukh.newattendancemanagement.recycleradapters.BatchAdapter;
import com.shubhamdeshmukh.newattendancemanagement.recycleradapters.CategoryAdapter;

import java.util.ArrayList;

public class ScheduleFragment extends Fragment {

    RecyclerView recyclerView;
    CategoryAdapter categoryAdapter;
    ArrayList<Category> categoryArrayList;
    Button preViewButton;
    Intent intent;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        preViewButton = view.findViewById(R.id.previewButton);
        intent = new Intent(getActivity(), SchedulePreviewActivity.class);

        recyclerView = view.findViewById(R.id.categoryRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize the category list
        categoryArrayList = new ArrayList<>();
        categoryArrayList.add(new Category("Practical"));
        categoryArrayList.add(new Category("Theory"));
        categoryArrayList.add(new Category("Seminar"));
        categoryArrayList.add(new Category("Workshop"));

        // Set up the adapter with a click listener
        categoryAdapter = new CategoryAdapter(categoryArrayList, getContext(), new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                String categoryName = categoryArrayList.get(position).getCategoryName();
                Toast.makeText(getContext(), "Clicked: " + categoryName, Toast.LENGTH_SHORT).show();
                showPopUp();
            }
        });
        recyclerView.setAdapter(categoryAdapter);

        // Set up the preview button
        preViewButton.setOnClickListener(view1 -> startActivity(intent));
    }

//    private void showPopUp() {
//        // Create and display a custom popup dialog
//        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
//        LayoutInflater inflater = getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.schedule_class_popup, null);
//
//        // Set the custom view to the dialog
//        builder.setView(dialogView);
//
//        // Add actions if needed
//        Button closeButton = dialogView.findViewById(R.id.doneButton);
//
//        AlertDialog dialog = builder.create();
//        closeButton.setOnClickListener(view -> dialog.dismiss());
//        dialog.show();
//    }

    private void showPopUp() {
        // Create and display a custom popup dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.schedule_class_popup, null);

        // Initialize RecyclerView
        RecyclerView batchRecycler = dialogView.findViewById(R.id.batchRecycler);
        batchRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        // Set up the adapter (replace 'YourBatchAdapter' with your actual adapter class)
        ArrayList<Batch> batchList = new ArrayList<>();
        batchList.add(new Batch("A"));
        batchList.add(new Batch("B"));
        batchList.add(new Batch("C"));
        batchList.add(new Batch("D"));



        BatchAdapter batchAdapter = new BatchAdapter(batchList, getContext(), true, true); // Replace with your adapter instance
        batchRecycler.setAdapter(batchAdapter);

        // Set up the "Done" button
        Button closeButton = dialogView.findViewById(R.id.doneButton);
        AlertDialog dialog = builder.setView(dialogView).create();

        closeButton.setOnClickListener(view -> dialog.dismiss());
        dialog.show();
    }

}
