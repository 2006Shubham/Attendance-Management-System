package com.shubhamdeshmukh.attendencemanagementsystem.frontend.teacherregistration;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.shubhamdeshmukh.attendencemanagementsystem.R;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.FirebaseDBConnection;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.database_entities.Category;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.database_entities.Class;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.database_entities.Subject;
import com.shubhamdeshmukh.attendencemanagementsystem.frontend.MainActivity;

import java.util.ArrayList;

public class RegisterSubjectInfoAndCategoryActivity extends AppCompatActivity {



    ArrayList<Category> categoryArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_subject_and_category);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        FloatingActionButton fab = findViewById(R.id.addcategory_and_select_class);
        Intent intent = getIntent();
        FirebaseDBConnection firebaseDBConnection = new FirebaseDBConnection(MainActivity.database,MainActivity.mAuth);
        int subjectIndex = intent.getIntExtra("subjectFetchedDataIndex", -1);

        if (subjectIndex == -1) {

            categoryArrayList = firebaseDBConnection.getFetchedData().categories;
        }
        else
        {
            Subject subject = firebaseDBConnection.getFetchedData().subjects.get(subjectIndex);
            EditText subjectName = findViewById(R.id.subject_name);
            EditText subjectCode = findViewById(R.id.subject_code);
            subjectName.setText(subject.getName());
            subjectCode.setText(subject.getCode());
            categoryArrayList = subject.getCategoryList();
        }
        RecyclerView recyclerView = findViewById(R.id.category_recyle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CategoryRegisterRecyclerAdapter(this, categoryArrayList, this));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    showCategoryInfoDialog(-1);

            }
        });

    }



    public void showCategoryInfoDialog(int selectedCategory) {
        // Create an AlertDialog Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Inflate the custom layout
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.layout_dialog_add_category, null);



        RecyclerView recyclerView = dialogView.findViewById(R.id.class_and_batch);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        FirebaseDBConnection dbConnection = new FirebaseDBConnection(MainActivity.database, MainActivity.mAuth);
        FirebaseDBConnection.fetchData();
        ArrayList<Class> classList = dbConnection.getFetchedData().classes;


        // Set the custom layout as the dialog's view
        builder.setView(dialogView);

        // Get the EditText and Button references
        EditText category_name = dialogView.findViewById(R.id.category_name);

        Button buttonSubmit = dialogView.findViewById(R.id.submit_category);

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();

        if (selectedCategory != -1)
        {
            category_name.setText(categoryArrayList.get(selectedCategory).getName());
            classList = categoryArrayList.get(selectedCategory).getClassList();
        }

        ClassSelectionRecyclerAdapter classSelectionRecyclerAdapter = new ClassSelectionRecyclerAdapter(getApplicationContext(), classList);
        recyclerView.setAdapter(classSelectionRecyclerAdapter);

        // Set an OnClickListener for the Submit button
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click event

                EditText category = findViewById(R.id.category_name);

//                Intent intent = new Intent(getApplicationContext(), RegisterAddClassesAndSubjectsActivity.class);
//
//                startActivity(intent);
//                finish();

                // Perform action with the input data
                // For example, you can validate inputs or submit them to a server

                // Dismiss the dialog after processing
                dialog.dismiss();
            }
        });
    }


}