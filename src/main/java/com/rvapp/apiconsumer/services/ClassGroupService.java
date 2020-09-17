package com.rvapp.apiconsumer.services;

import com.rvapp.apiconsumer.database.SQLService;
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

    private final StudentService serviceStudents;
    private final TeacherService serviceTeachers;

    @Inject
    public ClassGroupService(StudentService serviceStudents, TeacherService serviceTeachers) {
        this.serviceStudents = serviceStudents;
        this.serviceTeachers = serviceTeachers;
    }

    // Single ClassGroup
    @Override
    public void insertSingle(String responseBody) {
        ClassGroup classGroup = ClassGroupParser.parseClassGroup(responseBody);
        SQLService.insertClassGroup(classGroup, null);

        Teacher teacher = classGroup.getTeacher();
        serviceTeachers.insertSingle(classGroup, teacher);

        Set<Student> students = classGroup.getStudents();
        serviceStudents.insertList(classGroup, students);
    }

    // Multiple ClassGroups
    @Override
    public void insertList(String responseBody) {
        Set<ClassGroup> classGroups = ClassGroupParser.parseClassGroupList(responseBody);
        for (ClassGroup classGroup : classGroups) {
            SQLService.insertClassGroup(classGroup, null);

            Teacher teacher = classGroup.getTeacher();
            serviceTeachers.insertSingle(classGroup, teacher);

            Set<Student> students = classGroup.getStudents();
            serviceStudents.insertList(classGroup, students);
        }
    }

    // Called by CourseResource
    public void insertList(Course course, Set<ClassGroup> classGroups) {
        for (ClassGroup classGroup : classGroups) {
            SQLService.insertClassGroup(classGroup, course);

            Teacher teacher = classGroup.getTeacher();
            serviceTeachers.insertSingle(classGroup, teacher);

            Set<Student> students = classGroup.getStudents();
            serviceStudents.insertList(classGroup, students);
        }
    }
}
