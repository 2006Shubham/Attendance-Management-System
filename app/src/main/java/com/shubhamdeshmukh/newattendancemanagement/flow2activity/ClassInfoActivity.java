package com.shubhamdeshmukh.newattendancemanagement.flow2activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.shubhamdeshmukh.newattendancemanagement.R;
import com.shubhamdeshmukh.newattendancemanagement.adaptermodelclass.Batch;
import com.shubhamdeshmukh.newattendancemanagement.recycleradapters.BatchAdapter;

import java.util.ArrayList;

public class ClassInfoActivity extends AppCompatActivity {

    ImageView backarrow;
    Button button ;

    RecyclerView recyclerView;
    BatchAdapter batchAdapter;
    ArrayList<Batch>batchArrayList;

    FloatingActionButton floatingActionButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_class_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

       // checkBox.setVisibility(View.GONE);

        backarrow = findViewById(R.id.back);
        button = findViewById(R.id.doneButton);


        recyclerView = findViewById(R.id.batchRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        batchArrayList = new ArrayList<>();

        batchArrayList.add(new Batch("A"));
        batchArrayList.add(new Batch("B"));
        batchArrayList.add(new Batch("C"));
        batchArrayList.add(new Batch("D"));
        batchArrayList.add(new Batch("E"));


        batchAdapter = new BatchAdapter(batchArrayList,this,false, true);
        recyclerView.setAdapter(batchAdapter);


        floatingActionButton = findViewById(R.id.addBatch);
        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUp();
            }
        });
    }

    private void showPopUp() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.batch_info_popup, null);

        // Set custom view to dialog
        builder.setView(dialogView);

        // Add actions if needed
        Button closeButton = dialogView.findViewById(R.id.submitButton);

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