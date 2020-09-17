package com.rvapp.apiconsumer.database;

import com.rvapp.apiconsumer.domain.ClassGroup;
import com.rvapp.apiconsumer.domain.Course;
import com.rvapp.apiconsumer.domain.Student;
import com.rvapp.apiconsumer.domain.Teacher;

import java.sql.*;

public final class SQLService {

    static Connection conn = null;

    public static void checkTables() {
        ResultSet tables = null;

        try {
            conn = DBConnector.getConnection();
            DatabaseMetaData md = conn.getMetaData();
            tables = md.getTables(null, null, "%", null);
            if (!tables.next()) SQLService.createTables();
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

    public static void insertCourse(Course course) {
        PreparedStatement queryInsert = null;

        try {
            conn = DBConnector.getConnection();
            conn.setAutoCommit(false);

            queryInsert = conn.prepareStatement("INSERT INTO courses(course_id, coursename, coursetype) VALUES (?, ?, ?)");
            queryInsert.setString(1, course.getId());
            queryInsert.setString(2, course.getCourseName());
            queryInsert.setString(3, course.getType());

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

    public static void insertClassGroup(ClassGroup classGroup, Course course) {
        PreparedStatement queryInsert = null;

        try {
            conn = DBConnector.getConnection();
            conn.setAutoCommit(false);

            queryInsert = conn.prepareStatement("INSERT INTO classes(class_id, classname, classlevel, course_id) VALUES (?, ?, ?, ?)");
            queryInsert.setString(1, classGroup.getId());
            queryInsert.setString(2, classGroup.getClassName());
            queryInsert.setString(3, classGroup.getClassLevel());
            if (course != null) queryInsert.setString(4, course.getId());
            else queryInsert.setString(4, null);

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

    public static void insertTeacher(Teacher teacher, ClassGroup classGroup) {
        PreparedStatement queryInsert = null;

        try {
            conn = DBConnector.getConnection();
            conn.setAutoCommit(false);

            queryInsert = conn.prepareStatement("INSERT INTO teachers(teacher_id, firstname, lastname, email, telephone, class_id) VALUES (?, ?, ?, ?, ?, ?)");
            queryInsert.setString(1, teacher.getId());
            queryInsert.setString(2, teacher.getFirstName());
            queryInsert.setString(3, teacher.getLastName());
            queryInsert.setString(4, teacher.getEmail());
            queryInsert.setString(5, teacher.getTelephone());
            if (classGroup != null) queryInsert.setString(6, classGroup.getId());
            else queryInsert.setString(6, null);

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

    public static void insertStudent(Student student, ClassGroup classGroup) {
        PreparedStatement queryInsert = null;

        try {
            conn = DBConnector.getConnection();
            conn.setAutoCommit(false);

            queryInsert = conn.prepareStatement("INSERT INTO students(student_id, firstname, lastname, email, telephone, class_id) VALUES (?, ?, ?, ?, ?, ?)");
            queryInsert.setString(1, student.getId());
            queryInsert.setString(2, student.getFirstName());
            queryInsert.setString(3, student.getLastName());
            queryInsert.setString(4, student.getEmail());
            queryInsert.setString(5, student.getTelephone());
            if (classGroup != null) queryInsert.setString(6, classGroup.getId());
            else queryInsert.setString(6, null);

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
