package org.example.Models;

import java.util.ArrayList;

public class Course {
    private ArrayList<Topic> topics;
    private ArrayList<Student> students;

    public Course(ArrayList<Student> students){
        this.students = students;
    }

    public ArrayList<Topic> getTopics(){
        return topics;
    }

    public ArrayList<Student> getStudents(){
        return students;
    }

    public void setTopics(ArrayList<Topic> topics){
        this.topics = topics;
    }

    public void setStudents(ArrayList<Student> students){
        this.students = students;
    }
}
