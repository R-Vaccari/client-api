package com.rvapp.apiconsumer.services.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collection;

public interface GenericParser<T> {

    T parseEntity(String responseBody);
    Collection<T> parseSet(String responseBody);

    ObjectMapper om = ObjectMapperProvider.createDefaultMapper();
    
}
