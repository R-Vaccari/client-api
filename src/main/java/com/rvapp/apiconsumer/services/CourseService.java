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

    @Inject private ClassGroupService serviceClasses;
    @Inject private CourseRepository repositoryCourses;
    @Inject private CourseParser parserCourses;

    @Inject
    public CourseService(ClassGroupService serviceClasses, CourseRepository repositoryCourses, CourseParser parserCourses) {
        this.serviceClasses = serviceClasses;
        this.repositoryCourses = repositoryCourses;
        this.parserCourses = parserCourses;
    }

    @Override
    public void insertSingle(String responseBody) {
        Course course = parserCourses.parseEntity(responseBody);
        repositoryCourses.insertEntity(course);

        Set<ClassGroup> classGroupList = course.getClasses();
        serviceClasses.insertList(course, classGroupList);
    }

    @Override
    public void insertList(String responseBody) {
        Set<Course> courses = parserCourses.parseSet(responseBody);
        for (Course course : courses) {
            repositoryCourses.insertEntity(course);
            Set<ClassGroup> classGroupList = course.getClasses();
            serviceClasses.insertList(course, classGroupList);
        }
    }
}
