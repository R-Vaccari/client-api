package com.rvapp.apiconsumer;

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

import java.sql.SQLException;

public class application {

    public static void main(String[] args) throws SQLException {

        StudentResource resourceStudents = new StudentResource();
        TeacherResource resourceTeachers = new TeacherResource();
        ClassGroupResource resourceClasses = new ClassGroupResource();
        CourseResource resourceCourses = new CourseResource();

        StudentRepository repositoryStudents = new StudentRepository();
        TeacherRepository repositoryTeachers = new TeacherRepository();
        ClassGroupRepository repositoryClasses = new ClassGroupRepository();
        CourseRepository repositoryCourses = new CourseRepository();

        CourseParser parserCources = new CourseParser();
        ClassGroupParser parserClasses = new ClassGroupParser();
        StudentParser parserStudents = new StudentParser();
        TeacherParser parserTeachers = new TeacherParser();

        StudentService serviceStudents = new StudentService(repositoryStudents, parserStudents);
        TeacherService serviceTeachers = new TeacherService(repositoryTeachers, parserTeachers);
        ClassGroupService serviceClasses = new ClassGroupService(serviceStudents, serviceTeachers, repositoryClasses, parserClasses);
        CourseService serviceCourses = new CourseService(serviceClasses, repositoryCourses, parserCources);

        System.out.println(resourceClasses.getWebTarget());
        System.out.println();

    }
}
