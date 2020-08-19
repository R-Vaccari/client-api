package com.rvapp.apiconsumer;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class application {

    public static void main(String[] args) {

        ObjectMapper om = new ObjectMapper();

        Client client = ClientBuilder.newClient();
        WebTarget authTarget = client.target("http://localhost:8181").path("authenticate");
        WebTarget getTarget = client.target("http://localhost:8181").path("students");

        Invocation.Builder invocationBuilder;

        String login = "{ \"username\" : \"user\" , \"password\" : \"password\" }";

        Response authentication = authTarget.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.json(login));
        String authResponseBody = authentication.readEntity(String.class);
        String jwt = authResponseBody.substring(8, authResponseBody.length() - 2);

        Response getResponse = getTarget.request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + jwt)
                .get();

        String getResponseBody = getResponse.readEntity(String.class);

        System.out.println(getResponse.getStatus());
        System.out.println(getResponseBody);
        System.out.println(authResponseBody);
        System.out.println(jwt);

    }
}
