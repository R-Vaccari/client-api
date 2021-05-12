package com.rvapp.apiconsumer.resources;

import com.rvapp.apiconsumer.domain.Student;
import com.rvapp.apiconsumer.exceptions.NotOkHttpStatusException;
import com.rvapp.apiconsumer.resources.util.AuthenticationResource;
import com.rvapp.apiconsumer.services.StudentService;
import com.rvapp.apiconsumer.resources.util.ClientProvider;
import com.rvapp.apiconsumer.services.util.StudentParser;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

@Singleton
public class StudentResource implements GenericResource<Student> {

    private final WebTarget target = ClientProvider.getWebTarget().path("students");
    @Inject private final StudentParser parserStudents;

    public StudentResource(StudentParser parserStudents) { this.parserStudents = parserStudents; }

    @Override
    @Consumes("application/json")
    public Set<Student> getAll() {
        try {
            Response response = target.request(MediaType.APPLICATION_JSON_TYPE)
                    .header("Authorization", "Bearer " + AuthenticationResource.authenticate())
                    .get();

            if (response.getStatus() != 200) throw new NotOkHttpStatusException("Expected status: 200. Response status: " + response.getStatus());
            return parserStudents.parseSet(response.readEntity(String.class));
        } catch (NotOkHttpStatusException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Consumes("application/json")
    public Student getById(String id) {
        try {
            Response response = target.path(id).request(MediaType.APPLICATION_JSON_TYPE)
                    .header("Authorization", "Bearer " + AuthenticationResource.authenticate())
                    .get();

            if (response.getStatus() != 200) throw new NotOkHttpStatusException("Expected status: 200. Response status: " + response.getStatus());
            return parserStudents.parseEntity(response.readEntity(String.class));
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
        return target.request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + AuthenticationResource.authenticate())
                .get();
    }

}
