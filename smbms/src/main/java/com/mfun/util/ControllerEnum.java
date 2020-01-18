package com.mfun.util;

public enum ControllerEnum {
    USER("user"),
    MESSAGE("message");

    private String value;
    ControllerEnum(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
