package com.rvapp.apiconsumer.resources;

import com.rvapp.apiconsumer.resources.util.JWTAuthenticator;
import com.rvapp.apiconsumer.util.ClientProvider;

import javax.ws.rs.Consumes;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class CourseResource implements GenericResource {

    private final WebTarget target = ClientProvider.getWebTarget().path("courses");

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

    @Consumes("application/json")
    public String getEnglishCourses() {
        Response getResponse = target.path("typesearch").queryParam("type", "ENGLISH").request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + JWTAuthenticator.authenticate())
                .get();

        return getResponse.readEntity(String.class);
    }

    @Consumes("application/json")
    public String getGermanCourses() {
        Response getResponse = target.path("typesearch").queryParam("type", "GERMAN").request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + JWTAuthenticator.authenticate())
                .get();

        return getResponse.readEntity(String.class);
    }

    @Consumes("application/json")
    public String getPortugueseCourses() {
        Response getResponse = target.path("typesearch").queryParam("type", "PORTUGUESE").request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + JWTAuthenticator.authenticate())
                .get();

        return getResponse.readEntity(String.class);
    }

    @Consumes("application/json")
    public String getItalianCourses() {
        Response getResponse = target.path("typesearch").queryParam("type", "ITALIAN").request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + JWTAuthenticator.authenticate())
                .get();

        return getResponse.readEntity(String.class);
    }

    @Override
    public String getWebTarget() { return target.getUri().toString(); }

}
