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
public class StudentResource implements GenericResource {

    private final WebTarget target = ClientProvider.getWebTarget().path("students");
    @Inject private StudentParser parserStudents;

    public StudentResource() {}

    public StudentResource(StudentParser parserStudents) { this.parserStudents = parserStudents; }

    @Override
    @Consumes("application/json")
    public Set<Student> getAll() {
        try {
            Response response = target.request(MediaType.APPLICATION_JSON_TYPE)
                    .header("Authorization", "Bearer " + AuthenticationResource.authenticate())
                    .get();

            int status = response.getStatus();
            if (status != 200) throw new NotOkHttpStatusException("Expected status: 200. Response status: " + status);

            Set<Student> students = parserStudents.parseSet(response.readEntity(String.class));
            return students;
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

            int status = response.getStatus();
            if (status != 200) throw new NotOkHttpStatusException("Expected status: 200. Response status: " + status);

            Student student = parserStudents.parseEntity(response.readEntity(String.class));
            return student;
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
