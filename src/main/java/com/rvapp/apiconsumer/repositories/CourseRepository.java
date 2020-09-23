package com.rvapp.apiconsumer.repositories;

import com.rvapp.apiconsumer.database.DBConnector;
import com.rvapp.apiconsumer.domain.ClassGroup;
import com.rvapp.apiconsumer.domain.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CourseRepository implements GenericRepository<Course> {

    Connection conn = null;

    @Override
    public void insertEntity(Course course) {
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

    @Override
    public void deleteById(String id) {
        PreparedStatement queryDelete = null;

        try {
            conn = DBConnector.getConnection();
            conn.setAutoCommit(false);

            queryDelete = conn.prepareStatement("DELETE FROM courses WHERE course_id = ?");
            queryDelete.setString(1, id);

            queryDelete.executeUpdate();
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

    @Override
    public void updateById(Course course, String filler) {
        PreparedStatement queryUpdate = null;

        try {
            conn = DBConnector.getConnection();
            conn.setAutoCommit(false);

            queryUpdate = conn.prepareStatement("UPDATE courses SET coursename = ?, coursetype = ? WHERE class_id = ?");
            queryUpdate.setString(1, course.getCourseName());
            queryUpdate.setString(2, course.getType());
            queryUpdate.setString(3, course.getId());

            queryUpdate.executeUpdate();
            conn.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (queryUpdate != null) queryUpdate.close();
                if (conn != null) conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
