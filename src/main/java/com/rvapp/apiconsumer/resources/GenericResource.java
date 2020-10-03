package com.rvapp.apiconsumer.resources;

import javax.ws.rs.client.WebTarget;

public interface GenericResource {

    public String getAll();
    public String getById(String id);
    public WebTarget getWebTarget();
}
