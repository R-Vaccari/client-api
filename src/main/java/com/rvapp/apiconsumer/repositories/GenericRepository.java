package com.rvapp.apiconsumer.repositories;

public interface GenericRepository<T> {

    void insertEntity(T object);

}
