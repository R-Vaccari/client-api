package com.rvapp.apiconsumer.resources;

import com.rvapp.apiconsumer.database.SQL;
import com.rvapp.apiconsumer.domain.Student;
import com.rvapp.apiconsumer.domain.Teacher;
import com.rvapp.apiconsumer.util.ClientProvider;
import com.rvapp.apiconsumer.util.Parser;

import javax.ws.rs.Consumes;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

public class TeacherResource {

    private Client client = ClientProvider.getClient();
    private WebTarget target = ClientProvider.getWebTarget().path("teachers");

    @Consumes("application/json")
    public String getAllTeachers() {
        Response getResponse = target.request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + JWTAuthenticator.authenticate())
                .get();

        String responseBody = getResponse.readEntity(String.class);
        return responseBody;
    }

    @Consumes("application/json")
    public String getTeacherById(String id) {
        Response getResponse = target.path(id).request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + JWTAuthenticator.authenticate())
                .get();

        String responseBody = getResponse.readEntity(String.class);
        return responseBody;
    }

    public void insertParsedTeachers(String responseBody) {
        List<Teacher> listTeachers = Parser.parseTeachersList(responseBody);
        for (Teacher teacher : listTeachers) SQL.insertTeacher(teacher, null);
    }

    public String getWebTarget() {
        return target.getUri().toString();
    }
}
