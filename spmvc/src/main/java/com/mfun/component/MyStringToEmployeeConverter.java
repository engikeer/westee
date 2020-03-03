package com.mfun.component;

import com.mfun.bean.Employee;
import com.mfun.dao.DepartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MyStringToEmployeeConverter implements Converter<String, Employee> {
    private DepartmentDao departmentDao;

    @Autowired
    public MyStringToEmployeeConverter(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public Employee convert(String source) {
        if (source.contains("-")) {
            String[] strings = source.split("-");
            Employee employee = new Employee();
            employee.setName(strings[0]);
            employee.setEmail(strings[1]);
            employee.setGender(Integer.parseInt(strings[2]));
            employee.setDepartment(departmentDao.getOne(Integer.parseInt(strings[3])));
            return employee;
        } else {
            return null;
        }
    }
}
