package com.shubhamdeshmukh.attendencemanagementsystem.frontend.teacher;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.TextView;

import com.shubhamdeshmukh.attendencemanagementsystem.R;
import com.shubhamdeshmukh.attendencemanagementsystem.frontend.MainActivity;

public class CategorySelectionActivity extends AppCompatActivity {

    CardView lecture ,practical;

    private static int currentSubjectIndex;

    private static Context currentContext;

    public static void callAttendanceViewActivityWithCategory(int categoryIndex, int classIndex, int batchIndex) {
        Intent intent = new Intent(currentContext, AttendanceViewActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("subjectIndex", currentSubjectIndex);
        intent.putExtra("categoryIndex", categoryIndex);
        intent.putExtra("classIndex", classIndex);
        intent.putExtra("batchIndex", batchIndex);
        currentContext.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_category_selection);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        CategorySelectionActivity.currentContext = getApplicationContext();
        Intent intent = getIntent();
        currentSubjectIndex = intent.getIntExtra("Subject", 0);

        RecyclerView categoryrecycler = findViewById(R.id.categoryrecycler);
        categoryrecycler.setLayoutManager(new LinearLayoutManager(this));

        CategorySelectionRecylerAdapter categorySelectionRecylerAdapter = new CategorySelectionRecylerAdapter(this,SubjectRecyclerAdapter.getSubjects_array_list().get(this.currentSubjectIndex).getCategoryList());

        categoryrecycler.setAdapter(categorySelectionRecylerAdapter);
        Log.d(MainActivity.TAG, "onCreate: SUBJECT: " + SubjectRecyclerAdapter.getSubjects_array_list().get(currentSubjectIndex).getCategoryList().toString());



    }

    public int getCurrentSubjectIndex() {
        return currentSubjectIndex;
    }

    public void showDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout._class, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        builder.setTitle("Select the Class");

        final AlertDialog dialog = builder.create();

        TextView item1 = dialogView.findViewById(R.id.item1);
        TextView item2 = dialogView.findViewById(R.id.item2);
        TextView item3 = dialogView.findViewById(R.id.item3);

       Intent intent = new Intent(this, AttendanceViewActivity.class);

        item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intent);
            }
        });

        item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

        item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

        dialog.show();
    }
}



