package com.rvapp.apiconsumer.services.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public interface GenericParser {

    ObjectMapper om = ObjectMapperProvider.createDefaultMapper();
    
}
