package com.mfun.dao;

import com.mfun.bean.Department;
import com.mfun.bean.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {
    private static Map<Integer, Employee> employeeMap;
    private static int initId = 1006;

    @Autowired
    public EmployeeDao(Map<Integer, Employee> map) {
        employeeMap = map;
    }

    static {
        employeeMap.put(1001, new Employee(1001, "宋江", "及时雨@梁山.com", 1, new Department(102,"内阁府")));
        employeeMap.put(1002, new Employee(1002, "卢俊义", "玉麒麟@梁山.com", 1, new Department(101,"人事院")));
        employeeMap.put(1003, new Employee(1003, "武松", "行者@梁山.com", 1, new Department(107,"防卫省")));
        employeeMap.put(1004, new Employee(1004, "潘金莲", "小莲儿@好好吃炊饼.com", 1, new Department(105,"外务省")));
        employeeMap.put(1005, new Employee(1005, "时迁", "有求必应@梁山.com", 1, new Department(106,"财务省")));

    }

    public void save(Employee employee) {
        if(employee.getId() == null) {
            employee.setId(initId++);
        }
        employeeMap.put(employee.getId(), employee);
    }

    public Collection<Employee> getAll() {
        return employeeMap.values();
    }

    public Employee getOne(int id) {
        return employeeMap.get(id);
    }

    public void delete(int id) {
        employeeMap.remove(id);
    }

}
