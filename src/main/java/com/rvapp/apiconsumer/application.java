package com.rvapp.apiconsumer;

import com.rvapp.apiconsumer.database.*;
import com.rvapp.apiconsumer.repositories.ClassGroupRepository;
import com.rvapp.apiconsumer.repositories.CourseRepository;
import com.rvapp.apiconsumer.repositories.StudentRepository;
import com.rvapp.apiconsumer.repositories.TeacherRepository;
import com.rvapp.apiconsumer.resources.ClassGroupResource;
import com.rvapp.apiconsumer.resources.CourseResource;
import com.rvapp.apiconsumer.resources.StudentResource;
import com.rvapp.apiconsumer.resources.TeacherResource;
import com.rvapp.apiconsumer.services.ClassGroupService;
import com.rvapp.apiconsumer.services.CourseService;
import com.rvapp.apiconsumer.services.StudentService;
import com.rvapp.apiconsumer.services.TeacherService;
import com.rvapp.apiconsumer.services.util.ClassGroupParser;
import com.rvapp.apiconsumer.services.util.CourseParser;
import com.rvapp.apiconsumer.services.util.StudentParser;
import com.rvapp.apiconsumer.services.util.TeacherParser;

import java.sql.SQLException;
import java.util.Scanner;

public class application {

    public static void main(String[] args) throws SQLException {

        StudentResource resourceStudents = new StudentResource();
        TeacherResource resourceTeachers = new TeacherResource();
        ClassGroupResource resourceClasses = new ClassGroupResource();
        CourseResource resourceCourses = new CourseResource();

        StudentRepository repositoryStudents = new StudentRepository();
        TeacherRepository repositoryTeachers = new TeacherRepository();
        ClassGroupRepository repositoryClasses = new ClassGroupRepository();
        CourseRepository repositoryCourses = new CourseRepository();

        CourseParser parserCources = new CourseParser();
        ClassGroupParser parserClasses = new ClassGroupParser();
        StudentParser parserStudents = new StudentParser();
        TeacherParser parserTeachers = new TeacherParser();

        StudentService serviceStudents = new StudentService(repositoryStudents, parserStudents);
        TeacherService serviceTeachers = new TeacherService(repositoryTeachers, parserTeachers);
        ClassGroupService serviceClasses = new ClassGroupService(serviceStudents, serviceTeachers, repositoryClasses, parserClasses);
        CourseService serviceCourses = new CourseService(serviceClasses, repositoryCourses, parserCources);

        Scanner sc = new Scanner(System.in);
        System.out.println(resourceClasses.getWebTarget());
        System.out.println();

        System.out.println("Connection = " + DBConnector.getConnection().toString() + "\nWould you like to create database tables(1) or delete all data in current database(2)?");
        switch (sc.nextInt()) {
            case 1:
                DBAdministrator.createTables();
                System.out.println("Tables created successfully.");
                break;
            case 2:
                DBAdministrator.deleteAllData();
                System.out.println("All data deleted successfully.");
                break;
        }

        System.out.println();
        System.out.println("What data would you like to retrieve? \nCourses(1); \nClasses(2); \nTeachers(3); \nStudents(4). \nClasses include associated teachers and students. " +
                "Courses include associated classes and it's members.");

        switch (sc.nextInt()) {
            case 1:
                serviceCourses.insertList(resourceCourses.getAll());
                System.out.println("Target: " + resourceCourses.getWebTarget());
                System.out.println("Course list inserted in database successfully.");
                break;
            case 2:
                serviceClasses.insertList(resourceClasses.getAll());
                System.out.println("Target: " + resourceClasses.getWebTarget());
                System.out.println("Classes list inserted in database successfully.");
                break;
            case 3:
                serviceTeachers.insertList(resourceTeachers.getAll());
                System.out.println("Target: " + resourceTeachers.getWebTarget());
                System.out.println("Teacher list inserted in database successfully.");
                break;
            case 4:
                serviceStudents.insertList(resourceStudents.getAll());
                System.out.println("Target: " + resourceStudents.getWebTarget());
                System.out.println("Student list inserted in database successfully.");
                break;
        }
        sc.close();
    }
}
