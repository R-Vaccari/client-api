package com.rvapp.apiconsumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rvapp.apiconsumer.resources.StudentResource;

import java.sql.SQLException;

public class application {

    public static void main(String[] args) {

        StudentResource resourceStudent = new StudentResource();

        try {
            resourceStudent.insertStudentList();
            System.out.println("Data retrieval successful.");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
