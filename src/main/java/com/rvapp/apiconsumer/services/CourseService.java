package com.rvapp.apiconsumer.services;

import com.rvapp.apiconsumer.database.SQLService;
import com.rvapp.apiconsumer.domain.ClassGroup;
import com.rvapp.apiconsumer.domain.Course;
import com.rvapp.apiconsumer.services.util.CourseParser;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Set;

@Singleton
public class CourseService implements GenericService {

    private final ClassGroupService serviceClasses;

    @Inject
    public CourseService(ClassGroupService serviceClasses) { this.serviceClasses = serviceClasses; }

    @Override
    public void insertSingle(String responseBody) {
        Course course = CourseParser.parseCourse(responseBody);
        SQLService.insertCourse(course);

        Set<ClassGroup> classGroupList = course.getClasses();
        serviceClasses.insertList(course, classGroupList);
    }

    @Override
    public void insertList(String responseBody) {
        Set<Course> courses = CourseParser.parseCourseList(responseBody);
        for (Course course : courses) {
            SQLService.insertCourse(course);
            Set<ClassGroup> classGroupList = course.getClasses();
            serviceClasses.insertList(course, classGroupList);
        }
    }
}
