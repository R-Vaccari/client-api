package com.rvapp.apiconsumer.services.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public interface Parser {

    ObjectMapper om = ObjectMapperProvider.createDefaultMapper();

}
