package com.rvapp.apiconsumer;

import com.rvapp.apiconsumer.domain.ClassGroup;
import com.rvapp.apiconsumer.domain.Course;
import com.rvapp.apiconsumer.exceptions.NotOkHttpStatusException;
import com.rvapp.apiconsumer.repositories.ClassGroupRepository;
import com.rvapp.apiconsumer.repositories.CourseRepository;
import com.rvapp.apiconsumer.repositories.StudentRepository;
import com.rvapp.apiconsumer.repositories.TeacherRepository;
import com.rvapp.apiconsumer.resources.ClassGroupResource;
import com.rvapp.apiconsumer.resources.CourseResource;
import com.rvapp.apiconsumer.resources.StudentResource;
import com.rvapp.apiconsumer.resources.TeacherResource;
import com.rvapp.apiconsumer.resources.util.AuthenticationResource;
import com.rvapp.apiconsumer.services.ClassGroupService;
import com.rvapp.apiconsumer.services.CourseService;
import com.rvapp.apiconsumer.services.StudentService;
import com.rvapp.apiconsumer.services.TeacherService;
import com.rvapp.apiconsumer.services.util.ClassGroupParser;
import com.rvapp.apiconsumer.services.util.CourseParser;
import com.rvapp.apiconsumer.services.util.StudentParser;
import com.rvapp.apiconsumer.services.util.TeacherParser;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationTests {

    @Nested
    @DisplayName("Resource response testing.")
    class ResponseUnitTests {

        StudentResource resourceStudents = new StudentResource(new StudentParser());
        TeacherResource resourceTeachers = new TeacherResource(new TeacherParser());
        ClassGroupResource resourceClasses = new ClassGroupResource(new ClassGroupParser());
        CourseResource resourceCourses = new CourseResource(new CourseParser());

        @Test
        @DisplayName("Authentication resource testing.")
        void authenticationResponseTest() {
            Response response = AuthenticationResource.getResponse();

            assertEquals(200, response.getStatus());
            assertEquals(MediaType.APPLICATION_JSON_TYPE, response.getMediaType());
        }

        @Test
        @DisplayName("Course resource testing.")
        void courseResponseTest() {
            Response response = resourceCourses.getResponse();

            assertEquals(200, response.getStatus());
            assertEquals(MediaType.APPLICATION_JSON_TYPE, response.getMediaType());
        }

        @Test
        @DisplayName("ClassGroup resource testing.")
        void classGroupResponseTest() {
            Response response = resourceClasses.getResponse();

            assertEquals(200, response.getStatus());
            assertEquals(MediaType.APPLICATION_JSON_TYPE, response.getMediaType());
        }

        @Test
        @DisplayName("Student resource testing.")
        void studentResponseTest() {
            Response response = resourceStudents.getResponse();

            assertEquals(200, response.getStatus());
            assertEquals(MediaType.APPLICATION_JSON_TYPE, response.getMediaType());
        }

        @Test
        @DisplayName("Teacher resource testing.")
        void teacherResponseTest() {
            Response response = resourceTeachers.getResponse();

            assertEquals(200, response.getStatus());
            assertEquals(MediaType.APPLICATION_JSON_TYPE, response.getMediaType());
        }

        @Test
        @DisplayName("GetByType Course testing.")
        void courseGetEnglishTest() {
            Course course = resourceCourses.getByType("english");
            assertEquals(course.getType(), "ENGLISH");
        }

        @Test
        @DisplayName("GetByType Course testing.")
        @Disabled // Remove try/catch in original method for test to succeed
        void exceptionTest() {
            Exception exception = assertThrows(NotOkHttpStatusException.class, () -> resourceCourses.getByType("non-existent"));
            assertEquals("Expected status: 200. Response status: 404", exception.getMessage());
        }
    }


    @Nested
    @DisplayName("Testing using multiple Resources / Services.")
    class IntegrationTests {

        StudentResource resourceStudents = new StudentResource(new StudentParser());
        TeacherResource resourceTeachers = new TeacherResource(new TeacherParser());
        ClassGroupResource resourceClasses = new ClassGroupResource(new ClassGroupParser());
        CourseResource resourceCourses = new CourseResource(new CourseParser());

        StudentService serviceStudents = new StudentService(new StudentRepository());
        TeacherService serviceTeachers = new TeacherService(new TeacherRepository());
        ClassGroupService serviceClasses = new ClassGroupService(serviceStudents, serviceTeachers, new ClassGroupRepository());
        CourseService serviceCourses = new CourseService(serviceClasses, new CourseRepository());

        @Test
        @DisplayName("ClassGroup equality testing.")
        void classGroupEqualityTest() {
            Course course = resourceCourses.getByType("english");

            // These are the only classGroups in the API, and thus must be same as those retrieved by CourseResource
            Set<ClassGroup> classGroups = resourceClasses.getAll();
            Set<ClassGroup> courseClassGroups = course.getClasses();

            assertEquals(classGroups, courseClassGroups);
        }

        @Test
        @DisplayName("ClassGroup content testing.")
        void classGroupContentTest() {
            ClassGroup classGroup1 = resourceClasses.getByLevel("business");
            ClassGroup classGroup2 = resourceClasses.getByLevel("intermediate");

            assertEquals(classGroup1.getStudents().size(), classGroup2.getStudents().size());
            assertNotEquals(classGroup1.getTeacher(), classGroup2.getTeacher());
        }
    }
}
