package com.rvapp.apiconsumer.resources;

import com.rvapp.apiconsumer.domain.Teacher;
import com.rvapp.apiconsumer.exceptions.NotOkHttpStatusException;
import com.rvapp.apiconsumer.resources.util.AuthenticationResource;
import com.rvapp.apiconsumer.resources.util.ClientProvider;
import com.rvapp.apiconsumer.services.util.TeacherParser;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

public class TeacherResource implements GenericResource {

    private final WebTarget target = ClientProvider.getWebTarget().path("teachers");

    @Inject private TeacherParser parserTeachers;

    public TeacherResource() {}
    @Inject
    public TeacherResource(TeacherParser parserTeachers) { this.parserTeachers = parserTeachers; }

    @Override
    @Consumes("application/json")
    public Set<Teacher> getAll() {
        try {
            Response response = target.request(MediaType.APPLICATION_JSON_TYPE)
                    .header("Authorization", "Bearer " + AuthenticationResource.authenticate())
                    .get();

            int status = response.getStatus();
            if (status != 200) throw new NotOkHttpStatusException("Expected status: 200. Response status: " + status);

            Set<Teacher> teachers = parserTeachers.parseSet(response.readEntity(String.class));
            return teachers;
        } catch (NotOkHttpStatusException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Consumes("application/json")
    public Teacher getById(String id) {
        try {
            Response response = target.path(id).request(MediaType.APPLICATION_JSON_TYPE)
                    .header("Authorization", "Bearer " + AuthenticationResource.authenticate())
                    .get();

            int status = response.getStatus();
            if (status != 200) throw new NotOkHttpStatusException("Expected status: 200. Response status: " + status);

            Teacher teacher = parserTeachers.parseEntity(response.readEntity(String.class));
            return teacher;
        } catch (NotOkHttpStatusException e) {
            e.printStackTrace();
        }
            return null;
    }

    @Override
    public WebTarget getWebTarget() {
        return target;
    }

    @Override
    public Response getResponse() {
        Response response = target.request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + AuthenticationResource.authenticate())
                .get();
        return response;
    }

}
