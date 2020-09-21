package com.rvapp.apiconsumer.services;

import com.rvapp.apiconsumer.repositories.TeacherRepository;
import com.rvapp.apiconsumer.domain.ClassGroup;
import com.rvapp.apiconsumer.domain.Teacher;
import com.rvapp.apiconsumer.services.util.TeacherParser;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class TeacherService implements GenericService {

    private final TeacherRepository repositoryTeacher;

    public TeacherService(TeacherRepository repositoryTeacher) {

        this.repositoryTeacher = repositoryTeacher;
    }

    @Override
    public void insertSingle(String responseBody) {
        Teacher teacher = TeacherParser.parseTeacher(responseBody);
        repositoryTeacher.insertEntity(teacher);
    }

    @Override
    public void insertList(String responseBody) {
        List<Teacher> listTeachers = TeacherParser.parseTeachersList(responseBody);
        for (Teacher teacher : listTeachers) repositoryTeacher.insertEntity(teacher, null);
    }

    // Called by ClassGroupService
    public void insertSingle(ClassGroup classGroup, Teacher teacher) {
        repositoryTeacher.insertEntity(teacher, classGroup);
    }

}
