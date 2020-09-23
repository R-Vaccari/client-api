package com.rvapp.apiconsumer.repositories;

public interface GenericRepository<T> {

    void insertEntity(T entity);
    void deleteById(String id);
    void updateById(T entity, String upperClassId);
}
