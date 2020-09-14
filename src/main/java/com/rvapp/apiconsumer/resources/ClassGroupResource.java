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
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

@Singleton
public class ClassGroupResource implements GenericResource {

    private final WebTarget target = ClientProvider.getWebTarget().path("classes");
    private final TeacherResource resourceTeachers;
    private final StudentResource resourceStudents;

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

        return getResponse.readEntity(String.class);
    }

    @Override
    @Consumes("application/json")
    public String getById(String id) {
        Response getResponse = target.path(id).request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + JWTAuthenticator.authenticate())
                .get();

        return getResponse.readEntity(String.class);
    }

    @Consumes("application/json")
    public String getIntermediateClasses() {
        Response getResponse = target.path("levelsearch").queryParam("level", "INTERMEDIATE").request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + JWTAuthenticator.authenticate())
                .get();

        return getResponse.readEntity(String.class);
    }

    @Consumes("application/json")
    public String getAdvancedClasses() {
        Response getResponse = target.path("levelsearch").queryParam("level", "ADVANCED").request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + JWTAuthenticator.authenticate())
                .get();

        return getResponse.readEntity(String.class);
    }

    @Consumes("application/json")
    public String getBeginnerClasses() {
        Response getResponse = target.path("levelsearch").queryParam("level", "BEGINNER").request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + JWTAuthenticator.authenticate())
                .get();

        return getResponse.readEntity(String.class);
    }

    @Consumes("application/json")
    public String getBusinessClasses() {
        Response getResponse = target.path("levelsearch").queryParam("level", "BUSINESS").request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + JWTAuthenticator.authenticate())
                .get();

        return getResponse.readEntity(String.class);
    }

    @Override
    public String getWebTarget() { return target.getUri().toString(); }

    // ------------ Insert methods --------------------------------- //

    // Single ClassGroup
    @Override
    public void insertSingle(String responseBody) {
        ClassGroup classGroup = Parser.parseClassGroup(responseBody);
        SQL.insertClassGroup(classGroup, null);

        Teacher teacher = classGroup.getTeacher();
        resourceTeachers.insertList(classGroup, teacher);

        Set<Student> students = classGroup.getStudents();
        resourceStudents.insertList(classGroup, students);
    }

    // Multiple ClassGroups
    @Override
    public void insertList(String responseBody) {
        Set<ClassGroup> classGroupList = Parser.parseClassGroupList(responseBody);
        for (ClassGroup classGroup : classGroupList) {
            SQL.insertClassGroup(classGroup, null);

            Teacher teacher = classGroup.getTeacher();
            resourceTeachers.insertList(classGroup, teacher);

            Set<Student> students = classGroup.getStudents();
            resourceStudents.insertList(classGroup, students);
        }
    }

    // Called by CourseResource
    public void insertList(Course course, Set<ClassGroup> classGroupList) {
        for (ClassGroup classGroup : classGroupList) {
            SQL.insertClassGroup(classGroup, course);

            Teacher teacher = classGroup.getTeacher();
            resourceTeachers.insertList(classGroup, teacher);

            Set<Student> students = classGroup.getStudents();
            resourceStudents.insertList(classGroup, students);
        }
    }
}
