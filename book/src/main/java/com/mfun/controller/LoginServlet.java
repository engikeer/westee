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

public class LoginServlet extends HttpServlet {
    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            User user = userService.login(new User(null, username, password, null));
            if (user == null) {
                // 登陆失败，返回登陆页面
                req.getRequestDispatcher("/pages/user/login.html").forward(req, resp);
            } else {
                // 登陆成功，返回成功页面
                resp.sendRedirect(req.getContextPath() + "/pages/user/login_success.html");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
