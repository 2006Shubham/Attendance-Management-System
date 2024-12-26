package com.shubhamdeshmukh.newattendancemanagement.flow3fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shubhamdeshmukh.newattendancemanagement.R;
import com.shubhamdeshmukh.newattendancemanagement.flow3activity.SchedulePreviewActivity;


public class ScheduleFragment extends Fragment {


    View card;
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
        
        card = view.findViewById(R.id.cat1);
        preViewButton = view.findViewById(R.id.previewButton);
        intent = new Intent(getActivity(), SchedulePreviewActivity.class);

        preViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
        
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUp();
            }
        });
        
    }

    private void showPopUp() {


            // Pass the context to the AlertDialog.Builder
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            LayoutInflater inflater = getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.schedule_class_popup, null);

            // Set custom view to dialog
            builder.setView(dialogView);

            // Add actions if needed
            Button closeButton = dialogView.findViewById(R.id.doneButton);

            AlertDialog dialog = builder.create();
            closeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }



}