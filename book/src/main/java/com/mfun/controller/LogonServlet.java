package com.mfun.controller;

import com.mfun.pojo.User;
import com.mfun.service.user.UserService;
import com.mfun.service.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class LogonServlet extends HttpServlet {
    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        try {
           if (userService.logon(new User(null, username, password, email))) {
               // 注册成功，前往成功页
               resp.sendRedirect(req.getContextPath() + "/pages/user/regist_success.html");
           } else {
               // 注册失败，返回注册页
               req.getRequestDispatcher("/pages/user/regist.html").forward(req, resp);
           }

        } catch (SQLException e) {
            e.printStackTrace();
            req.getRequestDispatcher("/pages/user/regist.html").forward(req, resp);
        }
    }
}
