package com.rosyidgrobogan.handsonprotobuf;



import com.rosyidgrobogan.api.Person;

public class PersonDemo {

    public static void main(String[] args) {
        Person rosyid = Person.newBuilder()
                .setName("Rosyid")
                .setAge(25)
                .build();

        System.out.println(rosyid);
    }
}
