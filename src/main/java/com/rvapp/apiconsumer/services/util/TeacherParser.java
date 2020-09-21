package com.rvapp.apiconsumer.services.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.rvapp.apiconsumer.domain.Teacher;

import javax.inject.Singleton;
import java.util.Set;

@Singleton
public class TeacherParser implements GenericParser<Teacher> {

    @Override
    public Teacher parseEntity(String responseBody) {
        try {
            return om.readValue(responseBody, new TypeReference<Teacher>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Set<Teacher> parseSet(String responseBody) {
        try {
            return om.readValue(responseBody, new TypeReference<Set<Teacher>>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
