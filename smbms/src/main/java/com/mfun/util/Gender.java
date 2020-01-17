package com.mfun.util;

public enum Gender {
    FEMALE(1, "女"),
    MALE(2, "男");

    private int value;
    private String desc;

    Gender(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static Gender getGender(int i) {
        Gender[] genders = values();
        for (Gender gender : genders) {
            if (i == gender.value) {
                return gender;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
