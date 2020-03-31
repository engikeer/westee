package com.mfun.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {
    @Bean
    public Man man() {
        return new Man();
    }
}

class Man {
    public Man() {
        System.out.println("初始化Man");
    }
}
