package com.rvapp.apiconsumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rvapp.apiconsumer.resources.StudentResource;




public class application {


    public static void main(String[] args) throws JsonProcessingException {

        StudentResource resourceStudent = new StudentResource();
        resourceStudent.getData();
    }




}
