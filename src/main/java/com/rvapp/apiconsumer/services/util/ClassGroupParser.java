package com.rvapp.apiconsumer.services.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rvapp.apiconsumer.domain.ClassGroup;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ClassGroupParser implements GenericParser {

    public static ClassGroup parseClassGroup(String responseBody) {
        try {
            ClassGroup[] classGroupArray = om.readValue(responseBody, ClassGroup[].class);

            return classGroupArray[0];
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Set<ClassGroup> parseClassGroupList(String responseBody) {
        try {
            ClassGroup[] classGroupArray = om.readValue(responseBody, ClassGroup[].class);
            Set<ClassGroup> classGroupList = new HashSet<>();

            Collections.addAll(classGroupList, classGroupArray);

            return classGroupList;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
