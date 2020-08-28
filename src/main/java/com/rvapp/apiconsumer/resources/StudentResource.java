package com.rvapp.apiconsumer.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rvapp.apiconsumer.util.ClientProvider;
import com.rvapp.apiconsumer.util.DBConnector;
import com.rvapp.apiconsumer.domain.Student;

import javax.ws.rs.Consumes;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        List<Student> studentList = om.readValue(body, new TypeReference<List<Student>>(){});
        return studentList;
    }

    public void insertStudentList() throws JsonProcessingException, SQLException {

        Connection conn = DBConnector.getConnection();

        String data = getData();
        List<Student> listStudent = parseStudentList(data);

        for (Student st : listStudent) {

            PreparedStatement queryInsert = conn.prepareStatement("INSERT INTO student(id, firstname, lastname, email, telephone) " +
                    "VALUES (?, ?, ?, ?, ?)");
            queryInsert.setString(1, st.getId());
            queryInsert.setString(2, st.getFirstName());
            queryInsert.setString(3, st.getLastName());
            queryInsert.setString(4, st.getEmail());
            queryInsert.setString(5, st.getTelephone());

            queryInsert.executeUpdate();
            queryInsert.close();
        }
        conn.close();
    }
}
