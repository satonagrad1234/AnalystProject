package org.example;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import org.example.Models.Student;
import org.example.vkApi.VKInfo;

import java.util.List;

public class Main {
    public static void main(String[] args) throws ClientException, ApiException {
        var file = "C:\\Users\\Erop\\IdeaProjects\\AnalystProject\\src\\org\\example\\basicprogramming_2.csv";
        var values = Parser.readCSVFile(file);
        var students = Parser.parseStudents(values);
        var vk = new VKInfo();

        for (Student student : students) {
            var studentInfo = vk.getUserByName(student.getName());
            vk.printUserInfo(studentInfo);
            System.out.println("STUDENT INFO: " + student.toString());
            System.out.println("--------------------------------------");
        }
    }

    public static void printStudents(List<String[]> values) throws ClientException, ApiException {
        var students = Parser.parseStudents(values);
        for(var student : students) {
            System.out.println(student.toString());
        }
    }
}
