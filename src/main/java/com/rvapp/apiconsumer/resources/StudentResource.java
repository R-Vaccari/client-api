package com.rvapp.apiconsumer.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rvapp.apiconsumer.database.SQL;
import com.rvapp.apiconsumer.domain.Student;
import com.rvapp.apiconsumer.util.ClientProvider;
import com.rvapp.apiconsumer.util.Parser;

import javax.ws.rs.Consumes;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

public class StudentResource {

    private Client client = ClientProvider.getClient();
    private WebTarget target = ClientProvider.getWebTarget().path("students");

    @Consumes("application/json")
    public String getAllStudents() {
        Response getResponse = target.request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + JWTAuthenticator.authenticate())
                .get();

        String responseBody = getResponse.readEntity(String.class);
        return responseBody;
    }

    @Consumes("application/json")
    public String getStudentById(String id) {
        Response getResponse = target.path(id).request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + JWTAuthenticator.authenticate())
                .get();

        String responseBody = getResponse.readEntity(String.class);
        return responseBody;
    }

    public void insertParsedStudents(String responseBody) {
        Set<Student> listStudents = Parser.parseStudentsList(responseBody);
        for (Student student : listStudents) SQL.insertStudent(student, null);
    }

    public String getWebTarget() {
        return target.getUri().toString();
    }
}
