package com.shubhamdeshmukh.newattendancemanagement.backend.database_entities;

import java.util.ArrayList;
import java.util.Date;

public class Attendance {
    private Date date;
    private ArrayList<StudentStatus> studentStatusList;

    public Attendance() {};

    public Attendance(Date date)
    {
        this.date = date;
        this.studentStatusList = new ArrayList<>();
    }

    public void addStudentStatus(StudentStatus studentStatus)
    {
        this.studentStatusList.add(studentStatus);
    }

    public Date getDate() {
        return date;
    }

    public ArrayList<StudentStatus> getStudentStatusList() {
        return studentStatusList;
    }

    public int getStudentIndexWithId(String id)
    {
        for(int i = 0; i < this.studentStatusList.size(); i++)
        {
            if (this.studentStatusList.get(i).getStudent().getId().equals(id))
            {
                return i;
            }
        }
        return -1;
    }
}
