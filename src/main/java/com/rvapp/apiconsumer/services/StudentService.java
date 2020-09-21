package com.rvapp.apiconsumer.services;

import com.rvapp.apiconsumer.repositories.StudentRepository;
import com.rvapp.apiconsumer.domain.ClassGroup;
import com.rvapp.apiconsumer.domain.Student;
import com.rvapp.apiconsumer.services.util.StudentParser;

import javax.inject.Singleton;
import java.util.Set;

@Singleton
public class StudentService implements GenericService {

    private final StudentRepository repositoryStudent;

    public StudentService(StudentRepository repositoryStudent) {
        this.repositoryStudent = repositoryStudent;
    }

    @Override
    public void insertSingle(String responseBody) {
        Student student = StudentParser.parseSingle(responseBody);
        repositoryStudent.insertEntity(student);
    }

    @Override
    public void insertList(String responseBody) {
        Set<Student> students = StudentParser.parseStudentsList(responseBody);
        for (Student student : students) repositoryStudent.insertEntity(student, null);
    }

    // Called by ClassGroupService
    public void insertList(ClassGroup classGroup, Set<Student> students) {
        for (Student student : students) repositoryStudent.insertEntity(student, classGroup);
    }

}
