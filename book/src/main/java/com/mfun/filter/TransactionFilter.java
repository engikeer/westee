package com.mfun.filter;

import com.mfun.utils.ConnectionUtils;
import com.mfun.utils.ThreadConnection;
import com.sun.xml.internal.bind.v2.TODO;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try (Connection conn = ThreadConnection.getCurrentConnection()) {
            conn.setAutoCommit(false);
            // 要用该连接执行所有的 sql，BaseDaoImpl 中 Runner 要使用 ThreadConnection.getCurrentConnection() 作为连接
            // 这种通过异常将消息传递到过滤器的方法太过繁琐且容易出错
            // 不仅需要详细地区分异常类型，保证需要的异常没有在中途被抑制；
            // 还要通过线程来识别连接，因为事务控制的代码与实际执行查询的代码相距甚远
            try {
                chain.doFilter(request, response);
                conn.commit();
            } catch (IOException e) {
                conn.rollback();
                // 实践中应该返回错误页面并给出提示信息
//                request.getRequestDispatcher("/error.jsp").forward(request, response);
                response.setContentType("text/html");
                response.getWriter().println("<h3>提交数据时发生错误</h3>");
            }
        } catch (SQLException e) {
            System.out.println("获取连接失败");
            e.printStackTrace();
        } finally {
            ThreadConnection.removeCurrentConnection();
        }
    }

    @Override
    public void destroy() {

    }
}
