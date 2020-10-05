package com.rvapp.apiconsumer.repositories;

import com.rvapp.apiconsumer.database.DBConnector;
import com.rvapp.apiconsumer.domain.ClassGroup;
import com.rvapp.apiconsumer.domain.Student;


import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

public class StudentRepository implements GenericRepository<Student> {

    Connection conn = null;
    EntityManagerFactory emFactory = null;

    protected void setUp() {
       emFactory = Persistence.createEntityManagerFactory("apiconsumer");
    }

    public void persistSingle(Student student) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
        em.close();
    }

    public void persistMany(Set<Student> students) {
        setUp();
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        for (Student student : students) em.persist(student);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void insertEntity(Student student) {
        PreparedStatement queryInsert = null;

        try {
            conn = DBConnector.getConnection();
            conn.setAutoCommit(false);

            queryInsert = conn.prepareStatement("INSERT INTO students(student_id, firstname, lastname, email, telephone) VALUES (?, ?, ?, ?, ?)");
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

    public void insertEntity(Student student, ClassGroup classGroup) {
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

    @Override
    public void deleteById(String id) {
        PreparedStatement queryDelete = null;

        try {
            conn = DBConnector.getConnection();
            conn.setAutoCommit(false);

            queryDelete = conn.prepareStatement("DELETE FROM students WHERE student_id = ?");
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
    public void updateById(Student student, String classGroupId) {
        PreparedStatement queryUpdate = null;

        try {
            conn = DBConnector.getConnection();
            conn.setAutoCommit(false);

            queryUpdate = conn.prepareStatement("UPDATE students SET firstname = ?, lastname = ?, email = ?, telephone = ?, class_id = ? WHERE class_id = ?");
            queryUpdate.setString(1, student.getFirstName());
            queryUpdate.setString(2, student.getLastName());
            queryUpdate.setString(3, student.getEmail());
            queryUpdate.setString(4, student.getTelephone());
            if (classGroupId != null) queryUpdate.setString(5, classGroupId);
            queryUpdate.setString(6, student.getId());

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

    public void updateSet(Set<Student> students, String classGroupId) {
        PreparedStatement queryUpdate = null;

        try {
            conn = DBConnector.getConnection();
            conn.setAutoCommit(false);

            for (Student student : students) {
                queryUpdate = conn.prepareStatement("UPDATE students SET firstname = ?, lastname = ?, email = ?, telephone = ?, class_id = ? WHERE class_id = ?");
                queryUpdate.setString(1, student.getFirstName());
                queryUpdate.setString(2, student.getLastName());
                queryUpdate.setString(3, student.getEmail());
                queryUpdate.setString(4, student.getTelephone());
                if (classGroupId != null) queryUpdate.setString(5, classGroupId);
                queryUpdate.setString(6, student.getId());
            }

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
