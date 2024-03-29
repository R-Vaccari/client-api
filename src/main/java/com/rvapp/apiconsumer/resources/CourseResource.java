package com.rvapp.apiconsumer.resources;

import com.rvapp.apiconsumer.domain.Course;
import com.rvapp.apiconsumer.exceptions.NotOkHttpStatusException;
import com.rvapp.apiconsumer.resources.util.AuthenticationResource;
import com.rvapp.apiconsumer.resources.util.ClientProvider;
import com.rvapp.apiconsumer.services.util.CourseParser;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

public class CourseResource implements GenericResource<Course> {

    private final WebTarget target = ClientProvider.getWebTarget().path("courses");
    @Inject private final CourseParser parserCourses;

    public CourseResource(CourseParser parserCourses) { this.parserCourses = parserCourses; }

    @Override
    @Consumes("application/json")
    public Set<Course> getAll() {
        try {
            Response response = target.request(MediaType.APPLICATION_JSON_TYPE)
                    .header("Authorization", "Bearer " + AuthenticationResource.authenticate())
                    .get();

            if (response.getStatus() != 200) throw new NotOkHttpStatusException("Expected status: 200. Response status: " + response.getStatus());
            return parserCourses.parseSet(response.readEntity(String.class));
        } catch (NotOkHttpStatusException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Consumes("application/json")
    public Course getById(String id) {
        try {
            Response response = target.path(id).request(MediaType.APPLICATION_JSON_TYPE)
                    .header("Authorization", "Bearer " + AuthenticationResource.authenticate())
                    .get();

            if (response.getStatus() != 200) throw new NotOkHttpStatusException("Expected status: 200. Response status: " + response.getStatus());
            return parserCourses.parseEntity(response.readEntity(String.class));
        } catch (NotOkHttpStatusException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Consumes("application/json")
    public Course getByType(String type) {
        try {
            Response response = target.path("typesearch").queryParam("type", type.toUpperCase()).request(MediaType.APPLICATION_JSON_TYPE)
                    .header("Authorization", "Bearer " + AuthenticationResource.authenticate())
                    .get();

            if (response.getStatus() != 200) throw new NotOkHttpStatusException("Expected status: 200. Response status: " + response.getStatus());
            return parserCourses.parseEntity(response.readEntity(String.class));
        } catch (NotOkHttpStatusException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public WebTarget getWebTarget() { return target; }

    @Override
    public Response getResponse() {
        return target.request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + AuthenticationResource.authenticate())
                .get();
    }

}
