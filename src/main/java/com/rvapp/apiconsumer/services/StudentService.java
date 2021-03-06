package com.rvapp.apiconsumer.services;

import com.rvapp.apiconsumer.domain.ClassGroup;
import com.rvapp.apiconsumer.domain.Student;
import com.rvapp.apiconsumer.repositories.StudentRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Set;

@Singleton
public class StudentService implements GenericService<Student> {

    @Inject private StudentRepository repositoryStudent;

    @Inject
    public StudentService(StudentRepository repositoryStudent) { this.repositoryStudent = repositoryStudent; }

    @Override
    public void insertSingle(Student student) { repositoryStudent.insertEntity(student); }
    public void persistSingle(Student student) { repositoryStudent.persistSingle(student); }
    public void persistMany(Set<Student> students) { repositoryStudent.persistMany(students);}

    @Override
    public void insertList(Set<Student> students) { for (Student student : students) repositoryStudent.insertEntity(student, null); }
    // Called by ClassGroupService
    public void insertList(ClassGroup classGroup, Set<Student> students) { for (Student student : students) repositoryStudent.insertEntity(student, classGroup); }

    public void deleteById(String id) { repositoryStudent.deleteById(id); }

    public void updateById(Student student, String classGroupId) { repositoryStudent.updateById(student, classGroupId); }
    public void updateSet(Set<Student> students, String classGroupId) { repositoryStudent.updateSet(students, classGroupId); }

}
