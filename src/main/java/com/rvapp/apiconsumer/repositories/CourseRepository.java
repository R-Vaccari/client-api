package com.rvapp.apiconsumer.repositories;

import com.rvapp.apiconsumer.database.DBConnector;
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
}
