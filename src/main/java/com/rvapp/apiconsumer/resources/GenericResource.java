package com.rvapp.apiconsumer.resources;

public interface GenericResource {

    public String getAll();
    public String getById(String id);
    public String getWebTarget();

    public void insertSingle(String responseBody);
    public void insertList(String responseBody);
}
