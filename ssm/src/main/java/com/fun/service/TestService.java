package com.fun.service;

import org.springframework.stereotype.Service;

@Service
public class TestService {
    public TestService() {
        System.out.println("初始化 Service");
    }

    public String getName() {
        return "Service";
    }
}
