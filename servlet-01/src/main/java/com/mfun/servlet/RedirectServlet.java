package com.mfun.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/servlet01/img");

        // 重定向实现的操作：
//        resp.setHeader("Location", "/servlet01/img");
//        resp.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
    }
}
