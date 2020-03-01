package com.mfun.dao;

import com.mfun.bean.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class DepartmentDao {
    private final Map<Integer, Department> departmentMap = new HashMap<>();

    public DepartmentDao() {
        departmentMap.put(101, new Department(101,"人事院"));
        departmentMap.put(102, new Department(102,"内阁府"));
        departmentMap.put(103, new Department(103,"总务省"));
        departmentMap.put(104, new Department(104,"法务省"));
        departmentMap.put(105, new Department(105,"外务省"));
        departmentMap.put(106, new Department(106,"财务省"));
        departmentMap.put(107, new Department(107,"防卫省"));
    }

    public Collection<Department> getAll() {
        return departmentMap.values();
    }

    public Department getOne(int id) {
        return departmentMap.get(id);
    }

}
