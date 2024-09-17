package com.shubhamdeshmukh.attendencemanagementsystem.backend;

import android.util.Log;
import android.webkit.ValueCallback;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shubhamdeshmukh.attendencemanagementsystem.R;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.database_entities.Batch;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.database_entities.Data;
import com.shubhamdeshmukh.attendencemanagementsystem.frontend.MainActivity;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.other_entities.Account;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.database_entities.Attendance;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.database_entities.Category;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.database_entities.Class;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.database_entities.Monitor;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.database_entities.StudentStatus;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.database_entities.Subject;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.database_entities.Teacher;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class FirebaseDBConnection {

    private static FirebaseDatabase database;
    private static FirebaseAuth mAuth;
    private static Data fetchedData;
    private static Data registrationData;

    private static String uid;

    public FirebaseDBConnection(String dbAccessLink)
    {
        this.database = FirebaseDatabase.getInstance(dbAccessLink);

        this.mAuth = FirebaseAuthentication.getMAuth();

//        fetchAccounts();
        BackendTestingCode.trialCode(uid, database);
    }

    public static void setUserId(String uid) {
        FirebaseDBConnection.uid = uid;
    }

    public static void fetchData()
    {
        DatabaseReference dataRef = database.getReference("Data");

        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    FirebaseDBConnection.fetchedData = snapshot.getValue(Data.class);
                    Log.d(MainActivity.TAG, "onDataChange: Data" + fetchedData);
                }
                else {
                    Log.d(MainActivity.TAG, "onDataChange: NULL Data");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static void updateDatabase()
    {
        // Send the fetchedData (with possible changes) to eh Database
        DatabaseReference dataRef = database.getReference("Data");
        dataRef.setValue(fetchedData);
    }

    public void getAccount(final ValueCallback<Object> callback)
    {
        Log.d(MainActivity.TAG, "getAccount: Current Account: " + MainActivity.authHandler.getCurrentUser().getUid());
        Account accountRef = null;
        if (FirebaseDBConnection.fetchedData != null)
        {
            for (int i = 0; i < FirebaseDBConnection.fetchedData.accounts.size(); i++) {
                if (Objects.equals(FirebaseDBConnection.fetchedData.accounts.get(i).getUserID(), MainActivity.authHandler.getCurrentUser().getUid()))
                {
                    if (Objects.equals(FirebaseDBConnection.fetchedData.accounts.get(i).getType(), "Teacher"))
                    {
                        DatabaseReference teacherRef = database.getReference("Data").child("accounts").child(String.valueOf(i));
                        teacherRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    Teacher teacher = snapshot.getValue(Teacher.class);
                                    callback.onReceiveValue(teacher);
                                    Log.d(MainActivity.TAG, "onDataChange: Teacher Info: " + teacher.printInfo());

                                } else {
                                    Log.d(MainActivity.TAG, "onDataChange: Value doesn't exist");
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {}
                        });
                    }
                    else if (Objects.equals(FirebaseDBConnection.fetchedData.accounts.get(i).getType(), "Monitor"))
                    {
                        DatabaseReference monitorRef = database.getReference("Data").child("accounts").child(String.valueOf(i));
                        monitorRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    Monitor monitor = snapshot.getValue(Monitor.class);
                                    callback.onReceiveValue(monitor);
                                    Log.d(MainActivity.TAG, "onDataChange: Monitor Info: " + monitor.printInfo());
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {}
                        });
                    }
                }
            }
        }
        callback.onReceiveValue(null);
    }

    public Data getFetchedData()
    {
        return fetchedData;
    }

    public Data getRegistrationData()
    {
        if (registrationData == null)
        {
            registrationData = fetchedData;
        }
        return registrationData;
    }

    public void setRegistrationData(Data data)
    {
        registrationData = data;
    }

    public void completeRegistration()
    {
        fetchedData = registrationData;
    }

    public static FirebaseDatabase getDatabase() {
        return database;
    }

    public static FirebaseAuth getmAuth() {
        return mAuth;
    }

    public static String getUid() {
        return uid;
    }

    public int getCurrentUserAsTeacherIndex() {
        String uid = MainActivity.authHandler.getCurrentUser().getUid();
        ArrayList<Account> accountArrayList = registrationData.accounts;


        for (int i = 0; i < accountArrayList.size(); i++) {
            Account account = accountArrayList.get(i);
            if (account instanceof Teacher)
            {
                Teacher teacherAccount = (Teacher) account;
                if (Objects.equals(teacherAccount.getUserID(), uid))
                {
                    return i;
                }
            }
        }

        accountArrayList.add(new Teacher("", uid));
        registrationData.accounts = accountArrayList;
        completeRegistration();
        updateDatabase();

        return accountArrayList.size() - 1; // New Teacher Index
    }
}