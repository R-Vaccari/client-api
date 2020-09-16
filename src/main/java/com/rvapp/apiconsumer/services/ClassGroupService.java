package com.rvapp.apiconsumer.services;

import com.rvapp.apiconsumer.database.SQLService;
import com.rvapp.apiconsumer.domain.ClassGroup;
import com.rvapp.apiconsumer.domain.Course;
import com.rvapp.apiconsumer.domain.Student;
import com.rvapp.apiconsumer.domain.Teacher;
import com.rvapp.apiconsumer.util.Parser;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Set;

@Singleton
public class ClassGroupService implements GenericService {

    private final StudentService serviceStudent;
    private final TeacherService serviceTeacher;

    @Inject
    public ClassGroupService(StudentService serviceStudent, TeacherService serviceTeacher ) {
        this.serviceStudent = serviceStudent;
        this.serviceTeacher = serviceTeacher;
    }

    // Single ClassGroup
    @Override
    public void insertSingle(String responseBody) {
        ClassGroup classGroup = Parser.parseClassGroup(responseBody);
        SQLService.insertClassGroup(classGroup, null);

        Teacher teacher = classGroup.getTeacher();
        serviceTeacher.insertList(classGroup, teacher);

        Set<Student> students = classGroup.getStudents();
        serviceStudent.insertList(classGroup, students);
    }

    // Multiple ClassGroups
    @Override
    public void insertList(String responseBody) {
        Set<ClassGroup> classGroups = Parser.parseClassGroupList(responseBody);
        for (ClassGroup classGroup : classGroups) {
            SQLService.insertClassGroup(classGroup, null);

            Teacher teacher = classGroup.getTeacher();
            serviceTeacher.insertList(classGroup, teacher);

            Set<Student> students = classGroup.getStudents();
            serviceStudent.insertList(classGroup, students);
        }
    }

    // Called by CourseResource
    public void insertList(Course course, Set<ClassGroup> classGroups) {
        for (ClassGroup classGroup : classGroups) {
            SQLService.insertClassGroup(classGroup, course);

            Teacher teacher = classGroup.getTeacher();
            serviceTeacher.insertList(classGroup, teacher);

            Set<Student> students = classGroup.getStudents();
            serviceStudent.insertList(classGroup, students);
        }
    }
}
