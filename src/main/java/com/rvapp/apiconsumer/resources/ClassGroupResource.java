package com.rvapp.apiconsumer.resources;

import com.rvapp.apiconsumer.util.ClientProvider;

import javax.ws.rs.Consumes;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

public class ClassGroupResource {

    private Client client = ClientProvider.getClient();
    private WebTarget getTarget = client.target("https://rvapp-course-api.herokuapp.com").path("classes").path("levelsearch").queryParam("level", "INTERMEDIATE");

    @Consumes("application/json")
    public String getIntermediateClasses() throws IOException {
        Response getResponse = getTarget.request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + JWTAuthenticator.authenticate())
                .get();

        String responseBody = getResponse.readEntity(String.class);
        return responseBody;
    }

    public String getWebTarget() {
        return getTarget.getUri().toString();
    }
}
