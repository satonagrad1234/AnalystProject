package Models;

import java.util.ArrayList;

public class Topic {
    private final String title;
    private ArrayList<Task> tasks;

    public Topic(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public ArrayList<Task> getTasks(){
        return tasks;
    }
}