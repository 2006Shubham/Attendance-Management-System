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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.shubhamdeshmukh.attendencemanagementsystem.R;

public class RegisterClassInfoAndBatchesActivity extends AppCompatActivity {

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



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showBatchInfoDialog();
            }
        });
    }


    private void showBatchInfoDialog() {
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

        // Set an OnClickListener for the Submit button
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click event
                String batchName = editTextBatchName.getText().toString();
                String startingStudentNo = editTextStartingStudentNo.getText().toString();
                String endingStudentNo = editTextEndingStudentNo.getText().toString();
                EditText classname = findViewById(R.id.class_name);

                Intent intent = new Intent(RegisterClassInfoAndBatchesActivity.this, RegisterAddClassesAndSubjectsActivity.class);

                startActivity(intent);
                finish();


                // Perform action with the input data
                // For example, you can validate inputs or submit them to a server

                // Dismiss the dialog after processing
                dialog.dismiss();
            }
        });
    }



}