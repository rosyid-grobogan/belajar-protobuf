package com.rosyidgrobogan.handsonprotobuf;

import com.rosyidgrobogan.api.Person;
import org.junit.jupiter.api.Test;

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

}