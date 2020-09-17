package com.rvapp.apiconsumer.services.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.rvapp.apiconsumer.domain.Teacher;

import java.util.List;

public class TeacherParser implements Parser {

    public static Teacher parseTeacher(String responseBody) {
        try {
            return om.readValue(responseBody, new TypeReference<Teacher>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Teacher> parseTeachersList(String responseBody) {
        try {
            return om.readValue(responseBody, new TypeReference<List<Teacher>>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
