package com.rvapp.apiconsumer.resources;

import com.rvapp.apiconsumer.database.SQLService;
import com.rvapp.apiconsumer.domain.ClassGroup;
import com.rvapp.apiconsumer.domain.Student;
import com.rvapp.apiconsumer.resources.util.JWTAuthenticator;
import com.rvapp.apiconsumer.util.ClientProvider;
import com.rvapp.apiconsumer.util.Parser;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

@Singleton
public class StudentResource implements GenericResource {

    private final WebTarget target = ClientProvider.getWebTarget().path("students");

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

    @Override
    public String getWebTarget() {
        return target.getUri().toString();
    }

    // ------------ Insert methods --------------------------------- //

    @Override
    public void insertSingle(String responseBody) {
        Student student = Parser.parseStudent(responseBody);
        SQLService.insertStudent(student, null);
    }

    @Override
    public void insertList(String responseBody) {
        Set<Student> students = Parser.parseStudentsList(responseBody);
        for (Student student : students) SQLService.insertStudent(student, null);
    }

    // Called by ClassGroupResource
    public void insertList(ClassGroup classGroup, Set<Student> students) {
        for (Student student : students) SQLService.insertStudent(student, classGroup);
    }
}
