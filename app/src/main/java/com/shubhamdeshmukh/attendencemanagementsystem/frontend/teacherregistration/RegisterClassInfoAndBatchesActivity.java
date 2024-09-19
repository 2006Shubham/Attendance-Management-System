package com.shubhamdeshmukh.attendencemanagementsystem.frontend.teacherregistration;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.shubhamdeshmukh.attendencemanagementsystem.backend.database_entities.Batch;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.database_entities.Class;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.database_entities.Data;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.models.ClassSelection;
import com.shubhamdeshmukh.attendencemanagementsystem.frontend.MainActivity;

import java.util.ArrayList;

public class RegisterClassInfoAndBatchesActivity extends AppCompatActivity {

    ArrayList<Batch> batchArrayList;
    int selectedClassIndex;

    BatchRegisterRecyclerAdapter currentAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_class_info_and_batches);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FloatingActionButton addBatchFloatingButton = findViewById(R.id.addbatch);

        Intent intent = getIntent();
        selectedClassIndex = intent.getIntExtra("classFetchedDataIndex", -1);
        EditText className = findViewById(R.id.class_name);

        if (selectedClassIndex == -1) {

            Data registrationData = MainActivity.dbConnection.getRegistrationData();
            Class _class = new Class();
            RegisterAddClassesAndSubjectsActivity.classSelectionArrayList.add(new ClassSelection(_class, false));
            registrationData.classes.add(_class);
            selectedClassIndex = registrationData.classes.size() - 1;
            batchArrayList  = new ArrayList<>();

        }
        else
        {
            Class _class = MainActivity.dbConnection.getRegistrationData().classes.get(selectedClassIndex);
            className.setText(_class.getName());
            batchArrayList = _class.getBatchList();
        }

//        selectedBatches = new ArrayList<>(Collections.nCopies(batchArrayList.size(), false));
        RecyclerView recyclerView = findViewById(R.id.register_batch_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        currentAdapter = new BatchRegisterRecyclerAdapter(batchArrayList, this);
        recyclerView.setAdapter(currentAdapter);


        addBatchFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showBatchInfoDialog(-1);
            }
        });

        Button submitDataButton = findViewById(R.id.submitDataButton);
        submitDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Data registrationData = MainActivity.dbConnection.getRegistrationData();

                registrationData.classes.get(selectedClassIndex).setName(className.getText().toString());
                registrationData.classes.get(selectedClassIndex).setBatchList(batchArrayList);
                registrationData.batches.addAll(batchArrayList);
                RegisterAddClassesAndSubjectsActivity.classSelectionArrayList.set(selectedClassIndex, new ClassSelection(registrationData.classes.get(selectedClassIndex), true));
                MainActivity.dbConnection.setRegistrationData(registrationData);
                MainActivity.dbConnection.completeRegistration();
                FirebaseDBConnection.updateDatabase();
                Log.d(MainActivity.TAG, "onClick: COMPLETED CLASS REGISTRATION!");
                finish();
            }
        });
    }

    private void updateRecycler()
    {
        RecyclerView recyclerView = findViewById(R.id.register_batch_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new BatchRegisterRecyclerAdapter(batchArrayList, this));
    }

    public void showBatchInfoDialog(int selectedBatch) {
        // Create an AlertDialog Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Inflate the custom layout
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.layout_dailog_add_class_and_batch, null);




        // Set the custom layout as the dialog's view
        builder.setView(dialogView);

        // Get the EditText and Button references
        EditText editTextBatchName = dialogView.findViewById(R.id.editTextBatchName);
        EditText editTextStartingStudentNo = dialogView.findViewById(R.id.editTextStartingStudentNo);
        EditText editTextEndingStudentNo = dialogView.findViewById(R.id.editTextEndingStudentNo);
        Button buttonSubmit = dialogView.findViewById(R.id.buttonSubmit);

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();

        if (selectedBatch != -1)
        {
            editTextBatchName.setText(batchArrayList.get(selectedBatch).getName());
            editTextStartingStudentNo.setText(batchArrayList.get(selectedBatch).getStartId());
            editTextEndingStudentNo.setText(batchArrayList.get(selectedBatch).getEndId());
        }

        // Set an OnClickListener for the Submit button
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click event
                String batchName = editTextBatchName.getText().toString();
                String startingStudentNo = editTextStartingStudentNo.getText().toString();
                String endingStudentNo = editTextEndingStudentNo.getText().toString();

                // Update the batch & class info in registration Data in FirebaseDBConnection...
                Data registrationData = MainActivity.dbConnection.getRegistrationData();

                if (selectedClassIndex != -1)
                {
                    if (selectedBatch != -1)
                    {
                        // Both class & batch are selected
                        batchArrayList.get(selectedBatch).name = batchName;
                        batchArrayList.get(selectedBatch).startId = startingStudentNo;
                        batchArrayList.get(selectedBatch).endId = endingStudentNo;
                    }
                    else
                    {
                        // Only Class is selected
                        Batch newBatch = new Batch(batchName, startingStudentNo, endingStudentNo);
                        batchArrayList.add(newBatch);
                    }
                }
                else
                {
                    // None is selected
//                    Class newClass = new Class();
//                    newClass.addBatch(new Batch(batchName, startingStudentNo, endingStudentNo));
//                    registrationData.classes.add(newClass);
//                    selectedClassIndex = registrationData.classes.size() - 1;
                }
                updateRecycler();
                // Perform action with the input data
                // For example, you can validate inputs or submit them to a server

                // Dismiss the dialog after processing
                dialog.dismiss();
            }
        });
    }



}