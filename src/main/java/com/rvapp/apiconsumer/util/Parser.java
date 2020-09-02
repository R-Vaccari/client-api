package com.rvapp.apiconsumer.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rvapp.apiconsumer.domain.ClassGroup;
import com.rvapp.apiconsumer.domain.Student;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Parser {

    static ObjectMapper om = new ObjectMapper();

    public static List<Student> parseStudentsList(String body) throws JsonProcessingException {
        List<Student> listStudents = om.readValue(body, new TypeReference<List<Student>>(){});
        return listStudents;
    }

    public static List<ClassGroup> parseClassesList(String body) throws JsonProcessingException {
        List<ClassGroup> listClasses = om.readValue(body, new TypeReference<List<ClassGroup>>(){});
        return listClasses;
    }

    public static void produceJson(String body) throws IOException {
        om.writeValue(Paths.get("student-list.json").toFile(), body);
    }

    public static void parseNestedClassList(String responseBody) throws JsonProcessingException {
        JsonNode classNode = om.readTree(responseBody);
    }
}
