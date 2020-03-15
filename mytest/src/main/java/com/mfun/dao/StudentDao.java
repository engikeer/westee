package com.mfun.dao;

import com.mfun.bean.Student;

import java.util.List;

public interface StudentDao {
    Student getStudentById(int i);
    List<Student> getStudentByCondition(Student student);
}
