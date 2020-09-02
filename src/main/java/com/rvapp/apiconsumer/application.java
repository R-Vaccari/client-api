package com.rvapp.apiconsumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rvapp.apiconsumer.resources.ClassGroupResource;
import com.rvapp.apiconsumer.resources.StudentResource;
import com.rvapp.apiconsumer.util.DBConnector;
import com.rvapp.apiconsumer.util.Parser;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class application {

    public static void main(String[] args) throws SQLException {

        StudentResource resourceStudent = new StudentResource();
        ClassGroupResource resourceClasses = new ClassGroupResource();

        Scanner sc = new Scanner(System.in);

        System.out.println(resourceClasses.getWebTarget());
        System.out.println(resourceClasses.getClassesList());

        System.out.println("Connection = " + DBConnector.getConnection().toString() + "\nCurrent Webtarget = " + resourceStudent.getWebTarget());
        System.out.println();

        System.out.println("Choose between the following: \nInsert Student data in database(1); \nProduce file with Student data(2).");

        switch (sc.nextInt()) {
            case 1:
                try {
                    resourceStudent.insertStudentList();
                    System.out.println("Student list inserted in database successfully.");
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    System.out.println("An error occurred during Json processing.");
                } break;
            case 2:
                try {
                    Parser.produceJson(resourceStudent.getStudentsList());
                    System.out.println("Parse to file successful.");
                } catch (IOException e) {
                    e.printStackTrace();
                } break;
        }
        sc.close();
    }
}
