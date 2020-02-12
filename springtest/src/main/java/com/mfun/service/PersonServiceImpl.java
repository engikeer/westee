package com.mfun.service;

import com.mfun.bean.Person;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService{

    @Override
    public Person getPerson() {
        Person p = new Person();
        p.setName("Service");
        return p;
    }
}
