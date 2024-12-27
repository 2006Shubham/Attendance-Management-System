//package com.shubhamdeshmukh.newattendancemanagement.adaptermodelclass;
//
//public class AttendanceSubject {
//    private String subjectName;
//    private int imageResource; // For the profile image
//
//    public AttendanceSubject(String subjectName, int imageResource) {
//        this.subjectName = subjectName;
//        this.imageResource = imageResource;
//    }
//
//    public String getSubjectName() {
//        return subjectName;
//    }
//
//    public void setSubjectName(String subjectName) {
//        this.subjectName = subjectName;
//    }
//
//    public int getImageResource() {
//        return imageResource;
//    }
//
//    public void setImageResource(int imageResource) {
//        this.imageResource = imageResource;
//    }
//}

package com.shubhamdeshmukh.newattendancemanagement.adaptermodelclass;

import java.util.ArrayList;
import java.util.List;

public class AttendanceSubject {
    private String subjectName;
    private int imageResource;
    private ArrayList<Batch> batchList;

    public AttendanceSubject(String subjectName, int imageResource, ArrayList<Batch> batchList) {
        this.subjectName = subjectName;
        this.imageResource = imageResource;
        this.batchList = batchList;
    }

    public AttendanceSubject(String subjectName, int imageResource) {
        this.subjectName = subjectName;

       this.imageResource = imageResource;
  }

    public String getSubjectName() {
        return subjectName;
    }

    public int getImageResource() {
        return imageResource;
    }

    public ArrayList<Batch> getBatchList() {
        return batchList;
    }
}

