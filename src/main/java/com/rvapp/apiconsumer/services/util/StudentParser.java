package com.rvapp.apiconsumer.services.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.rvapp.apiconsumer.domain.Student;

import javax.inject.Singleton;
import java.util.Set;

@Singleton
public class StudentParser implements GenericParser<Student> {

    @Override
    public Student parseEntity(String responseBody) {
        try {
            return om.readValue(responseBody, new TypeReference<Student>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Set<Student> parseSet(String responseBody) {
        try {
            return om.readValue(responseBody, new TypeReference<Set<Student>>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
