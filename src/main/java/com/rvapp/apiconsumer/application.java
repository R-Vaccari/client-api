package com.rvapp.apiconsumer;

import com.rvapp.apiconsumer.database.DBConnector;
import com.rvapp.apiconsumer.database.SQL;
import com.rvapp.apiconsumer.resources.ClassGroupResource;
import com.rvapp.apiconsumer.resources.CourseResource;
import com.rvapp.apiconsumer.resources.StudentResource;
import com.rvapp.apiconsumer.resources.TeacherResource;
import com.rvapp.apiconsumer.util.Parser;

import java.sql.SQLException;
import java.util.Scanner;

public class application {

    public static void main(String[] args) throws SQLException {

        StudentResource resourceStudents = new StudentResource();
        TeacherResource resourceTeachers = new TeacherResource();
        ClassGroupResource resourceClasses = new ClassGroupResource(resourceStudents, resourceTeachers);
        CourseResource resourceCourses = new CourseResource(resourceClasses);

        SQL.createTables();
        //resourceClasses.insertParsedClassGroupList(resourceClasses.getAllClasses());
        resourceCourses.insertParsedCourse(resourceCourses.getAllCourses());


        Scanner sc = new Scanner(System.in);
        System.out.println(resourceClasses.getWebTarget());
        System.out.println();

        System.out.println("Connection = " + DBConnector.getConnection().toString() + "\nCurrent Webtarget = " + resourceStudents.getWebTarget());
        System.out.println();

        System.out.println("What data would you like to retrieve? \nCourses(1); \nClasses(2); \nTeachers(3); \nStudents(4). \nClasses include associated teachers and students. " +
                "Courses include associated classes and it's members.");

        switch (sc.nextInt()) {
            case 1:
                System.out.println("What courses \nAll courses(1) \nA specific course by id");


                resourceStudents.insertList(resourceStudents.getAll());
                System.out.println("Student list inserted in database successfully.");
                break;
            case 2:
                Parser.produceStudentsJson(resourceStudents.getAll());
                System.out.println("Parse to file successful.");
                break;
        }

        System.out.println("Choose between the following: \nInsert Student data in database(1); \nProduce file with Student data(2).");

        switch (sc.nextInt()) {
            case 1:
                resourceStudents.insertList(resourceStudents.getAll());
                System.out.println("Student list inserted in database successfully.");
                break;
            case 2:
                Parser.produceStudentsJson(resourceStudents.getAll());
                System.out.println("Parse to file successful.");
                break;
        }
        sc.close();

    }
}
