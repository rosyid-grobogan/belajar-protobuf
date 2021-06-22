package com.rosyidgrobogan.handsonprotobuf;

import com.rosyidgrobogan.api.Person;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class PersonDemoTest {

    @Test
    void equelValueTrue(){
        Person rosyid = Person.newBuilder()
                .setName("Rosyid")
                .setAge(25)
                .build();

        Person rosyid2 = Person.newBuilder()
                .setName("Rosyid")
                .setAge(25)
                .build();

        System.out.println(
                rosyid.equals(rosyid2)
        );
        // true
    }

    @Test
    void equelValueFalse(){
        Person rosyid = Person.newBuilder()
                .setName("rosyid")
                .setAge(25)
                .build();

        Person rosyid2 = Person.newBuilder()
                .setName("Rosyid")
                .setAge(25)
                .build();

        System.out.println(
                rosyid.equals(rosyid2)
        );
        // false
    }

    @Test
    void generateFileForSerialization() throws IOException {

        Person rosyid = Person.newBuilder()
                .setName("Rosyid")
                .setAge(25)
                .build();

        Path path = Paths.get("rosyid.bytes");
        Files.write(path, rosyid.toByteArray());
    }

    @Test
    void readFileForDeserialization() throws IOException {

        Path path = Paths.get("rosyid.bytes");
        byte[] bytes = Files.readAllBytes(path);

        Person newRosyid = Person.parseFrom(bytes);

        System.out.println(
                newRosyid
        );

    }
}