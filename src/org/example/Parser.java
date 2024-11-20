package org.example;

import com.opencsv.exceptions.CsvException;
import org.example.Models.Topic;
import org.example.Models.*;
import com.opencsv.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;

public class Parser {
    public static List<String[]> readCSVFile(String file) {
        var parser = new CSVParserBuilder()
                .withSeparator(';')
                .build();
        try (var reader = new CSVReaderBuilder(
                new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)) // Добавлена кодировка UTF-8
                .withCSVParser(parser)
                .build()) {
            return reader.readAll();
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Student> parseStudents(List<String[]> values) {
        var students = new ArrayList<Student>();
        for (int i = 3; i < values.size(); i++) {
            var name = values.get(i)[0];
            var group = values.get(i)[1];
            var pointsCount = Integer.parseInt(values.get(i)[2])
                    + Integer.parseInt(values.get(i)[3])
                    + Integer.parseInt(values.get(i)[4]);
            var topicsForStudent = parseTopics(values, i);
            var student = new Student(name, group, pointsCount, topicsForStudent);
            students.add(student);
        }
        return students;
    }

    public static ArrayList<Topic> parseTopics(List<String[]> values, int indexStudent) {
        var tasks = new ArrayList<Task>();
        var topics = new ArrayList<Topic>();
        var len = values.get(0).length;
        var titleTopic = values.get(0)[7];
        for (int i = 7; i < len; i++) {
            var line = values.get(1)[i];
            if (!Objects.equals(values.get(0)[i], "")) {
                var topic = new Topic(titleTopic, tasks);
                topics.add(topic);
                tasks = new ArrayList<Task>();
                titleTopic = values.get(0)[i];
            }
            if (line.contains("Упр:")) {
                var title = line.replace("Упр: ", "");
                var type = TasksTypes.exercise;
                var maxPointsCount = Integer.parseInt(values.get(indexStudent)[i]);
                var task = new Task(title, type, maxPointsCount);
                tasks.add(task);
            } else if (line.contains("ДЗ:")) {
                var title = line.replace("ДЗ: ", "");
                var type = TasksTypes.practice;
                var maxPointsCount = Integer.parseInt(values.get(indexStudent)[i]);
                var task = new Task(title, type, maxPointsCount);
                tasks.add(task);
            }
        }
        return topics;
    }
}

