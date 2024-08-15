package com.shubhamdeshmukh.attendencemanagementsystem.frontend.teacherregistration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.shubhamdeshmukh.attendencemanagementsystem.R;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.Class;

import java.util.ArrayList;

public class RegisterAddClassesAndSubjectsActivity extends AppCompatActivity {


    public static ArrayList<Class> classArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_classes_and_subjects);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FloatingActionButton  fab1 = findViewById(R.id.add);
        FloatingActionButton fab2 = findViewById(R.id.addSubject);
        FloatingActionButton fab3 = findViewById(R.id.addclass);

        RecyclerView recyclerView = findViewById(R.id.register_class_recycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        classArrayList.add(new Class("CO 3rd Year"));
        classArrayList.add(new Class("An 3rd Year"));
        classArrayList.add(new Class("MECH 3rd Year"));
        classArrayList.add(new Class("E&TC 3rd Year"));

        ClassRegisterRecyclerAdapter classRegisterRecyclerAdapter = new ClassRegisterRecyclerAdapter(this,classArrayList);
        recyclerView.setAdapter(classRegisterRecyclerAdapter);






       fab1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               fab2.setVisibility(View.VISIBLE);
               fab3.setVisibility(View.VISIBLE);
           }
       });

       fab2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(getApplicationContext(), RegisterSubjectInfoAndCategoryActivity.class);
               startActivity(intent);
           }
       });

        fab3.setOnClickListener(new View.OnClickListener() {

            Intent intent = new Intent(getApplicationContext(), RegisterClassInfoAndBatchesActivity.class);
            @Override
            public void onClick(View view) {
                    startActivity(intent);
            }
        });
    }
}