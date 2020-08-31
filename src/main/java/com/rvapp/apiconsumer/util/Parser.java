package com.rvapp.apiconsumer.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rvapp.apiconsumer.domain.Student;

import javax.ws.rs.Produces;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Parser {

    static ObjectMapper om = new ObjectMapper();

    public static List<Student> parseStudentList(String body) throws JsonProcessingException {
        List<Student> studentList = om.readValue(body, new TypeReference<List<Student>>(){});
        return studentList;
    }

    public static void produceJson(String body) throws IOException {
        om.writeValue(Paths.get("student-list.json").toFile(), body);
    }
}
