package com.shubhamdeshmukh.attendencemanagementsystem.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shubhamdeshmukh.attendencemanagementsystem.R;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.BackendTestingCode;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.FirebaseDBConnection;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.FirebaseAuthentication;
import com.shubhamdeshmukh.attendencemanagementsystem.frontend.login.LoginActivity;
import com.shubhamdeshmukh.attendencemanagementsystem.frontend.teacher.TeacherSubjectPortalActivity;
import com.shubhamdeshmukh.attendencemanagementsystem.frontend.teacherregistration.TeacherAfterRegistrationActivity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainActivity extends AppCompatActivity {

    public static final String  TAG = "YashGames2007";
    public static FirebaseDBConnection dbConnection;
    public static FirebaseAuthentication authHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Write Code Here
        Log.d(TAG, "In Main 1");
        dbConnection = new FirebaseDBConnection(getString(R.string.FIREBASE_DB_ACCESS_LINK));


        TextView idText = findViewById(R.id.loginID);
        Button logoutButton = findViewById(R.id.buttonLogout);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fireLoginActivity();
            }
        });

        Log.d(MainActivity.TAG, "In Main");
        authHandler = new FirebaseAuthentication();

        if (authHandler.authenticate()) {
            idText.setText(authHandler.getCurrentUser().getEmail());
        }
        else {
            fireLoginActivity();
        }

        FirebaseDBConnection.fetchData();
        manageInput();
//        dbConnection.trialCode();
    }

    private void manageInput() {
        EditText inputField = findViewById(R.id.data);
        Button sendButton = findViewById(R.id.sendData);

        Intent intent = new Intent(this, TeacherAfterRegistrationActivity.class);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String data = inputField.getText().toString();
                BackendTestingCode.testFirebase(data, FirebaseDBConnection.getDatabase());
                inputField.setText("");

                startActivity(intent);
                finish();


            }
        });
    }

    private void fireLoginActivity()
    {
        authHandler.signOut();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }
}