package com.rvapp.apiconsumer.services.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rvapp.apiconsumer.domain.Course;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CourseParser implements GenericParser {

    public static Course parseCourse(String responseBody) {
        try {
            Course[] courseArray = om.readValue(responseBody, Course[].class);

            return courseArray[0];
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Set<Course> parseCourseList(String responseBody) {
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
