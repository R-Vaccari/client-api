package com.rvapp.apiconsumer;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class ClientProvider {

    static Client client = ClientBuilder.newBuilder()
            .register(ObjectMapperProvider.class)
            .register(JacksonFeature.class)
            .build();

    public static Client getClient() {
        return client;
    }
}
