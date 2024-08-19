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
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.Batch;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.Data;
import com.shubhamdeshmukh.attendencemanagementsystem.frontend.MainActivity;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.Account;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.Attendance;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.Category;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.Class;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.Monitor;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.StudentStatus;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.Subject;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.Teacher;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class FirebaseDBConnection {

    private final FirebaseDatabase database;
    private final FirebaseAuth mAuth;
    private static Data fetchedData;
    private static Data registrationData;

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

        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.DAY_OF_MONTH, 19);
        Log.d(MainActivity.TAG, "trialCode: CALENDAR: " + cal1.getTime());
        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.DAY_OF_MONTH, 20);
        Log.d(MainActivity.TAG, "trialCode: 1CALENDAR: " + cal2.getTime());
        Calendar cal3 = Calendar.getInstance();
        cal3.set(Calendar.DAY_OF_MONTH, 21);
        Log.d(MainActivity.TAG, "trialCode: 2CALENDAR: " + cal3.getTime());
        Calendar cal4 = Calendar.getInstance();
        cal4.set(Calendar.DAY_OF_MONTH, 22);
        Log.d(MainActivity.TAG, "trialCode: 3CALENDAR: " + cal4.getTime());
        Calendar cal5 = Calendar.getInstance();
        cal5.set(Calendar.DAY_OF_MONTH, 23);
        Log.d(MainActivity.TAG, "trialCode: 4CALENDAR: " + cal5.getTime());

//        DateEntry dateEntry = new DateEntry(cal.getTime());

        Class _class1 = new Class("CO 3rd Year");
        Class _class2 = new Class("AI 3rd Year");
        Class _class3 = new Class("CO 3rd Year");
        _class1.addAttendance(getCOAttendance(cal1.getTime(), true));
        _class1.addAttendance(getCOAttendance(cal2.getTime(), false));
        _class1.addAttendance(getCOAttendance(cal3.getTime(), true));
        _class1.addAttendance(getCOAttendance(cal4.getTime(), false));
        _class1.addAttendance(getCOAttendance(cal5.getTime(), true));
        Batch batch1 = new Batch("Batch A", "226001", "226021");
        Batch batch2 = new Batch("Batch B", "226022", "226037");
        _class1.addBatch(batch1);
        _class1.addBatch(batch2);
        _class3.addAttendance(getCOAttendance(cal1.getTime(), true));
        _class3.addAttendance(getCOAttendance(cal2.getTime(), false));
        _class3.addAttendance(getCOAttendance(cal3.getTime(), true));
        _class3.addAttendance(getCOAttendance(cal4.getTime(), false));
        _class3.addAttendance(getCOAttendance(cal5.getTime(), true));
        Batch batch3 = new Batch("Batch A", "224001", "224019");
        Batch batch4 = new Batch("Batch B", "224020", "224035");
        _class2.addBatch(batch3);
        _class2.addBatch(batch4);
        _class2.addAttendance(getANAttendance(cal1.getTime(), true));
        _class2.addAttendance(getANAttendance(cal2.getTime(), false));
        _class2.addAttendance(getANAttendance(cal3.getTime(), true));
        _class2.addAttendance(getANAttendance(cal4.getTime(), false));
        _class2.addAttendance(getANAttendance(cal5.getTime(), true));

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
        Log.d(MainActivity.TAG, "trialCode: Current UID: " + FirebaseDBConnection.uid);
        teacher1.addSubject(subject1);
        teacher1.addSubject(subject2);
        Teacher teacher2 = new Teacher("N V Patil", "5678");
        teacher2.addSubject(subject1);
        teacher2.addSubject(subject2);

        Monitor monitor1 = new Monitor("Shubham Deshmukh", "226008", "1234", "CO 3rd Year");
        Monitor monitor2 = new Monitor("Yash Bhavsar", "224002", "1234", "AN 3rd Year");


        Data data = new Data();
        data.addAccount(teacher1);
        data.addAccount(teacher2);
        data.addAccount(monitor1);
        data.addAccount(monitor2);

        data.addClass(_class1);
        data.addClass(_class2);
        data.addClass(_class3);

        data.addSubject(subject1);
        data.addSubject(subject2);

        data.addBatch(batch1);
        data.addBatch(batch2);
        data.addBatch(batch3);
        data.addBatch(batch4);

        data.addCategory(category1);
        data.addCategory(category2);

        DatabaseReference node1 = database.getReference("Data");
        node1.setValue(data);

        data = null;
        Log.d(MainActivity.TAG, "trialCode: Success" + teacher1.printInfo());
        Log.d(MainActivity.TAG, "trialCode: Success" + teacher2.printInfo());
    }

    private Attendance getCOAttendance(Date date, boolean key)
    {
        Attendance attendance = new Attendance(date);
        attendance.addStudentStatus(new StudentStatus("206011", "Varsha Ghogare", key));
        attendance.addStudentStatus(new StudentStatus("216011", "Riya Gosavi", !key));
        attendance.addStudentStatus(new StudentStatus("216021", "Tanvi Mundale", key));
        attendance.addStudentStatus(new StudentStatus("226001", "Harshita Adhane", !key));
        attendance.addStudentStatus(new StudentStatus("226002", "Shruti Ahire", key));
        attendance.addStudentStatus(new StudentStatus("226006", "Anuja Bansode", !key));
        attendance.addStudentStatus(new StudentStatus("226007", "Preeti Deore", key));
        attendance.addStudentStatus(new StudentStatus("226008", "Shubham Deshmukh", !key));
        attendance.addStudentStatus(new StudentStatus("226009", "Kalyani Ghodke", key));
        attendance.addStudentStatus(new StudentStatus("226011", "Vaibhavi Gore", !key));
        attendance.addStudentStatus(new StudentStatus("226014", "Harsh Kathar", key));
        attendance.addStudentStatus(new StudentStatus("226015", "Kiran Kelode", !key));
        attendance.addStudentStatus(new StudentStatus("226016", "Anushka Kharat", key));
        attendance.addStudentStatus(new StudentStatus("226017", "Pranjal Nirkhe", !key));
        attendance.addStudentStatus(new StudentStatus("226018", "Vaishnavi Pardeshi", key));
        attendance.addStudentStatus(new StudentStatus("226019", "Aditya Pathrikar", !key));
        attendance.addStudentStatus(new StudentStatus("226020", "Omkar Patil", key));
        attendance.addStudentStatus(new StudentStatus("226021", "Anuja Pawar", !key));
        attendance.addStudentStatus(new StudentStatus("226022", "Rutuja Peherkar", key));
        attendance.addStudentStatus(new StudentStatus("226023", "Shreya Rajput", !key));
        attendance.addStudentStatus(new StudentStatus("226024", "Jayesh Rathod", key));
        attendance.addStudentStatus(new StudentStatus("226025", "Vishal Rathod", !key));
        attendance.addStudentStatus(new StudentStatus("226027", "Siddhi Shelke", key));
        attendance.addStudentStatus(new StudentStatus("226028", "Akash Sose", !key));
        attendance.addStudentStatus(new StudentStatus("226029", "Sanika Suryawanshi", key));
        attendance.addStudentStatus(new StudentStatus("226030", "Samiksha Tarte", !key));
        attendance.addStudentStatus(new StudentStatus("226031", "Harshada Taur", key));
        attendance.addStudentStatus(new StudentStatus("226032", "Dhanashree Tupshendre", !key));
        attendance.addStudentStatus(new StudentStatus("226033", "Anushka Udawant", key));
        attendance.addStudentStatus(new StudentStatus("226035", "Gauri Waghchaure", !key));
        attendance.addStudentStatus(new StudentStatus("226036", "Anushka Sukhadeve", key));
        attendance.addStudentStatus(new StudentStatus("226037", "Suraj Wakhure", !key));
        attendance.addStudentStatus(new StudentStatus("236202", "Harshada Chaudhari", key));
        attendance.addStudentStatus(new StudentStatus("236203", "Arpita Nikam", !key));
        attendance.addStudentStatus(new StudentStatus("236204", "Jay Thuse", key));
        return attendance;
    }

    private Attendance getANAttendance(Date date, boolean key)
    {
        Attendance attendance = new Attendance(date);
        attendance.addStudentStatus(new StudentStatus("214013", "Rushikesh Jadhav", key));
        attendance.addStudentStatus(new StudentStatus("224001", "Tushar Ambhore", !key));
        attendance.addStudentStatus(new StudentStatus("224002", "Yash Bhavsar", key));
        attendance.addStudentStatus(new StudentStatus("224003", "Priya Bhokre", !key));
        attendance.addStudentStatus(new StudentStatus("224004", "Pranjali Bidkar", key));
        attendance.addStudentStatus(new StudentStatus("224005", "Snehal Bomble", !key));
        attendance.addStudentStatus(new StudentStatus("224006", "Nikhil Datar", key));
        attendance.addStudentStatus(new StudentStatus("224008", "Mayur Gaikwad", !key));
        attendance.addStudentStatus(new StudentStatus("224009", "Sarthak Ganbote", key));
        attendance.addStudentStatus(new StudentStatus("224010", "Gayatri Surase", !key));
        attendance.addStudentStatus(new StudentStatus("224011", "Pratiksha Ghodke", key));
        attendance.addStudentStatus(new StudentStatus("224012", "Gautami Jadhav", !key));
        attendance.addStudentStatus(new StudentStatus("224013", "Sainath Kadam", key));
        attendance.addStudentStatus(new StudentStatus("224014", "Shraddha Kale", !key));
        attendance.addStudentStatus(new StudentStatus("224016", "Diksha Kulkarni", key));
        attendance.addStudentStatus(new StudentStatus("224017", "Swananda Lakhole", !key));
        attendance.addStudentStatus(new StudentStatus("224019", "Anuj Mundawade", key));
        attendance.addStudentStatus(new StudentStatus("224020", "Bhujang Navgire", !key));
        attendance.addStudentStatus(new StudentStatus("224021", "Shivam Panchal", key));
        attendance.addStudentStatus(new StudentStatus("224022", "Anand Pathade", !key));
        attendance.addStudentStatus(new StudentStatus("224025", "Sejal Revanwar", key));
        attendance.addStudentStatus(new StudentStatus("224026", "Shubham Saraf", !key));
        attendance.addStudentStatus(new StudentStatus("224027", "Yash Sathe", key));
        attendance.addStudentStatus(new StudentStatus("224031", "Vaidehi Unhale", !key));
        attendance.addStudentStatus(new StudentStatus("224034", "Vasudha Lanke", key));
        attendance.addStudentStatus(new StudentStatus("224035", "Vikas Wagh", !key));
        attendance.addStudentStatus(new StudentStatus("234201", "Sakshi Mane", key));
        attendance.addStudentStatus(new StudentStatus("234202", "Nikita Shinde", !key));
        attendance.addStudentStatus(new StudentStatus("234203", "Shyam Shrimangle", key));
        attendance.addStudentStatus(new StudentStatus("234204", "Ganga Wade", !key));
        return attendance;
    }

    public static void fetchData()
    {
        DatabaseReference dataRef = MainActivity.database.getReference("Data");

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
        DatabaseReference dataRef = MainActivity.database.getReference("Data");
        dataRef.setValue(fetchedData);
    }

    public void getAccount(final ValueCallback<Object> callback)
    {
        Log.d(MainActivity.TAG, "getAccount: Current Account: " + mAuth.getCurrentUser().getUid());
        Account accountRef = null;
        if (FirebaseDBConnection.fetchedData != null)
        {
            for (int i = 0; i < FirebaseDBConnection.fetchedData.accounts.size(); i++) {
                if (Objects.equals(FirebaseDBConnection.fetchedData.accounts.get(i).getUserID(), mAuth.getCurrentUser().getUid()))
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
}