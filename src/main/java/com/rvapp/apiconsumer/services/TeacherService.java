package com.rvapp.apiconsumer.services;

import com.rvapp.apiconsumer.database.SQLService;
import com.rvapp.apiconsumer.domain.ClassGroup;
import com.rvapp.apiconsumer.domain.Teacher;
import com.rvapp.apiconsumer.util.Parser;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class TeacherService implements GenericService {

    @Override
    public void insertSingle(String responseBody) {
        Teacher teacher = Parser.parseTeacher(responseBody);
        SQLService.insertTeacher(teacher, null);
    }

    @Override
    public void insertList(String responseBody) {
        List<Teacher> listTeachers = Parser.parseTeachersList(responseBody);
        for (Teacher teacher : listTeachers) SQLService.insertTeacher(teacher, null);
    }

    // Called by ClassGroupService
    public void insertSingle(ClassGroup classGroup, Teacher teacher) {
        SQLService.insertTeacher(teacher, classGroup);
    }

}
