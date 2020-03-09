package com.mfun.dao;

import com.mfun.bean.Employee;

public interface EmployeeDao {
    Employee getEmpById(Integer id);
    int updateEmployee(Employee employee);
    int insertEmployee(Employee employee);
    boolean deleteEmployee(Integer id);
}
