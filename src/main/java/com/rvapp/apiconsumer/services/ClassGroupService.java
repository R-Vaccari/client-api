package com.rvapp.apiconsumer.services;

import com.rvapp.apiconsumer.repositories.ClassGroupRepository;
import com.rvapp.apiconsumer.domain.ClassGroup;
import com.rvapp.apiconsumer.domain.Course;
import com.rvapp.apiconsumer.domain.Student;
import com.rvapp.apiconsumer.domain.Teacher;
import com.rvapp.apiconsumer.services.util.ClassGroupParser;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Set;

@Singleton
public class ClassGroupService implements GenericService {

    @Inject private StudentService serviceStudents;
    @Inject private TeacherService serviceTeachers;
    @Inject private ClassGroupRepository repositoryClasses;
    @Inject private ClassGroupParser parserClasses;

    @Inject
    public ClassGroupService(StudentService serviceStudents, TeacherService serviceTeachers, ClassGroupRepository repositoryClasses, ClassGroupParser parserClasses) {
        this.serviceStudents = serviceStudents;
        this.serviceTeachers = serviceTeachers;
        this.repositoryClasses = repositoryClasses;
        this.parserClasses = parserClasses;
    }

    // Single ClassGroup
    @Override
    public void insertSingle(String responseBody) {
        ClassGroup classGroup = parserClasses.parseEntity(responseBody);
        repositoryClasses.insertEntity(classGroup, null);

        Teacher teacher = classGroup.getTeacher();
        serviceTeachers.insertSingle(classGroup, teacher);

        Set<Student> students = classGroup.getStudents();
        serviceStudents.insertList(classGroup, students);
    }

    // Multiple ClassGroups
    @Override
    public void insertList(String responseBody) {
        Set<ClassGroup> classGroups = parserClasses.parseSet(responseBody);
        for (ClassGroup classGroup : classGroups) {
            repositoryClasses.insertEntity(classGroup, null);

            Teacher teacher = classGroup.getTeacher();
            serviceTeachers.insertSingle(classGroup, teacher);

            Set<Student> students = classGroup.getStudents();
            serviceStudents.insertList(classGroup, students);
        }
    }

    // Called by CourseResource
    public void insertList(Course course, Set<ClassGroup> classGroups) {
        for (ClassGroup classGroup : classGroups) {
            repositoryClasses.insertEntity(classGroup, course);

            Teacher teacher = classGroup.getTeacher();
            serviceTeachers.insertSingle(classGroup, teacher);

            Set<Student> students = classGroup.getStudents();
            serviceStudents.insertList(classGroup, students);
        }
    }
}
