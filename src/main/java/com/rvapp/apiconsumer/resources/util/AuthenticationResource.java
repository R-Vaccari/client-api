package com.rvapp.apiconsumer.resources.util;

import com.rvapp.apiconsumer.exceptions.NotOkHttpStatusException;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class AuthenticationResource {

    public static String authenticate() {
        try {
            WebTarget authTarget = ClientProvider.getWebTarget().path("authenticate");
            String login = "{ \"username\" : \"user\" , \"password\" : \"password\" }";

            Response authentication = authTarget.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.json(login));

            int status = authentication.getStatus();
            if (status != 200) throw new NotOkHttpStatusException("Expected status: 200. Response status: " + status);

            String authResponseBody = authentication.readEntity(String.class);
            String jwt = authResponseBody.substring(8, authResponseBody.length() - 2);
            return jwt;
        } catch (NotOkHttpStatusException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Response getResponse() {
        try {
            WebTarget authTarget = ClientProvider.getWebTarget().path("authenticate");
            String login = "{ \"username\" : \"user\" , \"password\" : \"password\" }";

            Response authentication = authTarget.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.json(login));

            return authentication;
        } catch (NotOkHttpStatusException e) {
            e.printStackTrace();
        }
        return null;
    }
}
