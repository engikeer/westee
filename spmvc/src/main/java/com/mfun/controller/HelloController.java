package com.mfun.controller;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/book")
public class HelloController {

    @RequestMapping(value = "/hello", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String hello(@RequestParam String username) {
        System.out.println("你好：" + username);
        return "<h3>你好：" + username + "</h3>";
    }

    @RequestMapping("/page")
    public String page(@RequestParam String username) {
        System.out.println("页面：" + username);
        return "hello";
    }

}
