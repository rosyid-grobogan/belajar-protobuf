package com.rosyidgrobogan.handsonprotobuf;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;
import com.rosyidgrobogan.api.Person;
import org.junit.jupiter.api.Test;

public class PerformanceTest {

    @Test
    void performanceProtoVsJson(){

        // json
        JsonPerson jsonPerson = new JsonPerson();
        jsonPerson.setName("Rosyid");
        jsonPerson.setAge(25);
        ObjectMapper mapper = new ObjectMapper();

        Runnable runnableJson = () -> {
            try {
                byte[] bytes = mapper.writeValueAsBytes(jsonPerson);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        };

        // protobuf
        Person rosyid = Person.newBuilder()
                .setName("Rosyid")
                .setAge(25)
                .build();

        Runnable runnableProtobuf = () -> {
            byte[] bytes = rosyid.toByteArray();
            try {
                Person.parseFrom(bytes);
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
        };

        runPerformanceTest(runnableJson, "JSON");
        runPerformanceTest(runnableProtobuf, "PROTOBUF");

        // Hasilnya bisa berbeda:
        // JSON : 1077 ms
        // PROTOBUF : 245 ms
    }

    @Test
    void performanceProtoVsJsonLoop(){

        // json
        JsonPerson jsonPerson = new JsonPerson();
        jsonPerson.setName("Rosyid");
        jsonPerson.setAge(25);
        ObjectMapper mapper = new ObjectMapper();

        Runnable runnableJson = () -> {
            try {
                byte[] bytes = mapper.writeValueAsBytes(jsonPerson);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        };

        // protobuf
        Person rosyid = Person.newBuilder()
                .setName("Rosyid")
                .setAge(25)
                .build();

        Runnable runnableProtobuf = () -> {
            byte[] bytes = rosyid.toByteArray();
            try {
                Person.parseFrom(bytes);
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
        };

        for (int i = 0; i < 5; i++) {
            runPerformanceTest(runnableJson, "JSON");
            runPerformanceTest(runnableProtobuf, "PROTOBUF");
        }


        // Hasilnya bisa berbeda: (protobuf lebih stabil
//        JSON : 2673 ms
//        PROTOBUF : 763 ms
//        JSON : 1772 ms
//        PROTOBUF : 701 ms
//        JSON : 1571 ms
//        PROTOBUF : 743 ms
//        JSON : 1625 ms
//        PROTOBUF : 786 ms
//        JSON : 1832 ms
//        PROTOBUF : 802 ms
    }

    private static void runPerformanceTest(Runnable runnable, String method){
        long time1 = System.currentTimeMillis();
        for (int i = 0; i < 5_000_000; i++) {
            runnable.run();
        }

        long time2 = System.currentTimeMillis();
        System.out.println(
              method + " : " + (time2 - time1) + " ms"
        );
    }
}
