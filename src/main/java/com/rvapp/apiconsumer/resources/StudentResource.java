package com.rvapp.apiconsumer.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rvapp.apiconsumer.domain.Student;
import com.rvapp.apiconsumer.util.ClientProvider;
import com.rvapp.apiconsumer.util.DBConnector;
import com.rvapp.apiconsumer.util.Parser;

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

    private Client client = ClientProvider.getClient();
    private WebTarget getTarget = client.target("https://rvapp-course-api.herokuapp.com").path("students");


    @Consumes("application/json")
    public String getStudentsList() {
        Response getResponse = getTarget.request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + JWTAuthenticator.authenticate())
                .get();

        String responseBody = getResponse.readEntity(String.class);
        return responseBody;
    }

    public void insertStudentList() throws JsonProcessingException, SQLException {
        Connection conn = DBConnector.getConnection();

        String data = getStudentsList();
        List<Student> listStudents = Parser.parseStudentsList(data);

        for (Student st : listStudents) {
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

    public String getWebTarget() {
        return getTarget.getUri().toString();
    }
}