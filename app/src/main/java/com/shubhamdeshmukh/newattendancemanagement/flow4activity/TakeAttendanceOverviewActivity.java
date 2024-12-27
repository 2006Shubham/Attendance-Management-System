package com.shubhamdeshmukh.newattendancemanagement.flow4activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.shubhamdeshmukh.newattendancemanagement.R;
import com.shubhamdeshmukh.newattendancemanagement.adaptermodelclass.Batch;
import com.shubhamdeshmukh.newattendancemanagement.recycleradapters.BatchAdapter;

import java.util.ArrayList;

public class    TakeAttendanceOverviewActivity extends AppCompatActivity {


    private TextView selectedDate;
    private Button button;

    Intent intent ;

    RecyclerView recyclerView;
    BatchAdapter batchAdapter;

    ArrayList<Batch> batchArrayList;



    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_take_attendance_overview);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        selectedDate = findViewById(R.id.selected_date);
        button = findViewById(R.id.pick_date_button);
        imageView = findViewById(R.id.back);
        intent = new Intent(this, StudentListForAttendanceActivity.class);

        button.setOnClickListener(view -> showDatePicker());
        imageView.setOnClickListener(view -> onBackPressed());

        button = findViewById(R.id.okBtn);
        button.setOnClickListener(view -> startActivity(intent));

        recyclerView = findViewById(R.id.reviewBatchRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        batchArrayList = new ArrayList<>();

        batchArrayList.add(new Batch("A"));
        batchArrayList.add(new Batch("B"));
        batchArrayList.add(new Batch("C"));
        batchArrayList.add(new Batch("D"));
        batchArrayList.add(new Batch("E"));


        batchAdapter = new BatchAdapter(batchArrayList, this,true,false);
        recyclerView.setAdapter(batchAdapter);



    }





    private void showDatePicker() {
        // Create constraints to allow only future dates
        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder()
                .setValidator(DateValidatorPointForward.now());

        // Build the MaterialDatePicker
        MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Date")
                .setCalendarConstraints(constraintsBuilder.build())
                .build();

        // Show the picker
        datePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");

        // Set listener to get the selected date
        datePicker.addOnPositiveButtonClickListener(selection -> {
            // Convert the selected date to a readable format
            String formattedDate = new java.text.SimpleDateFormat("MMM dd, yyyy", java.util.Locale.getDefault())
                    .format(new java.util.Date(selection));
            selectedDate.setText(formattedDate);
        });
    }

}