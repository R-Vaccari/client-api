package com.rvapp.apiconsumer.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rvapp.apiconsumer.domain.ClassGroup;
import com.rvapp.apiconsumer.domain.Student;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Set;

public class Parser {

    static ObjectMapper om = ObjectMapperProvider.createDefaultMapper();

    public static Set<Student> parseStudentsList(String responseBody) throws JsonProcessingException {
        Set<Student> listStudents = om.readValue(responseBody, new TypeReference<Set<Student>>(){});
        return listStudents;
    }

    public static void produceStudentsJson(String responseBody) throws IOException {
        om.writeValue(Paths.get("student-list.json").toFile(), responseBody);
    }

    public static void produceClassesJson(String responseBody) throws IOException {
        om.writeValue(Paths.get("classes-list.json").toFile(), responseBody);
    }

    public static ClassGroup parseClassGroup(String responseBody) throws IOException {
        ClassGroup[] classGroupArray = om.readValue(responseBody, ClassGroup[].class);

        ClassGroup classGroup = classGroupArray[0];

        return classGroup;
    }
}
