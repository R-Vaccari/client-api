package com.rvapp.apiconsumer.services;

import com.rvapp.apiconsumer.services.util.Parser;

public interface GenericService {

    static Parser parser = null;

    void insertSingle(String responseBody);
    void insertList(String responseBody);
}
