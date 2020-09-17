package com.rvapp.apiconsumer.resources.util;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class AuthenticationResource {

    public static String authenticate() {
        WebTarget authTarget = ClientProvider.getWebTarget().path("authenticate");
        String login = "{ \"username\" : \"user\" , \"password\" : \"password\" }";

        Response authentication = authTarget.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.json(login));
        String authResponseBody = authentication.readEntity(String.class);
        String jwt = authResponseBody.substring(8, authResponseBody.length() - 2);
        return jwt;
    }
}
