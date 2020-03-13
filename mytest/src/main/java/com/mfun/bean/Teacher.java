package com.mfun.bean;

import java.io.Serializable;
import java.util.List;

public class Teacher implements Serializable {
    private Integer id;
    private String name;
    private Integer gender;
    private List<Student> students;

    public Teacher() {}

    public Teacher(String name, Integer gender) {
        this.name = name;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name=" + name +
                ", gender=" + gender +
                ", students=" + students +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
