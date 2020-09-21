package com.rvapp.apiconsumer.database;

import java.sql.*;

public class DBAdministrator {

    static Connection conn = null;

    public static void checkTables() {
        ResultSet tables = null;

        try {
            conn = DBConnector.getConnection();
            DatabaseMetaData md = conn.getMetaData();
            tables = md.getTables(null, null, "%", null);
            if (!tables.next()) createTables();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (tables != null) tables.close();
                if (conn != null) conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void createTables() {
        Statement queryTables = null;

        try {
            conn = DBConnector.getConnection();
            conn.setAutoCommit(false);

            queryTables = conn.createStatement();

            queryTables.addBatch("CREATE TABLE courses (course_id varchar(30) PRIMARY KEY, coursename varchar(20), " +
                    "coursetype varchar(20))"); // Courses

            queryTables.addBatch("CREATE TABLE classes (class_id varchar(30) PRIMARY KEY, classname varchar(20), " +
                    "classlevel varchar(20), course_id varchar(30) REFERENCES courses ON DELETE CASCADE)"); // Classes

            queryTables.addBatch("CREATE TABLE students (student_id varchar(30) PRIMARY KEY, firstname varchar(20), " +
                    "lastname varchar(20), email varchar(20), telephone varchar(20), class_id varchar(30) REFERENCES classes ON DELETE CASCADE)"); // Students

            queryTables.addBatch("CREATE TABLE teachers (teacher_id varchar(30) PRIMARY KEY, firstname varchar(20), " +
                    "lastname varchar(20), email varchar(20), telephone varchar(20), class_id varchar(30) REFERENCES classes ON DELETE CASCADE)"); // Teachers

            queryTables.executeBatch();
            conn.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (queryTables != null) queryTables.close();
                if (conn != null) conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    public static void deleteAllData() {
        Statement queryDelete = null;

        try {
            conn = DBConnector.getConnection();
            conn.setAutoCommit(false);

            queryDelete = conn.createStatement();

            queryDelete.addBatch("DELETE FROM courses");
            queryDelete.addBatch("DELETE FROM classes");
            queryDelete.addBatch("DELETE FROM teachers");
            queryDelete.addBatch("DELETE FROM students");

            queryDelete.executeBatch();
            conn.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (queryDelete != null) queryDelete.close();
                if (conn != null) conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
