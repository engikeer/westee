package com.mfun.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

@Configuration
public class MyConfig {

    @Bean
    public ViewResolver myViewResolver() {
        return new MyResolver();
    }

}

class MyResolver implements ViewResolver {

    @Override
    public View resolveViewName(String viewName, Locale locale) {
        return null;
    }
}

