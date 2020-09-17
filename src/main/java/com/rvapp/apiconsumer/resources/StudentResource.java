package com.rvapp.apiconsumer.resources;

import com.rvapp.apiconsumer.resources.util.AuthenticationService;
import com.rvapp.apiconsumer.services.StudentService;
import com.rvapp.apiconsumer.util.ClientProvider;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Singleton
public class StudentResource implements GenericResource {

    private StudentService studentService;
    private final WebTarget target = ClientProvider.getWebTarget().path("students");

    // ------------ GET methods --------------------------------- //

    @Override
    @Consumes("application/json")
    public String getAll() {
        Response getResponse = target.request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + AuthenticationService.authenticate())
                .get();

        return getResponse.readEntity(String.class);
    }

    @Override
    @Consumes("application/json")
    public String getById(String id) {
        Response getResponse = target.path(id).request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + AuthenticationService.authenticate())
                .get();

        return getResponse.readEntity(String.class);
    }

    @Override
    public String getWebTarget() {
        return target.getUri().toString();
    }

}
