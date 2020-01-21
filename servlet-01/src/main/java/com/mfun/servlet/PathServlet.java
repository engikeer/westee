package com.mfun.servlet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;

public class PathServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
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
            System.out.println("mm");

            // 当前对象调用和类调用

            writer.println("this. ：" + this.getClass().getResource(".").getPath());
            writer.println("this空：" + this.getClass().getResource("").getPath());
            writer.println("this/ ：" + this.getClass().getResource("/").getPath());
            // 访问 resources 目录下的 abc.txt 文件
            writer.println("绝对路径：abc.txt：" + this.getClass().getResource("/abc.txt").getPath());
            // 访问同一个包中的 FileServlet.class
            writer.println("相对路径：" + this.getClass().getResource("FileServlet.class").getPath());
            writer.println("相对路径：" + this.getClass().getResource("abc.txt").getPath());

            writer.println("this. ：" + PathServlet.class.getResource(".").getPath());
            writer.println("this空：" + PathServlet.class.getResource("").getPath());
            writer.println("this/ ：" + PathServlet.class.getResource("/").getPath());
            // 访问 resources 目录下的 abc.txt 文件
            writer.println("绝对路径：abc.txt ：" + PathServlet.class.getResource("/abc.txt").getPath());
            // 访问同一个包中的 FileServlet.class
            writer.println("相对路径：" + PathServlet.class.getResource("FileServlet.class").getPath());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
