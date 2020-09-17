package com.rvapp.apiconsumer.resources.util;

import com.rvapp.apiconsumer.services.util.ObjectMapperProvider;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class ClientProvider {

    static Client client = ClientBuilder.newBuilder()
            .register(ObjectMapperProvider.class)
            .register(JacksonFeature.class)
            .build();

    public static Client getClient() {
        return client;
    }

    public static WebTarget getWebTarget() {
        WebTarget baseTarget = client.target("https://rvapp-course-api.herokuapp.com");
        return baseTarget;
    }
}
