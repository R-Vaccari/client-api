package com.rvapp.apiconsumer.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rvapp.apiconsumer.domain.ClassGroup;
import com.rvapp.apiconsumer.util.ClientProvider;
import com.rvapp.apiconsumer.util.DBConnector;
import com.rvapp.apiconsumer.util.Parser;

import javax.ws.rs.Consumes;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ClassGroupResource {

    String path = "classes/levelsearch?level=INTERMEDIATE";
    private Client client = ClientProvider.getClient();
    private WebTarget getTarget = client.target("https://rvapp-course-api.herokuapp.com").path(path);



    @Consumes("application/json")
    public String getClassesList() {
        Response getResponse = getTarget.request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + JWTAuthenticator.authenticate())
                .get();

        String responseBody = getResponse.readEntity(String.class);
        return responseBody;
    }

    public void insertClassesList() throws JsonProcessingException, SQLException {
        Connection conn = DBConnector.getConnection();

        String data = getClassesList();
        List<ClassGroup> listStudent = Parser.parseClassesList(data);


        conn.close();
    }

    public String getWebTarget() {
        return getTarget.getUri().toString();
    }
}
