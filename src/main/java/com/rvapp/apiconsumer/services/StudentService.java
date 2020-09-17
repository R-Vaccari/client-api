package com.rvapp.apiconsumer.services;

import com.rvapp.apiconsumer.database.SQLService;
import com.rvapp.apiconsumer.domain.ClassGroup;
import com.rvapp.apiconsumer.domain.Student;
import com.rvapp.apiconsumer.util.Parser;

import javax.inject.Singleton;
import java.util.Set;

@Singleton
public class StudentService implements GenericService {

    @Override
    public void insertSingle(String responseBody) {
        Student student = Parser.parseStudent(responseBody);
        SQLService.insertStudent(student, null);
    }

    @Override
    public void insertList(String responseBody) {
        Set<Student> students = Parser.parseStudentsList(responseBody);
        for (Student student : students) SQLService.insertStudent(student, null);
    }

    // Called by ClassGroupService
    public void insertList(ClassGroup classGroup, Set<Student> students) {
        for (Student student : students) SQLService.insertStudent(student, classGroup);
    }

}
