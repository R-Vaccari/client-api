package com.rvapp.apiconsumer;

import com.rvapp.apiconsumer.database.DBAdministrator;
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

public class application {

    public static void main(String[] args) {

        StudentResource resourceStudents = new StudentResource(new StudentParser());
        TeacherResource resourceTeachers = new TeacherResource(new TeacherParser());
        ClassGroupResource resourceClasses = new ClassGroupResource(new ClassGroupParser());
        CourseResource resourceCourses = new CourseResource(new CourseParser());

        StudentService serviceStudents = new StudentService(new StudentRepository());
        TeacherService serviceTeachers = new TeacherService(new TeacherRepository());
        ClassGroupService serviceClasses = new ClassGroupService(serviceStudents, serviceTeachers, new ClassGroupRepository());
        CourseService serviceCourses = new CourseService(serviceClasses, new CourseRepository());

        DBAdministrator.deleteAllData();
        serviceStudents.persistMany(resourceStudents.getAll());

    }
}
