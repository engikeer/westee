package com.mfun.util;

public enum ControllerEnum {
    USER_SESSION("user_session");

    private String value;
    ControllerEnum(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
