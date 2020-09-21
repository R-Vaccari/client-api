package com.rvapp.apiconsumer.services.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Set;

public interface GenericParser<T> {

    T parseEntity(String responseBody);
    Set<T> parseSet(String responseBody);

    ObjectMapper om = ObjectMapperProvider.createDefaultMapper();
    
}
