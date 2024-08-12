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
import androidx.annotation.NonNull;
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
import com.shubhamdeshmukh.attendencemanagementsystem.backend.FirebaseDBConnection;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.Teacher;
import com.shubhamdeshmukh.attendencemanagementsystem.frontend.login.LoginActivity;
import com.shubhamdeshmukh.attendencemanagementsystem.frontend.teacher.TeacherSubjectPortalActivity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainActivity extends AppCompatActivity {

    public static final String  TAG = "YashGames2007";
    private final String ID_COUNT_KEY = "ID_COUNT";

    public static FirebaseDatabase database;
    private FirebaseDBConnection dbConnection;
    public static FirebaseAuth mAuth;

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
        String dbAccessLink = getString(R.string.FIREBASE_DB_ACCESS_LINK);
        MainActivity.database = FirebaseDatabase.getInstance(dbAccessLink);
        MainActivity.mAuth = FirebaseAuth.getInstance();
        authenticate();
        FirebaseDBConnection.fetchAccounts();
//        trialCode();
        dbConnection = new FirebaseDBConnection(MainActivity.database, MainActivity.mAuth);
        manageInput();
//        dbConnection.trialCode();
    }

    private void trialCode()
    {
//        Teacher teacher = new Teacher("P V Sontakke");
//        teacher.addSubject("Cloud Computing", "6N201");
//        teacher.addSubject("Computer Network", "6N203");
//
//        DatabaseReference node = database.getReference("XYZ");
//        node.setValue(teacher);
//        Log.d(TAG, "trialCode   : Teacher Info: " + teacher.printInfo());
//
//        // Get a node
//        node.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    Teacher teacher = snapshot.getValue(Teacher.class);
//                    Log.d(TAG, "onDataChange: Teacher Info: " + teacher.printInfo());
//
//                } else {
//                    Log.d(TAG, "onDataChange: Value doesn't exist");
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }

    private void authenticate() {
        TextView idText = findViewById(R.id.loginID);
        Button logoutButton = findViewById(R.id.buttonLogout);

        FirebaseUser user = mAuth.getCurrentUser();

        if (user == null) {
            fireLoginActivity();
        }
        else {
            Log.d(TAG, "In Main");
            idText.setText(user.getEmail());
            FirebaseDBConnection.setUserId(MainActivity.mAuth.getCurrentUser().getUid());
        }

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                fireLoginActivity();
            }
        });
    }

    private void manageInput() {
        EditText inputField = findViewById(R.id.data);
        Button sendButton = findViewById(R.id.sendData);

        Intent intent = new Intent(this, TeacherSubjectPortalActivity.class);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String data = inputField.getText().toString();
                testFirebase(data);
                inputField.setText("");

                startActivity(intent);
                finish();


            }
        });
    }

    private void getCurrentIDCount(final ValueCallback<Object> callback) {
        DatabaseReference idCountRef = database.getReference(ID_COUNT_KEY);

        idCountRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    int existingValue = Integer.parseInt(dataSnapshot.getValue().toString());
                    idCountRef.setValue(existingValue + 1);
                    callback.onReceiveValue(existingValue + 1); // Call the callback with the value
                } else {
                    idCountRef.setValue(1);
                    callback.onReceiveValue(1); // Indicate no value exists
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
                callback.onReceiveValue(null); // Indicate an error occurred
            }
        });
    }

    private void testFirebase(String data) {
        getCurrentIDCount(new ValueCallback<Object>() {
            @Override
            public void onReceiveValue(Object value) {
                if (value != null) {
                    // Do something with the existing value - Add new data to database

                    int currentID = Integer.parseInt(value.toString());
                    DatabaseReference dataRef = database.getReference("Message No " + currentID);

                    // Create child nodes
                    DatabaseReference dataValueRef = dataRef.child("value");
                    dataValueRef.setValue(data);

                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        DatabaseReference dateRef = dataRef.child("dateTime");

                        // Get current date and time
                        LocalDateTime now = LocalDateTime.now();

                        // Define the desired format
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                        // Format the date and time as a string
                        String formattedDateTime = now.format(formatter);

                        // Set values for the child nodes
                        dateRef.setValue(formattedDateTime);
                    }
                } else {
                    // No existing value or an error occurred
                    System.out.println("No existing value or error");
                }
            }
        });

        // Read from the database

//        idCountRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                Log.d(TAG, "Value is: " + value);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });
    }

    private void fireLoginActivity()
    {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }
}