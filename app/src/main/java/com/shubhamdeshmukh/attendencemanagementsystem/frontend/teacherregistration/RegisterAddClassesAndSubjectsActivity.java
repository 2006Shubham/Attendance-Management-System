package com.shubhamdeshmukh.attendencemanagementsystem.frontend.teacherregistration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.shubhamdeshmukh.attendencemanagementsystem.R;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.database_entities.Class;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.database_entities.Subject;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.models.ClassSelection;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.models.SubjectSelection;
import com.shubhamdeshmukh.attendencemanagementsystem.frontend.MainActivity;
import com.shubhamdeshmukh.attendencemanagementsystem.frontend.adapters.selection.list.ClassRecyclerAdapter;
import com.shubhamdeshmukh.attendencemanagementsystem.frontend.adapters.selection.single.SubjectSelectionRecyclerAdapter;

import java.util.ArrayList;

public class RegisterAddClassesAndSubjectsActivity extends AppCompatActivity {


    public static ArrayList<ClassSelection> classSelectionArrayList;
    public static ArrayList<SubjectSelection> subjectSelectionArrayList;

    private SubjectSelectionRecyclerAdapter subjectSelectionRecyclerAdapter;
    private ClassRecyclerAdapter classRecyclerAdapter;

    private boolean toggle = false;

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

        FloatingActionButton addSubjectOrClassParentButtonCard = findViewById(R.id.add);
        CardView addSubjectButtonCard = findViewById(R.id.addSubject);
        CardView addClassButtonCard = findViewById(R.id.add_class);
        Button submitButton = findViewById(R.id.submit);

        RecyclerView classRecyclerView = findViewById(R.id.register_class_recycler);
        RecyclerView subjectRecyclerView = findViewById(R.id.register_subject_recycler);

        classRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        subjectRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Class> classList = MainActivity.dbConnection.getFetchedData().classes;
        ArrayList<Subject> subjectList = MainActivity.dbConnection.getFetchedData().subjects;

        classSelectionArrayList = new ArrayList<>();
        subjectSelectionArrayList = new ArrayList<>();

        for (Class _class:
            classList) {
            classSelectionArrayList.add(new ClassSelection(_class, false));
        }
        for (Subject subject:
             subjectList) {
            subjectSelectionArrayList.add(new SubjectSelection(subject, false));
        }

        classRecyclerAdapter = new ClassRecyclerAdapter(this, classSelectionArrayList);
        classRecyclerView.setAdapter(classRecyclerAdapter);
        SubjectSelectionRecyclerAdapter subjectSelectionAdapter = new SubjectSelectionRecyclerAdapter(this, subjectSelectionArrayList);
        subjectRecyclerView.setAdapter(subjectSelectionAdapter);


        addSubjectOrClassParentButtonCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (toggle)
                {
                    addSubjectButtonCard.setVisibility(View.GONE);
                    addClassButtonCard.setVisibility(View.GONE);
                }
                else {
                    addSubjectButtonCard.setVisibility(View.VISIBLE);
                    addClassButtonCard.setVisibility(View.VISIBLE);
                }
                toggle = !toggle;
            }
        });

        addSubjectButtonCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterSubjectInfoAndCategoryActivity.class);
                startActivity(intent);
            }
        });

        addClassButtonCard.setOnClickListener(new View.OnClickListener() {

            Intent intent = new Intent(getApplicationContext(), RegisterClassInfoAndBatchesActivity.class);
            @Override
            public void onClick(View view) {
                    startActivity(intent);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void updateRecyclers()
    {
        RecyclerView classRecyclerView = findViewById(R.id.register_class_recycler);
        RecyclerView subjectRecyclerView = findViewById(R.id.register_subject_recycler);

        classRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        subjectRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        classRecyclerAdapter = new ClassRecyclerAdapter(this, classSelectionArrayList);
        classRecyclerView.setAdapter(classRecyclerAdapter);

        subjectSelectionRecyclerAdapter = new SubjectSelectionRecyclerAdapter(this, subjectSelectionArrayList);
        subjectRecyclerView.setAdapter(subjectSelectionRecyclerAdapter);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        updateRecyclers();
    }
}