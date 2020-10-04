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

    @Inject
    public TeacherService(TeacherRepository repositoryTeacher) {
        this.repositoryTeachers = repositoryTeacher;
    }

    @Override
    public void insertSingle(Teacher teacher) {
        repositoryTeachers.insertEntity(teacher);
    }

    @Override
    public void insertList(Set<Teacher> teachers) {
        for (Teacher teacher : teachers) repositoryTeachers.insertEntity(teacher, null);
    }

    // Called by ClassGroupService
    public void insertSingle(ClassGroup classGroup, Teacher teacher) {
        repositoryTeachers.insertEntity(teacher, classGroup);
    }

    public void deleteById(String id) { repositoryTeachers.deleteById(id); }

    public void updateById(Teacher teacher, String classGroupId) { repositoryTeachers.updateById(teacher, classGroupId); }

}
