package com.rvapp.apiconsumer.services.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rvapp.apiconsumer.domain.Course;

import javax.inject.Singleton;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Singleton
public class CourseParser implements GenericParser<Course> {

    @Override
    public Course parseEntity(String responseBody) {
        try {
            Course[] courseArray = om.readValue(responseBody, Course[].class);

            return courseArray[0];
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Set<Course> parseSet(String responseBody) {
        try {
            Course[] courseArray = om.readValue(responseBody, Course[].class);
            Set<Course> courseSet = new HashSet<>();

            Collections.addAll(courseSet, courseArray);

            return courseSet;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
