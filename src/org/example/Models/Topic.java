package org.example.Models;

import java.util.ArrayList;

public class Topic {
    private final String title;
    private final ArrayList<Task> tasks;

    public Topic(String title, ArrayList<Task> tasks) {
        this.title = title;
        this.tasks = tasks;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getPoints() {
        var sum = 0;
        for (var task : tasks) {
            sum += task.getMaxPointsCount();
        }
        return sum;
    }

    @Override
    public String toString() {
        var tasksBuilder = new StringBuilder();
        for (var task : tasks) {
            tasksBuilder.append(task.toString()).append("\n");
        }

        return String.format("""
                , TITLE: %s
                POINTS_COUNT: %d
                TASKS: [%s]
                """, title, getPoints(), tasksBuilder.toString().trim());
    }
}

