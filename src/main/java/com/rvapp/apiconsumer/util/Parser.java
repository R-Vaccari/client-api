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
import java.util.List;
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

    public static List<Teacher> parseTeachersList(String responseBody) {
        try {
            List<Teacher> listTeachers = om.readValue(responseBody, new TypeReference<List<Teacher>>(){});
            return listTeachers;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
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

    public static Course parseCourse(String responseBody) {
        try {
            Course[] courseArray = om.readValue(responseBody, Course[].class);

            Course course = courseArray[0];

            return course;
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
