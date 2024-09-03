package com.shubhamdeshmukh.attendencemanagementsystem.frontend.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.ValueCallback;
import android.widget.CalendarView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shubhamdeshmukh.attendencemanagementsystem.R;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.FirebaseDBConnection;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.database_entities.Attendance;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.database_entities.Class;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.database_entities.StudentStatus;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.database_entities.Teacher;
import com.shubhamdeshmukh.attendencemanagementsystem.frontend.MainActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class AttendanceViewActivity extends AppCompatActivity {

//    Previous Activity should share Account (Teacher), Subject, Category, Batch (If Any), Class

    Class _class;
    ArrayList<StudentStatus> studentStatusList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        this.setContentView(R.layout.activity_attendance_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        Intent intent = getIntent();
        int subjectIndex = intent.getIntExtra("subjectIndex", -1);
        int categoryIndex = intent.getIntExtra("categoryIndex", -1);
        int classIndex = intent.getIntExtra("classIndex", -1);
        int batchIndex = intent.getIntExtra("batchIndex", -1);

        CalendarView calendarView = findViewById(R.id.teacher_attendance_calendar_view);

        Log.d(MainActivity.TAG, "onCreate: INDEXES" + subjectIndex + " " + categoryIndex + " " + classIndex + " " + batchIndex);


        FirebaseDBConnection dbConnection = new FirebaseDBConnection(MainActivity.database, MainActivity.mAuth);

        dbConnection.getAccount(new ValueCallback<Object>() {

            @Override
            public void onReceiveValue(Object obj) {
                if (obj != null)
                {
                    Teacher teacher = (Teacher) obj;
                    _class = teacher.getSubjects().get(subjectIndex).getCategoryList().get(categoryIndex).getClassList().get(classIndex);

                    Log.d(MainActivity.TAG, "onReceiveValue AttendanceActivity: Valid Ref");

                }
                else {
                    Log.d(MainActivity.TAG, "onReceiveValue AttendanceActivity: NULL Ref");
                }
            }
        });

        RecyclerView recyclerView = findViewById(R.id.studentrecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                Calendar calendarDate = Calendar.getInstance();
                calendarDate.set(Calendar.YEAR, year);
                calendarDate.set(Calendar.MONTH, month);
                calendarDate.set(Calendar.DAY_OF_MONTH, day);

                Log.d(MainActivity.TAG, "onSelectedDayChange: Date: " + calendarDate.getTime());

                if (_class != null)
                {
                    Attendance attendance = _class.getAttendance(calendarDate);

                    if (attendance != null)
                    {
                        Log.d(MainActivity.TAG, "onSelectedDayChange: ATTENDANCE PRESENT");
                        if (batchIndex == -1)
                        {
                            studentStatusList = attendance.getStudentStatusList();
                        }
                        else
                        {
                            int batchStartIdIndex = attendance.getStudentIndexWithId(_class.getBatchList().get(batchIndex).getStartId());
                            int batchEndIdIndex = attendance.getStudentIndexWithId(_class.getBatchList().get(batchIndex).getEndId());
                            studentStatusList = new ArrayList<StudentStatus>(attendance.getStudentStatusList().subList(batchStartIdIndex, batchEndIdIndex));
                        }
                    }
                    else {
                        studentStatusList = new ArrayList<>();
                        Log.d(MainActivity.TAG, "onSelectedDayChange: ATTENDANCE NOT PRESENT");
                    }

                    AttendanceRecyclerAdapter adapter = new AttendanceRecyclerAdapter(studentStatusList, getBaseContext());
                    recyclerView.setAdapter(adapter);
                }
            }
        });
    }
}