package Models;

import java.util.ArrayList;

public class Course {
    private ArrayList<Topic> topics;
    private ArrayList<Student> students;

    public ArrayList<Topic> getModules(){
        return topics;
    }

    public ArrayList<Student> getStudents(){
        return students;
    }
}