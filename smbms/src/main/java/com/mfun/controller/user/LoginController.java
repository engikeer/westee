package com.mfun.controller.user;

import com.mfun.pojo.User;
import com.mfun.service.user.UserService;
import com.mfun.service.user.UserServiceImpl;
import com.mfun.util.ControllerEnum;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class LoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取用户名和密码
        String userCode = req.getParameter("userCode");
        String password = req.getParameter("userPassword");

        if (userCode == null || password == null) {
            resp.sendRedirect(req.getContextPath());
        } else {
            UserService userService = new UserServiceImpl();
            try {
                User user = userService.login(userCode, password);
                if (user != null) {
                    // 登陆成功后将用户对象放入 session 中
                    req.getSession().setAttribute(ControllerEnum.USER.getValue(), user);
                    // 跳转到主页
                    resp.sendRedirect("jsp/frame.jsp");
                } else {
                    // 没有对应的用户名和密码，返回登陆页
                    req.setAttribute("error", "用户名或密码不正确");
                    req.getRequestDispatcher("login.jsp").forward(req, resp);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(req.getContextPath());
    }
}
