package com.rvapp.apiconsumer.services;

import com.rvapp.apiconsumer.repositories.StudentRepository;
import com.rvapp.apiconsumer.domain.ClassGroup;
import com.rvapp.apiconsumer.domain.Student;
import com.rvapp.apiconsumer.services.util.StudentParser;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Set;

@Singleton
public class StudentService implements GenericService<Student> {

    @Inject private StudentRepository repositoryStudent;
    @Inject private StudentParser parserStudents;

    @Inject
    public StudentService(StudentRepository repositoryStudent, StudentParser parserStudents) {
        this.repositoryStudent = repositoryStudent;
        this.parserStudents = parserStudents;
    }

    @Override
    public Student insertSingle(String responseBody) {
        Student student = parserStudents.parseEntity(responseBody);
        repositoryStudent.insertEntity(student);
        return student;
    }

    @Override
    public Set<Student> insertList(String responseBody) {
        Set<Student> students = parserStudents.parseSet(responseBody);
        for (Student student : students) repositoryStudent.insertEntity(student, null);
        return students;
    }

    // Called by ClassGroupService
    public void insertList(ClassGroup classGroup, Set<Student> students) {
        for (Student student : students) repositoryStudent.insertEntity(student, classGroup);
    }

    public void deleteById(String id) { repositoryStudent.deleteById(id); }

    public void updateById(Student student, String classGroupId) { repositoryStudent.updateById(student, classGroupId); }

    public void updateSet(Set<Student> students, String classGroupId) { repositoryStudent.updateSet(students, classGroupId); }

}
