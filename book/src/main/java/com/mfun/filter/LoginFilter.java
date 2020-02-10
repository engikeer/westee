package com.mfun.filter;

import com.mfun.pojo.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req =  (HttpServletRequest) request;
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            // 返回登陆
            request.setAttribute("msg", "请先登陆");
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
        } else {
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {

    }
}
