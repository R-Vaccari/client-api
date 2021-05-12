package com.rvapp.apiconsumer.resources;

import com.rvapp.apiconsumer.domain.ClassGroup;
import com.rvapp.apiconsumer.exceptions.NotOkHttpStatusException;
import com.rvapp.apiconsumer.resources.util.AuthenticationResource;
import com.rvapp.apiconsumer.resources.util.ClientProvider;
import com.rvapp.apiconsumer.services.util.ClassGroupParser;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

@Singleton
public class ClassGroupResource implements GenericResource<ClassGroup> {

    private final WebTarget target = ClientProvider.getWebTarget().path("classes");
    @Inject private final ClassGroupParser parserClasses;

    @Inject
    public ClassGroupResource(ClassGroupParser parserClasses) { this.parserClasses = parserClasses; }

    @Override
    @Consumes("application/json")
    public Set<ClassGroup> getAll() {
        try {
            Response response = target.request(MediaType.APPLICATION_JSON_TYPE)
                    .header("Authorization", "Bearer " + AuthenticationResource.authenticate())
                    .get();

            if (response.getStatus() != 200) throw new NotOkHttpStatusException("Expected status: 200. Response status: " + response.getStatus());
            return parserClasses.parseSet(response.readEntity(String.class));
        } catch (NotOkHttpStatusException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Consumes("application/json")
    public ClassGroup getById(String id) {
        try {
            Response response = target.path(id).request(MediaType.APPLICATION_JSON_TYPE)
                    .header("Authorization", "Bearer " + AuthenticationResource.authenticate())
                    .get();

            if (response.getStatus() != 200) throw new NotOkHttpStatusException("Expected status: 200. Response status: " + response.getStatus());
            return parserClasses.parseEntity(response.readEntity(String.class));
        } catch (NotOkHttpStatusException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Consumes("application/json")
    public ClassGroup getByLevel(String level) {
        try {
            Response response = target.path("levelsearch").queryParam("level", level.toUpperCase()).request(MediaType.APPLICATION_JSON_TYPE)
                    .header("Authorization", "Bearer " + AuthenticationResource.authenticate())
                    .get();

            if (response.getStatus() != 200) throw new NotOkHttpStatusException("Expected status: 200. Response status: " + response.getStatus());
            return parserClasses.parseEntity(response.readEntity(String.class));
        } catch (NotOkHttpStatusException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public WebTarget getWebTarget() { return target; }

    @Override
    public Response getResponse() {
        return target.request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + AuthenticationResource.authenticate())
                .get();
    }
}
