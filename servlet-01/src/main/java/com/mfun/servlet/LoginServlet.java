package com.mfun.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置请求的编码
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println("登陆信息：" + username + "-" + password);
        String[] hobby = req.getParameterValues("hobby");
        for (String h : hobby) {
            System.out.println(h);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("hello");
        dispatcher.forward(req, resp);

//        resp.sendRedirect("/servlet01/hello");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
