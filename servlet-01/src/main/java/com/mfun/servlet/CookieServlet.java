package com.mfun.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 目的：保存用户上次访问的时间

        req.setCharacterEncoding("UTF-8");

        // 从客户端获取携带的 cookie
        // 返回 cookie 数组，一个域名可在客户端保存多个 cookie
        // 如果不存在用于本域名的 cookie，返回 null
        Cookie[] cookies = req.getCookies();
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        boolean visited = false;
        String dateString = "";
        String name = "";
        // 判断是否已存在 cookie
        if (cookies != null) {
            // 存在则取出需要的数据：lastLogin，跳过其他名称的 cookie
            // 注意，浏览器不保证遍历顺序，所以不能将输出的顺序依赖于遍历顺序
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("lastLogin")) {
                    long millis = Long.parseLong(cookie.getValue());
                    Date date = new Date(millis);
                    dateString = format.format(date);
                    visited = true;
                } else if (cookie.getName().equals("name")) {
                    name = cookie.getValue();

                }
            }
        }

        if (visited) {
            writer.println("<h3>上次登陆时间：" + dateString + "</h3>");
            // Safari 浏览器需要对 cookie 中的非英文字符进行编码和解码，否则无法写入。Chrome 可直接写入。
            writer.println("<p>欢迎：" + URLDecoder.decode(name, "UTF-8") + "</p>");

        } else {
            // 名为 lastLogin 的 cookie 不存在
            // 写出 未访问过，并在之后添加登陆时间
            writer.println("<h3>您之前未访问过该页面</h3>");
        }

        // 添加登陆时间，如果已存在则会更新
        Cookie lastLoginCookie = new Cookie("lastLogin", System.currentTimeMillis() + "");
        // 将 cookie 的有效期设置为 3 天
        lastLoginCookie.setMaxAge(3 * 24 * 60 * 60);
        resp.addCookie(lastLoginCookie);
        // Safari 浏览器需要对 cookie 中的非英文字符进行编码和解码，否则无法写入。Chrome 可直接写入。
        Cookie nameCookie = new Cookie("name", URLEncoder.encode("庄颜", "UTF-8"));
        nameCookie.setMaxAge(3 * 24 * 60 * 60);
        resp.addCookie(nameCookie);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
