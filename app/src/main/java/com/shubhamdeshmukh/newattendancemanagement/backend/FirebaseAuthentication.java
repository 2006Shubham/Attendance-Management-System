package com.shubhamdeshmukh.newattendancemanagement.backend;

import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shubhamdeshmukh.newattendancemanagement.R;
//import com.shubhamdeshmukh.newattendancemanagement.frontend.MainActivity;

public class FirebaseAuthentication {

    private final String ID_COUNT_KEY = "ID_COUNT";

    private static FirebaseAuth mAuth;
    private static FirebaseUser currentUser;

    public FirebaseAuthentication()
    {
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
    }

    public boolean authenticate() {

        if (currentUser == null) {
            return false;
        }

        FirebaseDBConnection.setUserId(mAuth.getCurrentUser().getUid());
        return true;

    }

    public FirebaseUser getCurrentUser()
    {
        return currentUser;
    }

    public static FirebaseAuth getMAuth()
    {
        return mAuth;
    }

    public void signOut()
    {
        FirebaseAuth.getInstance().signOut();
    }

    public void getCurrentIDCount(FirebaseDatabase database, final ValueCallback<Object> callback) {
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


}
