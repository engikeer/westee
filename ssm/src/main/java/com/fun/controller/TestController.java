package com.fun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TestController {

    public TestController() {
        super();
        System.out.println("初始化Controller");
    }

    public String getName() {
        return "Controller";
    }

    @GetMapping("/context")
    public String getContext(HttpServletRequest request) {
        System.out.println("====hello====");
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        System.out.println("根容器包含 Service：" + ctx.containsBean("testService"));
        System.out.println("根容器包含 Controller：" + ctx.containsBean("testController"));

        WebApplicationContext child = WebApplicationContextUtils.getWebApplicationContext(
                request.getServletContext(),
                DispatcherServlet.SERVLET_CONTEXT_PREFIX + "dispatcherServlet");
        if (child != null) {
            System.out.println("子容器包含 Service：" + child.containsBean("testService"));
            System.out.println("子容器包含 Controller：" + child.containsBean("testController"));
            System.out.println("通过子容器获得根容器：" + (child.getParent() == ctx));
        } else {
            System.out.println("子容器未获得");
        }
        System.out.println("====over====");
        return "forward:/index.jsp";
    }

}
