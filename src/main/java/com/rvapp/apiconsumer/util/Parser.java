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

    public static Set<Student> parseStudentsList(String responseBody) {
        try {
            Set<Student> listStudents = om.readValue(responseBody, new TypeReference<Set<Student>>(){});
            return listStudents;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void produceStudentsJson(String responseBody) {
        try {
            om.writeValue(Paths.get("student-list.json").toFile(), responseBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void produceClassesJson(String responseBody) {
        try {
            om.writeValue(Paths.get("classes-list.json").toFile(), responseBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ClassGroup parseClassGroup(String responseBody) {
        try {
            ClassGroup[] classGroupArray = om.readValue(responseBody, ClassGroup[].class);

            ClassGroup classGroup = classGroupArray[0];

            return classGroup;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
