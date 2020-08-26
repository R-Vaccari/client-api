package com.rvapp.apiconsumer.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rvapp.apiconsumer.ClientProvider;
import com.rvapp.apiconsumer.domain.Student;

import javax.ws.rs.Consumes;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class StudentResource {

    Client client = ClientProvider.getClient();
    ObjectMapper om = new ObjectMapper();

    @Consumes("application/json")
    public String getData() throws JsonProcessingException {

        WebTarget getTarget = client.target("http://localhost:8181").path("students");

        Response getResponse = getTarget.request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + JWTAuthenticator.authenticate())
                .get();

        String getResponseBody = getResponse.readEntity(String.class);
        return getResponseBody;
    }

    public List<Student> parseStudentList(String body) throws JsonProcessingException {
        List<Student> studentList = om.readValue(body, List.class);
        return studentList;
    }
}
