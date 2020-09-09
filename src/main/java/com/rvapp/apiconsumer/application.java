package com.rvapp.apiconsumer;

import com.rvapp.apiconsumer.database.DBConnector;
import com.rvapp.apiconsumer.database.SQL;
import com.rvapp.apiconsumer.resources.ClassGroupResource;
import com.rvapp.apiconsumer.resources.CourseResource;
import com.rvapp.apiconsumer.resources.StudentResource;
import com.rvapp.apiconsumer.util.Parser;

import java.sql.SQLException;
import java.util.Scanner;

public class application {

    public static void main(String[] args) throws SQLException {

        ClassGroupResource resourceClasses = new ClassGroupResource();
        StudentResource resourceStudents = new StudentResource();
        CourseResource resourceCourses = new CourseResource();

        SQL.createTables();
        //resourceClasses.insertParsedClassGroup(resourceClasses.getBusinessClasses());
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


                resourceStudents.insertParsedStudents(resourceStudents.getAllStudents());
                System.out.println("Student list inserted in database successfully.");
                break;
            case 2:
                Parser.produceStudentsJson(resourceStudents.getAllStudents());
                System.out.println("Parse to file successful.");
                break;
        }

        System.out.println("Choose between the following: \nInsert Student data in database(1); \nProduce file with Student data(2).");

        switch (sc.nextInt()) {
            case 1:
                resourceStudents.insertParsedStudents(resourceStudents.getAllStudents());
                System.out.println("Student list inserted in database successfully.");
                break;
            case 2:
                Parser.produceStudentsJson(resourceStudents.getAllStudents());
                System.out.println("Parse to file successful.");
                break;
        }
        sc.close();

    }
}
