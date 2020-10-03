package com.rvapp.apiconsumer.services;

import com.rvapp.apiconsumer.services.util.GenericParser;

import java.util.Set;

public interface GenericService<T> {

    static GenericParser GENERIC_PARSER = null;

    T insertSingle(String responseBody);
    Set<T> insertList(String responseBody);
}
