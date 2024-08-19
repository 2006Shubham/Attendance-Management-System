package com.shubhamdeshmukh.attendencemanagementsystem.frontend.teacherregistration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.shubhamdeshmukh.attendencemanagementsystem.R;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.FirebaseDBConnection;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.Subject;
import com.shubhamdeshmukh.attendencemanagementsystem.frontend.MainActivity;

import java.util.ArrayList;

public class TeacherAfterRegistrationActivity extends AppCompatActivity {

    ArrayList<Subject> subjectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_teacher_after_registration);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button addSubjects = findViewById(R.id.add_subjects);

        FirebaseDBConnection dbConnection = new FirebaseDBConnection(MainActivity.database, MainActivity.mAuth);

        subjectList = dbConnection.getFetchedData().subjects;

//        dbConnection.getAccount(new ValueCallback<Object>() {
//
//            @Override
//            public void onReceiveValue(Object obj) {
//                if (obj != null)
//                {
//                    Teacher teacher = (Teacher) obj;
//                    Log.d(MainActivity.TAG, "onReceiveValue: Valid Ref");
//
//                }
//                else {
//                    Log.d(MainActivity.TAG, "onReceiveValue: NULL Ref");
//                }
//            }
//        });

        addSubjects.setOnClickListener(new View.OnClickListener() {

            Intent intent = new Intent(getApplicationContext(), RegisterAddClassesAndSubjectsActivity.class);

            @Override
            public void onClick(View view) {

                startActivity(intent);

            }
        });




    }
}