package com.rvapp.apiconsumer.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rvapp.apiconsumer.domain.ClassGroup;
import com.rvapp.apiconsumer.domain.Student;
import com.rvapp.apiconsumer.domain.Teacher;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

public class Parser {

    static ObjectMapper om = new ObjectMapper();

    public static List<Student> parseStudentsList(String responseBody) throws JsonProcessingException {
        List<Student> listStudents = om.readValue(responseBody, new TypeReference<List<Student>>(){});
        return listStudents;
    }

    public static List<ClassGroup> parseClassesList(String responseBody) throws JsonProcessingException {
        List<ClassGroup> listClasses = om.readValue(responseBody, new TypeReference<List<ClassGroup>>(){});
        return listClasses;
    }

    public static void produceStudentsJson(String responseBody) throws IOException {
        om.writeValue(Paths.get("student-list.json").toFile(), responseBody);
    }

    public static void produceClassesJson(String responseBody) throws IOException {
        //responseBody = responseBody.substring(1, responseBody.length());
        om.writeValue(Paths.get("classes-list.json").toFile(), responseBody);
    }

    public static ClassGroup parseNestedClassList() throws IOException {
        JsonNode classNode = om.readTree(new File("C:\\Users\\Rodrigo\\IdeaProjects\\api-consumer\\classes-list.json"));

        ClassGroup classGroup = new ClassGroup();
        classGroup.setId(classNode.path("id").asText());
        classGroup.setClassLevel(classNode.get("classLevel").textValue());
        classGroup.setClassName(classNode.get("className").textValue());

        Teacher teacher = om.readValue(classNode.get("teacher").textValue(), new TypeReference<Teacher>(){});
        Set<Student> students = om.readValue(classNode.get("teacher").textValue(), new TypeReference<Set<Student>>(){});

        classGroup.setTeacher(teacher);
        classGroup.setStudents(students);

        return classGroup;
    }
}
