package com.mfun.servlet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class PathServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        // 测试获取磁盘路径
        try (PrintWriter writer = resp.getWriter()) {
            // 方法 1： File，获得了服务器启动目录
            writer.println("======== File ========");
            File file = new File("./abc.txt");
            writer.println(".：" + file.getAbsolutePath());
            writer.println("空：" + new File("abc.txt").getAbsolutePath());
            writer.println("/：" + new File("/abc.txt").getAbsolutePath());

            // 方法2：ServletContext，获得了项目资源的真实目录
            writer.println("======== ServletContext ========");
            ServletContext application = req.getServletContext();
            writer.println(".：" + application.getRealPath("./abc.txt"));
            writer.println("空：" + application.getRealPath("abc.txt"));
            writer.println("/：" + application.getRealPath("/abc.txt"));
        }

    }
}
