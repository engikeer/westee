package com.mfun.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class SessionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter writer = resp.getWriter();

        // 检查 session 是否是新建的
        if (session.isNew()) {
            writer.println("<h3>Session 是新建的</h3>");
        } else {
            writer.println("<h3>Session 已存在</h3>");
        }

        // 向 session 存入对象
        // 可存入任意类型的对象
        session.setAttribute("name", "罗辑");

        // 获取 sessionID，就是 cookie 中保存的 SESSIONID
        String id = session.getId();

        // 获取 session 中保存的属性
        String name = (String) session.getAttribute("name");

        writer.println("<p>Session ID：" + id + "</p><br>");
        writer.println("<p>姓名：" + name + "</p>");

        // 删除 session 的属性
        session.removeAttribute("name");

        // 手动注销 session，但再次打开浏览器就会生成新的 session
        session.invalidate();
        // 之后再用原 session 对象调用方法会抛出 NPE，但调用 req.getSession() 可立即获得新的 session
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req, resp);
    }
}
