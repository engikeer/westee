package com.mfun.pojo;


public class Person {
    private String personName;

    @Override
    public String toString() {
        return "Person{" +
                "personName='" + personName + '\'' +
                '}';
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }
}
