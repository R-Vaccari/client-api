package com.rvapp.apiconsumer;

import com.rvapp.apiconsumer.resources.ClassGroupResource;
import com.rvapp.apiconsumer.resources.CourseResource;
import com.rvapp.apiconsumer.resources.StudentResource;
import com.rvapp.apiconsumer.resources.TeacherResource;
import com.rvapp.apiconsumer.resources.util.AuthenticationResource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationTests {

    @Test
    @DisplayName("Authentication resource testing.")
    void authenticationTest() {
        Response response = AuthenticationResource.getResponse();

        assertEquals(200, response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON_TYPE, response.getMediaType());
    }

    @Test
    @DisplayName("Course resource testing.")
    void courseEndpoint() {
        CourseResource resourceCourses = new CourseResource();
        Response response = resourceCourses.getResponse();

        assertEquals(200, response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON_TYPE, response.getMediaType());
    }

    @Test
    @DisplayName("ClassGroup resource testing.")
    void classGroupEndpoint() {
        ClassGroupResource resourceClasses = new ClassGroupResource();
        Response response = resourceClasses.getResponse();

        assertEquals(200, response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON_TYPE, response.getMediaType());
    }

    @Test
    @DisplayName("Student resource testing.")
    void studentEndpoint() {
        StudentResource resourceStudents = new StudentResource();
        Response response = resourceStudents.getResponse();

        assertEquals(200, response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON_TYPE, response.getMediaType());
    }

    @Test
    @DisplayName("Teacher resource testing.")
    void teacherEndpoint() {
        TeacherResource resourceTeachers = new TeacherResource();
        Response response = resourceTeachers.getResponse();

        assertEquals(200, response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON_TYPE, response.getMediaType());
    }
}
