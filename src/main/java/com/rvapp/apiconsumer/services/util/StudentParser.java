package com.rvapp.apiconsumer.services.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.rvapp.apiconsumer.domain.Student;

import java.util.Set;

public class StudentParser implements GenericParser {

    public static Student parseSingle(String responseBody) {
        try {
            return om.readValue(responseBody, new TypeReference<Student>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Set<Student> parseStudentsList(String responseBody) {
        try {
            return om.readValue(responseBody, new TypeReference<Set<Student>>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
