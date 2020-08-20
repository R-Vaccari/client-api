package com.rvapp.apiconsumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rvapp.apiconsumer.domain.Student;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Scanner;

public class application {

    static Client client = ClientBuilder.newBuilder()
            .register(ObjectMapperProvider.class)
            .register(JacksonFeature.class)
            .build();

    static ObjectMapper om = new ObjectMapper();

    public static void main(String[] args) throws JsonProcessingException {

        Invocation.Builder invocationBuilder;
        getData();


    }

    public static String authenticate() {
        WebTarget authTarget = client.target("http://localhost:8181").path("authenticate");
        String login = "{ \"username\" : \"user\" , \"password\" : \"password\" }";

        Response authentication = authTarget.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.json(login));
        String authResponseBody = authentication.readEntity(String.class);
        String jwt = authResponseBody.substring(8, authResponseBody.length() - 2);
        return jwt;
    }

    public static String getData() throws JsonProcessingException {

        Scanner sc = new Scanner(System.in);

        WebTarget getTarget = client.target("http://localhost:8181").path(sc.next());

        Response getResponse = getTarget.request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + authenticate())
                .get();

        String getResponseBody = getResponse.readEntity(String.class);

        Student student01 = om.readValue(getResponseBody, Student.class);

        System.out.println(getResponse.getStatus());
        System.out.println(student01);

        sc.close();
        return getResponseBody;
    }
// 5f3e72f74af4fc05cd9bb6b5
}
