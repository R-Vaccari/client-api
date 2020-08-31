package com.rvapp.apiconsumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rvapp.apiconsumer.resources.StudentResource;
import com.rvapp.apiconsumer.util.Parser;

import java.io.IOException;
import java.sql.SQLException;

public class application {

    public static void main(String[] args) {

        StudentResource resourceStudent = new StudentResource();

        try {

            Parser.produceJson(resourceStudent.getData());
            System.out.println("Parse to file successful.");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
