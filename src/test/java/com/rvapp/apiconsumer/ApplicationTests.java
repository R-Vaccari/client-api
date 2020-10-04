package com.rvapp.apiconsumer;

import com.rvapp.apiconsumer.domain.Course;
import com.rvapp.apiconsumer.resources.ClassGroupResource;
import com.rvapp.apiconsumer.resources.CourseResource;
import com.rvapp.apiconsumer.resources.StudentResource;
import com.rvapp.apiconsumer.resources.TeacherResource;
import com.rvapp.apiconsumer.resources.util.AuthenticationResource;
import com.rvapp.apiconsumer.services.util.ClassGroupParser;
import com.rvapp.apiconsumer.services.util.CourseParser;
import com.rvapp.apiconsumer.services.util.StudentParser;
import com.rvapp.apiconsumer.services.util.TeacherParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    }


    @Nested
    @DisplayName("Resource endpoints testing.")
    class IntegrationTests {

    }
}
