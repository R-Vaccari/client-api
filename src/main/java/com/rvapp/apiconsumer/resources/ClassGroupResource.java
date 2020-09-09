package com.rvapp.apiconsumer.resources;

import com.rvapp.apiconsumer.database.SQL;
import com.rvapp.apiconsumer.domain.ClassGroup;
import com.rvapp.apiconsumer.domain.Student;
import com.rvapp.apiconsumer.domain.Teacher;
import com.rvapp.apiconsumer.util.ClientProvider;
import com.rvapp.apiconsumer.util.Parser;

import javax.ws.rs.Consumes;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

public class ClassGroupResource {

    private Client client = ClientProvider.getClient();
    private WebTarget target = ClientProvider.getWebTarget().path("classes").path("levelsearch").queryParam("level", "intermediate");

    @Consumes("application/json")
    public String getClassesByLevel() {
        Response getResponse = target.request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + JWTAuthenticator.authenticate())
                .get();

        String responseBody = getResponse.readEntity(String.class);
        return responseBody;
    }

    public void insertParsedClasses(String responseBody) {
        ClassGroup classGroup = Parser.parseClassGroup(responseBody);
        SQL.insertClassGroup(classGroup);

        Teacher teacher = classGroup.getTeacher();
        SQL.insertTeacher(teacher, classGroup);

        Set<Student> students = classGroup.getStudents();
        for (Student student : students) SQL.insertStudent(student, classGroup);
    }

    public String getWebTarget() {
        return target.getUri().toString();
    }
}
