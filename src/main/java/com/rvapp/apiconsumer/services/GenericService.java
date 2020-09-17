package com.rvapp.apiconsumer.services;

import com.rvapp.apiconsumer.services.util.GenericParser;

public interface GenericService {

    static GenericParser GENERIC_PARSER = null;

    void insertSingle(String responseBody);
    void insertList(String responseBody);
}
