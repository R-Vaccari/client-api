package com.rvapp.apiconsumer.services;

import com.rvapp.apiconsumer.domain.ClassGroup;
import com.rvapp.apiconsumer.domain.Teacher;
import com.rvapp.apiconsumer.repositories.TeacherRepository;
import com.rvapp.apiconsumer.services.util.TeacherParser;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Set;

@Singleton
public class TeacherService implements GenericService<Teacher> {

    @Inject private TeacherRepository repositoryTeachers;
    @Inject private TeacherParser parserTeachers;

    @Inject
    public TeacherService(TeacherRepository repositoryTeacher, TeacherParser parserTeachers) {
        this.repositoryTeachers = repositoryTeacher;
        this.parserTeachers = parserTeachers;
    }

    @Override
    public Teacher insertSingle(String responseBody) {
        Teacher teacher = parserTeachers.parseEntity(responseBody);
        repositoryTeachers.insertEntity(teacher);
        return teacher;
    }

    @Override
    public Set<Teacher> insertList(String responseBody) {
        Set<Teacher> teachers = parserTeachers.parseSet(responseBody);
        for (Teacher teacher : teachers) repositoryTeachers.insertEntity(teacher, null);
        return teachers;
    }

    // Called by ClassGroupService
    public void insertSingle(ClassGroup classGroup, Teacher teacher) {
        repositoryTeachers.insertEntity(teacher, classGroup);
    }

    public void deleteById(String id) { repositoryTeachers.deleteById(id); }

    public void updateById(Teacher teacher, String classGroupId) { repositoryTeachers.updateById(teacher, classGroupId); }

}
