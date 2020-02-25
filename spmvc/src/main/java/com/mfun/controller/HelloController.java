package com.mfun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/book")
public class HelloController {

    @RequestMapping(value = "/hello", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String hello(String username) {
        System.out.println("你好：" + username);
        return "<h3>你好：" + username + "</h3>";
    }

    @RequestMapping("/page")
    public String page(@RequestParam String username, ModelMap map) {
        System.out.println("页面：" + username);
        map.put("name", username);
        return "hello";
    }

    @RequestMapping("/mav")
    public ModelAndView mav() {
        // 创建对象时传入目标视图，视图解析器会解析页面的地址
        // ModelAndView hello = new ModelAndView("hello");
        // 也可以在创建后再设置视图
        ModelAndView hello = new ModelAndView();
        hello.setViewName("hello");
        hello.addObject("msg", "你好");
        return hello;
    }

}
