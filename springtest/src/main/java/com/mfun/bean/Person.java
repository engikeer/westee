package com.mfun.bean;

import org.springframework.stereotype.Component;

@Component()
public class Person {
    private String name;
    private int age;
    private String gender;
    private String email;

    private Car car;

    public Person() {
        System.out.println("创建 Person");
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        System.out.println("设置Car");
        this.car = car;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
