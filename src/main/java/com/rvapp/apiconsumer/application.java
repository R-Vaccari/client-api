package com.rvapp.apiconsumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rvapp.apiconsumer.domain.Student;
import com.rvapp.apiconsumer.resources.StudentResource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class application {

    public static void main(String[] args) throws SQLException {

        Connection conn = DBConnector.getConnection();

        PreparedStatement statementStudent = conn.prepareStatement("CREATE TABLE student(id varchar(50), firstname varchar(50), lastname varchar(50), " +
                    "email varchar(50), telephone varchar(50))");
        statementStudent.executeUpdate();
        statementStudent.close();

        try {
            insertStudentList();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        conn.close();
    }

    public static void insertStudentList() throws JsonProcessingException, SQLException {

        Connection conn = DBConnector.getConnection();

        StudentResource resourceStudent = new StudentResource();
        String data = resourceStudent.getData();
        List<Student> listStudent = resourceStudent.parseStudentList(data);

        System.out.println(listStudent);

        try {
            for (Student st : listStudent) {

                PreparedStatement queryInsert = conn.prepareStatement("INSERT INTO student(id, firstname, lastname, email, telephone) " +
                            "VALUES (?, ?, ?, ?, ?)");
                queryInsert.setString(1, st.getId());
                queryInsert.setString(2, st.getFirstName());
                queryInsert.setString(3, st.getLastName());
                queryInsert.setString(4, st.getEmail());
                queryInsert.setString(5, st.getTelephone());

                queryInsert.executeUpdate();
                queryInsert.close();
                }
        } catch (SQLException throwables) {
                throwables.printStackTrace();
        }
        conn.close();
    }
}
