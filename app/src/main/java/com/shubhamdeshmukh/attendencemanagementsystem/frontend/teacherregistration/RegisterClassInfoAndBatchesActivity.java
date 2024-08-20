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
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.Batch;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.Class;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.Data;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.models.BatchSelection;
import com.shubhamdeshmukh.attendencemanagementsystem.frontend.MainActivity;

import java.util.ArrayList;

public class RegisterClassInfoAndBatchesActivity extends AppCompatActivity {

    ArrayList<BatchSelection> batchSelectionArrayList;
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

        FloatingActionButton fab = findViewById(R.id.addbatch);

        Intent intent = getIntent();
        FirebaseDBConnection firebaseDBConnection = new FirebaseDBConnection(MainActivity.database,MainActivity.mAuth);
        selectedClassIndex = intent.getIntExtra("classFetchedDataIndex", -1);
        ArrayList<Batch> batchArrayList;

        if (selectedClassIndex == -1) {

            batchArrayList  = firebaseDBConnection.getRegistrationData().batches;

        }
        else
        {
            Class _class = firebaseDBConnection.getRegistrationData().classes.get(selectedClassIndex);
            EditText className = findViewById(R.id.class_name);
            className.setText(_class.getName());
            batchArrayList = _class.getBatchList();
        }

//        selectedBatches = new ArrayList<>(Collections.nCopies(batchArrayList.size(), false));
        RecyclerView recyclerView = findViewById(R.id.register_batch_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        this.batchSelectionArrayList = new ArrayList<>();
        for (Batch batch:
                batchArrayList) {
            this.batchSelectionArrayList.add(new BatchSelection(batch));
        }

        currentAdapter = new BatchRegisterRecyclerAdapter(getApplicationContext(),batchSelectionArrayList, this);
        recyclerView.setAdapter(currentAdapter);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showBatchInfoDialog(-1);
            }
        });

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Data registrationData = firebaseDBConnection.getRegistrationData();
                ArrayList<Batch> newBatchList = new ArrayList<>();
                ArrayList<Batch> registeredBatchList = new ArrayList<>();

                for (BatchSelection batchSelection:
                     batchSelectionArrayList) {
                    if (batchSelection.isSelected())
                    {
                        newBatchList.add(batchSelection.getBatch());
                    }
                    registeredBatchList.add((batchSelection.getBatch()));
                }
                registrationData.classes.get(selectedClassIndex).setBatchList(newBatchList);
                registrationData.batches = registeredBatchList;
                firebaseDBConnection.setRegistrationData(registrationData);
                firebaseDBConnection.completeRegistration();
                FirebaseDBConnection.updateDatabase();
                Log.d(MainActivity.TAG, "onClick: COMPLETED REGISTRATION!");
            }
        });
    }

    private void updateRecycler()
    {
        RecyclerView recyclerView = findViewById(R.id.register_batch_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new BatchRegisterRecyclerAdapter(getApplicationContext(), batchSelectionArrayList, this));
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
            editTextBatchName.setText(batchSelectionArrayList.get(selectedBatch).getBatch().getName());
            editTextStartingStudentNo.setText(batchSelectionArrayList.get(selectedBatch).getBatch().getStartId());
            editTextEndingStudentNo.setText(batchSelectionArrayList.get(selectedBatch).getBatch().getEndId());
        }

        // Set an OnClickListener for the Submit button
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click event
                String batchName = editTextBatchName.getText().toString();
                String startingStudentNo = editTextStartingStudentNo.getText().toString();
                String endingStudentNo = editTextEndingStudentNo.getText().toString();

                FirebaseDBConnection firebaseDBConnection = new FirebaseDBConnection(MainActivity.database,MainActivity.mAuth);

                // Update the batch & class info in registration Data in FirebaseDBConnection...
                Data registrationData = firebaseDBConnection.getRegistrationData();

                if (selectedClassIndex != -1)
                {
                    if (selectedBatch != -1)
                    {
                        // Both class & batch are selected
                        registrationData.classes.get(selectedClassIndex).getBatchList().get(selectedBatch).name = batchName;
                        registrationData.classes.get(selectedClassIndex).getBatchList().get(selectedBatch).startId = startingStudentNo;
                        registrationData.classes.get(selectedClassIndex).getBatchList().get(selectedBatch).endId = endingStudentNo;
                    }
                    else
                    {
                        // Only Class is selected
                        Batch newBatch = new Batch(batchName, startingStudentNo, endingStudentNo);
                        registrationData.classes.get(selectedClassIndex).addBatch(newBatch);
                    }
                }
                else
                {
                    // None is selected
                    Class newClass = new Class();
                    newClass.addBatch(new Batch(batchName, startingStudentNo, endingStudentNo));
                    registrationData.classes.add(newClass);
                    selectedClassIndex = registrationData.classes.size() - 1;
                }
                firebaseDBConnection.setRegistrationData(registrationData);
                ArrayList<Batch> batchArrayList = registrationData.classes.get(selectedClassIndex).getBatchList();
                batchSelectionArrayList = new ArrayList<>();
                for (Batch batch:
                        batchArrayList) {
                    batchSelectionArrayList.add(new BatchSelection(batch));
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