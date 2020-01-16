package com.mfun.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 通过监听 session 数统计在线人数
 */
public class OnlineCountListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // 将在线人数保存在应用作用域中
        HttpSession session = se.getSession();
        ServletContext context = session.getServletContext();
        Integer onlineCount = (Integer) context.getAttribute("OnlineCount");
        if (onlineCount == null) {
            onlineCount = 1;
        } else {
            onlineCount++;
        }
        context.setAttribute("OnlineCount", onlineCount);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("session 销毁");
        HttpSession session = se.getSession();
        ServletContext context = session.getServletContext();
        Integer onlineCount = (Integer) context.getAttribute("OnlineCount");
        context.setAttribute("OnlineCount", onlineCount -1);

    }
}
