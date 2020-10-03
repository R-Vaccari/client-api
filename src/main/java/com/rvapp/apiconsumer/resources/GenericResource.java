package com.rvapp.apiconsumer.resources;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public interface GenericResource {

    public String getAll();
    public String getById(String id);
    public WebTarget getWebTarget();
    public Response getResponse();
}
