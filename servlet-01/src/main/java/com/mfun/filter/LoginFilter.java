package com.mfun.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 使用常量可避免错误，便于修改（值需改变只要修改常量，不需要修改所有位置）
        // String userSession = (String) session.getAttribute(Constants.USER_SESSION);
        String userSession = (String) session.getAttribute("USER_SESSION");

        if (userSession == null) {
            // 未登陆则重定向到失败页面
            httpResponse.sendRedirect("/failure.jsp");
        }

        chain.doFilter(httpRequest, httpResponse);
    }

    @Override
    public void destroy() {

    }
}
