package com.rvapp.apiconsumer.repositories;

import com.rvapp.apiconsumer.database.DBConnector;
import com.rvapp.apiconsumer.domain.ClassGroup;
import com.rvapp.apiconsumer.domain.Student;
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

    @Override
    public void deleteById(String id) {
        PreparedStatement queryDelete = null;

        try {
            conn = DBConnector.getConnection();
            conn.setAutoCommit(false);

            queryDelete = conn.prepareStatement("DELETE FROM teachers WHERE teacher_id = ?");
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
    public void updateById(Teacher teacher, String classGroupId) {
        PreparedStatement queryUpdate = null;

        try {
            conn = DBConnector.getConnection();
            conn.setAutoCommit(false);

            queryUpdate = conn.prepareStatement("UPDATE students SET firstname = ?, lastname = ?, email = ?, telephone = ?, class_id = ? WHERE class_id = ?");
            queryUpdate.setString(1, teacher.getFirstName());
            queryUpdate.setString(2, teacher.getLastName());
            queryUpdate.setString(3, teacher.getEmail());
            queryUpdate.setString(4, teacher.getTelephone());
            if (classGroupId != null) queryUpdate.setString(5, classGroupId);
            queryUpdate.setString(6, teacher.getId());

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
