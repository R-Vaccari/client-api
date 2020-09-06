package com.rvapp.apiconsumer.database;

import com.rvapp.apiconsumer.domain.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQL {

    static Connection conn = null;

    public static void insertStudent(Student student) {
        PreparedStatement queryInsert = null;

        try {
            conn = DBConnector.getConnection();
            conn.setAutoCommit(false);

            queryInsert = conn.prepareStatement("INSERT INTO student(id, firstname, lastname, email, telephone) VALUES (?, ?, ?, ?, ?)");
            queryInsert.setString(1, student.getId());
            queryInsert.setString(2, student.getFirstName());
            queryInsert.setString(3, student.getLastName());
            queryInsert.setString(4, student.getEmail());
            queryInsert.setString(5, student.getTelephone());
            queryInsert.executeUpdate();

            conn.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (queryInsert != null) queryInsert.close();
                if (conn != null) conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
