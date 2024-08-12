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
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.Accounts;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.Batch;
import com.shubhamdeshmukh.attendencemanagementsystem.frontend.MainActivity;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.Account;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.Attendance;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.Category;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.Class;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.DateEntry;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.Monitor;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.Student;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.StudentStatus;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.Subject;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.Teacher;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FirebaseDBConnection {

    private final FirebaseDatabase database;
    private final FirebaseAuth mAuth;
    private static Accounts accountList;

    private static String uid;

    public FirebaseDBConnection(FirebaseDatabase database, FirebaseAuth mAuth)
    {
        this.database = database;
        this.mAuth = mAuth;
//        fetchAccounts();
        trialCode();
    }

    public static void setUserId(String uid) {
        FirebaseDBConnection.uid = uid;
    }

    public void trialCode()
    {
        StudentStatus studentStatus = new StudentStatus("226008", "Shubham Deshmukh", true);

        DateEntry dateEntry = new DateEntry(new Date(2024, 8, 2));
        dateEntry.addStudentStatus(studentStatus);

        Attendance attendance = new Attendance();
        attendance.addDateEntry(dateEntry);

        Class _class1 = new Class("CO 3rd Year");
        Class _class2 = new Class("AI 3rd Year");
        Class _class3 = new Class("CO 3rd Year");
        _class1.addAttendance(attendance);
        _class1.addBatch(new Batch("Batch A", "226001", "226021"));
        _class1.addBatch(new Batch("Batch B", "226022", "226037"));
        _class2.addAttendance(attendance);
        _class2.addBatch(new Batch("Batch A", "224001", "224019"));
        _class2.addBatch(new Batch("Batch B", "224020", "224035"));
        _class3.addAttendance(attendance);

        Category category1 = new Category("Lecture");
        Category category2 = new Category("Practical");
        category2.addClass(_class1);
        category2.addClass(_class2);
        category1.addClass(_class3);
//        category2.addClass(_class2);

        Subject subject1 = new Subject("Cloud Computing", "6S403");
        Subject subject2 = new Subject("Computer Network", "6N401");
        subject1.addCategory(category1);
        subject2.addCategory(category1);
        subject1.addCategory(category2);
        subject2.addCategory(category2);

        Teacher teacher1 = new Teacher("P V Sontakke", FirebaseDBConnection.uid);
        Log.d(MainActivity.TAG, "trialCode: UID: " + FirebaseDBConnection.uid);
        teacher1.addSubject(subject1);
        teacher1.addSubject(subject2);
        Teacher teacher2 = new Teacher("N V Patil", "5678");
        teacher2.addSubject(subject1);
        teacher2.addSubject(subject2);

        Monitor monitor1 = new Monitor("Shubham Deshmukh", "226008", "1234", "CO 3rd Year");
        Monitor monitor2 = new Monitor("Yash Bhavsar", "224002", "1234", "AN 3rd Year");

        DatabaseReference node1 = database.getReference("Accounts").child("accounts").child("0");
        node1.setValue(teacher1);
        DatabaseReference node2 = database.getReference("Accounts").child("accounts").child("1");
        node2.setValue(teacher2);
        DatabaseReference node3 = database.getReference("Accounts").child("accounts").child("2");
        node3.setValue(monitor1);
        DatabaseReference node4 = database.getReference("Accounts").child("accounts").child("3");
        node4.setValue(monitor2);

        Log.d(MainActivity.TAG, "trialCode: Success" + teacher1.printInfo());
        Log.d(MainActivity.TAG, "trialCode: Success" + teacher2.printInfo());

        node3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                 if (snapshot.exists()) {
                     Account teacher = snapshot.getValue(Account.class);

                     if (teacher != null)
                     {
//                    Log.d(MainActivity.TAG, "trialCode: Success" + teacher.printInfo());
                         if (Objects.equals(teacher.getType(), "Teacher"))
                         {
                             Teacher t = snapshot.getValue(Teacher.class);
                             Log.d(MainActivity.TAG, "onDataChange: Teacher");
                             DatabaseReference node = database.getReference("New Example");
                             node.setValue(t);
                         }
                         else if (Objects.equals(teacher.getType(), "Monitor"))
                         {
                             Student t = snapshot.getValue(Student.class);
                             Log.d(MainActivity.TAG, "onDataChange: Monitor");
                             DatabaseReference node = database.getReference("New Example");
                         }
                         Log.d(MainActivity.TAG, "Something Else:" + teacher.getType());
                     }
                     else
                         Log.d(MainActivity.TAG, "trialCode: NULL Teacher");

                } else {
                    Log.d(MainActivity.TAG, "onDataChange: Value doesn't exist");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static void fetchAccounts()
    {
        DatabaseReference accountsRef = MainActivity.database.getReference("Accounts");

        accountsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    FirebaseDBConnection.accountList = snapshot.getValue(Accounts.class);
                    Log.d(MainActivity.TAG, "onDataChange: AccountList");
                }
                else {
                    Log.d(MainActivity.TAG, "onDataChange: NULL Accounts");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void getAccount(final ValueCallback<Object> callback)
    {
        Account accountRef = null;
        if (FirebaseDBConnection.accountList != null)
        {
            for (int i = 0; i < FirebaseDBConnection.accountList.accounts.size(); i++) {
                if (Objects.equals(FirebaseDBConnection.accountList.accounts.get(i).getUserID(), mAuth.getCurrentUser().getUid()))
                {
                    if (Objects.equals(FirebaseDBConnection.accountList.accounts.get(i).getType(), "Teacher"))
                    {
                        DatabaseReference teacherRef = database.getReference("Accounts").child("accounts").child(String.valueOf(i));
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
                    else if (Objects.equals(FirebaseDBConnection.accountList.accounts.get(i).getType(), "Monitor"))
                    {
                        DatabaseReference monitorRef = database.getReference("Accounts").child("accounts").child(String.valueOf(i));
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
}