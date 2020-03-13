package com.mfun.dao;

import com.mfun.bean.Employee;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;

public interface EmployeeDao {
    @MapKey("id")
    List<Employee> getEmpById(Integer id);
    int updateEmployee(Employee employee);
    int insertEmployee(Employee employee);
    boolean deleteEmployee(Integer id);
}
