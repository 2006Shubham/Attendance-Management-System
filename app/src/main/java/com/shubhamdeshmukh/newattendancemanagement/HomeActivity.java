package com.shubhamdeshmukh.newattendancemanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.shubhamdeshmukh.newattendancemanagement.flow1activity.SubjectInfoActivity;
import com.shubhamdeshmukh.newattendancemanagement.flow1fragments.HomeFragment;
import com.shubhamdeshmukh.newattendancemanagement.flow1fragments.SubjectFragment;
import com.shubhamdeshmukh.newattendancemanagement.flow2fragments.ClassFragment;
import com.shubhamdeshmukh.newattendancemanagement.flow3fragments.ScheduleFragment;
import com.shubhamdeshmukh.newattendancemanagement.flow4fragments.TakeAttendanceFragment;
import com.shubhamdeshmukh.newattendancemanagement.flow4fragments.YourLoadFragment;

public class HomeActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    ImageView imageView;
    TextView fragname;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);


        drawerLayout = findViewById(R.id.homedrawer);
        navigationView =findViewById(R.id.navigationView);
        toolbar =  findViewById(R.id.toolBar);
        imageView  = findViewById(R.id.menue_stick);
        fragname = findViewById(R.id.toolbar_title);



        setSupportActionBar(toolbar);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.OpenDrawer,R.string.CloseDrawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                            drawerLayout.closeDrawer(GravityCompat.START);
                        } else {
                            drawerLayout.openDrawer(GravityCompat.START);

                         }
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id==R.id.subject){
                        loadFragment(new SubjectFragment());
                        fragname.setText("Subjects");
                }
                else if (id==R.id.cls){

                    loadFragment(new ClassFragment());
                    fragname.setText("Classes");

                }
                else if(id==R.id.home){
                    loadFragment(new HomeFragment());
                    fragname.setText("Home");
                }

                else if(id==R.id.schedule){
                    loadFragment(new ScheduleFragment());
                    fragname.setText("Schedule");
                } else if (id==R.id.load) {

                    loadFragment(new YourLoadFragment());
                    fragname.setText("Your Load");

                } else if (id==R.id.takeAttendance) {

                    loadFragment(new TakeAttendanceFragment());
                    fragname.setText("Take Attendance");

                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }

        });
    }

    private void loadFragment(Fragment fragment) {

        FragmentManager fm =  getSupportFragmentManager() ;
        FragmentTransaction ft = fm.beginTransaction();


        ft.replace(R.id.containerHome,fragment);
        ft.commit();
    }

    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            // Close the drawer if it's open
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            // If the drawer is not open, proceed with default back behavior
            super.onBackPressed();
        }
    }


}