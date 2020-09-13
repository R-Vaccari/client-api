package com.rvapp.apiconsumer.resources;

import com.rvapp.apiconsumer.database.SQL;
import com.rvapp.apiconsumer.domain.ClassGroup;
import com.rvapp.apiconsumer.domain.Course;
import com.rvapp.apiconsumer.domain.Student;
import com.rvapp.apiconsumer.domain.Teacher;
import com.rvapp.apiconsumer.resources.util.JWTAuthenticator;
import com.rvapp.apiconsumer.util.ClientProvider;
import com.rvapp.apiconsumer.util.Parser;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

@Singleton
public class ClassGroupResource implements GenericResource {

    private Client client = ClientProvider.getClient();
    private WebTarget target = ClientProvider.getWebTarget().path("classes");
    private TeacherResource resourceTeachers;
    private StudentResource resourceStudents;

    @Inject
    public ClassGroupResource(StudentResource resourceStudents, TeacherResource resourceTeachers) {
        this.resourceStudents = resourceStudents;
        this.resourceTeachers = resourceTeachers;
    }


    // ------------ GET methods --------------------------------- //

    @Override
    @Consumes("application/json")
    public String getAll() {
        Response getResponse = target.request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + JWTAuthenticator.authenticate())
                .get();

        String responseBody = getResponse.readEntity(String.class);
        return responseBody;
    }

    @Override
    @Consumes("application/json")
    public String getById(String id) {
        Response getResponse = target.path(id).request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + JWTAuthenticator.authenticate())
                .get();

        String responseBody = getResponse.readEntity(String.class);
        return responseBody;
    }

    @Consumes("application/json")
    public String getIntermediateClasses() {
        Response getResponse = target.path("levelsearch").queryParam("level", "INTERMEDIATE").request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + JWTAuthenticator.authenticate())
                .get();

        String responseBody = getResponse.readEntity(String.class);
        return responseBody;
    }

    @Consumes("application/json")
    public String getAdvancedClasses() {
        Response getResponse = target.path("levelsearch").queryParam("level", "ADVANCED").request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + JWTAuthenticator.authenticate())
                .get();

        String responseBody = getResponse.readEntity(String.class);
        return responseBody;
    }

    @Consumes("application/json")
    public String getBeginnerClasses() {
        Response getResponse = target.path("levelsearch").queryParam("level", "BEGINNER").request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + JWTAuthenticator.authenticate())
                .get();

        String responseBody = getResponse.readEntity(String.class);
        return responseBody;
    }

    @Consumes("application/json")
    public String getBusinessClasses() {
        Response getResponse = target.path("levelsearch").queryParam("level", "BUSINESS").request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + JWTAuthenticator.authenticate())
                .get();

        String responseBody = getResponse.readEntity(String.class);
        return responseBody;
    }

    // ------------ Insert methods --------------------------------- //

    // Single ClassGroup
    @Override
    public void insertSingle(String responseBody) {
        ClassGroup classGroup = Parser.parseClassGroup(responseBody);
        SQL.insertClassGroup(classGroup, null);

        Teacher teacher = classGroup.getTeacher();
        resourceTeachers.insertParsedTeachers(classGroup, teacher);

        Set<Student> students = classGroup.getStudents();
        resourceStudents.insertList(classGroup, students);
    }

    // Multiple ClassGroups
    public void insertParsedClassGroupList(String responseBody) {
        Set<ClassGroup> classGroupList = Parser.parseClassGroupList(responseBody);
        for (ClassGroup classGroup : classGroupList) {
            SQL.insertClassGroup(classGroup, null);

            Teacher teacher = classGroup.getTeacher();
            resourceTeachers.insertParsedTeachers(classGroup, teacher);

            Set<Student> students = classGroup.getStudents();
            resourceStudents.insertList(classGroup, students);
        }
    }

    // Called by CourseResource
    public void insertParsedClassGroupList(Course course, Set<ClassGroup> classGroupList) {
        for (ClassGroup classGroup : classGroupList) {
            SQL.insertClassGroup(classGroup, course);

            Teacher teacher = classGroup.getTeacher();
            resourceTeachers.insertParsedTeachers(classGroup, teacher);

            Set<Student> students = classGroup.getStudents();
            resourceStudents.insertList(classGroup, students);
        }
    }

    public String getWebTarget() { return target.getUri().toString(); }
}
