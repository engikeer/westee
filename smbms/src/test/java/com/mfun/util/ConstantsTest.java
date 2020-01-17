package com.mfun.util;

import org.junit.jupiter.api.Test;

public class ConstantsTest {

    @Test
    public void genderTest() {
        Gender gender = Gender.getGender(1);
        if (gender != null) {
            System.out.println(gender.getValue() + " - " + gender.getDesc());
            System.out.println("name: " + gender.name());
            System.out.println("ordinal: " + gender.ordinal());

        } else {
            System.out.println("无此性别");
        }
        Gender newGender = Gender.valueOf("FEMALE");
        System.out.println("newGender: " + newGender.getDesc());
    }
}
