package com.mfun.controller;

import com.mfun.pojo.User;
import com.mfun.service.user.UserService;
import com.mfun.service.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UserServlet extends BaseServlet {
    private final UserService userService = new UserServiceImpl();

    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            User user = userService.login(new User(null, username, password, null));
            if (user == null) {
                // 登陆失败，返回登陆页面
                req.setAttribute("msg", "用户名密码错误");
                req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            } else {
                // 登陆成功，返回成功页面
                resp.sendRedirect(req.getContextPath() + "/pages/user/login_success.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void logon(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        try {
            userService.logon(new User(null, username, password, email));
            // 注册成功，前往成功页
            resp.sendRedirect(req.getContextPath() + "/pages/user/regist_success.jsp");
        } catch (SQLException e) {
            // 注册失败，返回注册页
            req.setAttribute("msg", "用户已存在");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }
}
