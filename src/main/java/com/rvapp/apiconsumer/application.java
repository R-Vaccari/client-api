package com.rvapp.apiconsumer;

import com.rvapp.apiconsumer.database.DBConnector;
import com.rvapp.apiconsumer.database.SQL;
import com.rvapp.apiconsumer.resources.ClassGroupResource;
import com.rvapp.apiconsumer.resources.CourseResource;
import com.rvapp.apiconsumer.resources.StudentResource;
import com.rvapp.apiconsumer.resources.TeacherResource;

import java.sql.SQLException;
import java.util.Scanner;

public class application {

    public static void main(String[] args) throws SQLException {

        StudentResource resourceStudents = new StudentResource();
        TeacherResource resourceTeachers = new TeacherResource();
        ClassGroupResource resourceClasses = new ClassGroupResource(resourceStudents, resourceTeachers);
        CourseResource resourceCourses = new CourseResource(resourceClasses);

        //SQL.createTables();
        SQL.deleteAllData();

        Scanner sc = new Scanner(System.in);
        System.out.println(resourceClasses.getWebTarget());
        System.out.println();

        System.out.println("Connection = " + DBConnector.getConnection().toString() + "\nCurrent Webtarget = " + resourceStudents.getWebTarget());
        System.out.println();

        System.out.println("What data would you like to retrieve? \nCourses(1); \nClasses(2); \nTeachers(3); \nStudents(4). \nClasses include associated teachers and students. " +
                "Courses include associated classes and it's members.");

        switch (sc.nextInt()) {
            case 1:
                resourceCourses.insertSingle(resourceCourses.getAll());
                System.out.println("Course list inserted in database successfully.");
                break;
            case 2:
                resourceClasses.insertList(resourceClasses.getAll());
                System.out.println("Classes list inserted in database successfully.");
                break;
            case 3:
                resourceTeachers.insertList(resourceTeachers.getAll());
                System.out.println("Teacher list inserted in database successfully.");
                break;
            case 4:
                resourceStudents.insertList(resourceStudents.getAll());
                System.out.println("Student list inserted in database successfully.");
                break;
        }
        sc.close();
    }
}
