package com.rvapp.apiconsumer.repositories;

import com.rvapp.apiconsumer.database.DBConnector;
import com.rvapp.apiconsumer.domain.ClassGroup;
import com.rvapp.apiconsumer.domain.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClassGroupRepository implements GenericRepository<ClassGroup> {

    Connection conn = null;

    @Override
    public void insertEntity(ClassGroup classGroup) {
        PreparedStatement queryInsert = null;

        try {
            conn = DBConnector.getConnection();
            conn.setAutoCommit(false);

            queryInsert = conn.prepareStatement("INSERT INTO classes(class_id, classname, classlevel) VALUES (?, ?, ?)");
            queryInsert.setString(1, classGroup.getId());
            queryInsert.setString(2, classGroup.getClassName());
            queryInsert.setString(3, classGroup.getClassLevel());

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

    public void insertEntity(ClassGroup classGroup, Course course) {
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

    @Override
    public void deleteById(String id) {
        PreparedStatement queryDelete = null;

        try {
            conn = DBConnector.getConnection();
            conn.setAutoCommit(false);

            queryDelete = conn.prepareStatement("DELETE FROM classes WHERE class_id = ?");
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
    public void updateById(ClassGroup classGroup, String courseId) {
        PreparedStatement queryUpdate = null;

        try {
            conn = DBConnector.getConnection();
            conn.setAutoCommit(false);

            queryUpdate = conn.prepareStatement("UPDATE classes SET classname = ?, classlevel = ?, course_id = ? WHERE class_id = ?");
            queryUpdate.setString(1, classGroup.getClassName());
            queryUpdate.setString(2, classGroup.getClassLevel());
            if (courseId != null) queryUpdate.setString(3, courseId);
            queryUpdate.setString(4, classGroup.getId());

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
