package com.rvapp.apiconsumer.services;

import com.rvapp.apiconsumer.domain.ClassGroup;
import com.rvapp.apiconsumer.domain.Course;
import com.rvapp.apiconsumer.repositories.CourseRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Set;

@Singleton
public class CourseService implements GenericService<Course> {

    @Inject private ClassGroupService serviceClasses;
    @Inject private CourseRepository repositoryCourses;

    @Inject
    public CourseService(ClassGroupService serviceClasses, CourseRepository repositoryCourses) {
        this.serviceClasses = serviceClasses;
        this.repositoryCourses = repositoryCourses;
    }

    @Override
    public void insertSingle(Course course) {
        repositoryCourses.insertEntity(course);

        Set<ClassGroup> classGroupList = course.getClasses();
        serviceClasses.insertList(course, classGroupList);
    }

    @Override
    public void insertList(Set<Course> courses) {
        for (Course course : courses) {
            repositoryCourses.insertEntity(course);
            Set<ClassGroup> classGroupList = course.getClasses();
            serviceClasses.insertList(course, classGroupList);
        }
    }

    public void deleteById(String id) { repositoryCourses.deleteById(id); }

    public void updateById(Course course, String filler) { repositoryCourses.updateById(course, null); }
}
