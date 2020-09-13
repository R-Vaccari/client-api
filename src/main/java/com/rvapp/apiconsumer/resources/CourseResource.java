package com.rvapp.apiconsumer.resources;

import com.rvapp.apiconsumer.database.SQL;
import com.rvapp.apiconsumer.domain.ClassGroup;
import com.rvapp.apiconsumer.domain.Course;
import com.rvapp.apiconsumer.domain.Student;
import com.rvapp.apiconsumer.domain.Teacher;
import com.rvapp.apiconsumer.resources.util.JWTAuthenticator;
import com.rvapp.apiconsumer.util.ClientProvider;
import com.rvapp.apiconsumer.util.Parser;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

public class CourseResource {

    private Client client = ClientProvider.getClient();
    private WebTarget target = ClientProvider.getWebTarget().path("courses");
    private ClassGroupResource resourceClasses;

    @Inject
    public CourseResource(ClassGroupResource resourceClasses) {
        this.resourceClasses = resourceClasses;
    }

    // ------------ GET methods --------------------------------- //

    @Consumes("application/json")
    public String getAllCourses() {
        Response getResponse = target.request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + JWTAuthenticator.authenticate())
                .get();

        String responseBody = getResponse.readEntity(String.class);
        return responseBody;
    }

    @Consumes("application/json")
    public String getCourseById(String id) {
        Response getResponse = target.path(id).request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + JWTAuthenticator.authenticate())
                .get();

        String responseBody = getResponse.readEntity(String.class);
        return responseBody;
    }

    @Consumes("application/json")
    public String getEnglishCourses() {
        Response getResponse = target.path("typesearch").queryParam("type", "ENGLISH").request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + JWTAuthenticator.authenticate())
                .get();

        String responseBody = getResponse.readEntity(String.class);
        return responseBody;
    }

    @Consumes("application/json")
    public String getGermanCourses() {
        Response getResponse = target.path("typesearch").queryParam("type", "GERMAN").request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + JWTAuthenticator.authenticate())
                .get();

        String responseBody = getResponse.readEntity(String.class);
        return responseBody;
    }

    @Consumes("application/json")
    public String getPortugueseCourses() {
        Response getResponse = target.path("typesearch").queryParam("type", "PORTUGUESE").request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + JWTAuthenticator.authenticate())
                .get();

        String responseBody = getResponse.readEntity(String.class);
        return responseBody;
    }

    @Consumes("application/json")
    public String getItalianCourses() {
        Response getResponse = target.path("typesearch").queryParam("type", "ITALIAN").request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + JWTAuthenticator.authenticate())
                .get();

        String responseBody = getResponse.readEntity(String.class);
        return responseBody;
    }

    public String getWebTarget() { return target.getUri().toString(); }

    // ------------ Insert methods --------------------------------- //

    public void insertParsedCourse(String responseBody) {
        Course course = Parser.parseCourse(responseBody);
        SQL.insertCourse(course);

        Set<ClassGroup> classGroupList = course.getClasses();
        resourceClasses.insertParsedClassGroupList(course, classGroupList);
    }
}
