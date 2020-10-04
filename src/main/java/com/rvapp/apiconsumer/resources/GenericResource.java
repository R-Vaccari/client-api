package com.rvapp.apiconsumer.resources;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.Collection;

public interface GenericResource<T> {

    public Collection<T> getAll();
    public T getById(String id);
    public WebTarget getWebTarget();
    public Response getResponse();
}
