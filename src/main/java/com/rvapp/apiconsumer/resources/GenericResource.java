package com.rvapp.apiconsumer.resources;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.Collection;

public interface GenericResource<T> {

    Collection<T> getAll();
    T getById(String id);
    WebTarget getWebTarget();
    Response getResponse();
}
