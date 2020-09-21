package com.rvapp.apiconsumer.services;

import com.rvapp.apiconsumer.repositories.CourseRepository;
import com.rvapp.apiconsumer.domain.ClassGroup;
import com.rvapp.apiconsumer.domain.Course;
import com.rvapp.apiconsumer.services.util.CourseParser;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Set;

@Singleton
public class CourseService implements GenericService {

    private final ClassGroupService serviceClasses;
    private final CourseRepository repositoryCourses;

    @Inject
    public CourseService(ClassGroupService serviceClasses, CourseRepository repositoryCourses) {
        this.serviceClasses = serviceClasses;
        this.repositoryCourses = repositoryCourses;
    }

    @Override
    public void insertSingle(String responseBody) {
        Course course = CourseParser.parseCourse(responseBody);
        repositoryCourses.insertEntity(course);

        Set<ClassGroup> classGroupList = course.getClasses();
        serviceClasses.insertList(course, classGroupList);
    }

    @Override
    public void insertList(String responseBody) {
        Set<Course> courses = CourseParser.parseCourseList(responseBody);
        for (Course course : courses) {
            repositoryCourses.insertEntity(course);
            Set<ClassGroup> classGroupList = course.getClasses();
            serviceClasses.insertList(course, classGroupList);
        }
    }
}
