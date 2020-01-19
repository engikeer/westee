package com.mfun.filter;

import com.mfun.pojo.User;
import com.mfun.util.ControllerEnum;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SysFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        User user = (User) httpRequest.getSession().getAttribute(ControllerEnum.USER.getValue());

        if (user == null) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/error.jsp");
        } else {
            chain.doFilter(request, response);
        }
    }
}
