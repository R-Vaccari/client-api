package com.rvapp.apiconsumer.repositories;

import com.rvapp.apiconsumer.database.DBConnector;
import com.rvapp.apiconsumer.domain.ClassGroup;
import com.rvapp.apiconsumer.domain.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TeacherRepository implements GenericRepository<Teacher> {

    Connection conn = null;

    @Override
    public void insertEntity(Teacher teacher) {
        PreparedStatement queryInsert = null;

        try {
            conn = DBConnector.getConnection();
            conn.setAutoCommit(false);

            queryInsert = conn.prepareStatement("INSERT INTO teachers(teacher_id, firstname, lastname, email, telephone) VALUES (?, ?, ?, ?, ?)");
            queryInsert.setString(1, teacher.getId());
            queryInsert.setString(2, teacher.getFirstName());
            queryInsert.setString(3, teacher.getLastName());
            queryInsert.setString(4, teacher.getEmail());
            queryInsert.setString(5, teacher.getTelephone());

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

    public void insertEntity(Teacher teacher, ClassGroup classGroup) {
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
}
