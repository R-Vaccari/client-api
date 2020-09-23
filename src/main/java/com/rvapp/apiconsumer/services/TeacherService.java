package com.rvapp.apiconsumer.services;

import com.rvapp.apiconsumer.domain.ClassGroup;
import com.rvapp.apiconsumer.domain.Teacher;
import com.rvapp.apiconsumer.repositories.TeacherRepository;
import com.rvapp.apiconsumer.services.util.TeacherParser;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Set;

@Singleton
public class TeacherService implements GenericService {

    @Inject private TeacherRepository repositoryTeachers;
    @Inject private TeacherParser parserTeachers;

    @Inject
    public TeacherService(TeacherRepository repositoryTeacher, TeacherParser parserTeachers) {
        this.repositoryTeachers = repositoryTeacher;
        this.parserTeachers = parserTeachers;
    }

    @Override
    public void insertSingle(String responseBody) {
        Teacher teacher = parserTeachers.parseEntity(responseBody);
        repositoryTeachers.insertEntity(teacher);
    }

    @Override
    public void insertList(String responseBody) {
        Set<Teacher> listTeachers = parserTeachers.parseSet(responseBody);
        for (Teacher teacher : listTeachers) repositoryTeachers.insertEntity(teacher, null);
    }

    // Called by ClassGroupService
    public void insertSingle(ClassGroup classGroup, Teacher teacher) {
        repositoryTeachers.insertEntity(teacher, classGroup);
    }

    public void deleteById(String id) { repositoryTeachers.deleteById(id); }

    public void updateById(Teacher teacher, String classGroupId) { repositoryTeachers.updateById(teacher, classGroupId); }

}
