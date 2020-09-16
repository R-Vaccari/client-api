package com.rvapp.apiconsumer.resources;

import com.rvapp.apiconsumer.database.SQLService;
import com.rvapp.apiconsumer.domain.ClassGroup;
import com.rvapp.apiconsumer.domain.Course;
import com.rvapp.apiconsumer.resources.util.JWTAuthenticator;
import com.rvapp.apiconsumer.util.ClientProvider;
import com.rvapp.apiconsumer.util.Parser;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

public class CourseResource implements GenericResource {

    private final WebTarget target = ClientProvider.getWebTarget().path("courses");
    private final ClassGroupResource resourceClasses;

    @Inject
    public CourseResource(ClassGroupResource resourceClasses) {
        this.resourceClasses = resourceClasses;
    }

    // ------------ GET methods --------------------------------- //

    @Override
    @Consumes("application/json")
    public String getAll() {
        Response getResponse = target.request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + JWTAuthenticator.authenticate())
                .get();

        return getResponse.readEntity(String.class);
    }

    @Override
    @Consumes("application/json")
    public String getById(String id) {
        Response getResponse = target.path(id).request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + JWTAuthenticator.authenticate())
                .get();

        return getResponse.readEntity(String.class);
    }

    @Consumes("application/json")
    public String getEnglishCourses() {
        Response getResponse = target.path("typesearch").queryParam("type", "ENGLISH").request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + JWTAuthenticator.authenticate())
                .get();

        return getResponse.readEntity(String.class);
    }

    @Consumes("application/json")
    public String getGermanCourses() {
        Response getResponse = target.path("typesearch").queryParam("type", "GERMAN").request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + JWTAuthenticator.authenticate())
                .get();

        return getResponse.readEntity(String.class);
    }

    @Consumes("application/json")
    public String getPortugueseCourses() {
        Response getResponse = target.path("typesearch").queryParam("type", "PORTUGUESE").request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + JWTAuthenticator.authenticate())
                .get();

        return getResponse.readEntity(String.class);
    }

    @Consumes("application/json")
    public String getItalianCourses() {
        Response getResponse = target.path("typesearch").queryParam("type", "ITALIAN").request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + JWTAuthenticator.authenticate())
                .get();

        return getResponse.readEntity(String.class);
    }

    @Override
    public String getWebTarget() { return target.getUri().toString(); }

    // ------------ Insert methods --------------------------------- //

    @Override
    public void insertSingle(String responseBody) {
        Course course = Parser.parseCourse(responseBody);
        SQLService.insertCourse(course);

        Set<ClassGroup> classGroupList = course.getClasses();
        resourceClasses.insertList(course, classGroupList);
    }

    @Override
    public void insertList(String responseBody) {
        Set<Course> courses = Parser.parseCourseList(responseBody);
        for (Course course : courses) {
            SQLService.insertCourse(course);
            Set<ClassGroup> classGroupList = course.getClasses();
            resourceClasses.insertList(course, classGroupList);
        }
    }
}
