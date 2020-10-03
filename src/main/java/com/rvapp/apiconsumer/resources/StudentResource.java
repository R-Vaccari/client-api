package com.rvapp.apiconsumer.resources;

import com.rvapp.apiconsumer.exceptions.NotOkHttpStatusException;
import com.rvapp.apiconsumer.resources.util.AuthenticationResource;
import com.rvapp.apiconsumer.services.StudentService;
import com.rvapp.apiconsumer.resources.util.ClientProvider;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Singleton
public class StudentResource implements GenericResource {

    private final WebTarget target = ClientProvider.getWebTarget().path("students");

    // ------------ GET methods --------------------------------- //

    @Override
    @Consumes("application/json")
    public String getAll() {
        try {
            Response getResponse = target.request(MediaType.APPLICATION_JSON_TYPE)
                    .header("Authorization", "Bearer " + AuthenticationResource.authenticate())
                    .get();

            int status = getResponse.getStatus();
            if (status != 200) throw new NotOkHttpStatusException("Expected status: 200. Response status: " + status);

            return getResponse.readEntity(String.class);
        } catch (NotOkHttpStatusException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Consumes("application/json")
    public String getById(String id) {
        try {
            Response getResponse = target.path(id).request(MediaType.APPLICATION_JSON_TYPE)
                    .header("Authorization", "Bearer " + AuthenticationResource.authenticate())
                    .get();

            int status = getResponse.getStatus();
            if (status != 200) throw new NotOkHttpStatusException("Expected status: 200. Response status: " + status);

            return getResponse.readEntity(String.class);
        } catch (NotOkHttpStatusException e) {
            e.printStackTrace();
        }
            return null;
    }

    @Override
    public WebTarget getWebTarget() {
        return target;
    }

}
