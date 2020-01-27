package com.mfun.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RelativePathServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String method = req.getParameter("method");
//        if ("redirect".equals(method)) {
//            resp.sendRedirect("hello");
//        } else {
//            req.getRequestDispatcher("hello").forward(req, resp);
//        }
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println("<h1>转发前的内容</h1>");
//        req.getRequestDispatcher("hello").forward(req, resp);
        req.getRequestDispatcher("hello").include(req, resp);
        resp.getWriter().println("<h1>转发后的内容</h1>");
    }
}
