package com.rvapp.apiconsumer.resources;

import com.rvapp.apiconsumer.database.SQL;
import com.rvapp.apiconsumer.domain.ClassGroup;
import com.rvapp.apiconsumer.domain.Teacher;
import com.rvapp.apiconsumer.resources.util.JWTAuthenticator;
import com.rvapp.apiconsumer.util.ClientProvider;
import com.rvapp.apiconsumer.util.Parser;

import javax.ws.rs.Consumes;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class TeacherResource implements GenericResource {

    private final WebTarget target = ClientProvider.getWebTarget().path("teachers");

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
        Teacher teacher = Parser.parseTeacher(responseBody);
        SQL.insertTeacher(teacher, null);
    }

    @Override
    public void insertList(String responseBody) {
        List<Teacher> listTeachers = Parser.parseTeachersList(responseBody);
        for (Teacher teacher : listTeachers) SQL.insertTeacher(teacher, null);
    }

    // Called by ClassGroupResource
    public void insertList(ClassGroup classGroup, Teacher teacher) {
        SQL.insertTeacher(teacher, classGroup);
    }


}
