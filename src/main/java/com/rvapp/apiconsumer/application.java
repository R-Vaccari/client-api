package com.rvapp.apiconsumer;

import com.rvapp.apiconsumer.database.SQL;
import com.rvapp.apiconsumer.resources.ClassGroupResource;
import com.rvapp.apiconsumer.resources.StudentResource;

public class application {

    public static void main(String[] args) {

        ClassGroupResource resourceClasses = new ClassGroupResource();
        StudentResource resourceStudents = new StudentResource();

        SQL.createTables();
        resourceClasses.insertParsedClasses(resourceClasses.getIntermediateClasses());






        /*
        Scanner sc = new Scanner(System.in);
        System.out.println(resourceClasses.getWebTarget());
        System.out.println();

        System.out.println("Connection = " + DBConnector.getConnection().toString() + "\nCurrent Webtarget = " + resourceStudent.getWebTarget());
        System.out.println();

        System.out.println("Choose between the following: \nInsert Student data in database(1); \nProduce file with Student data(2).");

        switch (sc.nextInt()) {
            case 1:
                try {
                    resourceStudent.insertStudentList();
                    System.out.println("Student list inserted in database successfully.");
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    System.out.println("An error occurred during Json processing.");
                } break;
            case 2:
                try {
                    Parser.produceStudentsJson(resourceStudent.getStudentsList());
                    System.out.println("Parse to file successful.");
                } catch (IOException e) {
                    e.printStackTrace();
                } break;
        }
        sc.close();
         */
    }
}
