package com.rvapp.apiconsumer.services.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rvapp.apiconsumer.domain.ClassGroup;

import javax.inject.Singleton;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Singleton
public class ClassGroupParser implements GenericParser<ClassGroup> {

    @Override
    public ClassGroup parseEntity(String responseBody) {
        try {
            ClassGroup[] classGroupArray = om.readValue(responseBody, ClassGroup[].class);

            return classGroupArray[0];
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Set<ClassGroup> parseSet(String responseBody) {
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
