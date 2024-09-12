package com.shubhamdeshmukh.attendencemanagementsystem.frontend.teacher;

import android.os.Bundle;
import android.util.Log;
import android.webkit.ValueCallback;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shubhamdeshmukh.attendencemanagementsystem.R;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.FirebaseDBConnection;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.database_entities.Subject;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.database_entities.Teacher;
import com.shubhamdeshmukh.attendencemanagementsystem.frontend.MainActivity;

import java.util.ArrayList;

public class TeacherSubjectPortalActivity extends AppCompatActivity {


    ArrayList<Subject> sub_array_list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_teacher_subject_portal);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView recyclerView = findViewById(R.id.sub_recycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);

        MainActivity.dbConnection.getAccount(new ValueCallback<Object>() {

            @Override
            public void onReceiveValue(Object obj) {
                if (obj != null)
                {
                    Teacher teacher = (Teacher) obj;
                    sub_array_list = teacher.getSubjects();
                    SubjectRecyclerAdapter subjectRecyclerAdapter = new SubjectRecyclerAdapter(getApplicationContext(),sub_array_list);
                    recyclerView.setAdapter(subjectRecyclerAdapter);
                    Log.d(MainActivity.TAG, "onReceiveValue: Valid Ref");

                }
                else {
                    Log.d(MainActivity.TAG, "onReceiveValue: NULL Ref");
                }
            }
        });
    }
}