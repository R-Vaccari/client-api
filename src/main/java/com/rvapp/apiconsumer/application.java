package com.rvapp.apiconsumer;

import com.rvapp.apiconsumer.database.DBAdministrator;
import com.rvapp.apiconsumer.domain.Course;
import com.rvapp.apiconsumer.repositories.ClassGroupRepository;
import com.rvapp.apiconsumer.repositories.CourseRepository;
import com.rvapp.apiconsumer.repositories.StudentRepository;
import com.rvapp.apiconsumer.repositories.TeacherRepository;
import com.rvapp.apiconsumer.resources.ClassGroupResource;
import com.rvapp.apiconsumer.resources.CourseResource;
import com.rvapp.apiconsumer.resources.StudentResource;
import com.rvapp.apiconsumer.resources.TeacherResource;
import com.rvapp.apiconsumer.services.ClassGroupService;
import com.rvapp.apiconsumer.services.CourseService;
import com.rvapp.apiconsumer.services.StudentService;
import com.rvapp.apiconsumer.services.TeacherService;
import com.rvapp.apiconsumer.services.util.ClassGroupParser;
import com.rvapp.apiconsumer.services.util.CourseParser;
import com.rvapp.apiconsumer.services.util.StudentParser;
import com.rvapp.apiconsumer.services.util.TeacherParser;

import java.util.Set;

public class application {

    public static void main(String[] args) {

        StudentResource resourceStudents = new StudentResource();
        TeacherResource resourceTeachers = new TeacherResource();
        ClassGroupResource resourceClasses = new ClassGroupResource();
        CourseResource resourceCourses = new CourseResource();

        StudentService serviceStudents = new StudentService(new StudentRepository(), new StudentParser());
        TeacherService serviceTeachers = new TeacherService(new TeacherRepository(), new TeacherParser());
        ClassGroupService serviceClasses = new ClassGroupService(serviceStudents, serviceTeachers, new ClassGroupRepository(), new ClassGroupParser());
        CourseService serviceCourses = new CourseService(serviceClasses, new CourseRepository(), new CourseParser());

        DBAdministrator.deleteAllData();
        Set<Course> courses = serviceCourses.insertList(resourceCourses.getAll());

        for (Course course : courses) System.out.println(course);

    }
}
