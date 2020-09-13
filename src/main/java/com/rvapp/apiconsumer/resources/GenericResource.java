package com.rvapp.apiconsumer.resources;

public interface GenericResource {

    public String getAll();
    public String getById(String id);

    public void insertSingle(String responseBody);
    public void insertList(String responseBody);

    public String getWebTarget();

}
