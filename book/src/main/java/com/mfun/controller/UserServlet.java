package com.mfun.controller;

import com.mfun.pojo.User;
import com.mfun.service.user.UserService;
import com.mfun.service.user.UserServiceImpl;
import com.mfun.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class UserServlet extends BaseServlet {
    private final UserService userService = new UserServiceImpl();

    private void login(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            User requestUser = ServletUtils.param2bean(req, User.class);
            User user = userService.login(requestUser);
            if (user == null) {
                // 登陆失败，返回登陆页面
                req.setAttribute("msg", "用户名密码错误");
                req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            } else {
                // 登陆成功，返回成功页面
                resp.sendRedirect(req.getContextPath() + "/pages/user/login_success.jsp");
            }
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
            resp.getWriter().println("请求参数值错误");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void logon(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            User user = ServletUtils.param2bean(req, User.class);
            userService.logon(user);
            // 注册成功，前往成功页
            resp.sendRedirect(req.getContextPath() + "/pages/user/regist_success.jsp");
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
            resp.getWriter().println("请求参数值错误");
        } catch (SQLException e) {
            // 注册失败，返回注册页
            req.setAttribute("msg", "用户已存在");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }

    }
}
