package com.rvapp.apiconsumer.resources;

import java.util.Set;

public interface GenericResource {

    public String getAll();
    public String getById(String id);

    public void insertSingle(String responseBody);

}
