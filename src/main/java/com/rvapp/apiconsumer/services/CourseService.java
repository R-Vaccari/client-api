package com.rvapp.apiconsumer.services;

import com.rvapp.apiconsumer.domain.ClassGroup;
import com.rvapp.apiconsumer.domain.Course;
import com.rvapp.apiconsumer.repositories.CourseRepository;
import com.rvapp.apiconsumer.services.util.CourseParser;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Set;

@Singleton
public class CourseService implements GenericService<Course> {

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
    public Course insertSingle(String responseBody) {
        Course course = parserCourses.parseEntity(responseBody);
        repositoryCourses.insertEntity(course);

        Set<ClassGroup> classGroupList = course.getClasses();
        serviceClasses.insertList(course, classGroupList);
        return course;
    }

    @Override
    public Set<Course> insertList(String responseBody) {
        Set<Course> courses = parserCourses.parseSet(responseBody);
        for (Course course : courses) {
            repositoryCourses.insertEntity(course);
            Set<ClassGroup> classGroupList = course.getClasses();
            serviceClasses.insertList(course, classGroupList);
        }
        return courses;
    }

    public void deleteById(String id) { repositoryCourses.deleteById(id); }

    public void updateById(Course course, String filler) { repositoryCourses.updateById(course, null); }
}
