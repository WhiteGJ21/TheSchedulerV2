package org.example.theschedulerv2;
import java.util.Scanner;

public class Class {
    private String courseID;
    private String courseName;
    private int numCredits;
    private String daysOfWeek;
    private int beginTime; // Military Time
    private int endTime; // Military Time
    private String instructor;

    private String department;
    private int indexInDB;

    public Class(String courseID, String courseName, int numCredits,
                 String daysOfWeek, int beginTime, int endTime,
                 String instructor, String department, int indexInDB) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.numCredits = numCredits;
        this.daysOfWeek = daysOfWeek;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.instructor = instructor;
        this.department = department;
        this.indexInDB = indexInDB;
    }

    public int getIndexInDB() {
        return indexInDB;
    }

    public int getNumCredits() {
        return numCredits;
    }

    public int getBeginTime() {
        return beginTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getDaysOfWeek(){
        return daysOfWeek;
    }

    @Override
    public String toString() {

        return indexInDB + ": " + courseID + " " + courseName + " " + numCredits + " "+ daysOfWeek + " " +
                convertTime(beginTime) + "-" + convertTime(endTime) + " " + instructor;
    }

    public static String convertTime(int time){
        StringBuilder returnable = new StringBuilder();
        if(time<1200){
            returnable.append(time / 100);
            returnable.append(":");

            if(time % 100 < 10) returnable.append("0");

            returnable.append(time % 100);
            returnable.append(" AM");
            return returnable.toString();
        }else if(time < 1300){
            returnable.append(time/100);
            returnable.append(":");

            if(time % 100 < 10) returnable.append("0");

            returnable.append(time%100);
            returnable.append(" PM");
            return returnable.toString();
        }

        time -= 1200;
        returnable.append(time / 100);
        returnable.append(":");

        if(time % 100 < 10) returnable.append("0");

        returnable.append(time % 100);
        returnable.append(" PM");

        return returnable.toString();
    }

    public String getCourseName() {
        return courseName;
    }


    public boolean hasConflict(Class other){
        boolean sameDay = false;
        for(int i = 0; i < other.daysOfWeek.length(); i++){
            for(int j = 0; j < this.daysOfWeek.length(); j++){
                if(this.daysOfWeek.charAt(j) == other.daysOfWeek.charAt(i)){
                    sameDay = true;
                    break;
                }
            }

        }

        boolean timeOverlap = false;

        if(this.beginTime == other.beginTime) timeOverlap = true;
        else if(this.beginTime > other.beginTime && this.endTime <= other.endTime){
            timeOverlap = true;
        }else if(other.beginTime > this.beginTime && other.endTime <= this.endTime) {
            timeOverlap = true;
        }

        return sameDay && timeOverlap;
    }

    public boolean isSameClass(Class other){
        return this.courseID.equals(other.courseID) && this.instructor.equals(other.instructor);
    }
}

