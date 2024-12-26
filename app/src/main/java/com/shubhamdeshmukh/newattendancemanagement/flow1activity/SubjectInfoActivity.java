package com.shubhamdeshmukh.newattendancemanagement.flow1activity;

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
import com.shubhamdeshmukh.newattendancemanagement.adaptermodelclass.Category;
import com.shubhamdeshmukh.newattendancemanagement.recycleradapters.CategoryAdapter;

import java.util.ArrayList;

public class SubjectInfoActivity extends AppCompatActivity {

    ImageView backarrow;

    RecyclerView recyclerView;

    CategoryAdapter categoryAdapter;

    ArrayList<Category> categoryArrayList;
    FloatingActionButton floatingActionButton;
    Button doneButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_subject_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        backarrow = findViewById(R.id.back);
        floatingActionButton = findViewById(R.id.addCategory);
        doneButton = findViewById(R.id.doneButton);

        recyclerView = findViewById(R.id.recyclerViewCategory);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        categoryArrayList = new ArrayList<>(); ;
        categoryArrayList.add(new Category("Practical"));
        categoryArrayList.add(new Category("Theory"));
        categoryArrayList.add(new Category("Seminar"));
        categoryArrayList.add(new Category("Seminar"));

        categoryArrayList.add(new Category("Practical"));
        categoryArrayList.add(new Category("Theory"));
        categoryArrayList.add(new Category("Seminar"));
        categoryArrayList.add(new Category("Seminar"));

        categoryAdapter = new CategoryAdapter(categoryArrayList,this,null);
        recyclerView.setAdapter(categoryAdapter);

        
        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup();
            }
        });
        
        
    }

    private void showPopup() {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.category_info_popup, null);

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
