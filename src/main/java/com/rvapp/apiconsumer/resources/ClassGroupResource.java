package com.rvapp.apiconsumer.resources;

import com.rvapp.apiconsumer.resources.util.AuthenticationService;
import com.rvapp.apiconsumer.util.ClientProvider;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Singleton
public class ClassGroupResource implements GenericResource {

    private final WebTarget target = ClientProvider.getWebTarget().path("classes");

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

    @Consumes("application/json")
    public String getByLevel(String level) {
        Response getResponse = target.path("levelsearch").queryParam("level", level.toUpperCase()).request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + AuthenticationService.authenticate())
                .get();

        return getResponse.readEntity(String.class);
    }

    @Consumes("application/json")
    public String getIntermediateClasses() {
        Response getResponse = target.path("levelsearch").queryParam("level", "INTERMEDIATE").request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + AuthenticationService.authenticate())
                .get();

        return getResponse.readEntity(String.class);
    }

    @Consumes("application/json")
    public String getAdvancedClasses() {
        Response getResponse = target.path("levelsearch").queryParam("level", "ADVANCED").request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + AuthenticationService.authenticate())
                .get();

        return getResponse.readEntity(String.class);
    }

    @Consumes("application/json")
    public String getBeginnerClasses() {
        Response getResponse = target.path("levelsearch").queryParam("level", "BEGINNER").request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + AuthenticationService.authenticate())
                .get();

        return getResponse.readEntity(String.class);
    }

    @Consumes("application/json")
    public String getBusinessClasses() {
        Response getResponse = target.path("levelsearch").queryParam("level", "BUSINESS").request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + AuthenticationService.authenticate())
                .get();

        return getResponse.readEntity(String.class);
    }

    @Override
    public String getWebTarget() { return target.getUri().toString(); }
}
