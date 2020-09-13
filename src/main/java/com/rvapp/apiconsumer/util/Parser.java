package com.rvapp.apiconsumer.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rvapp.apiconsumer.domain.ClassGroup;
import com.rvapp.apiconsumer.domain.Course;
import com.rvapp.apiconsumer.domain.Student;
import com.rvapp.apiconsumer.domain.Teacher;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Parser {

    static ObjectMapper om = ObjectMapperProvider.createDefaultMapper();

    public static Student parseStudent(String responseBody) {
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

    public static List<Teacher> parseTeachersList(String responseBody) {
        try {
            return om.readValue(responseBody, new TypeReference<List<Teacher>>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ClassGroup parseClassGroup(String responseBody) {
        try {
            ClassGroup[] classGroupArray = om.readValue(responseBody, ClassGroup[].class);

            return classGroupArray[0];
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Set<ClassGroup> parseClassGroupList(String responseBody) {
        try {
            ClassGroup[] classGroupArray = om.readValue(responseBody, ClassGroup[].class);
            Set<ClassGroup> classGroupList = new HashSet<>();

            Collections.addAll(classGroupList, classGroupArray);

            return classGroupList;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Course parseCourse(String responseBody) {
        try {
            Course[] courseArray = om.readValue(responseBody, Course[].class);

            return courseArray[0];
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
}
